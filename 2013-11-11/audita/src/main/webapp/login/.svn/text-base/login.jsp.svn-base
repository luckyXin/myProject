<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=request.getContextPath() %>/css/login/css/login.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/default/easyui.css"
			type="text/css"></link>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/icon.css" type="text/css"></link>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/login.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/commonAlert.js">
</script>
		<title>登陆界面</title>
		<style type="text/css">
body {
	margin: 0px auto;
	font-size: 12px;
	color: #666666;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	width: 1000px;
	height: 610px;
}

.center {
	background-image: url(/audita/css/login/img/web_login.jpg);
	background-position: center top;
	background-repeat: no-repeat;
	padding-top: 355px;
	padding-left: 725px;
	height: 263px;
}
</style>
	</head>
	<body onload="messageLoginCheck()">
		<input type="hidden" id="contextPath" value="<%=request.getContextPath() %>">
		<input type="hidden" id="messgge" value="${msg}">
		<div class="center">
			<form id="login" method="post" action="<%=request.getContextPath() %>/login.do">
				<table width="230" border="0" cellpadding="4px" cellspacing="0">
					<tr>
						<td width="105" height="25" style="text-align: right;">
							<spring:message code="login.title.userName"/>：
						</td>
						<td width="170" height="25" align="left">
							<input id="userAccount" name="userAccount" type="text" />
						</td>
					</tr>
					<tr>
						<td width="105" height="25" style="text-align: right;">
							<spring:message code="login.title.password"/>：
						</td>
						<td width="170" height="25">
							<input id="password" name="password" type="password" />
						</td>
					</tr>
					<tr>
						<td height="50" style="padding-right: 5px;" colspan="2">
							<table height="27" border="0" cellpadding="10" cellspacing="0" id="key">
								<tr>
									<td width="60" height="30" valign="middle">
										<img id="loginButton" src="<%=request.getContextPath() %>/css/login/img/button.png"
											width="76" height="29" style="cursor: pointer;" />
									</td>
									<td style="padding-left: 20px;">
										<img id="restButton" src="<%=request.getContextPath() %>/css/login/img/reset.png"
											width="76" height="29" style="cursor: pointer;" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>