$(document).ready(
		function() {
			var isfind = $("#find").val();
			var isinput = $("#input").val();
			if (isinput != '1') {
				$.messager.show({
					title : $("#title").val(),
					msg : $("#noPower").val()
				});
				return false;
			}
			if (null != isfind && "null" != isfind && "" != isfind
					&& undefined != isfind) {
				// 初始化列表
				var infoTypeValue = $("#infoType").combobox("getValue");
				if (infoTypeValue == 1) {
					$("#ownerName").hide();
					$("#projectName").hide();
					$("#reportBatch").show();
					$("#assignCode").show();
					$("#title1").html("项目批次：");
					$("#title2").html("交办文号：");
					initialization();
				} else {
					$("#ownerName").show();
					$("#projectName").show();
					$("#reportBatch").hide();
					$("#assignCode").hide();
					$("#title1").html("项目名称：");
					$("#title2").html("业主名称：");
					initializationprjectInfo()
				}
				$('#p1').show();
				$('#p2').show();
				var height = document.body.clientHeight;
				var width = $(document.body).width() - 20;
				$('#p1').panel({
					title : '查询条件',
					iconCls : 'icon-search'
				});
				$('#p2').panel({
					title : '政府交办',
					iconCls : 'icon-tip'
				});
			}
			$("#add").click(
					function() {
						top.openModule($("#frameId").val(), '政府交办批次添加',
								'/project/governmentAssign/input.do', null,
								'add', null);
					});
			$("#assignCodeAdd").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null || row.id == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : '请选择需要录入的项目'
							});
							return;
						}
						top.openModule($("#frameId").val(), '政府文号添加',
								'/project/governmentAssign/input.do', null,
								'assignCodeAddState', row.id);
					});
			$("#update").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null || row.id == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : '请选择需要录入的项目'
							});
							return;
						}
						top.openModule($("#frameId").val(), '政府文号添加',
								'/project/governmentAssign/input.do', null,
								'assignCodeAddState', row.id);
					});
			$("#search").click(function() {
				var infoTypeValue = $("#infoType").combobox("getValue");
				if (infoTypeValue == 1) {
					$("#grid").datagrid('options').queryParams = {
						reportBatch : $("#reportBatch").val(),
						assignCode : $("#assignCode").val()
					};
					initialization();
				} else {
					$("#grid").datagrid('options').queryParams = {
						projectName : $("#projectName").val(),
						ownerName : $("#ownerName").val()
					};
					initializationprjectInfo()
				}
			});

			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350, 270, null);
			});

			$("#infoType").combobox({
				onSelect : function() {
					var infoTypeValue = $("#infoType").combobox("getValue");
					if (infoTypeValue == 1) {
						$("#ownerName").hide();
						$("#projectName").hide();
						$("#reportBatch").show();
						$("#assignCode").show();
						$("#title1").html("项目批次：");
						$("#title2").html("交办文号：");
						initialization();
					} else {
						$("#ownerName").show();
						$("#projectName").show();
						$("#reportBatch").hide();
						$("#assignCode").hide();
						$("#title1").html("项目名称：");
						$("#title2").html("业主名称：");
						initializationprjectInfo()
					}
				}
			});
			$("#delete").click(
 					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row != null) {
							$.post($("#coutextPath").val()
									+ "/project/governmentAssign/destroy.do", {
								id : row.id,
								state : '2'
							}, function(data, textStatus) {
								top.$.messager.show({
									title : $("#title").val(),
									msg : data.msg
								});
								reloadGrid();
							});
						} else {
							top.$.messager.show({
								title : $("#title").val(),
								msg : $("#noSelect").val()
							});
							
						}
					});
		});

function initializationprjectInfo() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	var path = $("#coutextPath").val();
	$("#add").show();
	$("#assignCodeAdd").hide();
	$("#update").hide();
	$("#delete").hide();
	var projectType = $("#projectType").combobox("getValue");
	
	var url;
	if (projectType == 0) {
		url = path + '/project/governmentAssign/find.do?findProjectState=1';
	} else if (projectType == 1) {
		url = path
				+ '/project/governmentAssign/find.do?assignProjectInfoState=1';
	} else {

		url = path
				+ '/project/governmentAssign/find.do?allAssignProjectInfoState=1';
	}

	$('#grid').datagrid({
		url : url,
		idField : 'id',
		columns : [ [ 
		              
		              {
			field : 'datapreno',
			title : '项目编号',
			width : fillsize(0.1),
			sortable : true
		},{
			field : 'projectName',
			title : '项目名称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'ownerName',
			title : '项目业主',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'sentAmount',
			title : '送审金额（元）',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'datapreTime',
			title : '接收资料时间',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'assignState',
			title : '交办状态',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {

					return "未交办";
				} else if (value == 1) {

					return "已交办";
				}
			}
		},
		{
			field : 'reportBatch',
			title : '项目批次',
			width : fillsize(0.2),
			sortable : true
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

function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	var path = $("#coutextPath").val();
	$("#add").hide();
	$("#assignCodeAdd").show();
	$("#update").show();
	$("#delete").show();
	$('#grid')
			.datagrid(
					{
						url : path + '/project/governmentAssign/find.do',
						columns : [ [
								{
									field : 'reportBatch',
									title : '项目批次',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'reportTime',
									title : '报批时间',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'assignCode',
									title : '政府交办文号',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'assignTime',
									title : '政府交办时间',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'OPERATION',
									title : '操作',
									width : fillsize(0.2),
									sortable : false,
									formatter : function(value, rowData,
											rowIndex) {
										var id = "'" + rowData.id + "'";
										var assignTime = rowData.assignTime;
										assignTime = "'" + assignTime + "'";
										return '<a href="javascript:void(0);" onclick="createword('+ id+ ','+ assignTime+ ',1);return false;">审计请示汇总</a>';

									}
								} ] ],
						singleSelect : true,
						pagination : true,
						striped : true,
						height : height - 200
					});

	// 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 50) * percent;
}

function reloadGrid() {
	$("#grid").datagrid("reload");
}
// 处理时间
function todate(time) {
	if (null != time && "" != time) {
		var date = new Date(time);
		var year = date.getFullYear();
		var month = (date.getMonth() + 1);
		var day = date.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		return year + "-" + month + "-" + day;
	} else {
		return "";
	}
}

function createword(id, time, method) {
	window.location = $("#coutextPath").val()+ '/common/project/shenjihuizong.do?id=' + id;
	return false;
}