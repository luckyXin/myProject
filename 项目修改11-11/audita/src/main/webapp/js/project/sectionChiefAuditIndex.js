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
			
			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});

			$("#audit").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "请选择需要审批的内容"
							});
						}
						top.openModule($("#frameId").val(), '科长审批-录入',
								'/project/SectionChiefAudit/input.do', null,
								'add', row.id);
					});
			$("#search").click(function() {
				$("#grid").datagrid('options').queryParams = {
					ownerName : $("#ownerName").val(),
					projectName : $("#projectName").val()
				};
				initialization();
			});
			$(".sectionChiefAuditType").change(function() {
				initialization();
			});
			initialization();
		});

function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/project/SectionChiefAudit/find.do?arrangeType='
						+ $("#arrangeType").val()
						+ '&auditType=' + $("#auditType").val()
						+ '&moduleId=' + $("#frameId").val(),
				treeField : 'projectName',
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
					field : 'intermediary',
					title : '中介机构',
					width : fillsize(0.2),
					sortable : true
				}, {
					field : 'governmentEmployee',
					title : '复核工程师',
					width : fillsize(0.2),
					sortable : true
				}, {
					field : 'mainAuditName',
					title : '主审',
					width : fillsize(0.17),
					sortable : true
				} ] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				height : height - 195
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

function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
	close();
}