/**
 * 项目基本信息查询
 * 使用是将该JS引入
 * 页面上必须包含id有proj_info_id(项目基本信息)、owner_info_id(项目业主)、
 * owner_info_name(项目联系人)、owner_info_phone(项目联系人电话)
 * 页面上必须包含变量 var isTrue = false/true;(是否可以编辑)
 */


$(function() {
	$("#proj_info_id_all").combobox({
		valueField : 'code',
		textField : 'name',
		url : "/audit-admin/ShowInfoAction/projInfoAll/list",
		editable : false,
		disabled : isTrue,
		onShowPanel : function() {
			$("#proj_info_id_all").combobox("hidePanel");
			aaa.window("open");
		}
	});

	/**
	 * 打开弹出框
	 */
	var aaa = $("<div id=\"win\" class=\"background\" iconCls=\"icon-search\" style=\"margin: 0px auto;padding: 0px;width: 95%;\"    title=\"项目查询\"><div style=\"height: 30px;text-align: center;line-height: 30px;padding: 5px;\" class=\"background\">项目名称：<input id=\"query_valueone\" type=\"text\" class=\"text\" />项目业主：<input id=\"query_valuetwo\" type=\"text\" class=\"text\" /> <a id=\"query_a\" style=\"border: 1px solid #ccc;\" href=\"#\">查询</a> </div><table class=\"background easyui-panel\" style=\"width: 95%;height: 300px;margin: 0px;padding: 0px;\" id=\"adasdasdsadsdsdsads\"></table></div>");
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
				"query_valueone" : aaa.find("#query_valueone").val(),
				"query_valuetwo" : aaa.find("#query_valuetwo").val()
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
				url : "/audit-admin/ShowInfoAction/projInfoSingleAll/query",
				idField : "code",
				columns : [ [ {
					field : "name",
					title : "项目名称",
					width : 120
				}, {
					field : "owner_name",
					title : "项目业主",
					width : 100
				}, {
					field : "direction",
					title : "项目方向",
					width : 100
				}, {
					field : "level",
					title : "立项级次",
					width : 100
				} ] ],
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				onDblClickRow : function(rowIndex, rowData) {
					$("#proj_info_id_all").combobox("select", rowData.name);
					$("#owner_info_id").combobox("select", rowData.ower_code);
					var owner_info_name = [
					                       {"id":rowData.name_one,"text":rowData.name_one},
					                       {"id":rowData.name_two,"text":rowData.name_two},
					                       {"id":rowData.name_three,"text":rowData.name_three}
					                       ];
					$("#owner_info_name").combobox({
						valueField : 'id',
						textField : 'text',
						data:owner_info_name,
						editable:false
					});
					var owner_info_phone = [
											{"id":rowData.phone_one,"text":rowData.phone_one},
											{"id":rowData.phone_two,"text":rowData.phone_two},
											{"id":rowData.phone_three,"text":rowData.phone_three}
											];
					$("#owner_info_phone").combobox({
						valueField : 'id',
						textField : 'text',
						data:owner_info_phone,
						editable:false
					});
					$("#audit_cost").val(rowData.examinefee);
					$("#burn_content").val(rowData.burn_content);
					$("#inveslevel").val(rowData.inveslevel);
					$("#ipb_code").val(rowData.code);
					$("#audit_cost").blur();
					aaa.window("close");
				},
				onLoadSuccess : function() {
					aaa.find("#adasdasdsadsdsdsads").datagrid("selectRecord",
							$("#proj_info_id_all").combobox("getValue"));
				}
			});
});