var falgsate = null;

function openshowdiv(falg, title, width, height, gridheight, parameter) {
	var height1 = document.body.clientHeight;
	var width2 =  document.body.clientWidth;
	width2 = width2-(width2*0.2);
	height1 = height1-(height1*0.24);
	$("#win").show();
	$('#win').window({
		width : width2,
		height : height,
		modal : true,
		collapsible : false,
		minimizable : false,
		title : title,
		iconCls : 'icon-search',
		draggable : false
	});
	var height = document.body.clientHeight;
	var width = $(document.body).width();
	var path = $("#root").val();
	
	if (falg == 0) {
		falgsate=0;
		var html = "";
		html += "<tr><td align='right'>企业名称:</td><td style='width: 200px;' ><input type='text' style='width: 200px;' id='searchname'></td><td align='left'><a href='javascript:void(0);' onclick='searchdata()' id='searchButton' >检索</a></td></tr>";
		$("#searchtable").html(html);
		$("#searchButton").linkbutton({
			iconCls : 'icon-search'
		});
		$('#mygrid').datagrid({
			url : path + '/common/project/findConstruction.do?state=0',
			columns : [ [ {
				field : 'constructname',
				title : '企业名称',
				width : fillsize(0.2),
				sortable : true
			}, {
				field : 'deptreferred',
				title : '企业简称',
				width : fillsize(0.2),
				sortable : true
			}, {
				field : 'regfunds',
				title : '注册资产',
				width : fillsize(0.1),
				sortable : true
			} ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			pageNumber:1,
			height :gridheight,
			onDblClickRow : function(rowIndex, rowData) {
				returnbackfunction(rowData);
			}
		});
	} else if (falg == 1) {
		falgsate=1;
		// 项目业主模板
		datagridOwner(height, width, path, gridheight);
	} else if (falg == 2) {
		falgsate=2;
		// 获取安排项目
		datagridArrangeProject(height, width, path, gridheight, parameter);
	} else if (falg == 3) {
		// 获取雇员信息
		falgsate=3;
		datagridEmployee(height, width, path, gridheight, title, parameter);
	} else if (falg == 4) {
		// 中介机构信息
		falgsate=4;
		datagridIntermediary(height, width, path, gridheight);
	} else if (falg == 5) {
		// 交办项目信息
		falgsate=5;
		datagridAssignProject(height, width, path, gridheight);
	} else if (falg == 6) {
		falgsate=6;
		datagridConstruct(height, width, path, gridheight);
	} 
	

	// 设置分页控件
	var p = $('#mygrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageNumber:1,
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth) * percent;
}

function datagridConstruct(height, width, path, gridheight){
	var html = "";
	html += "<tr><td align='right'>企业名称:</td><td style='width: 200px;' >" +
			"<input type='text' style='width: 200px;' id='searchname'></td><td align='left'><a href='javascript:void(0);' onclick='searchdata()' id='searchButton' >检索</a></td></tr>"
	$("#searchtable").html(html);
	$("#searchButton").linkbutton({
		iconCls : 'icon-search'
	});
	$('#mygrid').datagrid({
		url : path + '/common/project/findConstruction.do?state=0',
		columns : [ [ {
			field : 'constructname',
			title : '企业名称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'deptreferred',
			title : '企业简称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'regfunds',
			title : '注册资产',
			width : fillsize(0.1),
			sortable : true
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		pageNumber:1,
		height : height - gridheight,
		onDblClickRow : function(rowIndex, rowData) {
			returnbackConstruction(rowData);
		}
	});
}

/**
 * 中介机构信息
 * 
 * @param height
 * @param width
 * @param path
 * @param gridheight
 */
function datagridIntermediary(height, width, path, gridheight) {
	var html = "";
	html += "<tr><td align='right'>中介机构名称:</td><td style='width: 150px;' >"
			+ "<input type='text' style='width: 150px;' id='bidyear1'></td>"
			+ "<td align='right'>招标年度：</td><td style='width: 150px;'><input type='text' style='width: 150px;' id='bidyear'></td>"
			+ "<td align='left'><a href='javascript:void(0);' "
			+ "onclick='searchdata()' id='searchButton' >检索</a></td></tr>"
	$("#searchtable").html(html);
	$("#searchButton").linkbutton({
		iconCls : 'icon-search'
	});
	$('#mygrid').datagrid({
		url : path + '/common/project/findIntermediary.do',
		columns : [ [ {
			field : 'intermediaryname',
			title : '中介机构名称',
			width : fillsize(0.3),
			sortable : true
		}, {
			field : 'deptqualifica',
			title : '资质',
			width : fillsize(0.11),
			sortable : true
		}, {
			field : 'legal',
			title : '法人代表',
			width : fillsize(0.2),
			sortable : true
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : gridheight,
		width : width - (width*0.3),
		pageNumber:1,
		onDblClickRow : function(rowIndex, rowData) {
			returnIntermediary(rowData);
		}
	});
}

/**
 * 政府雇员信息
 * 
 * @param height
 * @param width
 * @param path
 * @param gridheight
 * @param title
 */
function datagridEmployee(height, width, path, gridheight, title,
		datagridEmployee) {
	// 政府雇员多选
	if (1 == datagridEmployee) {
		var html = "";
		html += "<tr><td align='right'>用户名称:</td><td style='width: 200px;' >"
				+ "<input type='text' style='width: 200px;' id='searchUserName'></td>"
				+ "<td align='left'><a href='javascript:void(0);' "
				+ "onclick='searchdata()' id='searchButton'>检索</a></td></tr>"
		$("#searchtable").html(html);
		$("#searchButton").linkbutton({
			iconCls : 'icon-search'
		});
		$("#choseok").linkbutton({
			iconCls : 'icon-search'
		});
		$('#mygrid').datagrid({
			url : path + '/common/project/findEmployee.do',
			columns : [ [ {
				field : 'userName',
				title : '雇员名称',
				width : fillsize(0.1),
				sortable : true
			}, {
				field : 'sex',
				title : '性别',
				width : fillsize(0.1),
				sortable : true
			}, {
				field : 'deptName',
				title : '所属部门',
				width : fillsize(0.1),
				sortable : true
			}, {
				field : 'telPhone',
				title : '联系电话',
				width : fillsize(0.1),
				sortable : true
			} ] ],
			singleSelect : false,
			pagination : true,
			striped : true,
			pageNumber:1,
			onDblClickRow : function(rowIndex, rowData) {		
			    
			      returnbackguyuanProject(rowData);
			},
			height : gridheight,
			width : width - (width*0.33)
		});
	} else {
		var html = "";
		html += "<tr><td align='right'>用户名称:</td><td style='width: 200px;' >"
				+ "<input type='text' style='width: 200px;' id='searchUserName'></td>"
				+ "<td align='left'><a href='javascript:void(0);' "
				+ "onclick='searchdata()'  id='searchButton'>检索</a></td></tr>"
		$("#searchtable").html(html);
		$("#searchButton").linkbutton({
			iconCls : 'icon-search'
		});
		$('#mygrid').datagrid({
			url : path + '/common/project/findEmployee.do',
			columns : [ [ {
				field : 'userName',
				title : '雇员名称',
				width : fillsize(0.1),
				sortable : true
			}, {
				field : 'sex',
				title : '性别',
				width : fillsize(0.1),
				sortable : true
			}, {
				field : 'deptName',
				title : '所属部门',
				width : fillsize(0.1),
				sortable : true
			}, {
				field : 'telPhone',
				title : '联系电话',
				width : fillsize(0.1),
				sortable : true
			} ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			height : gridheight,
			width : width - (width*0.33),
			pageNumber:1,
			onDblClickRow : function(rowIndex, rowData) {
				if (title == '主审人员' || title == '人员查询') {
					returnMainAuditEmployee(rowData);
				} else {
					returnGovernmentEmployee(rowData);
				}
			}
		});
	}
}

/**
 * 项目安排信息
 * 
 * @param height
 * @param width
 * @param path
 * @param gridheight
 */
function datagridArrangeProject(height, width, path, gridheight, id) {

	var html = "";
	html += "<tr><td align='right'>项目名称:</td><td style='width: 150px;' ><input type='text' style='width: 150px;' id='searchname'></td>"
			+ "<td align='right'>项目业主:</td><td style='width: 150px;' ><input type='text' style='width: 150px;' id='ownerName'></td>"
			+ "<td align='left'><a href='javascript:void(0);' onclick='searchdata()' id='searchButton' >检索</a><a href='javascript:void(0);' onclick='choseok()' id='choseok' >确定项目</a></td></tr>"
	$("#searchtable").html(html);
	$("#searchButton").linkbutton({
		iconCls : 'icon-search'
	});
	$("#choseok").linkbutton({
		iconCls : 'icon-search'
	});
	$('#mygrid')
			.datagrid(
					{
						url : path
								+ '/project/SingleProjectArrange/find.do?findProjectState=0&proid='
								+ id,
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'datapreno',
							title : '项目编码',
							width : fillsize(0.1),
							sortable : true
						}, {
							field : 'projectName',
							title : '项目名称',
							width : fillsize(0.12),
							sortable : true
						}, {
							field : 'constructUnit',
							title : '施工单位',
							width : fillsize(0.11),
							sortable : true
						}, {
							field : 'ownerName',
							title : '业主名称',
							width : fillsize(0.2),
							sortable : true
						} ] ],
						singleSelect : false,
						pagination : true,
						striped : true,
						checkbox : true,
						pageNumber:1,
						height : gridheight+40,
						width :width - (width*0.3)
					});
}

/**
 * 交办项目信息
 * 
 * @param height
 * @param width
 * @param path
 * @param gridheight
 */
function datagridAssignProject(height, width, path, gridheight) {
	var html = "";
	html += "<tr><td align='right'>项目名称:</td><td style='width: 150px;' ><input type='text' style='width: 150px;' id='searchname'></td>"
			+ "<td align='right'>项目业主:</td><td style='width: 150px;' ><input type='text' style='width: 150px;' id='ownerName'></td>"
			+ "<td align='left'><a href='javascript:void(0);' onclick='searchdata()' id='searchButton' >检索</a><a href='javascript:void(0);' onclick='choseok()' id='choseok' >确定项目</a></td></tr>"
	$("#searchtable").html(html);
	$("#searchButton").linkbutton({
		iconCls : 'icon-search'
	});
	$("#choseok").linkbutton({
		iconCls : 'icon-search'
	});
	$('#mygrid').datagrid({
		url : path + '/project/governmentAssign/find.do?findProjectState=0',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'datapreno',
			title : '项目编码',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'projectName',
			title : '项目名称',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'constructUnit',
			title : '施工单位',
			width : fillsize(0.11),
			sortable : true
		}, {
			field : 'ownerName',
			title : '业主名称',
			width : fillsize(0.2),
			sortable : true
		} ] ],
		singleSelect : false,
		pagination : true,
		striped : true,
		height : gridheight+40,
		width : width - (width*0.3),
		pageNumber:1,
		onDblClickRow : function(rowIndex, rowData) {
			returnbackAssignProject(rowData);
		}
	});
}

/**
 * 项目业主检索
 * 
 * @param height
 * @param width
 * @param path
 */
function datagridOwner(height, width, path, gridheight) {
	var html = "";
	html += "<tr><td align='right'>项目业主:</td><td style='width: 200px;' >"
			+ "<input type='text' style='width: 200px;' id='searchname'></td>"
			+ "<td align='left'><a href='javascript:void(0);' "
			+ "onclick='searchdata()' id='searchButton' >检索</a></td></tr>"
	$("#searchtable").html(html);
	$("#searchButton").linkbutton({
		iconCls : 'icon-search'
	});
	$('#mygrid').datagrid({
		url : path + '/common/project/findOwner.do?checkState=0',
		columns : [ [ {
			field : 'ownerName',
			title : '业主名称',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'ownerReferred',
			title : '业主简称',
			width : fillsize(0.12),
			sortable : true
		}, {
			field : 'unitType',
			title : '单位类型',
			width : fillsize(0.11),
			sortable : true
		}, {
			field : 'isMainUnit',
			title : '是否政府投资项目建设主要单位',
			width : fillsize(0.2),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 1) {
					return "是";
				} else if (value == 0) {
					return "否";
				}
			}
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : gridheight,
		width : width - (width*0.3),
		pageNumber:1,
		onDblClickRow : function(rowIndex, rowData) {
			returnback(rowData);
		}
	});
}

// 打开方法
function open() {
	$('#win').window('open');
}
// 关闭方法
function close() {
	$('#win').window('close');
}

// 检索
function searchdata() {
	var searchname = $("#searchname").val();
   
	if (null != falgsate && 0 == falgsate) {
		var path = $("#root").val();
		var url = path + '/staff/practitioners/construction/find.do?name='
				+ searchname;
		$('#mygrid').datagrid('options').url = encodeURI(encodeURI(url));
	} else if (1 == falgsate) {
		// 项目业主检索
		$('#mygrid').datagrid('options').queryParams = {
			projectOwnerName : $("#searchname").val()
		};
	} else if (2 == falgsate) {
		// 安排项目信息
		$('#mygrid').datagrid('options').queryParams = {
			ownerName : $("#ownerName").val(),
			proejctName : $("#searchname").val()
		};
	} else if (3 == falgsate) {
		$('#mygrid').datagrid('options').queryParams = {
			searchUserName : $("#searchUserName").val()
		}
	} else if (4 == falgsate) {
		$('#mygrid').datagrid('options').queryParams = {
			intermediaryName : $("#bidyear1").val(),
			bidyear:$("#bidyear").val()
		}
	} else if (5 == falgsate) {
		$('#mygrid').datagrid('options').queryParams = {
			ownerName : $("#ownerName").val(),
			projectName : $("#searchname").val()
		};
	}
	$('#mygrid').datagrid('reload');
}



// 选择
function choseok() {
	var id = "";
	var ids = $('#mygrid').datagrid('getSelections');
	for ( var i = 0; i < ids.length; i++) {
		id += ids[i].id + ",";
	}
	returnbackProject(id);
}
