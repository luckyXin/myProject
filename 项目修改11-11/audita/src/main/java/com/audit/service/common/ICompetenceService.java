package com.audit.service.common;



import java.util.List;



/**
 * 查询权限
 * @author dengyong
 *
 */
public interface ICompetenceService {
	
	/**
	 * 查询权限
	 * @param moduleid
	 * @return
	 */
	public  List<String> find(String moduleid, String userAccount);

}
