package com.audit.service.system;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Menu;

public interface IMenuInfoService {
    /**
     * 分页查询顶层菜单
     * @param page 当前页数
     * @param pagesize 每页条数
     * @param name  排序字段
     * @param order 排序方式
     * @return
     */
    public GridDataModel<Menu> find(Integer page,Integer pagesize,String name,String order);
    
    /**
     * 添加菜单
     * @param menu 添加内容
     * @return
     */
    public Map<String, Object> add(String menuName, String state, String sort, String remark) throws Exception;
    
    /**
     * 更新菜单
     * @param menu更新内容
     * @return
     */
    public boolean update(Menu menu) throws Exception;
    
    /**
     * 删除菜单
     * @param id
     * @return
     */
    public boolean delete(String id)throws Exception;
    
    /**
     * 根据ID获取菜单
     */
    public Menu getMenu(String id);
    
    /**
     * 分页查询下级菜单
     * @param page
     * @param pagesize
     * @param name
     * @param order
     * @return
     */
    public GridDataModel<Menu> findSubMenu(Integer page,Integer pagesize,String name,String order);
    
    /**
     * 增加子级菜单
     * @param menuName
     * @param state
     * @param sort
     * @param remark
     * @param pid
     * @return
     */
    public Map<String, Object> add(String menuName, String state, String sort, String remark, String pid)throws Exception;
    
    /**
     * 获取所有导航菜单
     * @return
     */
    public List<Menu> getTopMenu();
    
    /**
     * 更新子级菜单
     */
    public Map<String, Object> update(String menuName, String state, String sort, String remark, String pid, String menuId)throws Exception;
    
    /**
     * 检查导航名称是否存在
     * @param name
     * @return
     */
    public Map<String, Object> checkIsExist(String name);
    
    /**
     * 注销该菜单
     * @param id
     * @return
     */
    public Map<String, Object> destroy(Menu menu)throws Exception;
}
