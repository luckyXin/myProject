<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/tractProjectMonthReportInfoEdit.js"></script>
		<title>月报添加</title>
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
				style="background: #fafafa; height: 350px; margin-bottom: 5px;"
				collapsible="true" title="月报基础信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
				</div>
				<table class="form" style="width: 99%" id="mytableid"
					cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
							<input type="hidden" id="frameId" value="${id}">
							<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
							<input type="hidden" id="id" name="id" value="${monthReportInfo.id}" />
							<td align="right" class="label">
								月报时间 ：
							</td>
							<td>
								<input class="easyui-datebox" required="true"
									value="${monthReportInfo.createTime}" style="width: 200px;"
									id="createTime" name="createTime">
							</td>
							<td align="right" class="label">
								录入人员 ：
							</td>
							<td>
								<c:if test="${not empty monthReportInfo.createUserAccount }">
									<input class="easyui-validatebox"
										value="${monthReportInfo.createUserAccount}"
										style="width: 200px;" id="createUserAccount"
										name="createUserAccount" readonly="readonly">
								</c:if>
								<c:if test="${empty monthReportInfo.createUserAccount}">
									<input class="easyui-validatebox" value="${nowUser}"
										style="width: 200px;" id="createUserAccount"
										name="createUserAccount" readonly="readonly">
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								当月完成产值(万元)：
							</td>
							<td>
								<input class="easyui-numberbox" style="width: 200px;" precision='2'
									id="nowMonthCompleteValue" name="nowMonthCompleteValue"
									value="${monthReportInfo.nowMonthCompleteValue}" onblur="changeNowMonthCompleteValue();">
							</td>
							<td class="label" align="right">
								累计完成产值(万元)：
							</td>
							<td>
								<input class="easyui-numberbox" style="width: 200px;" precision='2'
									id="totalCompleteValue" name="totalCompleteValue"
									value="${monthReportInfo.totalCompleteValue}">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								当月支付产值(万元)：
							</td>
							<td>
								<input class="easyui-numberbox" style="width: 200px;" precision='2'
									id="nowMonthPayValue" name="nowMonthPayValue"
									value="${monthReportInfo.nowMonthPayValue}" onblur="changeNowMonthPayValue();">
							</td>
							<td class="label" align="right">
								累计已付工程款(万元)：
							</td>
							<td>
								<input class="easyui-numberbox" style="width: 200px;" precision='2'
									id="addPayProjectMoney" name="addPayProjectMoney"
									value="${monthReportInfo.addPayProjectMoney}">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								工程形象进度 ：
							</td>
							<td colspan="3">
								<textarea rows="6" cols="20" style="width: 550px;" id="projectImagePlan" name="projectImagePlan">
								${monthReportInfo.projectImagePlan}
								</textarea>
							</td>
							
						</tr>
						<tr>
							<td class="label" align="right">
								存在问题及解决方案 ：
							</td>
							<td colspan="3">
								<textarea rows="6" cols="20" style="width: 550px;" id="existProblem" name="existProblem">
								${monthReportInfo.existProblem}
								</textarea>
							</td>
							
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>