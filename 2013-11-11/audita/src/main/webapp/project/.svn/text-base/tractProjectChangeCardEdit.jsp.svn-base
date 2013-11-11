<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			src="<%=request.getContextPath()%>/js/project/tractProjectChangeCardEdit.js"></script>
		<title>跟踪审计项目立项-变更签证录入</title>
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
				style="background: #fafafa; height: 300px; margin-bottom: 5px;"
				collapsible="true" title="基础信息" iconCls="icon-search">
					<div style="margin: 1px;">
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 99%" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tbody style="border: 1px solid #ccc">
							<tr>
								<input type="hidden" id="frameId" value="${id}">
								<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
								<input type="hidden" id="isconfirmSubmit" name="isconfirmSubmit" value="${tractProject.submitState}" />
								<input type="hidden" id="id" name="id" value="${tractProject.id}" />
								<td align="right" class="label">
									项目名称 ：
								</td>
								<td>
									${tractProject.projectName}
								</td>
								<td class="label" align="right">
									工程类型：
								</td>
								<td>
									<select id="projectType" name="projectType"
										style="width: 200px;" disabled="disabled">
										<c:if test="${tractProject.projectType != 1 && tractProject.projectType != 2}">
										<option value="0" selected="selected">招标</option>
										<option value="1">EPC</option>
										<option value="2">BT</option>
										</c:if>
										<c:if test="${tractProject.projectType == 1}">
										<option value="0">招标</option>
										<option value="1" selected="selected">EPC</option>
										<option value="2">BT</option>
										</c:if>
										<c:if test="${tractProject.projectType == 2}">
										<option value="0">招标</option>
										<option value="1" >EPC</option>
										<option value="2" selected="selected">BT</option>
										</c:if>
									</select>
								</td>
							</tr>
							<tr>
							   <td align="right" class="label">
									立项文号 ：
								</td>
								<td colspan="3">
									${tractProject.projectCreateNo}
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									立项时间 ：
								</td>
								<td>
									${tractProject.projectCreateTime}
								</td>
								<td align="right" class="label">
									立项批复金额(万元) ：
								</td>
								<td>
									${tractProject.sentAuditMoney}
								</td>
							</tr>
							<tr>
							    <td align="right" class="label">
									概算文号：
								</td>
								<td colspan="3">
									${tractProject.budgetCode}
								</td>
							</tr>
							<tr>
							    <td align="right" class="label">
									概算批复金额(万元)：
								</td>
								<td>
									${tractProject.budgetMoney}
								</td>
							    <td align="right" class="label">
									直接费用(万元) ：
								</td>
								<td>
									${tractProject.directMoney}
								</td>
							</tr>
							
							<tr>
								<td class="label" align="right">
									项目业主 ：
								</td>
								<td>
									${tractProject.ownerName}
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									项目业主联系人 ：
								</td>
								<td>
									${tractProject.ownerLinker}
								</td>
								<td class="label" align="right">
									项目业主联系电话 ：
								</td>
								<td>
									${tractProject.ownerTelPhone}
								</td>
							</tr>
							<tr>
							    <td class="label" align="right">
									建设规模 ：
								</td>
								<td>
								    ${tractProject.jianSheGuiMo}
								</td>
								<td class="label" align="right">
									建设时间 ：
								</td>
								<td>
								    ${tractProject.jianSheShiJian}
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									备注 ：
								</td>
								<td colspan="3">
									<textarea id="remark" class="text" style="width: 99%"
										rows="4" name="remark" disabled="disabled">
										${tractProject.remark}
								    </textarea>
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									立项人员 ：
								</td>
								<td>
									${tractProject.createUserAccount}
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<table id="grid"></table>
			<div id="otherInfo">
			<div id="p3" class="easyui-panel"
				style="background: #fafafa; height: 300px; margin-bottom: 5px;"
				collapsible="true" title="标段信息" iconCls="icon-search">
				<div style="margin: 1px;">
						<a href="javascript:void(0);" id="addBiaoDuan" class="easyui-linkbutton" iconCls="icon-edit">变更签证录入</a>
				</div>
				<table id="gridBiaoDuan"></table>
			</div>
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>