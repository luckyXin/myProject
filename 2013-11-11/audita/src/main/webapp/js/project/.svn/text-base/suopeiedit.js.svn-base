$(document).ready(function() {
	$("#save").click(function(){
		if ($('#form').form('validate')) {
			var frameid=$("#frameid").val();
			var url = $("#coutextPath").val() + '/project/claimindemnity/add.do'
			if ($("#id").val() != ""){
				url = $("#coutextPath").val() + '/project/claimindemnity/update.do'
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
		}
	});

//金额
	$('#constructSentMoney').numberbox({   
		   precision:2   
	});
	$('#indemnityMoney').numberbox({   
		   precision:2   
	}); 
	$('#auditMoney').numberbox({   
		   precision:2   
	}); 
	$('#ownerReadyMoney').numberbox({   
		   precision:2   
	}); 
});


