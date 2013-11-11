<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script type="text/javascript">
    $(document).ready(function() {
        //导出
        $("#outword").click(function(){
          window.location=$("#path").val() + '/project/DataPre/outprojectfinish.do?key='+$("#key").val();
          return false;
        });
       
       //关闭按钮
		$("#closeback").click(function() {
		   top.closeModule(moduleName, parentModuleName);
		});
    
    });
    </script>
    
    <title>项目竣工决算资料单</title>
    

  </head>
  
  <body>
   <input type="hidden" id="path" value="<%=request.getContextPath()%>">
   
   <input type="hidden" id="key" value="${key}">
     <center>
        <table border="1" style="width: 100%;border-collapse:collapse" cellpadding="0" cellspacing="4">
           <tr >
              <th colspan="6"><font size="5">金牛区审计局<br>国家建设项目竣工决算资料单</font><br><font size="2" color="gray">附:《资料接交清单》</font></th>
           </tr>
           <tr>
             <td colspan="6">审计项目编号：${audit.no}</td>
           </tr>
           <tr>
             <td colspan="6">审计项目名称：${audit.auditName}</td>
           </tr>
           <tr>
             <td colspan="6">主审人员： ${audit.principalUser}</td>
           </tr>
           <tr>
             <td colspan="6">中介机构名称：${audit.intermediaryName}</td>
           </tr>
           <tr>
             <td colspan="6">复核工程师：${audit.departUser}</td>
           </tr>
           <tr>
             <td style="width: 5%">
                                          建设单位
             </td>
              <td style="width: 25%;">
              &nbsp;${audit.ownnerName}
             </td>
              <td style="width: 5%;">
                                         联系人
             </td>
              <td style="width: 25%;">
              &nbsp;${audit.ownnerLink}
             </td>
              <td style="width: 5%;">
                                       电话号码
             </td>
              <td style="width: 25%;">
              &nbsp;${audit.ownnerTelphone}
             </td>
           </tr>
           <tr>
              <td rowspan="8">施工单位</td>
              <td>1：${audit.consturctName}</td>
              <td>
                                         联系人
             </td>
              <td>
              &nbsp;${audit.consturctLink}
             </td>
              <td>
                                       电话号码
             </td>
              <td>
              &nbsp;${audit.consturctTelPhone}
             </td>
           </tr>
           <tr>
              <td>2：</td>
              <td>
                                         联系人
             </td>
              <td>
              &nbsp;
             </td>
              <td>
                                       电话号码
             </td>
              <td>
              &nbsp;
             </td>
           </tr>
           <tr>
              <td>3：</td>
              <td>
                                         联系人
             </td>
              <td>
              &nbsp;
             </td>
              <td>
                                       电话号码
             </td>
              <td>
              &nbsp;
             </td>
           </tr>
           <tr>
              <td>4：</td>
              <td>
                                         联系人
             </td>
              <td>
              &nbsp;
             </td>
              <td>
                                       电话号码
             </td>
              <td>
              &nbsp;
             </td>
           </tr>
           <tr>
              <td>5：</td>
              <td>
                                         联系人
             </td>
              <td>
              &nbsp;
             </td>
              <td>
                                       电话号码
             </td>
              <td>
              &nbsp;
             </td>
           </tr>
           <tr>
              <td>
                                 送审金额
              </td>
              <td colspan="2">
              ${audit.sendmoeny}(元)
              </td>
              <td colspan="3">
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${audit.year}年   ${audit.month}月 ${audit.day}日
              </td>
           </tr>
        </table>
        
        <div style="margin-top:10px;">
			<a  href="javascript:void(0);"  id="outword" class="easyui-linkbutton" iconCls="icon-save">导出</a>
			<a  href="javascript:void(0);" class="easyui-linkbutton" id="closeback"  iconCls="icon-back">返回</a>
		</div>
     </center>
</html>
