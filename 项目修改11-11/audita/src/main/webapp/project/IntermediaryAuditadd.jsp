<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/intermediaryAuditAdd.js"></script>
		<title>初审审核录入</title>
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
				<div id="p2" class="easyui-panel" style="background: #fafafa;" collapsible="true" title="中介机构初审基本信息" >
				   <div id="pp1">
					<table class="form" style="width: 99%;margin: 0px;"  cellpadding="3" cellspacing="0">
					          <input type="hidden" id="frameid" value="${requestScope.auditsubpromoduelId}">
						     <input type="hidden" id="path" value="<%=request.getContextPath()%>">
						     <input type="hidden" id="protype" value="${protype}"/>
						     <input type="hidden" id="projecttype" name="projecttype"  value="0"/>
						      <input type="hidden" id="arrangeId" name="arrangeId" value="${intermediaryAudit.arrangeId}">
						      <input type="hidden" id="datapreId" name="datapreId" value="${intermediaryAudit.datapreId}">
					<tbody style="border: 1px solid #ccc">
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
								${intermediaryAudit.datapreno}
							</td>
						</tr>
						<tr>
						    <td align="right" class="showLabel">
								立项文号 ：
							</td>
							<td  colspan="3">
								${intermediaryAudit.projectNo}
							</td>
						</tr>	
						<tr>
						  <td align="right" class="showLabel">
								项目名称 ：
							</td>
							<td colspan="3">
								${intermediaryAudit.projectName}
							</td>
						</tr>
						  <tr>
							
							<td align="right" class="showLabel">
								 审计局接收资料时间 ：
							</td>
							<td id="sj1">
								${intermediaryAudit.datapretime}
							</td>
							<td align="right" class="showLabel">
									送审金额（元） ：
							</td>
							<td>
									 <input type="hidden" id="sendmoney"  value="${intermediaryAudit.sentAmount}">
									${intermediaryAudit.sentAmount}
							</td>
						</tr>
						<tr>
							<td align="right" class="showLabel">
								合同价 ：
							</td>
							<td>
								${intermediaryAudit.htmoney}
							</td>
							 <td align="right" class="showLabel">
								其中暂列金额 ：
							</td>
							<td>
								${intermediaryAudit.zhanliemoney}
							</td>
						</tr>
						<tr>
							<td align="right" class="showLabel">
								项目业主 ：
							</td>
							<td colspan="3">
								${intermediaryAudit.proownerid}
							</td>
						</tr>
						<tr>
							<td align="right" class="showLabel">
								联系人 ：
							</td>
							<td>
								${intermediaryAudit.proownerlink}
							</td>
							 <td align="right" class="showLabel">
								联系电话 ：
							</td>
							<td>
								${intermediaryAudit.proownertelphone}
							</td>
						</tr>
						<tr>
						<td align="right" class="showLabel">
								施工单位 ：
							</td>
							<td colspan="3">
								${intermediaryAudit.constructId}
							</td>
					   </tr>
					   <tr>
							<td align="right" class="showLabel">
								联系人 ：
							</td>
							<td>
								${intermediaryAudit.constructlink}
							</td>
							 <td align="right" class="showLabel">
								联系电话 ：
							</td>
							<td>
								${intermediaryAudit.constructtelphone}
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
							<td colspan="3">
								${intermediaryAudit.intermediaryId}
							</td>
							
						</tr>
						<tr>
							<td align="right" class="showLabel">
								联系人 ：
							</td>
							<td>
								${intermediaryAudit.interlink}
							</td>
							 <td align="right" class="showLabel">
								联系电话 ：
							</td>
							<td>
								${intermediaryAudit.intertelphone}
							</td>
						</tr>
						<tr>
						    <td align="right" class="showLabel">
								中介机构接收时间 ：
							</td>
							<td>
								${intermediaryAudit.intermediarySendTime}
							</td>
							<td align="right" class="showLabel">
								中介机构应完成时间 ：
							</td>
							<td>
							<input type="hidden" id="zhongjiesubmit"  value="${intermediaryAudit.intermediaryAuditTime}">
							${intermediaryAudit.intermediaryAuditTime}
							</td>
						</tr>
						<tr>
						    <td align="right" class="showLabel">
								主审人员 ：
							</td>
							<td>
								${intermediaryAudit.mainAuditId}
							</td>
							</tr>
						<tr>
						<td align="right" class="showLabel">
								复核工程师 ：
							</td>
							<td colspan="3">
								${intermediaryAudit.empusers}
							</td>
						</tr>
						<tr>
						    <td align="right" class="showLabel">
								是否加急 ：
							</td>
							<td>
							    <c:if test="${intermediaryAudit.isUrgent=='1'}">
							     是
							    </c:if>
							     <c:if test="${intermediaryAudit.isUrgent=='0'}">
							    否
							    </c:if>
								
							</td>
							<td align="right" class="showLabel">
								限时 ：
							</td>
							<td>
								${intermediaryAudit.timeLimit}
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
						</c:if>
						</tbody>
				</table>
			 <table id="myfile"></table>
				</div>
				<c:if test="${empty section}">
				   <input type="hidden" id="issectionaudit" value="0">
				</c:if>
				<c:if test="${not empty section}">
				   <input type="hidden" id="issectionaudit" value="1">
				</c:if>
				<div id="pp2" style="display: none;">
					<table class="form" style="width: 99%;margin: 0px;"  cellpadding="3" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
					     <tr>
					        <td colspan="4" class="labelbase">
					          <b>投资科工程竣工结算审核</b>
					        </td>
					     </tr>
						<tr>
						    <td align="right" class="label">
								科长 ：
							</td>
							<td>
								${section.userAccount}
							</td>
							<td align="right" class="label">
								科长意见：
							</td>
							<td>
								不同意
							</td>
						</tr>
						<tr>
						    <td align="right" class="label">
								科长评价 ：
							</td>
							<td colspan="3">
							      ${section.remark}
							</td>

						</tr>
						
						</tbody>
				</table>
				</div>
				</div>
				<div id="pp3" style="display: none;">		
				  <div id="projectbase" class="easyui-panel" style="background: #fafafa;" collapsible="true" title="项目基本信息" >
				      <table id="subgrid"></table>
				  </div>
				
				</div>
			    <c:if test="${protype==0}">
				<div id="p3" class="easyui-panel" style="background: #fafafa;" collapsible="true" title="初审审核结果录入" >
				
				     <div style="margin:5px;">
					    <c:if test="${prointer.isconfirm!=1}">
					    <a  href="javascript:void(0);"  id="singlesave" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					    </c:if>
						<a class="easyui-linkbutton" onclick="javascript:cancel()" iconCls="icon-cancel">关闭</a>
						 <c:if test="${prointer.isconfirm!=1}">
					   <a id="suspend" class="easyui-linkbutton" iconCls="icon-save">项目暂停申请</a>
					    </c:if>
						
				   </div>
				   <table class="form" style="width: 99%;margin-top: 2px;"  cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
						     <input type="hidden" id="path" value="<%=request.getContextPath()%>">
						    <td align="right" class="label">
								初审审定金额 ：
							</td>
							<td>
							      <c:if test="${prointer.isconfirm==1}">
							         <input type="text" style="width: 200px;" readonly="readonly" value="${prointer.auditmoney }">
							     </c:if>
							     <c:if test="${prointer.isconfirm!=1}">
							          <input class="easyui-validatebox" required="true"   missingMessage="初审审定金额不能为空"  style="width: 200px;" id="auditmoney" name="auditmoney" value="${prointer.auditmoney }">
							     </c:if>
							     
								
							</td>
							<td align="right" class="label">
								初审审减金额 ：
							</td>
							<td>
								 <input  style="width: 200px;" id="cutmoney" name="cutmoney"  readonly="readonly"    value="${prointer.cutmoney }">
							</td>
						</tr>
						<tr>
						   <td align="right" class="label">
								审减率 ：
							</td>
							<td>
								 <input  style="width: 200px;" id="auditlv" name="auditlv"  readonly="readonly" value="${prointer.auditlv}">
							</td>
						    <td align="right" class="label">
								初审完成时间：
							</td>
							<td>
							     <input type="hidden" id="chushengtime" value="${prointer.auditoktime}"></input>
							    <c:if test="${prointer.isconfirm==1}">
							         <input type="text" style="width: 200px;" id="chushengoktime" readonly="readonly" >
							    </c:if>
							      <c:if test="${prointer.isconfirm!=1}">
							      <input class="easyui-datebox"  required="true"   missingMessage="实际提交初审时间不能为空"   style="width: 200px;" editable="false" id="auditoktime" name="auditoktime">
							      </c:if>
								
							</td>
							
						</tr>
						<tr>
						   <td align="right" class="label">
								审计同意延期天数：
							</td>
							<td>
							     <c:if test="${prointer.isconfirm==1}">
							         <input type="text" style="width: 200px;" readonly="readonly" value="${prointer.deferday}">
							     </c:if>
							     <c:if test="${prointer.isconfirm!=1}">
							        <input class="easyui-validatebox"    style="width: 200px;" id="deferday" name="deferday" value="${prointer.deferday}">
							     </c:if>
								
							</td>
						    <td align="right" class="label">
								超期天数 ：
							</td>
							<td>
								<input  style="width: 200px;" id="overday" name="overday" readonly="readonly" value="${prointer.overday }">
							</td>
						</tr>
						
						<tr>
						    <td align="right" class="label">
								备注：
							</td>
							<td colspan="3">
							     <c:if test="${prointer.isconfirm==1}">
								<textarea rows="4" cols="20" readonly="readonly"     style="width: 98%">${prointer.remark}</textarea>
								</c:if>
								<c:if test="${prointer.isconfirm!=1}">
								  <textarea rows="4" cols="20" id="remark" name="remark" style="width: 98%">${prointer.remark}</textarea>
								</c:if>
							</td>
						</tr>
						<tr>
						    <td align="right" class="label">
								确认提交：
							</td>
							<td colspan="3">
							  <c:if test="${prointer.isconfirm==1}">
								<input type="radio"  disabled="disabled" checked="checked">是
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" disabled="disabled" >否
						     </c:if>
						     <c:if test="${prointer.isconfirm!=1}">
				                  <input type="radio" name="isconfirm" value="1">是
						           &nbsp;&nbsp;&nbsp;&nbsp;
						          <input type="radio" name="isconfirm" value="0" checked="checked">否
						     </c:if>
								&nbsp;&nbsp;
								<span style="color: red;">注意：一旦确认提交将不能修改</span>
							</td>
						</tr>
						</tbody>
				</table>
				    
				   
		        </div>
			</div>
			 </c:if>
			  <c:if test="${protype==1}">
				<div id="totalp3" class="easyui-panel" style="background: #fafafa;" collapsible="true" title="初审审核结果录入" >
				     <div style="margin:5px;">
					    <c:if test="${ispacksubmit==0}">
					    <a  href="javascript:void(0);"  id="totalsave" class="easyui-linkbutton" iconCls="icon-save">提交</a>
					    </c:if>
						<a class="easyui-linkbutton" onclick="javascript:cancel()" iconCls="icon-cancel">关闭</a>
				   </div>
				   <table class="form" style="width: 99%;margin-top: 2px;"  cellpadding="3" cellspacing="0">
				   <input type="hidden" id="issubmit" value="${ispacksubmit}">
					<tbody style="border: 1px solid #ccc">
						<tr>
						    <td align="right" class="label">
								总审定金额 ：
							</td>
							<td class="zongauditmoney">
							    ${zongauditmoney}
							</td>
							<td align="right" class="label">
								总审减金额 ：
							</td>
							<td class="zongcutmoney">
							    <input type="hidden" id="zongcutmoney" value="${zongcutmoney}"></input>
								${zongcutmoney}
							</td>
						</tr>
						<tr>
						   <td align="right" class="label">
								总审减率 ：
							</td>
							<td id="shenjianlv">
								
							</td>
						    <td align="right" class="label">
								初审完成时间 ：
							</td>
							<td>
							     <input type="hidden" id="chushengtime" value="${auditoktime}"></input>
							    <c:if test="${ispacksubmit!=0}">
							         <input type="text" style="width: 200px;" id="chushengoktime" readonly="readonly"   value="${auditoktime}">
							    </c:if>
							      <c:if test="${ispacksubmit==0}">
							      <input class="easyui-datebox"  required="true"   missingMessage="实际提交初审时间不能为空"   style="width: 200px;" editable="false" id="auditoktime" name="auditoktime"  value="${auditoktime}">
							      </c:if>
								
							</td>
							
						</tr>
						<tr>
						  <td align="right" class="label">
								审计同意延期天数 ：
							</td>
							<td>
							       <c:if test="${ispacksubmit!=0}">
							        ${deferday}
							     </c:if>
							      <c:if test="${ispacksubmit==0}">
							        <input class="easyui-validatebox"  value="${deferday}"   style="width: 200px;" id="deferday" name="deferday" value="${prointer.deferday}">
							     </c:if>
								
							</td>
						    <td align="right" class="label">
								超期天数 ：
							</td>
							<td>
							    <c:if test="${ispacksubmit!=0}">
							        ${overday}
							     </c:if>
							      <c:if test="${ispacksubmit==0}">
							       <input  style="width: 200px;" id="overday" name="overday" readonly="readonly" value="${overday}">
							     </c:if>
								
							</td>
						</tr>
						 <c:if test="${ispacksubmit==0}">
						<tr>
									<td class="label">
										确认状态：
									</td>
									<td>
										<input type="checkbox" value="1" id="state" name="state">
										已确认 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="color: red;">确认之后无法修改</span>
									</td>
								</tr>
						</c:if>
						</tbody>
				</table>
				    
				   
		        </div>
			</div>
			 </c:if>
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
		<jsp:include page="../common/projectshowdiv.jsp"></jsp:include>
		<jsp:include page="../common/prosuspendapplication.jsp"></jsp:include>
	</body>
</html>