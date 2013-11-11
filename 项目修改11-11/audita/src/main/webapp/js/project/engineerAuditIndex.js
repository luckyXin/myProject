$(document).ready(
	
		function() {
			$("#reset").click(function() {
				$('#from').form('clear');
			});
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
				initialization();
				var height = document.body.clientHeight;
				var width = $(document.body).width() - 20;
				$('#p1').panel({
					title : '查询条件',
					iconCls : 'icon-search'
				});
				$('#p2').panel({
					title : '复核审计项目',
					iconCls : 'icon-tip'
				});
			}

			// 点击项目业主
			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350, 270, null);
			});
			// 点击检索
			$("#search").click(function() {
				$("#grid").datagrid('options').queryParams = {
					projectName : $("#projectName").val(),
					proownerid : $("#ownerName").val(),
					beginTime:$('#beginTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					timeLimitStart:$('#timeLimitStart').datebox('getValue'),
					timeLimitEnd:$('#timeLimitEnd').datebox('getValue'),
					isUrgent:$("#isUrgent").val(),
					greaterThan:$("#greaterThan").val(),
					lessThan:$("#lessThan").val()
					
				};
				initialization();
			});

			$("#add").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "请选择需要审批的内容"
							});
						}
						top.openModule($("#frameId").val(), '复核审计信息-录入',
								'/project/engineerAudit/input.do', null, 'add',
								row.id);
					});
		});
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

function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/project/engineerAudit/find.do?arrangeType='
						+ $("#arrangeType").val() + '&auditType='
						+ $("#auditType").val() + '&moduleId='
						+ $("#frameId").val(),
				treeField : 'projectName',
				columns : [ [ 
					{
						field : 'datapreno',
						title : '送审编号',
						width : fillsize(0.1),
						sortable : true
					}, 
				    {
					field : 'projectName',
					title : '项目名称',
					width : fillsize(0.2),
					sortable : true
				}, {
					field : 'ownerName',
					title : '项目业主',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'intermediary',
					title : '中介机构',
					width : fillsize(0.15),
					sortable : true
				}, {
					field : 'governmentEmployee',
					title : '复核工程师',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'mainAuditName',
					title : '主审',
					width : fillsize(0.05),
					sortable : true
				}, 
				{
					field : 'isUrgent',
					title : '是否加急',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,
							rowIndex) {
						if (value == 1) {
							return "是";
						}else{
							return "否";
						}
					}
				},
				{
					field : 'timeLimit',
					title : '限时',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,
							rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0, 10); 
						}else{
							return "";
						}
					}
				}, {
					field : 'sentAmount',
					title : '送审金额',
					width : fillsize(0.1),
					sortable : false
				}
				, {
					field : 'auditMoney',
					title : '中介审定金额',
					width : fillsize(0.1),
					sortable : false
				}
				, {
					field : 'intermediaryAuditCutMoney',
					title : '中介审减金额',
					width : fillsize(0.1),
					sortable : false
				}
				, {
					field : 'intermediaryAuditLv',
					title : '中介审减率',
					width : fillsize(0.1),
					sortable : false
				}
				, {
					field : 'employeeAuditMoney',
					title : '复核审定金额',
					width : fillsize(0.1),
					sortable : false
				}
				, {
					field : 'employeeAuditCutMoney',
					title : '复核审减金额',
					width : fillsize(0.1),
					sortable : false
				}
				, {
					field : 'employeeAuditLv',
					title : '复核审减率',
					width : fillsize(0.1),
					sortable : false
				},
				{
					field : 'totalAuditcutMoney',
					title : '总审减金额',
					width : fillsize(0.15),
					sortable :false
				}
				, {
					field : 'totalAuditLv',
					title : '总审减率',
					width : fillsize(0.15),
					sortable :false
				}
				
				
				] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				height : 350
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

function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#proownerid").val(str.id);
	close();
}