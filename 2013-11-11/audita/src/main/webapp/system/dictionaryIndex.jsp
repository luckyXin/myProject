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
		<title>字典管理</title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/default/easyui.css"
			type="text/css"></link>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/demo.css" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/icon.css" type="text/css"></link>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/JQuery.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.dataGrid.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/dftree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/dftree.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/dictionaryIndex.js"></script>
		<style type="text/css">
			body {
				margin-left: 0px;
				margin-top: 0px;
				padding: 0px;
			}
			
		   .content {
				display: block;
				float: left;
				overflow-x: visible;
				overflow-y: visible;
				width: 100%;
				margin: 0;
				padding: 0;
		   }
		
		   #treeDiv {
				margin: 0 2px 0 0;
				padding: 0;
				width: 200px;
				height: 100%;
				display: block;
				float: left;
				border: solid 1px #cccccc;
				overflow: auto;
		   }
		
		    .content #treeContainer {
				margin: 0;
				padding: 0;
				height: 100%;
				border: none;
				overflow: visible;
		  }
        </style>
	</head>
	<body>
	 <div style="width: 100%;margin: 5px;">
	    <input type="hidden" id="root" value="<%=request.getContextPath()%>">
	    <input type="hidden" id="frameid" value="${sessionScope.moduelid}">
	    <input type="hidden" id="noPower" value="<spring:message code='error.message.noPower'/>">
		<input type="hidden" id="isDelete" value="<spring:message code='prompt.delete.isDelete'/>">
		<input type="hidden" id="noSelect" value="<spring:message code='error.message.noSelect'/>">
		<input type="hidden" id="noSelectdel" value="<spring:message code='error.message.noSelectdel'/>">
		<input type="hidden" id="title" value="<spring:message code='prompt.title.title'/>">
		<div id="button-bar" style="margin:5px;">
		<c:forEach items="${dicmap}" var="dic">
		          <c:if test="${dic != 'input.do'}">
				       <input type="hidden" id="input" value="1">
				    </c:if>
			     <c:if test="${dic=='find.do'}">
		          <input type="hidden" id="isfind" value="find">
		        </c:if>
			      <c:if test="${dic=='add.do'}">
		          <a href="javascript:void(0);" id="add" iconCls="icon-add" class="easyui-linkbutton"
					 onclick="add()">增加</a>
		        </c:if>
		        <c:if test="${dic=='update.do'}">
		        <a href="javascript:void(0);" id="update" iconCls="icon-edit"  class="easyui-linkbutton"
					 onclick="edit()">编辑</a>
		        </c:if>
		        <c:if test="${dic=='delete.do'}">
		          <a href="javascript:void(0);" id="delete" iconCls="icon-remove" class="easyui-linkbutton"
					 onclick="del()">删除</a>
		        </c:if>
		        <!--<c:if test="${dic=='destroy.do'}">
		        <a href="javascript:void(0);" id="destroy" iconCls="icon-cancel" class="easyui-linkbutton"
					 onclick="destroy()">销毁</a>
		        </c:if>
		 --></c:forEach>	
		</div>
		<div class="content" id="content">
			<div class="treeDiv" style="overflow: auto;float: left;">
                <ul id="treeDiv" ></ul>     
			</div>
			<div id="treeContainer" style="float: left;">
				<table id="grid"></table>
			</div>
		</div>
		</div>
	</body>
</html>
