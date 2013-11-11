$(document).ready(function() {
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var frameid=$("#frameid").val();
			var url = $("#coutextPath").val() + '/common/project/addrengong.do'
			if ($("#id").val() != ""){
				url = $("#coutextPath").val() + '/common/project/updaterengong.do'
			}
			$("#form").ajaxSubmit({
				url : url,
				success : function(data) {
					if (data.success) {
						top.showMsg(data.msg);
						top.reloadModule(frameid+"updatesuopei");
						top.closeModule(moduleName, parentModuleName);
					} else {
						top.showMsg(data.msg);
					}
				}
			});
		}
	});
	$('#tztime').datebox('setValue',$("#tzdate").val());
});






