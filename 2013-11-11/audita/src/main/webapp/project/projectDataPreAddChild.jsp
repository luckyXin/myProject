<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_edit.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/default/easyui.css" type="text/css"></link>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/project/projectDataPreAddChild.js"></script>
		<title>资料预审管理</title>
		<style type="text/css">
		body {
			margin: 0px;
			padding: 0px;
			width: 100%;
			height: 100%;
		}
		.cancel a
		{
		  background-color: red;
		}
		</style>
	</head>
	<body>
	<form id="form" method="post">
		<div id="mainDiv" style="margin:3px;"  style="width: 98%;">
		 <div id="addp1" class="easyui-panel" style="background: #fafafa;" iconCls="icon-edit" collapsible="true" title="资料管理">
			<div style="margin:1px;">
				<a  href="javascript:void(0);"  id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
		   </div>
				<table class="form" style="width: 99%" id="mytableid" cellpadding="0" cellspacing="0">
					<tbody style="border: 1px solid #ccc">
					 <tr>
					         <td align="right" class="label">
								送审编号 ：
							</td>
							<td colspan="3">
								<input class="easyui-validatebox"   required="true"  missingMessage="送审编号不能为空 "  style="width: 200px;" id="datapreno" name="datapreno">
							</td>
					    </tr>
						<tr>
						     <input type="hidden"  id="pid" name="pid" value="${datapre.id}">
						     <input type="hidden" id="frameid" value="${requestScope.moduelid}">
						     <input type="hidden" id="path" value="<%=request.getContextPath()%>">
						     <td align="right" class="label">
								项目名称 ：
							</td>
							<td colspan="3">
								<input class="easyui-validatebox"  validType="ishave"  required="true"   missingMessage="项目名称不能为空"  style="width: 550px;" id="projectName" name="projectName">
							</td>
						</tr>
						<tr>
						<td align="right" class="label">
								审计报告文号 ：
							</td>
							<td colspan="3">
								<input class="easyui-validatebox"     style="width: 550px;" id="reportNo" name="reportNo">
							</td>
						</tr>
						<tr>
						  <td align="right" class="label">
								立项文号 ：
							</td>
							<td colspan="3">
								<input class="easyui-validatebox"   style="width: 550px;" id="projectNo" name="projectNo">
							</td>
						</tr>
						<tr>
						    <td align="right" class="label">
								立项时间 ：
							</td>
							<td>
								<input class="easyui-datebox" editable="false" style="width: 205px;" id="projectTime" name="projectTime">
							</td>
							<td class="label" align="right">
								批复金额：
							</td>
							<td >
							<input type="text"   style="width: 200px;" id="auditMoney" name="auditMoney">
							
							</td>
						
						</tr>
						<tr>
						     <td align="right" class="label">
								直接费用 (万元)：
							</td>
							<td>
								<input type="text"   style="width: 200px;" id="zjMoney" name="zjMoney">
							</td>
							<td class="label" align="right">
								中标价：
							</td>
							<td >
							<input type="text"   style="width: 200px;" id="zbMoney" name="zbMoney">
							
							</td>
						</tr>
						<tr>
						     <td align="right" class="label">
								合同价 ：
							</td>
							<td>
								<input type="text"   style="width: 200px;" id="htmoney" name="htmoney">
							</td>
							<td class="label" align="right">
								其中暂列金额：
							</td>
							<td >
							<input type="text"   style="width: 200px;" id="zhanliemoney" name="zhanliemoney">
							
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								项目业主 ：
							</td>
							<td >
							    <input type="hidden"  style="width: 200px;"  name="proownerid" value="${datapre.proownerid}">
								<input type="text"  readonly="readonly" style="width: 200px;"  value="${ownerName}">
							</td>
						</tr>
						<tr>
						    <td align="right" class="label">
								项目业主联系人 ：
							</td>
							<td>
							      <input type="text" id="proownerlink" style="width: 200px;" name="proownerlink"  value="${datapre.proownerlink}">
							   
							</td>
							<td class="label" align="right">
								项目业主联系电话 ：
							</td>
							<td >
							<input type="text" id="proownertelphone" style="width: 200px;" name="proownertelphone"  value="${datapre.proownertelphone}">
							</td>
						</tr>
						<tr>
						   <td align="right" class="label">
								施工企业 ：
							</td>
							<td colspan="3">
							   <input type="text"  style="width: 550px;" id="constructName" name="constructName">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								施工企业联系人 ：
							</td>
							<td >
								<input type="text" id="linktext" style="width: 200px;" name="constructlinktext">
								 <select style="width: 205px;display: none;"  id="constructlink" name="constructlink" onchange="constructlinkchange();">
								  </select>
							</td>
							<td align="right" class="label">
								施工企业联系人电话 ：
							</td>
							<td>
								<input type="text"  style="width: 200px;" id="telphonetext" name="constructtelphonetext">
							<input type="text" readonly="readonly" style="width: 200px;display: none;" id="constructtelphone" name="constructtelphone">
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
								审计类型 ：
							</td>
							<td >
								<select  id="audittype" name="audittype" style="width: 205px;">
								     <option value="" selected="selected"></option>
								     <c:forEach items="${audittypelist}" var="type">
								       <option value="${type.id}">${type.dictionaryname }</option>
								     
								     </c:forEach>
								</select>
							</td>
							<td align="right" class="label">
								接收资料时间 ：
							</td>
							<td>
									<input class="easyui-datebox"       style="width: 205px;" id="datapretime" name="datapretime">
							</td>
						</tr>
						<tr>
							
							<td class="label" align="right">
								状态 ：
							</td>
							<td >
								<select  id="state" name="state" style="width: 205px;">
								        <option value="0">
								                        启用
										</option>
										 <option value="1">
								                        禁用
										</option>
										
								</select>
							</td>
							
							
						</tr>
						<tr>
								<td class="label" align="right">
									概算文号 ：
								</td>
								<td>
									<input type="text" id="budgetInfo" class="easyui-validatebox"
										name="budgetInfo" style="width: 200px;">
								</td>
								<td class="label" align="right">
									概算总金额：
								</td>
								<td>
									<input type="text" id="budgetTotalMoney"
										class="easyui-validatebox" name="budgetTotalMoney"
										style="width: 200px;" >
								</td>
								
							</tr>
							<tr>
								
								<td class="label" align="right">
									概算附件：
								</td>
								<td colspan="3">
									<input type="file" id="budgetInfoFile" name="budgetInfoFile"
										style="width: 400px;">
								</td>
							</tr>
							<tr>
								<td class="label" align="right">
									概算工程直接费用(万元)：
								</td>
								<td>
									<input type="text" id="budgetDirectMoney"
										class="easyui-validatebox" name="budgetDirectMoney"
										style="width: 200px;">
								</td>
								<td class="label" align="right">
									概算批改时间：
								</td>
								<td>
									<input  id="budgetUpdateTime"
										class="easyui-datebox" name="budgetUpdateTime"
										style="width: 200px;">
								</td>
							</tr>
						<tr>
							<td class="label" align="right">
								资料预审意见 ：
							</td>
							<td colspan="3">
									<textarea id="datapreopinion" class="text" style="width: 100%;"
										rows="4" name="datapreopinion">
								</textarea>
							</td>
							
						</tr>
						<tr>
							<td class="label" align="right">
								资料预审人员 ：
							</td>
							<td >
							   <input type="text" readonly="readonly" style="width: 200px;" value="${sessionScope.user.username}" id="datapreOperate" name="datapreOperate">
							</td>
							<td align="right" class="label">
								送审金额(元) ：
							</td>
							<td>
								 <input class="easyui-validatebox" required="true"   missingMessage="送审金额不能为空"  style="width: 200px;" id="sentAmount" name="sentAmount">
							</td>
						</tr>
						<tr id="files">
							<td class="label" align="right">送审资料文档：</td>
							<td colspan="3" align="left">
							<input type="file" style="width: 400px;" class="text" id="file" name="uploadFile" value="浏览" /> <a class="easyui-linkbutton" iconCls="icon-add"  id="addfile">增加</a>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">
							             是否确认提交：
							</td>
							
							<td colspan="2">
								<input type="radio" name="isconfirmSubmit" value="1">是
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="isconfirmSubmit" value="0" checked="checked">否
								&nbsp;&nbsp;
								<span style="color: red;">提交后不能修改</span>
							</td>
							<td >
								<a  href="javascript:void(0);" id="projectfinish" onclick="projectfinish()" class="easyui-linkbutton" iconCls="icon-print">项目竣工决算单</a>
							    <a  href="javascript:void(0);" id="projoinlist" onclick="projoinlist()"  class="easyui-linkbutton" iconCls="icon-save">交接清单</a>
							</td>
						</tr>
						</tbody>
				</table>
			</div>
			</div>
			</form>
			<jsp:include page="../common/showdiv.jsp"></jsp:include>
	</body>
</html>