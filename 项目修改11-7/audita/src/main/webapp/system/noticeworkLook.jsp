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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/noticeEdit.js"></script>
		<title>查看公告管理</title>
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
	    <input type="hidden" id="id" name="id" value="${noticeobject.id}">
	    <input type="hidden" id="frameid" value="${noticemoduelid}">
	     <input type="hidden" id="fabutime" value="${noticeobject.createtime}">
		<div id="mainDiv" style="margin:0px;width: 98%;">
		   <div id="p1" class="easyui-panel" style="background: #fafafa;height: 450px;" iconCls="icon-edit" collapsible="true"  title="公告管理">
		      <div style="border: 1px;height: 100%;">
				<table class="form" style="width: 99%;height: 100%;background-color:#fafafa;margin-top: 0px; " celblpadding="8" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
					<tr>
					  <td colspan="3" align="center">
					            <font size="30"><b>公告栏</b></font>
					  </td>
					</tr>
						<tr>
							<td colspan="3">
							 <center>${noticeobject.title}</center>
							  <center>
								<textarea id="content" class="text" style='overflow:auto; background-attachment: fixed; background-repeat: no-repeat; border-style: solid; 
border-color: #FFFFFF;width: 50%;border: 0px;background-color:#fafafa;'
										rows="13" name="content" readonly="readonly" >
										${noticeobject.content}
								</textarea>
								</center>
								<p style="margin-left: 570px;">公告时间：<span id="createtime"></span></p>
							</td>
							
						</tr>
					</tbody>
				</table>
				</div>
				</div>
			</div>
			</form>
	</body>
</html>