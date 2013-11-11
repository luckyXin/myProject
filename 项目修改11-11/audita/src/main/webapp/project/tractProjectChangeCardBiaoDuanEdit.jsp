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
			src="<%=request.getContextPath()%>/js/project/tractProjectChangeCardBiaoDuanEdit.js"></script>
		<title>月报录入</title>
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
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="标段基本信息" iconCls="icon-search">
					<table class="form" style="width: 99%" id="mytableid"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<input type="hidden" id="frameid" value="${id}">
								<input type="hidden" id="coutextPath"
									value="<%=request.getContextPath()%>">
								<input type="hidden" id="biaoDuanId" name="biaoDuanId"
									value="${proejctBiaoDuanInfo.id}" />
								<td align="right" class="label">
									标段名称 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.biaoDuanName}
								</td>
								<td align="right" class="label">
									项目概况 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.projectGaiKuang}
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									监理单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.supervisorUtil}
								</td>
								<td class="label" align="right">
									建管单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildManageUtil}
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									勘察单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.prospectUtil}
								</td>
								<td class="label" align="right">
									建设规模 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildContent}
								</td>
							</tr>
							<tr>
								<td align="right" class="label">
									建设单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.buildUtil}
								</td>
								<td class="label" align="right">
									设计单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.designUtil}
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									施工单位 ：
								</td>
								<td>
									${proejctBiaoDuanInfo.constructUtil}
								</td>
								<td class="label" align="right">
									预算控制价(万元) ：
								</td>
								<td>
									${proejctBiaoDuanInfo.preAuditMoney}
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									中标合同价(万元) ：
								</td>
								<td>
									${proejctBiaoDuanInfo.zhongBiaoMoney}
								</td>
								<td class="label" align="right">
									实际开工时间 ：
								</td>
								<td>
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
				<div id="p13" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;"
					collapsible="true" title="月报信息列表" iconCls="icon-search">
					<div style="margin: 1px;">
						<a href="javascript:void(0);" id="add" class="easyui-linkbutton"
							iconCls="icon-add">增加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
							id="update" iconCls="icon-edit">编辑</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
							id="delete" iconCls="icon-cut">删除</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
						签证类型：
						<select id="changeType" name="changeType">
						     <option value="" selected="selected">全部</option>
						     <option value="0" >清单漏项</option>
						     <option value="1">清单量差</option>
						     <option value="2">设计变更</option>
						     <option value="6">政策规范性设计变更</option>
						     <option value="3">技术核定 </option>
						     <option value="4">现场签证</option>
						     <option value="5">其他</option>
						</select>
					</div>
					<table id="grid"></table>
				</div>
				<!-- 
				<div id="p12" class="easyui-panel"
					style="background: #fafafa;"
					collapsible="true" title="变更签证文件" iconCls="icon-search">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">导入</a>
					<table class="form" style="width: 99%" cellpadding="0" cellspacing="0" id="">
						<tr>
							<td align="right" class="label">
								导入时间：
							</td>
							<td>
								<input class="easyui-datebox" style="width: 200px;"
									id="createTime" name="createTime" value="${nowTime}">
							</td>
							<td align="right" class="label">
								操作人员：
							</td>
							<td>
							   <input style="width: 200px;" readonly="readonly"
									id="createUserAccount" name="createUserAccount" value="${nowUser}">
							</td>
						</tr>
						<tr>
							<td align="right" class="label">
								签证导入表：
							</td>
							<td colspan="3">
								<input type="file" style="width: 400px;" class="text" id="file"
									name="monthReportFile" value="浏览" />
							</td>
						</tr>
						<tr id="files" width="20%">
							<td class="label" align="right">
								附件 ：
							</td>
							<td colspan="3" align="left">
								<input type="file" style="width: 400px;" class="text" id="file"
									name="uploadFile" value="浏览" />
								<a class="easyui-linkbutton" iconCls="icon-add" id="addfile">增加</a>
							</td>
						</tr>
					</table>
				</div>
				<table id="gridFile"></table>
			     -->
			</div>
		</form>
		<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>