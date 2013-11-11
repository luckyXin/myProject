<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>主审审核</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js">
</script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/mainAuditEdit.js">
</script>
		
</script>
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
		    <input type="hidden" class="datapreId" id="datapreId" name="datapreId" value="${dtatpreId}" />
			<div id="mainDiv">
				<div id="p4" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="项目基本信息" iconCls="icon-redo">
					<table class="form"
						style="width: 99%; border-style: none; margin: 0px;"
						cellpadding="3" cellspacing="0">
						
						<c:if test="${protype !=3 }">
							<input type="hidden" id="projectId" name="projectId"
								value="${projectInfo.arrangeId}">
							<input type="hidden" id="protype" value="${protype}" />
							<tr>
								<td colspan="4" class="labelbase">
									<b>项目资料信息</b>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									送审编号 ：
								</td>
								<td colspan="3">
									${projectInfo.datapreno}
								</td>

							</tr>
							<tr>
								<td align="right" class="showLabel">
									立项文号 ：
								</td>
								<td colspan="3">
									${projectInfo.projectNo}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									项目名称 ：
								</td>
								<td colspan="3">
									${projectInfo.projectName}
								</td>

							</tr>
							<tr>

								<td align="right" class="showLabel">
									审计局接收资料时间 ：
								</td>
								<td id="sja1">
								<input type="hidden" id="sj1" value="${projectInfo.datapretime}"/>
								</td>
								<td align="right" class="showLabel">
									送审金额（元） ：
								</td>
								<td>
									${projectInfo.sentAmount}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									合同价 ：
								</td>
								<td>
									${projectInfo.htmoney}
								</td>
								<td align="right" class="showLabel">
									其中暂列金额 ：
								</td>
								<td>
									${projectInfo.zhanliemoney}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									项目业主 ：
								</td>
								<td colspan="3">
									${projectInfo.proownerid}
								</td>

							</tr>
							<tr>
								<td align="right" class="showLabel">
									联系人 ：
								</td>
								<td>
									${projectInfo.proownerlink}
								</td>
								<td align="right" class="showLabel">
									联系电话 ：
								</td>
								<td>
									${projectInfo.proownertelphone}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									施工单位 ：
								</td>
								<td colspan="3">
									${projectInfo.constructId}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									联系人 ：
								</td>
								<td>
									${projectInfo.constructlink}
								</td>
								<td align="right" class="showLabel">
									联系电话 ：
								</td>
								<td>
									${projectInfo.constructtelphone}
								</td>
							</tr>
							<tr>
								<td colspan="4" class="labelbase">
									<b>项目安排信息</b>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									中介机构名称 ：
								</td>
								<td>
									${projectInfo.intermediaryId}
								</td>

							</tr>
							<tr>
								<td align="right" class="showLabel">
									联系人 ：
								</td>
								<td>
									${projectInfo.interlink}
								</td>
								<td align="right" class="showLabel">
									联系电话 ：
								</td>
								<td>
									${projectInfo.intertelphone}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									中介机构接收时间 ：
								</td>
								<td>
									${projectInfo.intermediarySendTime}
								</td>
								<td align="right" class="showLabel">
									中介机构应完成时间 ：
								</td>
								<td>
									${projectInfo.intermediaryAuditTime}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									主审人员 ：
								</td>
								<td>
									${projectInfo.mainAuditId}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									复核工程师 ：
								</td>
								<td colspan="3">
									${projectInfo.empusers}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									是否加急 ：
								</td>
								<td>
									<c:if test="${projectInfo.isUrgent=='1'}">
							     是
							    </c:if>
									<c:if test="${projectInfo.isUrgent=='0'}">
							    否
							    </c:if>

								</td>
								<td align="right" class="showLabel">
									限时 ：
								</td>
								<td>
									${projectInfo.timeLimit}
								</td>
							</tr>
						</c:if>
						<c:if test="${protype == 3}">
							<input type="hidden" id="path"
								value="<%=request.getContextPath()%>">
							<input type="hidden" id="protype" value="${protype}" />
							<input type="hidden" id="projecttype" name="projecttype"
								value="1" />
							<input type="hidden" id="projectId" name="projectId"
								value="${projectId}">
							<input type="hidden" class="datapreId" value="${dtatpreId}" />
							<tbody style="border: 1px solid #ccc">
								<tr>
									<td colspan="4" class="labelbase">
										<b>项目安排基本信息 </b>
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										项目名称 ：
									</td>
									<td>
										${projectInfo.projectName}
									</td>
									<td align="right" class="showLabel">
										项目业主 ：
									</td>
									<td>
										${projectInfo.proownerid}
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										中介机构名称 ：
									</td>
									<td>
										${projectInfo.intermediaryId}
									</td>
									<td align="right" class="showLabel">
										送审金额（元） ：
									</td>
									<td>
										<input type="hidden" id="sendmoney"
											value="${projectInfo.sentAmount}">
										${projectInfo.sentAmount}
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										发放中介机构时间 ：
									</td>
									<td>
										${projectInfo.intermediarySendTime}
									</td>
									<td align="right" class="showLabel">
										中介机构提交初审时间 ：
									</td>
									<td>
										<input type="hidden" id="zhongjiesubmit"
											value="${projectInfo.intermediaryAuditTime}">
										${projectInfo.intermediaryAuditTime}
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										主审人员 ：
									</td>
									<td>
										${projectInfo.mainAuditId}
									</td>
									<td align="right" class="showLabel">
										项目审计安排时间 ：
									</td>
									<td>
										${projectInfo.projectTime}
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										复核工程师 ：
									</td>
									<td colspan="3">
										${projectInfo.empusers}
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table id="mainmyfile"></table>
									</td>
								</tr>
							</tbody>
						</c:if>
					</table>
					<c:if test="${intermediaryAudit != null}">
						<div id="p1">
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>初审审核信息</b>
										</td>
									</tr>
									<!--
									<tr>
										<td class="label">
											中介机构名称：
										</td>
										<td style="width: 90%;" colspan="3">
											<input type="hidden" id="intermediaryAuditId"
												name="intermediaryAuditId" value="${intermediaryAudit.id}">
											${intermediaryAudit.intermediaryName}
										</td>
									</tr>
									-->
									<tr>
										<td class="showLabel">
											审定金额(元)：
										</td>
										<td>
											${intermediaryAudit.auditmoney}
										</td>
										<td class="showLabel">
											审减金额(元)：
										</td>
										<td>
											${intermediaryAudit.cutmoney}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											审计时间：
										</td>
										<td id="sja2">
										<input type="hidden" id="sj2" value="${intermediaryAudit.auditTime}"/>
										</td>
										<td class="showLabel">
											录入人员：
										</td>
										<td>
											${intermediaryAudit.userAccount}
											<input type="hidden" id="auditType" name="auditType"
												value="0">
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											初审审核意见：
										</td>
										<td>
											${intermediaryAudit.remark}
										</td>

									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${govermentEmployeeAudit != null}">
						<div id="p2">
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>复核工程师审核信息</b>
										</td>
									</tr>
									<tr>
										<td align="right" class="showLabel">
											审定金额 ：
										</td>
										<td>
											<input type="hidden" id="governmentAuditId"
												name="governmentAuditId"
												value="${govermentEmployeeAudit.id}">
											${govermentEmployeeAudit.auditMoney}
										</td>
										<td align="right" class="showLabel">
											审减金额 ：
										</td>
										<td>
											${govermentEmployeeAudit.reduceMoney}
										</td>
									</tr>
									<tr>
										<td align="right" class="showLabel">
											审计开始时间 ：
										</td>
										<td id="sja3">
											<input type="hidden" id="sj3" value="${govermentEmployeeAudit.auditStartTime}"/>
										</td>
										<td align="right" class="showLabel">
											审计结束时间 ：
										</td>
										<td id="sja4">
											<input type="hidden" id="sj4" value="${govermentEmployeeAudit.auditEndTime}"/>
										</td>
									</tr>
									<tr>
										<td align="right" class="showLabel">
											审计天数：
										</td>
										<td colspan="3">
											${govermentEmployeeAudit.auditDayCount}
										</td>
									</tr>
									<tr>
										<td align="right" class="showLabel">
											复核工作记录：
										</td>
										<td colspan="3">
											${govermentEmployeeAudit.auditRemark}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<input type="hidden" id="kezhanginfo" value="${kezhang}">
					<c:if test="${kezhang != null}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>投资科工程竣工结算审核</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											科长：
										</td>
										<td style="width: 90%;" colspan="3">
											${kezhang.username}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											科长意见
										</td>
										<td>
											${kezhang.isagree}
										</td>
										<td class="showLabel">
											科长复核审批时间
										</td>
										<td>
											${kezhang.audittime}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											科长评价：
										</td>
										<td colspan="3">
											${kezhang.remark}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<input type="hidden" id="fazhikezhang" value="${fazhikezhang}">
					<c:if test="${fazhikezhang != null}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>法制科工程竣工结算审核</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制科长：
										</td>
										<td style="width: 90%;" colspan="3">
											${fazhikezhang.username}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制科长意见
										</td>
										<td>
											${fazhikezhang.isagree}
										</td>
										<td class="showLabel">
											法制科长审核时间
										</td>
										<td>
											${fazhikezhang.audittime}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制科长评价：
										</td>
										<td colspan="3">
											${fazhikezhang.remark}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<input type="hidden" id="fazhifenguan" value="${fazhifenguan}">
					<c:if test="${fazhifenguan != null}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>法制分管审核信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制分管：
										</td>
										<td style="width: 90%;" colspan="3">
											${fazhifenguan.username}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制分管意见
										</td>
										<td>
											${fazhifenguan.isagree}
										</td>
										<td class="showLabel">
											法制分管审核时间
										</td>
										<td>
											${fazhifenguan.audittime}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制分管评价：
										</td>
										<td colspan="3">
											${fazhifenguan.remark}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>

					<input type="hidden" id="fenguan" value="${fenguan}">
					<c:if test="${fenguan != null}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>分管领导工程竣工结算审核</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											分管领导：
										</td>
										<td style="width: 90%;" colspan="3">
											${fenguan.username}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											分管领导意见
										</td>
										<td>
											${fenguan.isagree}
										</td>
										<td class="showLabel">
											分管领导审核时间
										</td>
										<td>
											${fenguan.audittime}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											分管领导评价：
										</td>
										<td colspan="3">
											${fenguan.remark}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${not empty investDepartAuditView.isInvestLeaderAudit}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>投资科室签审信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											投资科室签审评价：
										</td>
										<td colspan="3">
											${investDepartAuditView.investLeaderAuditRemark}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											投资科室签审意见：
										</td>
										<td colspan="">
											<c:if
												test="${investDepartAuditView.isInvestLeaderAudit == '1'}">
                                                                                                                     不同意								
									</c:if>
											<c:if
												test="${investDepartAuditView.isInvestLeaderAudit == '2'}">
                                                                                                                     同意							
									</c:if>
										</td>
										<td class="showLabel">
											签审时间：
										</td>
										<td>
											${investDepartAuditView.investLeaderAuditTime}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${not empty legalDepartAuditView.isLegalLeaderAudit}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>法制科签审信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制科签审评价：
										</td>
										<td colspan="3">
											${legalDepartAuditView.legalLeaderAuditRemark}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											法制科签审意见：
										</td>
										<td colspan="">
											<c:if
												test="${legalDepartAuditView.isLegalLeaderAudit == '1'}">
	                                                                                                                     不同意								
										</c:if>
											<c:if
												test="${legalDepartAuditView.isLegalLeaderAudit == '2'}">
	                                                                                                                     同意							
										</c:if>
										</td>
										<td class="showLabel">
											签审时间：
										</td>
										<td>
											${legalDepartAuditView.legalLeaderAuditTime}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${not empty totalAuditorAuditView.isTotalAudit}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>总审计师审签信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											总审计师审签评价：
										</td>
										<td colspan="3">
											${totalAuditorAuditView.totalAuditRemark}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											总审计师审签意见：
										</td>
										<td colspan="">
											<c:if test="${totalAuditorAuditView.isTotalAudit == '1'}">
	                                                                                                                     不同意								
										</c:if>
											<c:if test="${totalAuditorAuditView.isTotalAudit == '2'}">
	                                                                                                                     同意							
										</c:if>
										</td>
										<td class="showLabel">
											签审时间：
										</td>
										<td>
											${totalAuditorAuditView.totalAuditTime}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${not empty areaLeaderView.isAreaLeaderAudit}">
						<div>
							<table class="form"
								style="width: 99%; border-style: none; margin: 0px;"
								cellpadding="3" cellspacing="0">
								<tbody>
									<tr>
										<td colspan="4" class="labelbase">
											<b>分管领导审签信息</b>
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											分管领导审签评价：
										</td>
										<td colspan="3">
											${areaLeaderView.areaLeaderAuditRemark}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											分管领导审签意见：
										</td>
										<td colspan="">
											<c:if test="${areaLeaderView.isAreaLeaderAudit == '1'}">
	                                                                                                                     不同意								
										</c:if>
											<c:if test="${areaLeaderView.isAreaLeaderAudit == '2'}">
	                                                                                                                     同意							
										</c:if>
										</td>
										<td class="showLabel">
											签审时间：
										</td>
										<td>
											${areaLeaderView.areaLeaderAuditTime}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</c:if>
					</tbody>
					</table>
					<table id="mainmyfile"></table>
					<table id="subgrid"></table>
				</div>
				<div id="p3" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="主审审核" iconCls="icon-tip">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
						<a id="createsingleReport" class="easyui-linkbutton"
							iconCls="icon-save">审计报告</a>
						<a id="createIdeaReport" class="easyui-linkbutton"
							iconCls="icon-save">征求意见稿</a>
						<a id="suspend" class="easyui-linkbutton" iconCls="icon-save">项目暂停申请</a>
					</div>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<c:if test="${mainAuditInfo == null && protype!='1'}">
								<tr>
									<td>
										<tr>
											<td>
											</td>
										</tr>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>

								</tr>

								<tr>
									<td colspan="2" class="label">
										审计进度：
									</td>
									<td colspan="6">
										<textarea rows="5" style="width: 85%;" id="auditReportRemark"
											name="auditReportRemark" >
										</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="label">
										开工时间：
									</td>
									<td colspan="2">
										<input type="text" class="easyui-datebox"
											id="projectStartTime" name="projectStartTime" />
									</td>
									<td colspan="2" class="label">
										竣工时间：
									</td>
									<td colspan="2">
										<input type="text" class="easyui-datebox" id="projectEndTime"
											name="projectEndTime" />
									</td>

								</tr>
								<tr>
									<td colspan="8">
										<table>
											<tr>
												<td class="label">
													送审金额：
												</td>
												<td style="width: 180px;">
													<input type="text" name="sentAuditMone" id="sentAuditMone"
														value="${projectInfo.sentAmount}"  readonly="true"/>
												</td>
												<td class="label">
													审定金额：
												</td>
												<td style="width: 180px;">
													<input type="text" id="auditMoney" name="auditMoney"  value="${govermentEmployeeAudit.auditMoney}"readonly="true"/>
												</td>
												<td class="label">
													审减总金额：
												</td>
												<td style="width: 180px;">
													<c:if test="${intermediaryAudit == null}">
													<input type="text" id="auditReduceAllMoney"
														name="auditReduceAllMoney"
														value="${govermentEmployeeAudit.reduceMoney}"readonly="true"/>
													</c:if>
													<c:if test="${intermediaryAudit != null}">
													<input type="text" id="auditReduceAllMoney"
														name="auditReduceAllMoney"
														value="${govermentEmployeeAudit.reduceMoney+intermediaryAudit.cutmoney}"/>
													</c:if>
												</td>

											</tr>
											<table/>
												</td>
												</tr>
												<tr>
													<td colspan="2" class="label">
														工程主要施工内容：
													</td>
													<td colspan="6">
														<textarea rows="5" style="width: 85%;" id="maincontent"
															name="maincontent" >
										                </textarea>
													</td>
												</tr>
												<tr>
													<td colspan="8" class="labelbase">
														<b>资金来源</b>
													</td>
												</tr>
												<tr>
													<td colspan="2" class="label">
														区财政预算安排资金：
													</td>
													<td colspan="2">
														<input type="text" id="areaMoney" name="areaMoney"  />
													</td>
													<td colspan="2" class="label">
														市级及以上资金：
													</td>
													<td colspan="2">
														<input type="text" id="cityMoney" name="cityMoney"  />
													</td>

												</tr>
												<tr>
													<td colspan="2" class="label">
														自筹资金：
													</td>
													<td colspan="2">
														<input type="text" id="selfRaisedMoney"
															name="selfRaisedMoney" />
													</td>
													<td colspan="2" class="label">
														银行贷款：
													</td>
													<td colspan="2">
														<input type="text" id="bankMoney" name="bankMoney" />
													</td>

												</tr>
												<tr>
													<td colspan="2" class="label">
														其他资金：
													</td>
													<td colspan="2">
														<input type="text" id="otherInputMoney"
															name="otherInputMoney" />
													</td>
													<td colspan="4"></td>

												</tr>
												<tr>
													<td colspan="8" class="labelbase">
														<b> 资金支出</b>
													</td>
												</tr>
												  <tr>
												  	<td colspan="8">
												  		<table width="99%">
												  	<tr>
													<td style="width: 80px; background: #E0ECFF;">
														施工单位：
														<input type="hidden" value="工程进度款" id="unit1" name="unit1" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit1"
															name="capitalexpendituresUnit1" style="width: 80px"  
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount1"
															name="contractamount1"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney1" name="ttvmoney1"  style="width: 80px" 
														/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime1" name="stopAuditTime1"  />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid1" name="amountpaid1"  style="width: 80px" />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														设计单位：
														<input type="hidden" value="设计单位" id="unit2" name="unit2" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit2"
															name="capitalexpendituresUnit2"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount2"
															name="contractamount2"   style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney2" name="ttvmoney2"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime2" name="stopAuditTime2"    />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid2" name="amountpaid2"    style="width: 80px"  />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														勘察单位：
														<input type="hidden" value="勘察单位" id="unit3" name="unit3"/>
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit3"
															name="capitalexpendituresUnit3"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount3"
															name="contractamount3"  style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney3" name="ttvmoney3"  style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime3" name="stopAuditTime3"  />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid3" name="amountpaid3"  style="width: 80px" />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														监理单位：
														<input type="hidden" value="监理单位" id="unit4" name="unit4"  />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit4"
															name="capitalexpendituresUnit4"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount4"
															name="contractamount4"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney4" name="ttvmoney4"  style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime4" name="stopAuditTime4" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid4" name="amountpaid4" style="width: 80px" />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														项管单位：
														<input type="hidden" value="项管单位" id="unit5" name="unit5"   />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit5"
															name="capitalexpendituresUnit5"  style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount5"
															name="contractamount5"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney5" name="ttvmoney5" style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime5" name="stopAuditTime5"  />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid5" name="amountpaid5" style="width: 80px" />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														招标代理：
														<input type="hidden" value="招标代理" id="unit6" name="unit6"/>
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit6"
															name="capitalexpendituresUnit6"   style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount6"
															name="contractamount6" style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney6" name="ttvmoney6" style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime6" name="stopAuditTime6" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid6" name="amountpaid6" style="width: 80px"/>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														环评单位：
														<input type="hidden" value="环评单位" id="unit7" name="unit7" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit7"
															name="capitalexpendituresUnit7"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount7"
															name="contractamount7"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney7" name="ttvmoney7" style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime7" name="stopAuditTime7" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid7" name="amountpaid7"  style="width: 80px" />
													</td>
												</tr>


												<tr >
													<td style="width: 80px; background: #E0ECFF;">
														其他：
														<input type="hidden" value="其他" id="unit8" name="unit8" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit8"
															name="capitalexpendituresUnit8" style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount8"
															name="contractamount8"    style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney8" name="ttvmoney8"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime8" name="stopAuditTime8" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid8" name="amountpaid8" style="width: 80px"/>
														</td>
												</tr>
												<tr id="files">
																		<td colspan="9"></td>
																		<td>
																			<a class="easyui-linkbutton" iconCls="icon-add"
																				id="additems">增加</a>
																		</td>
																		</tr>
												  		</table>
												  	</td>
												  </tr>
												<tr>
													<td colspan="2" class="label">
														审定工程投资完成额：
													</td>
													<td colspan="1">
														<input type="text" id="totalInvestmentBudget" name="totalInvestmentBudget"/>
													</td>
													<td colspan="5"></td>
												</tr>
												<tr>
													<td colspan="2" class="label">
														工程直接费用：
													</td>
													<td colspan="2">
														<input type="text" id="projectDirectMoney"
															name="projectDirectMoney"
															value="${mainAuditInfo.projectDirectMoney}" />
													</td>
													<td colspan="2" class="label">
														工程间接费用：
													</td>
													<td colspan="2">
														<input type="text" id="indirectcosts" name="indirectcosts" />
													</td>

												</tr>
												<tr>
													<td colspan="8" class="labelbase">
														<b>存在问题</b>
													</td>
												</tr>
												 <tr>
												 	<td colspan="8">
												 		<table width="99%">
												 			<tr id="filest">
													<td style="background: #E0ECFF;">
														问题类型：
													</td>
													<td>
														<select id="type" name="type" class="123">

															<option value="0">
																是否超合同价
															</option>
															<option value="1">
																是否超概算
															</option>
															<option value="2">
																是否超工期
															</option>
															<option value="3">
																是否有工程质量验收记录
															</option>
															<option value="4">
																多计工程款
															</option>
															<option value="5" selected="selected">
																其他
															</option>
														</select>
													</td>
													<td style="background: #E0ECFF;">
														金额：
													</td>
													<td>
														<input type="text" id="money" name="money" />
													</td>
													<td style="background: #E0ECFF;">
														原因:
													</td>
													<td>
														<input type="text" id="reason"
															name="reason" />
													</td>
													<td style="background: #E0ECFF;">
														天数：
													</td>
													<td>
													<input type="text" id="day"
															name="day" />
													</td>
													<td><a class="easyui-linkbutton" iconCls="icon-add"
															id="additemst">增加</a></td>
												</tr>
												 		</table>
												 	</td>
												 </tr>
												<tr>
													<td colspan="2" class="label">
														出具审计报告时间：
													</td>
													<td colspan="2">
														<input type="text" class="easyui-datebox"
															id="auditReportTime" name="auditReportTime" />
													</td>
													<td colspan="2" class="label">
														审计报告文号：
													</td>
													<td colspan="2">
														<input type="text" id="auditReportCode"
															name="auditReportCode" />
													</td>

												</tr>

												</c:if>
												<c:if test="${mainAuditInfo != null && protype!='1'}">
													<tr>
														<td width="150"></td>
														<td ></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td></td>

													</tr>

													<tr>
														<td colspan="2" class="label">
															审计进度：
														</td>

														<td colspan="6">
															<textarea rows="5" style="width: 85%;"
																id="auditReportRemark" name="auditReportRemark" >
																${mainAuditInfo.auditReportRemark}
															</textarea>
														</td>
													</tr>
													<tr>
														<td colspan="2" class="label">
															开工时间：
														</td>
														<td colspan="2">
																<c:if test="${mainAuditInfo.projectStartTime=='1900-01-01 00:00:00.0'||mainAuditInfo.projectStartTime==null}">
																	<input type="text" class="easyui-datebox"
																	id="projectStartTime" name="projectStartTime"
																	value="" />
																</c:if>
																<c:if test="${mainAuditInfo.projectStartTime!=null &&mainAuditInfo.projectStartTime!='1900-01-01 00:00:00.0'}">
																	<input type="text" class="easyui-datebox"
																	id="projectStartTime" name="projectStartTime"
																	value="${mainAuditInfo.projectStartTime}" />
																</c:if>
														</td>
														<td colspan="2" class="label">
															竣工时间：
														</td>
														<td colspan="2">
																<c:if test="${mainAuditInfo.projectEndTime=='1900-01-01 00:00:00.0'||mainAuditInfo.projectEndTime==null}">
																	<input type="text" class="easyui-datebox"
																	id="projectEndTime" name="projectEndTime"
																	value="" />
																</c:if>
																<c:if test="${mainAuditInfo.projectEndTime!=null &&mainAuditInfo.projectEndTime!='1900-01-01 00:00:00.0'}">
																	<input type="text" class="easyui-datebox"
																	id="projectEndTime" name="projectEndTime"
																	value="${mainAuditInfo.projectEndTime}" />
																</c:if>
														</td>

													</tr>
													<tr>
														<td colspan="8">
															<table>
											<tr>
												<td class="label">
													送审金额：
												</td>
												<td style="width: 180px;">
													<input type="text" name="sentAuditMone" id="sentAuditMone"
														value="${projectInfo.sentAmount}"  readonly="true"/>
												</td>
												<td class="label">
													审定金额：
												</td>
												<td style="width: 180px;">
													<input type="text" id="auditMoney" name="auditMoney"  value="${govermentEmployeeAudit.auditMoney}"readonly="true"/>
												</td>
												<td class="label">
													审减总金额：
												</td>
												<td style="width: 180px;">
													<c:if test="${intermediaryAudit == null}">
													<input type="text" id="auditReduceAllMoney"
														name="auditReduceAllMoney"
														value="${govermentEmployeeAudit.reduceMoney}"readonly="true"/>
													</c:if>
													<c:if test="${intermediaryAudit != null}">
													<input type="text" id="auditReduceAllMoney"
														name="auditReduceAllMoney"
														value="${govermentEmployeeAudit.reduceMoney+intermediaryAudit.cutmoney}"readonly="true"/>
													</c:if>
												</td>

											</tr>
											<table/>
																	</td>

																	</tr>

																	<tr>
																		<td colspan="2" class="label">
																			工程主要施工内容：
																		</td>

																		<td colspan="6">
																			<textarea rows="5" style="width: 85%;"
																				id="maincontent" name="maincontent">
																				${mainAuditInfo.maincontent}
										</textarea>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="8" class="labelbase">
																			<b>资金来源</b>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" class="label">
																			区财政预算安排资金：
																		</td>
																		<td colspan="2">
																			<input type="text" id="areaMoney" name="areaMoney"
																				value="${mainAuditInfo.areaMoney}" />
																		</td>
																		<td colspan="2" class="label">
																			市级及以上资金：
																		</td>
																		<td colspan="2">
																			<input type="text" id="cityMoney" name="cityMoney"
																				value="${mainAuditInfo.cityMoney}" />
																		</td>

																	</tr>
																	<tr>
																		<td colspan="2" class="label">
																			自筹资金：
																		</td>
																		<td colspan="2">
																			<input type="text" id="selfRaisedMoney"
																				name="selfRaisedMoney"
																				value="${mainAuditInfo.selfRaisedMoney}"/>
																		</td>
																		<td colspan="2" class="label">
																			银行贷款：
																		</td>
																		<td colspan="2">
																			<input type="text" id="bankMoney" name="bankMoney"
																				value="${mainAuditInfo.bankMoney}"/>
																		</td>

																	</tr>
																	<tr>
																		<td colspan="2" class="label">
																			其他资金：
																		</td>
																		<td colspan="2">
																			<input type="text" id="otherInputMoney"
																				name="otherInputMoney"
																				value="${mainAuditInfo.otherInputMoney}"/>
																		</td>
																		<td colspan="4"></td>

																	</tr>
																	<tr>
																		<td colspan="8" class="labelbase">
																			<b> 资金支出</b>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="8">
																			<table width="99%">
																			<c:if test="${capitalexpenditures==null}">
																		<tr>
													<td style="width: 80px; background: #E0ECFF;">
														施工单位：
														<input type="hidden" value="工程进度款" id="unit1" name="unit1" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit1"
															name="capitalexpendituresUnit1" style="width: 80px" 
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount1"
															name="contractamount1"   style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney1" name="ttvmoney1" style="width: 80px" 
														/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime1" name="stopAuditTime1"  />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid1" name="amountpaid1"  style="width: 80px" />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														设计单位：
														<input type="hidden" value="设计单位" id="unit2" name="unit2" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit2"
															name="capitalexpendituresUnit2"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount2"
															name="contractamount2"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney2" name="ttvmoney2"  style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime2" name="stopAuditTime2" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid2" name="amountpaid2" style="width: 80px" />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														勘察单位：
														<input type="hidden" value="勘察单位" id="unit3" name="unit3"/>
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit3"
															name="capitalexpendituresUnit3"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount3"
															name="contractamount3" style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney3" name="ttvmoney3" style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime3" name="stopAuditTime3" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid3" name="amountpaid3"style="width: 80px"/>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														监理单位：
														<input type="hidden" value="监理单位"  />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit4"
															name="capitalexpendituresUnit4"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount4"
															name="contractamount4"   style="width: 80px"  />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney4" name="ttvmoney4"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime4" name="stopAuditTime4" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid4" name="amountpaid4"  style="width: 80px"  />
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														项管单位：
														<input type="hidden" value="项管单位" id="unit5" name="unit5" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit5"
															name="capitalexpendituresUnit5"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount5"
															name="contractamount5"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney5" name="ttvmoney5"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime5" name="stopAuditTime5"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid5" name="amountpaid5" style="width: 80px"/>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														招标代理：
														<input type="hidden" value="招标代理" id="unit6" name="unit6"/>
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit6"
															name="capitalexpendituresUnit6"   style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount6"
															name="contractamount6"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney6" name="ttvmoney6"style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime6" name="stopAuditTime6" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid6" name="amountpaid6" style="width: 80px"/>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														环评单位：
														<input type="hidden" value="环评单位" id="unit7" name="unit7" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit7"
															name="capitalexpendituresUnit7"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount7"
															name="contractamount7" style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney7" name="ttvmoney7" style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime7" name="stopAuditTime7" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid7" name="amountpaid7"  style="width: 80px"/>
													</td>
												</tr>


												<tr >
													<td style="width: 80px; background: #E0ECFF;">
														其他：
														<input type="hidden" value="其他" id="unit8" name="unit8" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit8"
															name="capitalexpendituresUnit8"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount8"
															name="contractamount8"  style="width: 80px"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney8" name="ttvmoney8"  style="width: 80px" />
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime8" name="stopAuditTime8" />
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid8" name="amountpaid8"  style="width: 80px" />
														</td>
												</tr>
												<tr id="files">
																		<td colspan="9"></td>
																		<td>
																			<a class="easyui-linkbutton" iconCls="icon-add"
																				id="additems">增加</a>
																		</td>
																		</tr>

																	</c:if>
																	<c:if test="${capitalexpenditures!=null}">
																	<c:forEach var="list1" items="${capitalexpenditures}">
																		<c:if test="${list1.unit=='工程进度款'}">
																				<tr>
													<td style="width: 80px; background: #E0ECFF;">
														施工单位：
														<input type="hidden" value="工程进度款" id="unit1" name="unit1" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit1"
															name="capitalexpendituresUnit1"style="width: 80px" 
															value="${list1.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount1"
															name="contractamount1"  style="width: 80px"  value="${list1.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney1" name="ttvmoney1"   style="width: 80px"
														value="${list1.ttvmoney}"
														/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime1" name="stopAuditTime1" value="${list1.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid1" name="amountpaid1"  style="width: 80px"  value="${list1.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list2" items="${capitalexpenditures}">
																		<c:if test="${list2.unit=='设计单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														设计单位：
														<input type="hidden" value="设计单位" id="unit2" name="unit2" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit2"
															name="capitalexpendituresUnit2" style="width: 80px" 
															value="${list2.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount2"
															name="contractamount2"  style="width: 80px" value="${list2.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney2" name="ttvmoney2"  style="width: 80px" 
														value="${list2.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime2" name="stopAuditTime2" value="${list2.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid2" name="amountpaid2" style="width: 80px" value="${list2.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list3" items="${capitalexpenditures}">
																		<c:if test="${list3.unit=='勘察单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														勘察单位：
														<input type="hidden" value="勘察单位" id="unit3" name="unit3" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit3"
															name="capitalexpendituresUnit3"style="width: 80px" 
															value="${list3.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount3"
															name="contractamount3" style="width: 80px" value="${list3.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney3" name="ttvmoney3" style="width: 80px" 
														value="${list3.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime3" name="stopAuditTime3" value="${list3.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid3" name="amountpaid3" style="width: 80px" value="${list3.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list4" items="${capitalexpenditures}">
																		<c:if test="${list4.unit=='监理单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														监理单位：
														<input type="hidden" value="监理单位" id="unit4" name="unit4" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit4"
															name="capitalexpendituresUnit4" style="width: 80px"
															value="${list4.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount4"
															name="contractamount4" style="width: 80px" value="${list4.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney4" name="ttvmoney4"  style="width: 80px" 
														value="${list4.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime4" name="stopAuditTime4" value="${list4.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid4" name="amountpaid4"  style="width: 80px" value="${list4.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list5" items="${capitalexpenditures}">
																		<c:if test="${list5.unit=='项管单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														项管单位：
														<input type="hidden" value="项管单位" id="unit5" name="unit5" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit5"
															name="capitalexpendituresUnit5"style="width: 80px" 
															value="${list5.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount5"
															name="contractamount5"  style="width: 80px"  value="${list5.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney5" name="ttvmoney5" style="width: 80px"
														value="${list5.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime5" name="stopAuditTime5"  value="${list5.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid5" name="amountpaid5"  style="width: 80px" value="${list5.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list6" items="${capitalexpenditures}">
																		<c:if test="${list6.unit=='招标代理'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														招标代理：
														<input type="hidden" value="招标代理" id="unit6" name="unit6" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit6"
															name="capitalexpendituresUnit6"style="width: 80px"
															value="${list6.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount6"
															name="contractamount6"  style="width: 80px" value="${list6.contractamount}" />
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney6" name="ttvmoney6"  style="width: 80px" 
														value="${list6.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime6" name="stopAuditTime6" value="${list6.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid6" name="amountpaid6"  style="width: 80px" value="${list6.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list7" items="${capitalexpenditures}">
																		<c:if test="${list7.unit=='环评单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														环评单位：
														<input type="hidden" value="环评单位" id="unit7" name="unit7" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit7"
															name="capitalexpendituresUnit7" style="width: 80px" 
															value="${list7.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount7"
															name="contractamount7"  style="width: 80px"  value="${list7.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney7" name="ttvmoney7"  style="width: 80px" 
														value="${list7.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime7" name="stopAuditTime7" value="${list7.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid7" name="amountpaid7" style="width: 80px"  value="${list7.amountpaid}"/>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>

																	<c:forEach var="list8" items="${capitalexpenditures}">
																		<c:if test="${list8.unit=='其他'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														其他：
														<input type="hidden" value="其他" id="unit8" name="unit8" />
													</td>
													<td>
														<input type="text" id="capitalexpendituresUnit8"
															name="capitalexpendituresUnit8"style="width: 80px"
															value="${list8.capitalexpendituresUnit}"
															/>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														<input type="text" id="contractamount8"
															name="contractamount8" style="width: 80px" 
															value="${list8.contractamount}"/>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														<input type="text" id="ttvmoney8" name="ttvmoney8"  style="width: 80px" 
														value="${list8.ttvmoney}"/>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														<input type="text" class="easyui-datebox"
															id="stopAuditTime8" name="stopAuditTime8" value="${list8.stopAuditTime}"/>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														<input type="text" id="amountpaid8" name="amountpaid8"  style="width: 80px" value="${list8.amountpaid}"/>
													</td>
													<td>
													<a class='easyui-linkbutton deletefile' iconCls='icon-cancel'>删除</a>
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<tr id="files">
																		<td colspan="9"></td>
																		<td>
																			<a class="easyui-linkbutton" iconCls="icon-add"
																				id="additems">增加</a>
																		</td>
																		</tr>
																		</c:if>
																			</table>
																		</td>
																	</tr>
																	 
																		<tr>
																			<td colspan="2" class="label">
																				审定工程投资完成额：
																			</td>
																			<td colspan="1">
																				<input type="text" id="totalInvestmentBudget"
																					name="totalInvestmentBudget"
																					value="${mainAuditInfo.totalInvestmentBudget}"/>
																			</td>
																			<td colspan="5"></td>
																		</tr>
																		<tr>
																			<td colspan="2" class="label">
																				工程直接费用：
																			</td>
																			<td colspan="2">
																				<input type="text" id="projectDirectMoney"
																					name="projectDirectMoney"
																					value="${mainAuditInfo.projectDirectMoney}"/>
																			</td>
																			<td colspan="2" class="label">
																				工程间接费用：
																			</td>
																			<td colspan="2">
																				<input type="text" id="indirectcosts"
																					name="indirectcosts"
																					value="${mainAuditInfo.indirectcosts}"/>
																			</td>

																		</tr>
																		<tr>
																			<td colspan="8" class="labelbase">
																				<b>存在问题</b>
																			</td>
																		</tr>
																		 <tr>
																		 	<td colspan="8">
																		 		<table width="99%">
																		 		<c:forEach var="list" items="${problems}">
																			<tr id="filest3">
																				<td style=" background: #E0ECFF;">
																					问题类型：
																				</td>
																				<td>
																					<select id="type" name="type" onchange="option()"class="123">

																						<c:if test="${list.type == '0'}">
																							<option value="0" selected="selected">
																								超合同价
																							</option>
																							<option value="1">
																								超概算
																							</option>
																							<option value="2">
																								超工期
																							</option>
																							<option value="3">
																								有工程质量验收记录
																							</option>
																							<option value="4">
																								多计工程款
																							</option>
																							<option value="5">
																								其他
																							</option>
																						</c:if>
																						<c:if test="${list.type == '1'}">
																							<option value="0">
																								超合同价
																							</option>
																							<option value="1" selected="selected">
																								超概算
																							</option>
																							<option value="2">
																								超工期
																							</option>
																							<option value="3">
																								有工程质量验收记录
																							</option>
																							<option value="4">
																								多计工程款
																							</option>
																							<option value="5">
																								其他
																							</option>
																						</c:if>
																						<c:if test="${list.type == '2'}">
																							<option value="0">
																								超合同价
																							</option>
																							<option value="1">
																								超概算
																							</option>
																							<option value="2" selected="selected">
																								超工期
																							</option>
																							<option value="3">
																								有工程质量验收记录
																							</option>
																							<option value="4">
																								多计工程款
																							</option>
																							<option value="5">
																								其他
																							</option>
																						</c:if>
																						<c:if test="${list.type == '3'}">
																							<option value="0">
																								超合同价
																							</option>
																							<option value="1">
																								超概算
																							</option>
																							<option value="2">
																								超工期
																							</option>
																							<option value="3" selected="selected">
																								有工程质量验收记录
																							</option>
																							<option value="4">
																								多计工程款
																							</option>
																							<option value="5">
																								其他
																							</option>
																						</c:if>
																						<c:if test="${list.type == '4'}">
																							<option value="0">
																								超合同价
																							</option>
																							<option value="1">
																								超概算
																							</option>
																							<option value="2">
																								超工期
																							</option>
																							<option value="3">
																								有工程质量验收记录
																							</option>
																							<option value="4" selected="selected">
																								多计工程款
																							</option>
																							<option value="5">
																								其他
																							</option>
																						</c:if>
																						<c:if test="${list.type == '5'}">
																							<option value="0">
																								超合同价
																							</option>
																							<option value="1">
																								超概算
																							</option>
																							<option value="2">
																								超工期
																							</option>
																							<option value="3">
																								有工程质量验收记录
																							</option>
																							<option value="4">
																								多计工程款
																							</option>
																							<option value="5" selected="selected">
																								其他
																							</option>
																						</c:if>
																					</select>
																				</td>
																				<td style="background: #E0ECFF;">
																					金额：
																				</td>
																				<td>
																					<input type="text" id="money" name="money"
																						value="${list.money}"  missingMessage="不能为空" />
																				</td>
																				<td style="background: #E0ECFF;">
																					原因:
																				</td>
																				<td>
																				<c:if test="${list.type==0||list.type==1||list.type==2}">
																					<input type="text" id="reason"
																						name="reason" value="${list.reason}" 
																						 required="true"  missingMessage="不能为空" class="easyui-validatebox"
																						/>
																				</c:if>
																				<c:if test="${list.type==3||list.type==4||list.type==5}">
																					<input type="text" id="reason"
																						name="reason" value="${list.reason}" 
																						   missingMessage="不能为空"
																						/>
																				</c:if>
																				</td>
																				<td style="background: #E0ECFF;">
																					天数：
																				</td>
																				<td>
																				<input type="text" id="day"
																						name="day" value="${list.day}" missingMessage="不能为空" />
																				</td>
																				<td>
																				<a class="easyui-linkbutton deletefile"
																						iconCls="icon-cancel" name="deleitemst">删除</a>
																				</td>

																			</tr>
																		</c:forEach>
																		 																				<tr id="filest">
																			<td colspan="8"></td>
																			<td>
																				<a class="easyui-linkbutton" iconCls="icon-add"
																					id="additemst">增加</a>
																			</td>
																			</tr>
																		 		</table>
																		 	</td>
																		 </tr>

																			<tr>
																				<td colspan="2" class="label">
																					出具审计报告时间：
																				</td>
																				<td colspan="2">
																				<c:if test="${mainAuditInfo.auditReportTime=='1900-01-01 00:00:00.0'||mainAuditInfo.auditReportTime==null}">
																					<input type="text" class="easyui-datebox"
																						id="auditReportTime" name="auditReportTime"
																						value="" />
																				</c:if>
																				<c:if test="${mainAuditInfo.auditReportTime!=null && mainAuditInfo.auditReportTime!='1900-01-01 00:00:00.0'}">
																					<input type="text" class="easyui-datebox"
																						id="auditReportTime" name="auditReportTime"
																						value="${mainAuditInfo.auditReportTime}" />
																				</c:if>
																				</td>
																				<td colspan="2" class="label">
																					审计报告文号：
																				</td>
																				<td colspan="2">
																					<input type="text" id="auditReportCode"
																						name="auditReportCode"
																						value="${mainAuditInfo.auditReportCode}" />
																				</td>
																			</tr>
																			
																		</tr>
																		</c:if>
																		<tr>
																				<td> 
																					<input type="hidden" value="${mainAuditInfo.id}"
																						id="mainAuditId" name="mainAuditId"/>
																				</td>
																			</tr>
																	</tbody>
																</table>
																</div>
																</div>
																<input type="hidden" id="coutextPath"
																	value="<%=request.getContextPath()%>">
																<input type="hidden" id="fid" value="${moduelId}">
																<input type="hidden" id="processType"
																	value="${processType}">
																<input type="hidden" id="url" value="${url}">
																<input type="hidden" id="noUpdate"
																	value="<spring:message code='error.message.noUpdate'/>">
																<input type="hidden" id="title"
																	value="<spring:message code='prompt.title.title'/>">
																</form>
																<jsp:include page="../common/showdiv.jsp"></jsp:include>
																<jsp:include page="../common/prosuspendapplication.jsp"></jsp:include>
	</body>
</html>
