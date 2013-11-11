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
			src="<%=request.getContextPath()%>/js/project/tractProjectChangeCardInfoEdit.js"></script>
		<title>变更签证编辑</title>
		<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

.cancel a {
	background-color: red;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="p1" class="easyui-panel"
				style="background: #fafafa; margin-bottom: 5px;"
				collapsible="true" title="变更签证基础信息" iconCls="icon-search">
				<div style="margin: 2px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
				</div>
				<table class="form" style="width: 99%;margin-bottom: 10px;" id="mytableid"
					cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
						<tr>
							<input type="hidden" id="frameId" value="${id}">
							<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
							<input type="hidden" id="id" name="id" value="${chanegCardInfo.id}" />
							<td align="right" class="label">
								变更签证编号 ：
							</td>
							<td>
								<input class="easyui-validatebox" required="true"
									value="${chanegCardInfo.changeCode}" style="width: 200px;"
									id="changeCode" name="changeCode">
							</td>
							<td align="right" class="label">
								变更时间 ：
							</td>
							<td>
							     <input class="easyui-datebox" required="true"
									value="${chanegCardInfo.changeTime}" style="width: 200px;"
									id="changeTime" name="changeTime">
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								变更内容：
							</td>
							<td colspan="3">
								<textarea id="changeContext" class="text" style="width: 80%"
										rows="4" name="changeContext" >
										${chanegCardInfo.changeContext}
								</textarea>
							</td>
						</tr>
						<tr>
						    <td class="label" align="right">
								施工单位报送变更金额：
							</td>
							<td>
								<input style="width: 200px;" class="easyui-numberbox" precision='2'
									id="constructSentMoney" name="constructSentMoney"
									value="${chanegCardInfo.constructSentMoney}">
							</td>
							<td class="label" align="right">
								跟踪审计建议变更金额 ：
							</td>
							<td>
								<input style="width: 200px;" class="easyui-numberbox" precision='2'
									id="tractAuditAdviceMoney" name="tractAuditAdviceMoney"
									value="${chanegCardInfo.tractAuditAdviceMoney}">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								确认变更金额：
							</td>
							<td>
								<input  style="width: 200px;" class="easyui-numberbox" precision='2'
									id="affirmChangeMoney" name="affirmChangeMoney"
									value="${chanegCardInfo.affirmChangeMoney}">
							</td>
							<td class="label" align="right">
								签证类型：
							</td>
							<td>
								<select id="changeType" name="changeType" style="width: 200px;">
								   <c:if test="${empty chanegCardInfo.changeType}">
								    <option value="0" selected="selected">清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								    <c:if test="${chanegCardInfo.changeType == '0'}">
								    <option value="0" selected="selected">清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								    <c:if test="${chanegCardInfo.changeType == '1'}">
								    <option value="0" >清单漏项</option>
								    <option value="1" selected="selected">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								    <c:if test="${chanegCardInfo.changeType == '2'}">
								    <option value="0" >清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2" selected="selected">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								    <c:if test="${chanegCardInfo.changeType == '3'}">
								    <option value="0" >清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3" selected="selected">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								    <c:if test="${chanegCardInfo.changeType == '4'}">
								    <option value="0" >清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4" selected="selected">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								   <c:if test="${chanegCardInfo.changeType == '5'}">
								    <option value="0" >清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5" selected="selected">其他</option>
								   </c:if>
								   <c:if test="${chanegCardInfo.changeType == '6'}">
								    <option value="0" >清单漏项</option>
								    <option value="1">清单量差</option>
								    <option value="2">设计变更</option>
								    <option value="6" selected="selected">政策规范性设计变更</option>
								    <option value="3">技术核定 </option>
								    <option value="4">现场签证</option>
								    <option value="5">其他</option>
								   </c:if>
								</select>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								变更及现场签证履行程序情况：
							</td>
							<td>
							 <c:if test="${chanegCardInfo.changeAndNowSiteInfo == '1'}">
										 <input type="checkbox" value="1" name="changeAndNowSiteInfo" id="changeAndNowSiteInfo" checked="checked">
										 <span style="color: red;">选中表示已施工,否则表示施工中</span>
							</c:if>
							<c:if test="${empty chanegCardInfo.changeAndNowSiteInfo}">
										 <input type="checkbox" value="1" name="changeAndNowSiteInfo" id="changeAndNowSiteInfo">
										  <span style="color: red;">选中表示已施工,否则表示施工中</span>
							</c:if>		
							</td>
							<td class="label" align="right">
								变更执行情况：
							</td>
							<td>
								<input class="easyui-validatebox" style="width: 200px;"
									id="changeProccessCondition" name="changeProccessCondition"
									value="${chanegCardInfo.changeProccessCondition}">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>