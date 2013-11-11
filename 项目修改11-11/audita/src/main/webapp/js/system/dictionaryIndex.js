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
		var height = document.body.clientHeight;
		var width = $(document.body).width() ;
		$(".treeDiv").css("height",height-10);
		//初始化左边树结构
	    initdictionaryTree();
		//初始化列表
		initialization();
		$('#p1').panel({
			height : height-10,
			width:width-240,
			title : '字典管理',
			iconCls : 'icon-tip'
		});
	}
	 
	
});

function initialization() {
	var height = document.body.clientHeight;
	var width = $(document.body).width() ;
    var path=$("#root").val();
	$('#grid').datagrid({   
	      url:path+'/system/dictionary/find.do',   
	      columns:[[   
	         {field:'id',title:'编号',width:fillsize(0.1),sortable:true},
	         {field:'dictionaryname',title:'名称',width:fillsize(0.3) ,sortable:true},
	          {field:'remark',title:'备注',width:fillsize(0.6),sortable:true}
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      fitColumns:true,
	      idField:"id",
	      collapsible:true,
	      height:height-250,
	      width:width-250

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
	return (bodyWidth-240)*percent;
}


/**
 * 初始化左边树
 * dengyong
 */
function initdictionaryTree() {
	    var path=$("#root").val();
		tree = null;
		$("#treeDiv").empty();
		$.ajax( {
					url : path+'/system/dictionary/finddictree.do', 
					type : "POST",
					dataType : "json",
					data : "",
					success : function(result) {
							tree = new dFTree( {
								name : 'tree',
								useIcons : true,
								checkBox : false,
								icondir : path+'/images/icon/TreeView',
								parentName : "treeDiv"});
								for ( var x = 0; x < result.length; x++) {
										tree.add(new dNode( {
											id : tree.allNodeNum,
											treetype : result[x].Ico,
											isFolder : 0,
											caption : result[x].Caption,
											onClick : result[x].OnClick,
											title : result[x].Alt,
											postvalue : result[x].PostValue,
											postpage : result[x].PostPage
										}), (result[x].ParentID));
								}
							   tree.draw(true);
					}
				});
}

//树机构点击事件
function relaodtable(id){
	 var path=$("#root").val(); 
	 var url=path+'/system/dictionary/find.do?dicid='+id;
	 $('#grid').datagrid('options').url=url;
	 $('#grid').datagrid('reload');
}

//加载
function reloadGrid(){
	initdictionaryTree();
	$("#grid").datagrid("reload");
}

/**
 * 增加
 * @return
 */
function add() {
	var frameid=$("#frameid").val();
	top.openModule(frameid,'添加字典','/system/dictionary/input.do',null,'add',null);
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
			msg:$("#noSelect").val(),
			timeout:3000
     });
        return;  
    }
    
    if(update[0].issystem==1)
    {
    	$.messager.show({
    		title:$("#title").val(),
			msg:"系统字典不能修改",
			timeout:3000
        });
    	return;
    }else
    {
    var frameid=$("#frameid").val();
	top.openModule(frameid,'修改字典','/system/dictionary/input.do',null,'update',update[0].id);
    }
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
			url : $("#root").val() + '/system/dictionary/delete.do',
			type : "POST",
			dataType : "json",
			data : "dicid="+id,
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
			 height:height-80
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