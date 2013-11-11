<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>会议记录-录入管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
	    <script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/proConferenceIndex.js"></script>
		<style type="text/css">
			body {
				margin-left: 0px;
				margin-top: 0px;
				padding: 0px;
			}
			
			tbody td {
				border: 1px solid #ccc;
			}
		</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 100%; margin: 5px;">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; height: 120px;display: none;" collapsible="true"
				title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 10px;" align="center">
						<table class="form" style="width: 99%; border-style: none;" cellpadding="0" cellspacing="0">
							<tbody>
							   <input type="hidden" id="datapreId" name="datapreId" value="${data.id}">
							    <tr>
									<td align="right" width="20%">
										审计项目编号：
									</td>
									<td>
										${data.id}
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										审计项目名称：
									</td>
									<td>
										${data.projectName}
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										项目业主名称：
									</td>
									<td>
										${data.proownerid}
									</td>
								</tr>
								
							</tbody>
						</table>
					</div>
				</form>
			</div>
			 <div id="p2" class="easyui-panel"  style="background:#fafafa;display: none;"   collapsible="true" >  
			 	<div id="button-bar" style="margin: 5px;">
				<c:forEach items="${conferencdataemap}" var="conference">
					<c:if test="${conference != 'input.do'}">
						<input type="hidden" id="input" value="1">
					</c:if>
					<c:if test="${conference=='find.do'}">
						<input type="hidden" id="find" value="find">
					</c:if>
					<c:if test="${conference=='add.do'}">
						<a href="javascript:void(0);" id="add" onclick="add();" iconCls="icon-add"
							class="easyui-linkbutton">增加</a>
					</c:if>
						<c:if test="${conference=='update.do'}">
						<a href="javascript:void(0);" id="update" onclick="update()" iconCls="icon-edit"
							class="easyui-linkbutton">编辑</a>
					</c:if>
					<c:if test="${conference=='delete.do'}">
						<a href="javascript:void(0);" id="delete"  onclick="del()"    iconCls="icon-remove"
							class="easyui-linkbutton">删除</a>
					</c:if>
					</c:forEach>
			</div>
                         <table id="gridtable" ></table>
            </div> 
			<input type="hidden" id="path" value="<%=request.getContextPath()%>">
			<input type="hidden" id="frameId" value="${requestScope.lookconference}">
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
		<input type="hidden" id="noSelectdel" value="<spring:message code='error.message.noSelectdel'/>">
		</div>
	</body>
</html>
