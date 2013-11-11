<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>单项目安排</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/singleProjectArrangeEdit.js">
	
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
				<div id="p2" class="easyui-panel"
					style="background: #fafafa; height: 390px; margin-bottom: 5px;"
					collapsible="true" title="录入项目安排信息" iconCls="icon-tip">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					<c:if test="${processType=='add' || processType=='update'}">
					   <a id="suspend" class="easyui-linkbutton" iconCls="icon-save">项目暂停申请</a>
					  </c:if>
					</div>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<c:if test="${processType=='add'}">
								<tr>
									<td class="label">
										项目名称：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="hidden" id="projectId" name="projectId"
											value="${projectInfo.id}">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="projectName" name="projectName"
											required="true" value="${projectInfo.projectName}">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目业主：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="ownerName" name="ownerName"
											value="${projectInfo.projectOwnerName}">
									</td>
								</tr>
								<tr>
									<td class="label">
										送审金额(元)：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="sentAmount" name="sentAmount"
											value="${projectInfo.sentAmount}">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目安排备注：
									</td>
									<td colspan="3">
										<textarea id="projectArrangeRemark" class="text"
											style="width: 50%;" rows="4" name="projectArrangeRemark">
								</textarea>
									</td>
								</tr>
								
								<c:if test="${singProjectInfo == null}">
									<tr>
										<td class="label">
											主审人员：
										</td>
										<td colspan="3">
											<input type="hidden" id="mainAuditId" name="mainAuditId">
											<input class="easyui-validatebox" readonly="readonly"
												style="width: 200px;" id="mainAuditName"
												name="mainAuditName" required="true">
										</td>
									</tr>
									<tr>
										<td class="label">
											复核工程师：
										</td>
										<td colspan="3">
											<input class="easyui-validatebox" readonly="readonly"
												style="width: 200px;" id="governmentEmployee"
												name="governmentEmployee">
											<font color="red">*最多五个复核工程师 </font>
											<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
										</td>
									</tr>
								</c:if>
								<c:if test="${singProjectInfo != null}">
									<tr>
										<td class="label">
											主审人员：
										</td>
										<td>
											<input type="hidden" id="mainAuditId" name="mainAuditId"
												value="${singProjectInfo.mainAuditId}">
											<input class="easyui-validatebox" readonly="readonly"
												style="width: 200px;" id="mainAuditName"
												name="mainAuditName" required="true"
												value="${singProjectInfo.mainAuditName}">
										</td>
									</tr>
									<tr>
										<td class="label">
											<span id="auditgover"> 复核工程师：</span>
										</td>
										<td colspan="3">
											<c:forEach items="${singProjectInfo.governmentEmployee}"
												var="geId">
												<input type="hidden" class="governmentEmployee"
													id="governmentEmployeeId" name="governmentEmployeeId"
													value="${geId}">
											</c:forEach>
											<input class="easyui-validatebox" readonly="readonly"
												style="width: 200px;" id="governmentEmployee"
												name="governmentEmployee" 
												value="${singProjectInfo.governmentEmployeeName}">
											<font color="red">*最多五个复核工程师 </font>
											<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
										</td>
									</tr>
								</c:if>
								<tr>
									<td class="label">
										资料发送中介机构时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="intermediarySendTime"
											name="intermediarySendTime">
									</td>
									<td class="label">
										中介机构应提交初审结果时间：
									</td>
									<td>
										<input readonly="readonly" class="easyui-datebox"
										    style="width: 200px;"
											id="intermediaryAuditTime" name="intermediaryAuditTime">
									</td>
								</tr>
								<tr>
									<td class="label">
										是否自审：
									</td>
									<td colspan="3">
										 <input type="checkbox" value="1" name="isMySelfState" id="isMySelfState">
									</td>
								</tr>
								<tr>
									<td class="label">
										状态：
									</td>
									<td colspan="3">
										<select name="state" style="width: 200px;">
											<option value="0">
												启用
											</option>
											<option value="1">
												禁用
											</option>
											<option value="2">
												删除
											</option>
										</select>
									</td>
								</tr>
							</c:if>
							<c:if test="${processType=='update'}">
								<tr>
									<td class="label">
										项目名称：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="hidden" id="projectId" name="projectId"
											value="${singProjectInfo.datapreId}">
										<input type="hidden" name="singleProjectArrangeId"
											value="${singProjectInfo.id}">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="projectName" name="projectName"
											required="true" value="${singProjectInfo.projectName}">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目业主：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="ownerName" name="ownerName"
											value="${singProjectInfo.ownerName}">
									</td>
								</tr>
								<tr>
									<td class="label">
										送审金额(元)：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="sentAmount" name="sentAmount"
											value="${singProjectInfo.sentAmount}">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目安排备注：
									</td>
									<td colspan="3">
										<textarea id="projectArrangeRemark" class="text"
											style="width: 50%;" rows="4" name="projectArrangeRemark">
											${singProjectInfo.projectRemark}
								        </textarea>
									</td>
								</tr>
							
								<tr>
									<td class="label">
										主审人员：
									</td>
									<td>
										<input type="hidden" id="mainAuditId" name="mainAuditId"
											value="${singProjectInfo.mainAuditId}">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="mainAuditName" name="mainAuditName"
											required="true" value="${singProjectInfo.mainAuditName}">
									</td>
								</tr>
								<tr>
									<td class="label">
									
									 <c:if test="${singProjectInfo.isMySelfState == '1'}">
										<span id="auditgover"> 自审工程师：</span>
									</c:if>
									<c:if test="${ empty singProjectInfo.isMySelfState}">
										<span id="auditgover"> 复核工程师：</span>
									</c:if>
										
									</td>
									<td colspan="3">
										<c:forEach items="${singProjectInfo.governmentEmployee}"
											var="geId">
											<input type="hidden" class="governmentEmployee"
												id="governmentEmployeeId" name="governmentEmployeeId"
												value="${geId}">
										</c:forEach>
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="governmentEmployee"
											name="governmentEmployee"
											value="${singProjectInfo.governmentEmployeeName}">
										<font color="red">*最多五个复核工程师 </font>
										<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
									</td>
								</tr>
								<tr>
									<td class="label">
										资料发送中介机构时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="intermediarySendTime"
											name="intermediarySendTime"
											value="${singProjectInfo.intermediarySendTime}">
									</td>
									<td class="label">
										中介机构应提交初审结果时间：
									</td>
									<td>
										<input readonly="readonly" class="easyui-datebox" style="width: 200px;"
											id="intermediaryAuditTime" name="intermediaryAuditTime"
											value="${singProjectInfo.intermediaryAuditTime}">
									</td>
								</tr>
							
								<tr>
									<td class="label">
										是否自审：
									</td>
									<td colspan="3">
									<c:if test="${singProjectInfo.isMySelfState == '1'}">
										 <input type="checkbox" value="1" name="isMySelfState" id="isMySelfState" checked="checked">
									</c:if>
									<c:if test="${ empty singProjectInfo.isMySelfState}">
										 <input type="checkbox" value="1" name="isMySelfState" id="isMySelfState">
									</c:if>
									</td>
								</tr>
								<tr>
									<td class="label">
										状态：
									</td>
									<td colspan="3">
										<select id="state" name="state" style="width: 200px;">
											<c:if test="${singProjectInfo.state == '0'}">
												<option value="0" selected="selected">
													启用
												</option>
												<option value="1">
													禁用
												</option>
												<option value="2">
													删除
												</option>
											</c:if>
											<c:if test="${singProjectInfo.state == '1'}">
												<option value="0">
													启用
												</option>
												<option value="1" selected="selected">
													禁用
												</option>
												<option value="2">
													删除
												</option>
											</c:if>
											<c:if test="${singProjectInfo.state == '2'}">
												<option value="0">
													启用
												</option>
												<option value="1">
													禁用
												</option>
												<option value="2" selected="selected">
													删除
												</option>
											</c:if>
										</select>
									</td>
								</tr>
								<input type="hidden" id="governmentEmployeeAuditState"
									value="${singProjectInfo.governmentEmployeeAuditState}">
								<input type="hidden" id="intermediaryAuditState"
									value="${singProjectInfo.intermediaryAuditState}">
								<input type="hidden" id="mainAuditState"
									value="${singProjectInfo.mainAuditState}">
							</c:if>
							<c:if test="${processType=='packProjectArrangeAdd'}">
								<tr>
									<td class="label">
										打包项目名称：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="hidden" id="projectPackId" name="projectPackId">
										<input class="easyui-validatebox" style="width: 200px;"
											id="projectPackName" name="projectPackName" required="true">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目业主：
									</td>
									<td colspan="3">
										<input type="hidden" name="ownerId" id="ownerId">
										<input type="hidden" id="ownerChangeState">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="ownerName" name="ownerName">
									</td>
								</tr>
								<tr>
									<td class="label">
										送审金额(元)：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="sentAmount" name="sentAmount"
											value="0">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目安排备注：
									</td>
									<td colspan="3">
										<textarea id="projectArrangeRemark" class="text"
											style="width: 50%;" rows="4" name="projectArrangeRemark">
								</textarea>
									</td>
								</tr>
								
								<tr>
									<td class="label">
										主审人员：
									</td>
									<td colspan="3">
										<input type="hidden" id="mainAuditId" name="mainAuditId">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="mainAuditName" name="mainAuditName"
											required="true">
									</td>
								</tr>
								<tr>
									<td class="label">
										复核工程师：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="governmentEmployee"
											name="governmentEmployee" >
										<font color="red">*最多五个复核工程师 </font>
										<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
									</td>
								</tr>
								<tr>
									<td class="label">
										资料发送中介机构时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="intermediarySendTime"
											name="intermediarySendTime">
									</td>
									<td class="label">
										中介机构应提交初审结果时间：
									</td>
									<td>
										<input readonly="readonly" class="easyui-datebox"
											style="width: 200px;"
											id="intermediaryAuditTime" name="intermediaryAuditTime">
									</td>
								</tr>
							
								<tr>
									<td class="label">
										是否自审：
									</td>
									<td colspan="3">
										 <input type="checkbox" value="1" name="isMySelfState" id="isMySelfState">
									</td>
								</tr>
								<tr>
									<td class="label">
										状态：
									</td>
									<td colspan="3">
										<select name="state" style="width: 200px;">
											<option value="0">
												启用
											</option>
											<option value="1">
												禁用
											</option>
											<option value="2">
												删除
											</option>
										</select>
									</td>
								</tr>
							</c:if>
							<c:if test="${processType=='packProjectArrangeUpdate'}">
								<tr>
									<td class="label">
										打包项目名称：
									</td>
									<td style="width: 90%;" colspan="3">
										<input type="hidden" id="projectPackId" name="projectPackId"
											value="${packProjectArrange.id}">
										<input class="easyui-validatebox" style="width: 200px;"
											id="projectPackName" name="projectPackName"
											value="${packProjectArrange.projectPackName}" required="true">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目业主：
									</td>
									<td colspan="3">
										<input type="hidden" name="ownerId" id="ownerId"
											value="${packProjectArrange.ownerId}">
										<input type="hidden" id="ownerChangeState">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="ownerName" name="ownerName"
											value="${packProjectArrange.ownerName}">
									</td>
								</tr>
								<tr>
									<td class="label">
										送审金额(元)：
									</td>
									<td colspan="3">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="sentAmount" name="sentAmount"
											value="${packProjectArrange.sentAmount}">
									</td>
								</tr>
								<tr>
									<td class="label">
										项目安排备注：
									</td>
									<td colspan="3">
										<textarea id="projectArrangeRemark" class="text"
											style="width: 50%;" rows="4" name="projectArrangeRemark">
											${packProjectArrange.projectRemark}
								</textarea>
									</td>
								</tr>
							
								<tr>
									<td class="label">
										主审人员：
									</td>
									<td colspan="3">
										<input type="hidden" id="mainAuditId" name="mainAuditId"
											value="${packProjectArrange.mainAuditId}">
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="mainAuditName" name="mainAuditName"
											required="true" value="${packProjectArrange.mainAuditName}">
									</td>
									<td>
									
									</td>
								</tr>
								<tr>
									<td class="label">
										复核工程师：
									</td>
									<td colspan="3">
										<c:forEach items="${packProjectArrange.governmentEmployee}"
											var="geId">
											<input type="hidden" class="governmentEmployee"
												id="governmentEmployeeId" name="governmentEmployeeId"
												value="${geId}">
										</c:forEach>
										<input class="easyui-validatebox" readonly="readonly"
											style="width: 200px;" id="governmentEmployee"
											name="governmentEmployee" value="${packProjectArrange.governmentEmployeeName}">
										<font color="red">*最多五个复核工程师 </font>
										<a id="clean" class="easyui-linkbutton" iconCls="icon-remove">清除</a>
									</td>
								</tr>
								<tr>
									<td class="label">
										资料发送中介机构时间：
									</td>
									<td>
										<input class="easyui-datebox" readonly="readonly"
											style="width: 200px;" id="intermediarySendTime"
											name="intermediarySendTime"
											value="${packProjectArrange.intermediarySendTime}">
									</td>
									<td class="label">
										中介机构应提交初审结果时间：
									</td>
									<td>
										<input readonly="readonly" class="easyui-datebox"
											style="width: 200px;"
											id="intermediaryAuditTime" name="intermediaryAuditTime"
											value="${packProjectArrange.intermediaryAuditTime}">
									</td>
								</tr>
								
								<tr>
									<td class="label">
										是否自审：
									</td>
									<td colspan="3">
										<c:if test="${packProjectArrange.isMySelfState == '1'}">
										 <input type="checkbox" value="1" name="isMySelfState" id="isMySelfState" checked="checked">
										 </c:if>
										 <c:if test="${empty packProjectArrange.isMySelfState}">
										 <input type="checkbox" value="1" name="isMySelfState" id="isMySelfState">
										 </c:if>
									</td>
								</tr>
								<tr>
									<td class="label">
										状态：
									</td>
									<td colspan="3">
										<select name="state" style="width: 200px;">
											<c:if test="${packProjectArrange.state == '0'}">
												<option value="0" selected="selected">
													启用
												</option>
												<option value="1">
													禁用
												</option>
												<option value="2">
													删除
												</option>
											</c:if>
											<c:if test="${packProjectArrange.state == '1'}">
												<option value="0">
													启用
												</option>
												<option value="1" selected="selected">
													禁用
												</option>
												<option value="2">
													删除
												</option>
											</c:if>
											<c:if test="${packProjectArrange.state == '2'}">
												<option value="0">
													启用
												</option>
												<option value="1">
													禁用
												</option>
												<option value="2" selected="selected">
													删除
												</option>
											</c:if>
										</select>
										<input type="hidden" id="governmentEmployeeAuditState"
											value="${packProjectArrange.governmentEmployeeAuditState}">
										<input type="hidden" id="intermediaryAuditState"
											value="${packProjectArrange.intermediaryAuditState}">
										<input type="hidden" id="mainAuditState"
											value="${packProjectArrange.mainAuditState}">
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<c:if
				test="${processType=='packProjectArrangeAdd' || processType=='packProjectArrangeUpdate'}">
				<div style="display: none;" id="subProjectIds">
				</div>
				<div id="p3" class="easyui-panel"
					style="background: #fafafa; height: 200px;" collapsible="true"
					title="打包项目对应的子项目" iconCls="icon-tip">
					<div style="margin-bottom: 5px;">
						<a id="addSubProject" class="easyui-linkbutton"
							iconCls="icon-save">添加</a>
						<a id="delSubProject" class="easyui-linkbutton"
							iconCls="icon-save">删除</a>
					    <a id="suspendclid" class="easyui-linkbutton" iconCls="icon-save">项目暂停申请</a>
					</div>
					<table id="packSubProject"></table>
				</div>
			</c:if>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${id}">
			<input type="hidden" id="processType" value="${processType}">
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
