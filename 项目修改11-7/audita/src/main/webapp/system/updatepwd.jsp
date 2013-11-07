<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
				<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/updatepwd.js"></script>
		<title>添加用户管理</title>
		<style type="text/css">
		body {
			margin: 0px;
			padding: 0px;
			width: 100%;
			height: 100%;
		}
		</style>
	</head>
	<body>
	<form id="form" method="post">
	    <input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
	    <div id="mainDiv" style="margin: 5px;width: 98%;">
	    <div id="p1" class="easyui-panel" style="background: #fafafa;" iconCls="icon-edit" collapsible="true"  title="用户密码修改">
			<div style="margin:1px;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
				<table class="form" cellpadding="0" cellspacing="0">
					<tbody >
						<tr>
							<td align="right" class="label">
								旧密码：
							</td>
							<td colspan="3">
								<input type="password" class="easyui-validatebox" required="true"    missingMessage="旧密码不能为空" style="width: 200px;" id="oldpwd" name="oldpwd">
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								新密码：
							</td>
							<td colspan="3">
								<input type="password"  class="easyui-validatebox" required="true"  missingMessage="新密码不能为空" style="width: 200px;" id="newpwd" name="newpwd">
							</td>
							
						</tr>
						<tr>
							<td align="right" class="label">
								重复新密码：
							</td>
							<td colspan="3">
								<input type="password" class="easyui-validatebox" required="true"  missingMessage="重复新密码不能为空" style="width: 200px;" id="renewpwd" name="renewpwd">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			</form>
	</body>
</html>