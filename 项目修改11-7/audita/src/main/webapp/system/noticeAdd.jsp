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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/noticeAdd.js"></script>
		<title>添加公告管理</title>
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
		<div id="mainDiv" style="margin:3px;width: 98%;">
		   <div id="p1" class="easyui-panel" style="background: #fafafa;" iconCls="icon-edit" collapsible="true"  title="公告管理">
			<div style="margin:1px;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save">发布公告</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
				<table class="form" style="width: 99%" cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
						     <input type="hidden" id="frameid" value="${noticemoduelid}">
							<td align="right" class="label" style="width:10%;">
								公告标题：
							</td>
							<td colspan="3">
								<input class="easyui-validatebox" required="true"   missingMessage="公告标题不能为空" style="width: 600px;" id="title" name="title">
							</td>
						</tr>
						<tr>
							<td class="label" align="right"  style="width:10%;">
								公告内容：
							</td>
							<td colspan="3">
								<textarea id="content" class="text" style="width: 100%;"
										rows="10" name="content">
								</textarea>
							</td>
							
						</tr>
						<tr>
							<td class="label" align="right"  style="width:10%;">
								公告日期：
							</td>
							<td colspan="3">
								<input style="width: 200px;" type="text" readonly="readonly"
								id="createtime" name="createtime"  >
							</td>
						</tr>
						
					</tbody>
				</table>
			</div>
			</div>
			</form>
	</body>
</html>