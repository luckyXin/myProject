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
					height : 120,
					title : '查询条件',
					iconCls : 'icon-search'
				});
				$('#p2').panel({
					width : width,
					height : height - 80,
					title : '审计项目信息列表',
					iconCls : 'icon-tip'
				});
			}
			//点击检索
			$("#search").click(function(){
				$("#grid").datagrid('options').queryParams = {
					projectName:$("#projectName").val(),
					proownerid:$("#proownerid").val()
				};
				$("#grid").datagrid("reload");
			});
			//点击项目业主
			$("#ownerName").click(function(){
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});
			
});
function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	var path = $("#path").val();
	$('#grid').datagrid({
			url : path + '/project/proConference/find.do',
			columns : [ [
					{
						field : 'projectName',
						title : '审计项目名称',
						width : fillsize(0.2),
						sortable : true
					},
					{
						field : 'proownerid',
						title : '项目业主',
						width : fillsize(0.2),
						sortable : true
					},
					{
						field : 'mainAuditId',
						title : '主审人员',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'intermediaryId',
						title : '中介机构名称',
						width : fillsize(0.2),
						sortable : true
					},
					{
						field : 'governmentEmp',
						title : '复核工程师',
						width : fillsize(0.15),
						sortable : false
					}
					 ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			height : height - 180
	});
	// 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initPage(pageSize){
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 20) * percent;
}

function reloadGrid() {
	$("#grid").datagrid("reload");
}
//回调方法
function returnback(rowData){
	$("#ownerName").val(rowData.ownerName);
	$("#proownerid").val(rowData.id);
	close();
}

//点击录入
function add(){
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){
    		$.messager.show({
    		title:$("#title").val(),
			msg: '请选择一行项目',
			timeout:3000});
            return;  
    }	
	top.openModule($("#frameId").val()+"-001",'会议纪要-录入','/project/proConference/input.do',null,'lookconference',update[0].datapreId);
}

