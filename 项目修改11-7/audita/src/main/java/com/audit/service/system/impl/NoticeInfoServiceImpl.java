/**
 * 
 */
package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.system.NoticeInfo;
import com.audit.service.system.INoticeInfoService;

/**
 * @author User
 *
 */
public class NoticeInfoServiceImpl implements INoticeInfoService{
   
	 /**
     * sqlMap操作DAO
     */
    private IbatisCommonDAO ibatisCommonDAO = null;

    /**
     * @param ibatisCommonDAO the ibatisCommonDAO to set
     */
    public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO)
    {
        this.ibatisCommonDAO = ibatisCommonDAO;
    }
    
    /**
	 * 分页查询公告信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<NoticeInfo> find(Integer page,Integer pagesize,String name,String order){
		NoticeInfo notice=new NoticeInfo();
    	GridDataModel<NoticeInfo> gm=null;
    	try
    	{
    		notice.setFiled(name);
    		notice.setSort(order);
			List<NoticeInfo> list=ibatisCommonDAO.executeForObjectList("selectAllNoticepage",notice,pagesize*(page-1),pagesize);
			Integer count=ibatisCommonDAO.executeForObject("selectNoticeAllcount",notice,Integer.class);
			gm=new GridDataModel<NoticeInfo>();
			gm.setRows(list);
			gm.setTotal(count);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return gm;
	}
	
	/**
	 * 增加公告信息
	 * @return
	 */
	public Map<String, Object>  add(NoticeInfo notice) throws Exception{
		Map<String, Object> map=null;
		notice.setId(AuditStringUtils.getUUID());
    	int row=ibatisCommonDAO.executeInsert("addnotice", notice);
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("notice.add.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("notice.add.fail"));
    	}
    	return map;
		
	}
	
	
	/**
	 * 修改公告信息
	 * @return
	 */
	public Map<String, Object>  update(NoticeInfo notice) throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updatenotice", notice);
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
	 *删除公告信息
	 * @return
	 */
	public Map<String, Object>  delete(String id) throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeDelete("deletenotice", id);
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("notice.del.success"));
    	}else{
    		map.put("msg", PropertiesGetValue.getContextProperty("notice.del.fail"));
    	}
    	return map;
	}
	
	
	/**
	 * 查询公告对象
	 * @return
	 */
	public NoticeInfo  findbyid(String id){
		NoticeInfo notice=null;
		try{
			notice=ibatisCommonDAO.executeForObject("selectnoticeobject",id, NoticeInfo.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return notice;
	}
	
	/**
	 * 查询前5条公告信息
	 * @return
	 */
	public List<NoticeInfo> findlist(){
		List<NoticeInfo> list=ibatisCommonDAO.executeForObjectList("selecttopdata",null);
		return list;
	}
}
