package com.audit.service.common;

import java.util.List;

import com.audit.entity.JsonMenu;
import com.audit.entity.Menu;
import com.audit.entity.Module;
import com.audit.entity.User;

public interface ILoginService {

    public User login(User user);
    
    public List<Menu> getTopMenu(User user);
    
    public List<JsonMenu> getSubMenu(String topMenuId, User user);
    
    /**
     * 查询菜单
     * @param user  用户
     * @param pstrParentValue 菜单id
     * @return
     */
    public List<Menu> getMenu(User user,String pstrParentValue);
    
   
    /**
     * 查询菜单下面对应的功能
     * @param user  用户
     * @param pstrParentValue 菜单id
     * @return
     */
    public List<Module> getModule(User user,String pstrParentValue);
    
    /**
     * 查询用户权限
     * @param user
     * @return
     */
    public List<Module> getPermission(User user);
}
