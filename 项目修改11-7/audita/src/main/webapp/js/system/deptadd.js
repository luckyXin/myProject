$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#remark").val(top.textTrim($("#remark").val()));
	//初始化机构信息
	initdepttree();
		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/system/department/add.do',
					success : function(data) {
						if ("success"==data.success) {
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

//初始化机构下拉树形机构
function initdepttree(){
	$.ajax( {
		url : $("#coutextPath").val() + '/system/department/findbypid.do',
		type : "POST",
		success : function(json) {
			var result = eval("(" + json + ")");
			$$.InputTree.New( {
				"m_strNodeValue" : "deprtId",
				"m_strNodeText" : "name",
				"m_jsonData" : result,
				"m_strContainerID" : "pid"
			});
		}
	});
}
