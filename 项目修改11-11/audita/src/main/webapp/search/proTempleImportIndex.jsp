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
			src="<%=request.getContextPath()%>/js/search/proTempleImportIndex.js"></script>
		<title>模板导入</title>
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
		<div id="mainDiv">
				<div id="p10" class="easyui-panel"
				style="background: #fafafa; height: 150px; margin-bottom: 5px;"
				collapsible="true" title="标段基本信息" iconCls="icon-search">
					<table class="form" style="width: 99%"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<input type="hidden" id="frameid" name="id" value="${id}">
								<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
								<input type="hidden" id="biaoDuanId" name="biaoDuanId" value="${proejctBiaoDuanInfo.id}"/>
								<td align="right" class="showLabel">
									标段名称 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.biaoDuanName}
								</td>
								<td align="right" class="showLabel">
									项目概况 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.projectGaiKuang}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									监理单位：
								</td>
								<td>
									${proejctBiaoDuanInfo.supervisorUtil}
								</td>
								<td class="showLabel" align="right">
									建管单位：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildManageUtil}
								</td>
							</tr>
							<tr>
								<td class="showLabel" align="right">
									勘察单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.prospectUtil}
								</td>
								<td class="showLabel" align="right">
									建设规模 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildContent}
								</td>
							</tr>
							<tr>
								<td align="right" class="showLabel">
									建设单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildUtil}
								</td>
								<td class="showLabel" align="right">
									设计单位：
								</td>
								<td>
									${proejctBiaoDuanInfo.designUtil}
								</td>
							</tr>
							<tr>
							    <td class="showLabel" align="right">
									施工单位 ：
								</td>
								<td>
								    ${proejctBiaoDuanInfo.constructUtil}
								</td>
								<td class="showLabel" align="right">
									预算控制价(万元) ：
								</td>
								<td>
								   ${proejctBiaoDuanInfo.preAuditMoney}
								</td>
							</tr>
							<tr>
								<td class="showLabel" align="right">
									中标合同价(万元) ：
								</td>
								<td >
								    ${proejctBiaoDuanInfo.zhongBiaoMoney}
								</td>
								<td class="showLabel" align="right">
									实际开工时间 ：
								</td>
								<td >
								    ${proejctBiaoDuanInfo.reallyStartTime} 
								</td>
							</tr>
							<tr>
								<td class="showLabel" align="right">
									含预留金额(万元) ：
								</td>
								<td colspan="3">
									<input class="easyui-numberbox" precision='6' maxlength="20"
										value="${proejctBiaoDuanInfo.zhongBiaoObligateMoney}"
										style="width: 200px;" id="zhongBiaoObligateMoney"
										name="zhongBiaoObligateMoney">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		
			    <div id="p11" class="easyui-panel"
				style="background: #fafafa; margin-bottom: 5px;"
				collapsible="true" title="模板导入列表" iconCls="icon-search">
				<div style="margin: 1px;">
				
							<a href="javascript:void(0);" id="add" iconCls="icon-add"
								class="easyui-linkbutton">导入</a>
					
					<table id="mytableImport">
						
					
					</table>
				</div>
			</div>
			
			</div>
		</form>
	</body>
</html>