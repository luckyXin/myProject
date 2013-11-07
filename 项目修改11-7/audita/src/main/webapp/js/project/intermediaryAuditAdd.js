var baseheight=250;
$(document).ready(function() {
	//更改时间格式
	$("#sj1").text($("#sj1").text().substr(0,10))
	$("#sj2").text($("#sj2").text().substr(0,10))
	$("#sj3").text($("#sj3").text().substr(0,10))
	$("#sj4").text($("#sj4").text().substr(0,10))
	
	
	$("#remark").val(textTrim($("#remark").val()));
	var height = document.body.clientHeight;
	var width = $(document.body).width() - 40;
	if(0==$("#protype").val()){
		baseheight=350;
	}
	//判断是否存在科长未审核意见
	if(1==$("#issectionaudit").val()){
		$("#pp2").show();
		baseheight=450;
	}
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
	$('#totalp3').panel({
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
	
	
	
	//总的审减金额
	zongcutmoney=$("#zongcutmoney").val();
	//获取送审金额的钱
	var sendmoney=$("#sendmoney").val();
	var lv=zongcutmoney/sendmoney;
	var alv=lv.toFixed(4)*100;
	if(alv==0){
		//得到审减率
		$("#shenjianlv").html("0");
	}else{
		//得到审减率
		$("#shenjianlv").html(alv.toFixed(2)+"%");
	}
	
	
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
			$("#auditlv").val("");
		}
	});
	
	//初审提交时间失去焦点
	$("#deferday").blur(function(){
		var zhongjiesubmit=$("#zhongjiesubmit").val();
		var auditoktime=$('#auditoktime').datebox('getValue');
		var deferday=$("#deferday").val();
		if(null!=deferday && ""!=deferday && null!=auditoktime && ""!=auditoktime && null!=zhongjiesubmit && ""!=zhongjiesubmit){
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
						top.reloadModule($("#frameid").val());
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
	
	//总的提交
	$("#totalsave").click(function(){
		if ($('#form').form('validate')) {
			var auditoktime = $("#auditoktime").datebox("getValue");
			var deferday=$("#deferday").val();
			var overday=$("#overday").val();
			var state=0;
			if(document.getElementById("state").checked){ 
				state=1;
			}
		 $.ajax( {
				url :  $("#path").val() + '/project/intermediaryAudit/add.do?method=totalsubmit',
				type : "POST",
				dataType : "json",
				async:false,
				data : 'arrangeId='+$("#arrangeId").val()+'&auditoktime='+auditoktime+'&deferday='+deferday+'&overday='+overday+'&state='+state,
				success : function(result) {
			        if (result.success=="success") {
						top.showMsg(result.msg);
						top.reloadModule($("#frameid").val());
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
		$('#projectbase').panel({
			height : 240,
			title : '项目基本信息',
			iconCls : 'icon-edit'
		});
		
		//查询子项目信息
		selectsubproject();
	}
	
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
			if(null!=deferday && ""!=deferday && null!=time && ""!=time && null!=zhongjiesubmit && ""!=zhongjiesubmit){
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

function selectsubproject() {
	var path = $("#path").val();
	//获取项目安排id
	var arrangeId=$("#arrangeId").val();
	$('#subgrid').datagrid({
			url : path + '/common/project/selectintersubproject.do?arrangeId='+arrangeId,
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
						width : fillsize(0.1),
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
			                  var issubmit=$("#issubmit").val();
			                  if(issubmit==0)
			                  {
			                	  return '<a href="javascript:void(0);" onclick="lookprobase('+id+');">审核</a>';
			                  }else{
			                	  return '<a href="javascript:void(0);" onclick="lookprobase('+id+');">查看</a>';
			                  } 	  
			                 
			                 
			                
						   
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
	return (bodyWidth - 100) * percent;
}

function lookprobase(id){
	top.openModule($("#frameid").val(),'初审审核单项目-录入','/project/intermediaryAudit/input.do',null,'adds',id);
}

function reloadGrid() {
	var arrangeId=$("#arrangeId").val();
	//调用后台重新计算金额
	 $.ajax( {
			url :  $("#path").val() + '/project/intermediaryAudit/findauditmoney.do',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'arrangeId='+arrangeId,
			success : function(data) {
		       $(".zongauditmoney").html(data.auditmoney.toFixed(2));
		       $(".zongcutmoney").html(data.cutmoney.toFixed(2));
			     //获取送审金额的钱
			   	var sendmoney=$("#sendmoney").val();
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
			}
	});
	$("#subgrid").datagrid("reload");
	
	
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
             {field:'OPERATION',title:'操作',width:fillsize(0.1) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '<a href="javascript:void(0);" onclick="download('+url+');return false;">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
             }
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width-60
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
//下载
function download(url){
	window.location=encodeURI(encodeURI($("#path").val() + '/common/project/download.do?url='+url));
	return false;
}

function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-50)*percent;
}

