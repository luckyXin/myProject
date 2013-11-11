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
			$("#reset").click(function(){
				$('#from').form('clear');
			});
			$("#search").click(function() {
				$("#grid").datagrid('options').queryParams = {
					auditStartTime : $("#auditStartTime").datebox("getValue"),
					projectOwner : $("#ownerId").val(),
					processPeople : $("#employeeId").val(),
					projectName : $("#projectName").val(),
					projectState : $("#projectState").val(),
					isStop : $("#isStop").val()
				};
				initialization();
			});
			
			$("#excelallnocompro").click(function(){
				var auditStartTime=$("#auditStartTime").datebox("getValue");
				var projectOwner=$("#ownerId").val();
				var projectName=$("#projectName").val();
				var isStop=$("#isStop").val();
			    var url=$("#coutextPath").val()+ '/common/project/importprojectexcel.do?auditStartTime='+auditStartTime+'&projectOwner='+projectOwner+'&projectName='+projectName+'&isStop='+isStop;
				window.location=encodeURI(encodeURI(url));
				return false;
			});

			$("#audit").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "未选择项目"
							});
						}
						top.openModule($("#frameId").val(), '项目明细展示',
								'/search/noCompleteProject/input.do', null,
								'add', row.projectId);
					});
			
			$("#openStopProject").click(function(){
				var row = $('#grid').datagrid('getSelected');
				if (row == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : "未选择项目"
					});
				}
				if (row.isStop != 2) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : "该项目未暂停，无法开启"
					});
					return;
				}
				$.post($("#coutextPath").val() + "/search/noCompleteProject/update.do", {
					projectId : row.projectId
				}, function(data, textStatus) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : data.msg
					});
					initialization();
				});
			});
			initialization();
			$("#employeeName").click(function() {
				openshowdiv(3, "人员查询", 600, 400, 220, null);
			});
			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});
		});

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


function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/search/noCompleteProject/find.do',
				frozenColumns : [ [ {
					field : 'datapreno',
					title : '工程编码',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'projectName',
					title : '项目名称',
					width : fillsize(0.2),
					sortable : true
				}] ],
				columns : [ [ 
				{
					field : 'ownerName',
					title : '项目业主',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'constructName',
					title : '施工企业',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'intermediaryName',
					title : '中介机构',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'mainAuditName',
					title : '主审',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'employeeAuditName',
					title : '复审',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'sentAmount',
					title : '送审金额',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'auditMoney',
					title : '审定金额',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'intermediaryAuditCutMoney',
					title : '中介审减金额',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'intermediaryAuditLv',
					title : '中介审减率',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'employeeAuditCutMoney',
					title : '雇员审减金额',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'employeeAuditLv',
					title : '雇员审减率',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'processState',
					title : '当前处理状态',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'processPeople',
					title : '当前处理人员',
					width : fillsize(0.15),
					sortable : true
				}, {
					field : 'isStop',
					title : '项目状态',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,
							rowIndex) {
						if (value == 2) {
							return "已暂停";
						}else{
							return "未暂停";
						}
					}
				}] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				height : height - 195,
				pageNumber : 1
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

function reloadGrid() {
	$("#grid").datagrid("reload");
}