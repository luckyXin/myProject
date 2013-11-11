$(document).ready(
		function() {
			var isfind = $("#find").val();
			var isinput = $("#input").val();
			if (isinput != '1') {
				top.$.messager.show({
					title : $("#title").val(),
					msg : $("#noPower").val()
				});
			}
			if (!isfind) {
				$('#p1').hide();
				$('#p2').hide();
				top.$.messager.show({
					title : $("#title").val(),
					msg : "没有查询权限"
				});
				return;
			}
			$("#reset").click(function(){
				$('#from').form('clear');
			});
			$("#search").click(function() {
				$("#grid").datagrid('options').queryParams = {
					projectOwner : $("#ownerId").val(),
					processPeople : $("#employeeId").val(),
					projectName : $("#projectName").val(),
					projectState : $("#projectState").val(),
					processStartTime : $("#processStartTime").datebox("getValue"),
					processEndTime : $("#processEndTime").datebox("getValue")
				};
				initialization();
			});

			$("#audit").click(
					function() {
						var row = $('#grid').datagrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "未选择项目"
							});
						}
						top.openModule($("#frameId").val(), '项目明细展示',
								'/search/myselfCompleteProject/input.do', null,
								'add', row.projectId);
					});
			
			initialization();
			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});
		});

/**
 * 项目业主回调方法
 * 
 * @param rowData
 */
function returnback(rowData) {
	$("#ownerName").val(rowData.ownerName);
	$("#ownerId").val(rowData.id);
	close();
}


function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$('#grid').datagrid(
			{
				url : $("#coutextPath").val()
						+ '/search/myselfCompleteProject/find.do',
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
					field : 'constructName',
					title : '施工企业',
					width : fillsize(0.2)
				}, {
					field : 'processBeforeState',
					title : '处理之前状态',
					width : fillsize(0.15)
				}, {
					field : 'processAfterState',
					title : '处理之后状态',
					width : fillsize(0.1),
					formatter : function(value, rowData,
							rowIndex) {
						if (value == "" || value == null) {
							return "审计结束";
						}else{
							return value;
						}
					}
				}, {
					field : 'processTime',
					title : '处理时间',
					width : fillsize(0.1)
				}] ],
				singleSelect : true,
				pagination : true,
				striped : true,
				height : height - 195,
				pageNumber : 1
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

function reloadGrid() {
	$("#grid").datagrid("reload");
}