<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_edit.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/default/easyui.css" type="text/css"></link>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/DPlug.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/project/proConferencedataAdd.js"></script>
		<title>会议纪要录入管理</title>
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
		<div id="mainDiv">
			<div style="margin:5px;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
				<table class="form" style="width: 99%" cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
					<input type="hidden" id="frameid" value="${requestScope.proconferencemoduelId}">
				    <input type="hidden" id="datapreId" name="datapreId" value="${requestScope.datapreIds}">
						<tr>
							<td align="right" class="label" style="width:5%;">
								会议日期 ：
							</td>
							<td>
							     <input class="easyui-datebox"  required="true"   missingMessage="该项为必输项"   style="width: 500px;" editable="false" id="conferencetime" name="conferencetime">
								
							</td>
						</tr>
				
						<tr>
							<td class="label" align="right" style="width:5%;">
								参会地点：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="该项为必输项" style="width: 500px;" id="conferenceaddress" name="conferenceaddress">
							</td>
						</tr>
						<tr>
							<td class="label" align="right" style="width:5%;">
								参会单位：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="该项为必输项" style="width: 500px;" id="conferenunit" name="conferenunit">
							</td>
						</tr>
						<tr>
							<td class="label" align="right" style="width:5%;">
								参会人员：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="该项为必输项" style="width: 500px;" id="conferenpeople" name="conferenpeople">
							</td>
						</tr>
						<tr>
							<td class="label" align="right" style="width:5%;">
								主持人：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="该项为必输项" style="width: 500px;" id="mainpeople" name="mainpeople">
							</td>
						</tr>
						<tr>
							<td class="label" align="right" style="width:5%;">
								会议议题：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"   missingMessage="该项为必输项" style="width: 500px;" id="conferentitle" name="conferentitle">
							</td>
						</tr>
						<tr>
							<td class="label" align="right" style="width:5%;">
								会议达成意见：
							</td>
							<td>
								<textarea id="conferencontent" class="text" style="width: 99%;" rows="4" name="conferencontent">
								</textarea>
							</td>
							
						</tr>
					</tbody>
				</table>
			</div>
			</form>
	</body>
</html>