$(document).ready(function() {
	$("#add").click(function (){
			top.openModule($("#frameid").val(),'标段-政策性调整增加','/project/policychang/input.do',null, 'addsuopei', $("#biaoDuanId").val());
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
		top.openModule($("#frameid").val(),'标段-政策性调整编辑','/project/policychang/input.do',null, 'updatesuopei', row.id);
	});
	
	$("#adddata").click(function (){
		top.openModule($("#frameid").val(),'标段-材料询价增加','/project/dataenquiry/input.do',null, 'addsuopei', $("#biaoDuanId").val());
     });
     $("#updatedata").click(function(){
	var row = $('#mydatatables').datagrid('getSelected');
	if (row == null || row.id == null) {
		top.$.messager.show({
			title : '提示',
			msg : '请选择一行数据'
		});
		return;
	}
	top.openModule($("#frameid").val(),'标段-材料询价编辑','/project/dataenquiry/input.do',null, 'updatesuopei', row.id);
    });
    if("1"!=$("#datamoney").val())
    {
		//查询标段下面索赔信息
		initialization();
		 //查询政策性调整的价格
		findTotalMoney();
    }
	if("1"==$("#datamoney").val())
	{	
	    //查询标段下面的材料询价文件
	     initdatafile();
	}
});






function initialization() {
	var height = document.body.clientHeight;
	var biaoDuanId=$("#biaoDuanId").val();
	var url=$("#coutextPath").val() + '/project/policychang/findbybd.do?biaoDuanId='+biaoDuanId;
	$('#mytables').datagrid({
		url : url,
		columns : [ [ {
			field : 'artificialfile',
			title : '人工费用调整',
			width : fillsize(0.15),
			sortable : false
		}, {
			field : 'datamoney',
			title : '材料价',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'machinemoney',
			title : '机械价',
			width : fillsize(0.15),
			sortable : true
		}
		, {
			field : 'otherContext',
			title : '其他价',
			width : fillsize(0.15),
			sortable : true
		}
		, {
			field : 'remark',
			title : '金额调整依据',
			width : fillsize(0.4),
			sortable : true
		}] ],
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
			initPage1(pageSize);
		}
	});
}
function initPage1(pageSize) {
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
			initPage1(pageSize);
		}
	});
}

function initdatafile() {
	var height = document.body.clientHeight;
	var biaoDuanId=$("#biaoDuanId").val();
	var url=$("#coutextPath").val() + '/project/dataenquiry/findbybd.do?biaoDuanId='+biaoDuanId;
	$('#mydatatables').datagrid({
		url : url,
		columns : [ [ {
			field : 'dataname',
			title : '文件名',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'datafile',
			title : '文件',
			width : fillsize(0.3),
			sortable : true
		}, {
			field : 'createtime',
			title : '上传时间',
			width : fillsize(0.3),
			sortable : true
		}
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#mydatatables').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20,30 , 40, 50],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#mydatatables').datagrid({
				height : (310 * newHeightNum)
			});
			initPage2(pageSize);
		}
	});
}
function initPage2(pageSize){
	var p = $('#mydatatables').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#mydatatables').datagrid({
				height : (310 * newHeightNum)
			});
			initPage2(pageSize);
		}
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-50) * percent;
}
function reloadGrid(){
	$("#mytables").datagrid("reload");
	$("#mydatatables").datagrid("reload");
	findTotalMoney();
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
			url : $("#coutextPath").val() + '/project/policychang/delete.do',
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


/**
 * 删除材料询价
 * @return
 */
function deldataenquiry() {
	var del = $('#mydatatables').datagrid('getSelections');  
    if(del.length <= 0 ){  
    	$.messager.show({
			title:'提示',
			msg: '请选择一行',
			timeout:3000
     });
        return;  
    }
    deletedatabyid(del[0].id);
    
}
//删除
function deletedatabyid(id){
	 $.ajax( {
			url : $("#coutextPath").val() + '/project/dataenquiry/delete.do',
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
						$('#mydatatables').datagrid('reload');   
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

function findTotalMoney(){
	//获取标段id
	var biaoDuanId =$("#biaoDuanId").val();
	 $.ajax( {
			url : $("#coutextPath").val() + '/project/policychang/findTotalMoney.do',
			type : "POST",
			dataType : "json",
			data : "biaoDuanId="+biaoDuanId,
			success : function(data) {
		        $("#rgTotal").html(data.rgTotal);
		        $("#clTotal").html(data.clTotal);
		        $("#jxTotal").html(data.jxTotal);
		        $("#qtTotal").html(data.qtTotal);
		        $("#total").html(data.total);
	         }
	   });	
}
