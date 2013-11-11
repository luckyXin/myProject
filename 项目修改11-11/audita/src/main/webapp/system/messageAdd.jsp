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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/messageAdd.js"></script>
		<title>添加消息管理</title>
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
	      <input type="hidden" id="frameid" value="${messagemoduelid}">
		<div id="mainDiv" style="margin:3px;width: 98%;">
		<div id="p1" class="easyui-panel"
				style="background: #fafafa; " collapsible="true"
				title="消息管理" iconCls="icon-tip">
			<div style="margin:1px;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save">发送消息</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
		   <div style="float: left;width:85%">
				<table class="form" style="width: 85%" cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
					    <tr>
							<td align="right" class="label" style="width:10%;">
								收件人：
							</td>
							<td colspan="3">
							    <input type="hidden" id="acceptuser" name="acceptuser">
								<input class="easyui-validatebox" readonly="readonly"   style="width: 500px;" id="username" name="username">
							</td>
							<td rowspan="5">
					          <select multiple="multiple" style="height: 400px;width:300px;" id="selectuser" onclick="clickuser()">
					          <c:forEach items="${listuser}" var="user">
					               <option value="${user.id}">${user.username}</option>
					          </c:forEach>
					          </select>
							</td>
						</tr>
						<tr>
							<td align="right" class="label" style="width:10%;">
								附件：
							</td>
							<td colspan="3">
									<input type="file" style="width: 500px;" class="text" id="file" name="uploadFile" value="浏览" /> 
							</td>
						</tr>
						<tr>
							<td align="right" class="label" style="width:10%;">
								消息标题：
							</td>
							<td colspan="3">
								<input class="easyui-validatebox" required="true"   missingMessage="消息标题不能为空" style="width: 500px;" id="messagetitle" name="messagetitle">
							</td>
						</tr>
						<tr>
							<td class="label" align="right"  style="width:10%;">
								消息内容：
							</td>
							<td colspan="3">
								<textarea id="messagecontent" class="text" style="width: 100%;"
										rows="20" name="messagecontent">
								</textarea>
							</td>
							
						</tr>
						<tr>
							<td class="label" align="right"  style="width:10%;">
								发送日期：
							</td>
							<td colspan="3">
								<input style="width: 200px;" type="text" readonly="readonly"
								id="messagetime" name="messagetime">
							</td>
						</tr>
						
					</tbody>
				</table>
				</div><!--
				<div style="float: left;" style="height: 100%;" >
				             <div>通讯录人员</div>
					          <select multiple="multiple" style="height: 300px;;width:120px;" id="selectuser" onclick="clickuser()">
					          <c:forEach items="${listuser}" var="user">
					               <option value="${user.id}">${user.username}</option>
					          </c:forEach>
					          </select>
				</div>
			--></div>
			</div>
			</form>
	</body>
</html>