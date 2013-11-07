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
			src="<%=request.getContextPath()%>/js/project/suopeiedit.js"></script>
		<title>索赔添加</title>
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
				style="background: #fafafa; height: 500px; margin-bottom: 5px;"
				collapsible="true" title="索赔信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
				</div>
				<input type="hidden" id="frameid" value="${id}">
				<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
				<input type="hidden" id="id" name="id" value="${claimIndemnity.id}" />
				<input type="hidden" id="biaoDuanId" name="biaoDuanId" value="${biaoDuanId}" />
				<table class="form" style="width: 99%" id="mytableid"
					cellpadding="0" cellspacing="0">
					<tr>
						<td class="label">
							索赔类型：
						</td>
						<td>
							<select style="width: 206px;" id="claimIndemnityType"
								name="claimIndemnityType">
								<option value=""></option>
								<c:forEach items="${listsptype}" var="type">
									<c:if test="${type.id == claimIndemnity.claimIndemnityType}">
										<option value="${type.id}" selected="selected">
											${type.dictionaryname}
										</option>
									</c:if>
									<c:if test="${type.id != claimIndemnity.claimIndemnityType}">
										<option value="${type.id}">
											${type.dictionaryname}
										</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td class="label">
							施工方索赔金额：
						</td>
						<td>
							<input type="text" id="constructSentMoney"
								name="constructSentMoney" style="width: 200px;"
								value="${claimIndemnity.constructSentMoney }" />
						</td>
					</tr>
					<tr>
						<td class="label">
							事务所审核金额：
						</td>
						<td>
							<input type="text" id="indemnityMoney" name="indemnityMoney"
								style="width: 200px;" value="${claimIndemnity.indemnityMoney }" />
						</td>
						<td class="label">
							审计建议金额：
						</td>
						<td>
							<input type="text" id="auditMoney" name="auditMoney"
								style="width: 200px;" value="${claimIndemnity.auditMoney}" />
						</td>
					</tr>
					<tr>
						<td class="label">
							业主确定价：
						</td>
						<td>
							<input type="text" id="ownerReadyMoney" name="ownerReadyMoney"
								style="width: 200px;" value="${claimIndemnity.ownerReadyMoney}" />
						</td>
					</tr>
					<tr>
						<td class="label">
							索赔事项：
						</td>
						<td>
						    <textarea rows="3" cols="10" style="width: 200px" id="claimitem" name="claimitem">
						       ${claimIndemnity.claimitem}
						    </textarea>
							
						</td>
						<td class="label">
							情况说明：
						</td>
						<td>
							  <textarea rows="3" cols="10" style="width: 200px" id="caseexplain" name="caseexplain">
						       ${claimIndemnity.caseexplain}
						    </textarea>
						</td>
					</tr>
					<tr>
						<td class="label">
							审核明细：
						</td>
						<td>
							  <textarea rows="3" cols="10" style="width: 200px" id="auditdetail" name="auditdetail">
						       ${claimIndemnity.auditdetail}
						    </textarea>
						</td>
					</tr>
			</table>
			</div>
		</form>
	</body>
</html>