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
		$('#p1').show();
		$('#p2').show();
		var height = document.body.clientHeight;
	    var width = $(document.body).width() -20;
		 //注册检索按钮事件
		 $("#search").click(function(){
			 	 var path=$("#root").val(); 
			 	 var url=path+'/staff/practitioners/construction/find.do?name='+$("#searchname").val();
			 	 $('#grid').datagrid('options').url=encodeURI(encodeURI(url));
			 	 $('#grid').datagrid('reload');
		 });

	}
	 
	
});

function initialization() {
	var height = document.body.clientHeight;                                                           
    var width = $(document.body).width() ;
    var path=$("#root").val();
	$('#grid').datagrid({   
	      url:path+'/staff/practitioners/construction/find.do',   
	      columns:[[   
	         {field:'constructname',title:'企业名称',width:fillsize(0.2),sortable:true},
	         {field:'deptreferred',title:'企业简称',width:fillsize(0.1) ,sortable:true},
	          {field:'regfunds',title:'注册资产',width:fillsize(0.1) ,sortable:true},
	          {field:'regaddress',title:'注册地址',width:fillsize(0.2) ,sortable:true},
	          {field:'legal',title:'法人代表',width:fillsize(0.1),sortable:true},
	          {field:'ischose',title:'是否区比选库企业',width:fillsize(0.2),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) { 
                  if(value==1){
                 	 return "是";
                  }
                  if(value==0){
                 	 return "否";
                  }
              }
	          },
	          {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
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
	          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-180
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


function reloadGrid(){
	$("#grid").datagrid("reload");
}

/**
 * 增加
 * @return
 */
function add() {
	var frameid=$("#frameid").val();
	top.openModule(frameid,'添加施工企业','/staff/practitioners/construction/input.do',null,'add',null);
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
	top.openModule(frameid,'修改施工企业','/staff/practitioners/construction/input.do',null,'update',update[0].id);
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
			url : $("#root").val() + '/staff/practitioners/construction/delete.do',
			type : "POST",
			dataType : "json",
			data : "id="+id,
			success : function(data) {
					if ("success"==data.success) {
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
						//刷新
						reloadGrid();  
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
/**
 * 销毁
 * @return
 */
function destroy() {
	var destroy = $('#grid').datagrid('getSelections');  
    if(destroy.length <= 0 ){  
    	$.messager.show({
    		title:$("#title").val(),
			msg: "请选择要销毁的数据",
			timeout:3000
     });
        return;  
    }
    destroybyid(destroy[0].id);
}
//销毁
function destroybyid(id){
	 $.ajax( {
			url : $("#root").val() + '/staff/practitioners/construction/destroy.do',
			type : "POST",
			dataType : "json",
			data : "id="+id,
			success : function(data) {
					if ("success"==data.success) {
						$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:3000
						 }
					    );
						//刷新
						reloadGrid();  
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