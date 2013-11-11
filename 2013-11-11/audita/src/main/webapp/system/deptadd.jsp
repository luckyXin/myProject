<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.InputTree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/deptadd.js"></script>
		<title>添加机构管理</title>
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
		<div id="mainDiv" style="margin:5px;">
		  <div id="addp1" class="easyui-panel" style="background: #fafafa;" iconCls="icon-edit" collapsible="true" title="机构管理">
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
								机构名称 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="机构名称不能为空" style="width: 200px;" id="deptname" name="deptname">
							</td>
							<td align="right" class="label">
								上级机构 ：
							</td>
							<td>
								<select  id="pid" name="pid" style="width: 205px;">
								         <option value="">
										</option>
										
									
										
									</select>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								备注：
							</td>
							<td colspan="3">
								<textarea id="remark" class="text" style="width: 100%;"
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