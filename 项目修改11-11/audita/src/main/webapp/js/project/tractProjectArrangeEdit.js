$(document).ready(
		function() {
			$("#addBiaoDuan").click(function (){
				var row = $('#gridBiaoDuan').datagrid('getSelected');
				if (row == null || row.id == null) {
					top.$.messager.show({
						title : $("#title").val(),
						msg : $("#noSelect").val()
					});
					return;
				}
				top.openModule($("#frameId").val(),
						'标段-安排','/project/tractProjectArrange/input.do',
						null, 'add', row.id);
			});
			initializationBiaoDuan();
			initFile();
});

/**
 * 初始化文件列表
 */
function initFile(){
	$.post($("#coutextPath").val() + '/common/project/findFile.do', {
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
	var height = document.getElementById("p3").clientHeight;
	var width = document.getElementById("p3").clientWidth;
	var url = $("#coutextPath").val() + '/project/tractProjectArrange/find.do?biaoDuanFind=0&projectId='+$("#id").val();
	$('#gridBiaoDuan').datagrid({
		url : url,
		columns : [ [ {
			field : 'biaoDuanName',
			title : '标段名称',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'preAuditMoney',
			title : '预审控制价',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'zhongBiaoMoney',
			title : '中标合同价',
			width : fillsize(0.2),
			sortable : true
		}, {
			field : 'projectGaiKuang',
			title : '工程概况',
			width : fillsize(0.15),
			sortable : true
		}, {
			field : 'isArrange',
			title : '安排状态',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "未安排";
				} else if (value == 1) {
					return "已安排";
				}
			}
		},{
			field : 'isPay',
			title : '是否付费',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "是";
				} else if (value == 1) {
					return "否";
				} else {
					return "否";
				}
			}
		} ] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310,
		width : width - 10
	});
	// 设置分页控件
	var p = $('#gridBiaoDuan').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#gridBiaoDuan').datagrid({
				height : (300 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initPage(pageSize){
	var p = $('#gridBiaoDuan').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}

function initializationfile() {
    var width = $("#mainDiv").width() ;
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
            	 return '<a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>';
             }
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width - 10
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

//下载
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
}

function reloadGrid() {
	$('#gridBiaoDuan').datagrid("reload");
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 10) * percent;
}