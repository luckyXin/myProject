$(document).ready(function() {
	var frameid=$("#frameid").val();
	$("#remark").val(top.textTrim($("#remark").val()));
	//初始化生日控件
		$('#birthday').datebox( {
			disabled : false,
			panelWidt:300,
			panelHeight:220,
			formatter : formatDate
		});
		$.extend($.fn.validatebox.defaults.rules, {
			ishave : {
			validator : function(value) {
			 var falg=true;
			  $.ajax( {
				url :  $("#coutextPath").val() + '/system/user/findbyid.do',
				type : "POST",
				dataType : "json",
				async:false,
				data : 'usercount='+value,
				success : function(data) {
					if ("yes"==data.ishave) {
						falg=false;
					}
				}
			   });
			return falg;
			},
			message : '用户账号存在'
			}
		});
		
		$.extend($.fn.validatebox.defaults.rules, {
			mobile : {
				validator : function(value) {
					return /^1[0-9]{10}$/i.test($.trim(value));
				},
				message : '手机号码格式错误.'
			},
			idCardNo : {
				validator : function(value) {
				var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			    return reg.test(value)
				},
				message : "请正确输入您的身份证号码."
			}

		});
		//初始化机构信息
		initdepttree();
		
		//保存按钮
		$("#save").click(function() {
			if ($('#form').form('validate')) {
				$("#form").ajaxSubmit( {
					url : $("#coutextPath").val() + '/system/user/add.do',
					success : function(data) {
						if (data.success) {
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
	});

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


//初始化机构下拉树形机构
function initdepttree(){
	$.ajax( {
		url : $("#coutextPath").val() + '/system/department/findbypid.do',
		type : "POST",
		success : function(json) {
			var result = eval("(" + json + ")");
			$$.InputTree.New( {
				"m_strNodeValue" : "deprtId",
				"m_strNodeText" : "name",
				"m_jsonData" : result,
				"m_strContainerID" : "deptid"
			});
		}
	});
}
