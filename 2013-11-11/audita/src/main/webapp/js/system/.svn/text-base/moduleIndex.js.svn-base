$(document).ready(function() {
	var isFind = $("#find").val();
	var isinput = $("#input").val();
	if (isinput != '1') {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noPower").val()
		});
	}
	if (isFind) {
		//初始化列表
		initialization();
	}
	
	$("#destroy").click(function(){
		if(confirm($("#isDelete").val())){
			destroy();
		}
	});
	
	$("#delete").click(function(){
		del();
	});
});

function initialization() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
	$('#mytable').datagrid({   
	      url:$("#coutextPath").val()+'/system/Module/find.do',
	      columns:[[ 
	         {field:'moduleId',title:'模块编号',width:fillsize(0.1),sortable:true},
	         {field:'moduleName',title:'名称',width:fillsize(0.15) ,sortable:true},
	         {field:'menuId',title:'菜单编号',width:fillsize(0.1),sortable:true},
	         {field:'menuName',title:'菜单名称',width:fillsize(0.1),sortable:true},
	         {field:'url',title:'地址',width:fillsize(0.25),sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) {
	                     if(value==0){
	                    	 return "启用";
	                     } else 
	                     if(value==1){
	                    	 return "禁用 ";
	                     } else 
	                     if(value==2){
	                    	 return "删除 ";
	                     }
	              }
	          },
	         {field:'remark',title:'备注',width:fillsize(0.20),sortable:true}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      title:"模块管理",
	      iconCls: 'icon-tip',
	      collapsible:true,
	      height:height-50
	  }); 
    //设置分页控件   
    var p = $('#mytable').datagrid('getPager');   
    $(p).pagination({   
        pageSize: 10,//每页显示的记录条数，默认为10   
        pageList: [5,10,15,20,40],//可以设置每页记录条数的列表   
        beforePageText: '第',//页数文本框前显示的汉字   
        afterPageText: '页    共 {pages} 页',   
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'  
    });
}

function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-60)*percent;
}

function add() {
	top.openModule($("#id").val(),'模块信息管理','/system/Module/input.do',null,'add');
}

function update() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		top.openModule($("#id").val(),'模块信息管理','/system/Module/input.do',null,'update',row.moduleId);	
	} else {
		$.messager.show({
			title : "提示",
			msg : "没有选中更新的内容"
		});
	}
}

function del() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		$.post($("#coutextPath").val()+"/system/Module/delete.do", { id : row.moduleId,  state : '2'},
				function (data, textStatus){
			$.messager.show({
				title : "提示",
				msg : data.msg
			});
			reloadGrid();
		});
	} else {
		$.messager.show({
			title : "提示",
			msg : "没有选中更新的内容"
		});
	}
}

function destroy() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		$.post($("#coutextPath").val()+"/system/Module/destroy.do", { id : row.moduleId},
				function (data, textStatus){
			$.messager.show({
				title : "提示",
				msg : data.msg
			});
			reloadGrid();
		});
	} else {
		$.messager.show({
			title : "提示",
			msg : "没有选中更新的内容"
		});
	}
}
function reloadGrid() {
	$("#mytable").datagrid("reload");
}