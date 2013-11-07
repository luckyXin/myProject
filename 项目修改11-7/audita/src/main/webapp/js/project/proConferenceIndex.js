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
					title : '项目信息',
					iconCls : 'icon-edit'
				});
				$('#p2').panel({
					width : width,
					height : height - 130,
					title : '投资审计会议纪要列表',
					iconCls : 'icon-tip'
				});
			}
			
});
function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	var datapreId=$("#datapreId").val();
	var path = $("#path").val();
	$('#gridtable').datagrid({
			url : path + '/project/proConference/findlist.do?datapreId='+datapreId,
			columns : [ [
					{
						field : 'conferentitle',
						title : '会议议题',
						width : fillsize(0.2),
						sortable : true
					},
					{
						field : 'conferenceaddress',
						title : '会议地点',
						width : fillsize(0.3),
						sortable : true
					},
					{
						field : 'conferencetime',
						title : '会议日期',
						width : fillsize(0.2),
						sortable : true,
						formatter : function(value, rowData,rowIndex) {
						    if(null!=value && ""!=value){
						    	return todate(value);
						    }else{
						    	return "";
						    }
						}
						
						
					},
					{
						field : 'conferencontent',
						title : '会议达成意见',
						width : fillsize(0.3),
						sortable : true
					}
					 ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			height : height - 200
	});
	// 设置分页控件
	var p = $('#gridtable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#gridtable').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initPage(pageSize){
	var p = $('#gridtable').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#gridtable').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 60) * percent;
}

function reloadGrid() {
	$("#gridtable").datagrid("reload");
}
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

//点击增加
function add(){
	top.openModule($("#frameId").val(),'会议纪要-录入-新增','/project/proConference/input.do',null,'add',$("#datapreId").val());
}
//点击修改
function update(){
	var update = $('#gridtable').datagrid('getSelections');  
    if(update.length <= 0 ){
    		$.messager.show({
    		title:$("#title").val(),
			msg: '请选择一行数据编辑',
			timeout:3000});
            return;  
    }	
	top.openModule($("#frameId").val(),'会议纪要-录入-编辑','/project/proConference/input.do',null,'update',update[0].id);
}
/**
 * 删除
 * @return
 */
function del() {
	var del = $('#gridtable').datagrid('getSelections');  
    if(del.length <= 0 ){  
    	$.messager.show({
    		title:$("#title").val(),
			msg: $("#noSelectdel").val(),
			timeout:3000
     });
        return;  
    }
    if(confirm($("#isDelete").val())){
    	deletebyid(del[0].id);
	}
   
}
//删除
function deletebyid(id){
	 $.ajax( {
			url : $("#path").val() + '/project/proConference/delete.do',
			type : "POST",
			dataType : "json",
			data : "id="+id,
			success : function(data) {
					if ("success"==data.success) {
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
						//刷新
						reloadGrid();  
					}else{
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
					}
			}
	   });	
}

