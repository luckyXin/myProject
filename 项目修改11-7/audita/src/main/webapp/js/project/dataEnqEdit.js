$(document).ready(function() {
	$("#savedata").click(function(){
			var frameid=$("#frameid").val();
			var url = $("#coutextPath").val() + '/project/dataenquiry/add.do'
			if ($("#id").val() != "" && undefined !=$("#id").val()){
				url = $("#coutextPath").val() + '/project/dataenquiry/update.do'
			}
			$("#form").ajaxSubmit({
				url : url,
				success : function(data) {
				var data = eval("(" + data + ")");
					if (data.success == "success") {
						top.showMsg(data.msg);
						top.reloadModule(frameid+"add");
						top.closeModule(moduleName, parentModuleName);
					} else  {
						top.showMsg(data.msg);
						top.reloadModule($("#frameid").val());
					}
				}
			});
	});
	
});

//下载文件
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
}

