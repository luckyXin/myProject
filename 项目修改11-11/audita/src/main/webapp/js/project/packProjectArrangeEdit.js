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
$(document).ready(
		function() {
			// 处理类型
			var processType = $("#processType").val();
			// 主审审批状态
			var mainAuditState = $("#mainAuditState").val();
			// 政府雇员审批状态
			var governmentEmployeeAuditState = $("#governmentEmployeeAuditState").val();
			// 中介机构审批状态
			var intermediaryAuditState = $("#intermediaryAuditState").val();
			
			if (processType == 'update'){
				projectArrangeTime = $("#projectArrangeTime").val();
				projectArrangeRemark = $("#projectArrangeRemark").val();
				mainAuditId = $("#mainAuditId").val();
				intermediaryId = $("#intermediaryId").val();
				governmentEmployee = $("#governmentEmployee").val();
				intermediarySendTime = $("#intermediarySendTime").val();
				intermediaryAuditTime = $("#intermediaryAuditTime").val();
				state = $("#state").val();
			}
			
			$("#ownerName").click(function() {
			    if (processType != 'add'){
			    	top.$.messager.show({
						title : $("#title").val(),
						msg : "项目业主不可变更"
					});
			    	return;
			    }
			    openshowdiv(1, "业主查询", 580, 350,270,null);
			});
			$("#mainAuditName").click(function() {
				if (processType != 'add'){
					if (mainAuditState != '0'){
						top.$.messager.show({
							title : $("#title").val(),
							msg : "主审已经审批，无法更改"
						});
						return;
					}
				}
				openshowdiv(3, "主审人员", 600, 400, 220,null);
			});
			$("#governmentEmployee").click(function() {
				if (processType != 'add'){
					if (governmentEmployeeAuditState != '0'){
						top.$.messager.show({
							title : $("#title").val(),
							msg : "复核工程师已经审批，无法更改"
						});
						return;
					}
				}
				openshowdiv(3, "复核工程师", 600, 400, 220,null);
			});
			$("#intermediaryName").click(function() {
				if (processType != 'add'){
					if (intermediaryAuditState != '0'){
						top.$.messager.show({
							title : $("#title").val(),
							msg : "中介机构已经审批，无法更改"
						});
						return;
					}
				}
				openshowdiv(4, "中介机构", 600, 400, 220,null);
			});
			$('#intermediarySendTime').datebox(
					{
						onSelect : function(date) {
							var sentAmount = $("#sentAmount").val();
							if(sentAmount == "" || sentAmount < 0){
								top.$.messager.show({
									title : $("#title").val(),
									msg : "送审金额有误，无法计算中介机构初审时间"
								});
								return;
							}
							var dateStr = date.getFullYear() + "-"
									+ (date.getMonth() + 1) + "-"
									+ date.getDate();
							var now = new Date(dateStr.replace(/-/g, '/'));
							var targetDate = top.AddDays(now, firstAuditDay(sentAmount));
							var newDate = targetDate.format("yyyy-MM-dd");
							$("#intermediaryAuditTime").datebox("setValue", newDate);
						}
					});
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					if (processType == 'update'){
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
								intermediaryAuditTime == newIntermediaryAuditTime && newState == state){
							$.messager.show({
								title : $("#title").val(),
								msg : $("#noUpdate").val()
							});
							return;
						}
					}
					
					$("#form").ajaxSubmit({
						url : $("#coutextPath").val() + $("#url").val(),
						success : function(data) {
							if (data.isSuccess) {
								top.showMsg(data.msg);
								top.reloadModule($("#fid").val());
								top.closeModule(moduleName, parentModuleName);
							} else {
								msg = data.msg;
								top.showMsg(msg);
							}
						}
					});
				}
			});
			$("#clean").click(function(){
				$(".governmentEmployee").remove();
				$("#governmentEmployee").val("");
			});
		});

/**
 * 中介机构提交初审结果时间判定
 */
function firstAuditDay(sentAmount) {
	
	// 50万，20天内
	if (sentAmount < 50) {
		return 20;
		
	// 50万~200万，30天内
	} else if (sentAmount > 50 && sentAmount <  200){
		return 30;
		
	// 200万~1000万，45天内
	} else if (sentAmount >  200 && sentAmount < 1000) { 
		return 45;
	// 1000万以上，60天
	} else if (sentAmount > 1000) {
		return 60;
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

function returnback(str) {
	$("#ownerName").val(str.ownerName);
	$("#ownerId").val(str.id);
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
