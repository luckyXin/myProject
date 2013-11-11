var nowrow=8;
var endrow=9;
var maxrow=100;
$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#remark").val(top.textTrim($("#remark").val()));
		//保存按钮
		$("#save").click(function() {
			var tb = document.getElementById("mytableid");  
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/staff/practitioners/construction/add.do?beginrow='+nowrow+'&totalrow='+tb.rows.length,
					success : function(data) {
						if ("success"==data.success) {
							top.showMsg(data.msg);
							top.reloadModule(frameid);
							top.closeModule(moduleName, parentModuleName);
						} else {
							top.showMsg(data.msg);
						}
					}
				});
			} else {
				$.messager.show( {
					title : "提示",
					msg : "请正确填写"
				});
				return false;
			}
		});

	//关闭按钮
	$("#back").click(function() {
		top.closeModule(moduleName, parentModuleName);
	});
	//数字文本框
	 $('#regfunds').numberbox({   
	     precision:2   
	  });

	$.extend($.fn.validatebox.defaults.rules, {
		mobile : {
			validator : function(value) {
				return /^1[0-9]{10}$/i.test($.trim(value));
			},
			message : '手机号码格式错误.'
		}
	});
	//添加行
	 $('#addrow').click(function(){
		 add();
	 });
	 //删除行
	 $('#delrow').click(function(){
		 move();
	 });
		
});
//增加行
function add(){
	   var tb = document.getElementById("mytableid");  
	   var rnum = tb.rows.length+1;
	   var number=rnum-(nowrow-1);
	   if(number<maxrow)
	   {	   
		   var numname=converyNumToDaXie(number);
		   var row = tb.insertRow(); 
		   
		   var cell = row.insertCell();  
		   cell.style.backgroundColor = "#e0ecff"; 
		   var html1="<p align='right'>第"+numname+"联系人：</p>";
		   cell.innerHTML = html1; 
		   
		   cell = row.insertCell();  
		   var html2="<input type='text' style='width: 150px;' id='linkname"+rnum+"' name='linkname"+rnum+"'>";
		   cell.innerHTML = html2; 
		   
		   cell = row.insertCell();  
		   cell.style.backgroundColor = "#e0ecff"; 
		   var html3="<p align='right'>第"+numname+"联系人电话 ：</p>";
		   cell.innerHTML = html3; 
		   
		  
		   cell = row.insertCell();  
		   var html4="<input type='text'  style='width: 140px;' id='linktellphone"+rnum+"' name='linktellphone"+rnum+"'>";
		   cell.innerHTML = html4; 
	   }
}
//减少行
function move(){
	  var tb = document.getElementById("mytableid");  
	  var rnum = tb.rows.length; 
	  if(rnum>=endrow)
	  {	   
		  tb.deleteRow(rnum-1);  
	  }
	   
}