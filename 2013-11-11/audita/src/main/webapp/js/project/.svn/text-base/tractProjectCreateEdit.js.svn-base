var pheight = null;
var projectNameStemp = null;
$(document).ready(
		function() {
			$("#ownerName").click(function() {
				openshowdiv(1, "业主查询", 580, 350,270,null);
			});
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					var url = $("#coutextPath").val() + '/project/tractProjectCreate/edit.do'
					$("#form").ajaxSubmit({
						url : url,
						success : function(data) {
							var data = eval("(" + data + ")");
							if (data.isSuccess != "") {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
								if ($("#id").val() != ""){
									top.closeModule(moduleName, parentModuleName);
								}
								$("#id").val(data.id);
								projectNameStemp = $("#projectName").val();
								$("#otherInfo").show();
								initializationBiaoDuan();
								initFile();
							} else  {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
							}
						}
					});
				}
			});
			
			$("#addfile").click(function(){
				   var tr=$("<tr><td class='label' align='right'>附件 ：</td>"+
							"<td colspan='3' align='left'><input  type='file' style='width: 400px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
							"iconCls='icon-cancel'>删除</a></td></tr>");
						$("#files").before(tr);
						pheight = $("#p1").height() + 60;
						$('#p1').panel({
							height : pheight
						});
						tr.find(".deletefile").click(function(){
							pheight = pheight - 30;
							$('#p1').panel({
								height : pheight
							});
							$(this).parent().parent().remove();
						});
						$(".easyui-linkbutton").linkbutton({
							plain:false
						});
				});
			$("#addBiaoDuan").click(function (){
				top.openModule($("#frameId").val(),
						'标段-增加','/project/tractProjectCreate/input.do',
						null, 'addBiaoDuan', $("#id").val());
			});
			
			$("#updateBiaoDuan").click(function (){
				var row = $('#gridBiaoDuan').datagrid('getSelected');
				if (row == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : '没有选择项目'
					});
					return;
				}
				top.openModule($("#frameId").val(),
						'标段-更新','/project/tractProjectCreate/input.do',
						null, 'updateBiaoDuan', row.id);
			});
			
			$("#delBiaoDuan").click(function (){
				var row = $('#gridBiaoDuan').datagrid('getSelected');
				if (row == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : '没有选择项目，无法删除'
					});
					return;
				}
				$.post($("#coutextPath").val()+"/project/tractProjectCreate/delete.do", {id : row.id},
						function (data, textStatus){
					$.messager.show({
						title : "提示",
						msg : data.msg
				});
				reloadGrid();
				});
			});
			
			projectNameStemp = $("#projectName").val();
			var flg = true;
			// 验证标段名称
			$.extend($.fn.validatebox.defaults.rules, {
				ishave : {
				validator : function(value) {
				if ($("#id").val() != "" && projectNameStemp == $("#projectName").val()) {
					return true;
				}
			   $.post($("#coutextPath").val() + "/project/tractProjectCreate/checkNameIsExist.do", {
					projectName : $("#projectName").val()
				}, function(data, textStatus) {
					if (data.success != '1') {
						flg = false;
					} else {
						flg = true;
					}
				});
				return flg;
				},
				message : '项目名称已经存在'
				}
			});
			
			if($("#id").val() != "") {
				$("#otherInfo").show();
				initializationBiaoDuan();
			} else {
				$("#otherInfo").hide();
			}
			initFile();
});

/**
 * 初始化文件列表
 */
function initFile(){
	$.post($("#coutextPath").val() + '/common/project/findFile.do', {
		biaoDuanId : $("#biaoDuanId").val(),
		method : $("#id").val()
	}, function(data, textStatus) {
		if (data.total == '0') {
			$("#grid").hide();
		} else {
			$("#grid").show();
			initializationfile();
		}
	});
}

function initializationBiaoDuan() {
	var height = document.getElementById("p3").clientHeight
	var url = $("#coutextPath").val() + '/project/tractProjectCreate/find.do?biaoDuanFind=0&projectId='+$("#id").val();
	$('#gridBiaoDuan').datagrid({
		url : url,
		columns : [[ {field : 'biaoDuanName',title : '标段名称',width : fillsize(0.2),sortable : true}, 
		{field : 'projectGaiKuang',title : '项目概况',width : fillsize(0.2),sortable : true}, 
		{field : 'preAuditMoney',title : '预审控制价',width : fillsize(0.2),sortable : true}, 
		{field : 'zhongBiaoMoney',title : '中标合同价',width : fillsize(0.2),sortable : true},
		{field : 'reallyStartTime',title : '实际开工时间',width : fillsize(0.15),sortable : true}]],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#gridBiaoDuan').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#gridBiaoDuan').treegrid({
				height : (300 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initPage(pageSize){
	var p = $('#gridBiaoDuan').treegrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#gridBiaoDuan').treegrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initializationfile() {
    var width = $("#p1").width() - 30;
	$('#grid').datagrid({   
	      url:$("#coutextPath").val()+'/common/project/findFile.do?method='+ $("#id").val(),   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.2),sortable:true},
	         {field:'uploadTime',title:'上传时间',width:fillsize(0.2),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return todate(value);
                 }
	         },
             {field:'OPERATION',title:'操作',width:fillsize(0.15) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '<a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delFile('+id+');return false;">删除</a>';
             }
          }
	      ]] ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width,
	      fitColumns :true
	  }); 
    //设置分页控件   
    var p = $('#grid').datagrid('getPager');   
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10   
        pageList: [5,10,15],//可以设置每页记录条数的列表   
        beforePageText: '第',//页数文本框前显示的汉字   
        afterPageText: '页    共 {pages} 页',   
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
    });
}
/**
 * 业主选择 回调函数
 * @param rowData
 */
function returnback(rowData){
	$("#ownerName").val(rowData.ownerName);
	$("#ownerId").val(rowData.id);
	$(".ownerlinkusertext").hide();
	$(".ownerlinkuserseleect").show();
	$.ajax( {
		url :  $("#coutextPath").val() + '/common/project/findlinkbyid.do',
		type : "POST",
		dataType : "json",
		data : "key=" + rowData.id,
		success : function(result) {
			document.getElementById("proownerlink").options.length=0; 
			document.getElementById("proownerlink").options.add(new Option("","")); 
		    for(var i=0;i<result.length;i++){
		    	document.getElementById("proownerlink").options.add(new Option(result[i].linkname,result[i].id)); 
		    }
		    //清空联系人电话
		    $("#proownertelphone").val("");
		}
	});
	close();
}

/**
 * 业主联系电话
 */
function proownerlinkchange(){
	var id=$("#proownerlink").val();
	$("#proownerlinkname").val($("#proownerlink>option:selected").text());
	$.ajax({
		url :  $("#coutextPath").val() + '/common/project/findlinkbyphone.do',
		type : "POST",
		dataType : "json",
		data : "id=" + id,
		success : function(result) {
		    $("#proownertelphone").val(result.phone);
		}
	});
}

//下载
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
}

//删除
function delFile(id){
	$.ajax({
		url : $("#coutextPath").val() + '/common/project/deleteFile.do',
		type : "POST",
		dataType : "json",
		data : "key=" + id,
		success : function(result) {
			if (result.success == "success") {
				$.messager.show({
					title : '提示',
					msg : result.msg,
					timeout : 3000
				});
				$("#grid").datagrid("reload");
			} else {
				$.messager.show({
					title : '提示',
					msg : result.msg,
					timeout : 3000
				});
				return false;
			}
		}
	});
	initializationfile();
}

function reloadGrid() {
	$('#gridBiaoDuan').datagrid("reload");
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 10) * percent;
}