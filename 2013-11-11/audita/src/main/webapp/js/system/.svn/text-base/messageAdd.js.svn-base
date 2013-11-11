$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#messagecontent").val(top.textTrim($("#messagecontent").val()));
	//得到当前时间
	var date = new Date();
	var year = date.getFullYear();
	var month = (date.getMonth() + 1);
	var day = date.getDate();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	var time= year + "-" + month + "-" + day;
	$("#messagetime").val(time);
		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/system/message/add.do',
					success : function(data) {
					 var data = eval("(" + data + ")");
						if (data.success=="success") {
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
});

function clickuser(){
	var username="";
	var userid=$("#selectuser").val();
	 var user = document.getElementById("selectuser");
     for(i=0;i<user.length;i++){
         if(user[i].selected==true){
        	 username+= user[i].text+",";
         }
     }
     username=username.substring(0,username.length-1);
     $("#acceptuser").val(userid);
     $("#username").val(username);
}

