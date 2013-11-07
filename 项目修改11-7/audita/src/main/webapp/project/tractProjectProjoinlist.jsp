<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_edit.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/default/easyui.css" type="text/css"></link>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common-edit.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/core/jquery.form.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        //导出
        $("#outword").click(function(){
           $("#form").ajaxSubmit( {
					url : $("#path").val() + '/project/tractProjectDataTransfer/addprojoin.do',
					success : function(result) {
						if (result.success=="success") {
							 window.location=$("#path").val() + '/project/tractProjectDataTransfer/outprojoin.do?key='+$("#biaoDuanId").val();
							 return false;
						} else {
							top.showMsg(result.msg);
						}
					}
		  });
         
        });
        //保存
        $("#saveprojoin").click(function(){
                $("#form").ajaxSubmit( {
					url : $("#path").val() + '/project/tractProjectDataTransfer/addprojoin.do',
					success : function(result) {
						if (result.success=="success") {
							top.showMsg(result.msg);
						} else {
							top.showMsg(result.msg);
						}
					}
				});
        });
       
       //关闭按钮
		$("#closeback").click(function() {
		   top.closeModule(moduleName, parentModuleName);
		});
    
    });
    </script>
    
    <title>跟踪审计相关资料移交，留存目录</title>
    

  </head>
  
  <body>
  <form id="form" method="post">
   <input type="hidden" id="path" value="<%=request.getContextPath()%>">
   <input type="hidden" id="biaoDuanId" name="biaoDuanId" value="${id}">
     <center>
     
       <c:if test="${empty list}">
        <table border="1" style="width: 100%;border-collapse:collapse" cellpadding="0" cellspacing="4">
           <tr >
              <th colspan="6"><font size="5">跟踪审计相关资料移交，留存目录</font></th>
           </tr>
           <tr>
             <td colspan="6">工程项目名称：${projectName}</td>
           </tr>
           <tr>
             <td style="width: 10%" align="center">序号</td>
             <td  style="width: 45%" align="center">资   料   名   称</td>
              <td  style="width: 25%" align="center">有无</td>
               <td  style="width: 5%" align="center">份数</td>
                <td  style="width: 5%" align="center">页数</td>
                 <td  style="width: 5%" align="center">页码</td>
           </tr>
            <tr>
             <td colspan="6"><b>一.综合资料</b></td>
           </tr>
           <tr>
             <td align="center">1
               <input type="hidden" value="1" name="xuhao">
             </td>
             <td>
                                       建设工程造价咨询合同
               <input type="hidden" id="dataname" name="dataname" value="建设工程造价咨询合同">                        
             </td>
              <td align="center">
                  <input type="radio" name="have1" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have1" value="0"/>无
             </td>
             <td align="center">
                <input type="text"  name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">2
              <input type="hidden" value="2" name="xuhao">
             </td>
             <td>
                                       项目分配单
              <input type="hidden" id="dataname" name="dataname" value="项目分配单">    
             </td>
              <td align="center">
                  <input type="radio" name="have2" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have2" value="0"/>无
             </td>
            <td align="center">
                <input type="text"  name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">3
              <input type="hidden" value="3" name="xuhao">
             </td>
             <td>
                                    建设工程咨询实施方案
              <input type="hidden" id="dataname" name="dataname" value="建设工程咨询实施方案">    
             </td>
              <td align="center">
                  <input type="radio" name="have3" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have3" value="0"/>无
             </td>
             <td align="center">
                <input type="text"  name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">4
              <input type="hidden" value="4" name="xuhao">
             </td>
             <td>
                                       资料交接台账
               <input type="hidden" id="dataname" name="dataname" value="资料交接台账"> 
             </td>
              <td align="center">
                  <input type="radio" name="have4" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have4" value="0"/>无
             </td>
            <td align="center">
                <input type="text"  name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td colspan="6"><b>二.报告成果资料</b></td>
           </tr>
           <tr>
             <td align="center">5
             <input type="hidden" value="5" name="xuhao">
             </td>
             <td>
                                      工程量清单审查报告(按工程量清单存档要求)
              <input type="hidden" id="dataname" name="dataname" value="工程量清单审查报告(按工程量清单存档要求)">      
             </td>
              <td align="center">
                  <input type="radio" name="have5" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have5" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">6
               <input type="hidden" value="6" name="xuhao">
             </td>
             <td>
                                招标报价审查报告(按投标报价存档要求)
         <input type="hidden" id="dataname" name="dataname" value="招标报价审查报告(按投标报价存档要求)">    
             </td>
              <td align="center">
                  <input type="radio" name="have6" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have6" value="0"/>无
             </td>
              <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">7
               <input type="hidden" value="7" name="xuhao">
             </td>
             <td>
                                 招标控制价(标底)审查报告(按工程预算存档要求)
             <input type="hidden" id="dataname" name="dataname" value="招标控制价(标底)审查报告(按工程预算存档要求)">  
             </td>
              <td align="center">
                  <input type="radio" name="have7" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have7" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">8
               <input type="hidden" value="8" name="xuhao">
             </td>
             <td>
                             竣工决算审查报告(按竣工决算存档要求)
          <input type="hidden" id="dataname" name="dataname" value="竣工决算审查报告(按竣工决算存档要求)">  
             </td>
              <td align="center">
                  <input type="radio" name="have8" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have8" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">9
               <input type="hidden" value="9" name="xuhao">
             </td>
             <td>
              竣工财务决算审查报告(按竣工财务决算存档要求)
               <input type="hidden" id="dataname" name="dataname" value="竣工财务决算审查报告(按竣工财务决算存档要求)">  
             </td>
              <td align="center">
                  <input type="radio" name="have9" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have9" value="0"/>无
             </td>
            
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">10
               <input type="hidden" value="10" name="xuhao">
             </td>
             <td>
                跟踪管理审计报告
                  <input type="hidden" id="dataname" name="dataname" value="跟踪管理审计报告"> 
             </td>
              <td align="center">
                  <input type="radio" name="have10" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have10" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           
           <tr>
             <td colspan="6"><b>三.过程成果资料</b></td>
           </tr>
           <tr>
             <td align="center">11
               <input type="hidden" value="11" name="xuhao">
             </td>
             <td>
                                      跟踪审计意见
                                       <input type="hidden" id="dataname" name="dataname" value="跟踪审计意见"> 
             </td>
              <td align="center">
                  <input type="radio" name="have11" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have11" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">12
              <input type="hidden" value="12" name="xuhao">
             </td>
             <td>
                               工程进度款支付意见
                               <input type="hidden" id="dataname" name="dataname" value="工程进度款支付意见">    
             </td>
              <td align="center">
                  <input type="radio" name="have12" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have12" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">13
              <input type="hidden" value="13" name="xuhao">
             </td>
             <td>
                                 投标报价(清标)分析及管理建议表
             <input type="hidden" id="dataname" name="dataname" value="投标报价(清标)分析及管理建议表">  
             </td>
              <td align="center">
                  <input type="radio" name="have13" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have13" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">14
              <input type="hidden" value="14" name="xuhao">
             </td>
             <td>
                             投标报价审查表
                               <input type="hidden" id="dataname" name="dataname" value="投标报价审查表">  
             </td>
              <td align="center">
                  <input type="radio" name="have14" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have14" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">15
              <input type="hidden" value="15" name="xuhao">
             </td>
             <td>
               跟踪审计日志
               <input type="hidden" id="dataname" name="dataname" value="跟踪审计日志"> 
             </td>
              <td align="center">
                  <input type="radio" name="have15" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have15" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">16
              <input type="hidden" value="16" name="xuhao">
             </td>
             <td>
                月度报表
                 <input type="hidden" id="dataname" name="dataname" value="月度报表"> 
             </td>
              <td align="center">
                  <input type="radio" name="have16" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have16" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">17
              <input type="hidden" value="17" name="xuhao">
             </td>
             <td>
               跟踪审计阶段报告
               <input type="hidden" id="dataname" name="dataname" value="跟踪审计阶段报告"> 
             </td>
              <td align="center">
                  <input type="radio" name="have17" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have17" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">18
             <input type="hidden" value="18" name="xuhao">
             </td>
             <td>
               管理建议书
               <input type="hidden" id="dataname" name="dataname" value="管理建议书"> 
             </td>
              <td align="center">
                  <input type="radio" name="have18" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have18" value="0"/>无
             </td>
           
              <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">19
              <input type="hidden" value="19" name="xuhao">
             </td>
             <td>
                工作联系函
                <input type="hidden" id="dataname" name="dataname" value="工作联系函"> 
             </td>
              <td align="center">
                  <input type="radio" name="have19" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have19" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">20
              <input type="hidden" value="20" name="xuhao">
             </td>
             <td>
                询价记录表
                <input type="hidden" id="dataname" name="dataname" value="询价记录表"> 
             </td>
              <td align="center">
                  <input type="radio" name="have20" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have20" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">21
              <input type="hidden" value="21" name="xuhao">
             </td>
             <td>
                设计变更,洽商费用审核意见
                 <input type="hidden" id="dataname" name="dataname" value="设计变更,洽商费用审核意见"> 
             </td>
              <td align="center">
                  <input type="radio" name="have21" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have21" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">22
              <input type="hidden" value="22" name="xuhao">
             </td>
             <td>
                跟踪审计总结报告
                 <input type="hidden" id="dataname" name="dataname" value="跟踪审计总结报告"> 
             </td>
              <td align="center">
                  <input type="radio" name="have22" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have22" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">23
             <input type="hidden" value="23" name="xuhao">
             </td>
             <td>
               其他
                <input type="hidden" id="dataname" name="dataname" value="其他"> 
             </td>
              <td align="center">
                  <input type="radio" name="have23" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have23" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           
           
           <tr>
             <td colspan="6"><b>四.过程留存资料</b></td>
           </tr>
           <tr>
             <td colspan="6">施工招标文件</td>
           </tr>
           <tr>
             <td align="center">24
             <input type="hidden" value="24" name="xuhao">
             </td>
             <td>
                                      施工资格预审文件
                <input type="hidden" id="dataname" name="dataname" value="施工资格预审文件"> 
             </td>
              <td align="center">
                  <input type="radio" name="have24" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have24" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">25
             <input type="hidden" value="25" name="xuhao">
             </td>
             <td>
                              施工招标文件及答疑文件
            <input type="hidden" id="dataname" name="dataname" value="施工招标文件及答疑文件"> 
             </td>
              <td align="center">
                  <input type="radio" name="have25" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have25" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">26
             <input type="hidden" value="26" name="xuhao">
             </td>
             <td>
                               施工招标文件及澄清,承诺文件
              <input type="hidden" id="dataname" name="dataname" value="施工招标文件及澄清,承诺文件">                   
             </td>
              <td align="center">
                  <input type="radio" name="have26" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have26" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td align="center">27
              <input type="hidden" value="27" name="xuhao">
             </td>
             <td>
                             中标通知书
               <input type="hidden" id="dataname" name="dataname" value="中标通知书">                     
             </td>
              <td align="center">
                  <input type="radio" name="have27" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have27" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">28
              <input type="hidden" value="28" name="xuhao">
             </td>
             <td>
               施工项目评标报告
               <input type="hidden" id="dataname" name="dataname" value="施工项目评标报告">
             </td>
              <td align="center">
                  <input type="radio" name="have28" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have28" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">29
             <input type="hidden" value="29" name="xuhao">
             </td>
             <td>
                招标控制价(或)标底文件
                <input type="hidden" id="dataname" name="dataname" value="招标控制价(或)标底文件">
             </td>
              <td align="center">
                  <input type="radio" name="have29" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have29" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">30
              <input type="hidden" value="30" name="xuhao">
             </td>
             <td>
               施工合同
            <input type="hidden" id="dataname" name="dataname" value="施工合同">   
             </td>
              <td align="center">
                  <input type="radio" name="have30" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have30" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
              <td colspan="6">施工文件</td>
           </tr>
             <tr>
             <td align="center">31
              <input type="hidden" value="31" name="xuhao">
             </td>
             <td>
               施工组织设计及专项方案(标后)
              <input type="hidden" id="dataname" name="dataname" value="施工组织设计及专项方案(标后)">     
             </td>
              <td align="center">
                  <input type="radio" name="have31" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have31" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">32
              <input type="hidden" value="32" name="xuhao">
             </td>
             <td>
                  技术交底,图纸会审
              <input type="hidden" id="dataname" name="dataname" value="技术交底,图纸会审">     
             </td>
              <td align="center">
                  <input type="radio" name="have32" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have32" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">33
              <input type="hidden" value="33" name="xuhao">
             </td>
             <td>
          设计变更记录,工程洽商记录
          <input type="hidden" id="dataname" name="dataname" value="设计变更记录,工程洽商记录">  
             </td>
              <td align="center">
                  <input type="radio" name="have33" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have33" value="0"/>无
             </td>
           <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">34
             <input type="hidden" value="34" name="xuhao">
             </td>
             <td>
                现场签证记录
                 <input type="hidden" id="dataname" name="dataname" value="现场签证记录">  
             </td>
              <td align="center">
                  <input type="radio" name="have34" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have34" value="0"/>无
             </td>
           <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">35
             <input type="hidden" value="35" name="xuhao">
             </td>
             <td>
                地基与基槽验收记录
                 <input type="hidden" id="dataname" name="dataname" value="地基与基槽验收记录">
             </td>
              <td align="center">
                  <input type="radio" name="have35" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have35" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
             <tr>
             <td align="center">36
              <input type="hidden" value="36" name="xuhao">
             </td>
             <td>
               工程质量事故报告
               <input type="hidden" id="dataname" name="dataname" value="工程质量事故报告">
             </td>
              <td align="center">
                  <input type="radio" name="have36" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have36" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">37
              <input type="hidden" value="37" name="xuhao">
             </td>
             <td>
               工程事故处理记录
                <input type="hidden" id="dataname" name="dataname" value="工程事故处理记录">
             </td>
              <td align="center">
                  <input type="radio" name="have37" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have37" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">38
             <input type="hidden" value="38" name="xuhao">
             </td>
             <td>
               工程监理例会纪要
                <input type="hidden" id="dataname" name="dataname" value="工程监理例会纪要">
             </td>
              <td align="center">
                  <input type="radio" name="have38" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have38" value="0"/>无
             </td>
             
              <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">39
             <input type="hidden" value="39" name="xuhao">
             </td>
             <td>
               工程造价专题会议纪要
                <input type="hidden" id="dataname" name="dataname" value="工程造价专题会议纪要">
             </td>
              <td align="center">
                  <input type="radio" name="have39" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have39" value="0"/>无
             </td>
              <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">40
              <input type="hidden" value="40" name="xuhao">
             </td>
             <td>
               预付款报审与支付及月付款报审与支付
               <input type="hidden" id="dataname" name="dataname" value="预付款报审与支付及月付款报审与支付">
             </td>
              <td align="center">
                  <input type="radio" name="have40" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have40" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">41
              <input type="hidden" value="41" name="xuhao">
             </td>
             <td>
               设计变更,洽商费用报审与签认
                <input type="hidden" id="dataname" name="dataname" value="设计变更,洽商费用报审与签认">
             </td>
              <td align="center">
                  <input type="radio" name="have41" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have41" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">42
             <input type="hidden" value="42" name="xuhao">
             </td>
             <td>
               工程延期报告及审批
                <input type="hidden" id="dataname" name="dataname" value="工程延期报告及审批">
             </td>
              <td align="center">
                  <input type="radio" name="have42" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have42" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">43
             <input type="hidden" value="43" name="xuhao">
             </td>
             <td>
            费用索赔报告及审批
              <input type="hidden" id="dataname" name="dataname" value="费用索赔报告及审批">
             </td>
              <td align="center">
                  <input type="radio" name="have43" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have43" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">44
             <input type="hidden" value="44" name="xuhao">
             </td>
             <td>
              合同变更材料
              <input type="hidden" id="dataname" name="dataname" value="合同变更材料">
             </td>
              <td align="center">
                  <input type="radio" name="have44" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have44" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">45
             <input type="hidden" value="45" name="xuhao">
             </td>
             <td>
               材料设备价格确认单
               <input type="hidden" id="dataname" name="dataname" value="材料设备价格确认单">
             </td>
              <td align="center">
                  <input type="radio" name="have45" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have45" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">46
             <input type="hidden" value="46" name="xuhao">
             </td>
             <td>
               材料设备合同
               <input type="hidden" id="dataname" name="dataname" value="材料设备合同">
             </td>
              <td align="center">
                  <input type="radio" name="have46" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have46" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">47
             <input type="hidden" value="47" name="xuhao">
             </td>
             <td>
               综合单价确认单
               <input type="hidden" id="dataname" name="dataname" value="综合单价确认单">
             </td>
              <td align="center">
                  <input type="radio" name="have47" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have47" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">48
             <input type="hidden" value="48" name="xuhao">
             </td>
             <td>
               基础处理核定单
               <input type="hidden" id="dataname" name="dataname" value="基础处理核定单">
             </td>
              <td align="center">
                  <input type="radio" name="have48" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have48" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">49
             <input type="hidden" value="49" name="xuhao">
             </td>
             <td>
               隐蔽工程验收资料
                <input type="hidden" id="dataname" name="dataname" value="隐蔽工程验收资料">
             </td>
              <td align="center">
                  <input type="radio" name="have49" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have49" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">50
             <input type="hidden" value="50" name="xuhao">
             </td>
             <td>
               施工日记
                <input type="hidden" id="dataname" name="dataname" value="施工日记">
             </td>
              <td align="center">
                  <input type="radio" name="have50" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have50" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td colspan="6">施工验收</td>
           </tr>
             <tr>
             <td align="center">51
             <input type="hidden" value="51" name="xuhao">
             </td>
             <td>
              施工验收报告
              <input type="hidden" id="dataname" name="dataname" value="施工验收报告">
             </td>
              <td align="center">
                  <input type="radio" name="have51" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have51" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">52
              <input type="hidden" value="52" name="xuhao">
             </td>
             <td>
               竣工验收说明书
               <input type="hidden" id="dataname" name="dataname" value="竣工验收说明书">
             </td>
              <td align="center">
                  <input type="radio" name="have52" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have52" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">53
              <input type="hidden" value="53" name="xuhao">
             </td>
             <td>
               质量评价意见报告
                <input type="hidden" id="dataname" name="dataname" value="质量评价意见报告">
             </td>
              <td align="center">
                  <input type="radio" name="have53" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have53" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">54
              <input type="hidden" value="54" name="xuhao">
             </td>
             <td>
              竣工验收备案表
               <input type="hidden" id="dataname" name="dataname" value="竣工验收备案表">
             </td>
              <td align="center">
                  <input type="radio" name="have54" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have54" value="0"/>无
             </td>
            <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
            <tr>
             <td align="center">55
              <input type="hidden" value="55" name="xuhao">
             </td>
             <td>
            工程质量保修书
            <input type="hidden" id="dataname" name="dataname" value="工程质量保修书">
             </td>
              <td align="center">
                  <input type="radio" name="have55" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have55" value="0"/>无
             </td>
             <td align="center">
                <input type="text" name="scores">
             </td>
             <td align="center">
                <input type="text" name="pagenumber">
             </td>
             <td align="center">
                 <input type="text" name="pagination">
             </td>
           </tr>
           <tr>
             <td colspan="6">其他</td>
           </tr>
            <tr>
             <td colspan="6" height="100px;">跟踪审计单位责任人:</td>
           </tr>
        </table>
        </c:if>
        <c:if test="${not empty list}">
          <table border="1" style="width: 100%;border-collapse:collapse" cellpadding="0" cellspacing="4">
           <tr>
              <th colspan="6"><font size="5">跟踪审计相关资料移交，留存目录</font></th>
           </tr>
           <tr>
             <td colspan="6">工程项目名称：${datapre.projectName}</td>
           </tr>
           <tr>
             <td style="width: 10%" align="center">序号</td>
             <td  style="width: 45%" align="center">资   料   名   称</td>
              <td  style="width: 25%" align="center">有无</td>
               <td  style="width: 5%" align="center">份数</td>
                <td  style="width: 5%" align="center">页数</td>
                 <td  style="width: 5%" align="center">页码</td>
           </tr>
           <c:forEach items="${list}" var="data" varStatus="i">
            <c:if test="${i.count==1}">
              <tr>
             <td colspan="6"><b>一.综合资料</b></td>
              </tr>
            </c:if>
            <c:if test="${i.count==5}">
              <tr>
             <td colspan="6"><b>二.报告成果资料</b></td>
              </tr>
            </c:if>
            <c:if test="${i.count==11}">
              <tr>
             <td colspan="6"><b>三.过程成果资料</b></td>
              </tr>
            </c:if>
            <c:if test="${i.count==24}">
              <tr>
             <td colspan="6"><b>四.过程留存资料</b></td>
              </tr>
            </c:if>
             <c:if test="${i.count==24}">
              <tr>
             <td colspan="6">施工招标文件</td>
              </tr>
            </c:if>
            <c:if test="${i.count==31}">
              <tr>
             <td colspan="6">施工文件</td>
              </tr>
            </c:if>
             <c:if test="${i.count==51}">
              <tr>
             <td colspan="6">施工验收</td>
              </tr>
            </c:if>
           <tr>
             <td align="center">${data.xuhao}
               <input type="hidden" value="${data.xuhao}" name="xuhao">
             </td>
             <td>
               ${data.dataname}
               <input type="hidden" id="dataname" name="dataname" value="${data.dataname}">                        
             </td>
              <td align="center">
                  <c:if test="${data.have==1}">
                      <input type="radio" name="have${data.xuhao}" value="1"  checked="checked"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have${data.xuhao}" value="0"/>无
                  </c:if>
                   <c:if test="${data.have==0}">
                      <input type="radio" name="have${data.xuhao}" value="1" />有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have${data.xuhao}" value="0" checked="checked"/>无
                  </c:if>
                    <c:if test="${empty data.have}">
                       <input type="radio" name="have${data.xuhao}" value="1"/>有
                  &nbsp;&nbsp;&nbsp;<input type="radio" name="have${data.xuhao}" value="0"/>无
                    </c:if>
             </td>
             <td align="center">
                <input type="text"  name="scores" value="${data.scores}">
             </td>
             <td align="center">
                <input type="text" name="pagenumber" value="${data.pagenumber}">
             </td>
             <td align="center">
                 <input type="text" name="pagination" value="${data.pagination}">
             </td>
           </tr>
           
           
           </c:forEach>
         <tr>
             <td colspan="6">其它</td>
          </tr>
          <tr>
             <td colspan="6" height="100px;">跟踪审计单位责任人:</td>
           </tr>
        </table>
        </c:if>
        <div style="margin-top:10px;">
			<a  href="javascript:void(0);"  id="outword" class="easyui-linkbutton" iconCls="icon-save">导出</a>
			<a  href="javascript:void(0);"  id="saveprojoin" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			<a  href="javascript:void(0);" class="easyui-linkbutton" id="closeback"  iconCls="icon-back">返回</a>
		</div>
     </center>
     </form>
</html>
