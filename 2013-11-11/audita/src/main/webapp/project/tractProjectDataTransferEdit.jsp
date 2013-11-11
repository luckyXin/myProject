<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
			src="<%=request.getContextPath()%>/js/project/tractProjectDataTransferInfoEdit.js"></script>
		<title>资料移交</title>
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
				style="background: #fafafa; margin-bottom: 5px;"
				collapsible="true" title="资料移交基础信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
					<a href="javascript:void(0);" id="projoinlist" class="easyui-linkbutton" iconCls="icon-save">资料移交</a>
				</div>
				<table class="form" style="width: 99%" id="mytableid"
					cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
							<input type="hidden" id="frameId" value="${id}">
							<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
							<input type="hidden" id="id" name="id" value="${dataTransfer.id}" />
							<td align="right" class="label">
								资料移交时间 ：
							</td>
							<td>
								<c:if test="${empty dataTransfer.createTime}">
									<input class="easyui-datebox" required="true"
										value="${nowTime}" style="width: 200px;" id="createTime"
										name="createTime">
								</c:if>
								<c:if test="${not empty dataTransfer.createTime}">
									<input class="easyui-datebox" required="true"
										value="${dataTransfer.createTime}" style="width: 200px;"
										id="createTime" name="createTime">
								</c:if>
							</td>
							<td align="right" class="label">
								移交人员 ：
							</td>
							<td>
								<c:if test="${not empty dataTransfer.createUserAccount }">
									<input class="easyui-validatebox"
										value="${dataTransfer.createUserAccount}"
										style="width: 200px;" id="createUserAccount"
										name="createUserAccount" readonly="readonly">
								</c:if>
								<c:if test="${empty dataTransfer.createUserAccount}">
									<input class="easyui-validatebox" value="${nowUser}"
										style="width: 200px;" id="createUserAccount"
										name="createUserAccount" readonly="readonly">
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								综合性报告 ：
							</td>
							<td colspan="3">
								 <c:if test="${not empty dataTransfer.comprehensiveReportFile}">
										<a href="javascript:download('${dataTransfer.comprehensiveReportFile}')"
											id="comprehensiveReportFileDownload">综合性报告 下载</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a id="deletecomprehensiveReportFile" class="easyui-linkbutton"
											iconCls="icon-remove">删除文件</a>
								</c:if>
								<input type="file" id="comprehensiveReportFile" name="comprehensiveReportFile"
										style="width: 400px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr id="files">
								<td class="label" align="right">
									其他附件 ：
								</td>
								<td colspan="3" align="left">
									<input type="file" style="width: 400px;" class="text" id="file"
										name="otherFile" value="浏览" />
									<a class="easyui-linkbutton" iconCls="icon-add" id="addfile">增加</a>
								</td>
							</tr>
					</tbody>
				</table>
			</div>
			<table id="grid"></table>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>