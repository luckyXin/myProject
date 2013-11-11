<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>审计费用查询管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
	    <script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/proAuditMoenyIndex.js"></script>
		<style type="text/css">
			body {
				margin-left: 0px;
				margin-top: 0px;
				padding: 0px;
			}
			
			tbody td {
				border: 1px solid #ccc;
			}
		</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 100%; margin: 5px;">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; height: 95px;display: none;" collapsible="true"
				title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 10px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="40%">
										项目名称：
									</td>
									<td style="width: 200px;" >
									   <input type="text" style="width: 200px;" id="projectName">
									</td>
									<td align="left">
										<a href="javascript:void(0);" id="search" iconCls="icon-search" class="easyui-linkbutton">查询</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			 <div id="p2" class="easyui-panel"  style="background:#fafafa;display: none;"   collapsible="true" >  
			 	<div id="button-bar" style="margin: 5px;">
				<c:forEach items="${auditmoneymap}" var="audit">
					<c:if test="${audit != 'input.do'}">
						<input type="hidden" id="input" value="1">
					</c:if>
					<c:if test="${audit=='find.do'}">
						<input type="hidden" id="find" value="find">
					</c:if>
					</c:forEach>
			</div>
                         <table id="grid" ></table>
            </div> 
			<input type="hidden" id="path" value="<%=request.getContextPath()%>">
			<input type="hidden" id="frameId" value="${requestScope.proauditmoduelId}">
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
		<input type="hidden" id="noSelectdel" value="<spring:message code='error.message.noSelectdel'/>">
		</div>
	</body>
</html>
