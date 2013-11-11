var oldRemark = null;
var oldSubmitState = null;
var oldPassState = null;
var baseheight=400;
$(document).ready(function() {
			//更改时间格式
	$("#sj1").text($("#sj1").text().substr(0,10))
			$("#intermediaryName").click(function() {
				openshowdiv(4, "中介机构选择", 580, 350,270,null);
			});
			// 保存审批信息
			$("#save").click(
					function() {
						var url = $("#coutextPath").val()+ '/common/project/addDataPreInter.do';
						$("#form").ajaxSubmit(
								{
									url : url,
									success : function(data) {
										if (null == data || "" == data) {
											$.messager.show({
												title : "提示",
												msg : "系统异常"
											});
										} else {
											if (data.success == "success") {
												top.showMsg(data.msg);
												top.reloadModule($("#frameid").val());
												top.closeModule(moduleName,parentModuleName);
											} else {
												top.showMsg(data.msg);
											}
										}
									}
								});
					});
});
function returnIntermediary(rowData) {
	$("#intermediary").val(rowData.id);
	$("#intermediaryName").val(rowData.intermediaryname);
	close();
}
