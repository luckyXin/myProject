/**
 * 
 */
package com.audit.controller.work;

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
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.system.MessageInfo;
import com.audit.entity.system.NoticeInfo;
import com.audit.entity.work.MyNoCompleteWorkInfo;
import com.audit.entity.work.WorkInfo;
import com.audit.service.system.IMessageInfoService;
import com.audit.service.system.INoticeInfoService;
import com.audit.service.system.IUserInfoService;
import com.audit.service.work.IMyWorkService;

/**
 * @author User
 * 
 */
@Controller
@RequestMapping("/work/MyWork")
public class MyWorkController implements IControllerBase {

	@Autowired
	private IMyWorkService myWorkService;
	
	@Autowired
	private INoticeInfoService  noticeInfoService;
	
	@Autowired
	private IMessageInfoService  messageInfoService;
	
	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();

		// 未完成工作
		List<WorkInfo> noCompleteWorks = myWorkService.getMyNoCompleteWork(userAccount);
		if (noCompleteWorks.size() > 6) {
			for (int i = noCompleteWorks.size(); i < 6; i--) {
				noCompleteWorks.remove(i);
			}
		}
		request.setAttribute("noCompleteWorks", noCompleteWorks);

		// 已完成工作
		List<WorkInfo> completeWorks = myWorkService.getMyCompleteWork(userAccount);
		if (completeWorks.size() > 6) {
			for (int i = completeWorks.size(); i < 6; i--) {
				completeWorks.remove(i);
			}
		}
		request.setAttribute("completeWorks", completeWorks);

		// 公告
		// 查询前5条公告信息
		List<NoticeInfo> listnotice= noticeInfoService.findlist();
		if(null!=listnotice && listnotice.size()!=0){
			for(NoticeInfo notice:listnotice){
				notice.setFabutime(AuditStringUtils.getDatetoyyyyMMdd(notice.getFabutime()));
			}
		}
		request.setAttribute("listnotice", listnotice);
		
		//消息查询前5条当前用户接受的消息
		List<MessageInfo> listmessage=messageInfoService.findnowtopmessage(user.getId());
		for(MessageInfo message:listmessage){
			EditUser u=userInfoService.findbyid(message.getSenduser());
			message.setSenduser(u.getUsername());
		}
		request.setAttribute("listmessage", listmessage);
		/*// 消息
	    MessageInfo messageinfo=new MessageInfo();
	    messageinfo.setSenduser(user.getId());
	    messageinfo.setMessagestate(CommonConstant.NOREAD);
	    //发送者消息未阅读条数
	    Integer sendnoreadcount=messageInfoService.findSendMessageNum(messageinfo);
	    request.setAttribute("sendnoreadcount", sendnoreadcount);
	    
	    
	    MessageInfo messageinfo1=new MessageInfo();
	    messageinfo1.setSenduser(user.getId());
	    messageinfo1.setMessagestate(CommonConstant.READ);
	    //发送者消息已阅读条数
	    Integer sendreadcount=messageInfoService.findSendMessageNum(messageinfo1);
	    request.setAttribute("sendreadcount", sendreadcount);
	    
	    //收到消息未阅读条数
	    MessageInfo messageinfo2=new MessageInfo();
	    messageinfo2.setAcceptuser(user.getId());
	    messageinfo2.setMessagestate(CommonConstant.NOREAD);
	    Integer shoudaonoreadcount=messageInfoService.findjieshouMessageNum(messageinfo2);
	    request.setAttribute("shoudaonoreadcount", shoudaonoreadcount);
	    //收到消息已阅读
	    MessageInfo messageinfo3=new MessageInfo();
	    messageinfo3.setAcceptuser(user.getId());
	    messageinfo3.setMessagestate(CommonConstant.READ);
	    Integer shoudaoreadcount=messageInfoService.findjieshouMessageNum(messageinfo3);
	    request.setAttribute("shoudaoreadcount", shoudaoreadcount);*/
		return "/work/myWork";
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 当前页
			int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
			// 每页显示条数
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			GridDataModel<MyNoCompleteWorkInfo> myNoCompleteWorkInfo = myWorkService.getMyNoCompleteWork(page,
					pagesize, name, order, userAccount);
			map.put("rows", myNoCompleteWorkInfo.getRows());
			map.put("total", myNoCompleteWorkInfo.getTotal());
		} catch (NumberFormatException e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		return map;
	}
	
	/**
	 * 是否存在三天以上都未处理的项目，如果存在发出提示
	 * @param request
	 * @return
	 */
	@RequestMapping("threeDayNoProcessProject")
	@ResponseBody
	public Map<String, Object> threeDayNoProcessProject(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		try {
			map = myWorkService.getThreeDayNoProccess(userAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		return null;
	}

	/**
	 * (non-Javadoc) 2013-6-20
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		return null;
	}

}
