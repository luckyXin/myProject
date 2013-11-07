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
	
	$("#delete").click(function(){
			del();
	});
	$("#destroy").click(function(){
		if(confirm($("#isDelete").val())){
			destroy();
		}
	});
});

function initialization() {
	var height = document.body.clientHeight;
    var width = $(document.body).width();
	$('#mytable').datagrid({   
	      url:$("#coutextPath").val()+'/system/Role/find.do',
	      columns:[[ 
	         {field:'id',title:'角色编号',width:fillsize(0.15),sortable:true},
	         {field:'rolename',title:'角色名称',width:fillsize(0.2) ,sortable:true},
	         {field:'createtime',title:'创建时间',width:fillsize(0.2),sortable:true},
	         {field:'state',title:'状态',width:fillsize(0.15),sortable:true,
			        	  formatter : function(value, rowData, rowIndex) {
				if (value == 0) {
					return "启用";
				} else if (value == 1) {
					return "禁用 ";
				} else if (value == 2) {
					return "删除";
				}
			}
	          },
	         {field:'remark',title:'备注',width:fillsize(0.30),sortable:true}
	      ]],
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      title:"角色管理",
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
	top.openModule($("#id").val(),'角色信息编辑','/system/Role/input.do',null,'add');
}

function update() {
	var row = $('#mytable').datagrid('getSelected');
	if (row != null) {
		top.openModule($("#id").val(),'角色信息编辑','/system/Role/input.do',null,'update',row.id);	
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
		$.post($("#coutextPath").val()+"/system/Role/destroy.do", {id : row.id,state : '2'},
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
		$.post($("#coutextPath").val()+"/system/Role/delete.do", {id : row.id},
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