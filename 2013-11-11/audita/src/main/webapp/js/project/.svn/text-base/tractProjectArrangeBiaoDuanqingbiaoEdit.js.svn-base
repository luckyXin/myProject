$(document).ready(function() {
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					var url = $("#coutextPath").val() + '/project/tractQingBiao/add.do'
					if ($("#id").val() != ""){
						url = $("#coutextPath").val() + '/project/tractQingBiao/update.do'
					}
					$("#form").ajaxSubmit({
						url : url,
						success : function(data) {
						var data = eval("(" + data + ")");
						    
							if (data.success == "success") {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
								top.closeModule(moduleName, parentModuleName);
							} else  {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
							}
						}
					});
				}
			});
		    //查询清标上传文件
			lookqbfile();
	
			// 清标后金额
			$('#afterMoney').numberbox({
				precision : 2
			});
			$('#maxUtilPrice').numberbox({
				precision : 2
			});
			$('#bidUtilPrice').numberbox({
				precision : 2
			});
			$('#exceedOneTwenty').numberbox({
				precision : 2
			});
			$('#underEighty').numberbox({
				precision : 2
			});
			$('#exceedControllerPrice').numberbox({
				precision : 2
			});
			$('#underControllerPrice').numberbox({
				precision : 2
			});  
});

function addfileqingbiao(){
	 var tr=$("<tr><td class='label' align='right'>清标资料：</td>"+
				"<td colspan='3' align='left'><input  type='file' style='width: 300px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
				"iconCls='icon-cancel'>删除</a></td></tr>");
			$("#files").before(tr);
			tr.find(".deletefile").click(function(){
				$(this).parent().parent().remove();
			});
			$(".easyui-linkbutton").linkbutton({
				plain:false
			});
}

/**
 * 主审人员回调函数
 * @param data
 */
function returnMainAuditEmployee(data) {
	$("#mainAuditId").val(data.id);
	$("#mainAuditName").val(data.userName);
	$('#form').form('validate');
	close();
}

function returnbackguyuanProject(data){
	for(var j=0;j<data.length;j++){
		var hiddenGovernmentEmployee = $('<input type="hidden" class="governmentEmployee" id="governmentEmployeeId" name="governmentEmployeeId">');
		var governmentEmployee = $("#governmentEmployee").val();
		var count = 0;
		for ( var i = 0; i < governmentEmployee.length; i++) {
			if ("," == governmentEmployee.charAt(i)) {
				count++;
			}
		}
		for ( var k = 0; k < $(".governmentEmployee").length; k++) {
			var stepvar = $(".governmentEmployee:eq(" + k + ")").val();
			if (data[j].id == stepvar) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : "已经存在"
				});
				return;
			}
		}
		if ("" == governmentEmployee) {
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployeeId").val(data[j].id);
			$("#governmentEmployee").val(data[j].userName);
		} else if (count == 4) {
			$(".governmentEmployee:eq(0)").val(data[j].id);
			var strs = new Array();
			strs = governmentEmployee.split(",");
			strs[4] = data[j].userName;
			var governmentEmployees = "";
			for ( var j = 0; j < strs.length; j++) {
				if (j == 0) {
					governmentEmployees += strs[j];
				} else {
					governmentEmployees += ',' + strs[j];
				}
			}
			$("#governmentEmployee").val(governmentEmployees);
		} else {
			hiddenGovernmentEmployee.attr("value", data[j].id);
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployee").val(governmentEmployee + "," + data[j].userName);
		}
		$('#form').form('validate');
    }
	close();
}

function lookqbfile() {
	$('#grid').datagrid({   
	      url:$("#coutextPath").val()+'/common/project/findFile.do?method='+ $("#id").val(),   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.2),sortable:true},
	         {field:'uploadTime',title:'上传时间',width:fillsize(0.1),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return todate(value);
                 }
	         },
	         {field:'state',title:'来源',width:fillsize(0.1),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return "清标";
                 }
	         },
             {field:'OPERATION',title:'操作',width:fillsize(0.15) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '<a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delFile('+id+');return false;">删除</a>';
             }
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true
	  }); 
    //设置分页控件   
    var p = $('#grid').datagrid('getPager');   
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10   
        pageList: [5,10,15],//可以设置每页记录条数的列表   
        beforePageText: '第',//页数文本框前显示的汉字   
        afterPageText: '页    共 {pages} 页',   
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
    });
}

//下载
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
	
}//删除
function delFile(id){
		$.ajax( {
			url :  $("#coutextPath").val() + '/common/project/deleteFile.do',
			type : "POST",
			dataType : "json",
			data : "key=" + id,
			success : function(result) {
			  if(result.success=="success")
			  {
				  $.messager.show({
			    		title:'提示',
						msg: result.msg,
						timeout:3000
			       });
				  $("#grid").datagrid("reload");
			  }else{
				  $.messager.show({
			    		title:'提示',
						msg: result.msg,
						timeout:3000
			       });
				  return false;
			  }
			   
			}
		});
}


function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 50) * percent;
}