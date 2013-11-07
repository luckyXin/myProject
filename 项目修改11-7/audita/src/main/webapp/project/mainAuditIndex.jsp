<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>主审审核</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/mainAuditIndex.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 99%; margin: 1px;">
				<div id="p1" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 1px;"
					collapsible="true" title="查询条件" iconCls="icon-search">
					<form id="from" name="from">
						<div id="center" style="margin-top: 10px;" align="center">
							<table class="form" cellspacing="0" width="99%">
								<tbody>
									<tr>
										<td align="right" width="20%">&nbsp; 
											项目名称： 
										</td>
										<td align="left">
											<input type="text" style="width: 200px;" id="projectName"
												name="projectName">
										</td>
										<td align="right" width="20%">
											项目业主：
										</td>
										<td align="left">
											<input type="text" style="width: 200px;" readonly="readonly"
												name="ownerName" id="ownerName">
											<input type="hidden" id="ownerId">
										</td>
									</tr>
									<tr>
										<td align="right" width="20%">
											项目安排类型：
										</td>
										<td align="left">
											<select id="arrangeType" style="width: 200px;">
												<option value="0">
													单项目
												</option>
												<option value="1">
													打包项目
												</option>
												<option value="2" selected="selected">
													全部
												</option>
											</select>
										</td>
										<td align="right" width="20%">
											复审状态：
										</td>
										<td align="left">
											<select id="auditType" style="width: 200px;">
												<c:if test="${isAudit == '1'}">
													<option value="0">
														未审批
													</option>
													<option value="1" selected="selected">
														已审批
													</option>
												</c:if>
												<c:if test="${isAudit != '1'}">
													<option value="0" selected="selected">
														未审批
													</option>
													<option value="1">
														已审批
													</option>
												</c:if>
											</select>
										</td>
									</tr>
									<tr>
								     <td align="right" width="20%">
								            项目审计安排时间：
									</td>
									<td align="left">
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="beginTime" name="beginTime">
										  ~
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="endTime" name="endTime">
									</td>
									<td align="right" width="20%">
											操作类型：
										</td>
										<td align="left">
											<input type="radio" class="mainAuditType" id="mainAuditType" name="mainAuditType"
												value="0" >
											生成审计通知书
											<input type="radio" class="mainAuditType" id="mainAuditType" name="mainAuditType"
												value="1">
											财务收支取证信息录入
											<input type="radio"  class="mainAuditType" id="mainAuditType" name="mainAuditType"
												value="2" checked="checked">
											生成审计报告
											<input type="hidden" id="processType" value="2">
										</td>
								</tr>
								<tr>
								    <td align="right" width="20%">
								              限时：
									</td>
									<td align="left">
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="timeLimitStart" name="timeLimitStart">
										  ~
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="timeLimitEnd" name="timeLimitEnd">
									</td>
									<td align="right" width="20%">
								              是否加急：
									</td>
									<td align="left">
									     <select id="isUrgent" style="width: 200px;">
									         <option value="">全部</option>
									         <option value="0">否</option>
									         <option value="1">是</option>
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
					collapsible="true" title="审核项目列表" iconCls="icon-tip">
					<div id="button-bar" style="margin: 1px;">
						<c:forEach items="${commonFunction}" var="con">
							<c:if test="${con != 'input.do'}">
								<input type="hidden" id="input" value="1">
							</c:if>
							<c:if test="${con=='add.do'}">
								<a href="javascript:void(0);" id="audit"
									iconCls="icon-mini-edit" class="easyui-linkbutton">
									<span id="text">录入</span>
									</a>
							</c:if>
							<c:if test="${con=='find.do'}">
								<input type="hidden" id="find" value="find">
							</c:if>
						</c:forEach>
					</div>
					<table id="grid"></table>
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
