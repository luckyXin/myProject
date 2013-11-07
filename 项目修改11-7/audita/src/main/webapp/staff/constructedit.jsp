<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.audit.common.AuditStringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_edit.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/default/easyui.css" type="text/css"></link>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/staff/constructedit.js"></script>
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
	    <input type="hidden" id="frameid" value="${sessionScope.moduelid}" />
        <input type="hidden" id="id" name="id" value="${construct.id}" />
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
						     
						     
							<td align="right" class="label">
								企业名称 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true" value="${construct.constructname}"   missingMessage="企业名称不能为空" style="width: 200px;" id="constructname" name="constructname">
							</td>
							<td align="right" class="label">
								企业简称 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"  value="${construct.deptreferred}"  missingMessage="企业简称不能为空" style="width: 200px;" id="deptreferred" name="deptreferred">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								企业类型：
							</td>
							<td >
							<input class="easyui-validatebox" style="width: 200px;" value="${construct.enterprisetype}"  id="enterprisetype" name="enterprisetype">
							
							</td>
							<td align="right" class="label">
								组织机构代码 ：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 200px;" value="${construct.deptcode}" id="deptcode" name="deptcode">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								企业注册地 ：
							</td>
							<td >
								<input class="easyui-validatebox"  value="${construct.regaddress}" style="width: 200px;" id="regaddress" name="regaddress">
							</td>
							<td align="right" class="label">
								企业注册资金(万元) ：
							</td>
							<td>
								<input class="easyui-validatebox"  value="${construct.regfunds}" style="width: 200px;" id="regfunds" name="regfunds">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								企业法人代表 ：
							</td>
							<td >
								<input class="easyui-validatebox" value="${construct.legal}"  style="width: 200px;" id="legal" name="legal">
							</td>
							<td align="right" class="label">
								企业资质 ：
							</td>
							<td>
								<select  id="deptqualifica" name="deptqualifica" style="width: 205px;">
								     <option value="">
                                      </option>
										<c:forEach items="${listdeptqualifica}" var="deptqualifica">
										   <c:if test="${deptqualifica.id == construct.deptqualifica}">
												<option value="${deptqualifica.id}"  selected="selected">
											    ${deptqualifica.dictionaryname}
										           </option>
											</c:if>
											<c:if test="${deptqualifica.id != construct.deptqualifica}">
												<option value="${deptqualifica.id}" >
											    ${deptqualifica.dictionaryname}
										           </option>
											</c:if>
										</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								电子邮箱 ：
							</td>
							<td >
								<input class="easyui-validatebox" value="${construct.email}"  validType="email" invalidMessage="电子邮箱格式错误" style="width: 200px;" id="email" name="email">
							</td>
							<td align="right" class="label">
								企业地址 ：
							</td>
							<td>
								<input class="easyui-validatebox" value="${construct.address}"  style="width: 200px;" id="address" name="address">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								是否区比选库企业 ：
							</td>
							<td >
								<select  id="ischose" name="ischose" style="width: 205px;">
								        <c:if test="${construct.ischose == 0}">
											<option value="0" selected="selected">
												   否
											</option>
											<option value="1">
												  是
											</option>
										</c:if>
										<c:if test="${construct.ischose == 1}">
											<option value="0">
												   否
											</option>
											<option value="1" selected="selected">
												 是
											</option>
										</c:if>
								</select>
							</td>
							<td align="right" class="label">
								状态 ：
							</td>
							<td>
								<select  id="state" name="state" style="width: 205px;">
								      <c:if test="${construct.state == 0}">
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
										<c:if test="${construct.state == 1}">
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
										 <c:if test="${construct.state == 2}">
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
							<td class="label" align="right">
								备注 ：
							</td>
							<td colspan="3">
									<textarea id="remark" class="text" style="width: 100%;"
										rows="4" name="remark">
										${construct.remark}
								</textarea>
							</td>
							
						</tr>
					<c:if test="${empty listlink }">
					       <tr>
							<td class="label" align="right">
							               第一联系人：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 150px;" value="${link.linkname }" id="linkname8" name="linkname8">
							</td>
							<td align="right" class="label">
								第一联系人电话 ：
							</td>
							<td>
								<input type="text" style="width: 140px;"  value="${link.linktellphone}" id="linktellphone8" name="linktellphone8">
								<a href="javascript:void(0);" id="addrow" class="easyui-linkbutton"  iconCls="icon-add" style="margin: 1px;">添加</a>
				                <a href="javascript:void(0);"  class="easyui-linkbutton" id="delrow" iconCls="icon-remove"  style="margin: 1px;">删除</a>
							</td>
						   </tr>
					    
					</c:if>	
					<c:if test="${not empty listlink }">
					   <c:forEach items="${listlink}" var="link" varStatus="i">
						<c:if test="${i.count == 1}">
						   <tr>
							<td class="label" align="right">
							               第一联系人：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 150px;" value="${link.linkname }" id="linkname8" name="linkname8">
							</td>
							<td align="right" class="label">
								第一联系人电话 ：
							</td>
							<td>
								<input type="text" style="width: 140px;"  value="${link.linktellphone}" id="linktellphone8" name="linktellphone8">
								<a href="javascript:void(0);" id="addrow" class="easyui-linkbutton"  iconCls="icon-add" style="margin: 1px;">添加</a>
				                <a href="javascript:void(0);"  class="easyui-linkbutton" id="delrow" iconCls="icon-remove"  style="margin: 1px;">删除</a>
							</td>
						   </tr>
						  </c:if>
						  <c:if test="${i.count != 1}">
						  <tr>
							<td class="label" align="right">
							               第${link.number}联系人：
							</td>
							<td>
								<input class="easyui-validatebox"  style="width: 150px;" value="${link.linkname }" id="linkname${i.count+7}" name="linkname${i.count+7}">
							</td>
							<td align="right" class="label">
								第${link.number}联系人电话 ：
							</td>
							<td>
								<input type="text" style="width: 140px;"  value="${link.linktellphone}" id="linktellphone${i.count+7}" name="linktellphone${i.count+7}">
							</td>
						   </tr>
						   </c:if>
					</c:forEach>
					</c:if>	
						
					
					</tbody>
				</table>
			</div>
			</div>
			</form>
	</body>
</html>