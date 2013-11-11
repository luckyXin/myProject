<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>中介结构管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/staff/intermediaryagencyIndex.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 99%; margin: 1px;">

			<div id="p1" class="easyui-panel"
				style="background: #fafafa; height: 100px;margin-bottom: 5px;" collapsible="true"
				title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 10px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="25%;">
										中介机构名称：
									</td>
									<td style="width: 200px;">
										<input type="text" style="width: 200px;" id="searchname">
									</td>
									<td align="right" width="25%;">
										中标年度：
									</td>
									<td>
										<select style="width: 200px;" name="bidyear" id="bidyear">
											<option value="">
											</option>
											<c:forEach items="${DicYear}" var="DicYear">
												<option value="${DicYear.id}">
													${DicYear.dictionaryname}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="4">
										<a href="javascript:void(0);" id="search"
											iconCls="icon-search" class="easyui-linkbutton">查询</a>
										<a href="javascript:void(0);" id="reset" iconCls="icon-search"
											class="easyui-linkbutton">重置</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div id="p2" class="easyui-panel" style="background: #fafafa;"
				collapsible="true" title="中介结构" iconCls="icon-tip">
				<div id="button-bar" style="margin: 1px;">
					<c:forEach items="${conmap}" var="con">
						<c:if test="${con != 'input.do'}">
							<input type="hidden" id="input" value="1">
						</c:if>
						<c:if test="${con=='find.do'}">
							<input type="hidden" id="find" value="find">
						</c:if>
						<c:if test="${con=='add.do'}">
							<a href="javascript:void(0);" id="add" iconCls="icon-add"
								class="easyui-linkbutton" onclick="add()">增加</a>
						</c:if>
						<c:if test="${con=='update.do'}">
							<a href="javascript:void(0);" id="update" iconCls="icon-edit"
								class="easyui-linkbutton" onclick="edit()">编辑</a>
						</c:if>
						<!-- 
					<c:if test="${con=='delete.do'}">
						<a href="javascript:void(0);" id="delete" iconCls="icon-remove"
							class="easyui-linkbutton" onclick="del()">删除</a>
					</c:if>
					 -->
						<c:if test="${con=='destroy.do'}">
							<a href="javascript:void(0);" id="destroy" iconCls="icon-cancel"
								class="easyui-linkbutton" onclick="destroy()" disable="true">销毁</a>
						</c:if>
					</c:forEach>
					<table id="grid" class="easyui-datagrid">
					</table>
				</div>
				<input type="hidden" id="path" value="<%=request.getContextPath()%>">
				<input type="hidden" id="frameId" value="${id}">
				<input type="hidden" id="noPower"
					value="<spring:message code='error.message.noPower'/>">
				<input type="hidden" id="isDelete"
					value="<spring:message code='prompt.delete.isDelete'/>">
				<input type="hidden" id="noSelect"
					value="<spring:message code='error.message.noSelect'/>">
				<input type="hidden" id="title"
					value="<spring:message code='prompt.title.title'/>">
			</div>
	</body>
</html>
