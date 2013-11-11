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
			height : height-20,
			title : '公告管理',
			iconCls : 'icon-tip'
		});
	}
	 
	
});

function initialization() {
	var height = document.body.clientHeight;
    var width = $(document.body).width() ;
    var path=$("#root").val();
	$('#grid').datagrid({   
	      url:path+'/system/notice/find.do',   
	      columns:[[   
	         {field:'title',title:'标题',width:fillsize(0.2),sortable:true},
	         {field:'content',title:'公告内容',width:fillsize(0.7) ,sortable:true},
	         {field:'createtime',title:'发布时间',width:fillsize(0.1) ,sortable:true,
	               formatter : function(value, rowData, rowIndex) {
	        	         var time= todate(value);
	        	         return time;
	                    }        
	          }
	        
	      ]]  ,
	      singleSelect:true,
	      pagination:true,
	      striped:true,
	      fitColumns:true,
	      idField:"id",
	      collapsible:true,
	      height:height-85,
	      width:width-40
	      

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
	top.openModule(frameid,'添加公告','/system/notice/input.do',null,'add',null);
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
	top.openModule(frameid,'编辑公告','/system/notice/input.do',null,'update',update[0].id);
}
//查看
function look() {
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){  
    	$.messager.show({
			title:$("#title").val(),
			msg: "请选择要查看的公告",
			timeout:3000
     });
        return;  
    }
    var frameid=$("#frameid").val();
	top.openModule(frameid,'公告查看','/system/notice/input.do',null,'worklook',update[0].id);
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
			url : $("#root").val() + '/system/notice/delete.do',
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
function todate(time) {
	if (null != time && "" != time) {
		var date = new Date(time);
		var year = date.getFullYear();
		var month = (date.getMonth() + 1);
		var day = date.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		return year + "-" + month + "-" + day;
	} else {
		return "";
	}
}