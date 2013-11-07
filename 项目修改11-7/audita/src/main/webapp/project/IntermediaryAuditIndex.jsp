<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>初审审核管理</title>
			<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
	    <script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/intermediaryAudit.js"></script>
		<style type="text/css">
			body {
				margin-left: 0px;
				margin-top: 0px;
				padding: 0px;
			}
		</style>
	</head>
	<body>
		<div id="mainDiv" style="width: 99%; margin: 5px;">
			<div id="p1" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 5px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="20%">
										项目名称：
									</td>
									<td align="left">
										<input type="text" style="width: 180px;" id="projectName">
									</td>
									<td align="right" width="20%">
										项目业主：
									</td>
									<td align="left">
									   <input type="hidden" id="proownerid"> 
									   <input type="text" style="width: 180px;"   id="ownerName" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td align="right" width="20%">
										中介机构：
									</td>
									<td align="left">
										 <input type="hidden" id="intermeidiaryId"> 
										<input type="text" style="width: 180px;" id="intermeidiaryName" readonly="readonly">
									</td>
									<td align="right" width="20%">
								                      初审结果：
									</td>
									<td align="left">
									<input type="hidden"  id="inprint" value="${param.key}">
									<c:if test="${empty param.key}">
									     <select style="width: 180px"  class="isconfirm" onchange="change();">
										 <option value="0" >未审核</option>
										  <option value="1">已审核</option>
									      </select>
									</c:if>
									<c:if test="${param.key==0}">
									     <select style="width: 180px"  class="isconfirm" onchange="change();">
										 <option value="0" selected="selected" >未审核</option>
										  <option value="1">已审核</option>
									      </select>
									</c:if>
									<c:if test="${param.key==1}">
									     <select style="width: 180px"  class="isconfirm" onchange="change();">
										 <option value="0" >未审核</option>
										  <option value="1" selected="selected">已审核</option>
									      </select>
									</c:if>
									</td>
									
								</tr>
								<tr>
								    <td align="right" width="20%">
									  项目安排类型：
									</td>
									<td align="left">
										<select style="width: 180px"  id="arrangerpro">
										 <option value="" selected="selected">全部</option>
										 <option value="0" >单项目</option>
										  <option value="1">打包项目</option>
										</select>
									</td>
									 <td align="right" width="20%">
									  初审审核应完成时间 ：
									</td>
									<td align="left">
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="beginTime" name="beginTime">
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="endTime" name="endTime">
									</td>
								  
								</tr>
								<tr>
								    <td align="right" width="20%">
								              限时：
									</td>
									<td align="left">
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="timeLimitStart" name="timeLimitStart">
										  ~
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="timeLimitEnd" name="timeLimitEnd">
									</td>
									<td align="right" width="20%">
								              是否加急：
									</td>
									<td align="left">
									     <select id="isUrgent" style="width: 180px;">
									         <option value="">全部</option>
									         <option value="0">否</option>
									         <option value="1">是</option>
									     </select>
									</td>
								</tr>
								<tr>
								    <td colspan="4" align="center">
								     
										<a href="javascript:void(0);" id="search"
											iconCls="icon-search" class="easyui-linkbutton">查询</a>
									<a href="javascript:void(0);" id="reset"
												iconCls="icon-cancel" class="easyui-linkbutton">重置</a>
								   </td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>

			<div id="p2" class="easyui-panel"
				style="background: #fafafa;" collapsible="true" iconCls='icon-tip'>
				<div id="button-bar" style="margin: 5px;">
					<c:forEach items="${intermap}" var="inter">
						<c:if test="${inter != 'input.do'}">
							<input type="hidden" id="input" value="1">
						</c:if>
						<c:if test="${inter=='find.do'}">
							<input type="hidden" id="find" value="find">
						</c:if>
						<c:if test="${inter=='add.do'}">
							<a href="javascript:void(0);" id="add" onclick="add();"
								iconCls="icon-add" class="easyui-linkbutton">录入</a>

							<a href="javascript:void(0);" id="look" style="display: none;"
								onclick="add();" iconCls="icon-add" class="easyui-linkbutton">查看</a>
						</c:if>
					</c:forEach>
				</div>
				<table id="grid"></table>
				<input type="hidden" id="path" value="<%=request.getContextPath()%>">
				<input type="hidden" id="frameId" value="${requestScope.cdmoduelId}">
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
			<jsp:include page="../common/showdiv.jsp"></jsp:include></body>
</html>
