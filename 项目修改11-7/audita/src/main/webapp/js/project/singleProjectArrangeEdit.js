// 项目安排时间
var projectArrangeTime = null;
// 项目安排备注
var projectArrangeRemark = null;
// 主审人员
var mainAuditId = null;
// 中介机构
var intermediaryId = null;
// 政府雇员
var governmentEmployee = null;
// 资料发送中介机构时间
var intermediarySendTime = null;
// 中介机构提交初审结果时间
var intermediaryAuditTime = null;
// 状态
var state = null;
// 政府雇员资料交接时间
var dataTransferTime = null;
$(document).ready(
	
		function() {
			//更改时间格式
	$("#sj1").text($("#sj1").text().substr(0,10))
	$("#sj2").text($("#sj2").text().substr(0,10))
	$("#sj3").text($("#sj3").text().substr(0,10))
	$("#sj4").text($("#sj4").text().substr(0,10))
			// 处理类型
			var processType = $("#processType").val();
			// 主审审批状态
			var mainAuditState = $("#mainAuditState").val();
			// 政府雇员审批状态
			var governmentEmployeeAuditState = $("#governmentEmployeeAuditState").val();
			
			if (processType == 'update'){
				projectArrangeTime = $("#projectArrangeTime").val();
				projectArrangeRemark = $("#projectArrangeRemark").val();
				mainAuditId = $("#mainAuditId").val();
				intermediaryId = $("#intermediaryId").val();
				governmentEmployee = $("#governmentEmployee").val();
				intermediarySendTime = $("#intermediarySendTime").val();
				intermediaryAuditTime = $("#intermediaryAuditTime").val();
				state = $("#state").val();
				dataTransferTime = $("#dataTransferTime").val();
			}
			if(processType != 'add' && processType != 'update'){
				$("#ownerName").click(function() {
					
				    if ($("#projectPackId").val() != ""){
				    	$.post($("#coutextPath").val() + "/project/SingleProjectArrange/find.do", {
							packProjectId : $("#projectPackId").val(),
							findPackSubProject : '1'
						}, function(data, textStatus) {
							if (data.total != '0') {
								top.$.messager.show({
									title : $("#title").val(),
									msg : '打包项目下面已经存在子项目，不能更改业主'
								});
							} else {
								openshowdiv(1, "业主查询", 580, 350,270,null);
							}
						});
				    } else {
				    	openshowdiv(1, "业主查询", 580, 350,270,null);
				    }
				});
				initialization();
			}
			$("#mainAuditName").click(function() {
				if (processType != 'add' && processType != 'packProjectArrangeAdd'){
					if (mainAuditState != '0'){
						top.$.messager.show({
							title : $("#title").val(),
							msg : "主审已经审批，无法更改"
						});
						return;
					}
				}
				openshowdiv(3, "主审人员", 580, 350,270,null);
			});
			$("#governmentEmployee").click(function() {
				if (processType != 'add' && processType != 'packProjectArrangeAdd'){
					if (governmentEmployeeAuditState != '0'){
						top.$.messager.show({
							title : $("#title").val(),
							msg : "复核工程师已经审批，无法更改"
						});
						return;
					}
				}
				openshowdiv(3, "复核工程师", 580, 350,270,1);
			});
			$('#intermediarySendTime').datebox(
					{
						onSelect : function(date) {
							intermediaryAuditTimeSet(date);
						}
					});
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					/*if (processType == 'update'){
						var newProjectArrangeTime = $("#projectArrangeTime").datebox('getValue');
						var newProjectArrangeRemark = $("#projectArrangeRemark").val();
						var newMainAuditId = $("#mainAuditId").val();
						var newIntermediaryId = $("#intermediaryId").val();
						var newGovernmentEmployee = $("#governmentEmployee").val();
						var newIntermediarySendTime = $("#intermediarySendTime").datebox('getValue');
						var newIntermediaryAuditTime = $("#intermediaryAuditTime").datebox('getValue');
						var newState = $("#state").val();
						if (projectArrangeTime == newProjectArrangeTime && newProjectArrangeRemark == projectArrangeRemark
								&& mainAuditId == newMainAuditId && intermediaryId == newIntermediaryId && 
								governmentEmployee == newGovernmentEmployee && intermediarySendTime == newIntermediarySendTime &&
								intermediaryAuditTime == newIntermediaryAuditTime && newState == state 
								&& dataTransferTime == $("#dataTransferTime").datebox('getValue')){
							$.messager.show({
								title : $("#title").val(),
								msg : $("#noUpdate").val()
							});
							return;
						}
					}*/
					
					var url;
					if (processType == 'update') {
						url = $("#coutextPath").val() + '/project/SingleProjectArrange/update.do'
					} else {
						url = $("#coutextPath").val() + $("#url").val()
					}
					
					$("#form").ajaxSubmit({
						url : url,
						success : function(data) {
							if (data.isSuccess) {
								top.showMsg(data.msg);
								top.reloadModule($("#fid").val());
								top.closeModule(moduleName, parentModuleName);
							} else if (data.isAddPackProjectState) {
								top.showMsg(data.msg);
								top.reloadModule($("#fid").val());
								$("#projectPackId").val(data.packProjectId);
							} else if (data.isUpdatePackProjectState){
								top.showMsg(data.msg);
								top.reloadModule($("#fid").val());
								if ($("#projectPackId").val() != "" && $("#projectPackId").val() != null) {
									top.closeModule(moduleName, parentModuleName);
								}
							} else  {
								top.showMsg(data.msg);
								top.reloadModule($("#fid").val());
								if ($("#projectPackId").val() != "" && $("#projectPackId").val() != null) {
									top.closeModule(moduleName, parentModuleName);
								}
							}
						}
					});
				}
			})
			$("#clean").click(function(){
				$(".governmentEmployee").remove();
				$("#governmentEmployee").val("");
			});
			
			$("#addSubProject").click(function (){
				var projectPackId = $("#projectPackId").val();
				if (projectPackId == "" || projectPackId == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : "创建打包项目之后，才能添加子项目"
					});
					return;
				}
				openshowdiv(2, "项目信息", 640, 380, 220,$("#ownerId").val());
			});
			
		$("#delSubProject").click(function (){
			var row = $('#packSubProject').datagrid('getSelected');
			if (row == null) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : '没有选择项目，无法删除'
				});
				return;
			}
			$.post($("#coutextPath").val()+"/project/SingleProjectArrange/delete.do", {
				packProjectId : $("#projectPackId").val(), subProjectId : row.id},
					function (data, textStatus){
				$.messager.show({
					title : $("#title").val(),
					msg : data.msg
				});
				top.reloadModule($("#fid").val());
				$("#sentAmount").val(data.sentAmount);
				var dateSendTime = $("#intermediarySendTime").datebox("getValue")
				var tmpTime = new Date(dateSendTime.replace(/-/g,"\/"));
				intermediaryAuditTimeSet(tmpTime)
				initialization();
			});
		});
		
		$("#suspend").click(function(){
			//得到资料预审id
			var datapreId=$("#projectId").val();
		   openproiv(550,350,datapreId);
		});
		$("#suspendclid").click(function(){
			//得到资料预审id
			var row = $('#packSubProject').datagrid('getSelected');
			if (row == null) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : '请选择项目'
				});
				return;
			}
		   openproiv(550,350,row.id);
		});
		
		//自审点击事件
		$("#isMySelfState").click(function(){
			if($("#isMySelfState").attr("checked")=="checked"){
				$("#auditgover").html("自审工程师：");
			}else{
				$("#auditgover").html("复核工程师: ");
			}
		});
		
});
/**
 * 中介送审时间设定
 */
function intermediaryAuditTimeSet(date){
	var sentAmount = $("#sentAmount").val();
	if(sentAmount == "" || sentAmount < 0){
		top.$.messager.show({
			title : $("#title").val(),
			msg : "送审金额有误，无法计算中介机构初审时间"
		});
		return;
	}
	var dateStr = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate());
	var now = new Date(dateStr.replace(/-/g, '/'));
	var targetDate = top.AddDays(now, firstAuditDay(sentAmount));
	var newDate = targetDate.format("yyyy-MM-dd");
	$("#intermediaryAuditTime").datebox("setValue", newDate);
}

/**
 * 中介机构提交初审结果时间判定
 */
function firstAuditDay(sentAmount) {
	// 50万，20天内
	if (sentAmount < 500000) {
		return 21;
		
	// 50万~200万，30天内
	} else if (sentAmount > 500000 && sentAmount <  2000000){
		return 31;
		
	// 200万~1000万，45天内
	} else if (sentAmount >  2000000 && sentAmount < 10000000) { 
		return 46;
	// 1000万以上，60天
	} else if (sentAmount > 10000000) {
		return 61;
	} else {
		return 0
	}
}

/**
 * 中介机构安排回调函数
 */
function returnIntermediary(str) {
	$("#intermediaryId").val(str.id);
	$("#intermediaryName").val(str.intermediaryname);
	$('#form').form('validate');
	close();
}

/**
 * 项目业主安排回调函数
 * 
 * @param str
 */
function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
	close();
}

/**
 * 安排项目回调函数
 * 
 * @param str
 */
function returnbackProject(id) {
		$.post($("#coutextPath").val() + "/project/SingleProjectArrange/add.do", {
			packProjectId : $("#projectPackId").val(),
			subProjectId : id
		}, function(data, textStatus) {
			top.$.messager.show({
				title : $("#title").val(),
				msg : data.msg
			});
			
			$("#sentAmount").val(data.sentAmount);
			top.reloadModule($("#fid").val());
			var dateSendTime = $("#intermediarySendTime").datebox("getValue")
			var tmpTime = new Date(dateSendTime.replace(/-/g,"\/"));
			intermediaryAuditTimeSet(tmpTime)
			initialization();
		});
		close();
}


/**
 * 主审人员回调函数
 * 
 * @param data
 */
function returnMainAuditEmployee(data) {
	$("#mainAuditId").val(data.id);
	$("#mainAuditName").val(data.userName);
	$('#form').form('validate');
	close();
}


function returnbackguyuanProject(data){
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
			if (data.id == stepvar) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : "已经存在"
				});
				return;
			}
		}
		if ("" == governmentEmployee) {
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployeeId").val(data.id);
			$("#governmentEmployee").val(data.userName);
		} else if (count == 4) {
			$(".governmentEmployee:eq(0)").val(data.id);
			var strs = new Array();
			strs = governmentEmployee.split(",");
			strs[4] = data.userName;
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
			hiddenGovernmentEmployee.attr("value", data.id);
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployee").val(governmentEmployee + "," + data.userName);
		}
		$('#form').form('validate');
	close();
}
/**
 * 政府雇员回调函数
 */
function returnGovernmentEmployee(data) {
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
		if (data.id == stepvar) {
			top.$.messager.show({
				title : $("#title").val(),
				msg : "已经存在"
			});
			return;
		}
	}
	if ("" == governmentEmployee) {
		$("#governmentEmployee").after(hiddenGovernmentEmployee);
		$("#governmentEmployeeId").val(data.id);
		$("#governmentEmployee").val(data.userName);
	} else if (count == 4) {
		$(".governmentEmployee:eq(0)").val(data.id);
		var strs = new Array();
		strs = governmentEmployee.split(",");
		strs[4] = data.userName;
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
		hiddenGovernmentEmployee.attr("value", data.id);
		$("#governmentEmployee").after(hiddenGovernmentEmployee);
		$("#governmentEmployee").val(governmentEmployee + "," + data.userName);
	}
	$('#form').form('validate');
	close();
}

function initialization() {
	var height = document.getElementById("p3").clientHeight
	var url;
	if ($("#projectPackId").val() != null && $("#projectPackId").val() != ""){
		
		url = $("#coutextPath").val() + '/project/SingleProjectArrange/find.do?findPackSubProject=0&packProjectId='+$("#projectPackId").val();
	} else {
		url = $("#coutextPath").val() + '/project/SingleProjectArrange/find.do?findPackSubProject=0';
	}
	$('#packSubProject').datagrid({
		url : url,
		columns : [ [ {
			field : 'projectName',
			title : '投资项目名称',
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
			field : 'state',
			title : '状态',
			width : fillsize(0.15),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {

					return "启用";
				} else if (value == 1) {

					return "禁用";

				} else if (value == 2) {
					return "删除";
				}
			}
		}] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : height - 30
	});
	// 设置分页控件
	var p = $('#packSubProject').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 10) * percent;
}