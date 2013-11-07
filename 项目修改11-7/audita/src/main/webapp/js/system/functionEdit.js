$(document).ready(function() {
	$("td").addClass("tdClass");
	var path = $("#coutextPath").val();
	var url = path + $("#url").val();
	var processType = $("#processType").val();
	$("#remark").val(top.textTrim($("#remark").val()));
	$(".easyui-combobox").attr("editable","false");
	var name;
	var state;
	var method;
	var remark;
	if(processType == "update") {
		name = $("#name").val();
		state = $("#state").combobox("getValue");
		method = $("#method").val();
		remark = $("#remark").val();
	}
	$("#save").click(function() {
		var isUpdate = true;
		if (processType == "update") {
			var newName = $("#name").val();
			var newState = $("#state").combobox("getValue");
			var newMethod = $("#method").val();
			var newRemark = $("#remark").val();
			if (name == newName && newState == state 
					&& method == newMethod && newRemark == remark){
				isUpdate = false;
			} 
		}
		
		if (isUpdate) {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit({
					url : url,
					success : function(data) {
						if (data.isSuccess) {
							top.showMsg(data.msg);
							top.reloadModule($("#fatherId").val());
							top.closeModule(moduleName, parentModuleName);
						} else {
							msg = data.msg;
							top.showMsg(msg);
						}
					}
				});
			}
		} else {
			$.messager.show({
				title : $("#title").val(),
				msg : $("#noUpdate").val()
			});
		}
	});
});
