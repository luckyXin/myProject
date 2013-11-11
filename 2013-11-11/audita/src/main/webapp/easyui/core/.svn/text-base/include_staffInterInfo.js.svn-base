/**
 * 中介机构基本信息
 * 使用是将该JS引入
 * 页面上必须包含id有staffInter_info_id(中介机构基本信息)
 * 页面上必须包含变量 var isTrue = false/true;(是否可以编辑)
 */


$(function() {
	$("#staffInter_info_id").combobox({
		valueField : 'code',
		textField : 'name',
		url : "/audit-admin/ShowInfoAction/staffInterInfo/list",
		editable : false,
		disabled : isTrue,
		onShowPanel : function() {
			$("#staffInter_info_id").combobox("hidePanel");
			aaa.window("open");
		}
	});

	/**
	 * 打开弹出框
	 */
	var aaa = $("<div id=\"win\" class=\"background\" iconCls=\"icon-search\" style=\"margin: 0px auto;padding: 0px;width: 95%;\"    title=\"项目查询\"><div style=\"height: 30px;text-align: center;line-height: 30px;padding: 5px;\" class=\"background\">中介机构名称：<input id=\"query_valueone\" type=\"text\" class=\"text\" /> <a id=\"query_a\" style=\"border: 1px solid #ccc;\" href=\"#\">查询</a> </div><table class=\"background easyui-panel\" style=\"width: 95%;height: 300px;margin: 0px;padding: 0px;\" id=\"adasdasdsadsdsdsads\"></table></div>");
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
				"query_valueone" : aaa.find("#query_valueone").val()
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
	/**
	 * 设置表格数据
	 */
	aaa.find("#adasdasdsadsdsdsads").datagrid(
			{
				url : "/audit-admin/ShowInfoAction/staffInterInfo/query",
				idField : "code",
				columns : [ [ {
					field : "name",
					title : "机构名称",
					width : 120
				}, {
					field : "shortName",
					title : "简称",
					width : 100
				}, {
					field : "businesstype",
					title : "业务类型",
					width : 100
				}, {
					field : "qualification",
					title : "资质",
					width : 100
				} ] ],
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				onDblClickRow : function(rowIndex, rowData) {
					$("#staffInter_info_id").combobox("select", rowData.code);
					aaa.window("close");
				},
				onLoadSuccess : function() {
					aaa.find("#adasdasdsadsdsdsads").datagrid("selectRecord",
							$("#staffInter_info_id").combobox("getValue"));
				}
			});
});