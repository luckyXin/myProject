$(document).ready(function() {
	$('#detailDiv').tabs({
		onSelect : function(title) {
			if ('月报表信息' == title) {
				initializationReportMonth();
			} else if ('清标信息' == title) {
				$.post($("#coutextPath").val() + $("#url").val() + "getDedailInfo.do", {
					biaoDuanId : $("#biaoDuanId").val(),
					infoType : 0
				}, function(data, textStatus) {
					$("#qingBiaoAfterMoney").text(data.qingbiaoInfo.afterMoney);
					$("#measureUtil").text(data.qingbiaoInfo.measureUtil);
					$("#projectCount").text(data.qingbiaoInfo.projectCount);
					$("#maxUtilPrice").text(data.qingbiaoInfo.maxUtilPrice);
					$("#bidUtilPrice").text(data.qingbiaoInfo.bidUtilPrice);
					$("#exceedOneTwenty").text(data.qingbiaoInfo.exceedOneTwenty);
					$("#underEighty").text(data.qingbiaoInfo.underEighty);
					$("#exceedControllerPrice").text(data.qingbiaoInfo.exceedControllerPrice);
					$("#underControllerPrice").text(data.qingbiaoInfo.underControllerPrice);
				});
			} else if ('变更签证信息' == title){
				initializationChangeCodeInfo();
				// 签证类型切换方法
				$("#changeType").change(function (){
					$.post($("#coutextPath").val() + $("#url").val() + "getDedailInfo.do", {
						biaoDuanId : $("#biaoDuanId").val(),
						infoType : 1,
						changeType : $("#changeType").val()
					}, function(data, textStatus) {
						initializationChangeCodeInfo();
						$("#totalChangeMoney").text(data.totalChangeMoney);
					});
				});
			} else if ('索赔信息' == title) {
				initializationClaimIndemnityTable();
				// 切换索赔类型方法
				$("#claimIndemnityType").change(function (){
					$.post($("#coutextPath").val() + $("#url").val() + "getDedailInfo.do", {
						biaoDuanId : $("#biaoDuanId").val(),
						infoType : 2,
						claimIndemnityType : $("#claimIndemnityType").val()
					}, function(data, textStatus) {
						initializationClaimIndemnityTable();
						$("#totalIndemnityMoney").text(data.totalIndemnityMoney);
					});
				});
			} else if ('政策性调整信息' == title) {
				initializationZhengCeChange();
			} else if ('材料询价信息' == title) {
				initdatafile();
			} else if ('其他管理信息' == title) {
				initializationOtherManage();
			}
		}
	});
});

function initializationOtherManage() {
	var width = $("#otherManageTitle").width();
	var biaoDuanId = $("#biaoDuanId").val();
	var url = $("#coutextPath").val()
			+ $("#url").val() + 'find.do?otherManageFind=0&biaoDuanId=' + biaoDuanId;
	$('#otherManageTable').datagrid({
		url : url,
		columns : [ [ {
			field : 'dataname',
			title : '文件名',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'datafile',
			title : '文件地址',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'remark',
			title : '备注',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'createtime',
			title : '上传时间',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'option',
			title : '操作',
			width : fillsize(0.2),
			sortable : true,
			formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.datafile+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '   <a href="javascript:void(0);" onclick="download('+url+');return false;">查看</a>';
             }
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width + 2
	});
	// 设置分页控件
	var p = $('#otherManageTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

/**
 * 材料询价列表初始化
 */
function initdatafile() {
	var width = $("#cailiaoTitle").width();
	var biaoDuanId = $("#biaoDuanId").val();
	var url = $("#coutextPath").val()
			+ $("#url").val() + 'find.do?cailiaoxunjia=1&biaoDuanId=' + biaoDuanId;
	$('#cailiaoTable').datagrid({
		url : url,
		columns : [ [ {
			field : 'dataname',
			title : '文件名',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'datafile',
			title : '文件',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'createtime',
			title : '上传时间',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'option',
			title : '操作',
			width : fillsize(0.2),
			sortable : true,
			formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.datafile+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '   <a href="javascript:void(0);" onclick="download('+url+');return false;">查看</a>';
             }
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width +2
	});
	// 设置分页控件
	var p = $('#cailiaoTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

/**
 * 政策性调整
 */
function initializationZhengCeChange() {
	var width = $("#zhengceChangeTitle").width();
	var biaoDuanId = $("#biaoDuanId").val();
	var url = $("#coutextPath").val()
			+ $("#url").val() + 'find.do?zhengCeChangeFind=0&biaoDuanId='
			+ biaoDuanId;
	$('#zhengceChangeTable').datagrid({
		url : url,
		columns : [ [ {
			field : 'artificialfile',
			title : '人工费用调整',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'datamoney',
			title : '材料价',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'machinemoney',
			title : '机械价',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'otherContext',
			title : '其他价',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'remark',
			title : '金额调整依据',
			width : fillsize(0.3),
			sortable : true
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width + 2
	});
	// 设置分页控件
	var p = $('#zhengceChangeTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

/**
 * 索赔信息检索
 */
function initializationClaimIndemnityTable() {
	var width = $("#claimIndemnityBaseInfo").width();
	var url = $("#coutextPath").val()
			+ $("#url").val() + 'find.do?claimindemnity=0&biaoDuanId='
			+ $("#biaoDuanId").val()+ '&claimIndemnityType='+$("#claimIndemnityType").val();
	$('#claimIndemnityTable').datagrid({
		url : url,
		columns : [ [ {
			field : 'claimIndemnityType',
			title : '索赔类型',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'constructSentMoney',
			title : '施工方提出金额',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'indemnityMoney',
			title : '中介建议价',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'auditMoney',
			title : '审计建议价',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'ownerReadyMoney',
			title : '业主确定价',
			width : fillsize(0.17),
			sortable : true
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width + 2
	});
	// 设置分页控件
	var p = $('#claimIndemnityTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

/**
 * 变更签证信息
 */
function initializationChangeCodeInfo() {
	var width = $("#changeCodeBaseInfo").width();
	var url = $("#coutextPath").val()
			+ $("#url").val() + 'find.do?changeCardState=0&biaoDuanId='
			+ $("#biaoDuanId").val() + '&changeType=' + $("#changeType").val();
	$('#changeCodeBaseTable').datagrid({
		url : url,
		columns : [ [ {
			field : 'changeCode',
			title : '变更签证编号',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'changeTime',
			title : '变更时间',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'changeContext',
			title : '变更内容',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'constructSentMoney',
			title : '施工单位报送变更金额',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'tractAuditAdviceMoney',
			title : '跟踪审计建议变更金额',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'affirmChangeMoney',
			title : '确认变更金额',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'changeProccessCondition',
			title : '变更执行情况',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'changeType',
			title : '签证类型',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == '0') {
					return "清单漏项";
				} else if (value == '1') {
					return "清单量差";
				} else if (value == '2') {
					return "设计变更";
				} else if (value == '3') {
					return "技术核定";
				} else if (value == '4') {
					return "现场签证";
				} else if (value == '5') {
					return "其他";
				} else if (value == '6') {
					return "政策规范性设计变更";
				} else {
					return "";
				}
			}
		}
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width + 2
	});
	// 设置分页控件
	var p = $('#changeCodeBaseTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [  10 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

/**
 * 月报列表初始化
 */
function initializationReportMonth() {
	var width = $("#reportTableBaseInfo").width();
	var url = $("#coutextPath").val() + $("#url").val() + 'find.do?monthReprotState=0&biaoDuanId='+ $("#biaoDuanId").val();
	$('#reportTable').datagrid({
		url : url,
		columns : [ [ {
			field : 'createTime',
			title : '创建时间',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'createUserAccount',
			title : '录入人员',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'nowMonthCompleteValue',
			title : '当月完成产值(万元)',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'totalCompleteValue',
			title : '累计完成产值(万元)',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'nowMonthPayValue',
			title : '当月支付产值(万元)',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'updateTime',
			title : '最终更新时间',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'updateUserAccount',
			title : '更新人员',
			width : fillsize(0.12),
			sortable : true
		}
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width+2
	});
	// 设置分页控件
	var p = $('#reportTable').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [10],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

/**
 * 列宽控制
 * @param percent
 * @returns {Number}
 */
function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-60)*percent;
}

/**
 * 查看文件
 * @param url
 * @returns {Boolean}
 */
function lookfile(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/lookWord.do?url='+url));
	return false;
}

/**
 * 下载
 * @param url
 * @returns {Boolean}
 */
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
}
