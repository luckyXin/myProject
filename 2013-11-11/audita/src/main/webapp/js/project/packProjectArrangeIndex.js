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
			if (isfind) {
				// 初始化列表
				initialization();
				// 注册检索按钮事件
				$("#search").click(function() {
					$('#grid').datagrid('options').queryParams = {
						ownerName : $("#ownerName").val(),
						proejctName : $("#projectName").val()
					};
					reloadGrid('#grid');
				});
				$("#ownerName").click(function() {
					$('#searchOwner').window('open');
				});
			} else {
				$('#p1').hide();
				$('#p2').hide();
			}

			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});

			$("#add").click(
					function() {
						top.openModule($("#frameId").val(), '打包项目安排编辑',
								'/project/PackProjectArrange/input.do', null,
								'add');
					});

			$("#update").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null || row.id == null) {
							$.messager.show({
								title : $("#title").val(),
								msg : $("#noSelect").val()
							});
							return;
						}
						top.openModule($("#frameId").val(), '打包项目安排编辑',
								'/project/PackProjectArrange/input.do', null,
								'update', row.id);
					});

			$("#delete").click(function() {
				del("/project/PackProjectArrange/delete.do", 'grid');
			});

			$("#destroy").click(function() {
				destroy("/project/PackProjectArrange/destroy.do", 'grid');
			});
		});

function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid({
		url : $("#coutextPath").val() + '/project/PackProjectArrange/find.do',
		columns : [ [ {
			field : 'projectPackName',
			title : '项目包名称',
			width : fillsize(0.35),
			sortable : true
		}, {
			field : 'ownerName',
			title : '项目业主',
			width : fillsize(0.35),
			sortable : true
		}, {
			field : 'sentAmount',
			title : '送审金额（元）',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'projectTime',
			title : '安排时间',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'state',
			title : '状态',
			width : fillsize(0.18),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {

					return "启用";
				} else if (value == 1) {

					return "禁用";

				} else if (value == 2) {
					return "删除";
				}
			}
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

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 50) * percent;
}
function reloadGrid() {
	$("#grid").datagrid("reload");
}
function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
	close();
}