<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>科长审批</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/sectionChiefAuditEdit.js"></script>
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
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="项目基本信息" iconCls="icon-redo">
					<table class="form"
						style="width: 99%; border-style: none; margin: 5px;"
						cellpadding="3" cellspacing="0">
						<tbody>
						  <input type="hidden" id="projectId" name="projectId"
										value="${projectInfo.arrangeId}"> 
							<input type="hidden" id="protype" value="${protype}"/>
							<input type="hidden" id="guyuan" value="${govermentEmployeeAudit}"/>
							<input type="hidden" id="zhongjie" value="${intermediaryAudit}"/>
							<input type="hidden" id="lingdao" value="${leaderAuditBaseInfo}"/>
							<input type="hidden" id="datareId" value="${datareId}"/>
							
							
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
							</td >
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
							
							
				<c:if test="${intermediaryAudit != null}">
					<div id="p1">
						<table class="form"
							style="width: 99%; border-style: none; margin-top: 0px;"
							cellpadding="3" cellspacing="0">
							<tbody>
							   <tr>
					             <td colspan="4" class="labelbase">
					            <b>初审审核信息</b>
					           </td>
					           </tr>  
								<!--<tr>
									<td class="label">
										中介机构名称：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="hidden" id="intermediaryAuditId"
											name="intermediaryAuditId" value="${intermediaryAudit.id}">
											${intermediaryAudit.intermediaryName}
									</td>
								</tr>
								--><tr>
									<td align="right" class="showLabel">
										初审审定金额(元)：
									</td>
									<td>
											${intermediaryAudit.auditmoney}
									</td>
									<td align="right" class="showLabel">
										初审审减金额(元)：
									</td>
									<td>
											${intermediaryAudit.cutmoney}
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										审计时间：
									</td>
									<td id="sj2">
											${intermediaryAudit.auditTime}
									</td>
									<td align="right" class="showLabel">
										录入人员：
									</td>
									<td>
									${intermediaryAudit.userAccount}
										<input type="hidden" id="auditType" name="auditType" value="0">
									</td>
								</tr>
								<tr>
									<td align="right" class="showLabel">
										初审审核意见：
									</td>
									<td colspan="3">
											${intermediaryAudit.remark}
									</td>
									
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${govermentEmployeeAudit != null}">
					<div id="p2">
						<table class="form" style="width: 99%; border-style: none;margin-top: 0px;"
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
											name="governmentAuditId" value="${govermentEmployeeAudit.id}">
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
											<input type="hidden" id="auditType" name="auditType" value="1">
									</td>
								</tr>
								<tr>
									<td align="right"  class="showLabel">
										复核工作记录：
									</td>
									<td colspan="3" >
											${govermentEmployeeAudit.auditRemark}
									</td>
									
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${leaderAuditBaseInfo != null}">
					<div>
						<table class="form" style="width: 99%; border-style: none;margin-top: 0px;"
							cellpadding="3" cellspacing="0">
							<tbody>
							     <tr>
					             <td colspan="4" class="labelbase">
					            <b>领导审核信息</b>
					           </td>
					           </tr>  
								<tr>
									<td align="right" class="showLabel">
										审批意见：
									</td>
									<td style="width: 90%;" colspan="3">
										<textarea rows="5" id="remark" name="remark"
											style="width: 50%;" disabled="disabled">
											${leaderAuditBaseInfo.remark}
									</textarea>
									</td>
								</tr>
								<tr>
									<td align="right"  class="showLabel">
										是否同意：
									</td>
									<td style="width: 90%;" colspan="3">
										<c:if test="${leaderAuditBaseInfo.passState == '1'}">
										   &nbsp;&nbsp;&nbsp;不同意 
									    </c:if>
										<c:if test="${leaderAuditBaseInfo.passState == '2'}">
										    &nbsp;&nbsp;&nbsp;同意
									    </c:if>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
						</tbody>
					</table>
				</div>
				<div id="pp3" style="display: none;">		
				  <div id="projectbase" class="easyui-panel" style="background: #fafafa;" collapsible="true" title="项目基本信息" >
				      <table id="subgrid"></table>
				  </div>
				</div>
				  <table id="myfile"></table>
				<div id="p3" class="easyui-panel"
					style="background: #fafafa; height: 200px; margin-bottom: 5px;"
					collapsible="true" title="投资科工程竣工结算审核" iconCls="icon-tip">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<c:if test="${sectionChiefAuditBaseInfo == null}">
								<tr>
									<td class="label">
										审批意见：
									</td>
									<td style="width: 90%;" colspan="3">
										<textarea rows="5" id="remark" name="remark"
											style="width: 50%;">
									</textarea>
									</td>
								</tr>
								<tr>
									<td class="label">
										是否同意：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="radio" value="1" name="passState"
											class="radioClass" checked="checked">
										不同意 &nbsp;&nbsp;&nbsp;
										<input type="radio" value="2" name="passState"
											class="radioClass">
										同意
									</td>
								</tr>
								<tr>
									<td class="label">
										确认状态：
									</td>
									<td>
										<input type="checkbox" value="1" id="state" name="state">
										已确认 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="color: red;">确认之后无法修改</span>
										<input type="hidden" id="submitState" name="submitState"
											value="0">
										<input type="hidden" id="auditId" name="auditId" value="">
									</td>
								</tr>
							</c:if>
							<c:if test="${sectionChiefAuditBaseInfo != null}">
								<tr>
									<td class="label">
										审批意见：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="hidden" id="auditId" name="auditId"
											value="${sectionChiefAuditBaseInfo.id}">
										<textarea rows="5" id="remark" name="remark"
											style="width: 50%;">
											${sectionChiefAuditBaseInfo.remark}
									</textarea>
									</td>
								</tr>
								<tr>
									<td class="label">
										是否同意：
									</td>
									<td style="width: 90%;" colspan="3">
										<c:if test="${sectionChiefAuditBaseInfo.passState == '1'}">
											<input type="radio" value="1" name="passState"
												class="radioClass" checked="checked">
										不同意 &nbsp;&nbsp;&nbsp;
										<input type="radio" value="2" name="passState"
												class="radioClass">
										同意
									</c:if>
										<c:if test="${sectionChiefAuditBaseInfo.passState == '2'}">
											<input type="radio" value="1" name="passState"
												class="radioClass">
										不同意 &nbsp;&nbsp;&nbsp;
										<input type="radio" value="2" name="passState"
												class="radioClass" checked="checked">
										同意
									</c:if>
									</td>
								</tr>
								<tr>
									<td class="label">
										确认状态：
									</td>
									<td style="width: 90%;" colspan="3">
										<c:if test="${sectionChiefAuditBaseInfo.isSubmit == '1'}">
											<input type="checkbox" value="1" id="state" name="state"
												checked="checked">
										已确认 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="color: red;">确认之后无法修改</span>
											<input type="hidden" id="submitState" name="submitState"
												value="1">
										</c:if>
										<c:if test="${sectionChiefAuditBaseInfo.isSubmit != '1'}">
											<input type="checkbox" value="1" id="state" name="state">
											<input type="hidden" id="submitState" name="submitState"
												value="0">
										已确认 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="color: red;">确认之后无法修改</span>
										</c:if>
										<input type="hidden" id="auditId" name="auditId"
											value="${sectionChiefAuditBaseInfo.id}">
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${moduelId}">
			<input type="hidden" id="processType" value="${processType}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
		<jsp:include page="../common/projectshowdiv.jsp"></jsp:include>
	</body>
</html>
