$(document).ready(function() {
	$("#add").click(function (){
			top.openModule($("#frameid").val(),'标段-索赔增加','/project/claimindemnity/input.do',null, 'addsuopei', $("#biaoDuanId").val());
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
		top.openModule($("#frameid").val(),'标段-索赔编辑','/project/claimindemnity/input.do',null, 'updatesuopei', row.id);
	});
	
	$("#search").click(function() {
		$("#mytables").datagrid('options').queryParams = {
			claimIndemnityType : $("#claimIndemnityType").val()
		};
		$('#mytables').datagrid('reload');   
	});
		
	//查询标段下面索赔信息
	initialization();
});

/**
 * 主审人员回调函数
 * @param data
 */
function returnMainAuditEmployee(data) {
	$("#mainAuditId").val(data.id);
	$("#mainAuditName").val(data.userName);
	$('#form').form('validate');
	close();
}

function returnbackguyuanProject(data){
	for(var j=0;j<data.length;j++){
		var hiddenGovernmentEmployee = $('<input type="hidden" class="governmentEmployee" id="governmentEmployeeId" name="governmentEmployeeId">');
		var governmentEmployee = $("#governmentEmployee").val();
		var count = 0;
		for ( var i = 0; i < governmentEmployee.length; i++) {
			if ("," == governmentEmployee.charAt(i)) {
				count++;
			}
		}
		for ( var k = 0; k < $(".governmentEmployee").length; k++) {
			var stepvar = $(".governmentEmployee:eq(" + k + ")").val();
			if (data[j].id == stepvar) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : "已经存在"
				});
				return;
			}
		}
		if ("" == governmentEmployee) {
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployeeId").val(data[j].id);
			$("#governmentEmployee").val(data[j].userName);
		} else if (count == 4) {
			$(".governmentEmployee:eq(0)").val(data[j].id);
			var strs = new Array();
			strs = governmentEmployee.split(",");
			strs[4] = data[j].userName;
			var governmentEmployees = "";
			for ( var j = 0; j < strs.length; j++) {
				if (j == 0) {
					governmentEmployees += strs[j];
				} else {
					governmentEmployees += ',' + strs[j];
				}
			}
			$("#governmentEmployee").val(governmentEmployees);
		} else {
			hiddenGovernmentEmployee.attr("value", data[j].id);
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployee").val(governmentEmployee + "," + data[j].userName);
		}
		$('#form').form('validate');
    }
	close();
}



function initialization() {
	var height = document.body.clientHeight;
	var biaoDuanId=$("#biaoDuanId").val();
	var url=$("#coutextPath").val() + '/project/claimindemnity/findbybd.do?biaoDuanId='+biaoDuanId;
	$('#mytables').datagrid({
		url : url,
		columns : [ [ {
			field : 'claimIndemnityType',
			title : '索赔类型',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'constructSentMoney',
			title : '施工方提出金额',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'indemnityMoney',
			title : '中介建议价',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'auditMoney',
			title : '审计建议价',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'ownerReadyMoney',
			title : '业主确定价',
			width : fillsize(0.2),
			sortable : true
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height:310
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
function delsuop() {
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
			url : $("#coutextPath").val() + '/project/claimindemnity/delete.do',
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
