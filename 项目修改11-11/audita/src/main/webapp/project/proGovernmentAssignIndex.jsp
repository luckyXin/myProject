<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>政府交办管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/governmentAssign.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 100%; margin: 5px;">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; display: none;margin-bottom: 5px;"
				collapsible="true" title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 5px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="20%" id="title1">
										项目批次：
									</td>
									<td>
										<input type="text" id="reportBatch" style="width: 200px;">
										<input type="text" id="projectName" style="width: 200px;">
									</td>
									<td align="right" width="20%" id="title2">
										交办文号：
									</td>
									<td>
										<input type="text" id="assignCode" style="width: 200px;">
										<input type="text" id="ownerName" style="width: 200px;">
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										信息类型：
									</td>
									<td>
										<select class="easyui-combobox" id="infoType" name="infoType"
											style="width: 200px; display: none;">
											<option value="0" selected="selected">项目信息</option>
											<option value="1">交办信息</option>
										</select>
									</td>
									<td align="right" width="20%">
										项目类型：
									</td>
									<td>
										<select class="easyui-combobox" id="projectType"
											style="width: 200px;display: none;" name="projectType">
											<c:if test="${isAssign=='0'}">
												<option value="0" selected="selected">未交办</option>
												<option value="1">已交办</option>
												<option value="2">全部</option>
											</c:if>
											<c:if test="${isAssign=='1'}">
												<option value="0">未交办</option>
												<option value="1" selected="selected">已交办</option>
												<option value="2">全部</option>
											</c:if>
											<c:if test="${isAssign!='1' && isAssign!='0'}">
												<option value="0">未交办</option>
												<option value="1">已交办</option>
												<option value="2" selected="selected">全部</option>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<a href="javascript:void(0);" id="search"
											iconCls="icon-search" class="easyui-linkbutton">查询</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div id="p2" class="easyui-panel"
				style="background: #fafafa; display: none;" collapsible="true">
				<div id="button-bar" style="margin: 1px;">
					<c:forEach items="${govmap}" var="gov">
						<c:if test="${gov != 'input.do'}">
							<input type="hidden" id="input" value="1">
						</c:if>
						<c:if test="${gov=='find.do'}">
							<input type="hidden" id="find" value="find">
						</c:if>
						<c:if test="${gov=='add.do'}">
							<a href="javascript:void(0);" id="add" iconCls="icon-add"
								class="easyui-linkbutton">创建交办批次</a>
							<a href="javascript:void(0);" id="assignCodeAdd"
								iconCls="icon-add" class="easyui-linkbutton">录入政府交办</a>
						</c:if>
						<c:if test="${gov=='update.do'}">
							<a href="javascript:void(0);" id="update" onclick="update()"
								iconCls="icon-edit" class="easyui-linkbutton">编辑</a>
						</c:if>
						<c:if test="${gov=='delete.do'}">
							<a href="javascript:void(0);" id="delete" onclick="del()"
								iconCls="icon-remove" class="easyui-linkbutton">删除</a>
						</c:if>
					</c:forEach>
				</div>
				<table id="grid"></table>
				<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
				<input type="hidden" id="frameId" value="${govmoduelId}">
				<input type="hidden" id="noPower"
					value="<spring:message code='error.message.noPower'/>">
				<input type="hidden" id="isDelete"
					value="<spring:message code='prompt.delete.isDelete'/>">
				<input type="hidden" id="noSelect"
					value="<spring:message code='error.message.noSelect'/>">
				<input type="hidden" id="title"
					value="<spring:message code='prompt.title.title'/>">
				<input type="hidden" id="alreadyDelete"
					value="<spring:message code='error.message.alreadyDelete'/>">
				<input type="hidden" id="noSelectdel"
					value="<spring:message code='error.message.noSelectdel'/>">
			</div>
	</body>
</html>
