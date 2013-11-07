<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>审计状态管理</title>
		<jsp:include page="../common/common.jsp"></jsp:include>

	</head>
	<body>
		<div id="mainDiv" style="width: 100%; margin: 5px;">
			<div id="button-bar" style="margin: 5px;">
				<c:forEach items="${conmap}" var="con">
					<c:if test="${con != 'input.do'}">
						<input type="hidden" id="input" value="1">
					</c:if>
					<c:if test="${con=='find.do'}">
						<input type="hidden" id="find" value="find">
					</c:if>
					<c:if test="${con=='add.do'}">
						<a href="javascript:void(0);" id="add" iconCls="icon-add"
							class="easyui-linkbutton" >增加</a>
					</c:if>
					<c:if test="${con=='update.do'}">
						<a href="javascript:void(0);" id="update" iconCls="icon-edit"
							class="easyui-linkbutton" >编辑</a>
					</c:if>
					<c:if test="${con=='delete.do'}">
						<a href="javascript:void(0);" id="delete" iconCls="icon-remove"
							class="easyui-linkbutton" >删除</a>
					</c:if>
					<c:if test="${con=='destroy.do'}">
						<a href="javascript:void(0);" id="destroy" iconCls="icon-cancel"
							class="easyui-linkbutton">销毁</a>
					</c:if>
				</c:forEach>
			</div>
			<div id="p2" class="easyui-panel" style="background: #fafafa;"
				collapsible="true" title="审计状态管理" iconCls="icon-tip">
				<table id="mytable" class="easyui-datagrid">
				</table>
			</div>
			<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
			<input type="hidden" id="frameId" value="${id}">
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
	</body>
</html>
