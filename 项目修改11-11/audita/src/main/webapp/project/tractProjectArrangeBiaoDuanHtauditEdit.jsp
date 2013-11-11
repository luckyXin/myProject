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
			src="<%=request.getContextPath()%>/js/project/tractProjectArrangeBiaoDuanHtauditEdit.js"></script>
		<title>标段创建</title>
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
									中标合同价 (万元)：
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
				collapsible="true" title="合同审核条款信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
					<table class="form" style="width: 99%" id="htaudittable"
						cellpadding="0" cellspacing="0">
						
						<c:if test="${empty htlist}">
						<input type="hidden" id="saveorupdate" value="0"/>
						   <tr>
							<td class="label" align="right">地勘合同：</td>
							<td  align="left">
							<input type="hidden" value="地勘合同" name="dikanht">
							<input type="file" style="width: 300px;" class="text" id="dikanhtfile" name="dikanhtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="dikanexistproblem" name="dikanexistproblem" style="width: 300px;"></textarea>
							</td>
							
						</tr>
						<tr>
						   <td class="label" align="right">审核意见：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="dikanauditview" name="dikanauditview" style="width: 300px;"></textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="dikanexecutecase" name="dikanexecutecase" style="width: 300px;"></textarea>
							</td>
						</tr>
					
						 <tr>
							<td class="label" align="right">设计合同：</td>
							<td  align="left">
							<input type="hidden" value="设计合同" name="shejiht">
							<input type="file" style="width: 300px;" class="text" id="shejihtfile" name="shejihtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="shejiexistproblem" name="shejiexistproblem" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="shejiauditview" name="shejiauditview" style="width: 300px;"></textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="shejiexecutecase" name="shejiexecutecase" style="width: 300px;"></textarea>
							</td>
						</tr>
						 <tr>
							<td class="label" align="right">项目管理合同：</td>
							<td  align="left">
							<input type="hidden" value="项目管理合同" name="proht">
							<input type="file" style="width: 300px;" class="text" id="prohtfile" name="prohtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td  align="left">
							  <textarea rows="3" cols="10" id="proexistproblem" name="proexistproblem" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="proauditview" name="proauditview" style="width: 300px;"></textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="proexecutecase" name="proexecutecase" style="width: 300px;"></textarea>
							</td>
						</tr>
						 <tr>
							<td class="label" align="right">监理合同：</td>
							<td align="left">
							<input type="hidden" value="监理合同" name="jianliht">
							<input type="file" style="width: 300px;" class="text" id="jianlihtfile" name="jianlihtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="jianliexistproblem" name="jianliexistproblem" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="jianliauditview" name="jianliauditview" style="width: 300px;"></textarea>
							</td>
						
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="jianliexecutecase" name="jianliexecutecase" style="width: 300px;"></textarea>
							</td>
						</tr>
						 <tr>
							<td class="label" align="right">总承包合同：</td>
							<td align="left">
							<input type="hidden" value="总承包合同" name="zongcbht">
							<input type="file" style="width: 300px;" class="text" id="zongcbhtfile" name="zongcbhtfile" value="浏览" /> 
							</td>
						
							<td class="label" align="right">存在问题：</td>
							<td  align="left">
							  <textarea rows="3" cols="10" id="zongcbexistproblem" name="zongcbexistproblem" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="zongcbauditview" name="zongcbauditview" style="width: 300px;"></textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="zongcbexecutecase" name="zongcbexecutecase" style="width: 300px;"></textarea>
							</td>
						</tr>
						 <tr>
							<td class="label" align="right">设备供应合同：</td>
							<td  align="left">
							<input type="hidden" value="设备供应合同" name="shebeiht">
							<input type="file" style="width: 300px;" class="text" id="shebeihtfile" name="shebeihtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="shebeiexistproblem" name="shebeiexistproblem" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="shebeiauditview" name="shebeiauditview" style="width: 300px;"></textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="shebeiexecutecase" name="shebeiexecutecase" style="width: 300px;"></textarea>
							</td>
						</tr>
						<tr>
						<td  colspan="4">
						        <a href="javascript:void(0);" id="addrow" class="easyui-linkbutton"  iconCls="icon-add" style="margin: 1px;">添加分包</a>
				                <a href="javascript:void(0);"  class="easyui-linkbutton" id="delrow" iconCls="icon-remove"  style="margin: 1px;">删除分包</a>
						 </td>
						</tr>
						
	
						
						</c:if>
						<c:if test="${ not empty htlist}">
						<input type="hidden" id="saveorupdate" value="1"/>
						   <!--<input type="hidden" id="id" name="id" value="${ht.id}"/>-->
						  <c:forEach items="${htlist}" var="ht"  varStatus="i">
						    <c:if test="${ht.htName=='地勘合同'}">
						      <tr>
							     <input type="hidden" id="dikanid" name="dikanid" value="${ht.id}"/>
								<td class="label" align="right">地勘合同：</td>
								<td  align="left">
								<input type="hidden" value="地勘合同" name="dikanht">
								<c:if test="${not empty ht.htFile }">
								   <a href="javascript:download('${ht.htFile}')" >下载</a>
								   <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
								<input type="file" style="width: 300px;" class="text" id="dikanhtfile" name="dikanhtfile" value="浏览"  /> 
								</td>
								<td class="label" align="right">存在问题：</td>
								<td align="left">
								  <textarea rows="3" cols="10" id="dikanexistproblem" name="dikanexistproblem" style="width: 300px;" >${ht.existproblem}</textarea>
								</td>
						        </tr>
						        <tr>
								   <td class="label" align="right">审核意见：</td>
									<td align="left">
									<textarea rows="3" cols="10" id="dikanauditview" name="dikanauditview" style="width: 300px;" >${ht.auditview}</textarea>
									</td>
									<td class="label" align="right">执行情况：</td>
									<td align="left">
									<textarea rows="3" cols="10" id="dikanexecutecase" name="dikanexecutecase" style="width: 300px;" >${ht.executecase}</textarea>
									</td>
						        </tr>
						    </c:if>
						   <c:if test="${ht.htName=='设计合同'}">
							 <tr>
							  <input type="hidden" id="shejiid" name="shejiid" value="${ht.id}"/>
								<td class="label" align="right">设计合同：</td>
								<td  align="left">
								<input type="hidden" value="设计合同" name="shejiht">
								<c:if test="${not empty ht.htFile }">
								 <a href="javascript:download('${ht.htFile}')" >下载</a>
								 <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
								<input type="file" style="width: 300px;" class="text" id="shejihtfile" name="shejihtfile" value="浏览" /> 
								</td>
								<td class="label" align="right">存在问题：</td>
								<td align="left">
								  <textarea rows="3" cols="10" id="shejiexistproblem" name="shejiexistproblem" style="width: 300px;" >${ht.existproblem}</textarea>
								</td>
							</tr>
							<tr>
								<td class="label" align="right">审核意见：</td>
								<td align="left">
								<textarea rows="3" cols="10" id="shejiauditview" name="shejiauditview" style="width: 300px;">${ht.auditview}</textarea>
								</td>
								<td class="label" align="right">执行情况：</td>
								<td align="left">
								<textarea rows="3" cols="10" id="shejiexecutecase" name="shejiexecutecase" style="width: 300px;">${ht.executecase}</textarea>
								</td>
							</tr>
						    
						   </c:if>
					  <c:if test="${ht.htName=='项目管理合同'}">
					       <tr>
					       <input type="hidden" id="proid" name="proid" value="${ht.id}"/>
							<td class="label" align="right">项目管理合同：</td>
							<td  align="left">
							<input type="hidden" value="项目管理合同" name="proht">
							<c:if test="${not empty ht.htFile }">
								 <a href="javascript:download('${ht.htFile}')" >下载</a>
								 <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
							<input type="file" style="width: 300px;" class="text" id="prohtfile" name="prohtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td  align="left">
							  <textarea rows="3" cols="10" id="proexistproblem" name="proexistproblem" style="width: 300px;">${ht.existproblem}</textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="proauditview" name="proauditview" style="width: 300px;">${ht.auditview}</textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="proexecutecase" name="proexecutecase" style="width: 300px;">${ht.executecase}</textarea>
							</td>
						</tr>
					  </c:if>
					
					<c:if test="${ht.htName=='监理合同'}">
					    <tr>
					        <input type="hidden" id="jianliid" name="jianliid" value="${ht.id}"/>
							<td class="label" align="right">监理合同：</td>
							<td align="left">
							<input type="hidden" value="监理合同" name="jianliht">
							<c:if test="${not empty ht.htFile }">
								 <a href="javascript:download('${ht.htFile}')" >下载</a>
								 <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
							<input type="file" style="width: 300px;" class="text" id="jianlihtfile" name="jianlihtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="jianliexistproblem" name="jianliexistproblem" style="width: 300px;">${ht.existproblem}</textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td align="left">
							<textarea rows="3" cols="10" id="jianliauditview" name="jianliauditview" style="width: 300px;">${ht.auditview}</textarea>
							</td>
						
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="jianliexecutecase" name="jianliexecutecase" style="width: 300px;">${ht.executecase}</textarea>
							</td>
						</tr>
					</c:if>
					<c:if test="${ht.htName=='总承包合同'}">
					     <tr>
					        <input type="hidden" id="zongcbid" name="zongcbid" value="${ht.id}"/>
							<td class="label" align="right">总承包合同：</td>
							<td  align="left">
							<input type="hidden" value="总承包合同" name="zongcbht">
							<c:if test="${not empty ht.htFile }">
								 <a href="javascript:download('${ht.htFile}')" >下载</a>
								 <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
							<input type="file" style="width: 300px;" class="text" id="zongcbhtfile" name="zongcbhtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="zongcbexistproblem" name="zongcbexistproblem" style="width: 300px;">${ht.existproblem}</textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="zongcbauditview" name="zongcbauditview" style="width: 300px;">${ht.auditview}</textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="zongcbexecutecase" name="zongcbexecutecase" style="width: 300px;">${ht.executecase}</textarea>
							</td>
						</tr>
					</c:if>	
					
					<c:if test="${ht.htName=='设备供应合同'}">
					     <tr>
					        <input type="hidden" id="shebeiid" name="shebeiid" value="${ht.id}"/>
							<td class="label" align="right">设备供应合同：</td>
							<td  align="left">
							<input type="hidden" value="设备供应合同" name="shebeiht">
							<c:if test="${not empty ht.htFile }">
								 <a href="javascript:download('${ht.htFile}')" >下载</a>
								 <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
							<input type="file" style="width: 300px;" class="text" id="shebeihtfile" name="shebeihtfile" value="浏览" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="shebeiexistproblem" name="shebeiexistproblem" style="width: 300px;">${ht.existproblem}</textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="shebeiauditview" name="shebeiauditview" style="width: 300px;">${ht.auditview}</textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="shebeiexecutecase" name="shebeiexecutecase" style="width: 300px;">${ht.executecase}</textarea>
							</td>
						</tr>
						<tr>
						<td  colspan="4">
						        <a href="javascript:void(0);" id="addrow" class="easyui-linkbutton"  iconCls="icon-add" style="margin: 1px;">添加分包</a>
				                <a href="javascript:void(0);"  class="easyui-linkbutton" id="delrow" iconCls="icon-remove"  style="margin: 1px;">删除分包</a>
						 </td>
						</tr>
					</c:if>	
					<c:if test="${ht.htName!='设备供应合同' &&  ht.htName!='总承包合同'  &&  ht.htName!='监理合同'  &&  ht.htName!='项目管理合同'  &&  ht.htName!='设计合同' &&  ht.htName!='地勘合同'}">
					 <tr>
					     <input type="hidden" id="fenbaoid${i.count-6}" name="fenbaoid${i.count-6}" value="${ht.id}"/>
							<td class="label" align="right">${ht.htName}：</td>
							<td  align="left">
							<input type="hidden" value="${ht.htName}" name="fenbao${i.count-6}">
							<c:if test="${not empty ht.htFile }">
								 <a href="javascript:download('${ht.htFile}')" >下载</a>
								 <a href="javascript:lookfile('${ht.htFile}')" >查看</a>
								</c:if>
							<input type="file" style="width: 300px;" class="text" id="fenbaofile${i.count-6}" name="fenbaofile${i.count-6}" value="${ht.htFile}" /> 
							</td>
							<td class="label" align="right">存在问题：</td>
							<td align="left">
							  <textarea rows="3" cols="10" id="existproblem${i.count-6}" name="existproblem${i.count-6}" style="width: 300px;">${ht.existproblem}</textarea>
							</td>
						</tr>
						<tr>
							<td class="label" align="right">审核意见：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="auditview${i.count-6}" name="auditview${i.count-6}" style="width: 300px;">${ht.auditview}</textarea>
							</td>
							<td class="label" align="right">执行情况：</td>
							<td  align="left">
							<textarea rows="3" cols="10" id="executecase${i.count-6}" name="executecase${i.count-6}" style="width: 300px;">${ht.executecase}</textarea>
							</td>
						</tr>
						</c:if>
				 </c:forEach>
						</c:if>
					</table>
				</div>
			</div>
			</div>
		</form>
	</body>
</html>