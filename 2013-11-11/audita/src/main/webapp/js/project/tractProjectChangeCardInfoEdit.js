$(document).ready(function() {
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var url = $("#coutextPath").val() + '/project/tractProjectChangeCard/add.do'
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
