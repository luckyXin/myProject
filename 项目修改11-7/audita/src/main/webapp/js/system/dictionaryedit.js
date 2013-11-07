$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#remark").val(top.textTrim($("#remark").val()));
		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/system/dictionary/update.do',
					success : function(data) {
						if (data.success) {
							top.showMsg(data.msg);
							top.reloadModule(frameid);
							top.closeModule(moduleName, parentModuleName);
						} else {
							top.showMsg(data.msg);
						}
					}
				});
			} else {
				$.messager.show( {
					title : "提示",
					msg : "请正确填写"
				});
				return false;
			}
		});

		//关闭按钮
		$("#back").click(function() {
			top.closeModule(moduleName, parentModuleName);
		});
});
