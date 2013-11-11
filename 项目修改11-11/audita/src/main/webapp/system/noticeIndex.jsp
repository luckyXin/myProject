<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>公告管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/noticeIndex.js"></script>
		<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			padding: 0px;
		}
		</style>
	</head>
	<body>
	   <div style="width: 100%;margin: 3px;">
	     <input type="hidden" id="root" value="<%=request.getContextPath()%>">
	     <input type="hidden" id="frameid" value="${noticemoduelid}">
	     <input type="hidden" id="noPower" value="<spring:message code='error.message.noPower'/>">
		<input type="hidden" id="isDelete" value="<spring:message code='prompt.delete.isDelete'/>">
		<input type="hidden" id="noSelect" value="<spring:message code='error.message.noSelect'/>">
		<input type="hidden" id="noSelectdel" value="<spring:message code='error.message.noSelectdel'/>">
		<input type="hidden" id="title" value="<spring:message code='prompt.title.title'/>">
		<div id="p1" class="easyui-panel"
				style="background: #fafafa; " collapsible="true"
		   title="公告管理" iconCls="icon-tip">
		<div id="button-bar" style="margin:1px;">
		   <c:forEach items="${noticemap}" var="notice">
		           <c:if test="${notice != 'input.do'}">
				       <input type="hidden" id="input" value="1">
				    </c:if>
			     <c:if test="${notice == 'find.do'}">
		          <input type="hidden" id="isfind" value="find">
		           <a href="javascript:void(0);" id="look" iconCls="icon-edit"  class="easyui-linkbutton"
					 onclick="look()">查看公告</a>
		        </c:if>
			      <c:if test="${notice == 'add.do'}">
		          <a href="javascript:void(0);" id="add" iconCls="icon-add" class="easyui-linkbutton"
					 onclick="add()">发布</a>
		        </c:if>
		        <c:if test="${notice == 'update.do'}">
		        <a href="javascript:void(0);" id="update" iconCls="icon-edit"  class="easyui-linkbutton"
					 onclick="edit()">编辑</a>
		        </c:if>
		        <c:if test="${notice == 'delete.do'}">
		          <a href="javascript:void(0);" id="delete" iconCls="icon-remove" class="easyui-linkbutton"
					 onclick="del()">删除</a>
		        </c:if>
		     </c:forEach>	
		</div>
		<table id="grid" style="background-color:#e0edfe"></table>
		</div>
		</div>
	</body>
</html>
