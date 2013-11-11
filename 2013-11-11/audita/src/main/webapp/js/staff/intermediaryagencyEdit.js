var nowrow = 7;
var endrow = 8;
var maxrow = 100;
$(document).ready(function() {
	//$("#remark").val(top.textTrim($("#remark").val()));
	var url = $("#url").val();
	var processType = $("#processType").val();
	$("#save").click(function() {
		if ($('#form').form('validate')) {
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
	
	initdepttree();
	
	//部门
	$("#manageragency").val($("#manager").val());
});
//初始化机构下拉树形机构
function initdepttree(){
	$.ajax( {
		url : $("#coutextPath").val() + '/system/department/findbypid.do',
		type : "POST",
		async:false,
		success : function(json) {
			var result = eval("(" + json + ")");
			$$.InputTree.New( {
				"m_strNodeValue" : "deprtId",
				"m_strNodeText" : "name",
				"m_jsonData" : result,
				"m_strContainerID" : "manageragency"
			});
		}
	});
}

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
		var html4 = "<input type='text' class='easyui-validatebox' validType='mobile' invalidMessage='联系电话格式错误' style='width: 110px;' id='linktellphone' name='linktellphone'>";
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