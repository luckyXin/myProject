function filedown(url){
	 window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	 return false;
}

