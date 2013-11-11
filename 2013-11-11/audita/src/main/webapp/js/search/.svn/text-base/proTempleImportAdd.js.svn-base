$(document).ready(function() {
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var frameid=$("#frameid").val();
			var url = $("#coutextPath").val() + '/search/myselfTractProject/addExcelTemp.do'
			$("#form").ajaxSubmit({
				url : url,
				success : function(data) {
				var data = eval("(" + data + ")");
					if (data.isSuccess == 1) {
						top.showMsg(data.msg);
						top.reloadModule(frameid+"templateimport");
						top.closeModule(moduleName, parentModuleName);
					} else  {
						top.showMsg(data.msg);
					}
				}
			});
		}
	}); 
});


