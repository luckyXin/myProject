/**
 * 政策性调整
 */
package com.audit.controller.project;

import java.io.IOException;
import java.math.BigDecimal;
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


import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;

import com.audit.entity.User;

import com.audit.entity.project.ProTractAdjustment;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;

import com.audit.entity.project.TractPolicyChange;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.IProTractProjectContractService;

import com.audit.service.project.ITractPolicyChangeService;
import com.audit.service.project.ITractProjectCreateService;


/**
 * @author dengyong
 */
@Controller
@RequestMapping("/project/policychang/")
public class TractPolicyChangeController {

	@Autowired
	private ICompetenceService competenceService;

	
	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;
	
	
	@Autowired
	private IProTractProjectContractService proTractProjectContractService;
	
	
	
	@Autowired
	private ITractPolicyChangeService  tractPolicyChangeService;


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
		String addrengong=request.getParameter("addrengong");
		String updaterengong=request.getParameter("updaterengong");
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
				request.setAttribute("policy", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				return "/project/tractBiaoDuanpolicychangEdit";
			}
		} else	if (AuditStringUtils.isNotEmpty(addsuopei)) {
			//获取标段id
			String key = request.getParameter("key");
			request.setAttribute("biaoDuanId", key);
			request.setAttribute("id", id);
			return "/project/zhengcechangeEdit";
		} 
		else if (AuditStringUtils.isNotEmpty(addrengong)) {
			//获取标段id
			String key = request.getParameter("key");
			request.setAttribute("policyChangeId", key);
			request.setAttribute("id", id);
			return "/project/rengongmoney";
		}else if (AuditStringUtils.isNotEmpty(updaterengong)) {
			//获取标段id
			String key = request.getParameter("key");
			//根据标段查询政策性
			ProTractAdjustment adjustment=tractPolicyChangeService.findbyrengongid(key);
			request.setAttribute("adjustment", adjustment);
			request.setAttribute("id", id);
			return "/project/rengongmoney";
		}  
		 else	if (AuditStringUtils.isNotEmpty(updatesuopei)) {
				String key = request.getParameter("key");
				//根据标段查询政策性
				TractPolicyChange zhengce=tractPolicyChangeService.findbyid(key);
				request.setAttribute("zhengce", zhengce);
				request.setAttribute("id", id);
				return "/project/zhengcechangeEdit";
				
		} 
		
		else if (AuditStringUtils.isNotEmpty(update)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId")+"update");
				request.setAttribute("policy", "1");
				return "/project/tractProjectQingBiaoEdit";
			} else {
				// 标段信息获取
				ProejctBiaoDuanInfo proejctBiaoDuanInfo = iTractProjectCreateService.getProejctBiaoDuanInfo(key);
				request.setAttribute("proejctBiaoDuanInfo", proejctBiaoDuanInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				return "/project/tractBiaoDuanpolicychangEdit";
			}
		} else {
			request.getSession().setAttribute("arrangeModuleId", id);
			request.setAttribute("id", id);
			return "/project/tractPolicychangIndex";
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
		GridDataModel<TractPolicyChange> projectBiaoDuan = tractPolicyChangeService.findbybd(intPage, pagesize, name,order,biaoDuanId);
		map.put("rows", projectBiaoDuan.getRows());
		map.put("total", projectBiaoDuan.getTotal());
		return map;
	}
	
	
	/**
	 * 查询政策性调整总金额
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTotalMoney")
	@ResponseBody
	public Map<String, Object> findTotalMoney(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String biaoDuanId = request.getParameter("biaoDuanId");
		//根据标段查询政策性调整信息
		List<TractPolicyChange> list=tractPolicyChangeService.findbybdid(biaoDuanId);
		BigDecimal rgTotal = new BigDecimal(0.00);
		BigDecimal clTotal = new BigDecimal(0.00);
		BigDecimal jxTotal = new BigDecimal(0.00);
		BigDecimal qtTotal = new BigDecimal(0.00);
		BigDecimal total = new BigDecimal(0.00);
		if(null!=list && list.size()!=0){
			for(TractPolicyChange change:list){
				
				Map<String, Object> map1 = new HashMap<String, Object>();
				 try {
					map1=tractPolicyChangeService.findtotalrengong(change.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				//查询下面总的人工费用查询总价
				rgTotal=rgTotal.add(new BigDecimal(map1.get("msg").toString()));
				
				if(AuditStringUtils.isNotEmpty(change.getDatamoney())){
					clTotal=clTotal.add(new BigDecimal(change.getDatamoney()));
				}
				if(AuditStringUtils.isNotEmpty(change.getMachinemoney())){
					jxTotal=jxTotal.add(new BigDecimal(change.getMachinemoney()));
				}
				if(AuditStringUtils.isNotEmpty(change.getOtherContext())){
					qtTotal=qtTotal.add(new BigDecimal(change.getOtherContext()));
				}
			}
		}
		total=qtTotal.add(jxTotal.add(rgTotal.add(clTotal)));
		map.put("rgTotal", rgTotal);
		map.put("clTotal", clTotal);
		map.put("jxTotal", jxTotal);
		map.put("qtTotal", qtTotal);
		map.put("total", total);
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
	public void add(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取参数
		String biaoDuanId = request.getParameter("biaoDuanId");
		String artificialfile=request.getParameter("artificialfile");
		String otherContext=request.getParameter("otherContext");
		String remark=request.getParameter("remark");
        String datamoney=request.getParameter("datamoney");
        String machinemoney=request.getParameter("machinemoney");
		
		

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//实例化政策性对象
		TractPolicyChange zhengce=new TractPolicyChange();
		User user = (User) request.getSession().getAttribute("user");
		if(null!=user){
			zhengce.setCreateUserAccount(user.getUsername());
		}
		zhengce.setBiaoDuanId(biaoDuanId);
		zhengce.setCreateTime(sdf.format(new Date()));
		//得到id
	     String id=AuditStringUtils.getUUID();	
	     zhengce.setId(id);
	     zhengce.setArtificialfile(artificialfile);
	     zhengce.setOtherContext(otherContext);
	     zhengce.setRemark(remark);
	     zhengce.setDatamoney(datamoney);
	     zhengce.setMachinemoney(machinemoney);
	/*	 List<FileBelongRelate> listfile=new ArrayList<FileBelongRelate>();
	       List<MultipartFile> file = requestMultipart.getFiles("uploadFile"); 
	       //上传文件
	       List<Map<String,String>> listmap= AuditStringUtils.uploadfile(file, "zhengce", request);
		   if(null!=listmap && listmap.size()!=0)
		   {
	    	  for(int i=0;i<listmap.size();i++){
		    	   FileBelongRelate fbr=new FileBelongRelate();
	               fbr.setFileName(listmap.get(i).get("fileName"));
	               fbr.setUrl(listmap.get(i).get("url"));
	               fbr.setState("");
	               fbr.setBelongToId(id);
	               fbr.setUploadTime(new Date());
	               listfile.add(fbr);
		       }
		    }*/	
		 Integer row= tractPolicyChangeService.add(zhengce);
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
	public void update(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取参数
		String id = request.getParameter("id");
		String artificialfile=request.getParameter("artificialfile");
		String otherContext=request.getParameter("otherContext");
		String remark=request.getParameter("remark");
		String datamoney=request.getParameter("datamoney");
	    String machinemoney=request.getParameter("machinemoney");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//实例化政策性对象
		TractPolicyChange zhengce=tractPolicyChangeService.findbyid(id);
		User user = (User) request.getSession().getAttribute("user");
		if(null!=user){
			zhengce.setCreateUserAccount(user.getUsername());
		}
		zhengce.setCreateTime(sdf.format(new Date()));
	    zhengce.setArtificialfile(artificialfile);
	    zhengce.setOtherContext(otherContext);
	    zhengce.setRemark(remark);
	    zhengce.setDatamoney(datamoney);
	    zhengce.setMachinemoney(machinemoney);
		/*List<FileBelongRelate> listfile=new ArrayList<FileBelongRelate>();
	    List<MultipartFile> file = requestMultipart.getFiles("uploadFile"); 
	       //上传文件
	       List<Map<String,String>> listmap= AuditStringUtils.uploadfile(file, "zhengce", request);
		   if(null!=listmap && listmap.size()!=0)
		   {
	    	  for(int i=0;i<listmap.size();i++){
		    	   FileBelongRelate fbr=new FileBelongRelate();
	               fbr.setFileName(listmap.get(i).get("fileName"));
	               fbr.setUrl(listmap.get(i).get("url"));
	               fbr.setState("");
	               fbr.setBelongToId(id);
	               fbr.setUploadTime(new Date());
	               listfile.add(fbr);
		       }
		 }	*/
		 Integer row= tractPolicyChangeService.update(zhengce);
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
	 * 删除政策性
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			
			String id=request.getParameter("id");
			map=tractPolicyChangeService.delete(id);
		} catch (Exception e) {
			map.put("success", "fail");
	    	map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}
}
