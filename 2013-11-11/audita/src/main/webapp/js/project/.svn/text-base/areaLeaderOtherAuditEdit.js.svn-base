var oldRemark = null;
var oldSubmitState = null;
var oldPassState = null;
var baseheight=450;
$(document).ready(
	
		function() {
			$("#sj1").text($("#sj1").text().substr(0,10))
	$("#sj2").text($("#sj2").text().substr(0,10))
	$("#sj3").text($("#sj3").text().substr(0,10))
	$("#sj4").text($("#sj4").text().substr(0,10))
			$("#auditStartTime").datebox({
				disabled:true
			});
			$("#auditEndTime").datebox({
				disabled:true
			});
			//判断下面是否存在子项目
			if(0==$("#protype").val()){
				//单项目
				$("#pp3").hide();
				//判断文件是否存在是否显示
				 $.ajax( {
					 url:$("#coutextPath").val()+'/common/project/findhavedatafile.do',   
						type : "POST",
						dataType : "json",
						async:false,
						data : 'datapreId='+$("#datapreId").val(),
						success : function(data) {
					      if(data.count>0){
					    	  loadfile();
					      }
						}
				});
				
				
			}else{
				//打包项目
				$("#pp3").show();
				$('#projectbase').panel({
					height : 240,
					title : '项目基本信息',
					iconCls : 'icon-edit'
				});
				//查询子项目信息
				selectsubproject();
			}
			// 保存审批信息
			$("#save").click(
					function() {
						var url = $("#coutextPath").val() + '/project/areaLeaderOtherAudit/add.do';
						$("#form").ajaxSubmit(
								{
									url : url,
									success : function(data) {
										if (null == data || "" == data) {
											$.messager.show({
												title : "提示",
												msg : "系统异常"
											});
										} else {
											if (data.id != null) {
												top.showMsg(data.msg);
												top.reloadModule($("#fid").val());
												top.closeModule(moduleName,parentModuleName);
											} else {
												top.showMsg(data.msg);
											}
										}
									}
								});
					});
});
function selectsubproject() {
	var path = $("#coutextPath").val();
	//获取项目安排id
	var arrangeId=$("#projectId").val();
	$('#subgrid').datagrid({
			url : path + '/common/project/selectsubproject.do?arrangeId='+arrangeId,
			columns : [ [  
					{
						field : 'projectName',
						title : '项目名称',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'proownerid',
						title : '项目业主',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'intermediaryId',
						title : '中介机构',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'sentAmount',
						title : '送审金额(元)',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'intermediarySendTime',
						title : '发放中介时间',
						width : fillsize(0.15),
						sortable : true
					},
					{
						field : 'intermediaryAuditTime',
						title : '提交初审时间',
						width : fillsize(0.15),
						sortable : true
					},
					 {field:'OPERATION',title:'操作',width:fillsize(0.1) ,sortable:false,
			        	  formatter : function(value, rowData, rowIndex) { 
			                var id = "'"+rowData.datapreId+"'";
			                return '<a href="javascript:void(0);" onclick="lookprobase('+id+');">详情</a>';
			             }
			           
			          }
					 ] ],
			singleSelect : true,
			pagination : true,
			striped : true,
			height :200
	});
	// 设置分页控件
	var p = $('#subgrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function fillsize(percent) {
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth - 70) * percent;
}

function lookprobase(id){
	opendatashowdiv(id,"项目信息",600,300,null);
}



function loadfile() {
	var height = document.body.clientHeight;
    var width = $("#p4").width();
	$('#myfile').datagrid({   
	      url:$("#coutextPath").val()+'/common/project/findFile.do?method='+ $("#datareId").val(),   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.3),sortable:true},
	         {field:'uploadTime',title:'上传时间',width:fillsize(0.2),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return todate(value);
                 }
	         
	         },
	         {field:'state',title:'来源阶段',width:fillsize(0.2),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
	        	   if(null!=value && ""!=value){
	        		   if(1==value){
	        			   return "预审";
	        		   }
	        		   else if(2==value){
	        			   return "主审";
	        		   }else{
	        			   return "";
	        		   }
	        	   }else{
	        		   return "";
	        	   }
                 }
	         
	         },
             {field:'OPERATION',title:'操作',width:fillsize(0.2) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '          <a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>';
             }
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width-10
	  }); 
	    //设置分页控件   
	    var p = $('#myfile').datagrid('getPager');   
	    $(p).pagination({   
	        pageSize: 5,//每页显示的记录条数，默认为10   
	        pageList: [5,10,15],//可以设置每页记录条数的列表   
	        beforePageText: '第',//页数文本框前显示的汉字   
	        afterPageText: '页    共 {pages} 页',   
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    }); 
}
function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-50)*percent;
}
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
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