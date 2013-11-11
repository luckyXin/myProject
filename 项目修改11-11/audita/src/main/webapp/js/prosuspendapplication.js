var falgsate = null;

function openproiv(width, height,datapreId) {
	$("#winpro").show();
	$('#winpro').window({
		width : width,
		height : height,
		modal : true,
		collapsible : false,
		minimizable : false,
		title : '项目暂停审计申请表',
		iconCls : 'icon-edit',
		draggable : false
	});
	$("#zhiliaoid").val(datapreId);
	$("#suspendRemark").val("");
	 $.ajax( {
			url :  $("#coutextPath").val() + '/common/project/finddata.do',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'id='+datapreId,
			success : function(data) {
				$("#project").val(data.projectName);
				$("#ownnerName").val(data.ownerName);
				$("#constructName").val(data.constructId);
			}
		   });
	
	
	
}
// 打开方法
function openpro() {
	$('#winpro').window('open');
}
// 关闭方法
function closepro() {
	$('#winpro').window('close');
}
//保存
function save(){
	$("#proform").ajaxSubmit({
		url : $("#coutextPath").val() + '/common/project/addprosup.do',
		success : function(data) {
			if (data.success=="success") {
				top.showMsg(data.msg);
			} else  {
				top.showMsg(data.msg);
				
				
			}
			closepro();
		}
	});
}



