/**
 * 
 */
package com.audit.controller.system;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.system.NoticeInfo;
import com.audit.service.common.ICompetenceService;
import com.audit.service.system.INoticeInfoService;

/**
 * @author 公告控制层
 * dengyong
 *
 */
@Controller
@RequestMapping("system/notice")
public class NoticeController implements IControllerBase{

	
	@Autowired
	private ICompetenceService  competenceService;
	
	
	@Autowired
	private INoticeInfoService  noticeInfoService;
	
	
	
	/**
	 * 添加公告
	 */
	@Override
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String createtime=request.getParameter("createtime");
			NoticeInfo notice=new NoticeInfo();
			notice.setContent(content);
			notice.setCreatetime(sdf.parse(createtime));
			notice.setTitle(title);
			map=noticeInfoService.add(notice);
		} catch (Exception e) {
			map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}
     
	/**
	 * 
	 * 2013-7-12
	 * 删除公告
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("id");
			map=noticeInfoService.delete(id);
		} catch (Exception e) {
			map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

	/** (non-Javadoc)
	 * 2013-7-12
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 分页查询公告信息
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
		//当前页   
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        //每页显示条数   
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10":rows);   
		Map<String, Object> map = new HashMap<String, Object>();
		GridDataModel<NoticeInfo>  gridmodel=noticeInfoService.find(intPage, pagesize, sort,order);
		map.put("rows",gridmodel.getRows() );
		map.put("total", gridmodel.getTotal());
		return map;
	}

	/**
	 *
	 * 跳转入口
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("/input")
	public String input(HttpServletRequest request) {
		
		//获取登录用户
		User user=(User)request.getSession().getAttribute("user");
		String add = request.getParameter("add");
		String update = request.getParameter("update");
		String look=request.getParameter("look");
		String worklook=request.getParameter("worklook");
		//获取模块id
		String id=request.getParameter("id");
		request.setAttribute("noticemoduelid",id);
		if (AuditStringUtils.isNotEmpty(add)) {
			return "/system/noticeAdd";
		} else if (AuditStringUtils.isNotEmpty(update)) {
			//获取公告id
			String key=request.getParameter("key");
			NoticeInfo notice=noticeInfoService.findbyid(key);
			request.setAttribute("noticeobject", notice);
			return "/system/noticeEdit";
		}else if (AuditStringUtils.isNotEmpty(look)) {
			//获取公告id
			String key=request.getParameter("key");
			NoticeInfo notice=noticeInfoService.findbyid(key);
			request.setAttribute("noticeobject", notice);
			return "/system/noticeLook";
		}
		else if (AuditStringUtils.isNotEmpty(worklook)) {
			//获取公告id
			String key=request.getParameter("key");
			NoticeInfo notice=noticeInfoService.findbyid(key);
			request.setAttribute("noticeobject", notice);
			return "/system/noticeworkLook";
		}
		else {
			
			String useraccount="";
			if(null!=user){
				useraccount=user.getUserAccount();
			}
			List<String> cf=competenceService.find(id,useraccount);
			request.setAttribute("noticemap",cf);
			return "/system/noticeIndex";
		}
	}

	/**修改公告信息
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		Map<String, Object> map=null;
		try {
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			NoticeInfo notice=noticeInfoService.findbyid(id);
			notice.setContent(content);
			notice.setTitle(title);
			map=noticeInfoService.update(notice);
		} catch (Exception e) {
			map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

}
