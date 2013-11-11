var biaoDuanNameStemp = null;
$(document).ready(function() {
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					var url = $("#coutextPath").val() + '/project/tractProjectCreate/add.do';
					if ($("#id").val() != ""){
						url = $("#coutextPath").val() + '/project/tractProjectCreate/update.do';
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
			biaoDuanNameStemp = $("#biaoDuanName").val();
			var flg = true;
			// 验证标段名称
			$.extend($.fn.validatebox.defaults.rules, {
				ishave : {
				validator : function(value) {
				if ($("#id").val() != "" && biaoDuanNameStemp == $("#biaoDuanName").val()){
					return true;
				}
			   $.post($("#coutextPath").val() + "/project/tractProjectCreate/checkNameIsExist.do", {
					biaoDuanName : $("#biaoDuanName").val()
				}, function(data, textStatus) {
					if (data.success != '1') {
						flg = false;
					} else {
						flg = true;
					}
				});
				return flg;
				},
				message : '标段名称已经存在'
				}
			});
});
