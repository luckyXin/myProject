var superior = "";
var parentId = "";
$(function() {
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	var options = $("#trgid").treegrid('options');
	options.onDblClickRow = function(rowIndex, rowData) {
		edit();
	};
});

function hasSuperior() {
	if (superior && superior != "")
		return true;
	else
		return false;
}
function hasParent() {
	if (parentId && parentId != "")
		return true;
	else
		return false;
}

/**
 * 打开子记录
 * 
 * @param parent
 */
function openSub(parent) {
	$("#trgid").treegrid("reload", {
		_parent : parent
	});
	parentId = parent;
}

/**
 * 返回
 */
function back() {
	openSub(superior);
}
/**
 * 下级列表
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */
function subFormat(value, rowData, rowIndex) {
	var html = "<a href=\"#\" onclick=\"javascript:openSub('"
			+ eval("rowData." + $("#trgid").treegrid("options").idField)
			+ "');\" style=\"text-decoration:none;border: 1px solid #A8B8D1;background:#eee;\">下一级</a>";
	return html;
}

/**
 * 获取当前选中行
 * 
 * @returns
 */
function getSelectItem() {
	var row = $('#trgid').treegrid('getSelected');
	if (row)
		return row;
	else
		return null;
}
/**
 * 获取当前选中行ID
 * 
 * @returns
 */
function getSelectItemId() {
	var row = $('#trgid').treegrid('getSelected');
	if (row)
		return eval("row." + $("#trgid").treegrid("options").idField);
	else
		return null;
}
function add() {
	var parent = "";
	if (hasParent())
		parent = "_" + parentId;
	top.openModule(moduleId + "-add", moduleName + "-新增", moduleUrl + "/new"
			+ parent);
}
/**
 * 判断是否是父类,如果是父类返回为null,反之则返回父类的id
 */
function getIsparent() {
	var note = getSelectItem();
	if (note) {
		return $('#trgid').treegrid('getLevel',
				eval("note." + $("#trgid").treegrid("options").idField));
	}
	return 0;
}
function addChild() {
	switch (getIsparent()) {
	case 1:
		 top.openModule(moduleId + "-addchild", moduleName + "-新增子项目", moduleUrl +
					"/child/"+getSelectItemId()+"/new");
		break;
	default:
		top.showMsg("请选择父项目");
		break;
	}
}
function edit() {
	switch (getIsparent()) {
	case 1:
		top.openModule(moduleId + "-edit", moduleName + "-编辑", moduleUrl
				+ "/" + getSelectItemId() + "/edit");
		break;
	case 2:
		top.openModule(moduleId + "-editchild", moduleName + "-子项编辑",
				moduleUrl + "/child/" + getSelectItemId() + "/edit");
		break;
	default:
		top.showMsg("请选择");
		break;
	}
}
function show() {
	var parent = "";
	if (hasParent())
		parent = "_" + parentId;
	var selectItemId = getSelectItemId();
	if (selectItemId) {
		var isparent = getIsparent();
		if (isparent == null) {
			top.openModule(moduleId + "-show", moduleName + "-查看", moduleUrl
					+ "child/" + selectItemId + "/show" + parent);
		} else {
			top.openModule(moduleId + "-show", moduleName + "-子项查看", moduleUrl
					+ "child/" + selectItemId + "/show" + parent);
		}

	}

}

function del() {
	var selectItemId = getSelectItemId();
	if (selectItemId) {
		var baseUrl = moduleUrl.substring(moduleUrl.lastIndexOf("/") + 1);
		var isparent = getIsparent();
		if (isparent != null)
			baseUrl = baseUrl + "child";
		$.post(baseUrl + "/" + selectItemId + "/delete", function(data) {
			var msg = "删除失败";
			if (data.state) {
				msg = "删除成功";
				$("#trgid").treegrid("reload");
			}
			top.showMsg(msg);
		});
	}
}
function invalid(){
	var baseUrl="";
	switch (getIsparent()) {
	case 1:
		baseUrl=moduleUrl+ "/" + getSelectItemId() + "/invalid";
		$.post(baseUrl, function(data) {
			var msg = "操作失败";
			if (data.state) {
				msg = "操作成功";
				$("#trgid").treegrid("reload");
			}
			top.showMsg(msg);
		});
		break;
	case 2:
		baseUrl=moduleUrl+ "/child/" + getSelectItemId() + "/invalid";
		$.post(baseUrl, function(data) {
			var msg = "操作失败";
			if (data.state) {
				msg = "操作成功";
				$("#trgid").treegrid("reload");
			}
			top.showMsg(msg);
		});
		break;
	default:
		top.showMsg("请选择");
		break;
	}
}
function valid(){
	var baseUrl="";
	switch (getIsparent()) {
	case 1:
		baseUrl=moduleUrl+ "/" + getSelectItemId() + "/valid";
		$.post(baseUrl, function(data) {
			var msg = "操作失败";
			if (data.state) {
				msg = "操作成功";
				$("#trgid").treegrid("reload");
			}
			top.showMsg(msg);
		});
		break;
	case 2:
		baseUrl=moduleUrl+ "/child/" + getSelectItemId() + "/valid";
		$.post(baseUrl, function(data) {
			var msg = "操作失败";
			if (data.state) {
				msg = "操作成功";
				$("#trgid").treegrid("reload");
			}
			top.showMsg(msg);
		});
		break;
	default:
		top.showMsg("请选择");
		break;
	}
}
function destroy() {
	var selectItemId = getSelectItemId();
	if (selectItemId) {
		var baseUrl = moduleUrl.substring(moduleUrl.lastIndexOf("/") + 1);
		var isparent = getIsparent();
		if (isparent != null)
			baseUrl = baseUrl + "child";
		$.post(baseUrl + "/" + selectItemId + "/destroy", function(data) {
			var msg = "删除失败";
			if (data.state) {
				msg = "删除成功";
				$("#trgid").treegrid("reload");
			}
			top.showMsg(msg);
		});
	}
}
function reloadGrid() {
	$("#trgid").treegrid("reload");
}