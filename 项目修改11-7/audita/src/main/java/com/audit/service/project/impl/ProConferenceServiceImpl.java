/**
 * 
 */
package com.audit.service.project.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.ProConferenceinfo;
import com.audit.entity.project.ResultClassProConference;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.ProjectOwner;
import com.audit.service.project.IProConferenceService;
import com.audit.service.staff.IIntermediaryagencyService;
import com.audit.service.staff.IProjectOwnerService;
import com.audit.service.system.IUserInfoService;

/**
 * @author Administrator
 * 
 */
public class ProConferenceServiceImpl implements IProConferenceService {
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 分页查询审计项目
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	@Override
	public GridDataModel<ResultClassProConference> find(Integer page, Integer pagesize, String filed, String order,
			String projectName, String proownerid) {
		GridDataModel<ResultClassProConference> gm = null;
		try {
			ResultClassProConference conference = new ResultClassProConference();
			conference.setFiled(filed);
			conference.setSort(order);
			conference.setProjectName(projectName);
			conference.setProownerid(proownerid);
			// 查询
			List<ResultClassProConference> list = ibatisCommonDAO.executeForObjectList("selectdatapreconferencepage",
					conference, pagesize * (page - 1), pagesize);
			if (null != list && list.size() != 0) {
				for (ResultClassProConference pro : list) {

					if (AuditStringUtils.isNotEmpty(pro.getProownerid())) {
						ProjectOwner p = iProjectOwnerService.getProjectOwner(pro.getProownerid());
						pro.setProownerid(p.getOwnerName());
					}
					if (AuditStringUtils.isNotEmpty(pro.getArrangeId())) {
						if (AuditStringUtils.isNotEmpty(pro.getIntermediaryId())) {
							Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(pro
									.getIntermediaryId());
							pro.setIntermediaryId(in.getIntermediaryname());
						}
						if (AuditStringUtils.isNotEmpty(pro.getMainAuditId())) {
							EditUser edituser = userInfoService.findbyid(pro.getMainAuditId());
							pro.setMainAuditId(edituser.getUsername());
						}
						String arrangeId = pro.getArrangeId();
						// 查询政府雇员
						List<User> listuser = ibatisCommonDAO.executeForObjectList("selectbyanrrgebyemp", arrangeId);
						String governmentEmp = "";
						for (User user : listuser) {
							governmentEmp += user.getUsername() + ",";
						}
						if (AuditStringUtils.isNotEmpty(governmentEmp)) {
							governmentEmp = governmentEmp.substring(0, governmentEmp.length() - 1);
						}
						pro.setGovernmentEmp(governmentEmp);

					} else {
						String projectId = pro.getDatapreId();
						// 查询打包项目安排
						ResultClassProConference con = ibatisCommonDAO.executeForObject("selectbydatapreIdpackdata",
								projectId, ResultClassProConference.class);
						if (null != con) {
							if (AuditStringUtils.isNotEmpty(con.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService.getIntermediaryagency(con
										.getIntermediaryId());
								pro.setIntermediaryId(in.getIntermediaryname());
							}
							if (AuditStringUtils.isNotEmpty(con.getMainAuditId())) {
								EditUser edituser = userInfoService.findbyid(con.getMainAuditId());
								pro.setMainAuditId(edituser.getUsername());
							}
							String arrangeId = con.getArrangeId();
							// 查询政府雇员
							List<User> listuser = ibatisCommonDAO
									.executeForObjectList("selectbyanrrgebyemp", arrangeId);
							String governmentEmp = "";
							for (User user : listuser) {
								governmentEmp += user.getUsername() + ",";
							}
							if (AuditStringUtils.isNotEmpty(governmentEmp)) {
								governmentEmp = governmentEmp.substring(0, governmentEmp.length() - 1);
							}
							pro.setGovernmentEmp(governmentEmp);
						}

					}

				}
			}
			Integer count = ibatisCommonDAO.executeForObject("selectdatapreconferencecount", conference, Integer.class);
			gm = new GridDataModel<ResultClassProConference>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 分页查询会议纪要
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	@Override
	public GridDataModel<ProConferenceinfo> findlist(Integer page, Integer pagesize, String filed, String order,
			String datapreId) {
		GridDataModel<ProConferenceinfo> gm = null;
		try {
			ProConferenceinfo confere = new ProConferenceinfo();
			confere.setFiled(filed);
			confere.setSort(order);
			confere.setDatapreId(datapreId);
			List<ProConferenceinfo> list = ibatisCommonDAO.executeForObjectList("selectconferencepage", confere,
					pagesize * (page - 1), pagesize);
			Integer count = ibatisCommonDAO.executeForObject("selectconferencecount", datapreId, Integer.class);
			gm = new GridDataModel<ProConferenceinfo>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 增加会议记录
	 * 
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> add(ProConferenceinfo conference) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 执行增加
		int row = ibatisCommonDAO.executeInsert("addconferenceinfo", conference);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", "保存成功");
		} else {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 修改会议记录
	 * 
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> update(ProConferenceinfo conference) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int row = ibatisCommonDAO.executeUpdate("updateconferenceinfo", conference);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", "修改成功");
		} else {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}

	/**
	 * 查询会议记录对象
	 * 
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public ProConferenceinfo findbyid(String id) {
		ProConferenceinfo con = ibatisCommonDAO.executeForObject("selectbyconferenceId", id, ProConferenceinfo.class);
		return con;
	}

	/**
	 * 删除会议记录
	 * 
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> delete(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int row = ibatisCommonDAO.executeDelete("delconference", id);
		if (row > 0) {
			map.put("success", "success");
			map.put("msg", "删除成功");
		} else {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue.getContextProperty("system.exception"));
		}
		return map;
	}
}
