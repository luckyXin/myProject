/**
 * 
 */
package com.audit.controller.system;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.system.MessageInfo;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IMessageInfoService;
import com.audit.service.system.IUserInfoService;

/**
 * @author dengyong 消息控制层
 *
 */
@Controller
@RequestMapping("system/message")
public class MessageController{

	
	@Autowired
	private ICompetenceService  competenceService;
	
	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IMessageInfoService  messageInfoService;
	
	
	/**
	 * 添加消息
	 * @throws IOException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void add(HttpServletRequest request,MultipartHttpServletRequest requestMultipart,HttpServletResponse response) throws IOException {
		String msg = ""; 
		String falg = ""; 
		Map<String, Object> map=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//获取登录用户
			User user=(User)request.getSession().getAttribute("user");
			String acceptuser=request.getParameter("acceptuser");
			String messagetitle=request.getParameter("messagetitle");
			String messagecontent=request.getParameter("messagecontent");
			MessageInfo message=new MessageInfo();
			message.setMessagestate(CommonConstant.NOREAD);
			message.setSenduser(user.getId());
			message.setMessagetime(sdf.format(new Date()));
			message.setMessagetitle(messagetitle);
			message.setMessagecontent(messagecontent);
			List<FileBelongRelate> listfile=new ArrayList<FileBelongRelate>();
		    List<MultipartFile> file = requestMultipart.getFiles("uploadFile"); 
		    //上传文件
		    List<Map<String,String>> listmap= AuditStringUtils.uploadfile(file, "message", request);
		      if(null!=listmap && listmap.size()!=0)
		      {
		    	  for(int i=0;i<listmap.size();i++){
			    	   FileBelongRelate fbr=new FileBelongRelate();
		               fbr.setFileName(listmap.get(i).get("fileName"));
		               fbr.setUrl(listmap.get(i).get("url"));
		               fbr.setState("");
		               fbr.setUploadTime(new Date());
		               listfile.add(fbr);
			       }
		      }	  
			map=messageInfoService.add(message, acceptuser,listfile);
		} catch (Exception e) {
			map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		
		 if("success".equals(map.get("success")))
		 {
			  msg = "保存成功"; 
			  falg = "success"; 
		 }else{
			 msg = "保存失败"; 
			 falg = "fail"; 
		 }
		response.setContentType("text/html;charset=utf-8"); 
		response.getWriter().write("{'success':'"+falg+"','msg':'"+msg+"'}");
	}

	/**
	 * 
	 * 2013-7-12
	 * 删除消息
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("id");
			map=messageInfoService.delete(id);
		} catch (Exception e) {
			map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}



	/**
	 * 分页查询消息信息
	 * 
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		
		MessageInfo message=new MessageInfo();
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sort =request.getParameter("sort");
		String order=request.getParameter("order");
		//获取登录用户
		User user=(User)request.getSession().getAttribute("user");
		String method=request.getParameter("method");
		String messagestate=request.getParameter("messagestate");
		if(!AuditStringUtils.isNotEmpty(method) || "2".equals(method)){
			//查询全部
			message.setMehtodtype("1");
			message.setSenduser(user.getId());
			message.setAcceptuser(user.getId());
		}
		if("0".equals(method)){
			//发送消息
			message.setSenduser(user.getId());
		}
		if("1".equals(method)){
			//接受消息
			message.setAcceptuser(user.getId());
		}
		message.setMessagestate(messagestate);
		
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<MessageInfo>  gridmodel=messageInfoService.find(intPage, pagesize, sort,order,message);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 *
	 * 跳转入口
	 * @throws Exception 
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		
		//获取登录用户
		User user=(User)request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String look=request.getParameter("look");
		
		String sendnoread=request.getParameter("sendnoread");
		String sendread=request.getParameter("sendread");
		String jieshounoread=request.getParameter("jieshounoread");
		String jieshouread=request.getParameter("jieshouread");
		//获取模块id
		String id=request.getParameter("id");
		request.setAttribute("messagemoduelid",id);
		if (AuditStringUtils.isNotEmpty(add)) {
			//查询所有用户
			List<EditUser> listuser=userInfoService.findalluserinfo();
			request.setAttribute("listuser", listuser);
			return "/system/messageAdd";
		}else if (AuditStringUtils.isNotEmpty(look)) {
			
			String key=request.getParameter("key");
			MessageInfo messageobject=messageInfoService.findbyid(key);
			//判断查看消息的人是否是收件人
			if(user.getId().equals(messageobject.getAcceptuser())){
				messageobject.setMessagestate(CommonConstant.READ);
				//修改状态未已阅读
				try {
					messageInfoService.updatestate(messageobject);
				} catch (Exception e) {
				}
			}
			
			//查询消息的文件
			List<FileBelongRelate> list=messageInfoService.findmessagefile(key);
			if(null!=list && list.size()!=0){
				request.setAttribute("messagefile", list.get(0));
			}
			
			request.setAttribute("messageobject", messageobject);
			return "/system/messageLook";
		}else if(AuditStringUtils.isNotEmpty(sendnoread)){
			String key=request.getParameter("key");
			request.setAttribute("key", key);
			request.setAttribute("methodtype", "0");
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("messagemap",cf);
			return "/system/messageIndex";
		}
		else if(AuditStringUtils.isNotEmpty(sendread)){
			String key=request.getParameter("key");
			request.setAttribute("key", key);
			request.setAttribute("methodtype", "0");
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("messagemap",cf);
			return "/system/messageIndex";
		}
		else if(AuditStringUtils.isNotEmpty(jieshounoread)){
			String key=request.getParameter("key");
			request.setAttribute("key", key);
			request.setAttribute("methodtype", "1");
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("messagemap",cf);
			return "/system/messageIndex";
		}
		else if(AuditStringUtils.isNotEmpty(jieshouread)){
			String key=request.getParameter("key");
			request.setAttribute("key", key);
			request.setAttribute("methodtype", "1");
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("messagemap",cf);
			return "/system/messageIndex";
		}
		else {
			
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("messagemap",cf);
			return "/system/messageIndex";
		}
	}


}
