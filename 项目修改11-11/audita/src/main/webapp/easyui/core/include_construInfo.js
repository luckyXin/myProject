/**
 * 项目基本信息查询
 * 使用是将该JS引入
 * 页面上必须包含id有constru_info_id(施工企业信息)、
 * constru_info_phone(项目联系人)、constru_info_phone(项目联系人电话)
 * 页面上必须包含变量 var isTrue = false/true;(是否可以编辑)
 */

$(function() {
	$("#constru_info_id").combobox({
		valueField : 'code',
		textField : 'enterprise_name',
		url : "/audit-admin/ShowInfoAction/construInfo/list",
		editable : false,
		disabled : isTrue,
		onShowPanel : function() {
			$("#constru_info_id").combobox("hidePanel");
			aaa.window("open");
		}
	});

	/**
	 * 打开弹出框
	 */
	var aaa = $("<div id=\"win\" class=\"background\" iconCls=\"icon-search\" style=\"margin: 0px auto;padding: 0px;width: 95%;\"    title=\"项目查询\"><div style=\"height: 30px;text-align: center;line-height: 30px;padding: 5px;\" class=\"background\">施工企业名称：<input id=\"query_valueone\" type=\"text\" class=\"text\" /> <a id=\"query_a\" style=\"border: 1px solid #ccc;\" href=\"#\">查询</a> </div><table class=\"background easyui-panel\" style=\"width: 95%;height: 300px;margin: 0px;padding: 0px;\" id=\"adasdasdsadsdsdsads\"></table></div>");
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
				url : "/audit-admin/ShowInfoAction/construInfo/query",
				idField : "code",
				columns : [ [ {
					field : "enterprise_name",
					title : "企业名称",
					width : 120
				}, {
					field : "enterprise_abbreviation",
					title : "企业简称",
					width : 100
				}, {
					field : "enterprise_type",
					title : "企业类型",
					width : 100
				}, {
					field : "enterprise_reg_fund",
					title : "企业注册资金",
					width : 100
				} ] ],
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				onDblClickRow : function(rowIndex, rowData) {
					$("#constru_info_id").combobox("select", rowData.code);
					var constru_info_name = [
					                       {"id":rowData.name_one,"text":rowData.name_one},
					                       {"id":rowData.name_two,"text":rowData.name_two},
					                       {"id":rowData.name_three,"text":rowData.name_three}
					                       ];
					$("#constru_info_name").combobox({
						valueField : 'id',
						textField : 'text',
						data:constru_info_name,
						editable:false
					});
					var constru_info_phone = [
											{"id":rowData.phone_one,"text":rowData.phone_one},
											{"id":rowData.phone_two,"text":rowData.phone_two},
											{"id":rowData.phone_three,"text":rowData.phone_three}
											];
					$("#constru_info_phone").combobox({
						valueField : 'id',
						textField : 'text',
						data:constru_info_phone,
						editable:false
					});
					aaa.window("close");
				},
				onLoadSuccess : function() {
					aaa.find("#adasdasdsadsdsdsads").datagrid("selectRecord",
							$("#constru_info_id").combobox("getValue"));
				}
			});
});