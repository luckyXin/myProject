package com.audit.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;

import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.Dictionary;
import com.audit.entity.EditUser;
import com.audit.entity.RoleUser;
import com.audit.entity.RoleUserMap;
import com.audit.entity.User;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IDictionaryService;
import com.audit.service.system.IUserInfoService;
/**
 * 用户管理控制层
 * @author dengyong
 *
 */
@Controller
@RequestMapping("system/user")
public class UserController  implements IControllerBase{
    
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private ICompetenceService  competenceService;
	
	@Autowired
	private IDictionaryService  dictionaryService;

	
	/**
	 * 用户管理入口方法
	 */
	@Override
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		
		//获取登录用户
		User user=(User)request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		//获取模块id
		String id=request.getParameter("id");
		request.getSession().setAttribute("moduelid",id);
		if (add != null && !"".equals(add)) {
			//获取用户性别和政府雇员资质
			List<Dictionary> listsex=dictionaryService.findbyid(PropertiesGetValue.getContextProperty("dictionary.type.sex"));
			List<Dictionary> listtype=dictionaryService.findbyid(PropertiesGetValue.getContextProperty("dictionary.type.qualification"));
			request.getSession().setAttribute("listsex",listsex);
			request.getSession().setAttribute("listtype",listtype);
			return "/system/useradd";
		} else if (update != null && !"".equals(update)) {
			//获取用户性别和政府雇员资质
			List<Dictionary> listsex=dictionaryService.findbyid(PropertiesGetValue.getContextProperty("dictionary.type.sex"));
			List<Dictionary> listtype=dictionaryService.findbyid(PropertiesGetValue.getContextProperty("dictionary.type.qualification"));
			request.getSession().setAttribute("listsex",listsex);
			request.getSession().setAttribute("listtype",listtype);
			//获取用户id
			String key=request.getParameter("key");
			EditUser edituser=userInfoService.findbyid(key);
			request.getSession().setAttribute("edituser",edituser);
			return "/system/useredit";
		}else {
			
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("usermap",cf);
			return "/system/userIndex";
		}
	}
	/**
	 * 添加用户
	 */
	@Override
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String userAccount=request.getParameter("userAccount");
			String username=request.getParameter("username");
			String sex=request.getParameter("sex");
			String birthday=request.getParameter("birthday");
			String telPhone=request.getParameter("telPhone");
			String cardid=request.getParameter("cardid");
			String qualification=request.getParameter("qualification");
			String email=request.getParameter("email");
			String deptid=request.getParameter("deptid");
			String state=request.getParameter("state");
			String remark=request.getParameter("remark");
			Integer istype=Integer.parseInt(request.getParameter("istype"));
			//调用业务层方法	
			EditUser user=new EditUser();
			user.setUserAccount(userAccount);
			user.setUsername(username);
			user.setSex(sex);
			if(AuditStringUtils.isNotEmpty(birthday))
			{
			  user.setBirthday(sdf.parse(birthday));
			}
			user.setTelPhone(telPhone);
			user.setCardId(cardid);
			user.setQualification(qualification);
			user.setEmail(email);
			user.setDeptid(deptid);
			user.setState(Integer.parseInt(state));
			user.setIstype(istype);
			user.setRemark(remark);
			//用户初始密码
			user.setPassword( PropertiesGetValue.getContextProperty("system.user.password"));
			user.setCreateTime(new Date());
			map=userInfoService.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 删除用户
	 */
	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			
			String id=request.getParameter("userid");
			//调用业务层方法	
			EditUser user=userInfoService.findbyid(id);
			user.setState(CommonConstant.USER_DELETE);
			//调用业务层修改
			map=userInfoService.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 根据用户查询是否存在用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/findbyid")
	@ResponseBody
	public Map<String, Object> findbyid(HttpServletRequest request) {
	    String usercount=request.getParameter("usercount");
	    Map<String, Object>  map=userInfoService.findbyuserAccount(usercount);
		return map;
	}
	/**
	 * 分页查询用户信息
	 * 
	 */
	@RequestMapping("/find")
	@ResponseBody
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sort =request.getParameter("sort");
		String order=request.getParameter("order");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<User>  gridmodel=userInfoService.find(intPage, pagesize, sort,order);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}
	
	/**
	 * 修改用户信息
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			
			//判断
			String method=request.getParameter("method");
			if(!AuditStringUtils.isNotEmpty(method))
			{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String id=request.getParameter("id");
				String username=request.getParameter("username");
				String sex=request.getParameter("sex");
				String birthday=request.getParameter("birthday");
				String telPhone=request.getParameter("telPhone");
				String cardid=request.getParameter("cardid");
				String qualification=request.getParameter("qualification");
				String email=request.getParameter("email");
				String deptid=request.getParameter("deptid");
				String state=request.getParameter("state");
				String remark=request.getParameter("remark");
				Integer istype=Integer.parseInt(request.getParameter("istype"));
				//调用业务层方法	
				EditUser user=userInfoService.findbyid(id);
				user.setUsername(username);
				user.setSex(sex);
				if(AuditStringUtils.isNotEmpty(birthday))
				{
				  user.setBirthday(sdf.parse(birthday));
				}else{
					 user.setBirthday(null);
				}
				user.setIstype(istype);
				user.setTelPhone(telPhone);
				user.setCardId(cardid);
				user.setQualification(qualification);
				user.setEmail(email);
				user.setDeptid(deptid);
				user.setState(Integer.parseInt(state));
				user.setRemark(remark);
				//调用业务层修改
				map=userInfoService.update(user);
			}
			if("delroleuser".equals(method)){
				String userid=request.getParameter("userid");
				String roleid=request.getParameter("roleid");
				//调用业务层方法	
				RoleUser ru=new RoleUser();
				ru.setRoleid(roleid);
				ru.setUserid(userid);
				map=userInfoService.delroleuser(ru);
			}
			if("addroleuser".equals(method)){
				String userid=request.getParameter("userid");
				String roleid=request.getParameter("roleid");
				//调用业务层方法	
				RoleUser ru=new RoleUser();
				ru.setRoleid(roleid);
				ru.setUserid(userid);
				map=userInfoService.addroleuser(ru);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 分页查询用户角色信息
	 * 
	 */
	@RequestMapping("/findroleuser")
	@ResponseBody
	public Map<String, Object> findroleuser(HttpServletRequest request) {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String sort =request.getParameter("sort");
		String order=request.getParameter("order");
		String userid=request.getParameter("userid");
		String method=request.getParameter("method");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<RoleUserMap>  gridmodel=userInfoService.findroleuser(intPage, pagesize, sort,order,userid,method);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}
	
}
