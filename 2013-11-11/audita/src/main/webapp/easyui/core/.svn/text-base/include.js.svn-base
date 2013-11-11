$(function() {
	$("#owner_info_id").combobox({
		valueField : 'code',
		textField : 'owner_name',
		url : "/audit-admin/owner_info_id/list",
		editable : false,
		disabled : isTrue,
		onShowPanel : function() {
			$("#owner_info_id").combobox("hidePanel");
			aaa.window("open");
		}
	});

	/**
	 * 打开弹出框
	 */
	var aaa = $("<div id=\"win\" class=\"background\" iconCls=\"icon-search\" style=\"margin: 0px auto;padding: 0px;width: 95%;\"    title=\"政府雇员查询\"><div style=\"height: 30px;text-align: center;line-height: 30px;padding: 5px;\" class=\"background\">业主名称：<input id=\"query_value\" type=\"text\" class=\"text\" /> <a id=\"query_a\" style=\"border: 1px solid #ccc;\" href=\"#\">查询</a> </div><table class=\"background easyui-panel\" style=\"width: 95%;height: 300px;margin: 0px;padding: 0px;\" id=\"adasdasdsadsdsdsads\"></table></div>");
	aaa.window({
		modal : true,
		width : 564,
		height : 375,
		top:100,
		closed : true,
		minimizable : false,
		collapsible : false,
		draggable : false,
		onResize : function(w, h) {
			aaa.find("#adasdasdsadsdsdsads").datagrid({
				width : (w - 14),
				height : (h - 75)
			});
		}
	});
	/**
	 * 点击搜索方法设置查询条件
	 */
	aaa.find("#query_a").click(function() {
		var $query = {
			"_parent" : aaa.find("#query_value").val()
		};
		aaa.find("#adasdasdsadsdsdsads").datagrid({
			queryParams : $query
		});
	});
	/**
	 * 按钮样式
	 */
	aaa.find("#query_a").linkbutton({
		plain : true,
		iconCls : "icon-search"
	});
	var aaaaaaaaaa = function(value, rowData, rowIndex) {
		if (value == "0")
			return "否";
		else if (value == "1")
			return "是";
		else
			return "--";
	};
	/**
	 * 设置表格数据
	 */
	aaa.find("#adasdasdsadsdsdsads").datagrid(
			{
				url : "/audit-admin/owner_info_id/query",
				idField : "code",
				columns : [ [ {
					field : "owner_name",
					title : "业主名称",
					width : 120
				}, {
					field : "owner_abbreviation",
					title : "业主简称",
					width : 100
				}, {
					field : "noUnit_type",
					title : "单位类型",
					width : 100
				}, {
					field : "is_buldunit",
					title : "是否建设投资",
					width : 100,
					formatter : aaaaaaaaaa
				} ] ],
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				onDblClickRow : function(rowIndex, rowData) {
					$("#owner_info_id").combobox("select", rowData.code);
					
					aaa.window("close");
				},
				onLoadSuccess : function() {
					aaa.find("#adasdasdsadsdsdsads").datagrid("selectRecord",
							$("#owner_info_id").combobox("getValue"));
				}
			});
});