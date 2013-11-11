/**
 * 资料移交管理
 */
package com.audit.controller.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.audit.component.IAuditDocumentComponent;
import com.audit.controller.IControllerBase;
import com.audit.entity.User;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProejctBiaoDuanInfo;
import com.audit.entity.project.TractAuditProjectInfo;
import com.audit.entity.project.TractDataTurnList;
import com.audit.entity.project.TractProjectDataTransferInfo;
import com.audit.exception.AuditException;
import com.audit.service.common.ICompetenceService;
import com.audit.service.project.ITractProjectCreateService;
import com.audit.service.project.ITractProjectDataTransferService;
import com.audit.service.project.ITractProjectMonthReportService;

/**
 * @author dengxin
 */
@Controller
@RequestMapping("/project/tractProjectDataTransfer")
public class TractProjectDataTransferController implements IControllerBase {

	@Autowired
	private ICompetenceService competenceService;

	@Autowired
	private ITractProjectCreateService iTractProjectCreateService;

	@Autowired
	private ITractProjectMonthReportService iTractProjectMonthReportService;

	@Autowired
	private ITractProjectDataTransferService iTractProjectDataTransfer;

	@Autowired
	private IAuditDocumentComponent auditDocumentComponent;

	/**
	 * (non-Javadoc) 2013-7-28
	 * 
	 * @see com.audit.controller.IControllerBase#input(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String input(HttpServletRequest request) {
		String add = request.getParameter("add");
		String id = request.getParameter("id");
		String projoinlist = request.getParameter("projoinlist");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		if (AuditStringUtils.isNotEmpty(projoinlist)) {
			//String key = request.getParameter("key");
			String biaoDuanId = (String) request.getSession().getAttribute("biaoDuanId");
			List<TractDataTurnList> list=iTractProjectDataTransfer.findDataTurn(biaoDuanId);
			request.setAttribute("list", list);
			request.setAttribute("id", biaoDuanId);
			//查询标段名称
			ProejctBiaoDuanInfo proejctBiaoDuanInfo =iTractProjectCreateService.getProejctBiaoDuanInfo(biaoDuanId);
			request.setAttribute("projectName", proejctBiaoDuanInfo.getBiaoDuanName());
			return "/project/tractProjectProjoinlist";
		} else if (AuditStringUtils.isNotEmpty(add)) {
			String key = request.getParameter("key");
			if (AuditStringUtils.contains(key, CommonConstant.STR_TRACTPROJECTPRIMARYKEY) && key.length() < 8) {
				// 跟踪项目信息获取
				TractAuditProjectInfo tractAuditProjectInfo = iTractProjectCreateService.getTractAuditProjectInfoById(key);
				request.setAttribute("tractProject", tractAuditProjectInfo);
				request.setAttribute("id", request.getSession().getAttribute("arrangeModuleId") + "add");
				return "/project/tractProjectDataTransferProjectEdit";
			} else {
				// 标段信息获取
				TractProjectDataTransferInfo  tractProjectDataTransferInfo 	= iTractProjectDataTransfer.getTractProjectDataTransferInfo(key);
				request.setAttribute("dataTransfer", tractProjectDataTransferInfo);
				request.setAttribute("id", id);
				request.setAttribute("biaoDuanId", key);
				// 获取操作用户
				request.setAttribute("nowUser", user.getUsername());
				// 获取系统当前时间
				try {
					request.setAttribute("nowTime", AuditStringUtils.getSystem(AuditStringUtils.DATE_YYYYMMMDD));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 标段ID
				request.getSession().setAttribute("biaoDuanId", key);
				return "/project/tractProjectDataTransferEdit";
			}
		} else {
			List<String> commonFunction = competenceService.find(id, userAccount);
			request.setAttribute("mapFunction", commonFunction);
			request.setAttribute("id", id);
			return "/project/tractProjectDataTransferIndex";
		}
	}

	/**
	 * (non-Javadoc) 2013-7-28
	 * 
	 * @see com.audit.controller.IControllerBase#find(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> find(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strPage = request.getParameter("page");
		String rows = request.getParameter("rows");
		String name = request.getParameter("sort");
		String order = request.getParameter("order");
		String ownerName = request.getParameter("ownerName");
		String projectName = request.getParameter("projectName");
		String projectId = request.getParameter("projectId");
		String biaoDuanFind = request.getParameter("biaoDuanFind");
		User user = (User) request.getSession().getAttribute("user");
		String userAccount = user.getUserAccount();
		// 当前页
		int page = Integer.parseInt((strPage == null || strPage == "0") ? "1" : strPage);
		// 每页显示条数
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "2" : rows);
		if (AuditStringUtils.isNotEmpty(biaoDuanFind)) {
			ProejctBiaoDuanInfo proejctBiaoDuanInfo = new ProejctBiaoDuanInfo();
			proejctBiaoDuanInfo.setProjectId(projectId);
			proejctBiaoDuanInfo.setPageno(page);
			proejctBiaoDuanInfo.setPagesize(pagesize);
			proejctBiaoDuanInfo.setSort(order);
			proejctBiaoDuanInfo.setFiled(name);
			proejctBiaoDuanInfo.setUserAccount(userAccount);
			// 查询指定项目下的标段信息
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectMonthReportService.finProejctBiaoDuanInfo1(proejctBiaoDuanInfo);
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
			proejctBiaoDuanInfo.setUserAccount(userAccount);
			// 所有项目已经所有标段信息
			GridDataModel<ProejctBiaoDuanInfo> projectBiaoDuan = iTractProjectMonthReportService.finProejctBiaoDuanInfoAll1(proejctBiaoDuanInfo);
			map.put("rows", projectBiaoDuan.getRows());
			map.put("total", projectBiaoDuan.getTotal());
		}
		return map;
	}

	/**
	 * 资料交接文档添加
	 * 
	 * @param request
	 * @param requestMultipart
	 * @param response
	 */
	@RequestMapping("/addWord")
	public void addWord(HttpServletRequest request, MultipartHttpServletRequest requestMultipart, HttpServletResponse response) {
		List<MultipartFile> comprehensiveReportFile = requestMultipart.getFiles("comprehensiveReportFile");
		List<MultipartFile> otherFile = requestMultipart.getFiles("otherFile");
		FileBelongRelate file = new FileBelongRelate();
		Map<String, Object> map = new HashMap<String, Object>();
		String createTime = request.getParameter("createTime");
		String id = request.getParameter("id");
		try {
			List<FileBelongRelate> files = new ArrayList<FileBelongRelate>();
			List<Map<String, String>> otherlists = AuditStringUtils.uploadfile(otherFile, "dataTransfer", request);
			if (null != otherlists && otherlists.size() != 0) {
				for (int i = 0; i < otherlists.size(); i++) {
					FileBelongRelate fileBelongRelate = new FileBelongRelate();
					fileBelongRelate.setFileName(otherlists.get(i).get("fileName"));
					fileBelongRelate.setUrl(otherlists.get(i).get("url"));
					fileBelongRelate.setUploadTime(new Date());
					files.add(fileBelongRelate);
				}
			}
			
			String biaoDuanId = (String) request.getSession().getAttribute("biaoDuanId");
			User user = (User) request.getSession().getAttribute("user");
			String userAccount = user.getUserAccount();
			List<Map<String, String>> listmap = AuditStringUtils.uploadfile(comprehensiveReportFile, "dataTransfer", request);
			if (null != listmap && listmap.size() != 0) {
				for (int i = 0; i < listmap.size(); i++) {
					file.setFileName(listmap.get(i).get("fileName"));
					file.setUrl(listmap.get(i).get("url"));
					file.setUploadTime(new Date());
				}
			}
			map = iTractProjectDataTransfer.dataTransfer(file, createTime, biaoDuanId, userAccount, id, files);
		} catch (IOException e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		} catch (AuditException e) {
			map.put("msg", e.getMessage());
		} catch (Exception e) {
			map.put("msg", PropertiesGetValue.getContextProperty("data.error.message"));
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("{'isSuccess':'" + map.get("isSuccess") + "','msg':'" + map.get("msg") + "','id':'" + map.get("id") + "'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加交接清单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addprojoin")
	@ResponseBody
	public Map<String, Object> addprojoin(HttpServletRequest request) {
		
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			
			//定义集合
			List<TractDataTurnList> list=new ArrayList<TractDataTurnList>();
			//获取参数
			String biaoDuanId=request.getParameter("biaoDuanId");
			String[] xuhaos=request.getParameterValues("xuhao");
			String[] datanames=request.getParameterValues("dataname");
			String[] scoress=request.getParameterValues("scores");
			String[] pagenumbers=request.getParameterValues("pagenumber");
			String[] paginations=request.getParameterValues("pagination");
	        int len=xuhaos.length;
	        for(int i=1;i<=len;i++){
	        	//实例化对象
	        	TractDataTurnList dataturn=new TractDataTurnList();
	        	dataturn.setId(AuditStringUtils.getUUID());
	        	dataturn.setBiaoDuanId(biaoDuanId);
	        	dataturn.setXuhao(Integer.parseInt(xuhaos[i-1]));
	        	//判断序号
	        	if(Integer.parseInt(xuhaos[i-1])<=4){
	        		dataturn.setBigtype("1");
	        	}
	        	if(Integer.parseInt(xuhaos[i-1])>4 && Integer.parseInt(xuhaos[i-1])<=10){
	        		dataturn.setBigtype("2");
	        	}
	        	if(Integer.parseInt(xuhaos[i-1])>10 && Integer.parseInt(xuhaos[i-1])<=23){
	        		dataturn.setBigtype("3");
	        	}
	        	if(Integer.parseInt(xuhaos[i-1])>23){
	        		dataturn.setBigtype("4");
	        		if(Integer.parseInt(xuhaos[i-1])>23 && Integer.parseInt(xuhaos[i-1])<=30){
	        			dataturn.setSmalltype("1");
	        		}
	        		if(Integer.parseInt(xuhaos[i-1])>30 && Integer.parseInt(xuhaos[i-1])<=50){
	        			dataturn.setSmalltype("1");
	        		}
	        		if(Integer.parseInt(xuhaos[i-1])>50 && Integer.parseInt(xuhaos[i-1])<=55){
	        			dataturn.setSmalltype("3");
	        		}
	        	}
	        	String have=request.getParameter("have"+i);
	        	dataturn.setHave(have);
	        	dataturn.setDataname(datanames[i-1]);
	        	if(AuditStringUtils.isNotEmpty(scoress[i-1])){
	        		dataturn.setScores(scoress[i-1]);
	        	}else{
	        		dataturn.setScores("0");
	        	}
	        	if(AuditStringUtils.isNotEmpty(pagenumbers[i-1])){
	        		dataturn.setPagenumber(pagenumbers[i-1]);
	        	}else{
	        		dataturn.setPagenumber("0");
	        	}
	        	if(AuditStringUtils.isNotEmpty(paginations[i-1])){
	        		dataturn.setPagination(paginations[i-1]);
	        	}else{
	        		dataturn.setPagination("0");
	        	}
	        	list.add(dataturn);
	        }
		    Integer row=iTractProjectDataTransfer.addDataTurn(list);
			if(row>0)
			{
				map.put("success", "success");
				map.put("msg", "操作成功");
			}else{
				map.put("success", "fail");
				map.put("msg", "操作失败");
			}	

		} catch (Exception e) {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 导出word
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/outprojoin")
	public void outprojoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = request.getSession().getServletContext().getRealPath("/upload/template");
		Map<String, String> map = new HashMap<String, String>();
		//获取参数
		String key=request.getParameter("key");
		//查询标段名称
		ProejctBiaoDuanInfo proejctBiaoDuanInfo =iTractProjectCreateService.getProejctBiaoDuanInfo(key);
		map.put("projectName",proejctBiaoDuanInfo.getBiaoDuanName());
		//查询
		List<TractDataTurnList> list=iTractProjectDataTransfer.findDataTurn(key);
		if(null!=list && list.size()!=0){
			for(TractDataTurnList dataturn: list){
				if("0".equals(dataturn.getHave()))
				{	
				    map.put("a"+dataturn.getXuhao().toString()+"a", "无");
				}else{
					
					map.put("a"+dataturn.getXuhao().toString()+"a", "有");

				}
				map.put("b"+dataturn.getXuhao().toString()+"b", dataturn.getScores());
				map.put("c"+dataturn.getXuhao().toString()+"c", dataturn.getPagenumber());
				map.put("d"+dataturn.getXuhao().toString()+"d", dataturn.getPagination());
			}
		}
		
		String filePath = auditDocumentComponent.writeWord(url, "跟踪审计相关资料移交留存目录.doc", map);
		String fileName = ""; // 文件名，输出到用户的下载对话框
		if (filePath.lastIndexOf("/") > 0) {
			fileName = new String(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()).getBytes("GB2312"), "ISO8859_1");
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = new String(filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length()).getBytes("GB2312"), "ISO8859_1");
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(filePath));
			if (null != fs) {
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * (non-Javadoc) 2013-7-28
	 * 
	 * @see com.audit.controller.IControllerBase#add(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> add(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-28
	 * 
	 * @see com.audit.controller.IControllerBase#update(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> update(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-28
	 * 
	 * @see com.audit.controller.IControllerBase#delete(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-28
	 * 
	 * @see com.audit.controller.IControllerBase#destroy(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, Object> destroy(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
