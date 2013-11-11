var tab_count = 0;
var selectTab = undefined;
$(function() {
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	$(".menu_daohang").click(function() {
		$(this).addClass("menu_daohangCLikc");
	});
	$(".module").click(function() {
		var id = $(this).attr("id");
		var title = $(this).html();
		var url = $(this).attr("url");
		openModule(id, title, url);
	});
	
	showmessage();
	//定义一秒刷新一次

	
	// 绑定tabs的右键菜单
	$("#workbench").tabs({ 
	    onContextMenu :  function (e, title) { 
	        e.preventDefault(); 
	        $('#tabsMenu').menu('show', { 
	            left : e.pageX, 
	            top : e.pageY 
	        }).data("tabTitle", title); 
	    } 
	}); 
	$('#tabsMenu').menu({ 
		onClick: function (item) { 
			closeTab(item.id);
		} 
	}); 
});
//定时1秒执行一次
setInterval("showmessage()", 1000);
/**
 * 选项卡右键操作
 * @param action 操作类型
 * @returns {Boolean}
 */
function closeTab(action) {
	var alltabs = $('#workbench').tabs('tabs'); 
	var currentTab =$('#workbench').tabs('getSelected'); 
	var allTabtitle = []; 
	$.each(alltabs,function(i,n){ 
	   allTabtitle.push($(n).panel('options').title); 
	});
	
	switch (action) {
	   case "close": 
		   var currtab_title = currentTab.panel('options').title; 
		   $('#workbench').tabs('close', currtab_title); 
		   break;
	   case "closeall": 
	   $.each(allTabtitle, function (i, n) { 
		     $('#workbench').tabs('close', n); 
	   }); 
	   break;
	   case "closeother": 
		   var currtab_title = currentTab.panel('options').title; 
		   $.each(allTabtitle, function (i, n) { 
		   if (n != currtab_title){ 
		     $('#workbench').tabs('close', n); 
		     } 
		   }); 
	   break;
	   case "closeright": 
		   var tabIndex = $('#workbench').tabs('getTabIndex', currentTab); 
		   if (tabIndex == alltabs.length - 1){
			   showMsg("右边已经没有了");
		       return false; 
		   } 
		   $.each(allTabtitle, function (i, n) { 
			   if (i > tabIndex) { 
			       $('#workbench').tabs('close', n); 
			   } 
		   }); 
		   break;
	   case "closeleft": 
		   var tabIndex = $('#workbench').tabs('getTabIndex', currentTab);
		   if (tabIndex == 0) {
			   showMsg("左边已经没有了");
			   return false; 
		   } 
		   $.each(allTabtitle, function (i, n) { 
			   if (i < tabIndex) { 
				 $('#workbench').tabs('close', n); 
			   } 
		   }); 
	} 
} 

function backLogin(){
    window.location = "/audita";
 }

function changeScrollTop(str){
	$("#workbench").scrollTop(str);
}

/**
 * 打开模块
 * 
 * @param module
 *            模块名
 * @param url
 *            地址
 */
function openModule(id, module, url, reload, process, key) {
	if ($("#mywork") != undefined) {
		closeModule("我的工作");
	}
	closeModule(module);
	if ($("#workbench").tabs("exists", module)) {
		$("#workbench").tabs("select", module);
		if (reload) {
			if ($("#" + id).src != url)
				$("#" + id).src = url;
		}
	} else {
		var content = '<iframe id="' + id + '" name="' + id + '" title="'
				+ module + '" scrolling="auto" frameborder="0"  src="'
				+ '/audita' + url + '?id=' + id
				+ '" style="width:100%;height:100%;"></iframe>';
		if (process != null) {
			content = '<iframe id="' + id + process + '" name="' + id + process
					+ '" title="' + module
					+ '" scrolling="auto" frameborder="0"  src="' + '/audita'
					+ url + '?id=' + id + '&' + process + '=1&key=' + key
					+ '" style="width:100%;height:100%;"></iframe>';
		}
		$("#workbench").tabs("add", {
			title : module,
			content : content,
			closable : true
		});
	}
}
/**
 * 关闭模块
 * 
 * @param module
 */
function closeModule(module) {
	$("#workbench").tabs("close", module);
}
function closeModule(module, parentModule) {
	$("#workbench").tabs("close", module);
	$("#workbench").tabs("select", parentModule);
}
function selectModule(module) {
	$("#workbench").tabs("select", module);
}
function existsModule(module) {
	return $("#workbench").tabs("exists", module);
}
function reloadModule(moduleId) {
	window.frames[moduleId].reloadGrid();
}
function showMsg(msg) {
	$.messager.show({
		title : "提示",
		msg : msg
	});
}
function logout() {
}

/**
 * 消除前后空格
 * 
 * @param txt
 * @returns
 */
function textTrim(txt) {
	return txt.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 增加天数
 * 
 * @param date
 * @param value
 * @returns {Date}
 */
function AddDays(date, value) {
	return new Date(Date.parse(date) + (86400000 * value));
}

/**
 * Date格式复写
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

/**
 * 后退键禁止
 */
function banBackSpace(e) {
	var ev = e || window.event;// 获取event对象
	var obj = ev.target || ev.srcElement;// 获取事件源
	var t = obj.type || obj.getAttribute('type');// 获取事件源类型
	// 获取作为判断条件的事件类型
	var vReadOnly = obj.getAttribute('readonly');
	var vEnabled = obj.getAttribute('enabled');
	// 处理null值情况
	vReadOnly = (vReadOnly == null) ? false : vReadOnly;
	vEnabled = (vEnabled == null) ? true : vEnabled;

	// 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	// 并且readonly属性为true或enabled属性为false的，则退格键失效
	var flag1 = (ev.keyCode == 8
			&& (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
			: false;

	// 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true
			: false;
	// 判断
	if (flag2) {
		return false;
	}
	if (flag1) {
		return false;
	}
}

function showmessage(){
	var path=$("#path").val();
	 $.ajax( {
			url : path + '/common/project/findmessage.do',
			type : "POST",
			dataType : "json",
			async:false,
			data : '',
			success : function(data) {
		    	   $("#newmessage").html(data.num);
		    	   if(data.num!=0){
		    		 // 得到与当前状态相反的属性值
		    		   vs_ico.style.visibility = (vs_ico.style.visibility == "hidden") ? "visible" : "hidden";	
		    	   }
			}
	});
}
// 接收的消息未阅读消息
function jieshounoread(){
	top.openModule("M032", "消息管理","/system/message/input.do", null, "jieshounoread", "0");
	return false;
}
