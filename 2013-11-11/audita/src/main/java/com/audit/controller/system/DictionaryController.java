package com.audit.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.controller.IControllerBase;
import com.audit.entity.Dictionary;
import com.audit.entity.User;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.IDictionaryService;

/**
 * 字典控制层
 * @author dengyong
 *
 */
@Controller
@RequestMapping("system/dictionary")
public class DictionaryController implements IControllerBase{

	
	@Autowired
	private IDictionaryService  dictionaryService;
	
	@Autowired
	private ICompetenceService  competenceService;
	
	/**
	 * 添加字典
	 */
	@Override
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			
			String dictionaryname=request.getParameter("dictionaryname");
			String pid=request.getParameter("pid");
			String remark=request.getParameter("remark");
			//调用业务层方法	
			Dictionary dic=new Dictionary();
			if(AuditStringUtils.isNotEmpty(pid)){
				dic.setPid(pid);
				dic.setIssystem(CommonConstant.ISNOTSYSTEM);
			}else{
				dic.setPid("0");
				dic.setIssystem(CommonConstant.ISSYSTEM);
			}
			dic.setDictionaryname(dictionaryname);
			dic.setRemark(remark);
			map=dictionaryService.add(dic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 删除字典
	 */
	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("dicid");
			//调用业务层删除
			map=dictionaryService.delete(id);
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
	 * 分页查询字典信息
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
		String dicid=request.getParameter("dicid");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<Dictionary>  gridmodel=dictionaryService.find(intPage, pagesize, sort,order,dicid);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
     * 字典管理入口 
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
			//查询字典
			List<Dictionary> list=dictionaryService.findbyid("0");
			request.getSession().setAttribute("listdic",list);
			return "/system/dictionaryadd";
		} else if (update != null && !"".equals(update)) {
			//查询字典
			List<Dictionary> list=dictionaryService.findbyid("0");
			request.getSession().setAttribute("listdic",list);
			//获取机构id
			String key=request.getParameter("key");
			
			Dictionary dictionary=dictionaryService.finddicbyid(key);
			request.getSession().setAttribute("dictionary",dictionary);
			return "/system/dictionaryEdit";
		}else {
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("dicmap",cf);
			return "/system/dictionaryIndex";
		}
	}

	/**
	 * 修改字典信息
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("id");
			String dictionaryname=request.getParameter("dictionaryname");
			
			String remark=request.getParameter("remark");
			//调用业务层修改
			Dictionary dic=dictionaryService.finddicbyid(id);
			if(dic.getIssystem()==0){
				String pid=request.getParameter("pid");
				dic.setPid(pid);
			}
			dic.setDictionaryname(dictionaryname);
			dic.setRemark(remark);
			map=dictionaryService.update(dic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 获取字典树形结构
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/finddictree")
	@ResponseBody
	public List<Map<String, String>> finddictree(ModelMap model,HttpServletRequest request) {
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
			//查询字典
			List<Dictionary> list=dictionaryService.findbyid(pstrParentValue);
		   if(null!=list && list.size()!=0)
		   {   
				 for(int i=0;i<list.size();i++){
						Map<String,String> map=new HashMap<String,String>();
						map.put("Ico", "sort");
						map.put("OnClick", "relaodtable('"+list.get(i).getId().toString()+"')");
						map.put("Caption", list.get(i).getDictionaryname());
						map.put("Alt", list.get(i).getDictionaryname());
						map.put("checkBox", "false");
						map.put("PostValue", list.get(i).getId().toString());
						map.put("PostPage", request.getContextPath()+"/system/dictionary/finddictree.do");
						map.put("ParentID", piParentID.toString());
						m_listmap.add(map);
				}
		   }
	       return m_listmap;
	}


}
