function messageLoginCheck() {
	var msg = $('#messgge').val();
	if (msg != null && msg != "") {
		$.messager.show({
			title:'消息',
			msg:msg,
			timeout:5000,
			showType:'slide'
		});
	}
}
