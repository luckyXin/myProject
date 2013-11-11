$(document).ready(function() {
	$("#add").click(function (){
			top.openModule($("#frameid").val(),'标段-模板导入','/search/myselfTractProject/input.do',null, 'add', $("#biaoDuanId").val());
	});
	

	//查询 该标段模板文件信息
	initdatafile();
	
});


function initdatafile() {
	var height = document.body.clientHeight;
	var biaoDuanId=$("#biaoDuanId").val();
	var url=$("#coutextPath").val() + '/search/myselfTractProject/findTemple.do?biaoDuanId='+biaoDuanId;
	$('#mytableImport').datagrid({
		url : url,
		columns : [ [ {
			field : 'filename',
			title : '文件名',
			width : fillsize(0.1),
			sortable : true
		}, {
			field : 'fileurl',
			title : '文件地址',
			width : fillsize(0.3),
			sortable : true
		}, {
			field : 'createtime',
			title : '操作时间',
			width : fillsize(0.2),
			sortable : true
		},
		{
			field : 'createuser',
			title : '操作人',
			width : fillsize(0.2),
			sortable : true
		},
		{field:'OPERATION',title:'操作',width:fillsize(0.2) ,sortable:false,
      	  formatter : function(value, rowData, rowIndex) { 
              var id = "'"+rowData.id+"'";
              var url = "'"+rowData.fileurl+"'";
              return '<a href="javascript:void(0);" onclick="deltemp('+id+');return false;">删除</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="downloadtemp('+url+');return false;">下载</a>';
           }
         
        }
		] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height : 310
	});
	// 设置分页控件
	var p = $('#mytableImport').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20,30 , 40, 50],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#mytableImport').datagrid({
				height : (310 * newHeightNum)
			});
			initPage2(pageSize);
		}
	});
}
function initPage2(pageSize){
	var p = $('#mytableImport').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#mytableImport').datagrid({
				height : (310 * newHeightNum)
			});
			initPage2(pageSize);
		}
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-50) * percent;
}
function reloadGrid(){
	$("#mytableImport").datagrid("reload");
}


//删除
function deltemp(id){
	
	if(confirm("是否确定删除?")){
	 $.ajax( {
			url : $("#coutextPath").val() + '/common/project/deleteTempFile.do',
			type : "POST",
			dataType : "json",
			data : "id="+id,
			success : function(data) {
					if ("success"==data.success) {
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
						//刷新
						$("#mytableImport").datagrid("reload");
					}else{
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
					}
			}
	   });	
	}
}


//下载
function downloadtemp(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
}
