var falgsate = null;

function opendatashowdiv(id, title, width, height) {
	 $.ajax( {
			url :  $("#path").val() + '/common/project/finddata.do',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'id='+id,
			success : function(data) {
		        $(".projectNo").html(data.projectNo);
		        $(".auditMoney").html(data.auditMoney);
		        $(".projectTime").html(todate(data.projectTime));
		        $(".projectName").html(data.projectName);
		        if(null==data.ownerName && ""==data.ownerName)
		        {
		        	$(".ownerName").html("");
		        }else{
		        	$(".ownerName").html(data.ownerName);
		        }	
		        if(null==data.proownerlink && ""==data.proownerlink)
		        {
		        	 $(".proownerlink").html("");
		        }else{
		        	 $(".proownerlink").html(data.proownerlink);
		        }
		        if(null==data.proownertelphone && ""==data.proownertelphone)
		        {
		        	 $(".proownertelphone").html("");
		        }else{
		        	 $(".proownertelphone").html(data.proownertelphone);
		        }   
		        if(null==data.constructId && ""==data.constructId)
		        {
		        	$(".constructName").html("");
		        }else{
		        	$(".constructName").html(data.constructId);
		        }	
		        if(null==data.constructlink && ""==data.constructlink)
		        {
		        	$(".constructlink").html("");
		        }else{
		        	$(".constructlink").html(data.constructlink);
		        }	
		        if(null==data.constructtelphone && ""==data.constructtelphone)
		        {
		        	$(".constructtelphone").html("");
		        }else{
		        	$(".constructtelphone").html(data.constructtelphone);
		        }	
		        $(".audittype").html(data.audittype);
		        $(".sentAmount").html(data.sentAmount);
		        if(1==data.isExpedited)
		        {	
		              $(".isExpedited").html("是");
		        }else{
		        	  $(".isExpedited").html("否");
		        }
		        $(".datapreopinion").html(data.datapreopinion);
		        $(".datapreOperate").html(data.datapreOperate);
		        $(".datapretime").html( todate(data.datapretime));
			}
	});
	$("#win").show();
	$('#win').window({
		width : width,
		height : height,
		modal : false,
		collapsible : false,
		minimizable : false,
		title : title,
		iconCls : 'icon-search',
		draggable : true
	});
	
	//查询资料文件
	fileloadyushen(id,width);
}


function fileloadyushen(id,width) {
	var height = document.body.clientHeight;
	$('#myfilediv').datagrid({   
	      url:$("#path").val()+'/common/project/findFile.do?method='+ id,   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.2),sortable:true},
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
	      width:width-20
	  }); 
	    //设置分页控件   
	    var p = $('#myfilediv').datagrid('getPager');   
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
	return (bodyWidth-180)*percent;
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
