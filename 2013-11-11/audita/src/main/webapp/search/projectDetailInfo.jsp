<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/search/projectDetailInfo.js"></script>
	</head>
	<body>
		<div id="projectDetailDiv" style="display: none;">
		   <input type="hidden" id="detailProjectId">
		   <div class="easyui-tabs" id="detailDiv">
		      <div title="预审信息" style="padding:10px"></div>
		      <div title="安排信息" style="padding:10px"></div>
		      <div title="初审信息" style="padding:10px"></div>
		      <div title="复审信息" style="padding:10px"></div>
		      <div title="主审信息" style="padding:10px"></div>
		   </div>
        </div>  
        <input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
	</body>
</html>
