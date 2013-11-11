$(document).ready(function() {
			var isfind = $("#find").val();
			var isinput = $("#input").val();
			if (isinput != '1') {
				top.$.messager.show({
					title : $("#title").val(),
					msg : $("#noPower").val()
				});
			}
			if (!isfind) {
				$('#p1').hide();
				$('#p2').hide();
				top.$.messager.show({
					title : $("#title").val(),
					msg : "没有查询权限"
				});
				return;
			}
			$("#reset").click(function(){
				$('#from').form('clear');
			});
			$("#search").click(function() {
				$("#grid").treegrid('options').queryParams = {
					projectOwner : $("#ownerId").val(),
					projectName : $("#projectName").val(),
					projectType : $("#projectType").combobox("getValue"),
					projectTransferStartTime : $("#projectTransferStartTime").datebox("getValue"),
					projectTransferEndTime : $("#projectTransferEndTime").datebox("getValue"),
					constructUtil : $("#constructUtil").val()
				};
				initialization();
			});

			$("#audit").click(
					function() {
						var row = $('#grid').treegrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "未选择标段"
							});
						}
						if (row.id.length != 32) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "未选择标段"
							});
							return;
						}
						top.openModule($("#frameId").val(), '项目明细展示',
								'/search/relevantTractProject/input.do', null,
								'showProject', row.id);
					});
			
			initialization();
			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});
			$("#employeeName").click(function() {
				openshowdiv(3, "人员查询", 600, 400, 220, null);
			});
		});

/**
 * 中介机构安排回调函数
 */
function returnIntermediary(rowData) {
	$("#intermediaryId").val(rowData.id);
	$("#intermediaryName").val(rowData.intermediaryname);
	close();
}

/**
 * 主审人员回调函数
 * 
 * @param data
 */
function returnMainAuditEmployee(data) {
	$("#employeeId").val(data.id);
	$("#employeeName").val(data.userName);
	close();
}
/**
 * 项目业主回调方法
 * 
 * @param rowData
 */
function returnback(rowData) {
	$("#ownerName").val(rowData.ownerName);
	$("#ownerId").val(rowData.id);
	close();
}

/**
 * 初始化项目列表
 */
function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').treegrid({
		url : $("#coutextPath").val() + '/search/relevantTractProject/find.do',
		treeField : 'projectName',
		animate : true,
		idField : 'id',
		columns : [ [ {
			field : 'projectName',
			title : '项目名称',
			width : fillsize(0.1)
		}, {
			field : 'ownerName',
			title : '项目业主',
			width : fillsize(0.1)
		}, {
			field : 'projectType',
			title : '招标类型',
			width : fillsize(0.05),
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "招标";
				} else if(value == 1) {
					return "EPC";
				} else if (value == 2) {
					return "BT";
				} else {
					return "";
				}
			}
		}, {
			field : 'biaoDuanName',
			title : '标段名称',
			width : fillsize(0.1)
		}, {
			field : 'constructUtil',
			title : '施工单位',
			width : fillsize(0.1)
		}, {
			field : 'intermediaryName',
			title : '中介机构',
			width : fillsize(0.1)
		}, {
			field : 'projectGaiKuang',
			title : '工程概况',
			width : fillsize(0.1)
		}, {
			field : 'projectTransferStartTime',
			title : '项目移交时间',
			width : fillsize(0.1)
		}, {
			field : 'dataTransferState',
			title : '是否移交',
			width : fillsize(0.05),
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "已移交";
				} else {
					if (rowData._parentId) {
						return "未移交";
					} else {
						return "";
					}
				}
			}
		}, {
			field : 'isArrange',
			title : '安排状态',
			width : fillsize(0.05),
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "未安排";
				} else if (value == 1) {
					return "已安排";
				}
			}
		}, {
			field : 'mainAuditName',
			title : '主审',
			width : fillsize(0.05)
		}, {
			field : 'reexamineNames',
			title : '复审',
			width : fillsize(0.065)
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#grid').treegrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').treegrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth) * percent;
}

function initPage(pageSize){
	var p = $('#grid').treegrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').treegrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

/**
 * 重载项目列表
 */
function reloadGrid() {
	$("#grid").datagrid("reload");
}