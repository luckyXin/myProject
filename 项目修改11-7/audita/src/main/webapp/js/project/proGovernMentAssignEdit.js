var fileNumber = 0;
$(document).ready(
		function() {
			// 处理类型
			var processType = $("#processType").val();

			// 交办iD
			var assignId = $("#assignId").val();

			initialization();

			$("#save").click(function() {
				if ($('#form').form('validate')) {
					var url = $("#coutextPath").val() + $("#url").val();
					if (processType == 'add') {
						if ($("#assignId").val() != "" && $("#assignCode").val() == undefined) {
							alert("123");
							url = $("#coutextPath").val() + "/project/governmentAssign/update.do";
							
						} else if ($("#assignId").val() != "" && $("#assignCode").val() != undefined) {
							alert("abc");
							url = $("#coutextPath").val() + "/project/governmentAssign/updateAssignCode.do";
						}
					}
					$("#form").ajaxSubmit({
						url : url,
						success : function(data) {
							if (processType == 'add') {
								$("#assignId").val(data.id);
								top.showMsg(data.msg);
								top.reloadModule($("#fid").val());
								var stempUrl = $("#coutextPath").val() + "/project/governmentAssign/add.do" ;
								if (stempUrl != url) {
									top.closeModule(moduleName, parentModuleName);
								}
							} else {
								var result = eval("(" + data + ")");
								top.showMsg(result.msg);
								top.reloadModule($("#fid").val());
								if ($("#assignId").val() != "" && $("#assignId").val() != undefined) {
									top.closeModule(moduleName, parentModuleName);
								}
							}
						}
					});
				}
			});
			
			//导出Excel
			$("#daoexcel").click(function(){
				var url = $("#coutextPath").val() + '/common/project/outexcel.do?assignId='+$("#assignId").val();
				window.location=url;
				
			});
			$("#addSubProject").click(function() {
				var assignId = $("#assignId").val();
				if (assignId == "" || assignId == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : "保存批次之后，才能添加项目"
					});
					return;
				}
				openshowdiv(5, "项目信息", 650, 380, 220,null);
			});

			$("#delSubProject").click(
					function() {
						var assignId = $("#assignId").val();
						var row = $('#governMentAssignProject').datagrid('getSelected');
						if (row == null) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : '请选择需要删除的项目'
							});
							return;
						}
						$.post($("#coutextPath").val()
								+ "/project/governmentAssign/delete.do", {
							deleteAssignProjectState : '1',
							assignId : $("#assignId").val(),
							assignProjectId : row.id
						}, function(data, textStatus) {
							top.$.messager.show({
								title : $("#title").val(),
								msg : data.msg
							});
							initialization();
						});
					});
		    $("#addfile").click(function (){
		    	if (fileNumber > 10) {
		    		top.$.messager.show({
						title : $("#title").val(),
						msg : "最多只能添加十个文件"
					});
		    		return;
		    	}
		    	fileNumber++;
		    	var html = '<tr><td class="label">政府交办文件：</td><td colspan="3"><input type="file" style="width: 400px;" class="text" id="file" name="fileName" value="浏览" /><a class="easyui-linkbutton" id="deleteFile'+fileNumber+'" onclick="deleteFile(this);">删除</a></td></tr>'
		        $("#mytableid>tbody").append(html);
		    	$("#deleteFile"+fileNumber).linkbutton({
		    		iconCls:"icon-cancel"
		    	});
		    });
		});

function deleteFile(obj){
	$("#"+obj.id).parent().parent().remove();
}
function returnbackProject(id) {
	// 为添加政府交办项目
	$.post($("#coutextPath").val() + "/project/governmentAssign/add.do", {
		assignProjectState : '1',
		assignId : $("#assignId").val(),
		assignProjectId : id
	}, function(data, textStatus) {
		top.$.messager.show({
			title : $("#title").val(),
			msg : data.msg
		});
		initialization();
	});
	close();
}

function initialization() {
	var height = document.body.clientHeight;
	var url = $("#coutextPath").val()
			+ '/project/governmentAssign/find.do?findAssignSubProject=0&assignId='
			+ $("#assignId").val();
	$('#governMentAssignProject').datagrid({
		url : url,
		columns : [ [ 
		              {
			field : 'datapreno',
			title : '编号',
			width : fillsize(0.1),
			sortable : true
		},{
			field : 'projectName',
			title : '项目名称',
			width : fillsize(0.1),
			sortable : true
		},
		{
			field : 'projectNo',
			title : '立项文号',
			width : fillsize(0.1),
			sortable : true
		}, 
		{
			field : 'proownerid',
			title : '项目业主',
			width : fillsize(0.1),
			sortable : true
		}, 
		{
			field : 'mainAuditId',
			title : '主审',
			width : fillsize(0.1),
			sortable : true
		},
		{
			field : 'intermediaryId',
			title : '事务所',
			width : fillsize(0.1),
			sortable : true
		},
		{
			field : 'governmentEmp',
			title : '复核工程师',
			width : fillsize(0.1),
			sortable : true
		},
		{
			field : 'shigongunit',
			title : '施工单位',
			width : fillsize(0.1),
			sortable : true
		},
		
		{
			field : 'sendMoney',
			title : '送审金额（元）',
			width : fillsize(0.1),
			sortable : true
		}] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#governMentAssignProject').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#governMentAssignProject').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}
function initPage(pageSize) {
	var p = $('#governMentAssignProject').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#governMentAssignProject').datagrid({
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
function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
	close();
}