<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>消息管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/messageIndex.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	padding: 0px;
}
</style>
	</head>
	<body>
	 <div style="width: 100%;margin: 5px;">
	    <input type="hidden" id="root" value="<%=request.getContextPath()%>">
	     <input type="hidden" id="frameid" value="${messagemoduelid}">
	     <input type="hidden" id="noPower" value="<spring:message code='error.message.noPower'/>">
		<input type="hidden" id="isDelete" value="<spring:message code='prompt.delete.isDelete'/>">
		<input type="hidden" id="noSelect" value="<spring:message code='error.message.noSelect'/>">
		<input type="hidden" id="noSelectdel" value="<spring:message code='error.message.noSelectdel'/>">
		<input type="hidden" id="title" value="<spring:message code='prompt.title.title'/>">
		<input type="hidden" id="methodtype" value="${methodtype}">
		<input type="hidden" id="key" value="${key}">
	
		 <div id="p1" class="easyui-panel"  style="background:#fafafa;display: none;"  collapsible="true" > 
		 
		   	<form id="from" name="from">
						<div id="center" style="margin-top: 10px;" align="center">
							<table class="form" cellspacing="0" width="99%">
								<tbody>
									<tr>
										<td align="right" width="20%">
											消息方式：
										</td>
										<td>
											<select id="method" name="method" style="width: 200px;">
											  <c:if test="${empty methodtype}">
												<option value="0">
													发送消息
												</option>
												<option value="1">
													接收消息
												</option>
												<option value="2" selected="selected">
													全部
												</option>
												</c:if>
												<c:if test="${methodtype==0}">
												<option value="0" selected="selected">
													发送消息
												</option>
												<option value="1">
													接收消息
												</option>
												<option value="2">
													全部
												</option>
												</c:if>
												<c:if test="${methodtype==1}">
												<option value="0">
													发送消息
												</option>
												<option value="1" selected="selected">
													接收消息
												</option>
												<option value="2">
													全部
												</option>
												</c:if>
											</select>
										</td>
										<td align="right" width="20%">
											阅读状态：
										</td>
										<td>
											<select id="messagestate" name="messagestate" style="width: 200px;">
											<c:if test="${empty key}">
										      <option value="0">
													未阅读
												</option>
												<option value="1">
													已阅读
												</option>
												<option value="" selected="selected">
													全部
												</option>
										   </c:if>
										   <c:if test="${key==0}">
										      <option value="0" selected="selected">
													未阅读
												</option>
												<option value="1">
													已阅读
												</option>
												<option value="">
													全部
												</option>
										   </c:if>
										    <c:if test="${key==1}">
										      <option value="0">
													未阅读
												</option>
												<option value="1" selected="selected">
													已阅读
												</option>
												<option value="">
													全部
												</option>
										   </c:if>
											</select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="4">
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
		<c:forEach items="${messagemap}" var="message">
		        <c:if test="${message != 'input.do'}">
				       <input type="hidden" id="input" value="1">
			    </c:if>
			     <c:if test="${message=='find.do'}">
		          <input type="hidden" id="isfind" value="find">
		           <a href="javascript:void(0);" id="look" iconCls="icon-edit"  class="easyui-linkbutton"
					 onclick="look()">查看消息</a>
		        </c:if>
			      <c:if test="${message=='add.do'}">
		          <a href="javascript:void(0);" id="add" iconCls="icon-add" class="easyui-linkbutton"
					 onclick="add()">发送</a>
		        </c:if>
		        <c:if test="${message=='delete.do'}">
		          <a href="javascript:void(0);" id="delete" iconCls="icon-remove" class="easyui-linkbutton"
					 onclick="del()">删除</a>
		        </c:if>
		 </c:forEach>	
		</div>
                 <table id="grid" ></table>
        </div>  
		
		</div>
		
	</body>
</html>
