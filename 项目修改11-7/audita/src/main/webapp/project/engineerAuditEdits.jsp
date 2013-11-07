<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>复核工程师审批</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/center.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/engineerAuditEdits.js"></script>
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
	<body><form id="form" method="post">
			<div id="mainDiv">
			<div id="p4" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="项目基本信息" iconCls="icon-redo">
					 <table class="form"
						style="width: 99%; border-style: none; margin: 0px;"
						cellpadding="3" cellspacing="0">
						<tbody>
							<input type="hidden" id="projectId" name="projectId"
								value="${projectInfo.arrangeId}">
							<input type="hidden" id="datapreId" name="datapreId"
								value="${projectInfo.datapreId}">
							<input type="hidden" id="protype" value="${protype}" />
							<input type="hidden" id="projecttype" name="projecttype"
								value="0" />
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
									<input type="hidden" id="sentAmount"
										value="${projectInfo.sentAmount}" />
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
										<td class="showLabel">
											中介机构名称：
										</td>
										<td style="width: 90%;" colspan="3">
											<input type="hidden" id="intermediaryAuditId"
												name="intermediaryAuditId" value="${intermediaryAudit.id}">
											${intermediaryAudit.intermediaryName}
										</td>
						</tr><tr>
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
								<td>
									${projectInfo.empusers}
								</td>
								<td align="right" class="showLabel">
									是否自审 ：
								</td>
								<td>
								   <input type="hidden" value="${projectInfo.isMySelfState}" id="isMySelfState"/>
								    <c:if test="${projectInfo.isMySelfState==1}">
								       是
								    </c:if>
								    <c:if test="${projectInfo.isMySelfState!=1}">
								      否
								    </c:if>
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									中介机构接收资料时间：
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
					<c:if test="${not empty datagov}">
					                           <tr>
												        <td colspan="4" class="labelbase">
												          <b>主审录入信息</b>
												        </td>
					                        </tr>
						<tr>
						      <td align="right" class="showLabel">
								核对工作量时间 ：
							</td>
							<td>
							   <c:if test="${not empty wt }">
							      ${wt.startTime }到
							      ${wt.endTime}
							  </c:if>
							   <c:if test="${empty wt }">
							    &nbsp;
							   </c:if>
							</td>
							<td align="right" class="showLabel">
								现场勘踏时间：
							</td>
							<td>
							 <c:if test="${not empty datagov.prospectTime }">
							    ${datagov.prospectTime }
							  </c:if>
							   <c:if test="${empty datagov.prospectTime }">
							    &nbsp;
							   </c:if>
							</td>
						</tr>
					
						<tr>
						    <td align="right" class="showLabel">
								财务收支取证时间 ：
							</td>
							<td>
							  ${datagov. financialRAETime}
							
							 </td>
						
						</tr>
						</c:if>
					</table>
					 <c:if test="${intermediaryAudit != null}">
					<input type="hidden" value=" ${intermediaryAudit.auditmoney}" id="zhongjieauditmoney">
					    <div>
							<table class="form" style="width: 99%; border-style: none;margin: 0px;"
								id="mytableid" cellpadding="3" cellspacing="0">
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
											审减率：
										</td>
										<td>
											${intermediaryAudit.auditlv}
										</td>
										<td class="showLabel">
											审计同意延期天数：
										</td>
										<td>
											${intermediaryAudit.deferday}
										</td>
									</tr>
									<tr>
										<td class="showLabel">
											初审完成时间：
										</td>
										<td id="sj2">
											${intermediaryAudit.auditTime}
										</td>
										<td class="showLabel">
											超期天数：
										</td>
										<td>
											${intermediaryAudit.overday}
											
										</td>
									</tr>
									<tr>
										
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
										<td colspan="3">
											${intermediaryAudit.remark}
										</td>
									</tr>
									</table>
									</div>
					
					
					</c:if>
							<c:if test="${sectionChiefAuditBaseInfo != null}">
								<div>
									<table class="form" style="width: 99%; border-style: none;margin-top: 0px"
										id="mytableid" cellpadding="3" cellspacing="0">
										<tbody>
			                               <tr>
												        <td colspan="4" class="labelbase">
												          <b>投资科工程竣工结算审核</b>
												        </td>
					                        </tr>
											<tr>
												<td class="showLabel">
													审批意见：
												</td>
												<td style="width: 90%;" colspan="3">
													<input type="hidden" id="sectionChiefAuditId"
														name="sectionChiefAuditId"
														value="${sectionChiefAuditBaseInfo.id}">
													<textarea rows="5" id="remark" name="remark"
														style="width: 50%;" disabled="disabled">
														${sectionChiefAuditBaseInfo.remark}
												</textarea>
												</td>
											</tr>
											<tr>
												<td class="showLabel">
													是否同意：
												</td>
												<td style="width: 90%;" colspan="3">
													<c:if test="${sectionChiefAuditBaseInfo.passState == '1'}">
													不同意 
												</c:if>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
			           
						</tbody>
					</table>
				</div>
				<div id="p3" class="easyui-panel"
					style="background: #fafafa; height: 250px; margin-bottom: 5px;"
					collapsible="true" title="复核工程师审核" iconCls="icon-tip">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
							<a id="suspend" class="easyui-linkbutton" iconCls="icon-save">项目暂停申请</a>
					</div>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<c:if test="${govermentEmployeeAudit == null}">
								<tr>
									<td align="right" class="label">
										复核审定金额 ：
									</td>
									<td>
										<input type="hidden" id="auditId" name="auditId">
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="auditMoney" name="auditMoney">
									</td>
									<td align="right" class="label">
										复核审减金额 ：
									</td>
									<td>
										<input style="width: 200px;" id="reduceMoney"
											name="reduceMoney" readonly="readonly">
									</td>
								</tr>
								<tr>
								     <td align="right" class="label">
										复核审减率 ：
									</td>
									<td>
										 <input  style="width: 200px;" id="auditlv" name="auditlv"  readonly="readonly">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										复核工作记录 ：
									</td>
									<td colspan="3">
										<textarea rows="5" id="auditRemark" name="auditRemark"
											style="width: 50%;">
									</textarea>
									</td>
								</tr>
							</c:if>
							<c:if test="${govermentEmployeeAudit != null}">
								<tr>
									<td align="right" class="label">
										复核审定金额 ：
									</td>
									<td>
										<input type="hidden" id="auditId" name="auditId"
											value="${govermentEmployeeAudit.id}">
										<input class="easyui-validatebox" required="true"
											style="width: 200px;" id="auditMoney" name="auditMoney"
											value="${govermentEmployeeAudit.auditMoney}">
									</td>
									<td align="right" class="label">
										复核审减金额：
									</td>
									<td>
										<input style="width: 200px;" id="reduceMoney"
											name="reduceMoney" readonly="readonly"
											value="${govermentEmployeeAudit.reduceMoney}">
									</td>
								</tr>
								<tr>
								     <td align="right" class="label">
										复核审减率 ：
									</td>
									<td>
										 <input  style="width: 200px;" id="auditlv" name="auditlv"  readonly="readonly" value="${govermentEmployeeAudit.auditlv}">
									</td>
								</tr>
								<tr>
									<td align="right" class="label">
										复核工作记录 ：
									</td>
									<td colspan="3">
										<textarea rows="8" id="auditRemark" name="auditRemark"
											style="width: 50%;">
											${govermentEmployeeAudit.auditRemark}
									</textarea>
									</td>
									<c:if test="${govermentEmployeeAudit.submitState == 0}">
											<input type="hidden" id="submitState" name="submitState"
												value="0">
										</c:if>
										<c:if test="${govermentEmployeeAudit.submitState == 1}">
											<input type="hidden" id="submitState" name="submitState"
												value="1">
										</c:if>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${auditsubpromoduelId}">
			<input type="hidden" id="processType" value="${processType}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
		<jsp:include page="../common/projectshowdiv.jsp"></jsp:include>
		<jsp:include page="../common/prosuspendapplication.jsp"></jsp:include>
	</body>
</html>
