function del(url, mytable) {
	var row;
	if (mytable != null) {
		row = $('#'+mytable).datagrid('getSelected');
	} else {
		row = $('#mytable').datagrid('getSelected');
	}
	if (row != null) {
		if (row.state == '2') {
			$.messager.show({
				title : $("#title").val(),
				msg : $("#alreadyDelete").val()
			});
			return;
		}
		$.post($("#coutextPath").val() + url, {
			id : row.id,
			state : '2'
		}, function(data, textStatus) {
			top.$.messager.show({
				title : $("#title").val(),
				msg : data.msg
			});
			reloadGrid();
		});
	} else {
		top.$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

function destroy(url,mytable) {
	var row;
	if (mytable != null && mytable != "") {
		row = $('#'+mytable).datagrid('getSelected');
	} else {
		row = $('#mytable').datagrid('getSelected');
	}
	if (row != null) {
		if (confirm($("#isDelete").val())) {
			$.post($("#coutextPath").val() + url, {
				id : row.id
			}, function(data, textStatus) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : data.msg
				});
				reloadGrid();
			});
		}
	} else {
		top.$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

function update(name, url) {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		top.openModule($("#id").val(), name, url, null, 'update', row.id);
	} else {
		top.$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

