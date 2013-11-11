<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>分管领导安排信息确认</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/project/areaLeaderArrangeInfoAffirmEdit.js"></script>
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
					<table class="form"
						style="width: 99%; border-style: none; margin: 0px;"
						cellpadding="3" cellspacing="0">
						<tbody>
							<input type="hidden" id="arrangeId" name="arrangeId" value="${projectInfo.arrangeId}">
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
							<!--<td align="right" class="showLabel">
								分配项目时间 ：
							</td>
							<td>
								${projectInfo.projectTime}
							</td>
						--></tr>
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
			</tbody>
					</tbody>
					</table>
				</div>
				<table id="areamyfile"></table>
				<div id="p3" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="安排信息确认" iconCls="icon-tip">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%; border-style: none; margin-bottom: 10px;"
						id="mytableid" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td align="right" class="label">
									是否加急：
								</td>
								<td>
									<select id="isUrgent" name="isUrgent" style="width: 200px;">
										<c:if test="${arrangeInfo.isUrgent == '0'}">
											<option value=""></option>
											<option value="0" selected="selected">
												否
											</option>
											<option value="1">
												是
											</option>
										</c:if>
										<c:if test="${arrangeInfo.isUrgent == '1'}">
											<option value=""></option>
											<option value="0">
												否
											</option>
											<option value="1" selected="selected">
												是
											</option>
										</c:if>
										<c:if test="${arrangeInfo.isUrgent != 1 && arrangeInfo.isUrgent != 0}">
											<option value=""></option>
											<option value="0">
												否
											</option>
											<option value="1">
												是
											</option>
										</c:if>
									</select>
								</td>
								<td align="right" class="label">
									限时：
								</td>
								<td>
								   <input class="easyui-datebox" style="width: 200px;" id="timeLimit" name="timeLimit" value="${arrangeInfo.timeLimit}">
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									分管领导意见：
								</td>
								
								
								<td>
								     <textarea rows="5" cols="10"  style="width: 500px;"  id="areaauditRemark" name="areaauditRemark">
								       ${arrangeInfo.areaauditRemark}
								     </textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${moduelId}">
			<input type="hidden" id="noUpdate" value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title" value="<spring:message code='prompt.title.title'/>">
		</form>
	</body>
</html>
