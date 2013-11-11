package com.audit.service.system;
import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.EditUser;
import com.audit.entity.RoleUser;
import com.audit.entity.RoleUserMap;
import com.audit.entity.User;
/**
 * 用户管理接口
 * @author Administrator
 *
 */
public interface IUserInfoService {
    
	/**
	 * 分页查询用户信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<User> find(Integer page,Integer pagesize,String name,String order);
	/**
	 * 分页查询用户相应的角色
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<RoleUserMap> findroleuser(Integer page,Integer pagesize,String name,String order,String id,String method);
	
	
	/**
	 * 查询用户账号是否存在
	 * @return
	 */
	public Map<String, Object>  findbyuserAccount(String userAccount);
	
	/**
	 * 增加用户信息
	 * @return
	 */
	public Map<String, Object>  add(EditUser user) throws Exception;
	
	/**
	 * 增加用户角色关系信息
	 * @return
	 */
	public Map<String, Object>  addroleuser(RoleUser ru) throws Exception;
	
	
	/**
	 * 删除用户角色关系信息
	 * @return
	 */
	public Map<String, Object>  delroleuser(RoleUser ru)throws Exception;
	
	/**
	 *修改用户信息
	 * @return
	 */
	public Map<String, Object>  update(EditUser user) throws Exception;
	
	/**
	 *修改用户密码信息
	 * @return
	 */
	public Map<String, Object>  updatepwd(EditUser user) throws Exception;
	
	
	/**
	 *删除用户信息
	 * @return
	 */
	public Map<String, Object>  delete(EditUser user) throws Exception;
	
	
	/**
	 * 查询用户对象
	 * @return
	 */
	public EditUser  findbyid(String id);
	
	 /**
	 * 查询用户对象
	 * @return
	 */
	public EditUser  findbyuserAccountobject(String userAccount);
	
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<EditUser> findalluserinfo();
	
	/**
	 * 查询用户是否存在科长角色
	 * @return
	 */
	public Integer findUserRole(String userId);
}
