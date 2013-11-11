
$(document).ready(function() {
	$("#reset").click(function() {
		$("#projectName").val("");
	    $("#proownerid").val("");
	    $("#issubmit").val("");
	    $("#audittype").val("");
	    $('#beginTime').datebox('setValue','');
	    $('#endTime').datebox('setValue','');
	});
	var isfind=$("#find").val();
	var isinput = $("#input").val();
	if (isinput != '1') {
		$.messager.show({
			title : $("#title").val(),
			msg : $("#noPower").val()
		});
		return false;
	}
	 //资料预审时间		
	$('#beginTime').datebox( {
		disabled : false,
		panelWidt:300,
		panelHeight:220,
		formatter : formatDate
     });
	$('#endTime').datebox( {
		disabled : false,
		panelWidt:300,
		panelHeight:220,
		formatter : formatDate
     });
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
			   height:150,   
			   title: '查询条件',
			   iconCls: 'icon-search'
	    }); 
		 $('#p2').panel({   
			   width:width,   
			   height:height-100,   
			   title: '资料预审'	,
			   iconCls: 'icon-tip'
	    });
		 
		 //注册检索按钮事件
		 $("#search").click(function(){
			    var projectName=$("#projectName").val();
			    var proownerid=$("#proownerid").val();
			    var issubmit=$("#issubmit").val();
			    var audittype=$("#audittype").val();
			    var beginTime=$('#beginTime').datebox('getValue');
			    var endTime=$('#endTime').datebox('getValue');
				var path=$("#root").val(); 
			 	var url=path+'/project/DataPre/find.do?projectName='+projectName+'&proownerid='+proownerid+'&issubmit='+issubmit+'&audittype='+audittype+'&beginTime='+beginTime+'&endTime='+endTime;
			 	$('#grid').datagrid('options').pageNumber=1;
			 	$('#grid').datagrid('options').url=encodeURI(encodeURI(url));
			 	$('#grid').datagrid('reload');
		 });
		 
		 $("#excelallpro").click(function(){
			    var projectName=$("#projectName").val();
			    var proownerid=$("#proownerid").val();
			    var url=$("#path").val()+ '/common/project/importfinishprojectexcel.do?projectOwner='+proownerid+'&projectName='+projectName;
				window.location=encodeURI(encodeURI(url));
				return false;
		 });
		 
		 
		 
		 
		 $("#ownerName").click(function(){
			 openshowdiv(1, "业主查询", 580, 350,270,null);
		 });
		 
		 
		 //增加事务所
		 $("#addinter").click(function(){
			 var update = $('#grid').datagrid('getSelections');  
			    if(update.length <= 0 ){  
			    	$.messager.show({
			    		title:$("#title").val(),
						msg: '请选择一行项目',
						timeout:3000
			        });
			        return false;  
			   }else{
				   //判断该项目是否被安排
				  var isArrangement=  update[0].isArrangement;
				  if(1==isArrangement){
					  top.openModule($("#frameId").val(),'录入中介机构','/project/DataPre/input.do',null,'addinterdept',update[0].arrangeid);
				  }else{
					  $.messager.show({
				    		title:$("#title").val(),
							msg: '该项目还没有安排,安排后在添加事务所',
							timeout:3000
				        });
				        return false;  
				  }
				   
				   
			   }
		 });

	}
	 
	
});



function initialization() {
	var height = document.body.clientHeight;                                                            
    var width = $(document.body).width() ;
    var path=$("#path").val();
    $('#grid').datagrid({
		url : path+'/project/DataPre/find.do',
		frozenColumns : [ [ 
				{field:'datapreno',title:'送审编号',width:fillsize(0.1) ,sortable:true},
				{field:'projectName',title:'项目名称',width:fillsize(0.3) ,sortable:true}
		      ] ],
		 columns:[[   
			          {field:'projectNo',title:'立项文号',width:fillsize(0.15),sortable:true},
			          {field:'projectTime',title:'立项时间',width:fillsize(0.1),sortable:true,
			        	  formatter : function(value, rowData, rowIndex) { 
		                  if(null!=value && ""!=value){
		                 	 return todate(value);
		                  }
		                  if(value==0){
		                 	 return "";
		                  } 
		                }
			          
			          },
			          {field:'auditMoney',title:'批复金额',width:fillsize(0.1),sortable:true},
			          {field:'zjMoney',title:'直接费用(万元)',width:fillsize(0.1),sortable:true},
			          {field:'zbMoney',title:'中标价',width:fillsize(0.1),sortable:true},
			          {field:'htmoney',title:'合同价',width:fillsize(0.1),sortable:true},
			          {field:'zhanliemoney',title:'其中暂列金额',width:fillsize(0.1),sortable:true},
			          {field:'audittype',title:'审计类型',width:fillsize(0.1),sortable:true},
			          {field:'budgetInfo',title:'概算文号',width:fillsize(0.1),sortable:true},
			          {field:'budgetTotalMoney',title:'概算总金额',width:fillsize(0.1),sortable:true},
			          {field:'budgetDirectMoney',title:'概算直接费用(万元)',width:fillsize(0.1),sortable:true},
			          {field:'budgetUpdateTime',title:'概算批改时间',width:fillsize(0.1),sortable:true,
			        	  formatter : function(value, rowData, rowIndex) { 
		                  if(null!=value && ""!=value){
		                	  return value.substr(0, 10); 
		                	
		                  }
		                  if(value==0){
		                 	 return "";
		                  } 
		                }
			          },
			          {field:'ownerName',title:'项目业主',width:fillsize(0.1) ,sortable:true},
			          {field:'constructId',title:'施工单位',width:fillsize(0.25),sortable:true},
			          {field:'mainAuditId',title:'主审',width:fillsize(0.1),sortable:true},
			          {field:'intermediaryId',title:'事务所',width:fillsize(0.15),sortable:true},
			          {field:'employees',title:'复核工程师',width:fillsize(0.1),sortable:false},
			          {field:'sentAmount',title:'送审金额',width:fillsize(0.1),sortable:true},
			          {field:'interauditmoney',title:'审定金额',width:fillsize(0.1),sortable:false},
			          {field:'intercutmoney',title:'中介审减金额',width:fillsize(0.1),sortable:true},
			          {field:'intercutmoneylv',title:'中介审减率',width:fillsize(0.1),sortable:true},
			          {field:'reduceMoney',title:'雇员审减金额',width:fillsize(0.1),sortable:true},
			          {field:'reduceMoneylv',title:'雇员审减率',width:fillsize(0.1),sortable:true},
			          {field:'datapretime',title:'接收资料时间',width:fillsize(0.1),sortable:true,
			        	  formatter : function(value, rowData, rowIndex) { 
		                  if(null!=value && ""!=value){
		                 	 return todate(value);
		                  }
		                  if(value==0){
		                 	 return "";
		                  } 
		                }
			          }
			      ]]  ,
			      singleSelect:true,
			      pagination:true,
			      striped:true,
			      height:350
	});
     // 设置分页控件
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}
function initPage(pageSize) {
	var p = $('#grid').datagrid('getPager');
	$(p).pagination({
		pageSize : pageSize,// 每页显示的记录条数，默认为10
		pageList : [ 10, 20, 30, 40, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		onChangePageSize : function(pageSize) {
			var newHeightNum = pageSize / 10;
			$('#grid').datagrid({
				height : (310 * newHeightNum)
			});
			initPage(pageSize);
		}
	});
}


function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-40)*percent;
}


function reloadGrid(){
	$("#grid").datagrid("reload");
}


//回调方法
function returnback(rowData){
	$("#ownerName").val(rowData.ownerName);
	$("#proownerid").val(rowData.id);
	close();
}
/**
 * 增加
 * @return
 */
function add() {
	top.openModule($("#frameId").val(),'添加资料预审','/project/DataPre/input.do',null,'add',null);
}
function addchild(){
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){
    		$.messager.show({
    		title:$("#title").val(),
			msg: '请选择主项目资料',
			timeout:3000});
            return;  
    }else{
    	if(update[0].pid!="0")
    	{
    		$.messager.show({
        		title:$("#title").val(),
    			msg: '请选择主项目资料',
    			timeout:3000
    	    });
    		return false;
    	}
    }
	top.openModule($("#frameId").val(),'添加资料预审子项目','/project/DataPre/input.do',null,'addchild',update[0].id);
}
/**
 * 修改
 * @return
 */
function update() {
	var update = $('#grid').datagrid('getSelections');  
    if(update.length <= 0 ){  
    	$.messager.show({
    		title:$("#title").val(),
			msg: $("#noSelect").val(),
			timeout:3000
     });
        return;  
    }
	top.openModule($("#frameId").val(),'修改资料预审','/project/DataPre/input.do',null,'update',update[0].id);
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
  if(del[0].isconfirmSubmit==1){
    	$.messager.show({
    		title:'提示',
			msg: '提交后不能删除',
			timeout:3000
       });
    	return false;
    }
    if(confirm($("#isDelete").val())){
    	deletebyid(del[0].id);
	}
   
}
//删除
function deletebyid(id){
	 $.ajax( {
			url : $("#path").val() + '/project/DataPre/delete.do',
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
//格式化时间
function formatDate(v) {
	if (v instanceof Date) {
		var y = v.getFullYear();
		var m = v.getMonth() + 1;
		var d = v.getDate();
		var h = v.getHours();
		var i = v.getMinutes();
		var s = v.getSeconds();
		var ms = v.getMilliseconds();

		if (ms > 0) {
			return y + '-' + m + '-' + d + ' ' + h + ":" + s + ":" + i;
		}
		if (h > 0 || m > 0 || d > 0) {
			if(m<10){
				m="0"+m;
			}
			if(d<10){
				d="0"+d;
			}
			return y + '-' + m + '-' + d;
		}
	}
	return '';
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
