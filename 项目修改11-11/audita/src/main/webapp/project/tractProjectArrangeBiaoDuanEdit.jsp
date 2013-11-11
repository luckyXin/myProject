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
			src="<%=request.getContextPath()%>/js/project/tractProjectBiaoDuanArrangeEdit.js"></script>
		<title>标段创建</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

.mylabel {
	border: 1px solid #ccc;
	background: #E0ECFF;
	text-align: right;
	width: 200px;
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
								<td class="showLabel" align="right">
									建设单位  ：
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
			<div id="p11" class="easyui-panel"
				style="background: #fafafa; margin-bottom: 5px;"
				collapsible="true" title="安排信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
						<input type="hidden" id="arrangeId" name="arrangeId" value="${arrangeInfo.id}"/>
					<table class="form" style="width: 98%" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tr>
							<td class="mylabel">
								主审人员：
							</td>
							<td colspan="5">
								<input type="hidden" id="mainAuditId" name="mainAuditId"
									value="${arrangeInfo.mainAuditId}">
								<input class="easyui-validatebox" readonly="readonly"
									style="width: 200px;" id="mainAuditName" name="mainAuditName"
									required="true" value="${arrangeInfo.mainAuditName}">
							</td>
						</tr>
						<tr>
							<td class="mylabel">
								复核工程师：
							</td>
							<td colspan="5">
								<c:forEach items="${arrangeInfo.governmentEmployee}" var="geId">
									<input type="hidden" class="governmentEmployee"
										id="governmentEmployeeId" name="governmentEmployeeId"
										value="${geId}">
								</c:forEach>
								<input class="easyui-validatebox" readonly="readonly"
									style="width: 200px;" id="governmentEmployee"
									name="governmentEmployee" required="true"
									value="${arrangeInfo.governmentEmployeeName}">
								<font color="red">*最多五个复核工程师 </font>
								<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
							</td>
						</tr>
						<tr>
						    <td align="right" class="mylabel">
								中介事务所 ：
							</td>
							<td colspan="5">
							   <input type="hidden" id="intermediaryId" name="intermediaryId" value="${arrangeInfo.intermediaryId}">
							   <input class="easyui-validatebox"
										style="width: 300px;" id="intermediaryName" name="intermediaryName" readonly="readonly"
										value="${arrangeInfo.intermediaryName}">
							</td>
						</tr>
						<tr>
						    <td class="mylabel">
						                   项目负责人：
						    </td>
						    <td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="intermediaryLinker" name="intermediaryLinker" value="${arrangeInfo.intermediaryLinker}">
							</td>
							<td class="mylabel">
							        负责人联系电话：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="intermediaryTelphone" name="intermediaryTelphone" value="${arrangeInfo.intermediaryTelphone}">
							</td>
							<td class="mylabel">
								项目组成人员：
							</td>
							<td>
							    <input class="easyui-validatebox" style="width: 200px;"
									id="intermediaryTeamName" name="intermediaryTeamName" value="${arrangeInfo.intermediaryTeamName}">
							</td>
						</tr>
						<tr>
						    <td class="mylabel">
								工程开工令时间：
							</td>
							<td>
								<input class="easyui-datebox"
									style="width: 200px;" id="orderStartTime" name="orderStartTime"
									value="${arrangeInfo.orderStartTime}">
							</td>
							<td class="mylabel">
								工程实际开工时间：
							</td>
							<td>
								<input class="easyui-datebox"
									style="width: 200px;" id="startTime" name="startTime"
								 value="${arrangeInfo.startTime}">
							</td>
							<td class="mylabel">
								工程竣工时间：
							</td>
							<td>
								<input class="easyui-datebox"
									style="width: 200px;" id="projectEndTime" name="projectEndTime"
									value="${arrangeInfo.projectEndTime}">
							</td>
						</tr>
						<tr>
						    <td class="mylabel">
								项目审计安排时间：
							</td>
							<td>
								<input class="easyui-datebox"
									style="width: 200px;" id="projectArrangeTime" name="projectArrangeTime"
									value="${arrangeInfo.projectArrangeTime}">
							</td>
							  <td class="mylabel">
								项目审计开始时间：
							</td>
							<td>
								<input class="easyui-datebox"
									style="width: 200px;" id="projectStartTime" name="projectStartTime"
									value="${arrangeInfo.projectStartTime}">
							</td>
							<td class="mylabel">
								项目审计完成时间：
							</td>
							<td>
								<input class="easyui-datebox"
									style="width: 200px;" id="projectCompleteTime" name="projectCompleteTime"
									value="${arrangeInfo.projectCompleteTime}">
							</td>
						</tr>
					</table>
				</div>
			</div>
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>