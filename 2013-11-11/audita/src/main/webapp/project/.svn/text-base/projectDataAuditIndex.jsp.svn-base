<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>资料预审管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/commonFunction.js"></script>
	    <script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/projectDataPre.js"></script>
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
				style="background: #fafafa; height: 100px;display: none;" collapsible="true"
				title="查询条件" iconCls="icon-search">
				<form id="from" name="from">
					<div id="center" style="margin-top: 10px;" align="center">
						<table class="form" cellspacing="0" width="99%">
							<tbody>
								<tr>
									<td align="right" width="20%">
										项目名称：
									</td>
									<td>
										<input type="text" style="width: 180px;" id="projectName">
									</td>
									<td align="right" width="20%">
										业主名称：
									</td>
									<td>
									   <input type="hidden" id="proownerid"> 
										<input type="text" style="width: 200px;"   id="ownerName" readonly="readonly">
									</td>
									
								</tr>
								<tr>
								<td align="right" width="20%">
										是否提交：
									</td>
									<td>
										<select style="width: 180px"  id="issubmit">
										<option value=""></option>
										 <option value="0">未提交</option>
										  <option value="1">已提交</option>
										</select>
									</td>
									<td align="right" width="20%">
										审计类型：
									</td>
									<td>
										<select  id="audittype" name="audittype" style="width: 202px;">
								     <option value="" selected="selected"></option>
								     <c:forEach items="${audittypelist}" var="type">
								       <option value="${type.id}">${type.dictionaryname }</option>
								     </c:forEach>
								          </select>
									</td>
								   
								</tr>
								<tr>
								     <td align="right" width="20%">
										接收资料时间：
									</td>
									<td>
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="beginTime" name="beginTime">
										  ~
										  <input class="easyui-datebox"    style="width: 110px;" editable="false" id="endTime" name="endTime">
									</td>
								   <td colspan="2" align="center">
								     
										<a href="javascript:void(0);" id="search"
											iconCls="icon-search" class="easyui-linkbutton"
											onclick="search();">查询</a>
											<a href="javascript:void(0);" id="reset"
												iconCls="icon-cancel" class="easyui-linkbutton">重置</a>
								   </td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
			 <div id="p2" class="easyui-panel"  style="background:#fafafa;display: none;"   collapsible="true" >  
			 	<div id="button-bar" style="margin: 5px;">
				<c:forEach items="${mapFunction}" var="con">
					<c:if test="${con != 'input.do'}">
						<input type="hidden" id="input" value="1">
					</c:if>
					<c:if test="${con=='find.do'}">
						<input type="hidden" id="find" value="find">
					</c:if>
					<c:if test="${con=='add.do'}">
						<a href="javascript:void(0);" id="add" onclick="add();" iconCls="icon-add"
							class="easyui-linkbutton">增加</a>
					</c:if>
					<!--<c:if test="${con=='addchild.do'}">
						<a href="javascript:void(0);" id="addchild" onclick="addchild();" iconCls="icon-add"
							class="easyui-linkbutton">新增子项目</a>
					</c:if>
					--><c:if test="${con=='update.do'}">
						<a href="javascript:void(0);" id="update" onclick="update()" iconCls="icon-edit"
							class="easyui-linkbutton">编辑</a>
					</c:if>
					<c:if test="${con=='delete.do'}">
						<a href="javascript:void(0);" id="delete"  onclick="del()"    iconCls="icon-remove"
							class="easyui-linkbutton">删除</a>
					</c:if>
					<c:if test="${con=='add.do'}">
						<a href="javascript:void(0);" id="addinter"  iconCls="icon-add"
							class="easyui-linkbutton">录入事务所</a>
					</c:if>
					<!--<c:if test="${con=='destroy.do'}">
						<a href="javascript:void(0);" id="destroy" iconCls="icon-cancel"
							class="easyui-linkbutton">销毁</a>
					</c:if>
				--></c:forEach>
				<a href="javascript:void(0);" id="excelallpro"
									iconCls="icon-print" class="easyui-linkbutton">导出审计项目</a>	
			</div>
                         <table id="grid" ></table>
            </div> 
			<input type="hidden" id="path" value="<%=request.getContextPath()%>">
			<input type="hidden" id="frameId" value="${moduelid}">
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
