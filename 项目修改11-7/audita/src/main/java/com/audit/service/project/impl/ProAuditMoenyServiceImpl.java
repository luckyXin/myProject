/**
 * 
 */
package com.audit.service.project.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.Datapreinfo;
import com.audit.entity.project.ProAuditMoeny;
import com.audit.entity.project.ResultClassProAuditMoney;
import com.audit.service.project.IProAuditMoenyService;

/**
 * @author Administrator
 * 
 */
public class ProAuditMoenyServiceImpl implements IProAuditMoenyService {
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 分页查询审计费用
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	public GridDataModel<ResultClassProAuditMoney> find(Integer page, Integer pagesize, String filed, String order,
			String auditProjectName) {
		GridDataModel<ResultClassProAuditMoney> gm = null;
		try {
			ResultClassProAuditMoney proaudit = new ResultClassProAuditMoney();
			proaudit.setFiled(filed);
			proaudit.setSort(order);
			proaudit.setAuditProjectName(auditProjectName);
			List<ResultClassProAuditMoney> list = null;
			list = ibatisCommonDAO.executeForObjectList("selectproauditmoneypage", proaudit, pagesize * (page - 1),
					pagesize);
			Integer count = ibatisCommonDAO.executeForObject("selectproauditmoneycount", proaudit, Integer.class);
			gm = new GridDataModel<ResultClassProAuditMoney>();
			gm.setRows(list);
			gm.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 查询对象
	 * 
	 * @param datapreId
	 * @return
	 */
	public ProAuditMoeny findbyid(String datapreId) {
		ProAuditMoeny proaudit = null;
		try {
			proaudit = ibatisCommonDAO.executeForObject("getProAuditMoeny", datapreId, ProAuditMoeny.class);
		} catch (Exception e) {

		}
		return proaudit;
	}

	/**
	 * 增加结算审核费用
	 * 
	 * @throws Exception
	 */
	public void addauditmoeny(Datapreinfo data) throws Exception {
		// 查询是否录入费用
		ProAuditMoeny proaudit = this.findbyid(data.getId());
		// 计算基本费用
		BigDecimal basemoney = new BigDecimal(0);
		if (Double.parseDouble(data.getSentAmount().toString()) <= 1000000) {
			BigDecimal sentAmount = data.getSentAmount();
			basemoney = sentAmount.multiply(new BigDecimal(0.005));
		} else if (Double.parseDouble(data.getSentAmount().toString()) <= 5000000) {
			BigDecimal sentAmount = data.getSentAmount();
			basemoney = sentAmount.subtract(new BigDecimal(1000000));
			basemoney = basemoney.multiply(new BigDecimal(0.0048));
			basemoney = basemoney.add(new BigDecimal(5000));
		} else if (Double.parseDouble(data.getSentAmount().toString()) <= 10000000) {
			BigDecimal sentAmount = data.getSentAmount();
			basemoney = sentAmount.subtract(new BigDecimal(5000000));
			basemoney = basemoney.multiply(new BigDecimal(0.0046));
			basemoney = basemoney.add(new BigDecimal(19200));
			basemoney = basemoney.add(new BigDecimal(5000));
		} else if (Double.parseDouble(data.getSentAmount().toString()) <= 50000000) {
			BigDecimal sentAmount = data.getSentAmount();
			basemoney = sentAmount.subtract(new BigDecimal(10000000));
			basemoney = basemoney.multiply(new BigDecimal(0.0044));
			basemoney = basemoney.add(new BigDecimal(19200));
			basemoney = basemoney.add(new BigDecimal(23000));
			basemoney = basemoney.add(new BigDecimal(5000));
		} else if (Double.parseDouble(data.getSentAmount().toString()) <= 100000000) {
			BigDecimal sentAmount = data.getSentAmount();
			basemoney = sentAmount.subtract(new BigDecimal(50000000));
			basemoney = basemoney.multiply(new BigDecimal(0.004));
			basemoney = basemoney.add(new BigDecimal(19200));
			basemoney = basemoney.add(new BigDecimal(23000));
			basemoney = basemoney.add(new BigDecimal(176000));
			basemoney = basemoney.add(new BigDecimal(5000));
		} else if (Double.parseDouble(data.getSentAmount().toString()) > 100000000) {
			BigDecimal sentAmount = data.getSentAmount();
			basemoney = sentAmount.subtract(new BigDecimal(100000000));
			basemoney = basemoney.multiply(new BigDecimal(0.0035));
			basemoney = basemoney.add(new BigDecimal(19200));
			basemoney = basemoney.add(new BigDecimal(23000));
			basemoney = basemoney.add(new BigDecimal(176000));
			basemoney = basemoney.add(new BigDecimal(200000));
			basemoney = basemoney.add(new BigDecimal(5000));
		}
		basemoney = basemoney.multiply(new BigDecimal(0.8));
		// 判断是否复审
		if (AuditStringUtils.isNotEmpty(data.getAudittype())) {
			if (PropertiesGetValue.getContextProperty("reaudittype").equals(data.getAudittype())) {
				basemoney = basemoney.multiply(new BigDecimal(0.6));
			}
		}

		// 保留两位小数
		basemoney = basemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 判断
		if (null == proaudit) {
			proaudit = new ProAuditMoeny();
			proaudit.setId(AuditStringUtils.getUUID());
			proaudit.setDatapreId(data.getId());
			proaudit.setBasemoney(basemoney.toString());
			// 增加
			ibatisCommonDAO.executeInsert("addProAuditMoeny", proaudit);
		} else {
			// 修改
			proaudit.setBasemoney(basemoney.toString());
			ibatisCommonDAO.executeUpdate("updateProAuditMoeny", proaudit);
		}
	}

	/**
	 * 计算效益费用
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void addauditxiaoyimoney(String datapreId, BigDecimal cutmoney) throws Exception {
		// 查询是否录入费用
		ProAuditMoeny proaudit = this.findbyid(datapreId);
		BigDecimal xiaoyimoney = new BigDecimal(0);
		xiaoyimoney = cutmoney.multiply(new BigDecimal(0.03));
		xiaoyimoney = xiaoyimoney.multiply(new BigDecimal(0.8));
		// 保留两位小数
		xiaoyimoney = xiaoyimoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 判断
		if (null == proaudit) {
			proaudit = new ProAuditMoeny();
			proaudit.setId(AuditStringUtils.getUUID());
			proaudit.setDatapreId(datapreId);
			proaudit.setXiaoyimoney(xiaoyimoney.toString());
			// 增加
			ibatisCommonDAO.executeInsert("addProAuditMoeny", proaudit);
		} else {
			// 修改
			proaudit.setXiaoyimoney(xiaoyimoney.toString());
			ibatisCommonDAO.executeUpdate("updateProAuditMoeny", proaudit);
		}
	}
}
