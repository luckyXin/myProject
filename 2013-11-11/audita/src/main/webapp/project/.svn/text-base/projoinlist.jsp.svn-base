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
					url : $("#path").val() + '/project/DataPre/addprojoin.do',
					success : function(result) {
						if (result.success=="success") {
							 window.location=$("#path").val() + '/project/DataPre/outprojoin.do?key='+$("#key").val();
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
					url : $("#path").val() + '/project/DataPre/addprojoin.do',
					success : function(result) {
						if (result.success=="success") {
							top.showMsg(result.msg);
							//top.closeModule(moduleName, parentModuleName);
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
    
    <title>项目竣工决算资料单</title>
    

  </head>
  
  <body>
  <form id="form" method="post">
   <input type="hidden" id="path" value="<%=request.getContextPath()%>">
   
   <input type="hidden" id="key" name="key" value="${datapre.id}">
     <center>
        <table border="1" style="width: 100%;border-collapse:collapse" cellpadding="0" cellspacing="4">
           <tr >
              <th colspan="2"><font size="5">资 料 接 交 清 单</font></th>
           </tr>
           <tr>
             <td colspan="2">工程项目名称：${datapre.projectName}</td>
           </tr>
           <tr>
             <td style="width: 40%">被审计单位提供资料名称</td>
             <td align="center">备      注</td>
           </tr>
            <tr>
             <td>审计申请、建施双方承诺书、财务收支表</td>
             <td align="center">
             <c:if test="${empty datajoin.auditapply}">
             <input type="radio" name="auditapply" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="auditapply" value="0"/>×
             </c:if>
              <c:if test="${datajoin.auditapply=='1'}">
             <input type="radio" name="auditapply" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="auditapply" value="0"/>×
             </c:if>
              <c:if test="${datajoin.auditapply=='0'}">
             <input type="radio" name="auditapply" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="auditapply" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
           <tr>
             <td>工程立项批复文件</td>
             <td align="center">
             <c:if test="${empty datajoin.projectlixiangfile}">
             <input type="radio" name="projectlixiangfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectlixiangfile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.projectlixiangfile=='1'}">
             <input type="radio" name="projectlixiangfile" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectlixiangfile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.projectlixiangfile=='0'}">
             <input type="radio" name="projectlixiangfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectlixiangfile" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程概算审批文件</td>
             <td align="center">
               <c:if test="${empty datajoin.projectgaisuanfile}">
             <input type="radio" name="projectgaisuanfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectgaisuanfile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.projectgaisuanfile=='1'}">
             <input type="radio" name="projectgaisuanfile" value="1" checked="checked" />√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectgaisuanfile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.projectgaisuanfile=='0'}">
             <input type="radio" name="projectgaisuanfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectgaisuanfile" value="0" checked="checked" />×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程施工合同</td>
             <td align="center">
             <c:if test="${empty datajoin.projectconstructfile}">
             <input type="radio" name="projectconstructfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectconstructfile" value="0"/>×
             </c:if>
             <c:if test="${datajoin.projectconstructfile=='1'}">
             <input type="radio" name="projectconstructfile" value="1" checked="checked" />√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectconstructfile" value="0"/>×
             </c:if>
             <c:if test="${datajoin.projectconstructfile=='0'}">
             <input type="radio" name="projectconstructfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projectconstructfile" value="0"  checked="checked"/>×
             </c:if>
             </td>
           </tr>
           <tr>
             <td>工程设计、监理、勘察合同</td>
             <td align="center">
              <c:if test="${empty datajoin.projianlifile}">
             <input type="radio" name="projianlifile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projianlifile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.projianlifile=='1'}">
             <input type="radio" name="projianlifile" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projianlifile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.projianlifile=='0'}">
             <input type="radio" name="projianlifile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="projianlifile" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
           
           
            <tr>
             <td>工程施工图纸</td>
             <td align="center">
              <c:if test="${empty datajoin.proconstructtuzi}">
             <input type="radio" name="proconstructtuzi" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proconstructtuzi" value="0"/>×
             </c:if>
              <c:if test="${datajoin.proconstructtuzi=='1'}">
             <input type="radio" name="proconstructtuzi" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proconstructtuzi" value="0"/>×
             </c:if>
              <c:if test="${datajoin.proconstructtuzi=='0'}">
             <input type="radio" name="proconstructtuzi" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proconstructtuzi" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程竣工图纸</td>
             <td align="center">
              <c:if test="${empty datajoin.profinishtuzi}">
             <input type="radio" name="profinishtuzi" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="profinishtuzi" value="0"/>×
             </c:if>
              <c:if test="${datajoin.profinishtuzi=='1'}">
             <input type="radio" name="profinishtuzi" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="profinishtuzi" value="0"/>×
             </c:if>
              <c:if test="${datajoin.profinishtuzi=='0'}">
             <input type="radio" name="profinishtuzi" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="profinishtuzi" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程招标相关文件</td>
             <td align="center">
              <c:if test="${empty datajoin.proinvitefile}">
             <input type="radio" name="proinvitefile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proinvitefile" value="0"/>×
             </c:if>
             <c:if test="${datajoin.proinvitefile=='1'}">
             <input type="radio" name="proinvitefile" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proinvitefile" value="0"/>×
             </c:if>
             <c:if test="${datajoin.proinvitefile=='0'}">
             <input type="radio" name="proinvitefile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proinvitefile" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程比选相关文件</td>
             <td align="center">
              <c:if test="${empty datajoin.procomparisionfile}">
             <input type="radio" name="procomparisionfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="procomparisionfile" value="0"/>×
             </c:if>
             <c:if test="${datajoin.procomparisionfile=='1'}">
             <input type="radio" name="procomparisionfile" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="procomparisionfile" value="0"/>×
             </c:if>
             <c:if test="${datajoin.procomparisionfile=='0'}">
             <input type="radio" name="procomparisionfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="procomparisionfile" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>设计变更图纸、设计变更签证单</td>
             <td align="center">
              <c:if test="${empty datajoin.designchangetuzi}">
             <input type="radio" name="designchangetuzi" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="designchangetuzi" value="0"/>×
             </c:if>
             <c:if test="${datajoin.designchangetuzi=='1'}">
             <input type="radio" name="designchangetuzi" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="designchangetuzi" value="0"/>×
             </c:if>
             <c:if test="${datajoin.designchangetuzi=='0'}">
             <input type="radio" name="designchangetuzi" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="designchangetuzi" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>施工组织设计</td>
             <td align="center">
              <c:if test="${empty datajoin.constructgroupdesign}">
             <input type="radio" name="constructgroupdesign" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="constructgroupdesign" value="0"/>×
             </c:if>
              <c:if test="${datajoin.constructgroupdesign=='1'}">
             <input type="radio" name="constructgroupdesign" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="constructgroupdesign" value="0"/>×
             </c:if>
              <c:if test="${datajoin.constructgroupdesign=='0'}">
             <input type="radio" name="constructgroupdesign" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="constructgroupdesign" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
           
            <tr>
             <td>隐蔽工程记录</td>
             <td align="center">
             <c:if test="${empty datajoin.hideprolog}">
             <input type="radio" name="hideprolog" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="hideprolog" value="0"/>×
             </c:if>
             <c:if test="${datajoin.hideprolog=='1'}">
             <input type="radio" name="hideprolog" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="hideprolog" value="0"/>×
             </c:if>
             <c:if test="${datajoin.hideprolog=='0'}">
             <input type="radio" name="hideprolog" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="hideprolog" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>施工过程中双方签证资料</td>
             <td align="center">
              <c:if test="${empty datajoin.visadata}">
             <input type="radio" name="visadata" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="visadata" value="0"/>×
             </c:if>
             <c:if test="${datajoin.visadata=='1'}">
             <input type="radio" name="visadata" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="visadata" value="0"/>×
             </c:if>
             <c:if test="${datajoin.visadata=='0'}">
             <input type="radio" name="visadata" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="visadata" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>地质勘探报告</td>
             <td align="center">
             <c:if test="${empty datajoin.dizilook}">
             <input type="radio" name="dizilook" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="dizilook" value="0"/>×
             </c:if>
             <c:if test="${datajoin.dizilook=='1'}">
             <input type="radio" name="dizilook" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="dizilook" value="0"/>×
             </c:if>
             <c:if test="${datajoin.dizilook=='0'}">
             <input type="radio" name="dizilook" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="dizilook" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程竣工决算书</td>
             <td align="center">
              <c:if test="${empty datajoin.profinishsettlement}">
             <input type="radio" name="profinishsettlement" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="profinishsettlement" value="0"/>×
             </c:if>
              <c:if test="${datajoin.profinishsettlement=='1'}">
             <input type="radio" name="profinishsettlement" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="profinishsettlement" value="0"/>×
             </c:if>
              <c:if test="${datajoin.profinishsettlement=='0'}">
             <input type="radio" name="profinishsettlement" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="profinishsettlement" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>安全文明施工措施评价及费率测定表</td>
             <td align="center">
             <c:if test="${empty datajoin.ratemeasurementtable}">
             <input type="radio" name="ratemeasurementtable" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="ratemeasurementtable" value="0"/>×
             </c:if>
              <c:if test="${datajoin.ratemeasurementtable=='1'}">
             <input type="radio" name="ratemeasurementtable" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="ratemeasurementtable" value="0"/>×
             </c:if>
              <c:if test="${datajoin.ratemeasurementtable=='0'}">
             <input type="radio" name="ratemeasurementtable" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="ratemeasurementtable" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>材料价格证明文件</td>
             <td align="center">
              <c:if test="${empty datajoin.stuffzmfile}">
             <input type="radio" name="stuffzmfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="stuffzmfile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.stuffzmfile=='1'}">
             <input type="radio" name="stuffzmfile" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="stuffzmfile" value="0"/>×
             </c:if>
              <c:if test="${datajoin.stuffzmfile=='0'}">
             <input type="radio" name="stuffzmfile" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="stuffzmfile" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>施工单位资质证明、规费取费证书</td>
             <td align="center">
             <c:if test="${empty datajoin.feescertificate}">
             <input type="radio" name="feescertificate" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="feescertificate" value="0"/>×
             </c:if>
              <c:if test="${datajoin.feescertificate=='1'}">
             <input type="radio" name="feescertificate" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="feescertificate" value="0"/>×
             </c:if>
              <c:if test="${datajoin.feescertificate=='0'}">
             <input type="radio" name="feescertificate" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="feescertificate" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>金牛区国家投资项目工程变更签证确认函</td>
             <td align="center">
               <c:if test="${empty datajoin.probiangenvisaconfirm}">
             <input type="radio" name="probiangenvisaconfirm" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="probiangenvisaconfirm" value="0"/>×
             </c:if>
             <c:if test="${datajoin.probiangenvisaconfirm=='1'}">
             <input type="radio" name="probiangenvisaconfirm" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="probiangenvisaconfirm" value="0"/>×
             </c:if>
             <c:if test="${datajoin.probiangenvisaconfirm=='0'}">
             <input type="radio" name="probiangenvisaconfirm" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="probiangenvisaconfirm" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>建设单位预付工程款、垫付款项明细表</td>
             <td align="center">
              <c:if test="${empty datajoin.advanceforpro}">
             <input type="radio" name="advanceforpro" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="advanceforpro" value="0"/>×
             </c:if>
             <c:if test="${datajoin.advanceforpro=='1'}">
             <input type="radio" name="advanceforpro" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="advanceforpro" value="0"/>×
             </c:if>
             <c:if test="${datajoin.advanceforpro=='0'}">
             <input type="radio" name="advanceforpro" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="advanceforpro" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程质量评定表</td>
             <td align="center">
              <c:if test="${empty datajoin.proquality}">
             <input type="radio" name="proquality" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proquality" value="0"/>×
             </c:if>
             <c:if test="${datajoin.proquality=='1'}">
             <input type="radio" name="proquality" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proquality" value="0"/>×
             </c:if>
             <c:if test="${datajoin.proquality=='0'}">
             <input type="radio" name="proquality" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="proquality" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
            <tr>
             <td>工程开工、竣工报告</td>
             <td align="center">
              <c:if test="${empty datajoin.prostartend}">
             <input type="radio" name="prostartend" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="prostartend" value="0"/>×
             </c:if>
             <c:if test="${datajoin.prostartend=='1'}">
             <input type="radio" name="prostartend" value="1" checked="checked"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="prostartend" value="0"/>×
             </c:if>
             <c:if test="${datajoin.prostartend=='0'}">
             <input type="radio" name="prostartend" value="1"/>√
             &nbsp;&nbsp;&nbsp;<input type="radio" name="prostartend" value="0" checked="checked"/>×
             </c:if>
             </td>
           </tr>
          
        </table>
        
        <div style="margin-top:10px;">
			<a  href="javascript:void(0);"  id="outword" class="easyui-linkbutton" iconCls="icon-save">导出</a>
			<a  href="javascript:void(0);"  id="saveprojoin" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			<a  href="javascript:void(0);" class="easyui-linkbutton" id="closeback"  iconCls="icon-back">返回</a>
		</div>
     </center>
     </form>
</html>
