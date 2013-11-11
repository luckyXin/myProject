<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>单项目安排管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/singleProjectArrangeIndex.js"></script>
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
				style="background: #fafafa; height: 115px; margin-bottom: 5px;"
				collapsible="true" title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 10px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="20%">
										项目名称：
									</td>
									<td>
										<input type="text" style="width: 200px;" id="projectName" name="projectName">
									</td>
									<td align="right" width="20%">
										业主名称：
									</td>
									<td>
										<input type="text" style="width: 200px;" readonly="readonly"
											name="ownerName" id="ownerName">
										<input type="hidden" id="ownerId">
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										安排状态：
									</td>
									<input type="hidden" id="isarrange"  value="${isArrange}"/>
									<td>
										<c:if test="${isArrange =='0'}">
											<select id="arrangeState" style="width: 200px;"
												class="easyui-combobox">
												<option value="0" selected="selected">未安排</option>
												<option value="1">已安排</option>
												<option value="2">全部</option>
											</select>
										</c:if>
										<c:if test="${isArrange =='1'}">
											<select id="arrangeState" style="width: 200px;"
												class="easyui-combobox">
												<option value="0">未安排</option>
												<option value="1" selected="selected">已安排</option>
												<option value="2">全部</option>
											</select>
										</c:if>
										<c:if test="${isArrange != '1' && isArrange != '0'}">
											<select id="arrangeState" style="width: 200px;"
												class="easyui-combobox" >
												<option value="0" selected="selected">未安排</option>
												<option value="1">已安排</option>
												<option value="2">全部</option>
											</select>
										</c:if>
									</td>
									<td align="center" colspan="2">
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
				collapsible="true" title="项目安排管理" iconCls="icon-tip">
				<div id="button-bar" style="margin: 5px;">
					<c:forEach items="${mapFunction}" var="con">
						<c:if test="${con != 'input.do'}">
							<input type="hidden" id="input" value="1">
						</c:if>
						<c:if test="${con=='find.do'}">
							<input type="hidden" id="find" value="find">
							<a href="javascript:void(0);"  style="display: none;"  id="look" iconCls="icon-edit"
								class="easyui-linkbutton">查看</a>
						</c:if>
						<c:if test="${con=='add.do'}">
							<a href="javascript:void(0);" id="add" iconCls="icon-add"
								class="easyui-linkbutton">项目安排</a>
						</c:if>
						<c:if test="${con=='update.do'}">
							<a href="javascript:void(0);" id="update" iconCls="icon-edit"
								class="easyui-linkbutton">编辑</a>
						</c:if>
						<c:if test="${con=='delete.do'}">
							<a href="javascript:void(0);" id="delete" iconCls="icon-remove"
								class="easyui-linkbutton">删除</a>
						</c:if>
						<c:if test="${con=='destroy.do'}">
							<a href="javascript:void(0);" id="destroy" iconCls="icon-cancel"
								class="easyui-linkbutton">销毁</a>
						</c:if>
					</c:forEach>
						<a id="daoarrageexcel" class="easyui-linkbutton"
							iconCls="icon-save">导出Excel</a>
				</div>
				<input type="hidden" id="isArrangeState">
				<table id="grid" class="easyui-datagrid"></table>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="frameId" value="${id}">
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
		</div>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>
