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
	      url:$("#path").val()+'/system/DaoHangMenu/find.do',   
	      columns:[[ 
	         {field:'id',title:'导航ID',width:fillsize(0.3),sortable:true},
	         {field:'menuName',title:'名称',width:fillsize(0.25) ,sortable:true},
	         {field:'createTime',title:'创建时间',width:fillsize(0.25),sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.1),sortable:true,
	        	  formatter : function(value, rowData, rowIndex) { 
	                     if(value==0){
	                    	 return "启用";
	                     }
	                     if(value==1){
	                    	 return "禁用 ";
	                     }
	                     if(value==2){
	                    	 return "删除";
	                     }
	              }
	          },
	         {field:'remark',title:'备注',width:fillsize(0.20),sortable:true},
	         {field:'sort',title:'顺序',width:fillsize(0.10),sortable:true}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      fitColumns:true,
	      idField:"id",
	      title:"导航管理",
	      iconCls: 'icon-tip',
	      collapsible:true,
	      height:height-50
	  }); 
    //设置分页控件   
    var p = $('#mytable').datagrid('getPager');   
    $(p).pagination({   
        pageSize: 10,//每页显示的记录条数，默认为10   
        pageList: [5,10,15,20],//可以设置每页记录条数的列表   
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
	top.openModule($("#id").val(),'导航信息管理','/system/DaoHangMenu/input.do',null,'add');
}

function update() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		top.openModule($("#id").val(),'导航信息管理','/system/DaoHangMenu/input.do',null,'update',row.id);	
	} else {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noSelect").val()
		});
	}
}

function del() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		$.post($("#path").val()+"/system/DaoHangMenu/delete.do", {id : row.id, state : '2'},
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

function destroy() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		$.post($("#path").val()+"/system/DaoHangMenu/destroy.do", { id : row.id},
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

function reloadGrid() {
	$("#mytable").datagrid("reload");
}