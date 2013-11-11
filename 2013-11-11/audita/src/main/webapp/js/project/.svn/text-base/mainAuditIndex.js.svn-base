$(document).ready(
				function() {
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
					$("#reset").click(function() {
						$('#from').form('clear');
					});
					initialization($("#processType").val());
					if ($("#auditType").val() == '1') {
						$("#text").text("查看");
					} else {
						$("#text").text("录入");
					}

					$("#ownerName").click(function() {
						openshowdiv(1, "业主查询", 580, 350,270,null);
					});
					$(".mainAuditType").click(function() {
						initialization($(this).val());
						$("#processType").val($(this).val());
					});
					$("#search").click(function() {
				$("#grid").datagrid('options').queryParams = {
					ownerName : $("#ownerName").val(),
					projectName : $("#projectName").val(),
					legalState : $("#legalState").val(),
					beginTime : $('#beginTime').datebox('getValue'),
					endTime : $('#endTime').datebox('getValue'),
					timeLimitStart : $("#timeLimitStart").datebox('getValue'),
					timeLimitEnd : $("#timeLimitEnd").datebox('getValue'),
					isUrgent : $("#isUrgent").val()
				};
				initialization($("#processType").val());
				if ($("#auditType").val() == '1') {
					$("#text").text("查看");
				} else {
					$("#text").text("录入");
				}
			});

					$("#audit").click(
									function() {
										var row = $('#grid').datagrid(
												'getSelected');
										if (row == null) {
											top.$.messager.show({
												title : $("#title").val(),
												msg : "请选择项目"
											});
										}
										if ($("#processType").val() == 0) {
											top.openModule(
															$("#frameId").val(),
															'审计通知书-录入',
															'/project/MainAudit/input.do',
															null,
															'auditAdviceNote',
															row.id);
										} else if ($("#processType").val() == 1) {
											top.openModule(
															$("#frameId").val(),
															'项目意见稿-录入',
															'/project/MainAudit/input.do',
															null,
															'auditIdeaNote',
															row.id);
										} else {
											top.openModule(
															$("#frameId").val(),
															'审计报告 -录入',
															'/project/MainAudit/input.do',
															null,
															'auditReport',
															row.id);
										}
									});
				});

function initialization(checkNum) {
	if (checkNum != 2) {
		$("#arrangeType").attr("disabled", true);
		
		initializationOtherInfo(checkNum);
	} else {
		$("#arrangeType").attr("disabled", false);

		initializationMainAudit(checkNum);
	}
}

function initializationMainAudit(checkNum) {
	var width = $("#p2").width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/project/MainAudit/find.do?handleType='
						+ checkNum
						+ '&auditType=' + $("#auditType").val()
						+ '&arrangeType=' + $("#arrangeType").val()
						+ '&moduleId=' + $("#frameId").val(),
				columns : [ [ {
					field : 'projectName',
					title : '项目名称',
					width : fillsize(0.2),
					sortable : true
				}, {
					field : 'ownerName',
					title : '项目业主',
					width : fillsize(0.15),
					sortable : true
				}, {
					field : 'intermediary',
					title : '中介机构',
					width : fillsize(0.15),
					sortable : true
				}, {
					field : 'governmentEmployee',
					title : '复核工程师',
					width : fillsize(0.15),
					sortable : false
				}, {
					field : 'mainAuditName',
					title : '主审',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'isUrgent',
					title : '是否加急',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData, rowIndex) {
						if (value == 0) {
							return "否";
						} else if (value == 1) {
							return "是";
						} else {
							return "";
						}
					}
				}, {
					field : 'timeLimit',
					title : '限时',
					width : fillsize(0.1),
					sortable : true
				} ] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				height : 290,
				width : width
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
				height : (300 * newHeightNum)
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
				height : (300 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initializationOtherInfo(checkNum) {
	var width = $("#p2").width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/project/MainAudit/find.do?handleType='
						+ checkNum
						+ '&auditType=' + $("#auditType").val()
						+ '&arrangeType=' + $("#arrangeType").val()
						+ '&moduleId=' + $("#frameId").val(),
				columns : [ [ {
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
					field : 'constructUnit',
					title : '施工单位',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'datapreTime',
					title : '送审时间',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'auditStateTwo',
					title : '录入状态',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData, rowIndex) {
						if (value == 0) {

							return "未录入";
						} else if (value == 1) {

							return "已录入";
						}
					}
				}, {
					field : 'isUrgent',
					title : '是否加急',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData, rowIndex) {
						if (value == 0) {
							return "否";
						} else if (value == 1) {
							return "是";
						}else {
							return "";
						}
					}
				}, {
					field : 'timeLimit',
					title : '限时',
					width : fillsize(0.1),
					sortable : true
				} ] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				height : 290,
				width : width
			});
	// 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function fillsize(percent) {
	var bodyWidth = $("#p2").width();
	return (bodyWidth) * percent;
}

function reloadGrid() {
	$("#grid").datagrid("reload");
}
function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
	close();
}