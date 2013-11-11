$(document).ready(function() {
					var isfind = $("#find").val();
					var isinput = $("#input").val();
					if (isinput != '1') {
						top.$.messager.show({
							title : $("#title").val(),
							msg : $("#noPower").val()
						});
					}
					$("#reset").click(function(){
						$('#from').form('clear');
					});
					if (isfind) {
						// 初始化列表
						initialization();
						// 注册检索按钮事件
						$("#search").click(function() {
											$("#grid").treegrid('options').queryParams = {
												ownerName : $("#ownerId").val(),
												projectName : $("#projectName").val()
											};
											initialization();
										});
					} else {
						$('#p1').hide();
						$('#p2').hide();
					}
					$("#ownerName").click(function() {
						openshowdiv(1, "业主查询", 580, 350,270,null);
					});

					$("#add").click(function() {
						var row = $('#grid').treegrid('getSelected');
						if (row == null || row.id == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : $("#noSelect").val()
							});
							return;
						}
						top.openModule($("#frameId").val(),
							'项目标段-变更签证录入','/project/tractProjectChangeCard/input.do',
							null, 'add',row.id)
					});
	                initialization();
				});

function initialization() {
	var height = document.body.clientHeight;
	var url = $("#coutextPath").val() + "/project/tractProjectChangeCard/find.do";
	$('#grid').treegrid({
		url : url,
		treeField : 'projectName',
		animate : true,
		idField : 'id',
		columns : [ [ {
			field : 'projectName',
			title : '项目名称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'ownerName',
			title : '项目业主',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'biaoDuanName',
			title : '标段名称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'projectGaiKuang',
			title : '工程概况',
			width : fillsize(0.25),
			sortable : true
		},{
			field : 'isPay',
			title : '是否付费',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "是";
				} else if (value == 1) {
					return "否";
				} else {
					if (rowData._parentId) {
						return "否";
					} else {
						return "";
					}
				}
			}
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#grid').treegrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').treegrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}
function initPage(pageSize){
	var p = $('#grid').treegrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').treegrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth) * percent;
}
function reloadGrid() {
	$('#grid').treegrid("reload");
}
function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
	close();
}