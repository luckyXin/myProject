var baseheight=450;
$(document).ready(function() {
	//更改td 里面的时间的格式
					
					$("#sj1").text($("#sj1").text().substr(0,10))
					$("#sj2").text($("#sj2").text().substr(0,10))
	
	$("#remark").val(textTrim($("#remark").val()));
	var height = document.body.clientHeight;
	var width = $(document.body).width() - 40;
	$('#p2').panel({
		width : width,
		title : '中介机构初审基本信息',
		iconCls : 'icon-edit'
	});
	$('#p3').panel({
		width : width,
		title : '初审审核结果录入',
		iconCls : 'icon-edit'
	});
	
	//实际提交初审结束时间		
	$('#auditoktime').datebox( {
		disabled : false,
		panelWidt:300,
		panelHeight:220,
		formatter : formatDate
    });
	//审定金额
	$('#auditmoney').numberbox({   
	   precision:2   
	 }); 
	$('#deferday').numberbox({   
		   precision:0   
	 }); 
	//失去焦点事件
	$("#auditmoney").blur(function(){
		//获取审定金额的钱
		var auditmoney=$("#auditmoney").val();
		if(null!=auditmoney && ""!=auditmoney)
		{	
			//获取送审金额的钱
			var sendmoney=$("#sendmoney").val();
			//得到审减金额的钱
			var cutmoney=sendmoney-auditmoney;
			$("#cutmoney").val(cutmoney.toFixed(2));
			var a=cutmoney/sendmoney;
			var b=a.toFixed(4)*100;
			if(b==0){
				//得到审减率
				$("#auditlv").val("0");
			}else{
				//得到审减率
				$("#auditlv").val(b.toFixed(2)+"%");
			}
			
		}else{
			$("#cutmoney").val("");
		}
	});
	//判断文件是否存在是否显示
	 $.ajax( {
		 url:$("#path").val()+'/common/project/findhavedatafile.do',   
			type : "POST",
			dataType : "json",
			async:false,
			data : 'datapreId='+$("#datapreId").val(),
			success : function(data) {
		      if(data.count>0){
		    	//查询单个项目上传的资料
		        initializationfile();
		      }
			}
	});
	
	
	//初审提交时间失去焦点
	$("#deferday").blur(function(){
		var zhongjiesubmit=$("#zhongjiesubmit").val();
		var auditoktime=$('#auditoktime').datebox('getValue');
		var deferday=$("#deferday").val();
		if(null!=deferday && ""!=deferday && null!=auditoktime && ""!=auditoktime){
			var dateArr=zhongjiesubmit.split("-");
			var now=new Date(dateArr[0],dateArr[1],dateArr[2]);
            now.setDate(now.getDate()+parseInt(deferday));
            var temp = now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate();
            var begin=temp.split("-");
            var date1=new Date(begin[0],begin[1],begin[2]);  //开始时间
            var end=auditoktime.split("-");
            var date2=new Date(end[0],end[1],end[2]);    //结束时间
            var date3=date1.getTime()-date2.getTime();  //时间差的毫秒数
            //计算出相差天数
            var days=Math.floor(date3/(24*3600*1000));
            if(days>=0){
            	$("#overday").val("0");
            }else{
            	$("#overday").val(days);
            }
		}
	});
	//保存按钮
	$("#singlesave").click(function() {
		if ($('#form').form('validate')) {
			$("#form").ajaxSubmit( {
				url : $("#path").val() + '/project/intermediaryAudit/add.do',
				success : function(result) {
					if (result.success=="success") {
						top.showMsg(result.msg);
						top.reloadModule($("#frameid").val()+"add");
						top.closeModule(moduleName, parentModuleName);
					} else {
						top.showMsg(result.msg);
					}
				}
			});
		} else {
			$.messager.show( {
				title : "提示",
				msg : "请正确填写"
			});
			return false;
		}
	});
			
	
	var time=$("#chushengtime").val();
    $("#chushengoktime").val(todate(time));
	$('#auditoktime').datebox('setValue',todate(time));
	
	$("#suspend").click(function(){
		//得到资料预审id
		var datapreId=$("#datapreId").val();
	   openproiv(550,350,datapreId);
	});

});

function textTrim(txt) {
	if(undefined ==txt)
	{
		txt="";
	}	
	return txt.replace(/(^\s*)|(\s*$)/g, "");
}

//格式化时间
function formatDate(v) {
	if (v instanceof Date) {
		var y = v.getFullYear();
		var m = v.getMonth() + 1;
		var d = v.getDate();
		var h = v.getHours();
		var i = v.getMinutes();
		var s = v.getSeconds();
		var ms = v.getMilliseconds();

		if (ms > 0) {
			return y + '-' + m + '-' + d + ' ' + h + ":" + s + ":" + i;
		}
		if (h > 0 || m > 0 || d > 0) {
			if(m<10){
				m="0"+m;
			}
			if(d<10){
				d="0"+d;
			}
			time= y + '-' + m + '-' + d;
			var zhongjiesubmit=$("#zhongjiesubmit").val();
			var deferday=$("#deferday").val();
			if(null!=deferday && ""!=deferday && null!=time && ""!=time){
				var dateArr=zhongjiesubmit.split("-");
				var now=new Date(dateArr[0],dateArr[1],dateArr[2]);
	            now.setDate(now.getDate()+parseInt(deferday));
	            var temp = now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate();
	            var begin=temp.split("-");
	            var date1=new Date(begin[0],begin[1],begin[2]);  //开始时间
	            var end=time.split("-");
	            var date2=new Date(end[0],end[1],end[2]);    //结束时间
	            var date3=date1.getTime()-date2.getTime();  //时间差的毫秒数
	            //计算出相差天数
	            var days=Math.floor(date3/(24*3600*1000));
	            if(days>=0){
	            	$("#overday").val("0");
	            }else{
	            	$("#overday").val(days);
	            }
			}
			
			
			
			return time;
		}
	}
	return '';
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
