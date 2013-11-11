/**
 * 
 */
package com.audit.service.common;

import java.util.List;

import com.audit.entity.project.Problems;
import com.audit.entity.search.SearchProjectBaseInfoResult;

/**
 * @author dengyong
 *
 */
public interface ICommonPorjectExcelService {

	
	/**
	 * 查询未完成相关项目审计信息
	 * @return
	 */
	public List<SearchProjectBaseInfoResult> findProjectBaseInfo(SearchProjectBaseInfoResult pro);
	
	/**
	 * 查询已完成相关项目审计信息
	 * @return
	 */
	public List<SearchProjectBaseInfoResult> findProjectFinishBaseInfo(SearchProjectBaseInfoResult pro);
	
	
	/**
	 * 根据主审id查询存在问题
	 * @param id
	 * @return
	 */
	public List<Problems>   findProblemsById(String id);
}
