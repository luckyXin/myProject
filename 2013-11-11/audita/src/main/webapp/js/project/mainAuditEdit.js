var oldRemark = null;
var oldSubmitState = null;
var oldPassState = null;
var baseheight = 400;
$(document)
		.ready(function() {
			//更改td 里面的时间的格式
				if (null != $("#sj1").val() && "" != $("#sj1").val()) {
					$("#sja1").html($("#sj1").val().substr(0, 10));
				} else {
					$("#sja1").html("");
				}
				if (null != $("#sj2").val() && "" != $("#sj2").val()) {
					$("#sja2").html($("#sj2").val().substr(0, 10));
				} else {
					$("#sja2").html("");
				}
				if (null != $("#sj3").val() && "" != $("#sj3").val()) {
					$("#sja3").html($("#sj3").val().substr(0, 10));
				} else {
					$("#sja3").html("");
				}
				if (null != $("#sj4").val() && "" != $("#sj4").val()) {
					$("#sja4").html($("#sj4").val().substr(0, 10));
				} else {
					$("#sja4").html("");
				}

				//更改时间格式
				if (null != $('#projectStartTime').datebox('getValue')
						&& "" != $('#projectStartTime').datebox('getValue')) {
					$('#projectStartTime').datebox(
							'setValue',
							$('#projectStartTime').datebox('getValue').substr(
									0, 10));
				}
				if (null != $('#projectEndTime').datebox('getValue')
						&& "" != $('#projectEndTime').datebox('getValue')) {
					$('#projectEndTime').datebox(
							'setValue',
							$('#projectEndTime').datebox('getValue').substr(0,
									10));
				}
				if (null != $('#auditReportTime').datebox('getValue')
						&& "" != $('#auditReportTime').datebox('getValue')) {
					$('#auditReportTime').datebox(
							'setValue',
							$('#auditReportTime').datebox('getValue').substr(0,
									10));
				}

				//-------------------------------
				$("#auditStartTime").datebox( {
					disabled : true
				});
				$("#auditEndTime").datebox( {
					disabled : true
				});

				//金额文本框格式。
				$("#sentAuditMone").numberbox( {
					precision : 2
				});
				$("#auditMoney").numberbox( {
					precision : 2
				});
				$('#areaMoney').numberbox( {
					precision : 2
				});
				$('#cityMoney').numberbox( {
					precision : 2
				});
				$('#selfRaisedMoney').numberbox( {
					precision : 2
				});
				$('#bankMoney').numberbox( {
					precision : 2
				});
				$('#otherInputMoney').numberbox( {
					precision : 2
				});
				$('#contractamount1').numberbox( {
					precision : 2
				});
				$('#contractamount2').numberbox( {
					precision : 2
				});
				$('#contractamount3').numberbox( {
					precision : 2
				});
				$('#contractamount4').numberbox( {
					precision : 2
				});
				$('#contractamount5').numberbox( {
					precision : 2
				});
				$('#contractamount6').numberbox( {
					precision : 2
				});
				$('#contractamount7').numberbox( {
					precision : 2
				});
				$("[name='contractamount8']").numberbox( {
					precision : 2
				});
				$('#ttvmoney1').numberbox( {
					precision : 2
				});
				$('#ttvmoney2').numberbox( {
					precision : 2
				});
				$('#ttvmoney3').numberbox( {
					precision : 2
				});
				$('#ttvmoney4').numberbox( {
					precision : 2
				});
				$('#ttvmoney5').numberbox( {
					precision : 2
				});
				$('#ttvmoney6').numberbox( {
					precision : 2
				});
				$('#ttvmoney7').numberbox( {
					precision : 2
				});
				$("[name='ttvmoney8']").numberbox( {
					precision : 2
				});
				$('#amountpaid1').numberbox( {
					precision : 2
				});
				$('#amountpaid2').numberbox( {
					precision : 2
				});
				$('#amountpaid3').numberbox( {
					precision : 2
				});
				$('#amountpaid4').numberbox( {
					precision : 2
				});
				$('#amountpaid5').numberbox( {
					precision : 2
				});
				$('#amountpaid6').numberbox( {
					precision : 2
				});
				$('#amountpaid7').numberbox( {
					precision : 2
				});

				$("[name='amountpaid8']").numberbox( {
					precision : 2
				});
				$('#projectDirectMoney').numberbox( {
					precision : 2
				});
				$('#indirectcosts').numberbox( {
					precision : 2
				});
				$("[name='money']").numberbox( {
					precision : 2
				});
				$("[name='totalInvestmentBudget']").numberbox( {
					precision : 2
				});
				$("[name='day']").numberbox( {
					precision : 0
				});

				//````````````````````````		
				$('#auditReduceAllMoney').numberbox( {
					precision : 2
				});
				$('#auditInvestAllMoney').numberbox( {
					precision : 2
				});
				$('#noNormMoney').numberbox( {
					precision : 2
				});
				$('#otherNoNormMoney').numberbox( {
					precision : 2
				});
				// 判断下面是否存在子项目
				if (0 == $("#protype").val() || 3 == $("#protype").val()) {
					// 单项目
					$("#pp3").hide();
					//判断文件是否存在是否显示
					$
							.ajax( {
								url : $("#coutextPath").val() + '/common/project/findhavedatafile.do',
								type : "POST",
								dataType : "json",
								async : false,
								data : 'datapreId=' + $("#datapreId").val(),
								success : function(data) {
									if (data.count > 0) {
										mainauditdatafile();
									}
								}
							});

				} else {
					// 打包项目
					$("#pp3").show();
					$('#projectbase').panel( {
						title : '项目基本信息',
						iconCls : 'icon-edit'
					});
					baseheight = 600;
					// 查询子项目信息
					selectsubproject();
				}
				if ($("#protype").val() == 3) {
					if (submitState == '1') {
						$("textarea").attr("disabled", "disabled");
						$("input").attr("disabled", "disabled");
						$("#noNormProblemSelect").attr("disabled", "disabled");
						$("#save").hide();
						$("#suspend").hide();

					}
					$("#state").parent().parent().hide();
				}

				// 判断是否可以生成审计报告
				$.post($("#coutextPath").val()
						+ "/project/MainAudit/checkIsCanCraeteReport.do", {
					mainAuditId : $("#datapreId").val()
				}, function(data, textStatus) {
					if (data.auditState == '1') {
						$("#createsingleReport").show();
						$("#createIdeaReport").hide();
					} else if (data.auditState == '2') {
						$("#createsingleReport").show();
						$("#createIdeaReport").hide();
					} else {
						$("#createsingleReport").hide();
						$("#createIdeaReport").show();
					}
				});

				// 保存审批信息
				$("#save")
						.click(
								function() {
									if ($('#form').form('validate')) {

										var url = $("#coutextPath").val() + '/project/MainAudit/add.do';

										if ($("#mainAuditId").val() != "") {

											url = $("#coutextPath").val() + '/project/MainAudit/update.do';
										}

										$("#form")
												.ajaxSubmit(
														{
															url : url,
															success : function(
																	data) {
																if (null == data
																		|| "" == data) {
																	$.messager
																			.show( {
																				title : "提示",
																				msg : "系统异常"
																			});
																} else {
																	if (data.id != null) {
																		top
																				.showMsg(data.msg);
																		if ($(
																				"#protype")
																				.val() == 3) {
																			top
																					.reloadModule('M022auditReport');
																		} else {
																			top
																					.reloadModule($(
																							"#fid")
																							.val());
																		}
																		if ($(
																				"#mainAuditId")
																				.val() != null
																				&& $(
																						"#mainAuditId")
																						.val() != "") {
																			top
																					.closeModule(
																							moduleName,
																							parentModuleName);
																		} else {
																			$(
																					"#mainAuditId")
																					.val(
																							data.id);
																		}
																	} else {
																		top
																				.showMsg(data.msg);
																	}
																}
															}
														});
									}
								});
				$("#NoNormProblemSelect").change(function() {
					if ($("#NoNormProblemSelect").val() == '4') {
						$("#NoNormProblemInput").show();
					} else {
						$("#NoNormProblemInput").hide();
					}
				});

				// 生成审计报告
				$("#createsingleReport")
						.click(
								function() {
									if ($("#mainAuditId").val() == null
											|| $("#mainAuditId").val() == "") {
										top.showMsg("保存后才能生成审计报告");
										return;
									}
								
									// 判断是否可以生成审计报告
									$
											.post(
													$("#coutextPath").val()
															+ "/project/MainAudit/checkIsCanCraeteReport.do",
													{
														mainAuditId : $(
																"#datapreId")
																.val()
													},
													function(data, textStatus) {
														if (data.auditState == '1') {
															top
																	.showMsg(data.msg);
															return;
														}
													});
									window.location = $("#coutextPath").val()
											+ '/project/MainAudit/outmainauditword.do?mainAuditId='
											+ $("#datapreId").val();
									return false;
								});
				// 生成意见稿
				$("#createIdeaReport")
						.click(
								function() {
									if ($("#mainAuditId").val() == null
											|| $("#mainAuditId").val() == "") {
										top.showMsg("保存后才能生成意见稿");
										return;
									}
									window.location = $("#coutextPath").val()
											+ '/project/MainAudit/getIdeaNote.do?projectId='
											+ $("#datapreId").val();
									return false;
								});

				$("#suspend").click(function() {
					//得到资料预审id
						var datapreId = $("#datapreId").val();
						openproiv(550, 350, datapreId);
					});
                   //添加资金支出。
				$("#additems")
						.click(
								function() {

									var tr = $("<tr>"
											+ "<td style='width: 120px; background: #E0ECFF;'>其他：</td>"
											+ "<td class='1'><input type='hidden' value='其他' id='unit8' name='unit8' />"
											+ "<input type='text' id='capitalexpendituresUnit8' name='capitalexpendituresUnit8'  style='width: 80px'/></td>"
											+ "<td style='background: #E0ECFF;'>合同金额：</td>"
											+ "<td class='2'><input type='text' id='contractamount8' name='contractamount8'style='width: 80px'/></td>"
											+ "<td style='background: #E0ECFF;'>主审审定金额:</td>"
											+ "<td class='3'><input type='text' id='ttvmoney8' name='ttvmoney8' style='width: 80px' /></td>"
											+ "<td style='background: #E0ECFF;'>截止审计日:</td>"
											+ "<td class='4'><input type='text' class='datebox11' id='stopAuditTime8' name='stopAuditTime8' /></td>"
											+ "<td style='background: #E0ECFF;'>已支付金额：</td>"
											+ "<td class='5'><input type='text' id='amountpaid8' name='amountpaid8'   style='width: 80px'/>"
											+ "<td><a class='easyui-linkbutton deletefile' iconCls='icon-cancel'>删除</a></td>"
											+ "</td></tr>");
									$("#files").before(tr);
									tr.find(".deletefile").click(function() {
										$(this).parent().parent().remove();
									});

									$(".easyui-linkbutton").linkbutton( {
										plain : false
									});
									
									
									$('#form').form('validate');

								});
                        //添加问题。
				$("#additemst")
						.click(
								function() {

									var tr = $("<tr>"
											+ "<td style='background: #E0ECFF;'>问题类型：</td>"
											+ "<td>"
											+ "<select id='type' name='type' class='123'>"
											+ "<option value='0'>超合同价</option>"
											+ "<option value='1'>超概算</option><option value='2'>超工期</option>"
											+ "<option value='3'>有工程质量验收记录</option>"
											+ "<option value='4'>多计工程款</option>"
											+ "<option value='5'selected='selected'>其他</option></select>"
											+ "</td>"
											+ "<td style='background: #E0ECFF;'>金额：</td>"
											+ "<td><input type='text' id='money' name='money'/>"
											+ "</td><td style='background: #E0ECFF;'>原因:</td>"
											+ "<td><input type='text' id='reason' name='reason' missingMessage='原因不能为空'/></td>"
											+ "<td style='background: #E0ECFF;'>天数</td>"
											+ "<td><input type='text' id='day' name='day'></td>"
											+ "<td><a class='easyui-linkbutton deletefile' iconCls='icon-cancel'>删除</a>"
											+ "</td></tr>");
									$("#filest").before(tr);
									$("[name='money']").numberbox( {
										precision : 2
									});
									$("[name='day']").numberbox( {
										precision : 2
									});
									tr.find(".deletefile").click(function() {
										$(this).parent().parent().remove();
									});
									$(".easyui-linkbutton").linkbutton( {
										required : true
									});
									$(".123")
											.change(
													function() {
														x = 9;
														x = $(this).val();
														if (x == 0 || x == 2
																|| x == 1) {
															$(this)
																	.parent()
																	.next()
																	.next()
																	.next()
																	.next()
																	.children()
																	.validatebox(
																			{
																				required : true
																			});
															$(this)
																	.parent()
																	.next()
																	.next()
																	.next()
																	.next()
																	.next()
																	.next()
																	.children()
																	.validatebox(
																			{
																				required : true
																			});
															$(this)
																	.parent()
																	.next()
																	.next()
																	.children()
																	.validatebox(
																			{
																				required : true
																			});
															$('#form').form(
																	'validate');
														} else {
															$(this)
																	.parent()
																	.next()
																	.next()
																	.next()
																	.next()
																	.children()
																	.validatebox(
																			{
																				required : false
																			});
															$(this)
																	.parent()
																	.next()
																	.next()
																	.next()
																	.next()
																	.next()
																	.next()
																	.children()
																	.validatebox(
																			{
																				required : false
																			});
															$(this)
																	.parent()
																	.next()
																	.next()
																	.children()
																	.validatebox(
																			{
																				required : false
																			});
															$('#form').form(
																	'validate');
														}
													});

								});

				$(".deletefile").click(function() {
					$(this).parent().parent().remove();
				});
				$(".123").change(
						function() {
							x = 9;
							x = $(this).val();
							if (x == 0 || x == 2 || x == 1) {
								$(this).parent().next().next().next().next()
										.children().validatebox( {
											required : true
										});
								$(this).parent().next().next().next().next()
										.next().next().children().validatebox( {
											required : true
										});
								$(this).parent().next().next().children()
										.validatebox( {
											required : true
										});
								$('#form').form('validate');
							} else {
								$(this).parent().next().next().next().next()
										.children().validatebox( {
											required : false
										});
								$(this).parent().next().next().next().next()
										.next().next().children().validatebox( {
											required : false
										});
								$(this).parent().next().next().children()
										.validatebox( {
											required : false
										});
								$('#form').form('validate');
							}
						});
			});
function selectsubproject() {
	var path = $("#coutextPath").val();
	// 获取项目安排id
	var arrangeId = $("#projectId").val();
	$('#subgrid')
			.datagrid(
					{
						url : path
								+ '/common/project/findMainAuditSubproject.do?arrangeId='
								+ arrangeId,
						columns : [ [
								{
									field : 'projectName',
									title : '项目名称',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'proownerid',
									title : '项目业主',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'intermediaryId',
									title : '中介机构',
									width : fillsize(0.2),
									sortable : true
								},
								{
									field : 'sentAmount',
									title : '送审金额(元)',
									width : fillsize(0.15),
									sortable : true
								},
								{
									field : 'intermediarySendTime',
									title : '发放中介时间',
									width : fillsize(0.15),
									sortable : true
								},
								{
									field : 'intermediaryAuditTime',
									title : '提交初审时间',
									width : fillsize(0.15),
									sortable : true
								},
								{
									field : 'auditstate',
									title : '审核状态',
									width : fillsize(0.1),
									sortable : true,
									formatter : function(value, rowData,
											rowIndex) {
										if (value == 1) {
											return "已审核";
										} else {
											return "未审核";
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
										var id = "'" + rowData.datapreId + "'";
										return '<a href="javascript:void(0);" onclick="lookprobase(' + id + ');">审核</a>'
									}

								} ] ],
						singleSelect : true,
						pagination : true,
						striped : true,
						height : 200
					});
	// 设置分页控件
	var p = $('#subgrid').datagrid('getPager');
	$(p).pagination( {
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 70) * percent;
}

function reloadGrid() {
	var arrangeId = $("#projectId").val();
	$.post($("#coutextPath").val() + "/project/MainAudit/getAllCountMoney.do",
			{
				projectId : arrangeId
			}, function(data, textStatus) {
				if (data) {
					$("#auditReduceAllMoney").val(
							data.mainAuditInfo.auditReduceAllMoney);
					$("#auditInvestAllMoney").val(
							data.mainAuditInfo.auditInvestAllMoney);
					$("#noNormMoney").val(data.mainAuditInfo.noNormMoney);
					$("#otherNoNormMoney").val(
							data.mainAuditInfo.otherNoNormMoney);
					$("#subgrid").datagrid("reload");
				}
			});
}

function lookprobase(id) {
	top.openModule($("#fid").val(), '审计报告-打包子项目-录入',
			'/project/MainAudit/input.do', null, 'auditReportSub', id);
}
function mainauditdatafile() {
	var height = document.body.clientHeight;
	var width = $("#p4").width();
	$('#mainmyfile')
			.datagrid(
					{
						url : $("#coutextPath").val()
								+ '/common/project/findFile.do?method='
								+ $(".datapreId").val(),
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
									width : fillsize(0.2),
									sortable : false,
									formatter : function(value, rowData,
											rowIndex) {
										var url = "'" + rowData.url + "'";
										var id = "'" + rowData.id + "'";
										return '          <a href="javascript:void(0);" onclick="download(' + url + ');return false;">下载</a>';
									}
								} ] ],
						singleSelect : true,
						pagination : true,
						striped : true,
						width : width - 15
					});
	//设置分页控件   
	var p = $('#mainmyfile').datagrid('getPager');
	$(p).pagination( {
		pageSize : 5,//每页显示的记录条数，默认为10   
		pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表   
		beforePageText : '第',//页数文本框前显示的汉字   
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function download(url) {
	window.location = encodeURI(encodeURI($("#coutextPath").val()
			+ '/common/project/download.do?url=' + url));
	return false;
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