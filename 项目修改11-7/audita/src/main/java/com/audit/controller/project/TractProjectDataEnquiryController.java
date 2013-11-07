/**
 * 材料询价管理
 */
package com.audit.controller.project;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.audit.entity.User;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;

import com.audit.entity.project.TractProjectDataEnquiry;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IProTractProjectContractService;
import com.audit.service.project.IProjectDataEnquiryService;

import com.audit.service.project.ITractProjectCreateService;

/**
 * @author dengyong
 *
 */
@Controller
@RequestMapping("/project/dataenquiry/")
public class TractProjectDataEnquiryController {


	@Autowired
	private ICompetenceService competenceService;

	
	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;
	
	
	@Autowired
	private IProTractProjectContractService proTractProjectContractService;
	
	@Autowired
	private IProjectDataEnquiryService projectDataEnquiryService;
	
	


	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String id = request.getParameter("id");
		String addsuopei=request.getParameter("addsuopei");
		String updatesuopei=request.getParameter("updatesuopei");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		List<String> commonFunction = competenceService.find(id.substring(0, 4), userAccount);
		request.setAttribute("mapFunction", commonFunction);
		if (AuditStringUtils.isNotEmpty(add)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId")+"add");
				request.setAttribute("dataeng", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				request.setAttribute("datamoney", "1");
				return "/project/tractBiaoDuanpolicychangEdit";
			}
		} else	if (AuditStringUtils.isNotEmpty(addsuopei)) {
			//获取标段id
			String key = request.getParameter("key");
			request.setAttribute("biaoDuanId", key);
			request.setAttribute("id", id);
			return "/project/dataEnqEdit";
		} 
		 else	if (AuditStringUtils.isNotEmpty(updatesuopei)) {
				String key = request.getParameter("key");
				//根据标段查询材料询价
				TractProjectDataEnquiry data=projectDataEnquiryService.findbyid(key);
				request.setAttribute("data", data);
				request.setAttribute("id", id);
				return "/project/dataEnqEdit";
				
		} 
		
		else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId")+"update");
				request.setAttribute("dataeng", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				request.setAttribute("datamoney", "1");
				return "/project/tractBiaoDuanpolicychangEdit";
			}
		} else {
			request.getSession().setAttribute("arrangeModuleId", id);
			request.setAttribute("id", id);
			return "/project/tractProjectDataIndex";
		}
	}
	
	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/findbybd")
	@ResponseBody
	public Map<String, Object> findbybd(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String biaoDuanId = request.getParameter("biaoDuanId");
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		GridDataModel<TractProjectDataEnquiry> projectBiaoDuan = projectDataEnquiryService.findbybd(intPage, pagesize, name,order,biaoDuanId);
		map.put("rows", projectBiaoDuan.getRows());
		map.put("total", projectBiaoDuan.getTotal());
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String ownerName = request.getParameter("ownerName");
		String projectName = request.getParameter("projectName");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		String projectId = request.getParameter("projectId");

		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
        //获取当前登录用户
		User user=(User)request.getSession().getAttribute("user");
	
		if (AuditStringUtils.isNotEmpty(biaoDuanFind)) {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setProjectId(projectId);
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			if(null!=user){
				proejctBiaoDuanInfo.setBduserid(user.getId());
			}
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = proTractProjectContractService.finProejctBiaoDuanBaseInfo(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		} else {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			proejctBiaoDuanInfo.setOwnerName(ownerName);
			proejctBiaoDuanInfo.setProjectName(projectName);
			if(null!=user){
				proejctBiaoDuanInfo.setBduserid(user.getId());
			}
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = proTractProjectContractService.finProejctBiaoDuanInfo(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * @throws IOException 
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest requestMultipart) throws Exception {
		//获取参数
		String biaoDuanId = request.getParameter("biaoDuanId");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//实例化材料询价对象
		TractProjectDataEnquiry data=new TractProjectDataEnquiry();
		User user = (User) request.getSession().getAttribute("user");
		if(null!=user){
			data.setCreateuser(user.getUserAccount());
		}
		data.setBiaoDuanId(biaoDuanId);
		data.setCreatetime(sdf.format(new Date()));
		//得到id
	     String id=AuditStringUtils.getUUID();	
	     data.setId(id);
	     List<MultipartFile> file = requestMultipart.getFiles("datafile"); 
	     //上传文件
	     List<Map<String,String>> listmap= AuditStringUtils.uploadfile(file, "dataenquiry", request);
		 if(null!=listmap && listmap.size()!=0)
		 {
	    	  for(int i=0;i<listmap.size();i++){
	               data.setDataname(listmap.get(i).get("fileName"));
	               data.setDatafile(listmap.get(i).get("url"));
		       }
		 }
		 Integer row= projectDataEnquiryService.add(data);
		 String msg="";
		 String falg="";
		 if(row>0)
		 {
			  msg = "操作成功"; 
			  falg = "success"; 
		 }else{
			 msg = "操作失败"; 
			 falg = "fail"; 
		 }
		response.setContentType("text/html;charset=utf-8"); 
		response.getWriter().write("{'success':'"+falg+"','msg':'"+msg+"'}");
	}

	/**
	 * (non-Javadoc) 2013-7-25
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest requestMultipart) throws Exception {
		//获取参数
		String id = request.getParameter("id");
		//获取参数
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//实例化材料询价对象
		TractProjectDataEnquiry data=projectDataEnquiryService.findbyid(id);
		User user = (User) request.getSession().getAttribute("user");
		if(null!=user){
			data.setCreateuser(user.getUserAccount());
		}
		data.setCreatetime(sdf.format(new Date()));
	     List<MultipartFile> file = requestMultipart.getFiles("datafile"); 
	     //上传文件
	     List<Map<String,String>> listmap= AuditStringUtils.uploadfile(file, "dataenquiry", request);
		 if(null!=listmap && listmap.size()!=0)
		 {
	    	  for(int i=0;i<listmap.size();i++){
	               data.setDataname(listmap.get(i).get("fileName"));
	               data.setDatafile(listmap.get(i).get("url"));
		       }
		 }
		 Integer row= projectDataEnquiryService.update(data);
		 String msg="";
		 String falg="";
		 if(row>0)
		 {
			  msg = "操作成功"; 
			  falg = "success"; 
		 }else{
			 msg = "操作失败"; 
			 falg = "fail"; 
		 }
		response.setContentType("text/html;charset=utf-8"); 
		response.getWriter().write("{'success':'"+falg+"','msg':'"+msg+"'}");
	}
	
	/**
	 * 删除材料询价
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			map=new HashMap<String,Object>();
			String id=request.getParameter("id");
			TractProjectDataEnquiry data=projectDataEnquiryService.findbyid(id);
			//删除文件
			AuditStringUtils.deletefile(data.getDatafile(),request);
			Integer row=projectDataEnquiryService.delete(id);
			if(row>0){
				map.put("success", "success");
		    	map.put("msg", "删除成功");
			}else{
				map.put("success", "fail");
		    	map.put("msg", "删除失败");
			}
		} catch (Exception e) {
			map.put("success", "fail");
	    	map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}
}
