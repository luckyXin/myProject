<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>施工企业管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/staff/constructindex.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
	</head>
	<body>
		<div style="width: 100%; margin: 5px;">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; height: 100px;margin-bottom: 5px;" collapsible="true"
				title="查询条件" iconCls="icon-search">
					<div id="center" style="margin-top: 10px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody style="border: 1px solid #ccc">
								<tr>
									<td align="right" width="40%">
										企业名称：
									</td>
									<td style="width: 200px;">
										<input type="text" style="width: 200px;" id="searchname">
									</td>
									<td align="left">
										<a href="javascript:void(0);" id="search"
											iconCls="icon-search" class="easyui-linkbutton">查询</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div id="p2" class="easyui-panel" style="background: #fafafa;"
				collapsible="true" title="施工企业" iconCls="icon-tip">
				<div id="button-bar" style="margin: 1px;">
					<c:forEach items="${conmap}" var="con">
						<c:if test="${con != 'input.do'}">
							<input type="hidden" id="input" value="1">
						</c:if>
						<c:if test="${con=='find.do'}">
							<input type="hidden" id="isfind" value="find">
						</c:if>
						<c:if test="${con=='add.do'}">
							<a href="javascript:void(0);" id="add" iconCls="icon-add"
								class="easyui-linkbutton" onclick="add()">增加</a>
						</c:if>
						<c:if test="${con=='update.do'}">
							<a href="javascript:void(0);" id="update" iconCls="icon-edit"
								class="easyui-linkbutton" onclick="edit()">编辑</a>
						</c:if>
						<c:if test="${con=='delete.do'}">
							<a href="javascript:void(0);" id="delete" iconCls="icon-remove"
								class="easyui-linkbutton" onclick="del()">删除</a>
						</c:if>
					</c:forEach>
				</div>
				<table id="grid"></table>
			</div>
		</div>
		<input type="hidden" id="root" value="<%=request.getContextPath()%>">
		<input type="hidden" id="frameid" value="${sessionScope.moduelid}">
		<input type="hidden" id="noPower"
			value="<spring:message code='error.message.noPower'/>">
		<input type="hidden" id="isDelete"
			value="<spring:message code='prompt.delete.isDelete'/>">
		<input type="hidden" id="noSelect"
			value="<spring:message code='error.message.noSelect'/>">
		<input type="hidden" id="noSelectdel"
			value="<spring:message code='error.message.noSelectdel'/>">
		<input type="hidden" id="title"
			value="<spring:message code='prompt.title.title'/>">
	</body>
</html>
