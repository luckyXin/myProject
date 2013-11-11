var nowrow = 4;
var endrow = 5;
var maxrow = 100;
var ownerName = null;
var ownerReferred = null;
var unitType = null;
var state = null;
var isMainUnit = null;
var remark = null;
var linknames = null;
var linktellphones = null;
$(document).ready(function() {
	$("#remark").val(top.textTrim($("#remark").val()));
	var url = $("#url").val();
	var processType = $("#processType").val();
	if ('update'==processType){
		ownerName = $("#ownerName").val();
		ownerReferred = $("#ownerReferred").val();
		unitType = $("#unitType").val();
		state = $("#state").val();
		isMainUnit = $("#isMainUnit").val();
		remark = $("#remark").val();
		for (var i = 0; i<$("input[name=linkname]").length; i++){
			linknames += ','+$("input[name=linkname]:eq("+i+")").val();
		}
		for (var i = 0; i<$("input[name=linktellphone]").length; i++){
			linktellphones +=','+$("input[name=linktellphone]:eq("+i+")").val();
		}
	}
	$("#save").click(function() {
		if ($('#form').form('validate')) {
			if (isUpdate()){
				$("#form").ajaxSubmit({
					url : $("#coutextPath").val() + url,
					success : function(data) {
						if (data.isSuccess) {
							top.showMsg(data.msg);
							top.reloadModule($("#fid").val());
							top.closeModule(moduleName, parentModuleName);
						} else {
							msg = data.msg;
							top.showMsg(msg);
						}
					}
				});
			} else {
				$.messager.show({
					title : $("#title").val(),
					msg : $("#noUpdate").val()
				});
			}
		}
	});
	// 添加行
	$('#addrow').click(function() {
		add();
	});
	// 删除行
	$('#delrow').click(function() {
		move();
	});
});
// 增加行
function add() {
	var tb = document.getElementById("mytableid");
	var rnum = tb.rows.length + 1;
	var number = rnum - (nowrow - 1);
	if (number < maxrow) {
		var numname = converyNumToDaXie(number);
		var row = tb.insertRow();

		var cell = row.insertCell();
		cell.style.backgroundColor = "#e0ecff";
		var html1 = "<p align='right'>第" + numname + "联系人：</p>";
		cell.innerHTML = html1;

		cell = row.insertCell();
		var html2 = "<input type='text' style='width: 110px;' id='linkname' name='linkname'>";
		cell.innerHTML = html2;

		cell = row.insertCell();
		cell.style.backgroundColor = "#e0ecff";
		var html3 = "<p align='right'>第" + numname + "联系人电话 ：</p>";
		cell.innerHTML = html3;

		cell = row.insertCell();
		var html4 = "<input type='text' style='width: 110px;' id='linktellphone' name='linktellphone'>";
		cell.innerHTML = html4;
	}
}

// 减少行
function move() {
	var tb = document.getElementById("mytableid");
	var rnum = tb.rows.length;
	if (rnum >= endrow) {
		tb.deleteRow(rnum - 1);
	}
}

/**
 * 判断内容是否被更新
 * @returns {Boolean}
 */
function isUpdate(){
	if (ownerName != $("#ownerName").val()){
		return true;
	}
	
	if (ownerReferred != $("#ownerReferred").val()){
		return true;
	}

	if (unitType != $("#unitType").val()){
		return true;
	}
	
	if (state != $("#state").val()){
		return true;
	}
	
	if (isMainUnit != $("#isMainUnit").val()){
		return true;
	}
	
	if (remark != $("#remark").val()){
		return true;
	}
	
	var newLinknames = null;
	for (var i = 0; i<$("input[name=linkname]").length; i++){
		newLinknames += ','+$("input[name=linkname]:eq("+i+")").val();
	}
	if (linknames != newLinknames) {
		return true;
	}
	
	var newLinktellphones = null;
	for (var i = 0; i<$("input[name=linktellphone]").length; i++){
		newLinktellphones +=','+$("input[name=linktellphone]:eq("+i+")").val();
	}
	if (linktellphones != newLinktellphones){
		return true;
	}
	return false;
}