$(document).ready(function() {
	$("#remark").val(top.textTrim($("#remark").val()));
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var frameid=$("#frameid").val();
			var url = $("#coutextPath").val() + '/project/policychang/add.do'
			if ($("#id").val() != ""){
				url = $("#coutextPath").val() + '/project/policychang/update.do'
			}
			$("#form").ajaxSubmit({
				url : url,
				success : function(data) {
				var data = eval("(" + data + ")");
					if (data.success == "success") {
						top.showMsg(data.msg);
						top.reloadModule(frameid+"add");
						top.closeModule(moduleName, parentModuleName);
					} else  {
						top.showMsg(data.msg);
						top.reloadModule($("#frameid").val());
					}
				}
			});
		}
	});
	if ($("#id").val() != ""){
		findrengongtz($("#id").val());
		loadrengong($("#id").val());
	}

//金额
	$('#otherContext').numberbox({   
		   precision:2   
	}); 
	$('#datamoney').numberbox({   
		   precision:2   
	}); 
	$('#machinemoney').numberbox({   
		   precision:2   
	}); 
/*	  $("#addfile").click(function(){
		   var tr=$("<tr><td class='label' align='right'>政策性调整资料：</td>"+
					"<td colspan='3' align='left'><input  type='file' style='width: 400px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
					"iconCls='icon-cancel'>删除</a></td></tr>");
				$("#files").before(tr);
				tr.find(".deletefile").click(function(){
					$(this).parent().parent().remove();
				});
				$(".easyui-linkbutton").linkbutton({
					plain:false
				});
	});*/
	
	
	
	  $("#add").click(function(){
			
			top.openModule($("#frameid").val(),'添加人工费用调整','/project/policychang/input.do',null, 'addrengong', $("#id").val());
	   });
	  
	  $("#edit").click(function(){
			var row = $('#gridtz').datagrid('getSelected');
			if (row == null || row.id == null) {
				top.$.messager.show({
					title : '提示',
					msg : '请选择一行数据'
				});
				return;
			}
			top.openModule($("#frameid").val(),'修改人工费用调整','/project/policychang/input.do',null, 'updaterengong', row.id);
		});
	  $("#delete").click(function(){
			var row = $('#gridtz').datagrid('getSelected');
			if (row == null || row.id == null) {
				top.$.messager.show({
					title : '提示',
					msg : '请选择一行数据'
				});
				return;
			}
			deleterengong(row.id);
			
		});
});

function lookchengcefile() {
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
            	   return "政策性调整";
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
	      striped:true,
	  	  height : 310
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

//查询人工调整信息列表
function findrengongtz(id) {
	$('#gridtz').datagrid({   
	      url:$("#coutextPath").val()+'/common/project/findrengongtz.do?id='+ id,   
	      columns:[[   
	         {field:'adjustmentwh',title:'调整文号',width:fillsize(0.1),sortable:true},
	         {field:'delabourmoney',title:'定额人工费',width:fillsize(0.2),sortable:true},
	         {field:'tzlv',title:'调整费率',width:fillsize(0.1),sortable:true},
	         {field:'mantzmoney',title:'人工费调整金额',width:fillsize(0.2),sortable:true},
	         {field:'tztime',title:'调整时间',width:fillsize(0.2),sortable:true}
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height : 310
	  }); 
    //设置分页控件   
    var p = $('#gridtz').datagrid('getPager');   
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
function deleterengong(id){
		$.ajax( {
			url :  $("#coutextPath").val() + '/common/project/deleterengong.do',
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
				  reloadGrid();
				  loadrengong(id);
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
function reloadGrid(){
	$("#gridtz").datagrid("reload");
	loadrengong($("#id").val());
}

//查询人工费用
function loadrengong(id){
	$.ajax( {
		url :  $("#coutextPath").val() + '/common/project/findtotalrengong.do',
		type : "POST",
		dataType : "json",
		data : "key=" + id,
		success : function(result) {
			 $("#artificialfile").val(result.msg);
		  }
		   
	});
}

