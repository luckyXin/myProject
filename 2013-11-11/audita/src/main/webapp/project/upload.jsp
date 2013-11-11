<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传文件</title>
    <link href="<%=request.getContextPath()%>/thirdparty/uploadify/uploadify.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/thirdparty/uploadify/jquery-1.3.2.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/thirdparty/uploadify/swfobject.js"></script> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/thirdparty/uploadify/jquery.uploadify.v2.1.0.min.js"></script> 
    <script type="text/javascript">
    $(document).ready(function() {
     var path=$("#path").val();
	$("#multiple_file_upload").uploadify({
		      'uploader'       :path+'/thirdparty/uploadify/uploadify.swf?random=' + (new Date()).getTime(),
		      'cancelImg'      :path+'/thirdparty/uploadify/cancel.png',
		      'script'         :'../common/o_multiple_upload.do',//要提交到的处理文件上传的PHP文件
		      'auto'           : false, //是否自动开始   
		      'multi'          : true, //是否支持多文件上传
		      'buttonText'     : '上次文', //按钮上的文字 
		      'simUploadLimit' : 1000, //一次同步上传的文件数目   
		      'sizeLimit'      : 19871202, //设置单个文件大小限制   
		      'queueSizeLimit' : 1000, //队列中同时存在的文件个数限制   
		      onComplete: function (event, queueID, fileObj, response, data) {   
		        alert('文件'+fileObj.name+'上传完毕');

		      },
		      onError: function(event, queueID, fileObj) {   
		          alert("文件:" + fileObj.name + "上传失败");   
		      },
		      onCancel: function(event, queueID, fileObj){   
		      }
	    });
      });

       
    
    
    
    
    
    </script>
    
	
   
  </head>
  
  <body>
        <input type="hidden" id="path" value="<%=request.getContextPath()%>">
        <input type="file" name="uploadify" id="multiple_file_upload" />  
        <hr>  
        <a href="javascript:$('#multiple_file_upload').uploadify('upload')">开始上传</a>      
        <a href="javascript:$('#multiple_file_upload').uploadify('cancel', '*')">取消所有上传</a>   
  </body>
</html>
