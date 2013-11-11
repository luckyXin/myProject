<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>中介机构选择</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/legalsectionChiefAuditIntermediray.js"></script>
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
			     <div  class="easyui-panel"
					style="background: #fafafa;"
					collapsible="true" title="项目基本信息" iconCls="icon-tip">
				   <div id="pp1">
					<table class="form" style="width: 98%;margin: 0px;"  cellpadding="3" cellspacing="0">
					    
					<tbody style="border: 1px solid #ccc">
					    <tr>
					        <td colspan="4" class="labelbase">
					          <b>项目资料信息</b>
					        </td>
					     </tr>
					     <tr>
						    <td align="right" class="showLabel">
								送审编号：
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
						</tbody>
				</table>
			
				</div>
				
			
				</div>
				<div  class="easyui-panel"
					style="background: #fafafa; height: 100px;"
					collapsible="true" title="中介机构录入" iconCls="icon-tip">
					<div style="margin:3px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%; border-style: none;"
						id="mytableid" cellpadding="0" cellspacing="0">
						 <input type="hidden" id="arrangeId" name="arrangeId" value="${arrageId}">
						<tbody>
								<tr>
									<td class="label">
										中介机构名称：
									</td>
									<td >
									    <input type="hidden" id="intermediary" name="intermediary" value="${singlePro.intermediaryId}">
									    <input type="text" id="intermediaryName" readonly="readonly" name="intermediaryName" style="width: 200px;" value="${singlePro.intermediaryName}">
									</td>
									
								</tr>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			 <input type="hidden" id="frameid" value="${requestScope.moduelid}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>
