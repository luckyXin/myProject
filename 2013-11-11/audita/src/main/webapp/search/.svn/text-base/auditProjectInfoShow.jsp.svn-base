<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>项目明细展示</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
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
			<div id="mainDiv">
				<div id="p4" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="项目基本信息" iconCls="icon-redo">
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
					<c:if test="${projectInfo!=null}">
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
								<td id="sj1">
									${projectInfo.datapretime}
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
						</table>
					</c:if>
					</tbody>
					</table>
				</div>
			</div>
			<c:if
				test="${intermediaryAudit != null || govermentEmployeeAudit != null || mainAuditInfo != null || kezhang != null || fazhikezhang != null || fazhifenguan != null || fenguan != null}">
				<div id="p5" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="阶段信息" iconCls="icon-redo">
					<div class="easyui-tabs" id="detailDiv">
						<c:if test="${intermediaryAudit != null}">
							<div title="初审信息" style="padding: 10px">
								<table class="form"
									style="width: 98%; border-style: none; margin: 0px;"
									cellpadding="3" cellspacing="0">
									<tbody>
										<tr>
											<td colspan="4" class="labelbase">
												<b>初审审核信息</b>
											</td>
										</tr>
										<tr>
											<td class="showLabel">
												初审审定金额(元)：
											</td>
											<td>
												${intermediaryAudit.auditmoney}
											</td>
											<td class="showLabel">
												初审审减金额(元)：
											</td>
											<td>
												${intermediaryAudit.cutmoney}
											</td>
										</tr>
										<tr>
											<td class="showLabel">
												审计时间：
											</td>
											<td id="sj2"> 
												${intermediaryAudit.auditTime}
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
							<div title="复审信息" style="padding: 10px">
								<table class="form"
									style="width: 98%; border-style: none; margin: 0px;"
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
											<td id="sj3">
												${govermentEmployeeAudit.auditStartTime}
											</td>
											<td align="right" class="showLabel">
												审计结束时间 ：
											</td>
											<td id="sj4">
												${govermentEmployeeAudit.auditEndTime}
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
											<td class="showLabel">
												复核工作记录：
											</td>
											<td>
												${govermentEmployeeAudit.auditRemark}
											</td>

										</tr>
									</tbody>
								</table>
							</div>
						</c:if>
						<c:if test="${mainAuditInfo != null}">
							<div title="主审信息" style="padding: 10px">
								<table class="form"
									style="width: 98%; border-style: none; margin: 0px;"
									cellpadding="3" cellspacing="0">
									<tbody>
										<tr>
														<td colspan="2" class="label">
															审计进度：
														</td>

														<td colspan="6">
															
																${mainAuditInfo.auditReportRemark}
															
														</td>
													</tr>
													<tr>
														<td colspan="2" class="label">
															开工时间：
														</td>
														<td colspan="2">
															${mainAuditInfo.projectStartTime}
														</td>
														<td colspan="2" class="label">
															竣工时间：
														</td>
														<td colspan="2">
															${mainAuditInfo.projectEndTime}
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
																		${mainAuditInfo.sentAuditMone}
																	</td>
																	<td class="label">
																		审定金额：
																	</td>
																	<td style="width: 180px;">
																		${mainAuditInfo.auditMoney}
																	</td>
																	<td class="label">
																		审减总金额：
																	</td>
																	<td style="width: 180px;">
																		${mainAuditInfo.auditReduceAllMoney}
																	</td>
																</tr>
																<table />
																	</td>
																	</tr>
																	<tr>
																		<td colspan="2" class="label">
																			工程主要施工内容：
																		</td>
																		<td colspan="6">
																				${mainAuditInfo.maincontent}
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
																			${mainAuditInfo.areaMoney}
																		</td>
																		<td colspan="2" class="label">
																			市级及以上资金：
																		</td>
																		<td colspan="2">
																			${mainAuditInfo.cityMoney}
																		</td>

																	</tr>
																	<tr>
																		<td colspan="2" class="label">
																			自筹资金：
																		</td>
																		<td colspan="2">
																			${mainAuditInfo.selfRaisedMoney}
																		</td>
																		<td colspan="2" class="label">
																			银行贷款：
																		</td>
																		<td colspan="2">
																			${mainAuditInfo.bankMoney}
																		</td>

																	</tr>
																	<tr>
																		<td colspan="2" class="label">
																			其他资金：
																		</td>
																		<td colspan="2">
																			${mainAuditInfo.otherInputMoney}
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
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																				<td width="10%"></td>
																			</tr>
																			<c:if test="${capitalexpenditures==null}">
																		<tr>
													<td style="width: 80px; background: #E0ECFF;">
														施工单位：
													</td>
													<td> 
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														设计单位：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														勘察单位：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														监理单位：
														<input type="hidden" value="监理单位"  />
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														项管单位：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														招标代理：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>
												<tr>
													<td style="width: 80px; background: #E0ECFF;">
														环评单位：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													</td>
												</tr>


												<tr >
													<td style="width: 80px; background: #E0ECFF;">
														其他：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
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
														${list1.capitalexpendituresUnit}
														
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list1.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list1.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list1.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list1.amountpaid}
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
														${list2.capitalexpendituresUnit}
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list2.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list2.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list2.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list2.amountpaid}
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list3" items="${capitalexpenditures}">
																		<c:if test="${list3.unit=='勘察单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														勘察单位：
														
													</td>
													<td>
														${list3.capitalexpendituresUnit}
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list3.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list3.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list3.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list3.amountpaid}
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
														${list4.capitalexpendituresUnit}
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list4.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list4.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list4.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list4.amountpaid}
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list5" items="${capitalexpenditures}">
																		<c:if test="${list5.unit=='项管单位'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														项管单位：
														
													</td>
													<td>
														${list5.capitalexpendituresUnit}
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list5.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list5.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list5.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list5.amountpaid}
													</td>
												</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="list6" items="${capitalexpenditures}">
																		<c:if test="${list6.unit=='招标代理'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														招标代理：
														
													</td>
													<td>
														${list6.capitalexpendituresUnit}
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list6.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list6.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list6.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list6.amountpaid}
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
														${list7.capitalexpendituresUnit}
															
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list7.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list7.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list7.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
														${list7.amountpaid}
													</td>
												</tr>
																		</c:if>
																	</c:forEach>

																	<c:forEach var="list8" items="${capitalexpenditures}">
																		<c:if test="${list8.unit=='其他'}">
																			<tr>
													<td style="width: 80px; background: #E0ECFF;">
														其他：
														
													</td>
													<td>
														${list8.capitalexpendituresUnit}
													</td>
													<td style="background: #E0ECFF;">
														合同金额：
													</td>
													<td>
														${list8.contractamount}
													</td>
													<td style="background: #E0ECFF;">
															主审审定金额:
													</td>
													<td>
														${list8.ttvmoney}
													</td>
													<td style="background: #E0ECFF;">
														截止审计日:
													</td>
													<td>
														${list8.stopAuditTime}
													</td>
													<td style="background: #E0ECFF;">
														已支付金额：
													</td>
													<td>
													${list8.amountpaid}
													</td>
													
												</tr>
																		</c:if>
																	</c:forEach>
																	
																		</c:if>
																			</table>
																		</td>
																	</tr>
																	 
																		<tr>
																			<td colspan="2" class="label">
																				审定工程投资完成额：
																			</td>
																			<td colspan="1">
																				${mainAuditInfo.totalInvestmentBudget}
																			</td>
																			<td colspan="5"></td>
																		</tr>
																		<tr>
																			<td colspan="2" class="label">
																				工程直接费用：
																			</td>
																			<td colspan="2">
																				
																					${mainAuditInfo.projectDirectMoney}
																			</td>
																			<td colspan="2" class="label">
																				工程间接费用：
																			</td>
																			<td colspan="2">
																				${mainAuditInfo.indirectcosts}
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
																						<c:if test="${list.type == '0'}">
																							
																								超合同价
																							
																						</c:if>
																						<c:if test="${list.type == '1'}">
																							
																								超概算
																							
																						</c:if>
																						<c:if test="${list.type == '2'}">
																							
																								超工期
																							
																						</c:if>
																						<c:if test="${list.type == '3'}">
																							
																								有工程质量验收记录
																							
																						</c:if>
																						<c:if test="${list.type == '4'}">
																							
																								多计工程款
																							
																						</c:if>
																						<c:if test="${list.type == '5'}">
																							
																							
																								其他
																							
																						</c:if>
																					
																				</td>
																				<td style="background: #E0ECFF;">
																					金额：
																				</td>
																				<td>
																					
																						${list.money}
																				</td>
																				<td style="background: #E0ECFF;">
																					原因:
																				</td>
																				<td>
																				
																					${list.reason}
																				
																				</td>
																				<td style="background: #E0ECFF;">
																					天数：
																				</td>
																				<td>
																				${list.day}
																				</td>
																				

																			</tr>
																		</c:forEach>
																		 																				<tr id="filest">
																			
																		 		</table>
																		 	</td>
																		 </tr>

																			<tr>
																				<td colspan="2" class="label">
																					出具审计报告时间：
																				</td>
																				<td colspan="2">
																					${mainAuditInfo.auditReportTime}
																				</td>
																				<td colspan="2" class="label">
																					审计报告文号：
																				</td>
																				<td colspan="2">
																					${mainAuditInfo.auditReportCode}
																				</td>
																			</tr>
																			
																		</tr>
																		</c:if>
									</tbody>
								</table>
							</div>
						</c:if>
						
						 
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
