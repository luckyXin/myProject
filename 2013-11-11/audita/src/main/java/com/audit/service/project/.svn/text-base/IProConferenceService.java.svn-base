/**
 * 
 */
package com.audit.service.project;

import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.ProConferenceinfo;
import com.audit.entity.project.ResultClassProConference;

/**
 * @author dengyong 会议记录
 *
 */
public interface IProConferenceService {

	
	/**
	 * 分页查询审计项目
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	public GridDataModel<ResultClassProConference> find(Integer page, Integer pagesize, String filed, String order,String projectName,String proownerid);
	
	
	/**
	 * 分页查询会议纪要
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed 字段
	 * @param order 排序
	 * @return
	 */
	public GridDataModel<ProConferenceinfo> findlist(Integer page, Integer pagesize, String filed, String order,String datapreId);
	
	
	/**
	 * 增加会议记录
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> add(ProConferenceinfo conference)throws Exception;
	
	/**
	 * 修改会议记录
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> update(ProConferenceinfo conference)throws Exception;
	
	/**
	 * 查询会议记录对象
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public ProConferenceinfo findbyid(String id);
	
	/**
	 * 删除会议记录
	 * @param conference
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> delete(String id)throws Exception;
}
