$(document).ready(
				function() {
					var isarrange=$("#isarrange").val();
					if(isarrange==1){
						$("#look").show();
					}else{
						$("#look").hide();
					}
					
					
					var isfind = $("#find").val();
					var isinput = $("#input").val();
					if (isinput != '1') {
						top.$.messager.show({
							title : $("#title").val(),
							msg : $("#noPower").val()
						});
					}

					if (isfind) {
						// 安排状态初始化
						$("#isArrangeState").val(
								$("#arrangeState").combobox("getValue"));
						// 初始化列表
						initialization();
						// 注册检索按钮事件
						$("#search")
								.click(
										function() {
											 var arrangeState= $('#arrangeState').combobox("getValue");  
											if(arrangeState!=0){
												$("#look").show();
											}else{
												$("#look").hide();
											}
											if ($("#arrangeState").combobox(
													"getValue") == '0') {
												$("#add").show();
												$("#packProjectArrange").show()
												$("#update").hide();
												$("#delete").hide();
												$("#destroy").hide();
												$('#grid').treegrid('options').url = $(
														"#coutextPath").val()
														+ '/project/SingleProjectArrange/find.do?findProjectState=0'
											} else if ($("#arrangeState")
													.combobox("getValue") == '1') {
												$("#add").hide();
												$("#packProjectArrange").hide()
												$("#update").show();
												$("#delete").show();
												$("#destroy").show();
												$('#grid').treegrid('options').url = $(
														"#coutextPath").val()
														+ '/project/SingleProjectArrange/find.do'
											} else {
												$("#add").show();
												$("#packProjectArrange").show()
												$("#update").show();
												$("#delete").show();
												$("#destroy").show();
												$('#grid').treegrid('options').url = $(
														"#coutextPath").val()
														+ '/project/SingleProjectArrange/find.do?findAllProjectState=0'
											}
											$("#isArrangeState")
													.val(
															$("#arrangeState")
																	.combobox(
																			"getValue"));
											$("#grid").treegrid('options').queryParams = {
												ownerName : $("#ownerName")
														.val(),
												proejctName : $("#projectName")
														.val()
											};
											initialization();
										});
						$("#ownerName").click(function() {
							$('#searchOwner').window('open');
						});
					} else {
						$('#p1').hide();
						$('#p2').hide();
					}

					$("#ownerName").click(function() {
						openshowdiv(1, "业主查询", 580, 350,270,null);
					});

					$("#add")
							.click(
									function() {
										var row = $('#grid').treegrid(
												'getSelected');
										if (row == null || row.id == null) {
											top.$.messager.show({
												title : $("#title").val(),
												msg : '选择需要安排的项目'
											});
											return;
										}
										if (row.isArrangement == '1') {
											top
													.openModule(
															$("#frameId").val(),
															'单项目安排编辑',
															'/project/SingleProjectArrange/input.do',
															null, 'update',
															row.id);
										} else {
											top
													.openModule(
															$("#frameId").val(),
															'单项目安排编辑',
															'/project/SingleProjectArrange/input.do',
															null, 'add', row.id);
										}
									});
					$("#packProjectArrange")
							.click(
									function() {
										top
												.openModule(
														$("#frameId").val(),
														'打包项目安排编辑',
														'/project/SingleProjectArrange/input.do',
														null,
														'packProjectArrangeAdd');
									});

					$("#update").click(
									function() {
										var row = $('#grid').treegrid(
												'getSelected');
										if (row == null || row.id == null) {
											top.$.messager.show({
												title : $("#title").val(),
												msg : $("#noSelect").val()
											});
											return;
										}
										if (row.isArrangement == '0') {
											top.$.messager.show({
												title : $("#title").val(),
												msg : '没有安排的项目，无法编辑'
											});
											return;
										}
										top
												.openModule(
														$("#frameId").val(),
														'单项目安排编辑',
														'/project/SingleProjectArrange/input.do',
														null, 'update', row.id);
									});
					
					
					$("#look").click(
							function() {
								var row = $('#grid').treegrid(
										'getSelected');
								if (row == null || row.id == null) {
									top.$.messager.show({
										title : $("#title").val(),
										msg : $("#noSelect").val()
									});
									return;
								}
								if (row.isArrangement == '0') {
									top.$.messager.show({
										title : $("#title").val(),
										msg : '请选择项目'
									});
									return;
								}
								top.openModule(
												$("#frameId").val(),
												'单项目安排查看',
												'/project/SingleProjectArrange/input.do',
												null, 'look', row.id);
							});

					$("#delete").click(function() {
						var row = $('#grid').treegrid('getSelected');
						if (row.isArrangement == '0') {
							top.$.messager.show({
								title : $("#title").val(),
								msg : '没有安排的项目，无法删除'
							});
							return;
						}
						del("/project/SingleProjectArrange/delete.do", 'grid');
					});

					$("#destroy")
							.click(
									function() {
										var row = $('#grid').treegrid(
												'getSelected');
										if (row.isArrangement == '0') {
											top.$.messager.show({
												title : $("#title").val(),
												msg : '没有安排的项目，无法销毁'
											});
											return;
										}
										destroy(
												"/project/SingleProjectArrange/destroy.do",
												'grid');
									});
					
					//导出Excel
					$("#daoarrageexcel").click(function(){
						var url = $("#coutextPath").val() + '/common/project/outArrangeexcel.do?';
						window.location=url;
						
					});	
					
});

function initialization() {
	var height = document.body.clientHeight;
	var url;
	if ($("#arrangeState").combobox("getValue") == '0') {
		$("#add").show();
		$("#packProjectArrange").show()
		$("#update").hide();
		$("#delete").hide();
		$("#destroy").hide();
		url = $("#coutextPath").val()
				+ '/project/SingleProjectArrange/find.do?findProjectState=0';
	} else if ($("#arrangeState").combobox("getValue") == '1') {
		$("#add").hide();
		$("#packProjectArrange").hide()
		$("#update").show();
		$("#delete").show();
		$("#destroy").show();
		url = $("#coutextPath").val() + '/project/SingleProjectArrange/find.do';
	} else {
		$("#add").show();
		$("#packProjectArrange").show();
		$("#update").show();
		$("#delete").show();
		$("#destroy").show();
		url = $("#coutextPath").val()
				+ '/project/SingleProjectArrange/find.do?findAllProjectState=0';
	}
	$('#grid').treegrid({
		url : url,
		treeField : 'projectName',
		animate : true,
		idField : 'id',
		columns : [ [ 
		{
			field : 'datapreno',
			title : '送审编号',
			width : fillsize(0.1),
			sortable : true
		},
	   {
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
			field : 'sentAmount',
			title : '送审金额（元）',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'datapreTime',
			title : '接收资料时间',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'isArrangement',
			title : '安排状态',
			width : fillsize(0.15),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {

					return "未安排";
				} else if (value == 1) {

					return "已安排";
				}
			}
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : height - 195
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