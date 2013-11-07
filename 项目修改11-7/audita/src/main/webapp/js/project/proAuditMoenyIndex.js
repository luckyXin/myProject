$(document).ready(
		function() {
			var isfind = $("#find").val();
			var isinput = $("#input").val();
			if (isinput != '1') {
				$.messager.show({
					title : $("#title").val(),
					msg : $("#noPower").val()
				});
				return false;
			}
			if (null != isfind && "null" != isfind && "" != isfind && undefined != isfind) {
				// 初始化列表
				initialization();
				$('#p1').show();
				$('#p2').show();
				var height = document.body.clientHeight;
				var width = $(document.body).width() - 20;
				$('#p1').panel({
					width : width,
					height : 95,
					title : '查询条件',
					iconCls : 'icon-search'
				});
				$('#p2').panel({
					width : width,
					height : height - 110,
					title : '审计费用列表',
					iconCls : 'icon-tip'
				});
			}
			//点击检索
			$("#search").click(function(){
				$("#grid").datagrid('options').queryParams = {
					projectName:$("#projectName").val()
				};
				$("#grid").datagrid("reload");
			});
			
});
function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	var path = $("#path").val();
	$('#grid').datagrid({
			url : path + '/project/proAuditMoeny/find.do',
			columns : [ [
					{
						field : 'auditProjectName',
						title : '审计项目名称',
						width : fillsize(0.2),
						sortable : true
					},
					{
						field : 'baseauditmoney',
						title : '基本审计费用(元)',
						width : fillsize(0.25),
						sortable : true,
						formatter : function(value, rowData,rowIndex) {
							if(null==value || ""==value || undefined==value){
								return 0;
							}else{
								return value;
							}
						}
					},
					{
						field : 'xiaoyiauditmoney',
						title : '效益审计费用(元)',
						width : fillsize(0.25),
						sortable : true,
						formatter : function(value, rowData,rowIndex) {
							if(null==value || ""==value || undefined==value){
								return 0;
							}else{
								return value;
							}
						}
					},
					{
						field : 'total',
						title : '合计(元)',
						width : fillsize(0.2),
						sortable : false,
						formatter : function(value, rowData,rowIndex) {
						    var total=rowData.baseauditmoney;;
						    var b=0;
							if(null!=rowData.xiaoyiauditmoney && ""!=rowData.xiaoyiauditmoney && undefined!=rowData.xiaoyiauditmoney){
								 b=parseFloat(rowData.xiaoyiauditmoney);
							}
							total=parseFloat(total)+b;
							return total.toFixed(2);
						}
					}
					 ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			height : height - 150
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
	return (bodyWidth - 20) * percent;
}

function reloadGrid() {
	$("#grid").datagrid("reload");
}


