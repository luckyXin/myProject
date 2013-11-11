var baseheight=640;
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
	//判断文件是否存在是否显示
	 $.ajax( {
		 url:$("#coutextPath").val()+'/common/project/findhavedatafile.do',   
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
	
	
	
	var submitState = $("#submitState").val();
	if (submitState == '1') {
		$("#auditRemark").attr("disabled", "disabled");
		$("#auditStartTime").datebox({
			disabled:true
		});
		$("#auditEndTime").datebox({
			disabled:true
		});
		$("input").attr("disabled", "disabled");
		$("#save").hide();
		$("#suspend").hide();
		
	}
	$('#auditMoney').numberbox({   
		   precision:2   
	}); 
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
						top.reloadModule($("#fid").val()+"add");
						top.closeModule(moduleName, parentModuleName);
					} else {
						top.showMsg(data.msg);
					}
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
	
	  $("#suspend").click(function(){
			//得到资料预审id
			var datapreId=$("#datapreId").val();
		   openproiv(550,350,datapreId);
		});
});

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
