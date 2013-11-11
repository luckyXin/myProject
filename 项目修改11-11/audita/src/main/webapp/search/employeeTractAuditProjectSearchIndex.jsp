<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>所有跟踪审计项目查询</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/search/EmployeeTractAuditProjectSearchIndex.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 100%; margin: 5px;">
			<div id="subDiv" style="margin-bottom: 5px;">
				<div id="p1" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="查询条件" iconCls="icon-search">
					<form id="from" name="from">
						<div id="center" style="margin-top: 10px;" align="center">
							<table class="form" cellspacing="0" width="99%">
								<tbody>
									<tr>
										<td align="right" width="20%">
											项目名称：
										</td>
										<td>
											<input type="text" id="projectName" name="projectName"
												style="width: 200px;">
										</td>
										<td align="right" width="20%">
											项目业主：
										</td>
										<td>
											<input type="text" style="width: 200px;" readonly="readonly"
												name="ownerName" id="ownerName">
											<input type="hidden" id="ownerId">
										</td>
									</tr>
									<tr>
									    <td align="right" width="20%">
											招标类型：
										</td>
										<td>
											<select id="projectType" name="projectType"
												style="width: 200px;" class="easyui-combobox">
												<option value="" selected="selected">全部</option>
												<option value="0">招标</option>
												<option value="1">EPC</option>
												<option value="2">BT</option>
											</select>
										</td>
										<td align="right" width="20%">
											实际开工时间：
										</td>
										<td>
											<input type="text" class="easyui-datebox"
												style="width: 100px;" readonly="readonly"
												name="reallyStartTime" id="reallyStartTime">
											~
											<input type="text" class="easyui-datebox"
												style="width: 100px;" readonly="readonly"
												name="reallyEndTime" id="reallyEndTime">
										</td>
									</tr>
									<tr>
										<td align="right" width="20%">
											复核工程师名称：
										</td>
										<td>
											<input type="text" style="width: 200px;" readonly="readonly"
												name="employeeName" id="employeeName">
											<input type="hidden" id="employeeId">
										</td>
										<td align="right" width="20%">
											审计身份：
										</td>
										<td>
											<select name="auditIdentity" id="auditIdentity"
												style="width: 200px;" class="easyui-combobox">
												<option value="0" selected="selected">全部</option>
												<option value="1">主审</option>
												<option value="2">复审</option>
											</select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="4">
												<a href="javascript:void(0);" id="search"
													iconCls="icon-search" class="easyui-linkbutton">查询</a>
												<a href="javascript:void(0);" id="reset"
													iconCls="icon-cancel" class="easyui-linkbutton">重置</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</div>
				<div id="p2" class="easyui-panel" style="background: #fafafa;"
					collapsible="true" title="跟踪审计项目列表" iconCls="icon-tip">
					<div id="button-bar" style="margin: 5px;">
						<c:forEach items="${mapFunction}" var="con">
							<c:if test="${con != 'input.do'}">
								<input type="hidden" id="input" value="1">
							</c:if>
							<c:if test="${con=='update.do'}">
								<a href="javascript:void(0);" id="audit"
									iconCls="icon-mini-edit" class="easyui-linkbutton">查看明细</a>
							</c:if>
							<c:if test="${con=='find.do'}">
								<input type="hidden" id="find" value="find">
							</c:if>
						</c:forEach>
					</div>
					<table id="grid" class="easyui-datagrid"></table>
				</div>
				<input type="hidden" id="coutextPath"
					value="<%=request.getContextPath()%>">
				<input type="hidden" id="frameId" value="${moduelId}">
				<input type="hidden" id="noPower"
					value="<spring:message code='error.message.noPower'/>">
				<input type="hidden" id="isDelete"
					value="<spring:message code='prompt.delete.isDelete'/>">
				<input type="hidden" id="noSelect"
					value="<spring:message code='error.message.noSelect'/>">
				<input type="hidden" id="title"
					value="<spring:message code='prompt.title.title'/>">
				<input type="hidden" id="alreadyDelete"
					value="<spring:message code='error.message.alreadyDelete'/>">
			</div>
			<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>
