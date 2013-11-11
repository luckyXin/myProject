$(function() {
	$("#lookDetail").click(function(){
		$("#owner_info_id").combobox("hidePanel");
		aaa.window("open");
	});

	/**
	 * 打开弹出框
	 */
	var aaa = $("<div id=\"win\" class=\"background\" iconCls=\"icon-search\" style=\"margin: 0px auto;padding: 0px;width: 95%;\"    title=\"查看明细\"><div id=\"detail\"></div></div>");
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
			aaa.find("#detail").datagrid({
				width : (w - 14),
				height : (h - 75)
			});
		}
	});
	aaa.find("#detail").append("<div id=\"tt\" class=\"easyui-tabs\" style=\"width:500px;height:250px;\"><div title=\"Tab1\" style=\"padding:20px;display:none;\">tab1</div><div title=\"Tab2\" closable=\"true\" style=\"overflow:auto;padding:20px;display:none;\">tab2</div><div title=\"Tab3\" iconCls=\"icon-reload\" closable=\"true\" style=\"padding:20px;display:none;\">tab3</div></div><div>fdfsafa</div>");
});