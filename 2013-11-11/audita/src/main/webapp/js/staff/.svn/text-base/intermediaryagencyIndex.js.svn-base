$(document).ready(function() {
	var isfind=$("#find").val();
	var isinput = $("#input").val();
	if (isinput != '1') {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noPower").val()
		});
	}
	if(null!=isfind && "null"!=isfind && ""!=isfind && undefined!=isfind)
	{
		//初始化列表
		initialization();
		 //注册检索按钮事件
		 $("#search").click(function(){
			 	var url=$("#path").val()+'/staff/intermediaryagency/find.do';
		 		$('#grid').datagrid('options').queryParams={intermediaryName : $("#searchname").val()
		 			,bidyear:$("#bidyear").val()};
				$('#grid').datagrid('reload');
		 });
	} else {
		$('#p1').hide();
		$('#p2').hide();
		$("#center").hide();
	}
});

function initialization() {
	var height = document.body.clientHeight-10;                                                             
    var width = $(document.body).width() ;
	$('#grid').datagrid({
	      url:$("#path").val()+'/staff/intermediaryagency/find.do',   
	      columns:[[
             {field:'id',title:'中介机构编号',width:fillsize(0.1),sortable:true},
	         {field:'intermediaryname',title:'中介机构名称 ',width:fillsize(0.1),sortable:true},
	         {field:'referred',title:'中介机构简称 ',width:fillsize(0.1) ,sortable:true},
	          {field:'businesstype',title:'业务类型 ',width:fillsize(0.1) ,sortable:true},
	          {field:'manageragency',title:'管理机构 ',width:fillsize(0.1) ,sortable:true},
	          {field:'deptqualifica',title:'资质 ',width:fillsize(0.1) ,sortable:true},
	          {field:'regaddress',title:'注册地址 ',width:fillsize(0.2) ,sortable:true},
	          {field:'legal',title:'法人代表  ',width:fillsize(0.1) ,sortable:true}
	          
	          
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      height:height-160
	      /*
	      rowStyler:function(index,row){           
	    	  if (index!=0){       
	    		  return 'background-color:red;';           
	    		  }       
	    	  } 
	    	  */
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
 */
function add() {
	var frameid=$("#frameId").val();
	top.openModule(frameid,'中介机构信息增加','/staff/intermediaryagency/input.do',null,'add',null);
}

/**
 * 更新
 */
function edit(){
	var row = $('#grid').datagrid('getSelected');
	if (row != null) {
		top.openModule($("#frameId").val(),'中介机构信息编辑','/staff/intermediaryagency/input.do',null,'update',row.id);	
	} else {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

/**
 * 删除
 */
function del(){	
	//ToDo
}

function destroy(){
	var row = $('#grid').datagrid('getSelected');
	if (row != null) {
		
		$.post($("#path").val()+"/staff/intermediaryagency/destroy.do", {id : row.id},
				function (data, textStatus){
			$.messager.show({
				title : $("#title").val(),
				msg : data.msg
			});
			
			reloadGrid();
		});
	} else {
		
		$.messager.show({
			
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}