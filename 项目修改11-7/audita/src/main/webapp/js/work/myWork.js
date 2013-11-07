$(document).ready(function() {
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	$("#p1").panel({
		width : width-10,
		height : height -10
	});

    // 获取三日未审批的项目并且提示
	$.post($("#coutextPath").val()
			+ "/work/MyWork/threeDayNoProcessProject.do", {
		id : 0
	}, function(data, textStatus) {
		if (data.msg) {
			$.messager.show({
				title : "提示",
				msg : "有" + data.msg + "个项目已经超过三天还未处理"
			});
		}
	});
});

function clickNoComplete(url, moduleId, moduleName) {
	top.openModule(moduleId, moduleName, url + "input.do", null, "", "0");
}

function clickComplete(url, moduleId, moduleName) {
	top.openModule(moduleId, moduleName, url + "input.do", null, "", "1");
}
function clickNotice(id){
	top.openModule("M029", "公告查看","/system/notice/input.do", null, "worklook", id);
}

function clickMessage(id){
	top.openModule("M032", "消息查看","/system/message/input.do", null, "look", id);
}
function initializationNoCompleteWork() {
	var height = document.getElementById("p1").clientHeight;
	$('#noCompleteWork').datagrid({
		url : $("#coutextPath").val() + '/work/MyWork/find.do',
		columns : [ [ {
			field : 'projectId',
			title : '任务项目编码',
			width : fillsize(0.33),
			sortable : true
		}, {
			field : 'projectName',
			title : '任务项目名称',
			width : fillsize(0.33),
			sortable : true
		}, {
			field : 'stateName',
			title : '处理内容',
			width : fillsize(0.33),
			sortable : true
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : height
	});
	// 设置分页控件
	var p = $('#noCompleteWork').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '共 {total} 条记录'
	});
}

function fillsize(percent) {
	var bodyWidth = document.getElementById("p1").clientWidth;
	return (bodyWidth - 10) * percent;
}

function reloadGrid() {
	window.location.reload();
}

/**
 * 未完成工作
 */
function noProcessProject() {
	top.openModule("M049", "未完成工作","/search/mySelfNoCompleteProject/input.do", null, null, null);
}

/**
 * 已处理项目
 */
function alreadyProcessProject() {
	top.openModule("M048", "已完成工作","/search/myselfCompleteProject/input.do", null, null, null);
}

//公告更多
function morenotice(){
	top.openModule("M029", "公告管理","/system/notice/input.do", null, null, null);
}

//消息更多
function moremessage(){
	top.openModule("M032", "消息管理","/system/message/input.do", null, null, null);
}

//发送的消息未阅读消息
function sendnoread(){
	top.openModule("M032", "消息管理","/system/message/input.do", null, "sendnoread", "0");
}

//发送的消息已阅读消息
function sendread(){
	top.openModule("M032", "消息管理","/system/message/input.do", null, "sendread", "1");
}

//接收的消息未阅读消息
function jieshounoread(){
	top.openModule("M032", "消息管理","/system/message/input.do", null, "jieshounoread", "0");
}

//接收的消息已阅读消息
function jieshouread(){
	top.openModule("M032", "消息管理","/system/message/input.do", null, "jieshouread", "1");
}