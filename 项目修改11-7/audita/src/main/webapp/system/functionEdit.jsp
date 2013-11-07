<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:include page="../common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/icon.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/demo.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/common_edit.css">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/functionEdit.js"></script>
		<title>方法信息编辑</title>
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
			<div id="mainDiv">
				<div  style="margin:5px;">
					<a id="save" class="easyui-linkbutton" style="margin: 1px;" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()" style="margin: 1px;" iconCls="icon-cancel">关闭</a>
				</div>
				<table class="form" style="width: 99%" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<c:if test="${processType == 'update'}">
							<tr>
								<td class="label">
									方法编码 ：
								</td>
								<td>
									<input type="hidden" id="id" name="id" value="${function.id}">
									${function.id}
								</td>
								<td class="label">
									创建时间 ：
								</td>
								<td>
									${function.createTime}
								</td>
							</tr>
							<tr>
								<td class="label">
									方法名称 ：
								</td>
								<td>
									<input class="easyui-validatebox" required="true"
										validType="maxLength[20]" style="width: 180px;" id="name"
										name="name" value="${function.name}">
								</td>
								<td class="label">
									状态 ：
								</td>
								<td>
									<select class="easyui-combobox" id="state" name="state">
										<c:if test="${function.state == 0}">
											<option value="0" selected="selected">启用</option>
											<option value="1">禁用</option>
											<option value="2">删除</option>
										</c:if>
										<c:if test="${function.state == 1}">
											<option value="0" >启用</option>
											<option value="1" selected="selected">禁用</option>
											<option value="2">删除</option>
										</c:if>
										<c:if test="${function.state == 2}">
											<option value="0" >启用</option>
											<option value="1" >禁用</option>
											<option value="2" selected="selected">删除</option>
										</c:if>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									方法逻辑名 ：
								</td>
								<td>
									<input class="easyui-validatebox" id="method" name="method"
										required="true" validType="maxLength[30]"
										style="width: 180px;" value="${function.method}">
								</td>
								<td class="label">
									描述 ：
								</td>
								<td>
									<textarea id="remark" class="text" style="width: 400px;"
										cols="5" name="remark">
										 ${function.remark} 
										</textarea>
								</td>
							</tr>
						</c:if>
						<c:if test="${processType == 'add'}">
							<tr>
								<td class="label">
									方法名称 ：
								</td>
								<td>
									<input class="easyui-validatebox" id="name" name="name"
										required="true" validType="maxLength[20]" style="width: 180px;">
								</td>
								<td class="label">
									状态 ：
								</td>
								<td>
									<select class="easyui-combobox" name="state">
										<option value="0">启用
										</option>
										<option value="1">
											禁用
										</option>
										<option value="2">
												禁用
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="label">
									方法逻辑名 ：
								</td>
								<td>
									<input class="easyui-validatebox" id="method" name="method"
										required="true" validType="maxLength[20]" style="width: 180px;">
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
		</form>
		<input type="hidden" id="coutextPath"
			value="<%=request.getContextPath()%>">
		<input type="hidden" id="fatherId" value="${id}">
		<input type="hidden" id="processType" value="${processType}">
		<input type="hidden" id="url" value="${url}">
		<input type="hidden" id="title" value="<spring:message code='prompt.title.title'/>">
        <input type="hidden" id="noUpdate" value="<spring:message code='error.message.noUpdate'/>">
	</body>
</html>