<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/moduleEdit.js"></script>
		<title>模块信息管理</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

tbody td {
	border: 1px solid #ccc;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="mainDiv" style="margin: 5px; width: 99%;">
				<div style="margin: 5px;">
					<a id="save" iconCls="icon-save" class="easyui-linkbutton">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="javascript:cancel()">关闭</a>
				</div>

				<table class="form" style="width: 98%" cellspacing="0">
					<tbody>
						<c:if test="${processType == 'update'}">
							<tr>
								<td class="label">
									模块编码：
								</td>
								<td>
									<input type="hidden" id="moduleId" name="moduleId"
										value="${module.moduleId}">
									${module.moduleId}
								</td>
								<td class="label">
									创建时间：
								</td>
								<td>
									${module.createtime}
								</td>
							</tr>
							<tr>
								<td class="label">
									模块名称：
								</td>
								<td>
									<input class="easyui-validatebox" required="true"
										style="width: 200px;" id="moduleName" name="moduleName"
										value="${module.moduleName}" validType="maxLength[20]">
								</td>
								<td class="label">
									状态：
								</td>
								<td>
									<select class="easyui-combobox" id="state" name="state" style="width: 200px;">
										<c:if test="${module.state == 0}">
											<option value="0" selected="selected">启用</option>
											<option value="1">禁用</option>
											<option value="2">删除</option>
										</c:if>
										<c:if test="${module.state == 1}">
											<option value="0">启用</option>
											<option value="1" selected="selected">禁用</option>
											<option value="2">删除</option>
										</c:if>
										<c:if test="${module.state == 2}">
											<option value="0">启用</option>
											<option value="1">禁用</option>
											<option value="2" selected="selected">删除</option>
										</c:if>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									顶层菜单：
								</td>
								<td>
									<select id="topMenu" name="topMenu" class="easyui-combobox" style="width: 200px;">
										<c:forEach items="${topMenus}" var="menu">
											<c:if test="${menu.id == module.topMenuId}">
												<option value="${menu.id}" selected="selected">${menu.menuName}</option>
											</c:if>
											<c:if test="${menu.id != module.topMenuId}">
												<option value="${menu.id}">${menu.menuName}</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td class="label">
									子级菜单：
								</td>
								<td>
									<select class="easyui-combobox" name="subMenu" id="subMenu"
										valueField='id' textField='text' style="width: 200px;">
										<c:if test="${not empty subMenus}">
											<c:forEach items="${subMenus}" var="menu">
												<c:if test="${menu.id == module.menuId}">
													<option value="${menu.id}" selected="selected">${menu.text}</option>
												</c:if>
												<c:if test="${menu.id != module.menuId}">
													<option value="${menu.id}">${menu.text}</option>
												</c:if>
											</c:forEach>
										</c:if>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									URL：
								</td>
								<td>
									<input class="easyui-validatebox" id="url" name="url"
										style="width: 200px;" value="${module.url}">
								</td>
								<td class="label">
									描述：
								</td>
								<td>
									<textarea id="remark" class="text" style="width: 400px;"
										cols="5" name="remark">
										 ${module.remark} 
										</textarea>
								</td>
							</tr>
						</c:if>
						<c:if test="${processType == 'add'}">
							<tr>
								<td class="label">
									模块名称 ：
								</td>
								<td>
									<input class="easyui-validatebox" required="true"
										style="width: 200px;" id="moduleName" name="moduleName"
										validType="maxLength[20]">
								</td>
								<td class="label">
									状态 ：
								</td>
								<td>
									<select class="easyui-combobox" name="state" style="width: 200px;">
										<option value="0">启用</option>
										<option value="1">禁用</option>
										<option value="2">删除</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									顶层菜单：
								</td>
								<td>
									<select id="topMenu" name="topMenu" class="easyui-combobox" style="width: 200px;">
										<c:forEach items="${menus}" var="menu">
											<option value="${menu.id}">${menu.menuName}</option>
										</c:forEach>
									</select>
								</td>
								<td class="label">
									子级菜单：
								</td>
								<td>
									<select class="easyui-combobox" name="subMenu" id="subMenu"
										valueField='id' textField='text' style="width: 200px;">
										<option value=""></option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									URL：
								</td>
								<td>
									<input class="easyui-validatebox" id="url" name="url"
										style="width: 150px;">
								</td>
								<td class="label">
									描述：
								</td>
								<td>
									<textarea id="remark" class="text" style="width: 400px;"
										cols="5" name="remark"></textarea>
								</td>
							</tr>
						</c:if>
					</tbody>
					<c:if test="${processType == 'update'}">
						<tr>
							<td colspan="4" align="right">
								<table id="functionTable" class="easyui-datagrid"></table>
							</td>
						</tr>
					</c:if>
				</table>
				<input type="hidden" id="coutextPath"
					value="<%=request.getContextPath()%>">
				<input type="hidden" id="id" value="${id}">
				<input type="hidden" id="processType" value="${processType}">
				<input type="hidden" id="requestUrl" value="${url}">
				<input type="hidden" id="title"
					value="<spring:message code='prompt.title.title'/>">
				<input type="hidden" id="noUpdate"
					value="<spring:message code='error.message.noUpdate'/>">
			</div>
		</form>
	</body>
</html>