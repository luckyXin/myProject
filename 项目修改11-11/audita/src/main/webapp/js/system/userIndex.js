$(document).ready(function() {
	var isfind=$("#isfind").val();
	var isinput = $("#input").val();
	if (isinput != '1') {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noPower").val()
		});
		return false;
	}
	if(null!=isfind && "null"!=isfind && ""!=isfind && undefined!=isfind)
	{
		//初始化列表
		initialization();
		var height = document.body.clientHeight;
		$('#p1').panel({
			height : height-15,
			title : '用户管理',
			iconCls : 'icon-tip'
		});
	}
});

function initialization() {
	var height = document.body.clientHeight;
    var path=$("#root").val();
	$('#grid').datagrid({   
	      url:path+'/system/user/find.do',   
	      columns:[[   
	         {field:'username',title:'姓名',width:fillsize(0.1),sortable:true},
	         {field:'sex',title:'性别',width:fillsize(0.05) ,sortable:true,
	               formatter : function(value, rowData, rowIndex) { 
	        	          if(null==value){
	        	        	  return "";  
	        	          }else{
	        	        	  return value.dictionaryname;  
	        	          }
	                     
	                    }
	          },
	          {field:'deptid',title:'部门',width:fillsize(0.1) ,sortable:true,
	               formatter : function(value, rowData, rowIndex) {
			        	  if(null==value){
		    	        	  return "";  
		    	          }else{
		    	        	  return value.deptname;  
		    	          }
	                    }        
	          },
	          {field:'qualification',title:'类型',width:fillsize(0.1) ,sortable:true,
	               formatter : function(value, rowData, rowIndex) { 
				        	  if(null==value){
			    	        	  return "";  
			    	          }else{
			    	        	  return value.dictionaryname;  
			    	          }
	                    }
	          },
	          {field:'telphone',title:'电话号码',width:fillsize(0.15),sortable:true},
	          {field:'cardId',title:'身份证号码',width:fillsize(0.15),sortable:true},
	          {field:'email',title:'邮箱',width:fillsize(0.1),sortable:true},
	          {field:'state',title:'状态',width:fillsize(0.05),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) { 
	                     if(value==1){
	                    	 return "禁用";
	                     }
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==2){
	                    	 return "删除";
	                     }
	                     
	              }
	          },
	          {field:'remark',title:'备注',width:fillsize(0.15),sortable:true}
	        
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      fitColumns:true,
	      idField:"id",
	      collapsible:true,
	      height:height-100
	  }); 
	    //设置分页控件   
	    var p = $('#grid').datagrid('getPager');   
	    $(p).pagination({   
	        pageSize: 10,//每页显示的记录条数，默认为10   
	        pageList: [10,20,30],//可以设置每页记录条数的列表   
	        beforePageText: '第',//页数文本框前显示的汉字   
	        afterPageText: '页    共 {pages} 页',   
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    }); 
}

function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth)*percent;
}


function reloadGrid(){
	$("#grid").datagrid("reload");
}



/**
 * 增加
 * @return
 */
function add() {
	var frameid=$("#frameid").val();
	top.openModule(frameid,'添加用户','/system/user/input.do',null,'add',null);
}

/**
 * 修改
 * @return
 */
function edit() {
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){  
    	$.messager.show({
			title:$("#title").val(),
			msg: $("#noSelect").val(),
			timeout:3000
     });
        return;  
    }
    var frameid=$("#frameid").val();
	top.openModule(frameid,'修改用户','/system/user/input.do',null,'update',update[0].id);
}

/**
 * 删除
 * @return
 */
function del() {
	var del = $('#grid').datagrid('getSelections');  
    if(del.length <= 0 ){  
    	$.messager.show({
			title:$("#title").val(),
			msg: $("#noSelectdel").val(),
			timeout:3000
     });
        return;  
    }
    if(confirm($("#isDelete").val())){
    	deletebyid(del[0].id);
	}
    
}
//删除
function deletebyid(id){
	 $.ajax( {
			url : $("#root").val() + '/system/user/delete.do',
			type : "POST",
			dataType : "json",
			data : "userid="+id,
			success : function(data) {
					if ("success"==data.success) {
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
						//刷新
						$('#grid').datagrid('reload');   
					}else{
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
					}
			}
	   });	
}

//改变每页条数改变高度
function pagechange(value){
	var height = document.body.clientHeight;
	if(value==10){
		initialization();
	}
	if(value==20){
		$('#grid').datagrid({   
			 height:height-140
		});
		 //设置分页控件   
	    var p = $('#grid').datagrid('getPager');   
	    $(p).pagination({   
	        pageSize: value,//每页显示的记录条数，默认为10   
	        pageList: [10,20,30],//可以设置每页记录条数的列表   
	        beforePageText: '第',//页数文本框前显示的汉字   
	        afterPageText: '页    共 {pages} 页',   
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    }); 
	}
	if(value==30){
		$('#grid').datagrid({   
			 height:height-60
		});
		 //设置分页控件   
	    var p = $('#grid').datagrid('getPager');   
	    $(p).pagination({   
	        pageSize: value,//每页显示的记录条数，默认为10   
	        pageList: [10,20,30],//可以设置每页记录条数的列表   
	        beforePageText: '第',//页数文本框前显示的汉字   
	        afterPageText: '页    共 {pages} 页',   
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
	    }); 
		
	}
}