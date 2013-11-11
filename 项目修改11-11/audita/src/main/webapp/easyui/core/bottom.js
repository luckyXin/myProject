var a = $("<div class=\"mengbang\" style=\"background-color: #eee;text-align: center;padding-top: 100px;font-size: 30px;\">正在加载请稍后...</div>");
a.dialog({
	modal : true,
	closable : false,
	title : "",
	closed : false
});
a.dialog("open");
document.onreadystatechange = subSomething;// 当页面加载状态改变的时候执行这个方法.
function subSomething() {
	if (document.readyState == "complete") {
		a.dialog("close");
	}
};
$(window).resize(function(e){
		$(".easyui-panel").panel({width:($("body").width() - 10)});
});
$(".easyui-panel").panel({width:($("body").width() - 10)});
