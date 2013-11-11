<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>中介机构管理</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/staff/intermediaryagencyEdit.js">
	
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
			<div id="mainDiv" style="margin: 1px; width: 100%; height: 100%">
				<div id="addp1" class="easyui-panel" style="background: #fafafa;"
					iconCls="icon-edit" collapsible="true" title="中介机构">
					<div style="margin: 1px;">
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%;" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tbody style="border: 1px solid #ccc">
							<c:if test="${processType=='add' }">
								<tr>
									<td align="right" class="label">
										中介机构名称：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="intermediaryname"
											name="intermediaryname">
									</td>
									<td align="right" class="label">
										中介机构简称 ：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="referred" name="referred">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										业务类型：
									</td>
									<td>
										<select style="width: 200px;" name="businesstype">
											<option value="">
											</option>
											<c:forEach items="${DicBns}" var="DicBns">
												<option value="${DicBns.id}">
													${DicBns.dictionaryname}
												</option>
											</c:forEach>
										</select>
									</td>
									<td align="right" class="label">
										资质：
									</td>
									<td>
										<select style="width: 200px;" name="deptqualifica">
											<option value="">
											</option>
											<c:forEach items="${DicDep}" var="DicDep">
												<option value="${DicDep.id}">
													${DicDep.dictionaryname}
												</option>
											</c:forEach>
										</select>

									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										法人代表：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 200px;"
											id="legal" name="legal">
									</td>
									<td align="right" class="label">
										注册地 ：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 200px;"
											id="regaddress" name="regaddress">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										详细地址：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 200px;"
											id="address" name="address">
									</td>
									<td align="right" class="label">
										是否政府公开招标 ：
									</td>
									<td>

										<select style="width: 200px;" name="istenderunit">
											<option value="1">
												是
											</option>
											<option value="2">
												否
											</option>
										</select>

									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										中标年度：
									</td>
									<td>
										<select style="width: 200px;" name="bidyear">
											<option value="">
											</option>
											<c:forEach items="${DicYear}" var="DicYear">
												<option value="${DicYear.id}">
													${DicYear.dictionaryname}
												</option>
											</c:forEach>
										</select>
									</td>
									<td align="right" class="label">
										管理机构 ：
									</td>
									<td>
										<select style="width: 200px;" name="manageragency"
											id="manageragency">

										</select>

									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										备注：
									</td>
									<td>
										<input class="easyui-validatebox" style="width: 200px;"
											id="remark" name="remark">
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


							<!-- 分割线 -->
							<c:if test="${processType=='update'}">


								<input type="hidden" id="id" name="id" value="${inter.id}">


								<tr>
									<td align="right" class="label">
										中介机构名称：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											value="${inter.intermediaryname}" style="width: 200px;"
											id="intermediaryname" name="intermediaryname">
									</td>
									<td align="right" class="label">
										中介机构简称 ：
									</td>
									<td>
										<input class="easyui-validatebox" required="true"
											value="${inter.referred}" style="width: 200px;" id="referred"
											name="referred">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										业务类型：
									</td>
									<td>
										<select style="width: 200px;" name="businesstype"
											id="businesstype">
											<option value="">
											</option>
											<c:forEach items="${DicBns}" var="DicBns">
												<c:if test="${DicBns.id == inter.businesstype}">
													<option value="${DicBns.id}" selected="selected">
														${DicBns.dictionaryname}
													</option>
												</c:if>
												<c:if test="${DicBns.id != inter.businesstype}">
													<option value="${DicBns.id}">
														${DicBns.dictionaryname}
													</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
									<td align="right" class="label">
										资质：
									</td>
									<td>

										<select style="width: 200px;" name="deptqualifica"
											id="deptqualifica">
											<option value="">
											</option>
											<c:forEach items="${DicDep}" var="DicDep">
												<c:if test="${DicDep.id == inter.deptqualifica}">
													<option value="${DicDep.id}" selected="selected">
														${DicDep.dictionaryname}
													</option>
												</c:if>
												<c:if test="${DicDep.id != inter.deptqualifica}">
													<option value="${DicDep.id}">
														${DicDep.dictionaryname}
													</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										法人代表：
									</td>
									<td>
										<input class="easyui-validatebox" value="${inter.legal}"
											style="width: 200px;" id="legal" name="legal">
									</td>
									<td align="right" class="label">
										注册地 ：
									</td>
									<td>
										<input class="easyui-validatebox" value="${inter.regaddress}"
											style="width: 200px;" id="regaddress" name="regaddress">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										详细地址：
									</td>
									<td>
										<input class="easyui-validatebox" value="${inter.address}"
											style="width: 200px;" id="address" name="address">
									</td>
									<td align="right" class="label">
										是否政府公开招标 ：
									</td>
									<td>
										<input class="easyui-validatebox"
											value="${inter.istenderunit}" style="width: 200px;"
											id="istenderunit" name="istenderunit">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										中标年度：
									</td>
									<td>

										<select style="width: 200px;" name="bidyear" id="bidyear">
											<option value="">
											</option>
											<c:forEach items="${DicYear}" var="DicYear">
												<c:if test="${DicYear.id == inter.bidyear}">
													<option value="${DicYear.id}" selected="selected">
														${DicYear.dictionaryname}
													</option>
												</c:if>
												<c:if test="${DicYear.id != inter.bidyear}">
													<option value="${DicYear.id}">
														${DicYear.dictionaryname}
													</option>
												</c:if>
											</c:forEach>
										</select>

									</td>
									<td align="right" class="label">
										管理机构 ：
									</td>
									<td>
										<input type="hidden" value="${inter.manageragency}"
											id="manager">
										<select style="width: 200px;" name="manageragency"
											id="manageragency">

										</select>
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										备注：
									</td>
									<td>
										<input class="easyui-validatebox" value="${inter.remark}"
											style="width: 200px;" id="remark" name="remark">
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
