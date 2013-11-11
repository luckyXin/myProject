<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.InputTree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/adduser.js"></script>
		<title>添加用户管理</title>
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
	    <input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
	    <div id="mainDiv" style="margin: 5px;width: 98%;">
	    <div id="p1" class="easyui-panel" style="background: #fafafa;" iconCls="icon-edit" collapsible="true"  title="用户管理">
			<div style="margin:1px;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
				<table class="form" style="width: 99%" cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
						     <input type="hidden" id="frameid" value="${sessionScope.moduelid}">
							<td align="right" class="label">
								用户账号：
							</td>
							<td>
								<input class="easyui-validatebox" required="true" validType="ishave"   missingMessage="用户账号不能为空" style="width: 200px;" id="userAccount" name="userAccount">
							</td>
							<td align="right" class="label">
								用户姓名 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true" missingMessage="用户姓名不能为空"
									style="width: 200px;" id="username" name="username">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								性别：
							</td>
							<td>
								<select  id="sex" name="sex" style="width: 205px;">
								         <option value="" selected="selected">
										</option>
										<c:forEach items="${listsex}" var="sex">
										   <option value="${sex.id}" >
											    ${sex.dictionaryname}
										  </option>
										</c:forEach>
										
									</select>
							</td>
							<td class="label" align="right">
								出生日期：
							</td>
							<td>
								<input style="width: 200px;" class="easyui-datebox"   editable="false"
								id="birthday" name="birthday">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								联系电话：
							</td>
							<td>
								<input style="width: 200px;" class="easyui-validatebox"  id="telPhone" name="telPhone">
							</td>
							<td class="label" align="right">
								身份证号码：
							</td>
							<td>
								<input style="width: 200px;" class="easyui-validatebox" validType="idCardNo" invalidMessage=身份证号码格式错误" id="cardid" name="cardid">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								复核工程师资质：
							</td>
							<td>
								<select id="qualification" name="qualification" style="width: 205px;">
								         <option value="" selected="selected">
										</option>
										<c:forEach items="${listtype}" var="type">
										   <option value="${type.id}" >
											    ${type.dictionaryname}
										  </option>
										</c:forEach>
									</select>
							</td>
							<td class="label" align="right">
								电子邮箱：
							</td>
							<td>
								<input style="width: 200px;" class="easyui-validatebox" validType="email" invalidMessage="电子邮箱格式错误" id="email" name="email">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								部门：
							</td>
							<td>
							    <select id="deptid" name="deptid" style="width: 205px;">
							    </select>

							</td>
							<td class="label" align="right">
								状态：
							</td>
							<td>
								<select  id="state" name="state" style="width: 205px;">
										<option value="0" selected="selected" >
											启用
										</option>
										<option value="1" >
											禁用
										</option>
									</select>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								类型：
							</td>
							<td>
								<select  id="istype" name="istype" style="width: 205px;">
										<option value="0" selected="selected" >
											系统账号
										</option>
										<option value="1" >
											审计人员
										</option>
									</select>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								备注：
							</td>
							<td colspan="3">
								<textarea id="remark" class="text" style="width: 99%;"
										rows="4" name="remark">
								</textarea>
							</td>
							
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			</form>
	</body>
</html>