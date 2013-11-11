var falgsate = null;
/**
 * 打开项目详细信息显示层
 * @param width
 * @param height
 * @param datapreId
 */
function openDetailProjectInfo(width, height,datapreId) {
	$("#detailProjectId").val(datapreId);
	$('#detailDiv').tabs({
		border:false, 
		onSelect:function(title){ 
			if (title == '预审信息'){
				alert($("#detailProjectId").val());
			}
		} 
	});
	$("#projectDetailDiv").show();
	$('#projectDetailDiv').window({
		width : width,
		height : height,
		modal : true,
		collapsible : false,
		minimizable : false,
		title : '项目详细信息',
		iconCls : 'icon-edit',
		draggable : false
	});
}

// 打开方法
function openpro() {
	$('#projectDetailDiv').window('open');
}

// 关闭方法
function closepro() {
	$('#projectDetailDiv').window('close');
}

/**
 * 获取预审详细信息
 * @param projectId
 */
function getDataPreInfo(projectId) {
	$.post($("#coutextPath").val() + "/common/project/getDataPreInfo.do", {
		projectId : projectId
	}, function(data, textStatus) {
		
	});
}



