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
     });
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
			data : 'projectName='+value,
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
			url :  $("#path").val() + '/project/DataPre/findbyid.do',
			type : "POST",
			dataType : "json",
			async:false,
			data : 'projectNo='+value,
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
			$("#constructlink").hide();
			$("#constructtelphone").hide();
			$("#linktext").show();
			$("#telphonetext").show();
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
					url : $("#path").val() + '/project/DataPre/add.do?method=addchild',
					success : function(data) {
					   var result = eval("(" + data + ")");
						if (result.success=="success") {
							top.showMsg(result.msg);
							top.reloadModule(frameid);
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
	});

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

//回调方法
function returnbackfunction(rowData){
	$("#constructlink").show();
	$("#constructtelphone").show();
	$("#linktext").hide();
	$("#telphonetext").hide();
	$("#linktext").val("");
	$("#telphonetext").val("");
	$("#constructName").val(rowData.constructname);
	$.ajax( {
		url :  $("#path").val() + '/project/DataPre/findlinkbyid.do',
		type : "POST",
		dataType : "json",
		data : "key=" + rowData.id,
		success : function(result) {
			document.getElementById("constructlink").options.length=0; 
			document.getElementById("constructlink").options.add(new Option("","")); 
		    for(var i=0;i<result.length;i++){
		    	document.getElementById("constructlink").options.add(new Option(result[i].linkname,result[i].id)); 
		    }
		    //清空联系人电话
		    $("#constructtelphone").val("");
		}
	});
	close();
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
	$.messager.show( {
		title : "提示",
		msg : "请先保存资料"
	});
	return false;
}
function projoinlist(){
	$.messager.show( {
		title : "提示",
		msg : "请先保存资料"
	});
	return false;
}