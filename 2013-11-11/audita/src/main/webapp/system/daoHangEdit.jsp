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
			src="<%=request.getContextPath()%>/js/system/daoHangEdit.js"></script>
		<title>导航信息管理</title>
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
			<div id="mainDiv"  style="margin: 1px;">
				<div id="pa" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="导航基本信息" iconCls="icon-redo">
					<div style="margin: 1px;">
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%" cellspacing="0">
						<tbody style="border: 1px solid #ccc">
							<c:if test="${processType == 'update'}">
								<tr>
									<td class="label">
										导航编码 ：
									</td>
									<td>
										<input type="hidden" id="menuId" name="menuId"
											value="${menu.id}">
										${menu.id}
									</td>
									<td class="label">
										创建时间 ：
									</td>
									<td>
										${menu.createTime}
									</td>
								</tr>
								<tr>
									<td class="label">
										导航名称 ：
									</td>
									<td class="classtd">
										<input class="easyui-validatebox" required="true"
											validType="maxLength[20]" style="width: 200px;" id="menuName"
											name="menuName" value="${menu.menuName}">
									</td>
									<td class="label">
										状态 ：
									</td>
									<td>
										<select id="state" name="state" style="width: 200px;">
											<c:if test="${menu.state == 0}">
												<option value="0" selected="selected">
													启用
												</option>
												<option value="1">
													禁用
												</option>
											</c:if>
											<c:if test="${menu.state == 1}">
												<option value="0">
													启用
												</option>
												<option value="1" selected="selected">
													禁用
												</option>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td class="label">
										顺序 ：
									</td>
									<td>
										<input class="easyui-numberbox" id="sort" name="sort"
											required="true" style="width: 100px;" value="${menu.sort}">
									</td>
									<td class="label">
										描述 ：
									</td>
									<td>
										<textarea id="remark" class="text" style="width: 400px;"
											cols="5" name="remark">
										 ${menu.remark} 
										</textarea>
									</td>
								</tr>
							</c:if>
							<c:if test="${processType == 'add'}">
								<tr>
									<td class="label">
										导航名称 ：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											validType="maxLength[20]" style="width: 200px;" id="menuName"
											name="menuName">
									</td>
									<td class="label">
										状态 ：
									</td>
									<td>
										<select name="state" style="width: 200px;">
											<option value="0">
												启用
											</option>
											<option value="1">
												禁用
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="label">
										顺序 ：
									</td>
									<td>
										<input class="easyui-numberbox" id="sort" name="sort"
											required="true" style="width: 100px;" value="0"
											validType="maxLength[3]">
									</td>
									<td class="label">
										描述 ：
									</td>
									<td>
										<textarea id="remark" class="text" style="width: 400px;"
											cols="5" name="remark"></textarea>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</form>
		<input type="hidden" id="coutextPath"
			value="<%=request.getContextPath()%>">
		<input type="hidden" id="id" value="${id}">
		<input type="hidden" id="processType" value="${processType}">
		<input type="hidden" id="url" value="${url}">
		<input type="hidden" id="noUpdate"
			value="<spring:message code='error.message.noUpdate'/>">
		<input type="hidden" id="title"
			value="<spring:message code='prompt.title.title'/>">
	</body>
</html>