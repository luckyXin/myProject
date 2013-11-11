var pheight = null;
$(document).ready(function() {
			$("#save").click(
					function() {
						if ($('#form').form('validate')) {
							var url = $("#coutextPath").val() + '/project/tractProjectChangeCard/addWord.do';
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
			$("#changeType").change(function (){
				initializationBiaoDuan();
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
						'标段-月报添加','/project/tractProjectChangeCard/input.do',
						null, 'addChangeCard')
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
						'标段-月报编辑','/project/tractProjectChangeCard/input.do',
						null, 'addChangeCard',row.id);
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
				$.post($("#coutextPath").val()+"/project/tractProjectChangeCard/delete.do", {id : row.id},
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
	$.post($("#coutextPath").val()+'/common/project/findChangeCardFile.do' , {
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
	var url = $("#coutextPath").val()
			+ '/project/tractProjectChangeCard/find.do?changeCardState=0&biaoDuanId='
			+ $("#biaoDuanId").val()
			+'&changeType='+$("#changeType").val();
	$('#grid').datagrid({
		url : url,
		columns : [ [ {
			field : 'changeCode',
			title : '变更签证编号',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'changeTime',
			title : '变更时间',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'changeContext',
			title : '变更内容',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'constructSentMoney',
			title : '施工单位报送变更金额',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'tractAuditAdviceMoney',
			title : '跟踪审计建议变更金额',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'affirmChangeMoney',
			title : '确认变更金额',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'changeProccessCondition',
			title : '变更执行情况',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'changeType',
			title : '签证类型',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == '0') {
					return "清单漏项";
				} else if (value == '1') {
					return "清单量差";
				} else if (value == '2') {
					return "设计变更";
				} else if (value == '3') {
					return "技术核定";
				} else if (value == '4') {
					return "现场签证";
				} else if (value == '5') {
					return "其他";
				} else if (value == '6') {
					return "政策规范性设计变更";
				} else {
					return "";
				}
			}
		}
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [  10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
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
    var width = $("#p10").width() ;
    var height = $("#p10").height();
	 $('#gridFile').datagrid({
	      url:$("#coutextPath").val()+'/common/project/findChangeCardFile.do?biaoDuanId='+ $("#biaoDuanId").val(),   
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