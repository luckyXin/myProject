<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>会议记录管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
	    <script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/proConferencedataIndex.js"></script>
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
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="20%">
										审计项目名称：
									</td>
									<td>
										<input type="text" style="width: 200px;" id="projectName">
									</td>
									<td align="right" width="20%">
										项目业主：
									</td>
									<td>
									   <input type="hidden" id="proownerid"> 
										<input type="text" style="width: 200px;"   id="ownerName" readonly="readonly">
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
			 <div id="p2" class="easyui-panel"  style="background:#fafafa;display: none;"   collapsible="true" >  
			 	<div id="button-bar" style="margin: 5px;">
				<c:forEach items="${conferencemap}" var="conference">
					<c:if test="${conference != 'input.do'}">
						<input type="hidden" id="input" value="1">
					</c:if>
					<c:if test="${conference=='find.do'}">
						<input type="hidden" id="find" value="find">
					</c:if>
					<c:if test="${conference=='add.do'}">
						<a href="javascript:void(0);" id="add" onclick="add();" iconCls="icon-add"
							class="easyui-linkbutton">录入</a>
					</c:if>
					</c:forEach>
			</div>
                         <table id="grid" ></table>
            </div> 
			<input type="hidden" id="path" value="<%=request.getContextPath()%>">
			<input type="hidden" id="frameId" value="${requestScope.proconferencemoduelId}">
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
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>
