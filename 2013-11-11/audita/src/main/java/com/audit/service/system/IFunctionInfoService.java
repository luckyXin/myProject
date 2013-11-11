package com.audit.service.system;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.Function;

public interface IFunctionInfoService {
	
	/**
     * 分页查询顶层方法
     * @param page 当前页数
     * @param pagesize 每页条数
     * @param name  排序字段
     * @param order 排序方式
     * @return
     */
    public GridDataModel<Function> find(Integer page,Integer pagesize,String name,String order);
    
	/**
     * 分页查询顶层方法
     * @param page 当前页数
     * @param pagesize 每页条数
     * @param name  排序字段
     * @param order 排序方式
     * @return
     */
    public GridDataModel<Function> find(Integer page,Integer pagesize,String name,String order, String moduleId);
    
    /**
     * 添加方法
     * @param namem
     * @param state
     * @param method
     * @param remark
     * @return
     */
    public Map<String, Object> add(String name, String state, String method, String remark, String icon)throws Exception;
    
    /**
     * 更新方法
     * @param name
     * @param state
     * @param method
     * @param remark
     * @param icon
     * @param id
     * @return
     */
	public Map<String, Object> update(String name, String state, String method, String remark, String icon, String id)throws Exception;
    
    
    /**
     * 删除方法
     * @param id
     * @return
     */
    public Map<String, Object> delete(String id);
    
    /**
     * 注销方法
     * @param id
     * @param state
     * @return
     */
    public Map<String, Object> destory(String id, String state);
    
    /**
     * 通过ID获取方法信息
     * @param id
     * @return
     */
    public Function getFunctionById(String id);
}
