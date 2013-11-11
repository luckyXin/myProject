$(document).ready(
		function() {
			var isfind = $("#find").val();
			var isinput = $("#input").val();
			if (isinput != '1') {
				$.messager.show({
					title : $("#title").val(),
					msg : $("#noPower").val()
				});
			}
			if (null != isfind && "null" != isfind && "" != isfind
					&& undefined != isfind) {
				// 初始化列表
				initialization();
				// 注册检索按钮事件
				$("#search").click(function() {
					$('#grid').datagrid('options').queryParams = {
						projectOwnerName : $("#searchname").val()
					};
					$('#grid').datagrid('reload');
				});
			} else {
				$('#p1').hide();
				$('#p2').hide();
				$("#center").hide();
			}
			$("#destroy").click(function() {
				if (confirm($("#isDelete").val())) {
					destroy();
				}
			});
		});

function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid({
		url : $("#path").val() + '/staff/projectOwners/find.do',
		columns : [ [ {
			field : 'id',
			title : '业主编号',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'ownerName',
			title : '业主名称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'ownerReferred',
			title : '业主简称',
			width : fillsize(0.16),
			sortable : true
		}, {
			field : 'unitType',
			title : '单位类型',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'isMainUnit',
			title : '是否政府投资项目建设主要单位',
			width : fillsize(0.20),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "是";
				} else if (value == 0) {
					return "否";
				}
			}
		}, {
			field : 'state',
			title : '状态',
			width : fillsize(0.1),
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
		height : height - 170
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

/**
 * 增加
 */
function add() {
	var frameid = $("#frameId").val();
	top.openModule(frameid, '项目业主信息编辑', '/staff/projectOwners/input.do', null,
			'add', null);
}

/**
 * 更新
 */
function update() {
	var row = $('#grid').datagrid('getSelected');
	if (row != null) {
		top.openModule($("#frameId").val(), '项目业主编辑',
				'/staff/projectOwners/input.do', null, 'update', row.id);
	} else {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

/**
 * 删除
 */
function del() {
	var row = $('#grid').datagrid('getSelected');
	if (row != null) {
		if (row.state == '2') {
			$.messager.show({
				title : $("#title").val(),
				msg : $("#alreadyDelete").val()
			});
			return;
		}
		$.post($("#path").val() + "/staff/projectOwners/delete.do", {
			ownerId : row.id
		}, function(data, textStatus) {
			$.messager.show({
				title : $("#title").val(),
				msg : data.msg
			});
			reloadGrid();
		});
	} else {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

function destroy() {
	var row = $('#grid').datagrid('getSelected');
	if (row != null) {
		$.post($("#path").val() + "/staff/projectOwners/destroy.do", {
			ownerId : row.id
		}, function(data, textStatus) {
			$.messager.show({
				title : $("#title").val(),
				msg : data.msg
			});
			reloadGrid();
		});
	} else {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}
