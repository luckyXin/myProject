var isChangeImpowerTable = false; 
var bodyWidth = null;
var height = null;
$(document).ready(function() {
	height = document.body.clientHeight+50;
	bodyWidth = document.body.clientWidth ;
	width = document.body.clientWidth - 40;
	$("td").addClass("tdClass");
	var path = $("#coutextPath").val();
	var requestUrl = path + $("#requestUrl").val();
	var processType = $("#processType").val();
	$("#remark").val(top.textTrim($("#remark").val()));
	$('#pa').panel({
		width : width
	}); 
	$('#p1').panel({
		width : width
	}); 
	$('#p2').panel({
		width : width
	});
	var rolename;
	var state;
	var remark;
	if(processType == "update") {
		rolename = $("#rolename").val();
		state = $("#state").val();
		remark = $("remark").val();
	}
	$("#save").click(function() {
		var isUpdate = true;
		if (processType == "update") {
			var newRolename = $("#roleName").val();
			var newState = $("#state").val();
			var newRemark = $("#remark").val();
			if (rolename == newRolename && state == newState && remark == 
				newRemark && isChangeImpowerTable == false){
				isUpdate = false;
			} 
		}
		if (isUpdate) {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit({
					url : requestUrl,
					success : function(data) {
						if (data.isSuccess) {
							top.showMsg(data.msg);
							top.reloadModule($("#pid").val());
							top.closeModule(moduleName, parentModuleName);
						} else {
							msg = data.msg;
							top.showMsg(msg);
						}
					}
				});
			}
		} else {
			$.messager.show({
				title : $("#title").val(),
				msg : $("#noUpdate").val()
			});
		}
	});
	
	if(processType == "update") {
		topMenu();
		getSubMenu();
		$("#topMenu").combobox({onSelect:function(n,o){
			getSubMenu();
			subMenu();
		}});
		
		$("#subMenu").combobox({onSelect:function(n,o){
			getModule();
			moduleMenu();
		}});
		
		$("#moduleMenu").combobox({onSelect:function(n,o){
			functionMenu();
		}});
		
		$("#initialize").click(function(){
			topMenu();
		});
	}
	areadyHaveImpower();
});

function functionMenu(){
	$('#impowerTable').datagrid({
	      url:$("#coutextPath").val() + 
	      '/system/Role/find.do?proccessType=getFunctionMenu&roleId='+$("#roleId").val()+'&moduleId='+$("#moduleMenu").combobox("getValue"),
	      columns:[[ 
	         {field:'id',title:'方法编码',width:fillsize(0.1),sortable:true},
	         {field:'name',title:'方法名称',width:fillsize(0.15) ,sortable:true},
	         {field:'method',title:'方法逻辑名',width:fillsize(0.15) ,sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) {
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==1){
	                    	 return "禁用 ";
	                     }
	              }
	          },
	         {field:'createTime',title:'创建时间',width:fillsize(0.15),sortable:true},
	         {field:'remark',title:'备注',width:fillsize(0.20),sortable:true},
	         {field:'impower',title:'操作(双击)',width:fillsize(0.1),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) {
                     if(value==1){
                    	 return "已授权";
                     }
                     if(value==0){
                    	 return "未授权";
                     }
              }}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-100,
	      onDblClickRow : function(rowIndex, rowData){
	    	  $.post($("#coutextPath").val() + "/system/Role/update.do", {functionId : rowData.id,impower:rowData.impower,
	    		  proccessType : "updateFunction",roleId:$("#roleId").val(), moduleId:$("#moduleMenu").combobox("getValue")}, 
	  				function (data, textStatus){
	  			$.messager.show({
	  				title : $("#title").val(),
	  				msg : data.msg
	  			});
	  			$("#impowerTable").datagrid("reload");
	  			$('#areadyHaveImpower').treegrid('reload');
	  		});
	      }
	  }); 
  // 设置分页控件
  var p = $('#impowerTable').datagrid('getPager');   
  $(p).pagination({   
      pageSize: 10,// 每页显示的记录条数，默认为10
      pageList: [5,10,15],// 可以设置每页记录条数的列表
      beforePageText: '第',// 页数文本框前显示的汉字
      afterPageText: '页    共 {pages} 页',   
      displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
  });
}

function moduleMenu(){
	$('#impowerTable').datagrid({
	      url:$("#coutextPath").val() + 
	      '/system/Role/find.do?proccessType=getModuleMenu&roleId='+$("#roleId").val()+'&subMenuId='+$("#subMenu").combobox("getValue"),
	      columns:[[ 
	         {field:'moduleId',title:'模块编码',width:fillsize(0.1),sortable:true},
	         {field:'moduleName',title:'模块名称',width:fillsize(0.15) ,sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) {
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==1){
	                    	 return "禁用 ";
	                     }
	              }
	          },
	         {field:'createTime',title:'创建时间',width:fillsize(0.15),sortable:true},
	         {field:'menuName',title:'上级菜单名称',width:fillsize(0.15),sortable:true},
	         {field:'remark',title:'备注',width:fillsize(0.20),sortable:true},
	         {field:'impower',title:'操作(双击)',width:fillsize(0.1),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) {
                     if(value==1){
                    	 return "已授权";
                     }
                     if(value==0){
                    	 return "未授权";
                     }
              }}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-100,
	      onDblClickRow : function(rowIndex, rowData){
	    	  $.post($("#coutextPath").val() + "/system/Role/update.do", {moduleId : rowData.moduleId,impower:rowData.impower,
	    		  proccessType : "updateModuleMenu",roleId:$("#roleId").val()}, 
	  				function (data, textStatus){
	  			$.messager.show({
	  				title : $("#title").val(),
	  				msg : data.msg
	  			});
	  			$("#impowerTable").datagrid("reload");
	  			$('#areadyHaveImpower').treegrid('reload');
	  		});
	      }
	  }); 
  // 设置分页控件
  var p = $('#impowerTable').datagrid('getPager');   
  $(p).pagination({   
      pageSize: 10,// 每页显示的记录条数，默认为10
      pageList: [5,10,15],// 可以设置每页记录条数的列表
      beforePageText: '第',// 页数文本框前显示的汉字
      afterPageText: '页    共 {pages} 页',   
      displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
  });
}

function subMenu(){
	$('#impowerTable').datagrid({
	      url:$("#coutextPath").val() + 
	      '/system/Role/find.do?proccessType=getSubMenu&roleId='+$("#roleId").val()+'&topMenuId='+$("#topMenu").combobox("getValue"),
	      columns:[[ 
	         {field:'id',title:'菜单编码',width:fillsize(0.1),sortable:true},
	         {field:'menuName',title:'菜单名称',width:fillsize(0.15) ,sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) {
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==1){
	                    	 return "禁用 ";
	                     }
	              }
	          },
	         {field:'createTime',title:'创建时间',width:fillsize(0.15),sortable:true},
	         {field:'sort',title:'顺序',width:fillsize(0.15),sortable:true},
	         {field:'remark',title:'备注',width:fillsize(0.20),sortable:true},
	         {field:'impower',title:'操作(双击)',width:fillsize(0.1),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) {
                     if(value==1){
                    	 return "已授权";
                     }
                     if(value==0){
                    	 return "未授权";
                     }
              }}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-100,
	      onDblClickRow : function(rowIndex, rowData){
	    	  $.post($("#coutextPath").val()+"/system/Role/update.do", {subMenuId : rowData.id,impower:rowData.impower,
	    		  proccessType : "updateSubMenu",roleId:$("#roleId").val()}, 
	  				function (data, textStatus){
	  			$.messager.show({
	  				title : $("#title").val(),
	  				msg : data.msg
	  			});
	  			$("#impowerTable").datagrid("reload");
	  			$('#areadyHaveImpower').treegrid('reload');
	  		});
	      }
	  }); 
  // 设置分页控件
  var p = $('#impowerTable').datagrid('getPager');   
  $(p).pagination({   
      pageSize: 10,// 每页显示的记录条数，默认为10
      pageList: [5,10,15],// 可以设置每页记录条数的列表
      beforePageText: '第',// 页数文本框前显示的汉字
      afterPageText: '页    共 {pages} 页',   
      displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
  });
}

function topMenu(){
	$('#impowerTable').datagrid({
	      url:$("#coutextPath").val() + '/system/Role/find.do?proccessType=getTopMenu&roleId='+$("#roleId").val(),
	      columns:[[ 
	         {field:'id',title:'导航编码',width:fillsize(0.1),sortable:true},
	         {field:'menuName',title:'导航名称',width:fillsize(0.15) ,sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) {
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==1){
	                    	 return "禁用 ";
	                     }
	              }
	          },
	         {field:'createTime',title:'创建时间',width:fillsize(0.15),sortable:true},
	         {field:'sort',title:'顺序',width:fillsize(0.15),sortable:true},
	         {field:'remark',title:'备注',width:fillsize(0.20),sortable:true},
	         {field:'impower',title:'操作(双击)',width:fillsize(0.1),sortable:true,
	        	 formatter : function(value, rowData, rowIndex) {
                     if(value==1){
                    	 return "已授权";
                     }
                     if(value==0){
                    	 return "未授权";
                     }
              }}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-100,
	      onDblClickRow : function(rowIndex, rowData){
	    	  $.post($("#coutextPath").val()+"/system/Role/update.do", {topMenuId : rowData.id,impower:rowData.impower,
	    		  proccessType : "updateTopMenu",roleId:$("#roleId").val()}, 
	  				function (data, textStatus){
	  			$.messager.show({
	  				title : $("#title").val(),
	  				msg : data.msg
	  			});
	  			$("#impowerTable").datagrid("reload");
	  			$('#areadyHaveImpower').treegrid('reload');
	  		});
	      }
	  }); 
  // 设置分页控件
  var p = $('#impowerTable').datagrid('getPager');   
  $(p).pagination({   
      pageSize: 10,// 每页显示的记录条数，默认为10
      pageList: [5,10,15],// 可以设置每页记录条数的列表
      beforePageText: '第',// 页数文本框前显示的汉字
      afterPageText: '页    共 {pages} 页',   
      displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
  });
}

function areadyHaveImpower(){
	$('#areadyHaveImpower').treegrid({
	      url:$("#coutextPath").val() + '/system/Role/find.do?proccessType=getTreeMenu&roleId='+$("#roleId").val(),
	      treeField : 'menuId',
			animate : true,
			idField : 'menuId',
	      columns:[[ 
	         {field:'menuId',title:'菜单编码',width:fillsize(0.2),sortable:true},
	         {field:'menuName',title:'菜单名称',width:fillsize(0.2) ,sortable:true},
	         {field:'menuState',title:'状态',width:fillsize(0.2),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) {
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==1){
	                    	 return "禁用 ";
	                     }
	              }
	          },
	         {field:'remark',title:'备注',width:fillsize(0.2),sortable:true}
	      ]],
						singleSelect : true,
						pagination : true,
						striped : true,
						height : height - 100
	  }); 
// 设置分页控件
var p = $('#areadyHaveImpower').treegrid('getPager');   
$(p).pagination({   
    pageSize: 10,// 每页显示的记录条数，默认为10
    pageList: [5,10,15],// 可以设置每页记录条数的列表
    beforePageText: '第',// 页数文本框前显示的汉字
    afterPageText: '页    共 {pages} 页',   
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
});
}

function fillsize(percent){
	return (bodyWidth-60)*percent;
}

function getSubMenu(){
	var topMenuId = $("#topMenu").combobox("getValue");
	$('#subMenu').combobox("clear");
	$('#subMenu').combobox('reload', $("#coutextPath").val() + '/system/Module/getSubMenu.do?topMenuId='+topMenuId);
}

function getModule(){
	var subMenuId = $("#subMenu").combobox("getValue");
	$('#moduleMenu').combobox("clear");
	$('#moduleMenu').combobox('reload', $("#coutextPath").val() + '/system/Role/getModule.do?subMenuId='+subMenuId);
}

