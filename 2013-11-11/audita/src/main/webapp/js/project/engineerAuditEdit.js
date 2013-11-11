var baseheight=440;
$(document).ready(function() {
	
	//更改时间格式
	$("#sj1").text($("#sj1").text().substr(0,10))
	$("#sj2").text($("#sj2").text().substr(0,10))
	// 确认后不可修改内容
	$("#state").click(function() {
		if ($("#submitState").val() == "1") {
			$("#submitState").val("0");
		} else {
			$("#submitState").val("1");
		}
	});
	$('#auditMoney').numberbox({   
		   precision:2   
	}); 
	
	//判断下面是否存在子项目
	if(0==$("#protype").val()){
		//单项目
		$("#pp3").hide();
		//判断文件是否存在是否显示
		 $.ajax( {
			 url:$("#path").val()+'/common/project/findhavedatafile.do',   
				type : "POST",
				dataType : "json",
				async:false,
				data : 'datapreId='+$("#datapreId").val(),
				success : function(data) {
			      if(data.count>0){
			    	  initializationfile();
			      }
				}
		});
	}else{
		//打包项目
		$("#pp3").show();
		baseheight=600;
		$('#projectbase').panel({
			height : 240,
			title : '项目基本信息',
			iconCls : 'icon-edit'
		});
		//查询子项目信息
		selectsubproject();
	}
	//总的审减金额
	zongcutmoney=$("#zongcutmoney").val();
	//判断是否自审
	if(1==$("#isMySelfState").val())
	{
		//获取送审金额的钱
		var sendmoney=$("#sentAmount").val();
		var lv=zongcutmoney/sendmoney;
		var alv=lv.toFixed(4)*100;
		if(alv==0){
			//得到审减率
			$("#shenjianlv").html("0");
		}else{
			//得到审减率
			$("#shenjianlv").html(alv.toFixed(2)+"%");
		}
		//获取复核审减金额
		var fhreduceMoney=$("#reduceMoney").val();
		if(null!=fhreduceMoney && ""!=fhreduceMoney){
			$("#zongaudit").val(fhreduceMoney);
		}
		
	}else{
		// 获取中介 审定金额
		var zhongjieauditmoney = $("#zhongjieauditmoney").val();
		var lv=zongcutmoney/zhongjieauditmoney;
		var alv=lv.toFixed(4)*100;
		if(alv==0){
			//得到审减率
			$("#shenjianlv").html("0");
		}else{
			//得到审减率
			$("#shenjianlv").html(alv.toFixed(2)+"%");
		}
		//获取中介审减金额
		var intercutmoney=$("#chushenmoeny").val();
		//获取复核审减金额
		var fhreduceMoney=$("#reduceMoney").val();
		if(null!=intercutmoney && ""!=intercutmoney){
			$("#zongaudit").val(intercutmoney);
			if(null!=fhreduceMoney && ""!=fhreduceMoney){
				var money=parseFloat(fhreduceMoney)+ parseFloat(intercutmoney);
				$("#zongaudit").val(money.toFixed(2));
			}
		}
		
	}
	
	
	
	// 保存审批信息
	$("#save").click(function() {
		var url = $("#coutextPath").val() + '/project/engineerAudit/add.do';
		if ($("#auditId").val() != "") {
			url = $("#coutextPath").val() + '/project/engineerAudit/update.do';
		}
		$("#form").ajaxSubmit({
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
						top.closeModule(moduleName, parentModuleName);
					} else {
						top.showMsg(data.msg);
					}
				}
			}
		});
	});
	//总的提交
	$("#totalsave").click(function(){
		var state=0;
		if(document.getElementById("state").checked){ 
			state=1;
		}
		$("#form").ajaxSubmit({
				url :  $("#path").val() + '/project/engineerAudit/add.do?method=totalsubmit&arrangeId='+$("#projectId").val()+'&state='+state,
				success : function(result) {
			        if (result.id) {
						top.showMsg(result.msg);
						top.reloadModule($("#fid").val());
						top.closeModule(moduleName, parentModuleName);
					} else {
						top.showMsg(result.msg);
					}
				}
		});
	});
	// 失去焦点事件
	$("#auditMoney").blur(function() {
		// 获取审定金额的钱
		var auditmoney = $("#auditMoney").val();
		if (null != auditmoney && "" != auditmoney) {
			//判断是否自审
			if(1==$("#isMySelfState").val())
			{
				// 获取送审金额的钱
				var sendmoney = $("#sentAmount").val();
				// 得到审减金额的钱
				var cutmoney = sendmoney - auditmoney;
				$("#reduceMoney").val(cutmoney.toFixed(2));
				var a=cutmoney/sendmoney;
				var b=a.toFixed(4)*100;
				if(b==0){
					//得到审减率
					$("#auditlv").val("0");
				}else{
					//得到审减率
					$("#auditlv").val(b.toFixed(2)+"%");
				}
				
				$("#zongaudit").val(cutmoney.toFixed(2));
				
			}else
			{
				// 获取中介 审定金额
				var zhongjieauditmoney = $("#zhongjieauditmoney").val();
				// 得到审减金额的钱
				var cutmoney = zhongjieauditmoney - auditmoney;
				$("#reduceMoney").val(cutmoney.toFixed(2));
				
				var a=cutmoney/zhongjieauditmoney;
				var b=a.toFixed(4)*100;
				if(b==0){
					//得到审减率
					$("#auditlv").val("0");
				}else{
					//得到审减率
					$("#auditlv").val(b.toFixed(2)+"%");
				}
				
				//获取中介审减金额
				var intercutmoney=$("#chushenmoeny").val();
				var money=parseFloat(cutmoney)+ parseFloat(intercutmoney);
				$("#zongaudit").val(money.toFixed(2));
			}
		} else {
			$("#reduceMoney").val("");
		}
	});

	$("#auditEndTime").datebox({
		onSelect : function(date) {
			var startTime = $("#auditStartTime").datebox("getValue");
			var year = date.getFullYear();
			var month = (date.getMonth() + 1);
			var day = date.getDate();
			var endTime = year + "-" + month + "-" + day;
			var tmpBeginTime = new Date(startTime.replace(/-/g,"\/"));
			var tmpEndTime = new Date(endTime.replace(/-/g,"\/"));
			var auditTime=tmpEndTime.getTime()-tmpBeginTime.getTime();
			var auditDay = Math.floor(auditTime/(24*3600*1000));
			$("#auditDayCount").val(auditDay);
		}
	});
	
	//单项目生成意见单
	$("#singleaudit").click(function(){
		var type=$("#singleaudittype").val();
		//获取安排id
		var arrangeId=$("#projectId").val();
		window.location = $("#coutextPath").val()+ '/project/engineerAudit/projectauditlistword.do?arrangeId=' + arrangeId ;
		return false;
	});
	
	
	//打包项目生成意见单
    $("#packaudit").click(function(){
    	var type=$("#packaudittype").val();
		var arrangeId=$("#projectId").val();
		window.location = $("#coutextPath").val()+ '/project/engineerAudit/projectpackauditlistword.do?arrangeId=' + arrangeId;
		return false;
	});
    
    $("#suspend").click(function(){
		//得到资料预审id
		var datapreId=$("#datapreId").val();
	   openproiv(550,350,datapreId);
	});
    
    
     $("#lookoldinfo").click(function(){
		$("#wininfo").show();
		$('#wininfo').window({
			width : 800,
			height : 370,
			modal : true,
			maximizable : false,
			collapsible : false,
			minimizable : false,
			title : '复核意见单记录信息',
			iconCls : 'icon-search',
			draggable : false
		});
		var path = $("#coutextPath").val();
	//获取项目id
	var datapreId=$("#datapreId").val();
	$('#mygrid').datagrid({
		url : path + '/project/engineerAudit/findfh.do?datapreId='+datapreId,
		columns : [ [  
				{
					field : 'auditMoney',
					title : '审定金额',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'reduceMoney',
					title : '审减金额',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'auditlv',
					title : '审减率',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'auditStartTime',
					title : '审计开始时间',
					width : fillsize(0.15),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				},
				{
					field : 'auditEndTime',
					title : '审计结束时间',
					width : fillsize(0.15),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if(null!=value && ""!=value){
							return value.substr(0,10);
						}else{
							return "";
						}
					}
				},
				{
					field : 'auditDayCount',
					title : '审计天数',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'auditRemark',
					title : '复核工作记录',
					width : fillsize(0.2),
					sortable : true
				
				},
				{
					field : 'deskAuditRemark',
					title : '业务科室专职复核人员意见',
					width : fillsize(0.4),
					sortable : true
				
				},
			
				{
					field : 'createtime',
					title : '修改时间',
					width : fillsize(0.2),
					sortable : true
				}
			
		          			
				] ],
		singleSelect : true,
		pagination : true,
		striped : true,
		height :310
    });
	// 设置分页控件
	var p = $('#mygrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
		
		
	});

	
});
function selectsubproject() {
	var path = $("#coutextPath").val();
	//获取项目安排id
	var arrangeId=$("#projectId").val();
	$('#subgrid').datagrid({
		url : path + '/common/project/selectgoversubproject.do?arrangeId='+arrangeId,
		columns : [ [  
				{
					field : 'projectName',
					title : '项目名称',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'proownerid',
					title : '项目业主',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'intermediaryId',
					title : '中介机构',
					width : fillsize(0.1),
					sortable : true
				},
				{
					field : 'sentAmount',
					title : '送审金额(元)',
					width : fillsize(0.1),
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
				{
					field : 'auditstate',
					title : '审核状态',
					width : fillsize(0.1),
					sortable : true,
					formatter : function(value, rowData,rowIndex) {
						if (value == 1) {
							return "已审核";
						}else{
							return "未审核";
						}
					}
				},
				
				 {field:'OPERATION',title:'操作',width:fillsize(0.1) ,sortable:false,
		        	  formatter : function(value, rowData, rowIndex) { 
		                  var id = "'"+rowData.datapreId+"'";
		                  return '<a href="javascript:void(0);" onclick="lookprobase('+id+');">审核</a>';
		                 // var issubmit=$("#issubmit").val();
		                 /* if(issubmit==0)
		                  {
		                	  return '<a href="javascript:void(0);" onclick="lookprobase('+id+');">审核</a>';
		                  }else{
		                	  return '<a href="javascript:void(0);" onclick="lookprobase('+id+');">查看</a>';
		                  } */	  
		                 
		                 
		                
					   
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
	top.openModule($("#fid").val(),'复核审计信息子项目-录入','/project/engineerAudit/input.do',null,'adds',id);
}

function reloadGrid() {
	
	var path = $("#coutextPath").val();
	//获取项目安排id
	var arrangeId=$("#projectId").val();
	//调用后台重新计算金额
	 $.ajax( {
			url : path + '/project/engineerAudit/findauditmoney.do',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'arrangeId='+arrangeId,
			success : function(data) {
		       $(".zongauditmoney").html(data.auditmoney.toFixed(2));
		       $(".zongcutmoney").html(data.cutmoney.toFixed(2));
		       //判断是否自审
		     //判断是否自审
		   	if(1==$("#isMySelfState").val())
		   	{
		   	    //获取送审金额的钱
			   	var sendmoney=$("#sentAmount").val();
			   	var zongcutmoney=data.cutmoney.toFixed(2);
			   	var lv=zongcutmoney/sendmoney;
			   	var alv=lv.toFixed(4)*100;
			   	if(alv==0){
			   		//得到审减率
			   		$("#shenjianlv").html("0");
			   	}else{
			   		//得到审减率
			   		$("#shenjianlv").html(alv.toFixed(2)+"%");
			   	}
		   	}else{
		   		// 获取中介 审定金额
		   		var zhongjieauditmoney = $("#zhongjieauditmoney").val();
			   	var zongcutmoney=data.cutmoney.toFixed(2);
			   	var lv=zongcutmoney/zhongjieauditmoney;
			   	var alv=lv.toFixed(4)*100;
			   	if(alv==0){
			   		//得到审减率
			   		$("#shenjianlv").html("0");
			   	}else{
			   		//得到审减率
			   		$("#shenjianlv").html(alv.toFixed(2)+"%");
			   	}
		   	}
		       
		      
			}
	});
	$("#subgrid").datagrid("reload");
	
	
}
//补充立项下载
function filedown2(){
	var url=$("#addProjectApprovalFile").val();
	if(null!=url && ""!=url)
	{	
	  window.location=encodeURI(encodeURI($("#path").val() + '/common/project/download.do?url='+url));
	  return false;
	}else{
		$.messager.show( {
			title : "提示",
			msg : "没有附件资料"
		});
	}
}
//概算信息下载
function filedown1(){
	var url=$("#budgetInfoFile").val();
	if(null!=url && ""!=url)
	{	
	  window.location=encodeURI(encodeURI($("#path").val() + '/common/project/download.do?url='+url));
	  return false;
	}else{
		$.messager.show( {
			title : "提示",
			msg : "没有附件资料"
		});
	}
}
//财务收支取证下载
function filedown3(){
	var url=$("#financialRAE").val();
	if(null!=url && ""!=url)
	{	
	  window.location=encodeURI(encodeURI($("#path").val() + '/common/project/download.do?url='+url));
	  return false;
	}else{
		$.messager.show( {
			title : "提示",
			msg : "没有附件资料"
		});
	}
}
function initializationfile() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
	$('#myfile').datagrid({   
	      url:$("#path").val()+'/common/project/findFile.do?method='+ $("#datapreId").val(),   
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
	      width:width-30
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
	window.location=encodeURI(encodeURI($("#path").val() + '/common/project/download.do?url='+url));
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