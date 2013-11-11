<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>季度完成情况查询</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/search/quarterCompleteProjectSearch.js"></script>
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
					style="background: #fafafa; height: 120px; margin-bottom: 5px;"
					collapsible="true" title="查询条件" iconCls="icon-search">
					<form id="from" name="from">
						<div id="center" style="margin-top: 10px;" align="center">
							<table class="form" cellspacing="0" width="99%">
								<tbody>
									<tr>
										<td align="right" width="40%">
											审计报告时间：
										</td>
										<td>
											<input type="text" class="easyui-datebox"
												style="width: 200px;" id="auditStartTime"
												name="auditStartTime">~
											<input type="text" class="easyui-datebox"
												style="width: 200px;" readonly="readonly"
												name="auditEndTime" id="auditEndTime">
										</td>
									</tr>
									<tr>
										<td align="center" colspan="2">
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
					collapsible="true" title="季度完成情况查询" iconCls="icon-tip">
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
						<a href="javascript:void(0);" id="excelallfinishpro"
									iconCls="icon-print" class="easyui-linkbutton">导出审计项目</a>	
						
						
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
