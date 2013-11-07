<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/search/proTempleImportAdd.js"></script>
		<title>模板添加</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

.cancel a {
	background-color: red;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; height: 500px; margin-bottom: 5px;"
				collapsible="true" title="模板录入信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
				</div>
				<input type="hidden" id="frameid" value="${id}">
				<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
				<input type="hidden" id="biaoDuanId" name="biaoDuanId" value="${biaoDuanId}" />
				<table class="form" style="width: 99%" 
					cellpadding="0" cellspacing="0">
					<tr>
						<td class="label">
							模板文件：
						</td>
						<td colspan="3">
							<input type="file" id="fileurl" name="fileurl" style="width: 350px;">
						</td>
						
					</tr>
			</table>
			</div>
		</form>
	</body>
</html>