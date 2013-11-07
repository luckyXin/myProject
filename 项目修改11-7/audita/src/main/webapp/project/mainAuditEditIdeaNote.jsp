<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>生成出征求意见稿</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		</script>
		<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#createIdeaNote").click(function() {
							$("#form").submit();
						});

						// 保存审批信息
						$("#save").click(function() {
											$("#form").ajaxSubmit({url : $("#coutextPath").val()+ '/project/MainAudit/updateIdeaNoteInfo.do',
																success : function(data) {
																if (null == data || "" == data) {
																		$.messager.show({
																					title : "提示",
																					msg : "系统异常"
																				});
																	} else {
																	 var result = eval("(" + data + ")");
																		if (result.id != null) {
																			top.showMsg(result.msg);
																			top.reloadModule($("#fid").val());
																			top.closeModule(moduleName,parentModuleName);
																		} else {
																			top.showMsg(result.msg);
																		}
																	}
																}
															});
										});

						if ($("#budgetInfoFileState").text()) {
							$("#budgetInfoFile").hide();
						}
						if ($("#addProjectApprovalFileState").text()) {
							$("#addProjectApprovalFile").hide();
						}
						if ($("#financialRAEState").text()) {
							$("#financialRAE").hide();
						}
						$("#deleteFileFinancialRAE").click(function() {
							$("#deleteFileFinancialRAE").hide();
							$("#financialRAEState").hide();
							$("#financialRAE").show();
						});
						$("#deleteFileBudgetInfoFile").click(function() {
							$("#deleteFileBudgetInfoFile").hide();
							$("#budgetInfoFileState").hide();
							$("#budgetInfoFile").show();
						});
						$("#deleteFileAddProjectApprovalFile").click(
								function() {
									$("#addProjectApprovalFileState").hide();
									$("#addProjectApprovalFile").show();
									$("#deleteFileAddProjectApprovalFile")
											.hide();
								});
					});

	function download(url) {
		window.location = encodeURI(encodeURI($("#coutextPath").val()
				+ '/common/project/download.do?url=' + url));
				return false;
	}
</script>
		<style type="text/css">
body {
	margin: 10px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

form .label {
	padding-right: 80px;
}
</style>
	</head>
	<body>
		<form id="form" method="post"
			action="<%=request.getContextPath()%>/project/MainAudit/getIdeaNote.do"
			enctype="multipart/form-data">
			<div id="mainDiv">
				<div id="p1" class="easyui-panel"
					style="background: #fafafa; height: 350px; margin-bottom: 5px;"
					title="项目基本信息" iconCls="icon-redo">
					<table class="form"
						style="width: 99%; border-style: none; margin: 5px;"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="label">
									项目名称：
								</td>
								<td>
									<input type="hidden" id="projectId" name="projectId"
										value="${dataPreBaseInfo.id}">
									<input disabled="disabled" style="width: 200px;"
										required="true" value="${dataPreBaseInfo.projectName}">
								</td>
								<td class="label">
									立项文号：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										required="true" value="${dataPreBaseInfo.projectName}">
								</td>
							</tr>
							<tr>
								<td class="label">
									立项时间：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.projectTime}">
								</td>
								<td class="label">
									批复金额(元)：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.auditMoney}">
								</td>
							</tr>
							<tr>
								<td class="label">
									项目业主：
								</td>
								<td colspan="3">
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.proOwnerName}">
								</td>
							</tr>
							<tr>
								<td class="label">
									项目业主联系人：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.proOwnerLink}">
								</td>
								<td class="label">
									项目业主联系电话：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.proOwnerTelphone}">
								</td>
							</tr>
							<tr>
								<td class="label">
									施工单位：
								</td>
								<td colspan="3">
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.constructName}">
								</td>
							</tr>
							<tr>
								<td class="label">
									施工单位联系人：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.constructLink}">
								</td>
								<td class="label">
									施工单位联系电话：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.constructTelphone}">
								</td>
							</tr>
							<tr>
								<td class="label">
									审计类型：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.auditType}">
								</td>
								<td class="label">
									是否加急：
								</td>
								<td>
									<c:if test="${dataPreBaseInfo.isExpedited == 1}">
										<select disabled="disabled" style="width: 100px;">
											<option>
												是
											</option>
										</select>
									</c:if>
									<c:if test="${dataPreBaseInfo.isExpedited != 1}">
										<select disabled="disabled" style="width: 100px;">
											<option>
												否
											</option>
										</select>
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="label">
									送审时间：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.datapreTime}">
								</td>


								<td class="label">
									送审金额：
								</td>
								<td>
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.sentAmount}">
								</td>
							</tr>
							<tr>
								<td class="label">
									资料预审意见：
								</td>
								<td colspan="3">
									<textarea rows="5" style="width: 50%;" disabled="disabled">
										${dataPreBaseInfo.dataPreOpinion}
									</textarea>
								</td>
							</tr>
							<tr>
								<td class="label">
									送审人员：
								</td>
								<td colspan="3">
									<input disabled="disabled" style="width: 200px;"
										value="${dataPreBaseInfo.dataPreOperate}">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="p2" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="信息录入" iconCls="icon-redo">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form"
						style="width: 99%; border-style: none; margin: 5px;"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="label">
									财务收支取证：
								</td>
								<td>
									<c:if test="${not empty mainAuditTwo.financialRAE}">
										<a href="javascript:download('${mainAuditTwo.financialRAE}')"
											id="financialRAEState">补充立项文件 </a>
										<a id="deleteFileFinancialRAE" class="easyui-linkbutton"
											iconCls="icon-remove">删除文件</a>
									</c:if>
									<input type="file" id="financialRAE" name="financialRAE"
										style="width: 300px;">
								</td>
								<td class="label">
								         财务收支取证时间：
								</td>
								<td>
								   <input type="text" class="easyui-datebox"
										id="financialRAETime" name="financialRAETime"
										style="width: 200px;" value="${mainAuditTwo.financialRAETime}">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${moduelId}">
			<input type="hidden" id="processType" value="${processType}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
	</body>
</html>
