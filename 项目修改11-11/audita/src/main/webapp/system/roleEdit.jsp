<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/roleEdit.js"></script>
		<title>角色信息编辑</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="mainDiv" style="width: 100%;">
				<div id="pa" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true" 
					title="角色基本信息" iconCls="icon-redo">
					<div style="margin: 1px;">
						<a id="save" iconCls="icon-save" class="easyui-linkbutton">保存</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel"
							onclick="javascript:cancel()">关闭</a>
					</div>
					<table class="form" style="width: 99%;"
						cellspacing="0">
						<tbody style="border: 1px solid #ccc">
							<c:if test="${processType == 'update'}">
								<tr>
									<td  style="text-align: right;" class="label">
										角色编码 ：
									</td>
									<td >
										<input type="hidden" id="roleId" name="roleId"
											value="${role.id}">
										${role.id}
									</td>
									<td class="label" >
										创建时间：
									</td>
									<td  align="left">
										${role.createtime}
									</td>
								</tr>
								<tr>
									<td class="label">
										角色名称 ：
									</td>
									<td >
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="rolename" name="rolename"
											value="${role.rolename}" validType="maxLength[20]">
									</td>
									<td class="label" colspan="1">
										状态 ：
									</td>
									<td >
										<select id="state" name="state" style="width: 100px;"
											class="easyui-combobox">
											<c:if test="${role.state==0}">
												<option value="0" selected="selected">启用</option>
												<option value="1">禁用</option>
												<option value="2">删除</option>
											</c:if>
											<c:if test="${role.state== 1}">
												<option value="0">启用</option>
												<option value="1" selected="selected">禁用</option>
												<option value="2">删除</option>
											</c:if>
											<c:if test="${role.state== 2}">
												<option value="0">启用</option>
												<option value="1">禁用</option>
												<option value="2" selected="selected">删除</option>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td class="label" >
										描述：
									</td>
									<td colspan="3">
										<textarea id="remark" class="text" style="width: 400px;"
											cols="5" name="remark">
										 ${role.remark} 
										</textarea>
									</td>
								</tr>
							</c:if>
							<c:if test="${processType == 'add'}">
								<tr>
									<td class="label" >
										角色名称 ：
									</td>
									<td >
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="roleName" name="roleName"
											validType="maxLength[20]">
									</td>
									<td class="label">
										状态 ：
									</td>
									<td >
										<select class="easyui-combobox" name="state">
											<option value="0">启用</option>
											<option value="1">禁用</option>
											<option value="2">删除</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="label">
										描述：
									</td>
									<td colspan="3">
										<textarea id="remark" class="text" style="width: 500px;"
											cols="5" name="remark"></textarea>
									</td>
								</tr>
							</c:if>
						<c:if test="${processType == 'update'}">
						    <tr>
								<td class="label">
									顶层菜单：
								</td>
								<td colspan="3">
									<select id="topMenu" name="topMenu" class="easyui-combobox"
										style="width: 200px;">
										<c:forEach items="${topMenus}" var="menu">
											<option value="${menu.id}">${menu.menuName}</option>
										</c:forEach>
									</select>
									<a id="initialize" class="easyui-linkbutton"
										iconCls="icon-reload">顶层菜单</a>
								</td>
						    </tr>
							<tr>
								<td class="label">
									子级菜单：
								</td>
								<td>
									<select name="subMenu" id="subMenu" valueField="id"
										style="width: 200px;" textField="text" class="easyui-combobox">
									</select>
								</td>
								<td class="label">
									模块菜单：
								</td>
								<td>
									<select name="moduleMenu" id="moduleMenu" valueField="id"
										style="width: 200px;" textField="text" class="easyui-combobox">
									</select>
								</td>
							</tr>
							</tbody>
						</c:if>
					</table>
					<input type="hidden" id="coutextPath"
						value="<%=request.getContextPath()%>">
					<input type="hidden" id="pid" value="${id}">
					<input type="hidden" id="processType" value="${processType}">
					<input type="hidden" id="requestUrl" value="${url}">
					<input type="hidden" id="title"
						value="<spring:message code='prompt.title.title'/>">
					<input type="hidden" id="noUpdate"
						value="<spring:message code='error.message.noUpdate'/>">
				</div>
				<c:if test="${processType == 'update'}">
					<div id="p1" class="easyui-panel"
						style="background: #fafafa; margin-bottom: 5px;" collapsible="true" style="width: 98%;"
						title="权限分配" iconCls="icon-redo">
						<table id="impowerTable" class="easyui-datagrid"></table>
					</div>
					<div id="p2" class="easyui-panel"
						style="background: #fafafa; margin-bottom: 5px;" collapsible="true" style=" width: 98%;"
						title="已经拥有的权限" iconCls="icon-redo">
						<div style="margin: 1px;">
							<table id="areadyHaveImpower" class="easyui-treegrid"></table>
						</div>
					</div>
				</c:if>
		</form>
	</body>
</html>