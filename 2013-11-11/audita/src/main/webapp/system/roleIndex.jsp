<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/roleIndex.js"></script>
		<title>角色管理</title>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
		<script type="text/javascript">
	
</script>
	</head>
	<body>
		<div style="width: 99%; margin: 1px;">
			<div id="button-bar" style="margin: 1px;">
				<c:forEach items="${roleMap}" var="function">
					<c:if test="${function != 'input.do'}">
						<input type="hidden" id="input" value="1">
					</c:if>
					<c:if test="${function == 'find.do'}">
						<input type="hidden" id="find" value="1">
					</c:if>
					<c:if test="${function == 'add.do'}">
						<a href="javascript:void(0);" iconCls="icon-add"
							class="easyui-linkbutton" id="add" onclick="add()">增加</a>
					</c:if>
					<c:if test="${function == 'update.do'}">
						<a href="javascript:void(0);" iconCls="icon-edit"
							class="easyui-linkbutton" id="update" onclick="update()">编辑</a>
					</c:if>
					<c:if test="${function == 'delete.do'}">
						<a href="javascript:void(0);" id="delete" iconCls="icon-remove"
							class="easyui-linkbutton">删除</a>
					</c:if>
					<c:if test="${function == 'destroy.do'}">
						<a href="javascript:void(0);" iconCls="icon-cancel"
							class="easyui-linkbutton" id="destroy">注销</a>
					</c:if>
				</c:forEach>
			</div>
			<table id="mytable" class="easyui-datagrid"></table>
			<input type="hidden" id="id" value="${id}">
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="noPower"
				value="<spring:message code='error.message.noPower'/>">
			<input type="hidden" id="isDelete"
				value="<spring:message code='prompt.delete.isDelete'/>">
			<input type="hidden" id="noSelect"
				value="<spring:message code='error.message.noSelect'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</div>
	</body>
</html>
