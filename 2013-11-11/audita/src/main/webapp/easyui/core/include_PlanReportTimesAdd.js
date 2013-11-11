/**
 * 添加报批项目
 * 使用是将该JS引入
 * 页面上必须包含变量 var isTrue = false/true;(是否可以编辑)
 */


$(function() {
	
	$("#add").click(function(){
		var times = $("#times").val();
		if(times != null && times != ""){
			aaa.window("open");
			$("#adasdasdsadsdsdsads").datagrid("reload");
		}else{
			top.showMsg("请先保存报批信息!");
		}
	});
	
	$("#del").click(function(){
		//得到当前行
		var row = $("#grid").datagrid('getSelected');
		if (row) {
			var code = $("#grid").datagrid('getSelected').code;
			var data = {"_id":code};
			$.post("/audit-admin/projectManage/apm/gab/apmAManager/delTimes",data,function(data){
				top.showMsg(data);
				$("#grid").datagrid("reload");
			});
		}else {
			top.showMsg("请选择要删除的行!");
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
				url : "/audit-admin/ShowInfoAction/PlanprojInfo/query",
				idField : "code",
				columns : [ [ {
					field : "name",
					title : "项目名称",
					width : 240
				}, {
					field : "owner_name",
					title : "项目业主",
					width : 220
				}] ],
				pagination : true,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				onDblClickRow : function(rowIndex, rowData) {
					var data = {"times":$("#times").val(),"_id":rowData.code};
					$.post("/audit-admin/projectManage/apm/gab/apmAManager/addTimes",data,function(data){
						top.showMsg(data);
						$("#grid").datagrid("reload");
	    			});
					aaa.window("close");
				}
			});
});