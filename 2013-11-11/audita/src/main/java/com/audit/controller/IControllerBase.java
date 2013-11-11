package com.audit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.GridDataModel;
import com.audit.entity.User;
import com.audit.entity.project.GovermentEmployeeAudit;

/**
 * @author User dengxin
 */
public interface IControllerBase {

	/**
	 * 页面初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request);

	/**
	 * 数据查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request);

	/**
	 * 增加
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request);

	/**
	 * 更新
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request);

	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request);

	/**
	 * 销毁
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("destroy")
	@ResponseBody
	public Map<String, Object> destroy(HttpServletRequest request);
	

	
}
