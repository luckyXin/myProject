package com.audit.service.project.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.component.IWorkFlowComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.DataJoinList;
import com.audit.entity.project.DataPreBaseWordInfo;
import com.audit.entity.project.DataWord;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.Employee;
import com.audit.entity.project.EmployeeArrangeRelate;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.project.ProTractMonthFileInfo;
import com.audit.entity.project.ResultClassAuditlookproject;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.project.TractArrangeProjectInfo;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.ProjectOwner;
import com.audit.service.project.IProAuditMoenyService;
import com.audit.service.project.IProjectDatePreService;
import com.audit.service.project.ISingleProjectArrangeService;
import com.audit.service.staff.IIntermediaryagencyService;
import com.audit.service.staff.IProjectOwnerService;

/**
 * @author dengyong
 * 
 *         资料预审接口实现类
 */
public class ProjectDatePreServiceImpl implements IProjectDatePreService {

	/**
	 * sqlMap操作DAO
	 */
	private IbatisCommonDAO ibatisCommonDAO = null;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	@Autowired
	private IProAuditMoenyService proAuditMoenyService;

	@Autowired
	private ISingleProjectArrangeService iSingleProjectArrangeService;

	/**
	 * @param ibatisCommonDAO the ibatisCommonDAO to set
	 */
	public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO) {
		this.ibatisCommonDAO = ibatisCommonDAO;
	}

	/**
	 * 分页查询资料预审信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param datapre 资料预审对象
	 * @return
	 */
	@Override
	public GridDataModel<Datapreinfo> find(Integer page, Integer pagesize, String filed, String order,
			Datapreinfo datapre) {
		GridDataModel<Datapreinfo> gm = null;
		try {
			datapre.setFiled(filed);
			datapre.setSort(order);
			List<Datapreinfo> list = null;
			list = ibatisCommonDAO.executeForObjectList("selectDatapreinfopage", datapre, pagesize * (page - 1),
					pagesize);
			/*
			 * if (null != list && list.size() != 0) { List<Datapreinfo> subs =
			 * new ArrayList<Datapreinfo>(); for (Datapreinfo dataprojoin :
			 * list) { // 查询下面的子项目 List<Datapreinfo> subProjoinlist =
			 * ibatisCommonDAO.executeForObjectList(
			 * "selectDatapreinfochildpage", dataprojoin); if
			 * (subProjoinlist.size() != 0) { for (Datapreinfo subprojin :
			 * subProjoinlist) { subprojin.set_parentId(dataprojoin.getId());
			 * subs.add(subprojin); } } } // 添加子项目 list.addAll(subs); } else {
			 * list =
			 * ibatisCommonDAO.executeForObjectList("selectpidDatapreinfopage",
			 * datapre, pagesize * (page - 1), pagesize); } if (null != list &&
			 * list.size() != 0) { for (Datapreinfo data : list) { if
			 * (AuditStringUtils.isNotEmpty(data.getAudittype())) { Dictionary
			 * dic = dictionaryService.finddicbyid(data.getAudittype());
			 * data.setAudittype(dic.getDictionaryname()); } } }
			 */
			// 查询复核工程师
			if (null != list && list.size() != 0) {
				for (Datapreinfo data : list) {
					//判断是否自审
					if(AuditStringUtils.isNotEmpty(data.getIsMySelfState())){
						//自审
						if("1".equals(data.getIsMySelfState())){
							data.setInterauditmoney(data.getEmpauditMoney());
						}
					}
					String employes = "";
					List<EditUser> listuser = iSingleProjectArrangeService.findGovernmentEmpUserName(data
							.getArrangeid());
					if (null != listuser && listuser.size() != 0) {
						for (EditUser u : listuser) {
							employes += u.getUsername() + ",";
						}
					}
					if (AuditStringUtils.isNotEmpty(employes)) {
						employes = employes.substring(0, employes.length() - 1);
					}
					data.setEmployees(employes);

				}
			}

			Integer count = ibatisCommonDAO.executeForObject("selectDatapreinfocount", datapre, Integer.class);
			gm = new GridDataModel<Datapreinfo>();
			gm.setRows(list);
			gm.setTotal(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 分页查询资料预审对应的资料文件
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param file 资料对象
	 * @return
	 */
	public GridDataModel<FileBelongRelate> findfile(Integer page, Integer pagesize, String filed, String order,
			FileBelongRelate file) {
		GridDataModel<FileBelongRelate> gm = null;
		try {
			file.setFiled(filed);
			file.setSort(order);
			List<FileBelongRelate> list = ibatisCommonDAO.executeForObjectList("selectFileBelongRelatepage", file,
					pagesize * (page - 1), pagesize);
			Integer count = ibatisCommonDAO.executeForObject("selectFileBelongRelatecount", file.getBelongToId(),
					Integer.class);
			gm = new GridDataModel<FileBelongRelate>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 跟踪资料预审id查询文件数目
	 * 
	 * @param datapreId
	 * @return
	 */
	public Integer findFileBelongRelate(String datapreId) {
		Integer count = 0;
		count = ibatisCommonDAO.executeForObject("selectdataprefileshumu", datapreId, Integer.class);
		return count;
	}

	/**
	 * 查询立项文号是否存在
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> findbyid(String projectNo, String id) {
		Map<String, Object> map = null;
		// 获取条数
		Integer count = 0;

		if (AuditStringUtils.isNotEmpty(id)) {
			Datapreinfo data = new Datapreinfo();
			data.setProjectNo(projectNo);
			data.setId(id);
			count = ibatisCommonDAO.executeForObject("selectisupdatehaveprojectno", data, Integer.class);
		} else {
			count = ibatisCommonDAO.executeForObject("selectishaveprojectno", projectNo, Integer.class);
		}
		// 判断
		map = new HashMap<String, Object>();
		if (count > 0) {
			map.put("ishave", "yes");
		} else {
			map.put("ishave", "no");
		}
		return map;
	}

	/**
	 * 查询项目名称是否存在
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> findbyname(String projectName, String id) {
		Map<String, Object> map = null;
		// 获取条数
		Integer count = 0;

		if (AuditStringUtils.isNotEmpty(id)) {
			Datapreinfo data = new Datapreinfo();
			data.setProjectName(projectName);
			data.setId(id);
			count = ibatisCommonDAO.executeForObject("selectisupdatehaveprojectName", data, Integer.class);
		} else {
			count = ibatisCommonDAO.executeForObject("selectishaveprojectName", projectName, Integer.class);
		}
		// 判断
		map = new HashMap<String, Object>();
		if (count > 0) {
			map.put("ishave", "yes");
		} else {
			map.put("ishave", "no");
		}
		return map;
	}

	/**
	 * 增加预审资料
	 * 
	 * @return
	 */
	@Override
	public boolean add(Datapreinfo data, List<FileBelongRelate> listfile, User user) throws Exception {
		boolean falg = false;
		Integer id = ibatisCommonDAO.executeForObject("selectdatapremaxid", null, Integer.class);
		if (null == id) {
			id = 0;
		}
		// 设置id
		data.setId(AuditStringUtils.getID(CommonConstant.STR_DATAPRE, id, 3));
		int row = ibatisCommonDAO.executeInsert("adddatapre", data);
		if (row > 0) {

			if (null != listfile && listfile.size() != 0) {
				for (FileBelongRelate file : listfile) {
					file.setId(AuditStringUtils.getUUID());
					file.setBelongToId(AuditStringUtils.getID(CommonConstant.STR_DATAPRE, id, 3));
					// 设置状态表示预审资料阶段录入
					file.setState(CommonConstant.FILEDATAPRESTAE);
					row = ibatisCommonDAO.executeInsert("addfile", file);
				}
			}
			// 判断是否提交
			if (1 == data.getIsconfirmSubmit()) {
				// 开启流程
				String userAccount = "";
				if (null != user) {
					userAccount = user.getUserAccount();
				}
				iWorkFlowComponent.createWorkFlow(AuditStringUtils.getID(CommonConstant.STR_DATAPRE, id, 3),
						userAccount, PropertiesGetValue.getContextProperty("Flow.DatePreState"), data.getState()
								.toString());
				// 增加结算审计费用
				proAuditMoenyService.addauditmoeny(data);
			}
			falg = true;
		}
		return falg;
	}

	/**
	 * 修改预审资料
	 * 
	 * @return
	 */
	@Override
	public boolean update(Datapreinfo data, List<FileBelongRelate> listfile, User user, String updatestate)
			throws Exception {
		boolean falg = false;
		int row = ibatisCommonDAO.executeUpdate("updatedatapre", data);
		if (row > 0) {
			for (FileBelongRelate file : listfile) {
				file.setId(AuditStringUtils.getUUID());
				file.setBelongToId(data.getId());
				// 设置状态表示预审资料阶段录入
				file.setState(CommonConstant.FILEDATAPRESTAE);
				row = ibatisCommonDAO.executeInsert("addfile", file);
			}
			if ("0".equals(updatestate)) {
				// 判断是否提交
				if (1 == data.getIsconfirmSubmit()) {
					// 开启流程
					String userAccount = "";
					if (null != user) {
						userAccount = user.getUserAccount();
					}
					// 如果是跟踪审计的场合
					if (AuditStringUtils.equals(PropertiesGetValue.getContextProperty("dictionary.type.tractType"),
							data.getAudittype())) {
						try {
							String str = ibatisCommonDAO.executeForObject("getTractProjectBiaoDuanIdByName",
									data.getProjectName(), String.class);
							if (!AuditStringUtils.isNotEmpty(str)) {
								return false;
							}
							// 获取标段安排信息
							TractArrangeProjectInfo tractArrangeProjectInfo = ibatisCommonDAO.executeForObject(
									"selectTractArrangeProjectInfo", str, TractArrangeProjectInfo.class);
							if (tractArrangeProjectInfo == null) {
								return false;
							}
							// 获取单项目安排关联的政府雇员
							List<String> govenmentEmployees = ibatisCommonDAO.executeForObjectList(
									"getAllEmployeeNameByArrangeId", tractArrangeProjectInfo.getId());
							tractArrangeProjectInfo.setGovernmentEmployeeName(AuditStringUtils
									.addString(govenmentEmployees));
							// 政府工程师获取
							List<Employee> govenmentEmployeeList = ibatisCommonDAO.executeForObjectList(
									"getGovenmentEmployeeByProejctArrangeId", tractArrangeProjectInfo.getId());
							List<String> govenmentEmployeeId = new ArrayList<String>();
							for (Employee employee : govenmentEmployeeList) {
								govenmentEmployeeId.add(employee.getId());
							}
							tractArrangeProjectInfo.setGovernmentEmployee(govenmentEmployeeId);

							// 添加安排信息
							Integer id = ibatisCommonDAO.executeForObject("getSingleProjectMaxId", null, Integer.class);
							if (id == null) {
								id = 0;
							}
							SingleProjectArrange singleProjectArrange = new SingleProjectArrange();
							singleProjectArrange.setId(AuditStringUtils.getID(
									CommonConstant.STR_SINGLEPROJECTARRANGEPRIMARYKEY, id, 3));
							singleProjectArrange.setDatapreId(data.getId());
							singleProjectArrange.setMainAuditId(tractArrangeProjectInfo.getMainAuditId());
							ibatisCommonDAO.executeInsert("addSingleProjectArrange", singleProjectArrange);

							// 加入政府工程师审批关联
							for (String governmentEmployee : govenmentEmployeeId) {
								EmployeeArrangeRelate employeeArrangeRelate = new EmployeeArrangeRelate();
								employeeArrangeRelate.setId(AuditStringUtils.getUUID());
								employeeArrangeRelate.setGovernmentEmployeeId(governmentEmployee);
								employeeArrangeRelate.setProjectArrangeId(singleProjectArrange.getId());
								ibatisCommonDAO.executeInsert("addEmployeeArrangeRelete", employeeArrangeRelate);
							}
						} catch (Exception e) {
							return false;
						}
					}
					iWorkFlowComponent.createWorkFlow(data.getId(), userAccount,
							PropertiesGetValue.getContextProperty("Flow.DatePreState"), data.getState().toString());
					// 增加结算审计费用
					proAuditMoenyService.addauditmoeny(data);
				}
			}
			falg = true;
		}
		return falg;
	}

	/**
	 * 根据id查询资料预审对象
	 * 
	 * @param id
	 * @return
	 */
	public Datapreinfo findtoid(String id) {
		Datapreinfo data = null;
		try {
			data = ibatisCommonDAO.executeForObject("selectdataprebyid", id, Datapreinfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 查询项目信息对象
	 * 
	 * @param id
	 * @return
	 */
	public DataPreBaseWordInfo findObject(String id) {
		// 定义项目信息对象
		DataPreBaseWordInfo data = null;
		try {
			// 查询项目对象信息
			data = ibatisCommonDAO.executeForObject("selectdatapreObject", id, DataPreBaseWordInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 删除资料预审文件删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deletefile(String id, HttpServletRequest request) throws Exception {
		boolean falg = false;
		FileBelongRelate file = ibatisCommonDAO.executeForObject("selectdataprefilebyid", id, FileBelongRelate.class);
		int row = ibatisCommonDAO.executeDelete("deleteFile", id);
		if (row > 0) {
			// 删除文件
			AuditStringUtils.deletefile(file.getUrl(), request);
			falg = true;
		} else {
			// 删除跟踪审计月报文件
			FileBelongRelate file1 = ibatisCommonDAO.executeForObject("getMonthFile", id, FileBelongRelate.class);
			int row1 = ibatisCommonDAO.executeDelete("deleteMonthFile", id);
			if (row1 > 0) {
				// 删除文件
				AuditStringUtils.deletefile(file1.getUrl(), request);
				falg = true;
			} else {
				// 删除变更签证文件
				FileBelongRelate file2 = ibatisCommonDAO.executeForObject("getChangeCardFile", id,
						FileBelongRelate.class);
				int row2 = ibatisCommonDAO.executeDelete("deleteChangeCardFile", id);
				if (row2 > 0) {
					// 删除文件
					AuditStringUtils.deletefile(file2.getUrl(), request);
					falg = true;
				}
			}
		}
		return falg;
	}

	/**
	 * 删除模板文件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deletetempfile(String id, HttpServletRequest request) throws Exception {
		boolean falg = false;

		// 查询模板对象
		ProTractMonthFileInfo file = ibatisCommonDAO.executeForObject("selectMonthFileObject", id,
				ProTractMonthFileInfo.class);
		int row = ibatisCommonDAO.executeDelete("delMonthtempfile", id);
		if (row > 0) {
			// 删除文件
			AuditStringUtils.deletefile(file.getFileurl(), request);
			falg = true;
		}
		return falg;
	}

	/**
	 * 删除资料预审信息
	 * 
	 * @return
	 */
	public Map<String, Object> delete(String id, User user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Datapreinfo data = new Datapreinfo();
		data.setId(id);
		data.setState(CommonConstant.COMM_DELETE);
		int row = ibatisCommonDAO.executeUpdate("deletedatapre", data);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", PropertiesGetValue.getContextProperty("datajoinlist.del.success"));
		} else {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("datajoinlist.del.fail"));
		}
		/*
		 * if (row > 0) { // 关闭流程 // 开启流程 String userAccount = ""; if (null !=
		 * user) { userAccount = user.getUserAccount(); }
		 * iWorkFlowComponent.createWorkFlow(data.getId(), userAccount,
		 * PropertiesGetValue.getContextProperty("Flow.DatePreState"),
		 * data.getState().toString()); map.put("success", "success");
		 * map.put("msg",
		 * PropertiesGetValue.getContextProperty("datajoinlist.del.success")); }
		 * else { map.put("success", "fail"); map.put("msg",
		 * PropertiesGetValue.getContextProperty("datajoinlist.del.fail")); }
		 */
		return map;
	}

	/**
	 * 按资料预审id查询对象
	 */
	public DataJoinList finddatajoinlist(String datapreId) {
		DataJoinList datajoin = null;
		try {
			datajoin = ibatisCommonDAO.executeForObject("selectdatajoinlistbyid", datapreId, DataJoinList.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datajoin;
	}

	/**
	 * 增加资料交接单
	 * 
	 * @return
	 */
	public Map<String, Object> addprojoin(DataJoinList datajoin) throws Exception {
		Map<String, Object> map = null;

		int row = ibatisCommonDAO.executeInsert("adddatajoinlist", datajoin);
		// 判断
		map = new HashMap<String, Object>();
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", PropertiesGetValue.getContextProperty("datajoinlist.add.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("datajoinlist.add.fail"));
		}
		return map;
	}

	/**
	 * 修改资料交接单
	 * 
	 * @return
	 */
	public Map<String, Object> updateprojoin(DataJoinList datajoin) throws Exception {
		Map<String, Object> map = null;

		int row = ibatisCommonDAO.executeUpdate("updatedatajoinlist", datajoin);
		// 判断
		map = new HashMap<String, Object>();
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", PropertiesGetValue.getContextProperty("datajoinlist.update.success"));
		} else {
			map.put("msg", PropertiesGetValue.getContextProperty("datajoinlist.update.fail"));
		}
		return map;
	}

	/**
	 * 按政府交办查询资料预审中的施工企业
	 * 
	 * @param id
	 * @return
	 */
	public List<Datapreinfo> findConstructId(String id) {
		List<Datapreinfo> list = ibatisCommonDAO.executeForObjectList("selectConstructIdcreateWord", id);
		return list;
	}

	/**
	 * 查询政府交办项目审核类型
	 * 
	 * @param id
	 * @return
	 */
	public List<Datapreinfo> findproaudittype(String id) {
		List<Datapreinfo> list = ibatisCommonDAO.executeForObjectList("selectifaudittype", id);
		return list;
	}

	/**
	 * 按政府交办查询资料预审中工程名称
	 * 
	 * @param id
	 * @return
	 */
	public List<Datapreinfo> findProjectName(DataWord dw) {
		List<Datapreinfo> list = ibatisCommonDAO.executeForObjectList("selectdatapreproNameWord", dw);
		return list;
	}

	/**
	 * 根据项目安排id查询项目基本信息
	 * 
	 * @return
	 */
	public List<ResultClassAuditlookproject> findprobase(String id) {
		List<ResultClassAuditlookproject> list = ibatisCommonDAO.executeForObjectList("selectauditprojectbaseinfo", id);
		if (null != list && list.size() != 0) {
			for (ResultClassAuditlookproject project : list) {
				if (AuditStringUtils.isNotEmpty(project.getProownerid())) {
					ProjectOwner p = iProjectOwnerService.getProjectOwner(project.getProownerid());
					project.setProownerid(p.getOwnerName());
				}
				if (AuditStringUtils.isNotEmpty(project.getIntermediaryId())) {
					Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(project
							.getIntermediaryId());
					project.setIntermediaryId(in.getIntermediaryname());
				}

				project.setIntermediarySendTime(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediarySendTime()));
				project.setIntermediaryAuditTime(AuditStringUtils.getDatetoyyyyMMdd(project.getIntermediaryAuditTime()));
			}
		}
		return list;
	}

	/**
	 * 分页查询子项目信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> findsubproject(Integer page, Integer pagesize, String filed,
			String order, String arrangeId) {
		GridDataModel<ResultClassAuditlookproject> gm = null;
		try {
			ResultClassAuditlookproject pro = new ResultClassAuditlookproject();
			pro.setFiled(filed);
			pro.setSort(order);
			pro.setArrangeId(arrangeId);
			List<ResultClassAuditlookproject> list = null;
			list = ibatisCommonDAO.executeForObjectList("selectauditprojectbasepage", pro, pagesize * (page - 1),
					pagesize);
			if (null != list && list.size() != 0) {
				for (ResultClassAuditlookproject subpro : list) {
					if (AuditStringUtils.isNotEmpty(subpro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService.getProjectOwner(subpro.getProownerid());
						subpro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(subpro.getIntermediaryId())) {
						Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(subpro
								.getIntermediaryId());
						subpro.setIntermediaryId(in.getIntermediaryname());
					}

					subpro.setIntermediarySendTime(AuditStringUtils.getDatetoyyyyMMdd(subpro.getIntermediarySendTime()));
					subpro.setIntermediaryAuditTime(AuditStringUtils.getDatetoyyyyMMdd(subpro
							.getIntermediaryAuditTime()));
				}
			}

			Integer count = ibatisCommonDAO.executeForObject("selectauditprojectbasecount", pro, Integer.class);
			gm = new GridDataModel<ResultClassAuditlookproject>();
			gm.setRows(list);
			gm.setTotal(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 分页查询中介子项目信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> selectintersubproject(Integer page, Integer pagesize,
			String filed, String order, String arrangeId) {
		GridDataModel<ResultClassAuditlookproject> gm = null;
		try {
			ResultClassAuditlookproject pro = new ResultClassAuditlookproject();
			pro.setFiled(filed);
			pro.setSort(order);
			pro.setArrangeId(arrangeId);
			List<ResultClassAuditlookproject> list = null;
			list = ibatisCommonDAO.executeForObjectList("selectinterauditprojectbasepage", pro, pagesize * (page - 1),
					pagesize);
			if (null != list && list.size() != 0) {
				for (ResultClassAuditlookproject subpro : list) {
					if (AuditStringUtils.isNotEmpty(subpro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService.getProjectOwner(subpro.getProownerid());
						subpro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(subpro.getIntermediaryId())) {
						Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(subpro
								.getIntermediaryId());
						subpro.setIntermediaryId(in.getIntermediaryname());
					}

					subpro.setIntermediarySendTime(AuditStringUtils.getDatetoyyyyMMdd(subpro.getIntermediarySendTime()));
					subpro.setIntermediaryAuditTime(AuditStringUtils.getDatetoyyyyMMdd(subpro
							.getIntermediaryAuditTime()));
				}
			}

			Integer count = ibatisCommonDAO.executeForObject("selectinterauditprojectbasecount", pro, Integer.class);
			gm = new GridDataModel<ResultClassAuditlookproject>();
			gm.setRows(list);
			gm.setTotal(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 分页查询政府雇员子项目信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 排序字段
	 * @param order 排序方式
	 * @param datapre 安排id
	 * @return
	 */
	public GridDataModel<ResultClassAuditlookproject> selectgoversubproject(Integer page, Integer pagesize,
			String filed, String order, String arrangeId) {
		GridDataModel<ResultClassAuditlookproject> gm = null;
		try {
			ResultClassAuditlookproject pro = new ResultClassAuditlookproject();
			pro.setFiled(filed);
			pro.setSort(order);
			pro.setArrangeId(arrangeId);
			List<ResultClassAuditlookproject> list = null;
			list = ibatisCommonDAO.executeForObjectList("selectgoverauditprojectbasepage", pro, pagesize * (page - 1),
					pagesize);
			if (null != list && list.size() != 0) {
				for (ResultClassAuditlookproject subpro : list) {
					if (AuditStringUtils.isNotEmpty(subpro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService.getProjectOwner(subpro.getProownerid());
						subpro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(subpro.getIntermediaryId())) {
						Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(subpro
								.getIntermediaryId());
						subpro.setIntermediaryId(in.getIntermediaryname());
					}

					subpro.setIntermediarySendTime(AuditStringUtils.getDatetoyyyyMMdd(subpro.getIntermediarySendTime()));
					subpro.setIntermediaryAuditTime(AuditStringUtils.getDatetoyyyyMMdd(subpro
							.getIntermediaryAuditTime()));
				}
			}

			Integer count = ibatisCommonDAO.executeForObject("selectgoverauditprojectbasecount", pro, Integer.class);
			gm = new GridDataModel<ResultClassAuditlookproject>();
			gm.setRows(list);
			gm.setTotal(count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * (non-Javadoc) 2013-7-15
	 * 
	 * @see com.audit.service.project.IProjectDatePreService#findMainAuditSubproject(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public GridDataModel<ResultClassAuditlookproject> findMainAuditSubproject(Integer page, Integer pagesize,
			String filed, String order, String arrangeId) {
		GridDataModel<ResultClassAuditlookproject> gm = null;
		try {
			ResultClassAuditlookproject pro = new ResultClassAuditlookproject();
			pro.setFiled(filed);
			pro.setSort(order);
			pro.setArrangeId(arrangeId);
			List<ResultClassAuditlookproject> list = null;
			list = ibatisCommonDAO.executeForObjectList("selectMainAuditProjectBasePage", pro, pagesize * (page - 1),
					pagesize);
			if (null != list && list.size() != 0) {
				for (ResultClassAuditlookproject subpro : list) {
					if (AuditStringUtils.isNotEmpty(subpro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService.getProjectOwner(subpro.getProownerid());
						subpro.setProownerid(p.getOwnerName());
					}

					if (AuditStringUtils.isNotEmpty(subpro.getIntermediaryId())) {
						Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(subpro
								.getIntermediaryId());
						subpro.setIntermediaryId(in.getIntermediaryname());
					}

					subpro.setIntermediarySendTime(AuditStringUtils.getDatetoyyyyMMdd(subpro.getIntermediarySendTime()));
					subpro.setIntermediaryAuditTime(AuditStringUtils.getDatetoyyyyMMdd(subpro
							.getIntermediaryAuditTime()));
				}
			}

			Integer count = ibatisCommonDAO.executeForObject("selectMainAuditProjectBasePageCount", pro, Integer.class);
			gm = new GridDataModel<ResultClassAuditlookproject>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 通过安排id查询单项目安排信息
	 * 
	 * @param arrageId
	 * @return
	 */
	public SingleProjectArrange findZJByArrangeId(String arrageId) {
		SingleProjectArrange sing = null;
		sing = ibatisCommonDAO.executeForObject("selectzhongjiebyarrangeId", arrageId, SingleProjectArrange.class);
		return sing;
	}

	/**
	 * 录入中介机构
	 * 
	 * @param single
	 * @return
	 * @throws Exception
	 */
	public Integer updateZJByArrangeId(SingleProjectArrange single) throws Exception {
		Integer row = 0;
		row = ibatisCommonDAO.executeUpdate("updateDatapreZj", single);
		return row;
	}
}
