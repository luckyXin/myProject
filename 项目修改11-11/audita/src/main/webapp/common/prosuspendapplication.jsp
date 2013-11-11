<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/prosuspendapplication.js"></script>
	</head>
	<body>
	 <input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
		<div id="winpro" style="display: none;"> 
		   <form id="proform" method="post">
		   <input type="hidden" id="zhiliaoid" name="zhiliaoid">
		   <table  cellspacing="0" width="99%" align="center" border="1">
				 <tr>
						   <td align="left" class="label" width="20%">
								审计项目名称 ：
							</td>
							<td colspan="3">
								 <input type="text" readonly="readonly" id="project" name="project"  style="width: 280px;">
							</td>
				</tr>
				<tr>
						   <td align="left" class="label">
								子项目名称 ：
							</td>
							<td colspan="3">
								 <input type="text" readonly="readonly" id="childProjectName" name="childProjectName" style="width: 280px;">
							</td>
				</tr>
				<tr>
						   <td align="left" class="label">
								建设单位 ：
							</td>
							<td colspan="3">
								 <input type="text" readonly="readonly" id="ownnerName" name="ownnerName" style="width: 280px;">
							</td>
				</tr>
				<tr>
						   <td align="left" class="label">
								施工单位 ：
							</td>
							<td colspan="3">
								 <input type="text" readonly="readonly" id="constructName" name="constructName" style="width: 280px;">
							</td>
				</tr>
				<tr>
						   <td align="left" class="label">
								暂停审计原因：
							</td>
							<td colspan="3">
								<textarea rows="5" cols="10" id="suspendRemark" name="suspendRemark" style="width: 280px;"></textarea>
							</td>
				</tr>
					<a id="save" class="easyui-linkbutton" onclick="javascript:save();" iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:closepro();" iconCls="icon-cancel">关闭</a>
			
				
			</table>
           </form>
        </div>  
        	
	</body>
</html>