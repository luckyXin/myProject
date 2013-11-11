$(document).ready(function() {
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	$('#form').form('validate');
	$(".label").next().addClass("classtd");
	$("textarea").each(function(i){
		$(this).val(textTrim($(this).val()));
	});
	
	// 页面后退键禁止
	$("body").keydown(function(e) {
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
	});
	$(".fmoneyContext").each(function(i){
		$(this).text(fmoney($(this).text(), 2));
	});
});

function cancel() {
	top.closeModule(moduleName, null);
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
/**
 * 消除前后空格
 * 
 * @param txt
 * @returns
 */
function textTrim(txt) {
	if(undefined==txt || null==txt || ""==txt)
	{
		return "";
	}else{
		return txt.replace(/(^\s*)|(\s*$)/g, "");
	}	
	
}

function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

function fmoney(s, n)
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 
