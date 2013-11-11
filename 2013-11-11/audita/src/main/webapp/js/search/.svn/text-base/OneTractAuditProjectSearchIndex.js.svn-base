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
												projectName : $("#projectName").val(),
												reallyStartTime : $("#reallyStartTime").datebox("getValue"),
												reallyEndTime : $("#reallyEndTime").datebox("getValue"),
												projectType : $("#projectType").combobox("getValue"),
												dataTransferState : $("#dataTransferState").combobox("getValue"),
												qingBiaoState : $("#qingBiaoState").combobox("getValue")
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

					$("#update").click(function() {
						var row = $('#grid').treegrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "未选择标段"
							});
							return false;
						}else{
							if (row.id.length != 32) {
								top.$.messager.show({
									title : $("#title").val(),
									msg : "未选择标段"
								});
								return false;
							}
						}
						
						top.openModule($("#frameId").val(), '项目明细展示',
								'/search/myselfTractProject/input.do', null,
								'showProject', row.id);
					});
					
					$("#templateimport").click(function() {
						var row = $('#grid').treegrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : "未选择标段"
							});
							return false;
						}else
						{	
							if (row.id.length != 32) {
								top.$.messager.show({
									title : $("#title").val(),
									msg : "未选择标段"
								});
								return false;
							}
						}
						top.openModule($("#frameId").val(), '模板信息',
								'/search/myselfTractProject/input.do', null,
								'templateimport', row.id);
					});
					
					
					//导出个人跟踪审计台账
					$("#daoexceltz").click(function(){
						//判断该标段是否存在月报
						$.ajax( {
							url : $("#coutextPath").val() + '/common/project/haveuserMonth.do',
							type : "POST",
							dataType : "json",
							data : "",
							success : function(data) {
									if ("success"==data.success) {
										var url = $("#coutextPath").val() + '/common/project/tractoneExcel.do';
										window.location=url;
										return false;
									}else{
										$.messager.show({
											title:'提示',
											msg:"审计标段还没有月报信息！",
											timeout:3000
										 }
									    );
									}
							}
					   });
						
					});
					
	                initialization();
				});

function initialization() {
	var height = document.body.clientHeight;
	var url = $("#coutextPath").val() + "/search/myselfTractProject/find.do?qingBiaoState="+$("#qingBiaoState").combobox("getValue");
	$('#grid').treegrid({
		url : url,
		treeField : 'projectName',
		animate : true,
		idField : 'id',
		columns : [ [ {
			field : 'projectName',
			title : '项目名称',
			width : fillsize(0.15)
		}, {
			field : 'ownerName',
			title : '项目业主',
			width : fillsize(0.1)
		}, {
			field : 'biaoDuanName',
			title : '标段名称',
			width : fillsize(0.15)
		}, {
			field : 'projectGaiKuang',
			title : '工程概况',
			width : fillsize(0.2)
		},	
		 {
			field : 'projectType',
			title : '招标类型',
			width : fillsize(0.05),
			formatter : function(value, rowData, rowIndex) {
				  if(null!= value && ""!=value)
				  {
					  if (value == 0) {
							return "招标";
						} else if (value == 1) {
							return "EPC";
						}else{
							return "BT";
						}
				  }else
				  {
					  return "";
				  }	  
					
				}
		},
		{
			field : 'qingBiaoState',
			title : '清标状态',
			width : fillsize(0.1),
			formatter : function(value, rowData, rowIndex) {
			  if(null!= value && ""!=value)
			  {
				  if (value == 0) {
						return "未清标";
					} else if (value == 1) {
						return "已清标";
					}
			  }else
			  {
				  return "";
			  }	  
				
			}
		},{
			field : 'dataTransferState',
			title : '是否移交',
			width : fillsize(0.05),
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "已移交";
				} else {
					if (rowData._parentId) {
						return "未移交";
					} else {
						return "";
					}
				}
			}
		},{
			field : 'reallyStartTime',
			title : '实际开工时间',
			width : fillsize(0.1)
		}
		
		] ],
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