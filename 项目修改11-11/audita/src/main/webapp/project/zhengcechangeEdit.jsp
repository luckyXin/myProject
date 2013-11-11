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
			src="<%=request.getContextPath()%>/js/project/zhengcechangeEdit.js"></script>
		<title>政策性调整</title>
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
				style="background: #fafafa; height: 240px; margin-bottom: 5px;"
				collapsible="true" title="政策性调整信息" iconCls="icon-search">
				<div style="margin: 1px;">
					<a href="javascript:void(0);" id="save" class="easyui-linkbutton"
						iconCls="icon-save">保存</a>
					<a class="easyui-linkbutton" onclick="javascript:cancel()"
						iconCls="icon-cancel">关闭</a>
				</div>
				<input type="hidden" id="frameid" value="${id}">
				<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
				<input type="hidden" id="id" name="id" value="${zhengce.id}" />
				<input type="hidden" id="biaoDuanId" name="biaoDuanId" value="${biaoDuanId}" />
				<table class="form" style="width: 99%" id="mytableid"
					cellpadding="0" cellspacing="0">
					<tr>
						<td class="label"> 
							人工费用调整： 
						</td>
						<td>
							<input type="text" id="artificialfile" name="artificialfile"
								style="width: 200px;" readonly="readonly"  />
						</td>
						<td class="label">
							材料价：
						</td>
						<td>
							<input type="text" id="datamoney" name="datamoney"
								style="width: 200px;" value="${zhengce.datamoney}" />
						</td>
						
					</tr>
					
					<tr>
						<td class="label">
							机械价：
						</td>
						<td>
							<input type="text" id="machinemoney" name="machinemoney"
								style="width: 200px;" value="${zhengce.machinemoney}" />
						</td>
						<td class="label">
							其他价：
						</td>
						<td>
							<input type="text" id="otherContext" name="otherContext"
								style="width: 200px;" value="${zhengce.otherContext}" />
						</td>
						
					</tr>
					<tr>
						<td class="label">
							金额调整依据：
						</td>
						<td>
						     <textarea rows="6" cols="10" style="width: 100%;"  id="remark" name="remark">
						            ${zhengce.remark }
						     </textarea>
						</td>
					</tr>
					</table>
			</div>
			<c:if test="${ not empty zhengce}">
			    <div id="p2" class="easyui-panel" style="background: #fafafa;height: 400px;"
				collapsible="true" title="人工费用调整信息" iconCls="icon-tip">
				<div id="button-bar" style="margin: 5px;">
					
					
							<a href="javascript:void(0);" id="add" iconCls="icon-add"
								class="easyui-linkbutton">录入</a>
								<a href="javascript:void(0);" id="edit" iconCls="icon-edit"
								class="easyui-linkbutton">修改</a>
								<a href="javascript:void(0);" id="delete" iconCls="icon-remove"
								class="easyui-linkbutton">删除</a>
						
				</div>
				<table id="gridtz"></table>
			</div>
			</c:if>
			</form>
	</body>
</html>