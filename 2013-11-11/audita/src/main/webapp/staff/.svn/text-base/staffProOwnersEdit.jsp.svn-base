<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>项目业主管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/staff/staffProOwnerEdit.js">
	
</script>
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
			<div id="mainDiv" style="width: 99%;">
				<div id="p1" class="easyui-panel"
					style="background: #fafafa; height: 300px; margin-bottom: 1px;"
					collapsible="true" title="项目业主" iconCls="icon-edit">
					<div style="margin: 1px;">
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 98%" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tbody style="border: 1px solid #ccc">
							<c:if test="${processType=='add' }">
								<tr>
									<td align="right" class="label">
										业主名称：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="ownerName" name="ownerName">
									</td>
									<td align="right" class="label">
										业主简称 ：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="ownerReferred" name="ownerReferred">
									</td>
								</tr>
								<tr>
									<td class="label" align="right">
										单位类型：
									</td>
									<td>
										<select style="width: 200px;" name="unitType">
											<option value="">
											</option>
											<c:forEach items="${unitTypes}" var="unit">
												<option value="${unit.id}">
													${unit.dictionaryname}
												</option>
											</c:forEach>
										</select>
									</td>
									<td class="label">
										状态 ：
									</td>
									<td>
										<select style="width: 200px;" name="state">
											<option value="0">
												启用
											</option>
											<option value="1">
												禁用
											</option>
											<option value="2">
												删除
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										主要单位：
									</td>
									<td>
										<select style="width: 200px;" name="isMainUnit">
											<option value="0">
												否
											</option>
											<option value="1">
												是
											</option>
										</select>
									</td>
									<td class="label" align="right">
										备注 ：
									</td>
									<td>
										<textarea id="remark" class="text" style="width: 100%;"
											rows="4" name="remark">
								</textarea>
									</td>
								</tr>
								<tr>
									<td class="label" align="right">
										第一联系人：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 110px;"
											id="linkname" name="linkname">
									</td>
									<td align="right" class="label">
										第一联系人电话 ：
									</td>
									<td>
										<input class="easyui-validatebox" validType="mobile"
											invalidMessage="联系电话格式错误" style="width: 110px;"
											id="linktellphone" name="linktellphone">
										<a href="javascript:void(0);" id="addrow"
											class="easyui-linkbutton" iconCls="icon-add"
											style="margin: 1px;">添加行</a>
										<a href="javascript:void(0);" class="easyui-linkbutton"
											id="delrow" iconCls="icon-remove" style="margin: 1px;">删除行</a>
									</td>
								</tr>
							</c:if>
							<c:if test="${processType=='update'}">
								<tr>
									<td align="right" class="label">
										<input type="hidden" id="id" name="ownerId"
											value="${projectOwner.id}">
										业主名称：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="ownerName" name="ownerName"
											value="${projectOwner.ownerName}">
									</td>
									<td align="right" class="label">
										业主简称 ：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="ownerReferred" name="ownerReferred"
											value="${projectOwner.ownerReferred}">
									</td>
								</tr>
								<tr>
									<td class="label" align="right">
										单位类型：
									</td>
									<td>
										<select style="width: 200px;" name="unitType" id="unitType">
											<option value="">
											</option>
											<c:forEach items="${unitTypes}" var="unit">
												<c:if test="${unit.id == projectOwner.unitTypeId}">
													<option value="${unit.id}" selected="selected">
														${unit.dictionaryname}
													</option>
												</c:if>
												<c:if test="${unit.id != projectOwner.unitTypeId}">
													<option value="${unit.id}">
														${unit.dictionaryname}
													</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
									<td class="label">
										状态 ：
									</td>
									<td>
										<select style="width: 200px;" name="state" id="state">
											<c:if test="${projectOwner.state == '0'}">
												<option value="0" selected="selected">
													启用
												</option>
												<option value="1">
													禁用
												</option>
												<option value="2">
													删除
												</option>
											</c:if>
											<c:if test="${projectOwner.state == '1'}">
												<option value="0">
													启用
												</option>
												<option value="1" selected="selected">
													禁用
												</option>
												<option value="2">
													删除
												</option>
											</c:if>
											<c:if test="${projectOwner.state == '2'}">
												<option value="0">
													启用
												</option>
												<option value="1">
													禁用
												</option>
												<option value="2" selected="selected">
													删除
												</option>
											</c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										是否政府投资项目建设主要单位：
									</td>
									<td>
										<select style="width: 200px;" name="isMainUnit"
											id="isMainUnit">
											<c:if test="${projectOwner.isMainUnit == '0'}">
												<option value="0" selected="selected">
													否
												</option>
												<option value="1">
													是
												</option>
											</c:if>
											<c:if test="${projectOwner.isMainUnit == '1'}">
												<option value="0">
													否
												</option>
												<option value="1" selected="selected">
													是
												</option>
											</c:if>
										</select>
									</td>
									<td class="label" align="right">
										备注 ：
									</td>
									<td>
										<textarea id="remark" class="text" style="width: 100%;"
											rows="4" name="remark">
								</textarea>
									</td>
								</tr>
								<tr>
									<td class="label" align="right">
										第一联系人：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 110px;"
											id="linkname" name="linkname" value="${firstLink.linkname}">
									</td>
									<td align="right" class="label">
										第一联系人电话 ：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 110px;"
											id="linktellphone" name="linktellphone"
											value="${firstLink.linktellphone}">
										<a href="javascript:void(0);" id="addrow"
											class="easyui-linkbutton" iconCls="icon-add"
											style="margin: 1px;">添加</a>
										<a href="javascript:void(0);" class="easyui-linkbutton"
											id="delrow" iconCls="icon-remove" style="margin: 1px;">删除</a>
									</td>
								</tr>
								<c:forEach items="${links}" var="link">
									<tr>
										<td class="label" align="right">
											第${link.number}联系人：
										</td>
										<td>
											<input class="easyui-validatebox" style="width: 110px;"
												id="linkname" name="linkname" value="${link.linkname}">
										</td>
										<td align="right" class="label">
											第${link.number}联系人电话 ：
										</td>
										<td>
											<input class="easyui-validatebox" style="width: 110px;"
												id="linktellphone" name="linktellphone"
												value="${link.linktellphone}">
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<input type="hidden" id="coutextPath"
						value="<%=request.getContextPath()%>">
					<input type="hidden" id="fid" value="${id}">
					<input type="hidden" id="processType" value="${processType}">
					<input type="hidden" id="url" value="${url}">
					<input type="hidden" id="noUpdate"
						value="<spring:message code='error.message.noUpdate'/>">
					<input type="hidden" id="title"
						value="<spring:message code='prompt.title.title'/>">
				</div>
			</div>
		</form>
	</body>
</html>
