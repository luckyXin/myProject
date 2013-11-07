package com.audit.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Function;
import com.audit.entity.FunctionCompetenceSqlInput;
import com.audit.service.common.ICompetenceService;

/**
 * 查询权限实现类
 * 
 * @author dengyong
 * 
 */
public class CompetenceServiceImpl implements ICompetenceService {
	/**
	 * sqlMap操作DAO
	 */
	private IbatisCommonDAO ibatisCommonDAO = null;

	/**
	 * @param ibatisCommonDAO the ibatisCommonDAO to set
	 */
	public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO) {
		this.ibatisCommonDAO = ibatisCommonDAO;
	}

	/**
	 * 查询权限
	 * 
	 * @param moduleid
	 * @return
	 */
	@Override
	public List<String> find(String moduleid, String userAccount) {
		FunctionCompetenceSqlInput input = new FunctionCompetenceSqlInput();
		input.setMenuId(moduleid);
		input.setUserAccount(userAccount);
		List<String> liststr = null;
		try {
			List<Function> list = ibatisCommonDAO.executeForObjectList("selectCommonCompetence", input);
			if (null != list && list.size() != 0) {
				liststr = new ArrayList<String>();
				for (Function f : list) {
					liststr.add(f.getMethod());
				}
			}
		} catch (Exception e) {

		}
		return liststr;
	}
}
