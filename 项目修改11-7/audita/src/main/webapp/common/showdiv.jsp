<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/showdiv.js"></script>
	</head>
	<body>
	 
		<div id="win" style="display: none;"> 
		   <table  cellspacing="0" width="99%" align="center" id="searchtable">
				
			</table>
            <div style="margin-top: 5px; margin-left: 10px;"><table id="mygrid" ></table></div>
        </div>  
        	<input type="hidden" id="root" value="<%=request.getContextPath()%>">
	</body>
</html>
