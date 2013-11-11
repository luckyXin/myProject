/**
 * 
 */
package com.audit.service.system;

import java.util.List;
import java.util.Map;

import com.audit.common.GridDataModel;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.system.MessageInfo;

/**
 * @author dengyong 消息接口
 *
 */
public interface IMessageInfoService {

	
	/**
	 * 分页查询消息信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<MessageInfo> find(Integer page,Integer pagesize,String name,String order,MessageInfo message);
	
	
	/**
	 * 增加消息信息
	 * @return
	 */
	public Map<String, Object>  add(MessageInfo message,String userids,List<FileBelongRelate> file ) throws Exception;
	
	
	/**
	 * 修改消息状态为已阅读
	 * @param message
	 * @throws Exception
	 */
	public void updatestate(MessageInfo message)throws Exception;
	
	
	/**
	 * 修改消息信息
	 * @return
	 */
	public Map<String, Object>  update(MessageInfo message,String userids) throws Exception;
	
	
	/**
	 * 查询消息对象
	 * @return
	 */
	public MessageInfo  findbyid(String id);
	
	/**
	 *删除消息信息
	 * @return
	 */
	public Map<String, Object>  delete(String id) throws Exception;
	
	/**
	 * 查询消息发送条数
	 * @param message
	 * @return
	 */
	public Integer findSendMessageNum(MessageInfo message);
	
	/**
	 * 查询消息接收条数
	 * @param message
	 * @return
	 */
	public Integer findjieshouMessageNum(MessageInfo message);
	
	
	/**
	 * 查询消息附件
	 * @param id
	 * @return
	 */
	public List<FileBelongRelate> findmessagefile(String id);
	
	
	/**
	 * 查询最新前5条信息
	 * @return
	 */
	public List<MessageInfo>  findnowtopmessage(String userid);
	
}
