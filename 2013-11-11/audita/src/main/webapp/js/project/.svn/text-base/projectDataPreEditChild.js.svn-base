$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#datapreopinion").val(top.textTrim($("#datapreopinion").val()));
	//立项时间控件
		$('#projectTime').datebox( {
			disabled : false,
			panelWidt:300,
			panelHeight:220,
			formatter : formatDate
		});
    //资料预审时间		
		$('#datapretime').datebox( {
			disabled : false,
			panelWidt:300,
			panelHeight:220,
			formatter : formatDate
	});
	//概算批改时间		
	$('#budgetUpdateTime').datebox( {
			disabled : false,
			panelWidt:300,
			panelHeight:220,
			formatter : formatDate
	});	
	//批复金额
	$('#auditMoney').numberbox({   
	   precision:2   
     });  
    //送审金额
	$('#sentAmount').numberbox({   
		   min:0, 
		   precision:2   
     });  
	 //直接费用
	$('#zjMoney').numberbox({   
		   min:0, 
		   precision:2   
     })
      //中标价
	$('#zbMoney').numberbox({   
		   min:0, 
		   precision:2   
     });
	 $('#htmoney').numberbox({   
		   min:0, 
		   precision:2   
   });
   $('#zhanliemoney').numberbox({   
		   min:0, 
		   precision:2   
   });
    //概算总金额
	$('#budgetTotalMoney').numberbox({   
		   min:0, 
		   precision:2   
    });
	  //概算工程直接费用
	$('#budgetDirectMoney').numberbox({   
		   min:0, 
		   precision:2   
   });
	//验证项目名称	
	$.extend($.fn.validatebox.defaults.rules, {
		ishave : {
		validator : function(value) {
		 var falg=true;
		  $.ajax( {
			url :  $("#path").val() + '/project/DataPre/findbyid.do?method=name',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'id='+$("#id").val()+'&projectName='+value,
			success : function(data) {
				if ("yes"==data.ishave) {
					falg=false;
				}
			}
		   });
		return falg;
		},
		message : '项目名称存在请重新填写'
		}
	});	
	//验证立项文号	
	/*$.extend($.fn.validatebox.defaults.rules, {
		ishave : {
		validator : function(value) {
		 var falg=true;
		  $.ajax( {
			url :  $("#path").val() + '/project/DataPre/findbyid.do?i',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'id='+$("#id").val()+'&projectNo='+value,
			success : function(data) {
				if ("yes"==data.ishave) {
					falg=false;
				}
			}
		   });
		return falg;
		},
		message : '立项文号存在请重新填写'
		}
	});*/
	 $("#constructName").focus(function(){
		    //隐藏
		    $("#constructlinktext").show();
			$("#constructlink").hide();
			$("#constructlink").val("");
			$("#constructtelphone").val("");
	 });
	 $("#constructName").dblclick(function(){
		 openshowdiv(0, "施工企业", 600, 350,270,null);
	 });
	 
    $("#addfile").click(function(){
		   var tr=$("<tr><td class='label' align='right'>送审资料文档：</td>"+
					"<td colspan='3' align='left'><input  type='file' style='width: 400px;' class='text'  name='uploadFile'>  <a class='easyui-linkbutton deletefile'"+
					"iconCls='icon-cancel'>删除</a></td></tr>");
				$("#files").before(tr);
				tr.find(".deletefile").click(function(){
					$(this).parent().parent().remove();
				});
				$(".easyui-linkbutton").linkbutton({
					plain:false
				});
	});
		//保存按钮
		$("#save").click(function() {
				if ($('#form').form('validate')) {
					$("#form").ajaxSubmit( {
						url : $("#path").val() + '/project/DataPre/update.do?method=updatechild',
						success : function(data) {
						if(null==data || ""==data)
						{
							$.messager.show( {
								title : "提示",
								msg : "系统异常"
							});
						}else{	
						   var result = eval("(" + data + ")");
							if (result.success=="success") {
								top.showMsg(result.msg);
								top.reloadModule(frameid);
								top.closeModule(moduleName, parentModuleName);
							} else {
								top.showMsg(result.msg);
							}
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
		//立项时间
		$('#projectTime').datebox('setValue',todate($("#lixiangtime").val()));
		//资料预审时间
		$('#datapretime').datebox('setValue',todate($("#yushengtime").val()));
		
		//概算批改时间
		//$('#budgetUpdateTime').datebox('setValue',todate($("#gaisuantime").val()));
		//查询该资料预审下面关联的资料文件
		initializationfile();
});


function initializationfile() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
	$('#grid').datagrid({   
	      url:$("#path").val()+'/project/DataPre/find.do?method='+ $("#id").val(),   
	      columns:[[   
	         {field:'fileName',title:'文件名称',width:fillsize(0.5),sortable:true},
	         {field:'uploadTime',title:'上传时间',width:fillsize(0.3),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) { 
            	   return todate(value);
                 }
	         
	         },
             {field:'OPERATION',title:'操作',width:fillsize(0.2) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
            	 var url = "'"+rowData.url+"'";
            	 var id = "'"+rowData.id+"'";
            	 return '          <a href="javascript:void(0);" onclick="download('+url+');">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="delFile('+id+');">删除</a>';
             }
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      width:width-30
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
function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-50)*percent;
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
			return y + '-' + m + '-' + d;
		}
	}
	return '';
}



function returnbackfunction(rowData){
	$("#constructName").val(rowData.constructname);
	$("#constructId").val(rowData.id);
	$("#constructlinktext").hide();
	$("#constructlink").show();
	$("#constructlinktext").val("");
	$("#constructtelphone").val("");
	$.ajax( {
		url :  $("#path").val() + '/project/DataPre/findlinkbyid.do',
		type : "POST",
		dataType : "json",
		data : "key=" + rowData.id,
		success : function(result) {
			document.getElementById("constructlink").options.length=0; 
			document.getElementById("constructlink").options.add(new Option("","novalue")); 
		    for(var i=0;i<result.length;i++){
		    	document.getElementById("constructlink").options.add(new Option(result[i].linkname,result[i].id)); 
		    }
		    //清空联系人电话
		    $("#constructtelphone").val("");
		}
	});
	close();
}

//下载
function download(url){
	window.location=encodeURI(encodeURI($("#path").val() + '/project/DataPre/download.do?url='+url));
	return false;
}
//删除
function delFile(id){
	if(1==$("#isconfirmSubmit").val())
	{
    	$.messager.show({
    		title:'提示',
			msg: '提交后不能修改',
			timeout:3000
       });
    	return false;
	    
	}
	else
	{	
		$.ajax( {
			url :  $("#path").val() + '/project/DataPre/deleteFile.do',
			type : "POST",
			dataType : "json",
			data : "key=" + id,
			success : function(result) {
			  if(result.success=="success")
			  {
				  $.messager.show({
			    		title:'提示',
						msg: result.msg,
						timeout:3000
			       });
				  $("#grid").datagrid("reload");
			  }else{
				  $.messager.show({
			    		title:'提示',
						msg: result.msg,
						timeout:3000
			       });
				  return false;
			  }
			   
			}
		});
	}
}
function constructlinkchange(){
	var id=$("#constructlink").val();
	$.ajax( {
		url :  $("#path").val() + '/project/DataPre/findlinkbyphone.do',
		type : "POST",
		dataType : "json",
		data : "id=" +id,
		success : function(result) {
		    $("#constructtelphone").val(result.phone);
		}
	});
	
}
function projectfinish(){
	   top.openModule($("#frameid").val(),'项目竣工决算资料单','/project/DataPre/input.do',null,'projectfinish',$("#id").val());
}
function projoinlist(){
	 top.openModule($("#frameid").val(),'资料交接清单','/project/DataPre/input.do',null,'projoinlist',$("#id").val());
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