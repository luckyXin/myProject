package com.audit.controller.system;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.controller.IControllerBase;
import com.audit.entity.Department;
import com.audit.entity.User;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IDepartmentService;

/**
 * 机构管理
 * @author dengyong
 *
 */
@Controller
@RequestMapping("system/department")
public class DepartmentController implements IControllerBase{
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ICompetenceService  competenceService;

	/**
	 * 添加机构
	 */
	@Override
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			
			String deptname=request.getParameter("deptname");
			String pid=request.getParameter("pid");
			String remark=request.getParameter("remark");
			//调用业务层方法	
			Department dept=new Department();
			if(AuditStringUtils.isNotEmpty(pid)){
				dept.setPid(pid);
				dept.setDepttype(CommonConstant.DEPT);
			}else{
				dept.setPid("0");
				dept.setDepttype(CommonConstant.UNIT);
			}
		    dept.setDeptname(deptname);
		    dept.setRemark(remark);
			map=departmentService.add(dept);
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
	 * 分页查询机构信息
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
		String deptid=request.getParameter("deptid");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<Department>  gridmodel=departmentService.find(intPage, pagesize, sort,order,deptid);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}
    /**
     * 机构管理入口 
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
			return "/system/deptadd";
		} else if (update != null && !"".equals(update)) {
			//获取机构id
			String key=request.getParameter("key");
			Department dept=departmentService.findbyid(key);
			request.getSession().setAttribute("department",dept);
			return "/system/deptedit";
		}else {
			
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("deptmap",cf);
			return "/system/deptIndex";
		}
	}

	/**
	 * 修改部门信息
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("id");
			String deptname=request.getParameter("deptname");
			String remark=request.getParameter("remark");
			//调用业务层修改
			Department dept=departmentService.findbyid(id);
			dept.setDeptname(deptname);
			dept.setRemark(remark);
			map=departmentService.update(dept);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *查询部门信息
	 */
	@RequestMapping("/findbypid")
	public void findgroupuser(HttpServletRequest request,HttpServletResponse response) {
		    //调用业务层查询所有机构信息
		    String json=departmentService.findbypid(null);
		    PrintWriter out=null;
		    try {
		    	response.setContentType("text/javascript;charset=UTF-8");
				out = response.getWriter();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        out.write(json.toString());
	        out.flush();
	}
	
	/**
	 * 删除机构
	 */
	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("deptid");
			//调用业务层删除
			map=departmentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 获取机构树形结构
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/finddepttree")
	@ResponseBody
	public List<Map<String, String>> finddepttree(ModelMap model,HttpServletRequest request) {
		List<Map<String, String>> m_listmap = new ArrayList<Map<String, String>>();
		    //获取参数
			String  pstrParentValue=request.getParameter("pstrParentValue");
			String  piParentID=request.getParameter("piParentID");
			
			if (piParentID==null) {
				piParentID = "-1";
			}
			if(null==pstrParentValue || "".equals(pstrParentValue)){
				pstrParentValue="0";
			}
			//查询机构
			List<Department> list=departmentService.findbychildid(pstrParentValue);
		   if(null!=list && list.size()!=0)
		   {   
				 for(int i=0;i<list.size();i++){
						Map<String,String> map=new HashMap<String,String>();
						if("0".equals(list.get(i).getDepttype().toString())){
							map.put("Ico", "Unit");
						}else{
							map.put("Ico", "Department");
						}
						map.put("OnClick", "relaodtable('"+list.get(i).getId().toString()+"')");
						map.put("Caption", list.get(i).getDeptname());
						map.put("Alt", list.get(i).getDeptname());
						map.put("checkBox", "false");
						map.put("PostValue", list.get(i).getId().toString());
						map.put("PostPage", request.getContextPath()+"/system/department/finddepttree.do");
						map.put("ParentID", piParentID.toString());
						m_listmap.add(map);
				}
		   }
	       return m_listmap;
	}

}
