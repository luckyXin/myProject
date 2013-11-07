<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/packProjectArrangeEdit.js"></script>
		<title>打包项目安排编辑</title>
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
		<form id="form" method="post">
			<div id="mainDiv">
				<div id="p2" class="easyui-panel"
					style="background: #fafafa; height: 350px;" collapsible="true"
					title="打包项目录入" iconCls="icon-tip">
					<div style="margin: 5px;">
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<c:if test="${processType=='add'}">
								<tr>
									<td class="label" style="color: red;">
										*项目包名称：
									</td>
									<td style="width: 90%;" colspan="3">
										<input class="easyui-validatebox" style="width: 200px;"
											id="projectPackName" name="projectPackName" required="true">
									</td>
								</tr>
								<tr>
									<td class="label">
										*项目业主：
									</td>
									<td colspan="3">
										<input type="hidden" name="ownerId" id="ownerId">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="ownerName" name="ownerName">
									</td>
								</tr>
								<tr>
									<td class="label">
										送审金额(万)：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="sentAmount" name="sentAmount"
											value="0">
									</td>
								</tr>
								<tr>
									<td class="label" style="color: red;">
										*项目审计安排时间：
									</td>
									<td colspan="3">
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="projectArrangeTime"
											name="projectArrangeTime" required="true">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目安排备注：
									</td>
									<td colspan="3">
										<textarea id="projectArrangeRemark" class="text"
											style="width: 50%;" rows="4" name="projectArrangeRemark">
								</textarea>
									</td>
								</tr>
								<tr>
									<td class="label" style="color: red;">
										主审人员：
									</td>
									<td>
										<input type="hidden" id="mainAuditId" name="mainAuditId">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="mainAuditName" name="mainAuditName"
											required="true">
									</td>
									<td class="label" style="color: red;">
										*中介机构：
									</td>
									<td>
										<input type="hidden" id="intermediaryId" name="intermediaryId">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="intermediaryName"
											name="intermediaryName" required="true">
									</td>
								</tr>
								<tr>
									<td class="label" style="color: red;">
										*复核工程师：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="governmentEmployee"
											name="governmentEmployee" required="true">
										<font color="red">*最多五个复核工程师 </font>
										<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
									</td>
								</tr>
								<tr>
									<td class="label" style="color: red;">
										*资料发送中介机构时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="intermediarySendTime"
											name="intermediarySendTime" required="true">
									</td>
									<td class="label">
										中介机构提交初审结果时间：
									</td>
									<td>
										<input readonly="readonly" class="easyui-datebox"
											disabled="true" style="width: 200px;"
											id="intermediaryAuditTime" name="intermediaryAuditTime">
									</td>
								</tr>
								<tr>
									<td class="label">
										状态：
									</td>
									<td colspan="3">
										<select name="state" style="width: 200px;">
											<option value="0">
												启用
											</option>
											<option value="1">
												禁用
											</option>
											<option value="2">
												删除
											</option>
										</select>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${id}">
			<input type="hidden" id="processType" value="${processType}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>