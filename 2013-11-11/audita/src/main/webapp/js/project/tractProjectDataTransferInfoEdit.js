$(document).ready(function() {
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var url = $("#coutextPath").val() + '/project/tractProjectDataTransfer/addWord.do'
			$("#form").ajaxSubmit({
				url : url,
				success : function(data) {
					var data = eval("(" + data + ")");
					if (data.isSuccess) {
						top.showMsg(data.msg);
						top.reloadModule($("#frameId").val());
						top.closeModule(moduleName, parentModuleName);
					} else  {
						top.showMsg(data.msg);
						top.reloadModule($("#frameId").val());
					}
				}
			});
		}
	});
	
	$("#addfile").click(function(){
		   var tr=$("<tr><td class='label' align='right'>其他附件 ：</td>"+
					"<td colspan='3' align='left'><input  type='file' style='width: 400px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
					"iconCls='icon-cancel'>删除</a></td></tr>");
				$("#files").before(tr);
				pheight = $("#p1").height() + 60;
				$('#p1').panel({
					height : pheight
				});
				tr.find(".deletefile").click(function(){
					pheight = pheight - 30;
					$('#p1').panel({
						height : pheight
					});
					$(this).parent().parent().remove();
				});
				$(".easyui-linkbutton").linkbutton({
					plain:false
				});
		});
	
	if ($("#comprehensiveReportFileDownload").text()) {
		$("#comprehensiveReportFile").hide();
	}
	
	$("#deletecomprehensiveReportFile").click(function() {
		$("#deletecomprehensiveReportFile").hide();
		$("#comprehensiveReportFileDownload").hide();
		$("#comprehensiveReportFile").show();
	});
	
	$("#projoinlist").click(function (){
		top.openModule($("#frameid").val(),'资料交接清单','/project/tractProjectDataTransfer/input.do',null,'projoinlist',$("#id").val());
	});
	initFile();
});

function download(url) {
	window.location = encodeURI(encodeURI($("#coutextPath").val()
			+ '/common/project/download.do?url=' + url));
			return false;
}

/**
 * 初始化文件列表
 */
function initFile(){
	$.post($("#coutextPath").val() + '/common/project/findFile.do', {
		method : $("#id").val()
	}, function(data, textStatus) {
		if (data.total == '0') {
			$("#grid").hide();
		} else {
			$("#grid").show();
			initializationfile();
		}
	});
}

function initializationfile() {
    var width = $("#p1").width() ;
	$('#grid').datagrid({   
	      url:$("#coutextPath").val()+'/common/project/findFile.do?method='+ $("#id").val(),   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.2),sortable:true},
	         {field:'uploadTime',title:'上传时间',width:fillsize(0.2),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return todate(value);
                 }
	         },
             {field:'OPERATION',title:'操作',width:fillsize(0.15) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '<a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delFile('+id+');return false;">删除</a>';
             }
          }
	      ]] ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width
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

//删除
function delFile(id){
	$.ajax({
		url : $("#coutextPath").val() + '/common/project/deleteFile.do',
		type : "POST",
		dataType : "json",
		data : "key=" + id,
		success : function(result) {
			if (result.success == "success") {
				$.messager.show({
					title : '提示',
					msg : result.msg,
					timeout : 3000
				});
				$("#grid").datagrid("reload");
			} else {
				$.messager.show({
					title : '提示',
					msg : result.msg,
					timeout : 3000
				});
				return false;
			}
		}
	});
	initFile();
}