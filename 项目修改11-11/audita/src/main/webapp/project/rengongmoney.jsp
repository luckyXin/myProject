<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/rengongmoney.js"></script>
		<title>人工费用调整信息</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

.cancel a {
	background-color: red;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; height: 240px; margin-bottom: 5px;"
				collapsible="true" title="人工费用调整信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
				</div>
				<input type="hidden" id="frameid" value="${id}">
				<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
				<input type="hidden" id="id" name="id" value="${adjustment.id}" />
				<input type="hidden" id="policyChangeId" name="policyChangeId" value="${policyChangeId}" />
				<table class="form" style="width: 99%" id="mytableid"
					cellpadding="0" cellspacing="0">
					<tr>
						<td class="label"> 
							调整文号： 
						</td>
						<td>
							<input type="text" id="adjustmentwh" name="adjustmentwh"
								style="width: 200px;" value="${adjustment.adjustmentwh }" />
						</td>
						<td class="label">
							定额人工费：
						</td>
						<td>
							<input type="text" id="delabourmoney" name="delabourmoney"
								style="width: 200px;" value="${adjustment.delabourmoney}" />
						</td>
					</tr>
					
					<tr>
						<td class="label">
							调整费率：
						</td>
						<td>
							<input type="text" id="tzlv" name="tzlv"
								style="width: 200px;" value="${adjustment.tzlv}" />
						</td>
						<td class="label">
							人工费调整金额：
						</td>
						<td>
							<input type="text" id="mantzmoney" name="mantzmoney"
								style="width: 200px;" value="${adjustment.mantzmoney}" />
						</td>
						
					</tr>
					<tr>
						<td class="label">
							调整时间：
						</td>
						<td>
						     <input type="hidden" id="tzdate" value="${adjustment.tztime}">
						     <input class="easyui-datebox"    style="width: 200px;" editable="false" id="tztime" name="tztime">
						</td>
					</tr>
					</table>
			</div>
			</form>
	</body>
</html>