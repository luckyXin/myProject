$(document).ready(function() {
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					var url = $("#coutextPath").val() + '/project/tractProjectArrange/add.do';
					if ($("#arrangeId").val() != ""){
						url = $("#coutextPath").val() + '/project/tractProjectArrange/update.do';
					}
					$("#form").ajaxSubmit({
						url : url,
						success : function(data) {
							if (data.isSuccess != "") {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
								top.closeModule(moduleName, parentModuleName);
							} else  {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
							}
						}
					});
				}
			});
			
			$("#clean").click(function(){
				$(".governmentEmployee").remove();
				$("#governmentEmployee").val("");
			});
			
			$("#mainAuditName").click(function() {
				openshowdiv(3, "主审人员", 580, 350,270,null);
			});
			
			$("#governmentEmployee").click(function() {
				openshowdiv(3, "复核工程师", 580, 350,270,1);
			});
			
			$("#intermediaryName").click(function() {
				openshowdiv(4, "中介机构选择", 580, 350,270,null);
			});
});

function returnIntermediary(rowData) {
	$("#intermediaryId").val(rowData.id);
	$("#intermediaryName").val(rowData.intermediaryname);
	$("#intermediaryDutyName").val(rowData.legal);
	close();
}

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