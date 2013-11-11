$(document).ready(function() {
	$("#remark").val(top.textTrim($("#remark").val()));
	var frameid=$("#frameid").val();
	initdepttree();
	//初始化已拥护角色列表
	initialization();
	//初始化未拥有角色
	initialization1();
	//初始化生日控件
		$('#birthday').datebox( {
			disabled : false,
			panelWidt:300,
			panelHeight:220,
			formatter : formatDate
		});
		$.extend($.fn.validatebox.defaults.rules, {
			mobile : {
				validator : function(value) {
					return /^1[0-9]{10}$/i.test($.trim(value));
				},
				message : '手机号码格式错误.'
			},
			idCardNo : {
				validator : function(value) {
				var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			    return reg.test(value)
				},
				message : "请正确输入您的身份证号码."
			}

		});

		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/system/user/update.do',
					success : function(data) {
						if (data.success) {
							top.showMsg(data.msg);
							top.reloadModule(frameid);
							top.closeModule(moduleName, parentModuleName);
						} else {
							top.showMsg(data.msg);
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

		//关闭按钮
		$("#back").click(function() {
			top.closeModule(moduleName, parentModuleName);
		});
		
		var time=$("#usertime").val();
		//生日
		$('#birthday').datebox('setValue',todate(time));

		//部门
		$("#deptid").val($("#deptcode").val());
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
//初始化机构下拉树形机构
function initdepttree(){
	$.ajax( {
		url : $("#coutextPath").val() + '/system/department/findbypid.do',
		type : "POST",
		async:false,
		success : function(json) {
			var result = eval("(" + json + ")");
			$$.InputTree.New( {
				"m_strNodeValue" : "deprtId",
				"m_strNodeText" : "name",
				"m_jsonData" : result,
				"m_strContainerID" : "deptid"
			});
		}
	});
}
function initialization() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
	$('#grid').datagrid({   
	      url:$("#coutextPath").val()+'/system/user/findroleuser.do?userid='+$("#id").val()+'&method=1',   
	      columns:[[   
	         {field:'rolename',title:'角色',width:fillsize(0.4),sortable:true},
	         {field:'remark',title:'备注',width:fillsize(0.4),sortable:true},
          {field:'OPERATION',title:'操作',width:fillsize(0.2) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
                var id = "'"+rowData.id+"'";
                return '<a href="javascript:void(0);" onclick="delRole('+id+');">取消</a>';
             }
           
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-10,
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

function initialization1() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
	$('#nogrid').datagrid({   
	      url:$("#coutextPath").val()+'/system/user/findroleuser.do?userid='+$("#id").val()+'&method=2',   
	      columns:[[   
	         {field:'rolename',title:'角色',width:fillsize(0.4),sortable:true},
	         {field:'remark',title:'备注',width:fillsize(0.4),sortable:true},
          {field:'OPERATION',title:'操作',width:fillsize(0.2) ,sortable:false,
        	  formatter : function(value, rowData, rowIndex) { 
                	 var id = "'"+rowData.id+"'";
                	 return '<a href="javascript:void(0);" onclick="addRole('+id+');">授权</a>';
             }
           
          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-380,
	      width:width-30
	  }); 
	    //设置分页控件   
	    var p = $('#nogrid').datagrid('getPager');   
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
	return (bodyWidth-80)*percent;
}

//授权
function addRole(id){
    url=$("#coutextPath").val()+"/system/user/update.do?method=addroleuser";
	$.ajax( {
			url : url,
			type : "POST",
			data :'userid='+$("#id").val()+'&roleid='+id,
			success : function(data) {
					if ("success"==data.success) {
						top.showMsg(data.msg);
						$("#grid").datagrid("reload");
						$("#nogrid").datagrid("reload");
					} else {
						top.showMsg(data.msg);
					}
			}
		});
}
//取消
function delRole(id){
	url=$("#coutextPath").val()+"/system/user/update.do?method=delroleuser";
     $.ajax( {
				url : url,
				type : "POST",
				data :'userid='+$("#id").val()+'&roleid='+id,
				success : function(data) {
						if ("success"==data.success) {
							top.showMsg(data.msg);
							$("#grid").datagrid("reload");
							$("#nogrid").datagrid("reload");
						} else {
							top.showMsg(data.msg);
						}
				}
	});
}
