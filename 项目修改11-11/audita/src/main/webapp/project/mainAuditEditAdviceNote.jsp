<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<title>生成审计通知书</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common-edit.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
		</script>
		<script type="text/javascript">
	var baseheight = 240;
	$(document)
			.ready(
					function() {
						$("#createAuditNote").click(
										function() {
											window.location = $("#coutextPath")
													.val()
													+ '/project/MainAudit/getWord.do?projectId='
													+ $("#projectId").val();
											return false;
										});
						$("#addfile")
								.click(
										function() {
											baseheight += 20;
											var tr = $("<tr><td class='label' align='right'>资料文档：</td>"
													+ "<td colspan='3' align='left'><input  type='file' style='width: 400px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
									"iconCls='icon-cancel'>删除</a></td></tr>");
											$("#files").before(tr);
											tr
													.find(".deletefile")
													.click(
															function() {
																baseheight = baseheight - 20;
																$(this)
																		.parent()
																		.parent()
																		.remove();
															});
											$(".easyui-linkbutton").linkbutton(
													{
														plain : false
													});
										});

						$('#p2').panel({
							height : baseheight
						});
						// 保存审批信息
						$("#save").click(function() {$("#form").ajaxSubmit(
															{url : $("#coutextPath").val() + '/project/MainAudit/updateAuditNoteInfo.do',
																success : function(data) {
																	var data = eval("("+ data + ")");
																	if (data.success == "success") {
																		top.showMsg(data.msg);
																		top.reloadModule($("#fid").val());
																		top.closeModule(moduleName, parentModuleName);
																	} else {
																		top.showMsg(data.msg);
																	}
																}
															});
										});
						$("#addfile").click(function() {
											if (fileNumber > 10) {
												top.$.messager.show({
													title : $("#title").val(),
													msg : "最多只能添加十个文件"
												});
												return;
											}
											fileNumber++;
											var html = '<tr><td class="label">文件上传：</td><td colspan="3"><input type="file" style="width: 400px;" class="text" id="file" name="fileName" value="浏览" /><a class="easyui-linkbutton" id="deleteFile'
													+ fileNumber
													+ '" onclick="deleteFile(this);">删除</a></td></tr>'
											$("#mytableid>tbody").append(html);
											$("#deleteFile" + fileNumber)
													.linkbutton({
														iconCls : "icon-cancel"
													});
										});
						$("#addWorkloadInfo").click(function (){
						     $('<tr class="workloadClass"><td class="label">核对工作量时间：</td><td><input class="workloadTime" style="width: 100px;"id="workloadStartTime" name="workloadStartTime"value="">~<input class="workloadTime" style="width: 100px;"id="workloadEndTime" name="workloadEndTime"value=""></td><td class="label">内容：</td><td><input style="width: 200px;" class="workloadContext" id="workloadContext" name="workloadContext"value=""><a class="easyui-linkbutton" iconCls="icon-add"id="deleteWorkloadInfo">删除</a></td></tr>').insertAfter(".workloadClass:last");
						     $(".workloadTime").datebox({
						        width : 100
						     });
						     $(".workloadContext").next().linkbutton({
						       iconCls : "icon-cancel"
						     });
						     $(".workloadContext").next().click(function (){
						        $(this).parent().parent().remove();
						     });
						});
						initializationfile();
						
						  $(".workloadContext").next().linkbutton({
						       iconCls : "icon-cancel"
						     });
					     $(".workloadContext").next().click(function (){
					        $(this).parent().parent().remove();
					     });
					});
	function initializationfile() {
		var height = document.body.clientHeight;
		var width = $(document.body).width();
		$('#grid')
				.datagrid(
						{
							url : $("#coutextPath").val()
									+ '/common/project/findFile.do?method='
									+ $("#projectId").val(),
							columns : [ [
									{
										field : 'fileName',
										title : '文件名称',
										width : fillsize(0.3),
										sortable : true
									},
									{
										field : 'uploadTime',
										title : '上传时间',
										width : fillsize(0.2),
										sortable : true,
										formatter : function(value, rowData,
												rowIndex) {
											return todate(value);
										}
									},
									{
										field : 'state',
										title : '来源阶段',
										width : fillsize(0.2),
										sortable : true,
										formatter : function(value, rowData,
												rowIndex) {
											if (null != value && "" != value) {
												if (1 == value) {
													return "预审";
												} else if (2 == value) {
													return "主审";
												} else {
													return "";
												}
											} else {
												return "";
											}
										}

									},
									{
										field : 'OPERATION',
										title : '操作',
										width : fillsize(0.1),
										sortable : false,
										formatter : function(value, rowData,
												rowIndex) {
											var url = "'" + rowData.url + "'";
											var id = "'" + rowData.id + "'";
											return '<a href="javascript:void(0);" onclick="download('
													+ url
													+ ');return false;">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delFile('
													+ id + ');">删除</a>';
										}
									} ] ],
							singleSelect : true,
							pagination : true,
							striped : true,
							width : width - 50
						});
		//设置分页控件   
		var p = $('#grid').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10   
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表   
			beforePageText : '第',//页数文本框前显示的汉字   
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	}

	function todate(time) {
		if (null != time && "" != time) {
			var date = new Date(time);
			var year = date.getFullYear();
			var month = (date.getMonth() + 1);
			var day = date.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			return year + "-" + month + "-" + day;
		} else {
			return "";
		}
	}
	//下载
	function download(url) {
		window.location = encodeURI(encodeURI($("#coutextPath").val()
				+ '/common/project/download.do?url=' + url));
		return false;
	}

	function fillsize(percent) {
		var bodyWidth = document.body.clientWidth;
		return (bodyWidth - 50) * percent;
	}
	//删除
	function delFile(id) {
		if (1 == $("#isconfirmSubmit").val()) {
			$.messager.show({
				title : '提示',
				msg : '提交后不能修改',
				timeout : 3000
			});
			return false;

		} else {
			$
					.ajax({
						url : $("#coutextPath").val()
								+ '/common/project/deleteFile.do',
						type : "POST",
						dataType : "json",
						data : "key=" + id,
						success : function(result) {
							if (result.success == "success") {
								$.messager.show({
									title : '提示',
									msg : result.msg,
									timeout : 3000
								});
								$("#grid").datagrid("reload");
							} else {
								$.messager.show({
									title : '提示',
									msg : result.msg,
									timeout : 3000
								});
								return false;
							}
						}
					});
		}
	}
</script>
		<style type="text/css">
body {
	margin: 10px;
	padding: 0px;
	width: 100%;
	height: 100%;
}

form .label {
	padding-right: 80px;
}
</style>
	</head>
	<body>
		<form id="form" method="post">
			<div id="mainDiv">
				<div id="p1" class="easyui-panel"
					style="background: #fafafa; height: 350px; margin-bottom: 5px;"
					title="项目基本信息" iconCls="icon-redo">
					<table class="form"
						style="width: 99%; border-style: none; margin: 5px;"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="label">
									项目名称：
								</td>
								<td>
									<input type="hidden" id="projectId" name="projectId"
										value="${dataPreBaseInfo.id}">
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" required="true"
										value="${dataPreBaseInfo.projectName}">
								</td>
								<td class="label">
									立项文号：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" required="true"
										value="${dataPreBaseInfo.projectName}">
								</td>
							</tr>
							<tr>
								<td class="label">
									立项时间：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.projectTime}">
								</td>
								<td class="label">
									批复金额(元)：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.auditMoney}">
								</td>
							</tr>
							<tr>
								<td class="label">
									项目业主：
								</td>
								<td colspan="3">
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.proOwnerName}">
								</td>
							</tr>
							<tr>
								<td class="label">
									项目业主联系人：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.proOwnerLink}">
								</td>
								<td class="label">
									项目业主联系电话：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;"
										value="${dataPreBaseInfo.proOwnerTelphone}">
								</td>
							</tr>
							<tr>
								<td class="label">
									施工单位：
								</td>
								<td colspan="3">
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.constructName}">
								</td>
							</tr>
							<tr>
								<td class="label">
									施工单位联系人：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.constructLink}">
								</td>
								<td class="label">
									施工单位联系电话：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;"
										value="${dataPreBaseInfo.constructTelphone}">
								</td>
							</tr>
							<tr>
								<td class="label">
									审计类型：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.auditType}">
								</td>
								<td class="label">
									是否加急：
								</td>
								<td>
									<c:if test="${dataPreBaseInfo.isExpedited == 1}">
										<select disabled="disabled">
											<option>
												是
											</option>
										</select>
									</c:if>
									<c:if test="${dataPreBaseInfo.isExpedited != 1}">
										<select disabled="disabled">
											<option>
												否
											</option>
										</select>
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="label">
									送审时间：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.datapreTime}">
								</td>


								<td class="label">
									送审金额：
								</td>
								<td>
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;" value="${dataPreBaseInfo.sentAmount}">
								</td>
							</tr>
							<tr>
								<td class="label">
									资料预审意见：
								</td>
								<td colspan="3">
									<textarea rows="5" style="width: 50%;" disabled="disabled">
										${dataPreBaseInfo.dataPreOpinion}
									</textarea>
								</td>
							</tr>
							<tr>
								<td class="label">
									送审人员：
								</td>
								<td colspan="3">
									<input class="easyui-validatebox" disabled="disabled"
										style="width: 200px;"
										value="${dataPreBaseInfo.dataPreOperate}">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="p2" class="easyui-panel"
					style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
					title="信息录入" iconCls="icon-redo">
					<div style="margin: 5px;">
						&nbsp;
						<a id="save" class="easyui-linkbutton" iconCls="icon-save">保存</a>
						<a id="createAuditNote" class="easyui-linkbutton"
							iconCls="icon-save">生成审计通知书</a>
						<a class="easyui-linkbutton" onclick="javascript:cancel()"
							iconCls="icon-cancel">关闭</a>
					</div>
					<table class="form"
						style="width: 99%; border-style: none; margin: 5px;"
						cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="label">
									现场勘踏时间：
								</td>
								<td colspan="3">
									<input class="easyui-datebox" id="prospectTime"
										name="prospectTime" style="width: 200px;"
										value="${mainAuditOne.prospectTime}">
								</td>
							</tr>
							<tr class="workloadClass">
								<td class="label">
									核对工作量时间：
								</td>
								<td>
									<input class="easyui-datebox" style="width: 100px;"
										id="workloadStartTime" name="workloadStartTime"
										value="${firstWorkInfo.startTime}">~<input class="easyui-datebox" style="width: 100px;"
										id="workloadEndTime" name="workloadEndTime" value="${firstWorkInfo.endTime}">
								</td>
								<td class="label">
									内容：
								</td>
								<td>
									<input style="width: 200px;"
										id="workloadContext" name="workloadContext"
										value="${firstWorkInfo.context}">
									<a class="easyui-linkbutton" iconCls="icon-add"
										id="addWorkloadInfo">增加</a>
								</td>
							</tr>
							<c:forEach items="${workInfo}" var="wi">
								<tr class="workloadClass">
									<td class="label">
										核对工作量时间：
									</td>
									<td>
										<input class="easyui-datebox" style="width: 100px;"
											id="workloadStartTime" name="workloadStartTime"
											value="${wi.startTime}">~<input class="easyui-datebox" style="width: 100px;"
											id="workloadEndTime" name="workloadEndTime" value="${wi.endTime}">
									</td>
									<td class="label">
										内容：
									</td>
									<td>
										<input style="width: 200px;"
											id="workloadContext" name="workloadContext" class="workloadContext"
											value="${wi.context}">
										<a class="easyui-linkbutton" iconCls="icon-add"
											id="deleteWorkloadInfo" onclick="deleteWorkInfo()">删除</a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td class="label">
									备注：
								</td>
								<td colspan="3">
									<textarea rows="5" style="width: 85%;" id="auditNoteRemark"
										name="auditNoteRemark">
											${dataPreBaseInfo.auditNoteRemark}
										</textarea>
								</td>
							</tr>
							<tr id="files">
								<td class="label" align="right">
									审计报告上传：
								</td>
								<td colspan="3" align="left">
									<input type="file" style="width: 400px;" class="workloadContext" id="file"
										name="uploadFile" value="浏览" />
									<a class="easyui-linkbutton" iconCls="icon-add" id="addfile">增加</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="p3" class="easyui-panel"
				style="background: #fafafa; margin-bottom: 5px;" collapsible="true"
				title="文件查看" iconCls="icon-redo">
				<table id="grid"></table>
			</div>
			<input type="hidden" id="coutextPath"
				value="<%=request.getContextPath()%>">
			<input type="hidden" id="fid" value="${moduelId}">
			<input type="hidden" id="processType" value="${processType}">
			<input type="hidden" id="url" value="${url}">
			<input type="hidden" id="noUpdate"
				value="<spring:message code='error.message.noUpdate'/>">
			<input type="hidden" id="title"
				value="<spring:message code='prompt.title.title'/>">
		</form>
	</body>
</html>
