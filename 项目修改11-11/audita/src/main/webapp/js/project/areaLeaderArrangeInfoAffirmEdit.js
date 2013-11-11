var oldRemark = null;
var oldSubmitState = null;
var oldPassState = null;
var baseheight=550;
$(document).ready(
		function() {
				$("#sj1").text($("#sj1").text().substr(0,10))
			// 保存审批信息
			$("#save").click(
					function() {
						url = $("#coutextPath").val()
								+ '/project/ArrangeAffirm/update.do';
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

function loadareafile() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
	$('#areamyfile').datagrid({   
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
	      width:width-30
	  }); 
	    //设置分页控件   
	    var p = $('#areamyfile').datagrid('getPager');   
	    $(p).pagination({   
	        pageSize: 5,//每页显示的记录条数，默认为10   
	        pageList: [5,10,15],//可以设置每页记录条数的列表   
	        beforePageText: '第',//页数文本框前显示的汉字   
	        afterPageText: '页    共 {pages} 页',   
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    }); 
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