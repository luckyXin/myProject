<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/staff/constructadd.js"></script>
		<title>添加施工企业管理</title>
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
		<div id="mainDiv" style="margin:3px;" style="width: 99%;">
		<div id="addp1" class="easyui-panel" style="background: #fafafa;" iconCls="icon-edit" collapsible="true" title="施工企业">
			<div style="margin:1px;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
				<table class="form" style="width: 99%" id="mytableid" cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
						     <input type="hidden" id="frameid" value="${sessionScope.moduelid}">
							<td align="right" class="label">
								企业名称 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="企业名称不能为空" style="width: 200px;" id="constructname" name="constructname">
							</td>
							<td align="right" class="label">
								企业简称 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="企业简称不能为空" style="width: 200px;" id="deptreferred" name="deptreferred">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								企业类型：
							</td>
							<td >
							<input class="easyui-validatebox" style="width: 200px;" id="enterprisetype" name="enterprisetype">
							
							</td>
							<td align="right" class="label">
								组织机构代码 ：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 200px;" id="deptcode" name="deptcode">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								企业注册地 ：
							</td>
							<td >
								<input class="easyui-validatebox"  style="width: 200px;" id="regaddress" name="regaddress">
							</td>
							<td align="right" class="label">
								企业注册资金(万元) ：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 200px;" id="regfunds" name="regfunds">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								企业法人代表 ：
							</td>
							<td >
								<input class="easyui-validatebox"  style="width: 200px;" id="legal" name="legal">
							</td>
							<td align="right" class="label">
								企业资质 ：
							</td>
							<td>
								<select  id="deptqualifica" name="deptqualifica" style="width: 205px;">
								         <option value="" selected="selected">
										</option>
										<c:forEach items="${listdeptqualifica}" var="deptqualifica">
										   <option value="${deptqualifica.id}" >
											    ${deptqualifica.dictionaryname}
										  </option>
										</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								电子邮箱 ：
							</td>
							<td >
								<input class="easyui-validatebox"  validType="email" invalidMessage="电子邮箱格式错误" style="width: 200px;" id="email" name="email">
							</td>
							<td align="right" class="label">
								企业地址 ：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 200px;" id="address" name="address">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								是否区比选库企业 ：
							</td>
							<td >
								<select  id="ischose" name="ischose" style="width: 205px;">
								        <option value="1">
								                        是
										</option>
										 <option value="0">
								                        否
										</option>
								</select>
							</td>
							<td align="right" class="label">
								状态 ：
							</td>
							<td>
								<select  id="state" name="state" style="width: 205px;">
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
							<td class="label" align="right">
								备注 ：
							</td>
							<td colspan="3">
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
								<input type="text"  style="width: 150px;" id="linkname8" name="linkname8">
							</td>
							<td align="right" class="label">
								第一联系人电话 ：
							</td>
							<td>
								<input type="text"  style="width: 140px;" id="linktellphone8" name="linktellphone8">
								<a href="javascript:void(0);" id="addrow" class="easyui-linkbutton"  iconCls="icon-add" style="margin: 1px;">添加</a>
				                <a href="javascript:void(0);"  class="easyui-linkbutton" id="delrow" iconCls="icon-remove"  style="margin: 1px;">删除</a>
							</td>
						</tr>
						</tbody>
				</table>
			</div>
			</div>
			</form>
	</body>
</html>