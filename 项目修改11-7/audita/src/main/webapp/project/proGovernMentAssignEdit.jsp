<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/proGovernMentAssignEdit.js"></script>
		<title>政府交办编辑</title>
		<style type="text/css">
body {
	margin: 10px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

form .label {
	padding-right: 80px;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="mainDiv">
				<c:if test="${processType == 'assignCodeAddState'}">
					<div id="p2" class="easyui-panel"
						style="background: #fafafa; height: 100px;" collapsible="true"
						title="政府交办批次信息" iconCls="icon-tip">
						<div style="margin: 5px;">
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
						<table class="form" style="width: 99%; border-style: none; margin-top: 5px;"
							id="mytableid" cellpadding="0" cellspacing="0" >
							<tbody>
								<tr>
									<td class="label">
										报批批次：
									</td>
									<td>
										<input type="hidden" id="assignId" name="assignId"
											value="${proGovernmentAssign.id}">
											<input class="easyui-validatebox" style="width: 200px;"
											id="reportBatch" name="reportBatch" required="true"
											maxlength="20" value="${proGovernmentAssign.reportBatch}">
										
									</td>
									<td class="label">
										报批时间：
									</td>
									<td>
									<input type="text" class="easyui-datebox" id="reportTime"
											name="reportTime" value="${proGovernmentAssign.reportTime}"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
				<div id="p2" class="easyui-panel"
					style="background: #fafafa; height: 130px;" collapsible="true"
					title="政府交办批次信息" iconCls="icon-tip">
					<c:if test="${processType=='add'}">
					<div style="margin: 5px;">
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					</c:if>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<c:if test="${processType=='add'}">
								<tr>
									<td class="label">
										报批批次：
									</td>
									<td>
										<input type="hidden" id="assignId" name="assignId">
										<input class="easyui-validatebox" style="width: 200px;"
											id="reportBatch" name="reportBatch" required="true"
											maxlength="10">
									</td>
									<td class="label">
										报批时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="reportTime" name="reportTime">
									</td>
								</tr>
							</c:if>
							<c:if test="${processType == 'assignCodeAddState'}">
								<tr>
									<td class="label">
										政府交办文号：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 200px;"
											id="assignCode" name="assignCode" required="true"
											maxlength="20" value="${proGovernmentAssign.assignCode}">
									</td>
									<td class="label">
										政府交办时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="assignTime" name="assignTime"
											required="true" value="${proGovernmentAssign.assignTime}">
									</td>
								</tr>
								<tr>
									<td class="label">
										政府交办文件：
									</td>
									<td colspan="3">
										<input type="file" style="width: 400px;" class="text"
											id="file" name="fileName" value="浏览" />
										<!--<a class="easyui-linkbutton" iconCls="icon-add" id="addfile">增加</a>
									-->
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<div id="p3" class="easyui-panel"
					style="background: #fafafa; height: 450px;" collapsible="true"
					title="交办项目信息" iconCls="icon-tip">
					<div style="margin-bottom: 5px;">
						<a id="addSubProject" class="easyui-linkbutton"
							iconCls="icon-save">添加</a>
						<a id="delSubProject" class="easyui-linkbutton"
							iconCls="icon-remove">删除</a>
					<a id="daoexcel" class="easyui-linkbutton"
							iconCls="icon-save">导出Excel</a>
					</div>
					<table id="governMentAssignProject"></table>
				</div>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${govmoduelId}">
			<input type="hidden" id="processType" value="${processType}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>