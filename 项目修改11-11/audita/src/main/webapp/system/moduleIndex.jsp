<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/default/easyui.css"
			type="text/css"></link>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/demo.css" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/icon.css" type="text/css"></link>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/JQuery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/moduleIndex.js"></script>
		<title>菜单管理</title>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
		<script type="text/javascript">
	//模糊查询
	function doSearch(value) {
		$query = {
			"moduleName" : value
		};
		var options = $("#mytable").datagrid("options");
		options.queryParams = $query;
		reloadGrid();
	};
</script>
	</head>
	<body>
		<div style="width: 99%; margin: 5px;">
			<div id="button-bar" style="margin-left: 100px; margin: 5px;">
				<div style="float: left;">
					<c:forEach items="${menuMap}" var="function">
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
								class="easyui-linkbutton" id="destroy" onclick="destroy()">销毁</a>
						</c:if>
					</c:forEach>
				</div>
				<c:forEach items="${menuMap}" var="function">
					<c:if test="${function == 'find.do'}">
						<div style="margin-left: 150px;">
							<input class="easyui-searchbox" id="moduleName" name="moduleName"
								prompt='输入模块名称' searcher='doSearch' menu="#mm" style="width: 200px"></input>
						</div>
					</c:if>
				</c:forEach>
				<div id="mm" style="width:120px">
				    <div name="all">模块名称</div>  
				</div>  
			</div>
			<div>
				<table id="mytable" class="easyui-datagrid"></table>
			</div>
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
