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
			src="<%=request.getContextPath()%>/js/project/tractProjectArrangeBiaoDuanqingbiaoEdit.js"></script>
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
		<div id="mainDiv">
				<div id="p10" class="easyui-panel"
				style="background: #fafafa; height: 150px; margin-bottom: 5px;"
				collapsible="true" title="标段基本信息" iconCls="icon-search">
					<table class="form" style="width: 99%" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<input type="hidden" id="frameid" value="${id}">
								<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
								<input type="hidden" id="biaoDuanId" name="biaoDuanId" value="${proejctBiaoDuanInfo.id}"/>
								<td align="right" class="showLabel">
									标段名称 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.biaoDuanName}
								</td>
								<td align="right" class="showLabel">
									项目概况 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.projectGaiKuang}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									监理单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.supervisorUtil}
								</td>
								<td class="showLabel" align="right">
									建管单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildManageUtil}
								</td>
							</tr>
							<tr>
								<td class="showLabel" align="right">
									勘察单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.prospectUtil}
								</td>
								<td class="showLabel" align="right">
									建设规模 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildContent}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									建设单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildUtil}
								</td>
								<td class="showLabel" align="right">
									设计单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.designUtil}
								</td>
							</tr>
							<tr>
							    <td class="showLabel" align="right">
									施工单位 ：
								</td>
								<td>
								    ${proejctBiaoDuanInfo.constructUtil}
								</td>
								<td class="showLabel" align="right">
									预算控制价(万元) ：
								</td>
								<td>
								   ${proejctBiaoDuanInfo.preAuditMoney}
								</td>
							</tr>
							<tr>
								<td class="showLabel" align="right">
									中标合同价(万元) ：
								</td>
								<td >
								    ${proejctBiaoDuanInfo.zhongBiaoMoney}
								</td>
								<td class="showLabel" align="right">
									实际开工时间 ：
								</td>
								<td >
								    ${proejctBiaoDuanInfo.reallyStartTime} 
								</td>
							</tr>
							<tr>
								<td class="showLabel" align="right">
									含预留金额(万元) ：
								</td>
								<td colspan="3">
									<input class="easyui-numberbox" precision='6' maxlength="20"
										value="${proejctBiaoDuanInfo.zhongBiaoObligateMoney}"
										style="width: 200px;" id="zhongBiaoObligateMoney"
										name="zhongBiaoObligateMoney">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			<c:if test="${not empty qingbiao}">
				<table id="grid"></table>
			</c:if>
				<div id="p11" class="easyui-panel"
					style="background: #fafafa; height: 300px; margin-bottom: 5px;"
					collapsible="true" title="清标信息" iconCls="icon-search">
					<div style="margin: 1px;">
						<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
							iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
						<input type="hidden" id="id" name="id" value="${qingbiao.id}" />
						<table class="form" style="width: 99%" id="mytableid"
							cellpadding="0" cellspacing="0">
							<tr>
								<td class="label">
									清标后总金额：
								</td>
								<td colspan="3">
									<input type="text" value="${qingbiao.afterMoney}" class="easyui-numberbox" precision='2'
										style="width: 400px;" id="afterMoney" name="afterMoney">
								</td>
							</tr>
							<!--<tr>
								<td class="label">
									计量单位：
								</td>
								<td>
									<input type="text" value="${qingbiao.measureUtil}"
										style="width: 150px;" id="measureUtil" name="measureUtil">
								</td>
								<td class="label">
									工程数量：
								</td>
								<td>
									<input type="text" value="${qingbiao.projectCount}"
										style="width: 150px;" id="projectCount" name="projectCount">
								</td>
							</tr>
							<tr>
								<td class="label">
									综合单价最高限价：
								</td>
								<td>
									<input type="text" value="${qingbiao.maxUtilPrice}" class="easyui-numberbox" precision='2'
										style="width: 150px;" id="maxUtilPrice" name="maxUtilPrice">
								</td>
								<td class="label">
									投标综合单价：
								</td>
								<td>
									<input type="text" value="${qingbiao.bidUtilPrice}" class="easyui-numberbox" precision='2'
										style="width: 150px;" id="bidUtilPrice" name="bidUtilPrice">
								</td>
							</tr>
							<tr>
								<td class="label">
									高于控制价120%的投标价：
								</td>
								<td>
									<input type="text" value="${qingbiao.exceedOneTwenty}"
										style="width: 150px;" id="exceedOneTwenty" class="easyui-numberbox" precision='2'
										name="exceedOneTwenty">
								</td>
								<td class="label">
									低于控制价80%的投标价：
								</td>
								<td>
									<input type="text" value="${qingbiao.underEighty}" class="easyui-numberbox" precision='2'
										style="width: 150px;" id="underEighty" name="underEighty">
								</td>
							</tr>
							<tr>
								<td class="label">
									高出控价总金额（投标价-控价）万元：
								</td>
								<td>
									<input type="text" value="${qingbiao.exceedControllerPrice}"
										style="width: 150px;" id="exceedControllerPrice" class="easyui-numberbox" precision='2'
										name="exceedControllerPrice">
								</td>
								<td class="label">
									低出控价总金额（投标价-控价）万元：
								</td>
								<td>
									<input type="text" value="${qingbiao.underControllerPrice}"
										style="width: 150px;" id="underControllerPrice" class="easyui-numberbox" precision='2'
										name="underControllerPrice">
								</td>
							</tr>
							--><tr id="files">
								<td class="label" align="right">
									清标资料：
								</td>
								<td colspan="3" align="left">
									<input type="file" style="width: 300px;" class="text" id="file"
										name="uploadFile" value="浏览" />
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="addfileqingbiao();return false;">增加</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>