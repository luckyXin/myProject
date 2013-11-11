$(document).ready(function() {
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var url = $("#coutextPath").val() + '/project/tractMonthReport/add.do'
			$("#form").ajaxSubmit({
				url : url,
				success : function(data) {
					if (data.isSuccess) {
						top.showMsg(data.msg);
						top.reloadModule($("#frameId").val());
						top.closeModule(moduleName, parentModuleName);
					} else  {
						top.showMsg(data.msg);
						top.reloadModule($("#frameId").val());
					}
				}
			});
		}
	});
});

/**
 * 获取累计完成产值
 * @return
 */
function changeNowMonthCompleteValue(){
	$.post($("#coutextPath").val() + "/project/tractMonthReport/getTotalCompleteValue.do", {
		nowMonthCompleteValue : $("#nowMonthCompleteValue").val()
	}, function(data, textStatus) {
		$("#totalCompleteValue").val(data.totalCompleteValue);
	});
}

/**
 * 获取累计支付产值
 * @return
 */
function changeNowMonthPayValue() {
	$.post($("#coutextPath").val() + "/project/tractMonthReport/getAddPayProjectMoney.do", {
		nowMonthPayValue : $("#nowMonthPayValue").val()
	}, function(data, textStatus) {
		$("#addPayProjectMoney").val(data.addPayProjectMoney);
	});
}