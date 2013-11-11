var newSubMenu = null;
$(document).ready(
		function() {
			$('#form').form('validate');
			$("td").addClass("tdClass");
			var path = $("#coutextPath").val();
			var requestUrl = path + $("#requestUrl").val();
			var processType = $("#processType").val();
			$("#remark").val(top.textTrim($("#remark").val()));
			$(".easyui-combobox").attr("editable", "false");
			var moduleNameValue;
			var state;
			var subMenu;
			var url;
			var remark;
			if (processType == "add") {
				getSubMenu();
			}
			if (processType == "update") {
				moduleNameValue = $("#moduleName").val();
				state = $("#state").combobox("getValue");
				remark = $("#remark").val();
				subMenu = $("#subMenu").combobox("getValue");
				url = $("#url").val();
			}
			$("#save").click(
					function() {
						var isUpdate = true;
						if (processType == "update") {
							var newModuleName = $("#moduleName").val();
							var newState = $("#state").combobox("getValue");
							var newRemark = $("#remark").val();
							var newUrl = $("#url").val();
							if (moduleNameValue == newModuleName
									&& newState == state && remark == newRemark
									&& newSubMenu == subMenu && newUrl == url) {
								isUpdate = false;
							}
						}
						if (isUpdate) {
							if ($('#form').form('validate')) {
								$("#form").ajaxSubmit(
										{
											url : requestUrl,
											success : function(data) {
												if (data.menuId) {
													top.showMsg(data.msg);
													top.reloadModule($("#id")
															.val());
													top.closeModule(moduleName,
															parentModuleName);
												} else {
													msg = data.msg;
													top.showMsg(msg);
												}
											}
										});
							}
						} else {
							$.messager.show({
								title : $("#title").val(),
								msg : $("#noUpdate").val()
							});
						}
					});
			$("#topMenu").combobox({
				onSelect : function(n, o) {
					getSubMenu();
				}
			});

			$("#subMenu").combobox({
				onSelect : function(n, o) {
					newSubMenu = $("#subMenu").combobox("getValue");
				}
			});
			functionTable();
		});

function functionTable() {
	$('#functionTable').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/system/Function/find.do?moduleId='
						+ $("#moduleId").val(),
				columns : [ [ {
					field : 'id',
					title : '方法编码',
					width : fillsize(0.10),
					sortable : true
				}, {
					field : 'name',
					title : '方法名称',
					width : fillsize(0.2),
					sortable : true
				}, {
					field : 'method',
					title : '方法逻辑名',
					width : fillsize(0.25),
					sortable : true
				}, {
					field : 'state',
					title : '状态',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData, rowIndex) {
						if (value == 0) {
							return "启用";
						}
						if (value == 1) {
							return "禁用 ";
						}
					}
				}, {
					field : 'createTime',
					title : '创建时间',
					width : fillsize(0.15),
					sortable : true
				}, {
					field : 'impower',
					title : '操作(双击)',
					width : fillsize(0.15),
					sortable : true,
					formatter : function(value, rowData, rowIndex) {
						if (value == 1) {
							return "已授权";
						}
						if (value == 0) {
							return "未授权";
						}
					}
				} ] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				onDblClickRow : function(rowIndex, rowData) {
					$.post(
							$("#coutextPath").val()
									+ "/system/Module/update.do", {
								functionId : rowData.id,
								moduleId : $("#moduleId").val()
							}, function(data, textStatus) {
								$.messager.show({
									title : "提示",
									msg : data.msg
								});
								$("#functionTable").datagrid("reload");
							});
				}
			});
	// 设置分页控件
	var p = $('#functionTable').datagrid('getPager');
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
	return (bodyWidth - 60) * percent;
}

function getSubMenu() {
	var topMenuId = $("#topMenu").combobox("getValue");
	$('#subMenu').combobox("clear");
	$('#subMenu').combobox(
			'reload',
			$("#coutextPath").val() + '/system/Module/getSubMenu.do?topMenuId='
					+ topMenuId);
}
function cancel() {
	top.closeModule(moduleName, null);
}