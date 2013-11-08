<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/center.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/head/head.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/icon.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/JQuery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/dftree.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/system/center.js"></script>
		<title>管理中心</title>
		<style type="text/css">
.menu_daohang {
	text-align: center;
	margin-top: 1px;
	float: left;
	cursor: pointer;
	margin-right: 3px;
	height: 26px;
	line-height: 26px;
	min-width: 100px;
	border: 0px;
	padding-left: 5px;
	padding-right: 5px;
	background-image: url("/audita/css/head/images/q2.jpg");
}

.menu_daohangWork {
	text-align: center;
	margin-top: 1px;
	float: left;
	cursor: pointer;
	margin-right: 3px;
	height: 26px;
	line-height: 26px;
	min-width: 100px;
	border: 0px;
	padding-left: 5px;
	padding-right: 5px;
	background-image: url("/audita/css/head/images/q2.jpg");
}

.menu_daohangCLikc {
	background-image: url("/audita/css/head/images/q1.jpg");
}

.menu_daohang:HOVER {
	background-image: url("/audita/css/head/images/q1.jpg");
}

.root {
	margin-left: -40px;
}
</style>
		<script type="text/javascript">
	$(function() {
		if(top.location.href!=self.location.href){
			top.location.href = self.location.href;
		}
		function changeClock()
		{
			var tempTime  = new Date();
			$("#time_1").html(tempTime.getFullYear()+"-"+(tempTime.getMonth()+1) 
			+"-"+tempTime.getDate()+ " " + tempTime.getHours() + ":" + tempTime.getMinutes()+ ":" + tempTime.getSeconds()+"" );
		}
		setInterval(changeClock, 1000);
		$(".menu_daohang").click(function() {
			$(".menu_daohang").removeClass("menu_daohangCLikc");
			$(this).addClass("menu_daohangCLikc");
		});
		function loadTree(code) {
			$("#treeul").html("<div style=\"text-align: center;width: 100%;\">请等待，正在加载......<div>");
			tree = null;
			$("#treeul").empty();
			$.ajax( {
						url : "<%=request.getContextPath()%>/getusermenu.do",
						type : "POST",
						dataType : "json",
						data : "menuid="+code,
						success : function(result) {
						if (result.length != 0) {
						    tree = new dFTree( {
									name : 'tree',
									useIcons : true,
									checkBox : false,
									icondir : '<%=request.getContextPath()%>/images/icon/TreeView',
									parentName : "treeul"});
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
									tree.draw(true,true);
						} else {
						  $("#treeul").html('<span>会话过期<br>请<a href="#" onclick="backLogin();">重新登陆</a></span><br>');
						}
						    },
						error: function (xmlObject,msg, obj){
						   $("#treeul").html('<span>系统错误，请<a href="#" onclick="backLogin();">重新登陆</a></span><br>');
						}
				    
			});
		}
		$.each($(".menu_daohang"), function(index, item) {
			if (index == 0) {
				loadTree($(this).attr("code"));
			}
		});
		$(".menu_daohang").click(function() {
			loadTree($(this).attr("code"));
		});
		
		$("#myworkButton").linkbutton({
		    iconCls:'icon-redo'
		});
		
		$("#myworkButton").click(function(){
		  openModule("mywork", "我的工作", "/work/MyWork/input.do");
		});
	});
	document.onkeydown = banBackSpace;
</script>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	</head>
	<body class="easyui-layout" style="background-color: #e0edfe">
		<input type="hidden" id="path" value="<%=request.getContextPath()%>">
		<div region="north" border="false" class="background"
			style="height: 120px; overflow: hidden; background-color: #e0edfe;">
			<div id="head_bg">
				<div class="logo"
					style="width: 100%; height: 99px; background-image: url('/audita/css/head/images/logo_bg.jpg'); background-repeat: no-repeat; background-position: right bottom;">
					<img
						src="<%=request.getContextPath()%>/css/head/images/logo_bg.jpg"
						style="width: 100%;" height="90" />
				</div>
			</div>
			<div id="menu">
				<div class="left" style="padding-left: 2px;">
					<c:forEach items="${topMenus}" var="menu">
						<div style="margin-top: 2px;" class="menu_daohang"
							code="${menu.id}">
							${menu.menuName}
						</div>
					</c:forEach>
					<div style="margin-top: 2px;" class="menu_daohangWork"
						id="myworkButton">
						我的工作
					</div>
				</div>
				<div class="right">
					<input class="menu_button01" name="" type="button"
						onclick="javascript:location.href='logout.do'" />
				</div>
				<div class="left right">
					<div class="menu_txt" style="width: 120px;"
						margin-left: 20px;" id="time_1"></div>
				</div>
				<div
					class="left right>
				<div ><img src="<%=request.getContextPath()%>/css/head/images/ico_01.jpg" />用户名：${sessionScope.user.username}
				&nbsp;
				<a href="javascript:void(0);"  onclick="jieshounoread();">消息(<font color="red"><span id="newmessage">0</span></font>)条</a>
				  <img  id="vs_ico" src="<%=request.getContextPath()%>/images/icon/TreeView/useropen.gif" />
				</div>
			</div>
		</div>
		
	</div>
	<div region="south" border="true" style="height: 25px; 
	text-align: center; font-size: 12px; padding-top: 3px" class="background">
		Copyright
		© 2008&nbsp;&nbsp; All Rights Reserved&nbsp;&nbsp;长城计算机软件与系统有限公司
		</div>
	<div region="west" title="菜单" border="true"  class="background" style="width: 190px;  border-bottom: 1px solid #99BBE8; border-right: 1px solid #99BBE8;">
	     	<ul id="treeul" style="height: 150px;margin-top: 0px;"></ul>
	</div>
	<div region="center" class="background" style="padding: 5px; ">
		<div id="workbench" class="easyui-tabs" fit=true>
			<div title="我的工作" style="overflow: hidden;" closable='true'>
					    <iframe id="mywork" name="mywork" src="<%=request.getContextPath()%>/work/MyWork/input.do" style="width:100%;height:100%;border-style: none;" 
					    frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" ></iframe>
			</div>
		</div>
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width:150px;">
			<div id="close">关闭</div> 
			<div id="closeall">全部关闭</div> 
			<div id="closeother">除此之外全部关闭</div> 
			<div class="menu-sep"></div> 
			<div id="closeright">当前页右侧全部关闭</div> 
			<div id="closeleft">当前页左侧全部关闭</div> 
    </div>
</body>
</html>