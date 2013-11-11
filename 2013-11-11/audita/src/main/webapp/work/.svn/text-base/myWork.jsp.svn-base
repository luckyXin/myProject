<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>工作页面</title>
		<jsp:include page="../common/include.jsp"></jsp:include>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/work/myWork.js"></script>
		<style type="text/css">
* {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px
}

BODY {
	FONT-SIZE: 12px;
	LINE-HEIGHT: 24px;
	TEXT-ALIGN: center
}

UL {
	LIST-STYLE-TYPE: none
}d

LI {
	LIST-STYLE-TYPE: none
}

LI {
	VERTICAL-ALIGN: middle
}

A {
	TEXT-DECORATION: none
}

IMG {
	VERTICAL-ALIGN: middle;
	BORDER-TOP-STYLE: none;
	BORDER-RIGHT-STYLE: none;
	BORDER-LEFT-STYLE: none;
	BORDER-BOTTOM-STYLE: none
}

.shang {
	DISPLAY: inline;
	FLOAT: left;
	MARGIN-LEFT: 40px;
	MARGIN-RIGHT: 30PX;
	MARGIN-BOTTOM: 5PX;
	MARGIN-TOP: 10PX;
	OVERFLOW: hidden;
	WIDTH: 40%;
}

.shang P {
	BACKGROUND: url(/audita/images/index_tt.jpg) no-repeat;
	WIDTH: 100%;
	LINE-HEIGHT: 33px;
	HEIGHT: 33px
}

.shang UL {
	BORDER-RIGHT: #c5dee5 1px solid;
	BORDER-TOP: #c5dee5 1px solid;
	BACKGROUND: #fdfffb;
	OVERFLOW: hidden;
	BORDER-LEFT: #c5dee5 1px solid;
	BORDER-BOTTOM: #c5dee5 1px solid;
	HEIGHT: 157px;
}

.shang UL LI {
	PADDING-LEFT: 40px;
	BACKGROUND: url(/audita/images/h.jpg) no-repeat 10px 5px;
	MARGIN-LEFT: 1px;
	LINE-HEIGHT: 24px;
	MARGIN-RIGHT: 1px;
	BORDER-BOTTOM: #c5dee5 1px dotted;
	HEIGHT: 24px
}
</style>
	</head>
	<body style="padding: 5px;">
	<input type="hidden" id="coutextPath" value="<%=request.getContextPath()%>">
	<div id="p1" class="easyui-panel" title="工作信息" style="margin: 0;"  iconCls="icon-redo">
		<DIV class=shang>
			<P>
				<SPAN
					style="DISPLAY: inline; FONT-WEIGHT: bold; FLOAT: left; MARGIN-LEFT: 42px; COLOR: #035383">未完成工作</SPAN>
					<SPAN
					style="MARGIN-TOP: 10px; DISPLAY: inline; FLOAT: right; MARGIN-RIGHT: 5px">
					<a  href="javascript:void(0);"
							onclick="noProcessProject();"><IMG height=16 src="<%=request.getContextPath()%>/images/more.jpg"
						width=45> </a> </SPAN>
				</SPAN>
			</P>
			<UL>
				<c:forEach items="${noCompleteWorks}" var="work">
					<LI>
						<SPAN style="FLOAT: left; OVERFLOW: hidden; WIDTH: 300px">
							<SPAN
							style="FLOAT: left; OVERFLOW: hidden; WIDTH: 80px; COLOR: black; HEIGHT: 24px">${work.moduleName}</SPAN>
							&nbsp;&nbsp;&nbsp; <SPAN
							style="OVERFLOW: hidden; WIDTH: 200px; HEIGHT: 24px"> <A
								href="javascript:void(0);"
								onclick="clickNoComplete('${work.url}','${work.moduleId}','${work.moduleName}')">${work.stateName}(${work.count}个)</A>
						</SPAN> </SPAN>
					</LI>
				</c:forEach>
			</UL>
		</DIV>
		<DIV class=shang>
			<P>
				<SPAN
					style="DISPLAY: inline; FONT-WEIGHT: bold; FLOAT: left; MARGIN-LEFT: 42px; COLOR: #035383">已完成的工作</SPAN>
					<SPAN
					style="MARGIN-TOP: 10px; DISPLAY: inline; FLOAT: right; MARGIN-RIGHT: 5px">
					<a  href="javascript:void(0);"
							onclick="alreadyProcessProject();"><IMG height=16 src="<%=request.getContextPath()%>/images/more.jpg"
						width=45> </a> </SPAN>
			</P>
			<UL>
				<c:forEach items="${completeWorks}" var="work">
					<LI>
						<SPAN style="FLOAT: left; OVERFLOW: hidden; WIDTH: 300px">
							<SPAN
							style="FLOAT: left; OVERFLOW: hidden; WIDTH: 80px; COLOR: black; HEIGHT: 24px">${work.workTime}</SPAN>
							&nbsp;&nbsp;&nbsp; <SPAN
							style="OVERFLOW: hidden; WIDTH: 200px; HEIGHT: 24px"> <A
								href="javascript:void(0);"
								onclick="clickComplete('${work.url}','${work.moduleId}','${work.moduleName}')">${work.moduleName}(${work.count}个)</A>
						</SPAN> </SPAN>
					</LI>
				</c:forEach>
			</UL>
		</DIV>
		
		<DIV class=shang>
			<P>
				<SPAN
					style="DISPLAY: inline; FONT-WEIGHT: bold; FLOAT: left; MARGIN-LEFT: 42px; COLOR: #035383">公告</SPAN><SPAN
					style="MARGIN-TOP: 10px; DISPLAY: inline; FLOAT: right; MARGIN-RIGHT: 5px">
					<a  href="javascript:void(0);"
							onclick="morenotice();"><IMG height=16 src="<%=request.getContextPath()%>/images/more.jpg"
						width=45> </a> </SPAN>
			</P>
			<UL>
				<c:forEach items="${listnotice}" var="notice">
					<LI>
						<SPAN style="FLOAT: left; OVERFLOW: hidden; WIDTH: 300px">
							<SPAN style="FLOAT: left; OVERFLOW: hidden; WIDTH: 80px; COLOR: black; HEIGHT: 18px">${notice.fabutime}</SPAN>
							&nbsp;&nbsp;&nbsp; <SPAN style="OVERFLOW: hidden; WIDTH: 200px; HEIGHT: 18px"> <A href="javascript:void(0);"
							onclick="clickNotice('${notice.id}')"	>${notice.title}</A>
						</SPAN> </SPAN>
					
					</LI>
				</c:forEach>
			</UL>
		</DIV>
		
		<DIV class=shang>
			<P>
				<SPAN
					style="DISPLAY: inline; FONT-WEIGHT: bold; FLOAT: left; MARGIN-LEFT: 42px; COLOR: #035383">消息</SPAN><SPAN
					style="MARGIN-TOP: 10px; DISPLAY: inline; FLOAT: right; MARGIN-RIGHT: 5px">
					<a  href="javascript:void(0);"
							onclick="moremessage();"><IMG height=16 src="<%=request.getContextPath()%>/images/more.jpg"
						width=45> </a> </SPAN>
			</P>
			<UL>
				<c:forEach items="${listmessage}" var="message">
					<LI>
						<SPAN style="FLOAT: left; OVERFLOW: hidden; WIDTH: 300px">
							<SPAN style="FLOAT: left; OVERFLOW: hidden; WIDTH: 80px; COLOR: black; HEIGHT: 18px">${message.messagetime}</SPAN>
							&nbsp;&nbsp;&nbsp; <SPAN style="OVERFLOW: hidden; WIDTH: 200px; HEIGHT: 18px"> <A href="javascript:void(0);"
							onclick="clickMessage('${message.id}')"	>${message.senduser}</A>
						</SPAN> </SPAN>
					
					</LI>
				</c:forEach>
			</UL>
		</DIV>
		</div>
	</body>
</html>
