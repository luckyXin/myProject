$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#conferencontent").val(top.textTrim($("#conferencontent").val()));
	//议会日期
		$('#conferencetime').datebox( {
			disabled : false,
			formatter : formatDate
		});

		
		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/project/proConference/add.do',
					success : function(data) {
						if (data.success) {
							top.showMsg(data.msg);
							top.reloadModule(frameid+"lookconference");
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
	});

//格式化时间
function formatDate(v) {
	if (v instanceof Date) {
		var y = v.getFullYear();
		var m = v.getMonth() + 1;
		var d = v.getDate();
		var h = v.getHours();
		var i = v.getMinutes();
		var s = v.getSeconds();
		var ms = v.getMilliseconds();

		if (ms > 0) {
			return y + '-' + m + '-' + d + ' ' + h + ":" + s + ":" + i;
		}
		if (h > 0 || m > 0 || d > 0) {
			if(m<10){
				m="0"+m;
			}
			if(d<10){
				d="0"+d;
			}
			return y + '-' + m + '-' + d;
		}
	}
	return '';
}

