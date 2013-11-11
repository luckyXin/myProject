/**
 * 
 */
package com.audit.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.Problems;
import com.audit.entity.search.SearchProjectBaseInfoResult;
import com.audit.service.common.ICommonPorjectExcelService;

/**
 * @author User
 * 
 */
public class CommonPorjectExcelServiceImpl implements ICommonPorjectExcelService {

	/**
	 * sqlMap操作DAO
	 */
	@Autowired
	private IbatisCommonDAO ibatisCommonDAO;

	/**
	 * 查询相关项目审计信息
	 * 
	 * @return
	 */
	@Override
	public List<SearchProjectBaseInfoResult> findProjectBaseInfo(SearchProjectBaseInfoResult pro) {
		List<SearchProjectBaseInfoResult> list = null;
		list = ibatisCommonDAO.executeForObjectList("selectProjectBaseExcel", pro);
		return list;
	}
	

	/**
	 * 根据主审id查询存在问题
	 * @param id
	 * @return
	 */
	public List<Problems>   findProblemsById(String id){
		List<Problems> list = null;
		list = ibatisCommonDAO.executeForObjectList("selectpromainProblems", id);
		return list;
	}

	/**
	 * 查询已完成相关项目审计信息
	 * 
	 * @return
	 */
	@Override
	public List<SearchProjectBaseInfoResult> findProjectFinishBaseInfo(SearchProjectBaseInfoResult pro) {
		List<SearchProjectBaseInfoResult> list = null;
		list = ibatisCommonDAO.executeForObjectList("selectFinishProjectBaseExcel", pro);
		return list;
	}
}
