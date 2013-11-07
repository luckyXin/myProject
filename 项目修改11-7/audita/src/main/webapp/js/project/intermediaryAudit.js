$(document).ready(
		function() {
			$("#reset").click(function() {
				$('#from').form('clear');
			});
			var isfind = $("#find").val();
			var isinput = $("#input").val();
			if($(".isconfirm").val()==1){
				$("#add").hide();
				$("#look").show();
			}else{
				$("#look").hide();
				$("#add").show();
			}
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
					title : '查询条件',
					iconCls : 'icon-search'
				});
				$('#p2').panel({
					title : '初审审核',
					iconCls : 'icon-tip'
				});
			}
			
			//点击项目业主
			$("#ownerName").click(function(){
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});
			//点击中介机构
			$("#intermeidiaryName").click(function(){
				openshowdiv(4, "中介机构", 600, 400, 220,null);
			});
			
			//点击检索
			$("#search").click(function(){
				if($(".isconfirm").val()==1){
					$("#add").hide();
					$("#look").show();
				}else{
					$("#look").hide();
					$("#add").show();
				}
				$("#grid").datagrid('options').queryParams = {
					projectName:$("#projectName").val(),
					proownerid : $("#proownerid").val(),
					intermeidiaryId:$("#intermeidiaryId").val(),
					isconfirmss : $(".isconfirm").val(),
					arrangerpro:$("#arrangerpro").val(),
					beginTime:$('#beginTime').datebox('getValue'),
					endTime:$('#endTime').datebox('getValue'),
					timeLimitStart:$('#timeLimitStart').datebox('getValue'),
					timeLimitEnd:$('#timeLimitEnd').datebox('getValue'),
					isUrgent:$("#isUrgent").val(),
					module:$("#frameId").val()
				};
				var path = $("#path").val();
				var url=path + '/project/intermediaryAudit/find.do?module='+$("#frameId").val();
			 	$('#grid').datagrid('options').url=url;
				$("#grid").datagrid("reload");
			});	
});

function initialization() {
	var height = document.body.clientHeight;
	var path = $("#path").val();
	var isconfirm=$("#inprint").val();
	if(null==isconfirm || ""==isconfirm){
		isconfirm="0";
	}
	$('#grid').datagrid({
			url : path + '/project/intermediaryAudit/find.do?module='+$("#frameId").val()+'&isconfirmss='+isconfirm,
			columns : [ [
					{
						field : 'projectName',
						title : '项目名称',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'proownerid',
						title : '项目业主',
						width : fillsize(0.1),
						sortable : true
					},
					{
						field : 'intermediaryId',
						title : '中介机构',
						width : fillsize(0.1),
						sortable : true
					},
					{
						field : 'sentAmount',
						title : '送审金额(元)',
						width : fillsize(0.1),
						sortable : true
					},
					{
						field : 'intermediarySendTime',
						title : '发放中介时间',
						width : fillsize(0.1),
						sortable : true
					},
					{
						field : 'intermediaryAuditTime',
						title : '初审审核应完成时间',
						width : fillsize(0.1),
						sortable : true
					},
					{
						field : 'isUrgent',
						title : '是否加急',
						width : fillsize(0.1),
						sortable : true,
						formatter : function(value, rowData,
								rowIndex) {
							if (value == 1) {
								return "是";
							}else{
								return "否";
							}
						}
					},
					{
						field : 'timeLimit',
						title : '限时',
						width : fillsize(0.1),
						sortable : true,
						formatter : function(value, rowData,
								rowIndex) {
							if(null!=value && ""!=value){
								return value.substr(0, 10); 
							}else{
								return "";
							}
						}
					},
					{
						field : 'isconfirm',
						title : '是否审核',
						width : fillsize(0.1),
						sortable : true,
						formatter : function(value, rowData,
								rowIndex) {
							if (value == 1) {
								return "已审核";
							}else{
								return "未审核";
							}
						}
					}
					 ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			height : 310
	});
	// 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
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
	return (bodyWidth - 10) * percent;
}

function reloadGrid() {
	$("#grid").datagrid("reload");
}
// 处理时间
function todate(time) {
	if (null != time && "" != time) {
		var date = new Date(time);
		var year = date.getFullYear();
		var month = (date.getMonth() + 1);
		var day = date.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		return year + "-" + month + "-" + day;
	} else {
		return "";
	}
}

//回调方法
function returnback(rowData){
	$("#ownerName").val(rowData.ownerName);
	$("#proownerid").val(rowData.id);
	close();
}

function returnIntermediary(str) {
	$("#intermeidiaryId").val(str.id);
	$("#intermeidiaryName").val(str.intermediaryname);
	close();
}
//录入
function add(){
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){
    		$.messager.show({
    		title:$("#title").val(),
			msg: '请选择一行项目',
			timeout:3000});
            return;  
    }	
	top.openModule($("#frameId").val(),'初审审核-录入','/project/intermediaryAudit/input.do',null,'add',update[0].arrangeId);
}

