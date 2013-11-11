var num=0;
$(document).ready(function() {
			$("#save").click(function(){
				if ($('#form').form('validate')) {
					
					   var tb = document.getElementById("htaudittable");  
					   var rnum = tb.rows.length;
					   var rownumber=(rnum-13)/2;
					var url = $("#coutextPath").val() + '/project/contract/add.do?rownumber='+rownumber
					if ($("#saveorupdate").val() == "1"){
						url = $("#coutextPath").val() + '/project/contract/update.do?rownumber='+rownumber
					}
					$("#form").ajaxSubmit({
						url : url,
						success : function(data) {
						var data = eval("(" + data + ")");
						    
							if (data.success == "success") {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
								top.closeModule(moduleName, parentModuleName);
							} else  {
								top.showMsg(data.msg);
								top.reloadModule($("#frameid").val());
							}
						}
					});
				}
			});
			/*$("#addfile").click(function(){
				   num=num+1;
				   var tr=$("<tr><td class='label' align='right'>分包合同"+num+"：</td>"+
							"<td  align='left'><input type='hidden' name='fenbao"+num+"' value='分包合同"+num+"'/><input  type='file' style='width: 300px;' class='text'  name='fenbaofile"+num+"'></td>" +
							"" +
							"<td class='label' align='right'>存在问题"+num+"：</td><td  align='left'><textarea rows='3' cols='10' id='existproblem"+num+"' name='existproblem"+num+"' style='width: 300px;'></textarea></td></tr>" +
							"" +
							"<tr><td class='label' align='right'>审核意见：</td><td  align='left'><textarea rows='3' cols='10' id='auditview"+num+"' name='auditview"+num+"' style='width: 300px;'></textarea></td>" +
						"<td class='label' align='right'>执行情况：</td><td  align='left'><textarea rows='3' cols='10' id='executecase"+num+"' name='executecase"+num+"' style='width: 300px;'></textarea></td></tr>");
				      
						$(".files").append(tr);
						tr.find(".deletefile").click(function(){
							num=num-1;
							$(this).parent().parent().remove();
							$(this).parent().parent().remove();
						});
						$(".easyui-linkbutton").linkbutton({
							plain:false
						});
			});*/
			
			//添加行
			 $('#addrow').click(function(){
				 add();
			 });
			 //删除行
			 $('#delrow').click(function(){
				 move();
			 })
});

/**
 * 主审人员回调函数
 * @param data
 */
function returnMainAuditEmployee(data) {
	$("#mainAuditId").val(data.id);
	$("#mainAuditName").val(data.userName);
	$('#form').form('validate');
	close();
}

function returnbackguyuanProject(data){
	for(var j=0;j<data.length;j++){
		var hiddenGovernmentEmployee = $('<input type="hidden" class="governmentEmployee" id="governmentEmployeeId" name="governmentEmployeeId">');
		var governmentEmployee = $("#governmentEmployee").val();
		var count = 0;
		for ( var i = 0; i < governmentEmployee.length; i++) {
			if ("," == governmentEmployee.charAt(i)) {
				count++;
			}
		}
		for ( var k = 0; k < $(".governmentEmployee").length; k++) {
			var stepvar = $(".governmentEmployee:eq(" + k + ")").val();
			if (data[j].id == stepvar) {
				top.$.messager.show({
					title : $("#title").val(),
					msg : "已经存在"
				});
				return;
			}
		}
		if ("" == governmentEmployee) {
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployeeId").val(data[j].id);
			$("#governmentEmployee").val(data[j].userName);
		} else if (count == 4) {
			$(".governmentEmployee:eq(0)").val(data[j].id);
			var strs = new Array();
			strs = governmentEmployee.split(",");
			strs[4] = data[j].userName;
			var governmentEmployees = "";
			for ( var j = 0; j < strs.length; j++) {
				if (j == 0) {
					governmentEmployees += strs[j];
				} else {
					governmentEmployees += ',' + strs[j];
				}
			}
			$("#governmentEmployee").val(governmentEmployees);
		} else {
			hiddenGovernmentEmployee.attr("value", data[j].id);
			$("#governmentEmployee").after(hiddenGovernmentEmployee);
			$("#governmentEmployee").val(governmentEmployee + "," + data[j].userName);
		}
		$('#form').form('validate');
    }
	close();
}


//下载
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
	
}
function lookFile(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/lookWord.do?url='+url));
	return false;
}


//删除
function delFile(id){
		$.ajax( {
			url :  $("#coutextPath").val() + '/common/project/deleteFile.do',
			type : "POST",
			dataType : "json",
			data : "key=" + id,
			success : function(result) {
			  if(result.success=="success")
			  {
				  $.messager.show({
			    		title:'提示',
						msg: result.msg,
						timeout:3000
			       });
				  $("#grid").datagrid("reload");
			  }else{
				  $.messager.show({
			    		title:'提示',
						msg: result.msg,
						timeout:3000
			       });
				  return false;
			  }
			   
			}
		});
}

//增加行
function add(){
	   var tb = document.getElementById("htaudittable");  
	   var rnum = tb.rows.length+1;
		   var numname="";
		   var row = tb.insertRow(); 
		   num=(rnum-12)/2;
		   var cell = row.insertCell();  
		   cell.style.backgroundColor = "#e0ecff"; 
		   var html1="<p align='right'>分包合同"+(num)+"</p>";
		   cell.innerHTML = html1; 
		   
		   cell = row.insertCell();  
		   var html2="<input type='hidden' name='fenbao"+num+"' value='分包合同"+num+"'/><input  type='file' style='width: 300px;' class='text'  name='fenbaofile"+num+"'>";
		   cell.innerHTML = html2; 
		   
		   cell = row.insertCell();  
		   cell.style.backgroundColor = "#e0ecff"; 
		   var html3="<p align='right'>存在问题：</p>";
		   cell.innerHTML = html3; 
		   
		  
		   cell = row.insertCell();  
		   var html4="<textarea rows='3' cols='10' id='existproblem"+num+"' name='existproblem"+num+"' style='width: 300px;'></textarea>";
		   cell.innerHTML = html4; 
		   
           row = tb.insertRow(); 
		   
		   var cell = row.insertCell();  
		   cell.style.backgroundColor = "#e0ecff"; 
		   var html1="<p align='right'>审核意见：</p>";
		   cell.innerHTML = html1; 
		   
		   cell = row.insertCell();  
		   var html2="<textarea rows='3' cols='10' id='auditview"+num+"' name='auditview"+num+"' style='width: 300px;'></textarea>";
		   cell.innerHTML = html2; 
		   
		   cell = row.insertCell();  
		   cell.style.backgroundColor = "#e0ecff"; 
		   var html3="<p align='right'>执行情况：</p>";
		   cell.innerHTML = html3; 
		   
		  
		   cell = row.insertCell();  
		   var html4="<textarea rows='3' cols='10' id='executecase"+num+"' name='executecase"+num+"' style='width: 300px;'></textarea>";
		   cell.innerHTML = html4; 
	   
}
//减少行
function move(){
	  var tb = document.getElementById("htaudittable");  
	  var rnum = tb.rows.length; 
	  if(rnum>=14)
	  {	   
		  for(var i=1;i<=2;i++){
			  tb.deleteRow(rnum-i);  
		  } 
	  }
	  
}

//下载文件
function download(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/download.do?url='+url));
	return false;
}
//查看文件
function lookfile(url){
	window.location=encodeURI(encodeURI($("#coutextPath").val() + '/common/project/lookWord.do?url='+url));
	return false;
}


