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
			src="<%=request.getContextPath()%>/js/project/tractProjectBiaoDuanCreateEdit.js"></script>
		<title>标段创建</title>
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
				style="background: #fafafa; margin-bottom: 5px; padding-bottom: 5px;"
				collapsible="true" title="基础信息" iconCls="icon-search">
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
							<input type="hidden" id="frameid" value="${id}">
							<input type="hidden" id="coutextPath"
								value="<%=request.getContextPath()%>">
							<input type="hidden" id="id" name="id"
								value="${proejctBiaoDuanInfo.id}" />
							<input type="hidden" id="projectId" name="projectId"
								value="${projectId}" />
							<td align="right" class="label">
								标段名称 ：
							</td>
							<td>
								<input class="easyui-validatebox" validType="ishave"
									required="true" value="${proejctBiaoDuanInfo.biaoDuanName}"
									missingMessage="标段名称不能为空" style="width: 200px;"
									id="biaoDuanName" name="biaoDuanName">
							</td>
							<td align="right" class="label">
								项目概况 ：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="projectGaiKuang" name="projectGaiKuang"
									value="${proejctBiaoDuanInfo.projectGaiKuang}">
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								监理单位 ：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="supervisorUtil" name="supervisorUtil"
									value="${proejctBiaoDuanInfo.supervisorUtil}">
							</td>
							<td class="label" align="right">
								建管单位 ：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="buildManageUtil" name="buildManageUtil"
									value="${proejctBiaoDuanInfo.buildManageUtil}">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								勘察单位 ：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="prospectUtil" name="prospectUtil"
									value="${proejctBiaoDuanInfo.prospectUtil}">
							</td>
							<td class="label" align="right">
								建设规模 ：
							</td>
							<td>
								<input type="text" style="width: 200px;"
									value="${proejctBiaoDuanInfo.buildContent}" id="buildContent"
									name="buildContent">
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								建设单位 ：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="buildUtil" name="buildUtil"
									value="${proejctBiaoDuanInfo.buildUtil}">
							</td>
							<td class="label" align="right">
								设计单位 ：
							</td>
							<td>
								<input type="text" style="width: 200px;" id="designUtil"
									name="designUtil" value="${proejctBiaoDuanInfo.designUtil}">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								施工单位 ：
							</td>
							<td>
								<input class="easyui-validatebox"
									value="${proejctBiaoDuanInfo.constructUtil}"
									style="width: 200px;" id="constructUtil" name="constructUtil">
							</td>
							<td class="label" align="right">
								实际开工时间 ：
							</td>
							<td>
								<input class="easyui-datebox"
									value="${proejctBiaoDuanInfo.reallyStartTime}"
									style="width: 200px;" id="reallyStartTime"
									name="reallyStartTime">
							</td>
						</tr>
						<tr>
						    <td class="label" align="right">
								预算控制价(万元) ：
							</td>
							<td>
								<input class="easyui-numberbox" precision='6' maxlength="20"
									value="${proejctBiaoDuanInfo.preAuditMoney}"
									style="width: 200px;" id="preAuditMoney" name="preAuditMoney">
							</td>
							<td class="label" align="right">
								中标合同价(万元) ：
							</td>
							<td>
								<input class="easyui-numberbox" precision='6' maxlength="20"
									value="${proejctBiaoDuanInfo.zhongBiaoMoney}"
									style="width: 200px;" id="zhongBiaoMoney" name="zhongBiaoMoney">
							</td>
						</tr>
						<tr>
							<td class="showLabel" align="right">
								含预留金额(万元) ：
							</td>
							<td colspan="3">
								<input class="easyui-numberbox" precision='6' maxlength="20"
									value="${proejctBiaoDuanInfo.zhongBiaoObligateMoney}"
									style="width: 200px;" id="zhongBiaoObligateMoney" name="zhongBiaoObligateMoney">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>