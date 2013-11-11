$(document).ready(
		function() {
		
			initializationfile();
		
			
			$("#addBiaoDuan").click(function (){
				var row = $('#gridBiaoDuan').datagrid('getSelected');
				if (row == null || row.id == null) {
					top.$.messager.show({
						title : '提示',
						msg : '请选择标段'
					});
					return;
				}
				var htaudit=$("#htaudit").val();
				var suopei=$("#suopei").val();
				var policy=$("#policy").val();
				var dataeng=$("#dataeng").val();
				var other=$("#other").val();
				if(1==htaudit){
					top.openModule($("#frameId").val(),
							'标段-合同审核','/project/contract/input.do',
							null, 'add', row.id);
				}
				else if(1==suopei){
					top.openModule($("#frameId").val(),
							'标段-索赔','/project/claimindemnity/input.do',
							null, 'add', row.id);
				}
				else if(1==policy){
					top.openModule($("#frameId").val(),
							'标段-政策性','/project/policychang/input.do',
							null, 'add', row.id);
				
				}
				else if(1==dataeng){
					top.openModule($("#frameId").val(),
							'标段-材料询价','/project/dataenquiry/input.do',
							null, 'add', row.id);
				
				}
				else if(1==other){
					top.openModule($("#frameId").val(),
							'标段-其它管理首页','/project/othermanage/input.do',
							null, 'add', row.id);
				
				}
				else{
					top.openModule($("#frameId").val(),
							'标段-清标','/project/tractQingBiao/input.do',
							null, 'add', row.id);
				}
				
			});
			$("#addBiaoDuanedit").click(function (){
				var row = $('#gridBiaoDuan').datagrid('getSelected');
				if (row == null || row.id == null) {
					top.$.messager.show({
						title : '提示',
						msg : '请选择标段'
					});
					return;
				}
				var htaudit=$("#htaudit").val();
				var suopei=$("#suopei").val();
				if(1==htaudit){
					top.openModule($("#frameId").val(),
							'标段-合同审核','/project/contract/input.do',
							null, 'update', row.id);
				}else if(1==suopei){
					top.openModule($("#frameId").val(),
							'标段-索赔','/project/claimindemnity/input.do',
							null, 'update', row.id);
				}
				else{
					top.openModule($("#frameId").val(),
							'标段-清标','/project/tractQingBiao/input.do',
							null, 'add', row.id);
				}
				
			});
			initializationBiaoDuan();
});

function initializationBiaoDuan() {
	var height = document.getElementById("p3").clientHeight
	var htaudit=$("#htaudit").val();
	var suopei=$("#suopei").val();
	var url="";
	if(1==htaudit){
		url = $("#coutextPath").val() + '/project/contract/find.do?biaoDuanFind=0&projectId='+$("#id").val();
	}else if(1==suopei){
		url = $("#coutextPath").val() + '/project/claimindemnity/find.do?biaoDuanFind=0&projectId='+$("#id").val();
	}else{
		url = $("#coutextPath").val() + '/project/tractQingBiao/find.do?biaoDuanFind=0&projectId='+$("#id").val();
	}
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
			field : 'qingBiaoState',
			title : '清标状态',
			width : fillsize(0.1),
			sortable : true,
			formatter : function(value, rowData, rowIndex) {
			  if(null!= value && ""!=value)
			  {
				  if (value == 0) {
						return "未清标";
					} else if (value == 1) {
						return "已清标";
					}
			  }else
			  {
				  return "";
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
		height : height - 30
	});
	// 设置分页控件
	var p = $('#gridBiaoDuan').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function initializationfile() {
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
	      striped:true
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