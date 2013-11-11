$(document).ready(function() {
	$("#add").click(function (){
			top.openModule($("#frameid").val(),'标段-其它管理增加','/project/othermanage/input.do',null, 'addsuopei', $("#biaoDuanId").val());
	});
	$("#update").click(function(){
		var row = $('#mytables').datagrid('getSelected');
		if (row == null || row.id == null) {
			top.$.messager.show({
				title : '提示',
				msg : '请选择一行数据'
			});
			return;
		}
		top.openModule($("#frameid").val(),'标段-其它管理编辑','/project/othermanage/input.do',null, 'updatesuopei', row.id);
	});
		//查询标段下面其它管理信息
		initialization();
 
});






function initialization() {
	var height = document.body.clientHeight;
	var biaoDuanId=$("#biaoDuanId").val();
	var url=$("#coutextPath").val() + '/project/othermanage/findbybd.do?biaoDuanId='+biaoDuanId;
	$('#mytables').datagrid({
		url : url,
		columns : [ [ {
			field : 'dataname',
			title : '文件名',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'datafile',
			title : '文件地址',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'remark',
			title : '备注',
			width : fillsize(0.4),
			sortable : true
		}
		, {
			field : 'createtime',
			title : '上传时间',
			width : fillsize(0.2),
			sortable : true
		}
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#mytables').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#mytables').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}



function initPage(pageSize) {
	var p = $('#mytables').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#mytables').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}
function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-50) * percent;
}
function reloadGrid(){
	$("#mytables").datagrid("reload");
}

/**
 * 删除
 * @return
 */
function delzhengce() {
	var del = $('#mytables').datagrid('getSelections');  
    if(del.length <= 0 ){  
    	$.messager.show({
			title:'提示',
			msg: '请选择一行',
			timeout:3000
     });
        return;  
    }
    deletebyid(del[0].id);
    
}
//删除
function deletebyid(id){
	 $.ajax( {
			url : $("#coutextPath").val() + '/project/othermanage/delete.do',
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
						$('#mytables').datagrid('reload');   
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


