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
		 $('#p1').panel({   
			   width:width,   
			   height:120,   
			   title: '查询条件',
			   iconCls: 'icon-search'
	    }); 
		 $('#p2').panel({   
			   width:width,   
			   height:height-150,   
			   title: '消息管理'	,
			   iconCls: 'icon-tip'
	    });
		 
		//从工作页面进入
	    flowworktomessage();
		 //注册检索按钮事件
		 $("#search").click(function(){
			 	 var path=$("#root").val(); 
			 	 var url=path+'/system/message/find.do?method='+$("#method").val()+'&messagestate='+$("#messagestate").val();
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
	      url:path+'/system/message/find.do',   
	      columns:[[   
	         {field:'senduser',title:'发件人',width:fillsize(0.1),sortable:true},
	         {field:'messagetitle',title:'标题',width:fillsize(0.2) ,sortable:true},
	          {field:'messagecontent',title:'内容',width:fillsize(0.4) ,sortable:true},
	          {field:'acceptuser',title:'收件人',width:fillsize(0.1) ,sortable:true},
	          {field:'messagetime',title:'时间',width:fillsize(0.1),sortable:true},
	          {field:'messagestate',title:'是否阅读',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) { 
                  if(value==1){
                 	 return "已阅读";
                  }
                  if(value==0){
                 	 return "未阅读";
                  }
              }
	          }
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-220
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
	top.openModule(frameid,'发送消息','/system/message/input.do',null,'add',null);
}


//查看
function look(){
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){  
    	$.messager.show({
    		title:$("#title").val(),
			msg: "请选择一行查看消息",
			timeout:3000
     });
        return;  
    }
    var frameid=$("#frameid").val();
	top.openModule(frameid,'查看消息','/system/message/input.do',null,'look',update[0].id);
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
			url : $("#root").val() + '/system/message/delete.do',
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

function flowworktomessage(){
	    var path=$("#root").val();
	    var url=path+'/system/message/find.do?method='+$("#methodtype").val()+'&messagestate='+$("#key").val();
	 	$('#grid').datagrid('options').url=encodeURI(encodeURI(url));
	    $('#grid').datagrid('reload');
}
