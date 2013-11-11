/**
 * 
 */
package com.audit.service.project.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.component.IWorkFlowComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.EditUser;
import com.audit.entity.User;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.IntermediaryAudit;
import com.audit.entity.project.PackProjectArrange;
import com.audit.entity.project.ProIntermediaryAudit;
import com.audit.entity.project.ResultClassArrangeInfo;
import com.audit.entity.project.ResultInterAuidt;
import com.audit.entity.project.SectionChiefAuditBaseInfo;
import com.audit.entity.project.SingleProjectArrange;
import com.audit.entity.staff.Intermediaryagency;
import com.audit.entity.staff.Link;
import com.audit.entity.staff.ProjectOwner;
import com.audit.service.project.IIntermediaryAuditService;
import com.audit.service.project.IProAuditMoenyService;
import com.audit.service.staff.IConstructionService;
import com.audit.service.staff.IIntermediaryagencyService;
import com.audit.service.staff.IProjectOwnerService;
import com.audit.service.system.IUserInfoService;

/**
 * @author User
 * 
 */
public class IntermediaryAuditServiceImpl implements IIntermediaryAuditService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	@Autowired
	private IProjectOwnerService iProjectOwnerService;

	@Autowired
	private IIntermediaryagencyService iIntermediaryagencyService;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	@Autowired
	private IConstructionService constructionService;

	@Autowired
	private IProAuditMoenyService proAuditMoenyService;

	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 分页查询中介审核
	 * 
	 * @param page
	 *            当前页数
	 * @param pagesize
	 *            每页条数
	 * @param filed
	 *            字段
	 * @param order
	 *            排序
	 * @return
	 */
	@Override
	public GridDataModel<IntermediaryAudit> find(Integer page,
			Integer pagesize, String filed, String order,
			IntermediaryAudit inter, String arrangerpro) {

		GridDataModel<IntermediaryAudit> gm = null;
		try {
			// 默认查询未录入中介审核
			if (!AuditStringUtils.isNotEmpty(inter.getIsconfirm())) {
				inter.setIsconfirm("0");
			}
			// 判断是否录入
			if ("0".equals(inter.getIsconfirm())) {
				// 全部
				if (!AuditStringUtils.isNotEmpty(arrangerpro)) {
					List<IntermediaryAudit> list = ibatisCommonDAO
							.executeForObjectList(
									"selectIntermediaryAuditnosubmitallpage",
									inter, pagesize * (page - 1), pagesize);
					if (null != list && list.size() != 0) {
						for (IntermediaryAudit interm : list) {
							if (AuditStringUtils.isNotEmpty(interm
									.getProownerid())) {
								ProjectOwner p = iProjectOwnerService
										.getProjectOwner(interm.getProownerid());
								interm.setProownerid(p.getOwnerName());
							}
							if (AuditStringUtils.isNotEmpty(interm
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(interm
												.getIntermediaryId());
								interm.setIntermediaryId(in
										.getIntermediaryname());
							}

							interm.setIntermediarySendTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediarySendTime()));
							interm.setIntermediaryAuditTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediaryAuditTime()));
						}
					}
					Integer count = ibatisCommonDAO.executeForObject(
							"selectIntermediaryAuditnosubmitallcount", inter,
							Integer.class);
					gm = new GridDataModel<IntermediaryAudit>();
					gm.setRows(list);
					gm.setTotal(count);
				}
				// 单项目
				if ("0".equals(arrangerpro)) {
					List<IntermediaryAudit> list = ibatisCommonDAO
							.executeForObjectList(
									"selectIntermediaryAuditpage", inter,
									pagesize * (page - 1), pagesize);
					if (null != list && list.size() != 0) {
						for (IntermediaryAudit interm : list) {
							if (AuditStringUtils.isNotEmpty(interm
									.getProownerid())) {
								ProjectOwner p = iProjectOwnerService
										.getProjectOwner(interm.getProownerid());
								interm.setProownerid(p.getOwnerName());
							}
							if (AuditStringUtils.isNotEmpty(interm
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(interm
												.getIntermediaryId());
								interm.setIntermediaryId(in
										.getIntermediaryname());
							}

							interm.setIntermediarySendTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediarySendTime()));
							interm.setIntermediaryAuditTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediaryAuditTime()));
						}
					}
					Integer count = ibatisCommonDAO.executeForObject(
							"selectIntermediaryAuditcount", inter,
							Integer.class);
					gm = new GridDataModel<IntermediaryAudit>();
					gm.setRows(list);
					gm.setTotal(count);

				}
				// 打包项目
				if ("1".equals(arrangerpro)) {
					List<IntermediaryAudit> list = ibatisCommonDAO
							.executeForObjectList(
									"selectIntermediaryAuditpackpage", inter,
									pagesize * (page - 1), pagesize);
					if (null != list && list.size() != 0) {
						for (IntermediaryAudit interm : list) {

							if (AuditStringUtils.isNotEmpty(interm
									.getProownerid())) {
								ProjectOwner p = iProjectOwnerService
										.getProjectOwner(interm.getProownerid());
								interm.setProownerid(p.getOwnerName());
							}
							if (AuditStringUtils.isNotEmpty(interm
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(interm
												.getIntermediaryId());
								interm.setIntermediaryId(in
										.getIntermediaryname());
							}
							interm.setIntermediarySendTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediarySendTime()));
							interm.setIntermediaryAuditTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediaryAuditTime()));
						}
					}
					Integer count = ibatisCommonDAO.executeForObject(
							"selectIntermediaryAuditpackcount", inter,
							Integer.class);
					gm = new GridDataModel<IntermediaryAudit>();
					gm.setRows(list);
					gm.setTotal(count);
				}
			} else if ("1".equals(inter.getIsconfirm())) {
				// 全部
				if (!AuditStringUtils.isNotEmpty(arrangerpro)) {
					List<IntermediaryAudit> list = ibatisCommonDAO
							.executeForObjectList(
									"selectIntermediaryAuditsubmitallpage",
									inter, pagesize * (page - 1), pagesize);
					if (null != list && list.size() != 0) {
						for (IntermediaryAudit interm : list) {

							if (AuditStringUtils.isNotEmpty(interm
									.getProownerid())) {
								ProjectOwner p = iProjectOwnerService
										.getProjectOwner(interm.getProownerid());
								interm.setProownerid(p.getOwnerName());
							}
							if (AuditStringUtils.isNotEmpty(interm
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(interm
												.getIntermediaryId());
								interm.setIntermediaryId(in
										.getIntermediaryname());
							}
							interm.setIntermediarySendTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediarySendTime()));
							interm.setIntermediaryAuditTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediaryAuditTime()));
						}
					}
					Integer count = ibatisCommonDAO.executeForObject(
							"selectIntermediaryAuditsubmitallcount", inter,
							Integer.class);
					gm = new GridDataModel<IntermediaryAudit>();
					gm.setRows(list);
					gm.setTotal(count);
				}
				// 单项目
				if ("0".equals(arrangerpro)) {
					List<IntermediaryAudit> list = ibatisCommonDAO
							.executeForObjectList(
									"selectIntermediaryAuditsubmitpage", inter,
									pagesize * (page - 1), pagesize);
					if (null != list && list.size() != 0) {
						for (IntermediaryAudit interm : list) {

							if (AuditStringUtils.isNotEmpty(interm
									.getProownerid())) {
								ProjectOwner p = iProjectOwnerService
										.getProjectOwner(interm.getProownerid());
								interm.setProownerid(p.getOwnerName());
							}
							if (AuditStringUtils.isNotEmpty(interm
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(interm
												.getIntermediaryId());
								interm.setIntermediaryId(in
										.getIntermediaryname());
							}
							interm.setIntermediarySendTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediarySendTime()));
							interm.setIntermediaryAuditTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediaryAuditTime()));
						}
					}
					Integer count = ibatisCommonDAO.executeForObject(
							"selectIntermediaryAuditsubmitcount", inter,
							Integer.class);
					gm = new GridDataModel<IntermediaryAudit>();
					gm.setRows(list);
					gm.setTotal(count);
				}
				// 打包项目
				if ("1".equals(arrangerpro)) {
					List<IntermediaryAudit> list = ibatisCommonDAO
							.executeForObjectList(
									"selectIntermediaryAuditsubmitpackpage",
									inter, pagesize * (page - 1), pagesize);
					if (null != list && list.size() != 0) {
						for (IntermediaryAudit interm : list) {

							if (AuditStringUtils.isNotEmpty(interm
									.getProownerid())) {
								ProjectOwner p = iProjectOwnerService
										.getProjectOwner(interm.getProownerid());
								interm.setProownerid(p.getOwnerName());
							}
							if (AuditStringUtils.isNotEmpty(interm
									.getIntermediaryId())) {
								Intermediaryagency in = iIntermediaryagencyService
										.getIntermediaryagency(interm
												.getIntermediaryId());
								interm.setIntermediaryId(in
										.getIntermediaryname());
							}
							interm.setIntermediarySendTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediarySendTime()));
							interm.setIntermediaryAuditTime(AuditStringUtils
									.getDatetoyyyyMMdd(interm
											.getIntermediaryAuditTime()));
						}
					}
					Integer count = ibatisCommonDAO.executeForObject(
							"selectIntermediaryAuditsubmitpackcount", inter,
							Integer.class);
					gm = new GridDataModel<IntermediaryAudit>();
					gm.setRows(list);
					gm.setTotal(count);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 查询安排项目信息
	 * 
	 * @param arrangeId
	 *            安排id
	 * @return
	 */
	public ResultClassArrangeInfo findbyid(String arrangeId) {
		ResultClassArrangeInfo interm = null;
		try {
			interm = ibatisCommonDAO.executeForObject("selectsinglepackbyid",
					arrangeId, ResultClassArrangeInfo.class);
			ProjectOwner p = iProjectOwnerService.getProjectOwner(interm
					.getProownerid());
			Intermediaryagency in = iIntermediaryagencyService
					.getIntermediaryagency(interm.getIntermediaryId());
			if (interm != null) {
				if (p != null) {
					interm.setProownerid(p.getOwnerName());
					interm.setOwnnerId(p.getId());
				}
				if (in != null) {

					interm.setIntermediaryId(in.getIntermediaryname());
					List<Link> listlink = constructionService.findLink(in
							.getId());
					if (null != listlink && listlink.size() != 0) {
						interm.setInterlink(listlink.get(0).getLinkname());
						interm.setIntertelphone(listlink.get(0)
								.getLinktellphone());
					}
				}
				interm.setIntermediarySendTime(AuditStringUtils
						.getDatetoyyyyMMdd(interm.getIntermediarySendTime()));
				interm.setIntermediaryAuditTime(AuditStringUtils
						.getDatetoyyyyMMdd(interm.getIntermediaryAuditTime()));
				interm.setProjectTime(AuditStringUtils.getDatetoyyyyMMdd(interm
						.getProjectTime()));
				if (AuditStringUtils.isNotEmpty(interm.getMainAuditId())) {
					EditUser edituser = userInfoService.findbyid(interm
							.getMainAuditId());
					interm.setMainAuditId(edituser.getUsername());
				}
				// 查询政府雇员
				List<User> listuser = ibatisCommonDAO.executeForObjectList(
						"selectbyanrrgebyemp", arrangeId);
				String governmentEmp = "";
				for (User user : listuser) {
					governmentEmp += user.getUsername() + ",";
				}
				if (AuditStringUtils.isNotEmpty(governmentEmp)) {
					governmentEmp = governmentEmp.substring(0, governmentEmp
							.length() - 1);
				}
				interm.setEmpusers(governmentEmp);
			}
		} catch (Exception e) {

		}
		return interm;
	}

	/**
	 * 查询单项目安排信息
	 * 
	 * @param arrangeId
	 *            资料id
	 * @return
	 */
	public ResultClassArrangeInfo findsingleauditbyid(String datapreId) {
		ResultClassArrangeInfo interm = null;
		try {
			interm = ibatisCommonDAO.executeForObject("selectsingleauditbyid",
					datapreId, ResultClassArrangeInfo.class);
			ProjectOwner p = iProjectOwnerService.getProjectOwner(interm
					.getProownerid());
			Intermediaryagency in = iIntermediaryagencyService
					.getIntermediaryagency(interm.getIntermediaryId());
			interm.setProownerid(p.getOwnerName());
			if (null != in) {
				interm.setIntermediaryId(in.getIntermediaryname());
				List<Link> listlink = constructionService.findLink(in.getId());
				if (null != listlink && listlink.size() != 0) {
					interm.setInterlink(listlink.get(0).getLinkname());
					interm.setIntertelphone(listlink.get(0).getLinktellphone());
				}
			}
			interm.setIntermediarySendTime(AuditStringUtils
					.getDatetoyyyyMMdd(interm.getIntermediarySendTime()));
			interm.setIntermediaryAuditTime(AuditStringUtils
					.getDatetoyyyyMMdd(interm.getIntermediaryAuditTime()));
			interm.setProjectTime(AuditStringUtils.getDatetoyyyyMMdd(interm
					.getProjectTime()));
			if (AuditStringUtils.isNotEmpty(interm.getMainAuditId())) {
				EditUser edituser = userInfoService.findbyid(interm
						.getMainAuditId());
				interm.setMainAuditId(edituser.getUsername());
			}
			// 查询政府雇员
			List<User> listuser = ibatisCommonDAO.executeForObjectList(
					"selectbyanrrgebyemp", interm.getArrangeId());
			String governmentEmp = "";
			for (User user : listuser) {
				governmentEmp += user.getUsername() + ",";
			}
			if (AuditStringUtils.isNotEmpty(governmentEmp)) {
				governmentEmp = governmentEmp.substring(0, governmentEmp
						.length() - 1);
			}
			interm.setEmpusers(governmentEmp);
		} catch (Exception e) {

		}
		return interm;
	}

	/**
	 * 查询打包项目
	 * 
	 * @param datapreId
	 *            资料id
	 * @return
	 */
	public ResultClassArrangeInfo findpackauditbyid(String datapreId) {
		ResultClassArrangeInfo interm = null;
		try {
			interm = ibatisCommonDAO.executeForObject("selectpackauditbyid",
					datapreId, ResultClassArrangeInfo.class);
			ProjectOwner p = iProjectOwnerService.getProjectOwner(interm
					.getProownerid());
			Intermediaryagency in = iIntermediaryagencyService
					.getIntermediaryagency(interm.getIntermediaryId());
			interm.setProownerid(p.getOwnerName());
			interm.setIntermediaryId(in.getIntermediaryname());
			interm.setIntermediarySendTime(AuditStringUtils
					.getDatetoyyyyMMdd(interm.getIntermediarySendTime()));
			interm.setIntermediaryAuditTime(AuditStringUtils
					.getDatetoyyyyMMdd(interm.getIntermediaryAuditTime()));
			interm.setProjectTime(AuditStringUtils.getDatetoyyyyMMdd(interm
					.getProjectTime()));
			if (AuditStringUtils.isNotEmpty(interm.getMainAuditId())) {
				EditUser edituser = userInfoService.findbyid(interm
						.getMainAuditId());
				interm.setMainAuditId(edituser.getUsername());
			}
			// 查询政府雇员
			List<User> listuser = ibatisCommonDAO.executeForObjectList(
					"selectbyanrrgebyemp", interm.getArrangeId());
			String governmentEmp = "";
			for (User user : listuser) {
				governmentEmp += user.getUsername() + ",";
			}
			if (AuditStringUtils.isNotEmpty(governmentEmp)) {
				governmentEmp = governmentEmp.substring(0, governmentEmp
						.length() - 1);
			}
			interm.setEmpusers(governmentEmp);
			return interm;
		} catch (Exception e) {

		}
		return interm;
	}

	/**
	 * 查询打包项目
	 * 
	 * @param arrangeId
	 * @return
	 */
	public ResultClassArrangeInfo findpackbyid(String arrangeId) {

		ResultClassArrangeInfo interm = null;
		try {
			interm = ibatisCommonDAO.executeForObject("selectpackprobyid",
					arrangeId, ResultClassArrangeInfo.class);
			ProjectOwner p = iProjectOwnerService.getProjectOwner(interm
					.getProownerid());

			Intermediaryagency in = iIntermediaryagencyService
					.getIntermediaryagency(interm.getIntermediaryId());
			if (in != null) {
				interm.setIntermediaryId(in.getIntermediaryname());
			}
			if (p != null) {
				interm.setProownerid(p.getOwnerName());
				interm.setOwnnerId(p.getId());
			}
			interm.setIntermediarySendTime(AuditStringUtils
					.getDatetoyyyyMMdd(interm.getIntermediarySendTime()));
			interm.setIntermediaryAuditTime(AuditStringUtils
					.getDatetoyyyyMMdd(interm.getIntermediaryAuditTime()));
			interm.setProjectTime(AuditStringUtils.getDatetoyyyyMMdd(interm
					.getProjectTime()));
			if (AuditStringUtils.isNotEmpty(interm.getMainAuditId())) {
				EditUser edituser = userInfoService.findbyid(interm
						.getMainAuditId());
				if (edituser != null) {
					interm.setMainAuditId(edituser.getUsername());
				}
			}
			// 查询政府雇员
			List<User> listuser = ibatisCommonDAO.executeForObjectList(
					"selectbyanrrgebyemp", arrangeId);
			String governmentEmp = "";
			for (User user : listuser) {
				governmentEmp += user.getUsername() + ",";
			}
			if (AuditStringUtils.isNotEmpty(governmentEmp)) {
				governmentEmp = governmentEmp.substring(0, governmentEmp
						.length() - 1);
			}
			interm.setEmpusers(governmentEmp);
			return interm;
		} catch (Exception e) {

		}
		return interm;
	}

	/**
	 * 查询中介审核对象
	 * 
	 * @param arrangeId
	 * @return
	 */
	public ProIntermediaryAudit findobject(String arrangeId, String datapreId) {
		ProIntermediaryAudit inter = null;
		try {
			if (AuditStringUtils.isNotEmpty(datapreId)) {
				inter = ibatisCommonDAO.executeForObject(
						"selectintermediaryAuditobjectbydatapreId", datapreId,
						ProIntermediaryAudit.class);
			}
			if (AuditStringUtils.isNotEmpty(arrangeId)) {
				inter = ibatisCommonDAO.executeForObject(
						"selectintermediaryAuditobject", arrangeId,
						ProIntermediaryAudit.class);
			}
		} catch (Exception e) {

		}
		return inter;
	}

	/**
	 * 录入中介审核
	 * 
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> add(ProIntermediaryAudit prointer, User user)
			throws Exception {
		Map<String, Object> map = null;
		prointer.setId(AuditStringUtils.getUUID());
		String userAccount = "";
		if (null != user) {
			userAccount = user.getUserAccount();
		}
		prointer.setUserAccount(userAccount);
		int row = ibatisCommonDAO.executeInsert("addIntermediaryAudit",
				prointer);
		// 判断是否增加成功
		map = new HashMap<String, Object>();
		if (row > 0) {
			if (null != prointer.getIsconfirm()) {
				// 判断是否提交
				if (1 == prointer.getIsconfirm()) {
					try {
						// 修改项目安排中中介审核状态
						PackProjectArrange pack = new PackProjectArrange();
						pack.setId(prointer.getArrangeId());
						pack
								.setIntermediaryAuditState(CommonConstant.INTERMEDIARYAUDITOK);
						SingleProjectArrange single = new SingleProjectArrange();
						single.setId(prointer.getArrangeId());
						single
								.setIntermediaryAuditState(CommonConstant.INTERMEDIARYAUDITOK);
						row = ibatisCommonDAO.executeUpdate(
								"updatePackintermediaryState", pack);
						row = ibatisCommonDAO.executeUpdate(
								"updateSingleintermediaryState", single);
					} catch (Exception e) {

					}
					// 修改科长审核状态
					SectionChiefAuditBaseInfo section = this
							.findbysection(prointer.getArrangeId());
					if (null != section) {
						String id = section.getId();
						// 更新科长审批信息状态
						ibatisCommonDAO.executeUpdate(
								"updateCectionChiefAuditInfo", id);
					}
					// 开启流程
					String id = prointer.getArrangeId();
					List<Datapreinfo> list = ibatisCommonDAO
							.executeForObjectList("selectsingeproid", id);
					if (null == list || list.size() == 0) {
						list = ibatisCommonDAO.executeForObjectList(
								"selectpackproid", id);
					}
					if (null != list && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							iWorkFlowComponent
									.changeWorkFlow(
											list.get(i).getId(),
											PropertiesGetValue
													.getContextProperty("Flow.IntermediaryAudit"),
											userAccount);
						}
					}
					// 计算项目的效益费用
					if (null != list && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							proAuditMoenyService.addauditxiaoyimoney(list
									.get(i).getId(), new BigDecimal(prointer
									.getCutmoney().doubleValue()));
						}

					}

				}
			}
			map.put("success", "success");
			map.put("msg", PropertiesGetValue
					.getContextProperty("IntermediaryAudit.add.success"));
		} else {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("IntermediaryAudit.add.fail"));
		}
		return map;
	}

	/**
	 * 修改录入中介审核
	 * 
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> update(ProIntermediaryAudit prointer, User user)
			throws Exception {
		Map<String, Object> map = null;
		int row = ibatisCommonDAO.executeUpdate("updateIntermediaryAudit",
				prointer);
		// 判断是否修改成功
		map = new HashMap<String, Object>();
		if (row > 0) {
			if (null != prointer.getIsconfirm()) {
				// 判断是否提交
				if (1 == prointer.getIsconfirm()) {
					try {
						// 修改项目安排中中介审核状态
						PackProjectArrange pack = new PackProjectArrange();
						pack.setId(prointer.getArrangeId());
						pack
								.setIntermediaryAuditState(CommonConstant.INTERMEDIARYAUDITOK);
						SingleProjectArrange single = new SingleProjectArrange();
						single.setId(prointer.getArrangeId());
						single
								.setIntermediaryAuditState(CommonConstant.INTERMEDIARYAUDITOK);
						row = ibatisCommonDAO.executeUpdate(
								"updatePackintermediaryState", pack);
						row = ibatisCommonDAO.executeUpdate(
								"updateSingleintermediaryState", single);
					} catch (Exception e) {

					}
					// 修改科长审核状态
					SectionChiefAuditBaseInfo section = this
							.findbysection(prointer.getArrangeId());
					if (null != section) {
						String sectionChiefAuditId = section.getId();
						// 更新科长审批信息状态
						ibatisCommonDAO.executeUpdate(
								"updateCectionChiefAuditInfo",
								sectionChiefAuditId);
					}
					// 开启流程
					String userAccount = "";
					if (null != user) {
						userAccount = user.getUserAccount();
					}
					String id = prointer.getArrangeId();
					List<Datapreinfo> list = ibatisCommonDAO
							.executeForObjectList("selectsingeproid", id);
					if (null == list || list.size() == 0) {
						list = ibatisCommonDAO.executeForObjectList(
								"selectpackproid", id);
					}
					if (null != list && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							iWorkFlowComponent
									.changeWorkFlow(
											list.get(i).getId(),
											PropertiesGetValue
													.getContextProperty("Flow.IntermediaryAudit"),
											userAccount);
						}
					}
					// 计算项目的效益费用
					if (null != list && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							proAuditMoenyService.addauditxiaoyimoney(list
									.get(i).getId(), new BigDecimal(prointer
									.getCutmoney().doubleValue()));
						}

					}

				}
			}
			map.put("success", "success");
			map.put("msg", PropertiesGetValue
					.getContextProperty("IntermediaryAudit.add.success"));
		} else {
			map.put("success", "fail");
			map.put("msg", PropertiesGetValue
					.getContextProperty("IntermediaryAudit.add.fail"));
		}
		return map;
	}

	public Integer findissubmitcount(String arrangeId) {
		Integer count = ibatisCommonDAO.executeForObject(
				"selectispackprojectsubmit", arrangeId, Integer.class);
		return count;
	}

	/**
	 * 提交中介审核
	 * 
	 * @param prointer
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addsubmit(ProIntermediaryAudit pro, User user)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询是否可以提交
		Integer count = ibatisCommonDAO.executeForObject(
				"selectisallauditfinish", pro.getArrangeId(), Integer.class);
		List<ProIntermediaryAudit> listpro = this.findauditcutmoney(pro
				.getArrangeId());
		if (null == listpro || listpro.size() == 0) {
			count = 1;
		}
		if (count > 0) {
			map.put("success", "fail");
			map.put("msg", "请审核完全部子项目");
		} else {
			// 修改提交中介审核
			int row = ibatisCommonDAO.executeUpdate("updatesubmitpackpro", pro);
			if (row > 0) {

				if ("1".equals(pro.getIsconfirm().toString())) {
					try {
						// 修改项目安排中中介审核状态
						PackProjectArrange pack = new PackProjectArrange();
						pack.setId(pro.getArrangeId());
						pack
								.setIntermediaryAuditState(CommonConstant.INTERMEDIARYAUDITOK);
						SingleProjectArrange single = new SingleProjectArrange();
						single.setId(pro.getArrangeId());
						single
								.setIntermediaryAuditState(CommonConstant.INTERMEDIARYAUDITOK);
						row = ibatisCommonDAO.executeUpdate(
								"updatePackintermediaryState", pack);
						row = ibatisCommonDAO.executeUpdate(
								"updateSingleintermediaryState", single);
					} catch (Exception e) {

					}
					// 修改科长审核状态
					SectionChiefAuditBaseInfo section = this.findbysection(pro
							.getArrangeId());
					if (null != section) {
						String sectionChiefAuditId = section.getId();
						// 更新科长审批信息状态
						ibatisCommonDAO.executeUpdate(
								"updateCectionChiefAuditInfo",
								sectionChiefAuditId);
					}
					// 开启流程
					String userAccount = "";
					if (null != user) {
						userAccount = user.getUserAccount();
					}
					String id = pro.getArrangeId();
					List<Datapreinfo> list = ibatisCommonDAO
							.executeForObjectList("selectsingeproid", id);
					if (null == list || list.size() == 0) {
						list = ibatisCommonDAO.executeForObjectList(
								"selectpackproid", id);
					}
					if (null != list && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							iWorkFlowComponent
									.changeWorkFlow(
											list.get(i).getId(),
											PropertiesGetValue
													.getContextProperty("Flow.IntermediaryAudit"),
											userAccount);
						}
					}
					// 计算项目的效益费用
					if (null != list && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							// 根据资料id查询中介审核信息
							ProIntermediaryAudit prointer = this.findobject(
									null, list.get(i).getId());
							proAuditMoenyService.addauditxiaoyimoney(list
									.get(i).getId(), new BigDecimal(prointer
									.getCutmoney().doubleValue()));
						}

					}
				}
				map.put("success", "success");
				map.put("msg", "提交成功");
			} else {
				map.put("success", "fail");
				map.put("msg", "提交失败");
			}

		}
		// 判断是否修改成功

		return map;
	}

	/**
	 * 查询科长未同意信息
	 * 
	 * @param arrangeId
	 * @return
	 */
	public SectionChiefAuditBaseInfo findbysection(String arrangeId) {
		SectionChiefAuditBaseInfo con = ibatisCommonDAO.executeForObject(
				"selectsectionauditnopassstate", arrangeId,
				SectionChiefAuditBaseInfo.class);
		return con;
	}

	public ResultInterAuidt findinteraudit(String id) {
		ResultInterAuidt con = ibatisCommonDAO.executeForObject(
				"selectsingleinterauditmoney", id, ResultInterAuidt.class);
		return con;
	}

	/**
	 * 查询资料政府雇员审核
	 * 
	 * @param id
	 * @return
	 */
	public ResultInterAuidt findengaudit(String id) {
		ResultInterAuidt con = ibatisCommonDAO.executeForObject(
				"selectsingleengauditmoney", id, ResultInterAuidt.class);
		return con;
	}

	public List<ProIntermediaryAudit> findauditcutmoney(String id) {
		List<ProIntermediaryAudit> list = ibatisCommonDAO.executeForObjectList(
				"selectauditandcutmoney", id);
		if (null == list || list.size() == 0) {
			return null;
		} else {
			return list;
		}

	}

	/**
	 * 查询资料中介审核信息
	 * 
	 * @param id
	 * @return
	 */
	public List<ResultInterAuidt> findinterpackaudit(String id) {
		List<ResultInterAuidt> list = ibatisCommonDAO.executeForObjectList(
				"selectpackinterauditmoney", id);
		if (null == list || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	/**
	 * 查询资料政府雇员审核
	 * 
	 * @param id
	 * @return
	 */
	public List<ResultInterAuidt> findengpackaudit(String id) {
		List<ResultInterAuidt> list = ibatisCommonDAO.executeForObjectList(
				"selectpackengauditmoney", id);
		if (null == list || list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}
}
