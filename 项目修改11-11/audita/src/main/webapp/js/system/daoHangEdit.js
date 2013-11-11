$(document).ready(function() {
	$("td").addClass("tdClass");
	var path = $("#coutextPath").val();
	var url = path + $("#url").val();
	var processType = $("#processType").val();
	$("#remark").val(top.textTrim($("#remark").val()));
	var menuName;
	var state;
	var sort;
	var remark;
	if(processType == "update") {
		menuName = $("#menuName").val();
		state = $("#state").val();
		sort = $("#sort").val();
		remark = $("#remark").val();
	}
	
	$("#save").click(function() {
		var isUpdate = true;
		if (processType == "update") {
			var newMenuName = $("#menuName").val();
			var newState = $("#state").val();
			var newSort = $("#sort").val();
			var newRemark = $("#remark").val();
			if (menuName == newMenuName && newState == state 
					&& sort == newSort && newRemark == remark){
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
