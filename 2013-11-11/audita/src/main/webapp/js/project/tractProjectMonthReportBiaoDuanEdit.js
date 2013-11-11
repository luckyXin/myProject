var pheight = null;
$(document).ready(function() {
			$("#save").click(
					function() {
						if ($('#form').form('validate')) {
							var url = $("#coutextPath").val() + '/project/tractMonthReport/addWord.do'
							$("#form").ajaxSubmit({
										url : url,
										success : function(data) {
											var data = eval("(" + data + ")");
											if (data.isSuccess) {
												top.showMsg(data.msg);
												initializationBiaoDuan();
												initFile();
											} else {
												top.showMsg(data.msg);
											}
										}
							});
						}
					});
			
			$("#addfile").click(function(){
				   var tr=$("<tr><td class='label' align='right'>附件 ：</td>"+
							"<td colspan='3' align='left'><input  type='file' style='width: 400px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
							"iconCls='icon-cancel'>删除</a></td></tr>");
						$("#files").before(tr);
						pheight = $("#p12").height() + 60;
						$('#p12').panel({
							height : pheight
						});
						tr.find(".deletefile").click(function(){
							pheight = pheight - 30;
							$('#p12').panel({
								height : pheight
							});
							$(this).parent().parent().remove();
						});
						$(".easyui-linkbutton").linkbutton({
							plain:false
						});
				});
			
			$("#add").click(function (){
				top.openModule($("#frameId").val(),
						'标段-月报添加','/project/tractMonthReport/input.do',
						null, 'addMonth')
			});
			
			$("#update").click(function (){
				var row = $('#grid').datagrid('getSelected');
				if (row == null || row.id == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : $("#noSelect").val()
					});
					return;
				}
				top.openModule($("#frameId").val(),
						'标段-月报编辑','/project/tractMonthReport/input.do',
						null, 'addMonth',row.id)
			});
			
			$("#delete").click(function (){
				var row = $('#grid').datagrid('getSelected');
				if (row == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : '没有选择项目，无法删除'
					});
					return;
				}
				$.post($("#coutextPath").val()+"/project/tractMonthReport/delete.do", {id : row.id},
						function (data, textStatus){
					$.messager.show({
						title : "提示",
						msg : data.msg
				});
				reloadGrid();
				});
			});
			initializationBiaoDuan();
			initFile();
		});

/**
 * 初始化文件列表
 */
function initFile(){
	$.post($("#coutextPath").val()+'/common/project/findMonthReportFile.do' , {
		biaoDuanId : $("#biaoDuanId").val()
	}, function(data, textStatus) {
		if (data.total == '0') {
			$("#gridFile").hide();
		} else {
			$("#gridFile").show();
			initializationfile();
		}
	});
}

function initializationBiaoDuan() {
	var width = $("#p13").width() ;
	var url = $("#coutextPath").val()
			+ '/project/tractMonthReport/find.do?monthReprotState=0&biaoDuanId='
			+ $("#biaoDuanId").val();
	$('#grid').datagrid({
		url : url,
		columns : [ [ {
			field : 'createTime',
			title : '创建时间',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'createUserAccount',
			title : '录入人员',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'nowMonthCompleteValue',
			title : '当月完成产值(万元)',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'totalCompleteValue',
			title : '累计完成产值(万元)',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'nowMonthPayValue',
			title : '当月支付产值(万元)',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'updateTime',
			title : '最终更新时间',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'updateUserAccount',
			title : '更新人员',
			width : fillsize(0.12),
			sortable : true
		}
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width
	});
	// 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initPage(pageSize){
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initializationfile() {
    var width = $("#p12").width() ;
    var height = $("#p12").height();
	 $('#gridFile').datagrid({
	      url:$("#coutextPath").val()+'/common/project/findMonthReportFile.do?biaoDuanId='+ $("#biaoDuanId").val(),   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.5),sortable:true},
	         {field:'uploadTime',title:'上传时间',width:fillsize(0.3),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return todate(value);
                 }
	         },
             {field:'OPERATION',title:'操作',width:fillsize(0.18) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '<a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delFile('+id+');return false;">删除</a>';
             }
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width
	  }); 
	    //设置分页控件   
	    var p = $('#gridFile').datagrid('getPager');   
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
				$("#gridFile").datagrid("reload");
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
	initializationfile();
}

function reloadGrid() {
	$('#grid').datagrid("reload");
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 10) * percent;
}