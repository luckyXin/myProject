$(document).ready(function() {
	$("td").addClass("tdClass");
	var path = $("#coutextPath").val();
	var url = path + $("#url").val();
	var processType = $("#processType").val();
	$("#remark").val(top.textTrim($("#remark").val()));
	$(".easyui-combobox").attr("editable","false");
	var menuName;
	var state;
	var remark;
	var pid;
	var sort;
	if(processType == "update") {
		menuName = $("#menuName").val();
		state = $("#state").combobox("getValue");
		remark = $("#remark").val();
		pid = $("#pid").combobox("getValue");
		sort = $("#sort").val();
	}
	$("#save").click(function() {
		var isUpdate = true;
		if (processType == "update") {
			var newMenuName = $("#menuName").val();
			var newState = $("#state").combobox("getValue");
			var newRemark = $("#remark").val();
			var newPid = $("#pid").combobox("getValue");
			var newSort = $("#sort").val();
			if (menuName == newMenuName && newState == state 
                   && newRemark == remark && newPid == pid && newSort == sort){
				isUpdate = false;
			} 
		}
		
		if (isUpdate) {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit({
					url : url,
					success : function(data) {
						if (data.menuId) {
							top.showMsg(data.msg);
							top.reloadModule($("#id").val());
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
