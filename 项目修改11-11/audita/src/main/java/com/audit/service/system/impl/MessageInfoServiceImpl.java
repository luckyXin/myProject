/**
 * 
 */
package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.project.FileBelongRelate;
import com.audit.entity.system.MessageInfo;
import com.audit.service.system.IMessageInfoService;
import com.audit.service.system.IUserInfoService;

/**
 * @author dengyong 消息实现类
 *
 */
public class MessageInfoServiceImpl implements IMessageInfoService {
   
	
	 /**
     * sqlMap操作DAO
     */
    private IbatisCommonDAO ibatisCommonDAO = null;
    
	@Autowired
	private IUserInfoService userInfoService;

    /**
     * @param ibatisCommonDAO the ibatisCommonDAO to set
     */
    public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO)
    {
        this.ibatisCommonDAO = ibatisCommonDAO;
    }
    
	
	/**
	 * 分页查询消息信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<MessageInfo> find(Integer page,Integer pagesize,String name,String order,MessageInfo message){
    	GridDataModel<MessageInfo> gm=null;
    	try
    	{
    		message.setFiled(name);
    		message.setSort(order);
			List<MessageInfo> list=ibatisCommonDAO.executeForObjectList("selectAllMessagepage",message,pagesize*(page-1),pagesize);
			if(null!=list && list.size()!=0){
				for(MessageInfo message1:list){
					message1.setMessagetime(AuditStringUtils.getDatetoyyyyMMdd(message1.getMessagetime()));
					message1.setSenduser(userInfoService.findbyid(message1.getSenduser()).getUsername());
					message1.setAcceptuser(userInfoService.findbyid(message1.getAcceptuser()).getUsername());
				}
			}
			
			Integer count=ibatisCommonDAO.executeForObject("selectMessageAllcount",message,Integer.class);
			gm=new GridDataModel<MessageInfo>();
			gm.setRows(list);
			gm.setTotal(count);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return gm;
	}
	/**
	 * 增加消息信息
	 * @return
	 */
	public Map<String, Object>  add(MessageInfo message,String userids,List<FileBelongRelate> listfile ) throws Exception{
		Map<String, Object> map=null;
		int row=0;
		String[] userid=userids.split(",");
		for(int i=0;i<userid.length;i++)
		{	message.setId(AuditStringUtils.getUUID());
		    message.setAcceptuser(userid[i].toString());
    	   row=ibatisCommonDAO.executeInsert("addmessage", message);
    	   for(FileBelongRelate file:listfile){
				file.setId(AuditStringUtils.getUUID());
				file.setBelongToId(message.getId());
			    row=ibatisCommonDAO.executeInsert("addfile", file);
			}
		} 
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("message.add.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("message.add.fail"));
    	}
    	return map;
	}
	
	
	/**
	 * 修改消息信息
	 * @return
	 */
	public Map<String, Object>  update(MessageInfo message,String userids) throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updatenotice", message);
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("notice.update.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("notice.update.fail"));
    	}
    	return map;
	}
	
	/**
	 * 查询消息对象
	 * @return
	 */
	public MessageInfo  findbyid(String id){
		MessageInfo message=null;
		try{
			message=ibatisCommonDAO.executeForObject("selectmessageobject",id, MessageInfo.class);
			message.setAcceptusername(userInfoService.findbyid(message.getAcceptuser()).getUsername());
			message.setMessagetime(AuditStringUtils.getDatetoyyyyMMdd(message.getMessagetime()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	 * 修改消息状态为已阅读
	 * @param message
	 * @throws Exception
	 */
	public void updatestate(MessageInfo message)throws Exception{
		//修改状态
	    ibatisCommonDAO.executeUpdate("updatemessagestate", message);
	}
	
	/**
	 *删除消息信息
	 * @return
	 */
	public Map<String, Object>  delete(String id) throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeDelete("deletemessage", id);
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("message.del.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("message.del.fail"));
    	}
    	return map;
	}
	
	/**
	 * 查询消息发送条数
	 * @param message
	 * @return
	 */
	public Integer findSendMessageNum(MessageInfo message){
		Integer count=ibatisCommonDAO.executeForObject("selectsendMessageAllcount",message,Integer.class);
		return count;
	}
	
	/**
	 * 查询消息接收条数
	 * @param message
	 * @return
	 */
	public Integer findjieshouMessageNum(MessageInfo message){
		Integer count=ibatisCommonDAO.executeForObject("selectjieshouMessageAllcount",message,Integer.class);
		return count;
		
	}
	/**
	 * 查询消息附件
	 * @param id
	 * @return
	 */
	public List<FileBelongRelate> findmessagefile(String id){
		FileBelongRelate file=new FileBelongRelate();
		file.setBelongToId(id);
		List<FileBelongRelate> list=ibatisCommonDAO.executeForObjectList("selectFileBelongRelatepage",file);
		return list;
	}
	
	/**
	 * 查询最新前5条信息
	 * @return
	 */
	public List<MessageInfo>  findnowtopmessage(String userid){
		List<MessageInfo> list=ibatisCommonDAO.executeForObjectList("selecttopmessage",userid);
		return list;
	}
	
}
