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
		$("#grid").datagrid('options').queryParams = {
			auditEndTime : $("#auditEndTime").datebox("getValue"),
			auditStartTime : $("#auditStartTime").datebox("getValue")
		};
		initialization();
	});
	
	$("#excelallfinishpro").click(function(){
		var auditEndTime =$("#auditEndTime").datebox("getValue");
		var auditStartTime = $("#auditStartTime").datebox("getValue");
	    var url=$("#coutextPath").val()+ '/common/project/importfinishprojectexcel.do?datapreStartTime='+auditStartTime+'&datapreEndTime='+auditEndTime+'&method=1';
		window.location=encodeURI(encodeURI(url));
		return false;
	});
	
	
	
	$("#audit").click(
			function() {
				var row = $('#grid').datagrid('getSelected');
				if (row == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : "请选择需要查看的内容"
					});
				}
				top.openModule($("#frameId").val(), '项目明细展示',
						'/search/quarterCompleteProject/input.do', null,
						'add', row.projectId);
			});
	initialization();
});
function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/search/quarterCompleteProject/find.do',
				frozenColumns : [ [ {
					field : 'datapreno',
					title : '送审编号',
					width : fillsize(0.08),
					sortable : true
				}, {
					field : 'projectName',
					title : '项目名称',
					width : fillsize(0.2),
					sortable : true
				}] ],
				columns : [ [ {
					field : 'ownerName',
					title : '项目业主',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'intermediary',
					title : '中介机构',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'constructName',
					title : '施工单位',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'mainAuditName',
					title : '主审',
					width : fillsize(0.1),
					sortable : true
				}, {
					field : 'employeeAudits',
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
				}
				, {
					field : 'employeeAuditLv',
					title : '雇员审减率',
					width : fillsize(0.08),
					sortable : true
				},
				{
					field : 'datapretime',
					title : '资料接收时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'intermediarySendTime',
					title : '中介机构发送时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'intermediaryAuditTime',
					title : '中介机构应完成时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'deferday',
					title : '超时天数',
					width : fillsize(0.08),
					sortable : true
				}
				, {
					field : 'auditStartTime',
					title : '开始复核时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'auditEndTime',
					title : '结束复核时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'auditDayCount',
					title : '复核天数',
					width : fillsize(0.08),
					sortable : true
				}
				, {
					field : 'auditRemark',
					title : '复核说明',
					width : fillsize(0.15),
					sortable : true
				}
				, {
					field : 'auditReportTime',
					title : '审计报告时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'dayCount',
					title : '报告所用天数',
					width : fillsize(0.08),
					sortable : true
				}
				, {
					field : 'auditReportCode',
					title : '审计报告文号',
					width : fillsize(0.1),
					sortable : true
				}
				, {
					field : 'projectStartTime',
					title : '开工时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				, {
					field : 'projectEndTime',
					title : '竣工时间',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				}
				] ],
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