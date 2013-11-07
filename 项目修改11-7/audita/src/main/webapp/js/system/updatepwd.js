$(document).ready(function() {
		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				var oldpwd=$("#oldpwd").val();
				//判断输入原密码是否正确
				$.ajax( {
					url :  $("#coutextPath").val() + '/common/system/updateuser/find.do',
					type : "POST",
					dataType : "json",
					async:false,
					data : 'oldpwd='+oldpwd,
					success : function(data) {
				         if(data.yes!="yes"){
				        	 $.messager.show( {
									title : "提示",
									msg : "旧密码错误请重新输入"
								});
				        	 return false;
				         }else{
				        	    //得到新密码和重复新密码
								var newpwd=$("#newpwd").val();
								var renewpwd=$("#renewpwd").val();
								if(newpwd!=renewpwd){
									 $.messager.show( {
											title : "提示",
											msg : "新密码两次输入不一样"
										});
						        	 return false;
								}
								$("#form").ajaxSubmit( {
									url : $("#coutextPath").val() + '/common/system/updateuser/update.do',
									success : function(data) {
											top.showMsg(data.msg);
									}
								});
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


