package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.EditUser;
import com.audit.entity.RoleUser;
import com.audit.entity.RoleUserMap;
import com.audit.entity.User;
import com.audit.service.system.IUserInfoService;
/**
 * 用户管理业务层
 * @author dengyong
 *
 */
public class UserInfoServiceImpl implements IUserInfoService{
	 /**
     * sqlMap操作DAO
     */
    private IbatisCommonDAO ibatisCommonDAO = null;

    /**
     * @param ibatisCommonDAO the ibatisCommonDAO to set
     */
    public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO)
    {
        this.ibatisCommonDAO = ibatisCommonDAO;
    }
    
    /**
	 * 分页查询用户信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
    @Override
	public GridDataModel<User> find(Integer page,Integer pagesize,String name,String order){
    	User user=new User();
    	GridDataModel<User> gm=null;
    	try
    	{
			user.setFiled(name);
			user.setSort(order);
			List<User> list=ibatisCommonDAO.executeForObjectList("selectAllUser",user,pagesize*(page-1),pagesize);
			Integer count=ibatisCommonDAO.executeForObject("selectAllcount",user,Integer.class);
			gm=new GridDataModel<User>();
			gm.setRows(list);
			gm.setTotal(count);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return gm;
	}
    
    /**
	 * 分页查询用户相应的角色
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<RoleUserMap> findroleuser(Integer page,Integer pagesize,String name,String order,String userid,String method){
		
    	GridDataModel<RoleUserMap> gm=null;
    	try
    	{   
    		
    		//查询已拥护角色
    		if("1".equals(method)){
    			RoleUser roleuser=new RoleUser();
    			roleuser.setFiled(name);
    			roleuser.setSort(order);
    			roleuser.setUserid(userid);
    			List<RoleUserMap> list=ibatisCommonDAO.executeForObjectList("selectuserhaverolepage",roleuser,pagesize*(page-1),pagesize);
    			
    			Integer count=ibatisCommonDAO.executeForObject("selectuserhaverolecount",userid,Integer.class);
    			gm=new GridDataModel<RoleUserMap>();
    			gm.setRows(list);
    			gm.setTotal(count);
    		}else{
    			RoleUser roleuser=new RoleUser();
    			roleuser.setFiled(name);
    			roleuser.setSort(order);
    			roleuser.setUserid(userid);
    			List<RoleUserMap> list=ibatisCommonDAO.executeForObjectList("selectusernohaverolepage",roleuser,pagesize*(page-1),pagesize);
    			
    			Integer count=ibatisCommonDAO.executeForObject("selectusernohaverolecount",userid,Integer.class);
    			gm=new GridDataModel<RoleUserMap>();
    			gm.setRows(list);
    			gm.setTotal(count);
    		}
    		
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return gm;
	}
    
    /**
     * 增加用户方法
     * @throws Exception 
     */
    @Override
    public Map<String, Object> add(EditUser user) throws Exception{
    	Map<String, Object> map=null;
    	//获取用户id
    	Integer id=ibatisCommonDAO.executeForObject("selectmaxid",null,Integer.class);
    	if(null==id){
    		id=0;
    	}
    	//设置用户id
    	user.setId(AuditStringUtils.getID(CommonConstant.STR_USETPRIMARYKEY, id, 3));
    	int row=ibatisCommonDAO.executeInsert("adduser", user);
    	//判断用户是否增加成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("user.add.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("user.add.fail"));
    	}
    	return map;
    }
    
    /**
	 * 增加用户角色关系信息
	 * @return
	 */
	public Map<String, Object>  addroleuser(RoleUser ru) throws Exception{
		Map<String, Object> map=null;
    	//获取用户id
    	Integer id=ibatisCommonDAO.executeForObject("selectuserandrolemaxid",null,Integer.class);
    	if(null==id){
    		id=0;
    	}
    	//设置id
    	ru.setId((id+1));
    	int row=ibatisCommonDAO.executeInsert("insertuserandrole", ru);
    	//判断是否成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("userrole.add.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("userrole.add.fail"));
    	}
    	return map;
	}
	/**
	 * 删除用户角色关系信息
	 * @return
	 */
	public Map<String, Object>  delroleuser(RoleUser ru)  throws Exception{
		Map<String, Object> map=null;
		//执行删除
		int row=ibatisCommonDAO.executeDelete("deluserandrole", ru);
    	//判断是否成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("userrole.del.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("userrole.del.fail"));
    	}
    	return map;
	}
    
    /**
	 *修改用户信息
	 * @return
	 */
    @Override
	public Map<String, Object>  update(EditUser user)  throws Exception{
    	Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updateuser", user);
    	//判断用户是否修改成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("user.update.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("user.update.fail"));
    	}
    	return map;
    }
    
	/**
	 *修改用户密码信息
	 * @return
	 */
	public Map<String, Object>  updatepwd(EditUser user) throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updateuserpwd", user);
    	//判断用户是否修改成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", "修改密码成功");
    	}else{
    		map.put("msg",  "修改密码失败");
    	}
    	return map;
	}
    
    /**
	 * 查询用户账号是否存在
	 * @return
	 */
    @Override
	public Map<String, Object>  findbyuserAccount(String userAccount){
		Map<String, Object> map=null;
    	//获取该用户账号的条数
    	Integer id=ibatisCommonDAO.executeForObject("selectuserbyuserAccount",userAccount,Integer.class);
    	//判断
    	map=new HashMap<String, Object>();
    	if(id>0){
    		map.put("ishave", "yes");
    	}else{
    		map.put("ishave", "no");
    	}
    	return map;
	}
	
	/**
	 * 查询用户对象
	 * @return
	 */
    @Override
	public EditUser  findbyid(String id){
		EditUser user=null;
		try{
			user=ibatisCommonDAO.executeForObject("selectuserbyid",id, EditUser.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
    

    /**
	 * 查询用户对象
	 * @return
	 */
    @Override
	public EditUser  findbyuserAccountobject(String userAccount){
		EditUser user=null;
		try{
			user=ibatisCommonDAO.executeForObject("selectuserbyuseraccount",userAccount, EditUser.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
    /**
	 *删除用户信息
	 * @return
	 */
    @Override
	public Map<String, Object>  delete(EditUser user)  throws Exception{
    	Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updateuser", user);
    	//判断用户是否修改成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("user.delete.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("user.delete.fail"));
    	}
    	return map;
	}
    /**
	 * 查询所有用户
	 * @return
	 */
	public List<EditUser> findalluserinfo(){
		List<EditUser> list=ibatisCommonDAO.executeForObjectList("selectquanbuuser", null);
		return list;
	}
	
	/**
	 * 查询用户是否存在科长角色
	 * @return
	 */
	public Integer findUserRole(String userId){
		Integer count=0;
		//查询用户是否有科长角色
		count=ibatisCommonDAO.executeForObject("selectuserroleiskezhang",userId,Integer.class);
		return count;
	}
}
