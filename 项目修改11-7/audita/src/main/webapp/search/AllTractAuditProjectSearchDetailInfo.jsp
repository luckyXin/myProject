<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>跟踪项目明细展示</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/search/AllTractAuditProjectSearchDetailInfo.js"></script>
		<style type="text/css">
body {
	margin: 10px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

form .label {
	padding-right: 80px;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="mainDiv">
				<div id="p1" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px; padding: 5px;"
					collapsible="true" title="跟踪项目明细" iconCls="icon-redo">
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
					<c:if test="${not empty projectBaseInfo.projectId}">
						<table class="form"
							style="width: 99%; border-style: none; margin: 0px;"
							cellpadding="3" cellspacing="0">
							<tr>
								<td colspan="4" class="labelbase">
									<b>项目资料信息</b>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									项目名称 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.projectName}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									立项文号 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.projectNo}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									立项时间 ：
								</td>
								<td>
									${projectBaseInfo.projectCreateTime}
								</td>
								<td align="right" class="showLabel">
									立项批复金额 ：
								</td>
								<td>
									${projectBaseInfo.sentAuditMoney}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									概算文号 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.budgetCode}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									概算批复金额 ：
								</td>
								<td>
									${projectBaseInfo.budgetMoney}
								</td>
								<td align="right" class="showLabel">
									直接费用(元) ：
								</td>
								<td>
									${projectBaseInfo.directMoney}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									项目业主 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.ownerName}
								</td>

							</tr>
							<tr>
								<td align="right" class="showLabel">
									联系人 ：
								</td>
								<td>
									${projectBaseInfo.proownerLink}
								</td>
								<td align="right" class="showLabel">
									联系电话 ：
								</td>
								<td>
									${projectBaseInfo.proownerTelphone}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									建设规模 ：
								</td>
								<td>
									${projectBaseInfo.jianSheGuiMo}
								</td>
								<td align="right" class="showLabel">
									建设时间 ：
								</td>
								<td>
									${projectBaseInfo.jianSheTime}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									项目备注 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.projectRemark}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									立项用户 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.createUserAccount}
								</td>
							</tr>

							<tr>
								<td colspan="4" class="labelbase">
									<b>标段信息</b>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									标段名称 ：
								</td>
								<td colspan="3">
									<input type="hidden" id="biaoDuanId" name="biaoDuanId"
										value="${projectBaseInfo.biaoDuanId}">
									${projectBaseInfo.biaoDuanName}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									项目概况 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.projectGaiKuang}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									监理单位 ：
								</td>
								<td>
									${projectBaseInfo.supervisorUtil}
								</td>
								<td align="right" class="showLabel">
									建管单位 ：
								</td>
								<td>
									${projectBaseInfo.buildManageUtil}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									勘察单位 ：
								</td>
								<td>
									${projectBaseInfo.prospectUtil}
								</td>
								<td align="right" class="showLabel">
									建设规模 ：
								</td>
								<td>
									${projectBaseInfo.buildContent}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									设计单位 ：
								</td>
								<td>
									${projectBaseInfo.buildUtil}
								</td>
								<td align="right" class="showLabel">
									施工单位 ：
								</td>
								<td>
									${projectBaseInfo.constructUtil}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									预算控制价(万元)：
								</td>
								<td>
									${projectBaseInfo.preAuditMoney}
								</td>
								<td align="right" class="showLabel">
									中标合同价(万元) ：
								</td>
								<td>
									${projectBaseInfo.zhongBiaoMoney}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									实际开工时间 ：
								</td>
								<td colspan="3">
									${projectBaseInfo.reallyStartTime}
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
							<tr>
								<td colspan="4" class="labelbase">
									<b>项目安排信息</b>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									主审人员 ：
								</td>
								<td>
									${tractArrangeProjectInfo.mainAuditName}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									复核工程师 ：
								</td>
								<td colspan="3">
									${tractArrangeProjectInfo.governmentEmployeeName}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									中介事务所 ：
								</td>
								<td colspan="3">
									${tractArrangeProjectInfo.intermediaryName}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									项目负责人 ：
								</td>
								<td>
									${tractArrangeProjectInfo.intermediaryLinker}
								</td>
								<td class="showLabel">
									负责人联系电话 ：
								</td>
								<td>
									${tractArrangeProjectInfo.intermediaryTelphone}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									项目组成人员 ：
								</td>
								<td colspan="3">
									${tractArrangeProjectInfo.intermediaryTeamName}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									工程开工令时间 ：
								</td>
								<td colspan="3">
									${tractArrangeProjectInfo.orderStartTime}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									工程实际开工时间 ：
								</td>
								<td >
									${tractArrangeProjectInfo.startTime}
								</td>
								<td class="showLabel">
									工程竣工时间 ：
								</td>
								<td>
									${tractArrangeProjectInfo.projectEndTime}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									项目审计安排时间 ：
								</td>
								<td colspan="3">
									${tractArrangeProjectInfo.projectArrangeTime}
								</td>
							</tr>
							<tr>
								<td class="showLabel">
									项目审计开始时间 ：
								</td>
								<td>
									${tractArrangeProjectInfo.projectStartTime}
								</td>
								<td class="showLabel">
									项目审计完成时间 ：
								</td>
								<td>
									${tractArrangeProjectInfo.projectCompleteTime}
								</td>
							</tr>
							<tr>
								<td colspan="4" class="labelbase">
									<b>综合信息</b>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									清标后金额 ：
								</td>
								<td>
									${projectBaseInfo.tractProjectQingBiao.afterMoney}
								</td>
								<td align="right" class="showLabel">
									当前累计已完成产值 ：
								</td>
								<td>
									${projectBaseInfo.totalCompleteValue}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									变更总金额 ：
								</td>
								<td>
									${projectBaseInfo.totalChangeMoney}
								</td>
								<td align="right" class="showLabel">
									业主确认索赔总金额 ：
								</td>
								<td>
									${projectBaseInfo.totalIndemnityMoney}
								</td>
							</tr>
						</table>
					</c:if>
					</tbody>
					</table>
				</div>
				<div id="p2" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="阶段信息" iconCls="icon-redo">
					<div class="easyui-tabs" id="detailDiv">
						<div title="清标信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>清标详细信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											清标后金额 ：
										</td>
										<td colspan="3" id="qingBiaoAfterMoney">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div title="月报表信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0" id="reportTableBaseInfo">
								<tr>
									<td colspan="4" class="labelbase">
										<b>月报表详细信息</b>
									</td>
								</tr>
								<tr>
									<td class="showLabel">
										当前累计已完成产值 ：
									</td>
									<td>
										${projectBaseInfo.totalCompleteValue}
									</td>
								</tr>
							</table>
							<table id="reportTable"></table>
						</div>
						<div title="变更签证信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0" id="changeCodeBaseInfo">
								<tr>
									<td colspan="4" class="labelbase">
										<b>变更签证详细信息</b>
									</td>
								</tr>
								<tr>
									<td class="showLabel">
										签证类型 ：
									</td>
									<td>
										<select id="changeType" name="changeType">
											<option value="" selected="selected">
												全部
											</option>
											<option value="0">
												清单漏项
											</option>
											<option value="1">
												清单量差
											</option>
											<option value="2">
												设计变更
											</option>
											<option value="6">
												政策规范性设计变更
											</option>
											<option value="3">
												技术核定
											</option>
											<option value="4">
												现场签证
											</option>
											<option value="5">
												其他
											</option>
										</select>
									</td>
									<td align="right" class="showLabel">
										变更总金额 ：
									</td>
									<td id="totalChangeMoney" width="30%;">
										${projectBaseInfo.totalChangeMoney}
									</td>
								</tr>
							</table>
							<table id="changeCodeBaseTable"></table>
						</div>
						<div title="索赔信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0" id="claimIndemnityBaseInfo">
								<tr>
									<td colspan="4" class="labelbase">
										<b>索赔详细信息</b>
									</td>
								</tr>
								<tr>
									<td class="showLabel">
										索赔类型 ：
									</td>
									<td>
										<select id="claimIndemnityType" name="claimIndemnityType"
											style="width: 200px;">
											<option value="" selected="selected">
												全部
											</option>
											<c:forEach items="${listsptype}" var="type">
												<option value="${type.id}">
													${type.dictionaryname}
												</option>
											</c:forEach>
										</select>
									</td>
									<td align="right" class="showLabel">
										业主确认索赔总金额 ：
									</td>
									<td id="totalIndemnityMoney" width="30%;">
										${projectBaseInfo.totalIndemnityMoney}
									</td>
								</tr>
							</table>
							<table id="claimIndemnityTable"></table>
						</div>
						<div title="合同条款审核信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tr>
									<td colspan="4" class="labelbase">
										<b>合同条款详细信息</b>
									</td>
								</tr>
								<c:forEach items="${htlist}" var="ht" varStatus="i">
									<tr>
										<td class="showLabel" align="right">
											${ht.htName}：
										</td>
										<td align="left">
											<a href="javascript:download('${ht.htFile}')">查看</a>
										</td>
										<td class="showLabel" align="right">
											存在问题：
										</td>
										<td align="left">
											<textarea rows="3" cols="10" style="width: 300px;">${ht.existproblem}</textarea>
										</td>
									</tr>
									<tr>
										<td class="showLabel" align="right">
											审核意见：
										</td>
										<td align="left">
											<textarea rows="3" cols="10" style="width: 300px;">${ht.auditview}</textarea>
										</td>
										<td class="showLabel" align="right">
											执行情况：
										</td>
										<td align="left">
											<textarea rows="3" cols="10" style="width: 300px;">${ht.executecase}</textarea>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div title="政策性调整信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0" id="zhengceChangeTitle">
								<tr>
									<td colspan="4" class="labelbase">
										<b>政策性调整详细信息</b>
									</td>
								</tr>
							</table>
							<table id="zhengceChangeTable"></table>
						</div>
						<div title="材料询价信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0" id="cailiaoTitle">
								<tr>
									<td colspan="4" class="labelbase">
										<b>材料询价文件</b>
									</td>
								</tr>
							</table>
							<table id="cailiaoTable"></table>
						</div>
						<div title="其他管理信息" style="padding: 10px">
							<table class="form"
								style="width: 98%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0" id="otherManageTitle">
								<tr>
									<td colspan="4" class="labelbase">
										<b>其他管理文件</b>
									</td>
								</tr>
							</table>
							<table id="otherManageTable"></table>
						</div>
						<c:if test="${not empty dataTransfer.id}">
							<div title="资料移交信息" style="padding: 10px">
								<table class="form"
									style="width: 98%; border-style: none; margin: 0px;"
									cellpadding="3" cellspacing="0" id="otherManageTitle">
									<tr>
										<td colspan="4" class="labelbase">
											<b>资料移交详细信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel" align="right">
											资料移交时间 ：
										</td>
										<td align="left">
											${dataTransfer.createTime}
										</td>
										<td class="showLabel" align="right">
											移交人员 ： 
										</td>
										<td align="left">
											${dataTransfer.createUserAccount}
										</td>
									</tr>
									<tr>
									    <td class="showLabel" align="right">
											综合性报告 ：
										</td>
										<td align="left">
											<a href="javascript:download('${dataTransfer.comprehensiveReportFile}')"
											id="comprehensiveReportFileDownload">综合性报告 下载</a>
										</td>
									</tr>
								</table>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
			<input type="hidden" id="url" value="${url}">
		</form>
	</body>
</html>
