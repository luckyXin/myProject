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
			src="<%=request.getContextPath()%>/js/project/tractProjectCreateEdit.js"></script>
		<title>跟踪审计项目立项</title>
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
				collapsible="true" title="立项信息" iconCls="icon-search">
					<div style="margin: 1px;">
						<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
							iconCls="icon-save">保存</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form" style="width: 90%" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tbody style="border: 1px solid #ccc">
						    <tr>
						    <td align="right" class="label">
						                   工程编码 ：
						    </td>
						    <td colspan="3">
						       <input class="easyui-validatebox" required="true" value="${tractProject.projectNumber}" 
										 style="width: 200px;" id="projectNumber" name="projectNumber" maxlength="40">
						    </td>
						    </tr>
							<tr>
								<input type="hidden" id="frameid" value="${id}">
								<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
								<input type="hidden" id="isconfirmSubmit" name="isconfirmSubmit" value="${tractProject.submitState}" />
								<input type="hidden" id="id" name="id" value="${tractProject.id}" />
								<td align="right" class="label">
									项目名称 ：
								</td>
								<td>
									<input class="easyui-validatebox"  validType="ishave"  required="true"
										value="${tractProject.projectName}" missingMessage="项目名称不能为空" 
										 style="width: 200px;" id="projectName" name="projectName">
								</td>
								<td class="label" align="right">
									工程类型：
								</td>
								<td>
									<select id="projectType" name="projectType"
										style="width: 200px;">
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
									<input class="easyui-validatebox"
										style="width: 400px;" id="projectNo" name="projectNo"
										value="${tractProject.projectCreateNo}">
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									立项时间 ：
								</td>
								<td>
									<input class="easyui-datebox" editable="false" style="width: 200px;" id="projectCreateTime" name="projectCreateTime" 
									value="${tractProject.projectCreateTime}">
								</td>
								<td align="right" class="label">
									立项批复金额(万元) ：
								</td>
								<td>
									<input class="easyui-numberbox" precision='6'
										value="${tractProject.sentAuditMoney}"
										style="width: 200px;" id="sentAuditMoney" name="sentAuditMoney">
								</td>
							</tr>
							<tr>
							    <td align="right" class="label">
									概算文号：
								</td>
								<td colspan="3">
									<input class="easyui-validatebox"
										value="${tractProject.budgetCode}" missingMessage="项目名称不能为空" 
										 style="width: 400px;" id="budgetCode" name="budgetCode" >
								</td>
							</tr>
							<tr>
							    <td align="right" class="label">
									概算批复金额(万元)：
								</td>
								<td>
								<input class="easyui-numberbox" precision='6'
									value="${tractProject.budgetMoney}" style="width: 200px;"
									id="budgetMoney" name="budgetMoney">
							</td>
							    <td align="right" class="label">
									直接费用(万元) ：
								</td>
								<td>
									<input class="easyui-numberbox" precision='6'
										value="${tractProject.directMoney}" 
										style="width: 200px;" id="directMoney" name="directMoney">
								</td>
							</tr>
							
							<tr>
								<td class="label" align="right">
									项目业主 ：
								</td>
								<td>
									<input type="hidden" id="ownerId" name="ownerId" value="${tractProject.ownerId}">
									<input type="text" readonly="readonly" style="width: 200px;"
										id="ownerName" name="ownerName" value="${tractProject.ownerName}">
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									项目业主联系人 ：
								</td>
								<td>
									<input class="ownerlinkusertext" id="proownerlinkname" type="text" readonly="readonly" style="width: 200px;"
										name="proownerlinkname" value="${tractProject.ownerLinker}">
									<select class="ownerlinkuserseleect" style="width: 205px; display: none;" id="proownerlink"
										name="proownerlinkid" onchange="proownerlinkchange()">
									</select>
								</td>
								<td class="label" align="right">
									项目业主联系电话 ：
								</td>
								<td>
									<input type="text" readonly="readonly" style="width: 200px;"
										id="proownertelphone" name="proownertelphone" value="${tractProject.ownerTelPhone}">
								</td>
							</tr>
							<tr>
							    <td class="label" align="right">
									建设规模 ：
								</td>
								<td>
								    <input class="easyui-validatebox"
										value="${tractProject.jianSheGuiMo}" style="width: 200px;" id="jianSheGuiMo" name="jianSheGuiMo">
								</td>
								<td class="label" align="right">
									建设时间 ：
								</td>
								<td>
								    <input class="easyui-datebox" editable="false"
										value="${tractProject.jianSheShiJian}" style="width: 200px;" id="jianSheShiJian" name="jianSheShiJian">
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									备注 ：
								</td>
								<td colspan="3">
									<textarea id="remark" class="text" style="width: 99%"
										rows="4" name="remark">
										${tractProject.remark}
								    </textarea>
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									立项人员 ：
								</td>
								<td>
									<input type="text" readonly="readonly" style="width: 200px;"
										value="${tractProject.createUserAccount}" id="createUserAccount"
										name="createUserAccount">
								</td>
							</tr>
							<tr id="files">
								<td class="label" align="right">
									立项文件 ：
								</td>
								<td colspan="3" align="left">
									<input type="file" style="width: 400px;" class="text" id="file"
										name="uploadFile" value="浏览" />
									<a class="easyui-linkbutton" iconCls="icon-add" id="addfile">增加</a>
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									是否确认提交：
								</td>
								<td colspan="2">
								<c:if test="${tractProject.submitState != 1}">
										<input type="radio" name="submitState" value="1">是
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="submitState" value="0"
											checked="checked">否
											&nbsp;&nbsp;
								</c:if>
								<c:if test="${tractProject.submitState == 1}">
										<input type="radio" name="submitState" value="1"
											checked="checked">是
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="submitState" value="0">否
											&nbsp;&nbsp;
								</c:if>
								</td>
							</tr>
						</tbody>
					</table>
					<table id="grid"></table>
				</div>
			
			<div id="otherInfo">
			<div id="p3" class="easyui-panel"
				style="background: #fafafa; margin-bottom: 5px;padding-bottom: 5px;"
				collapsible="true" title="标段信息" iconCls="icon-search">
				<div style="margin: 1px;">
						<a href="javascript:void(0);" id="addBiaoDuan" class="easyui-linkbutton" iconCls="icon-save">增加</a>
						<a href="javascript:void(0);" id="updateBiaoDuan" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" id="delBiaoDuan" iconCls="icon-cancel">删除</a>
				</div>
				<table id="gridBiaoDuan"></table>
			</div>
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>