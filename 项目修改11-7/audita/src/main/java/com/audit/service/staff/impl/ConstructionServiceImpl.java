package com.audit.service.staff.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.staff.Construction;
import com.audit.entity.staff.Link;
import com.audit.service.staff.IConstructionService;
/**
 * 施工企业业务层实现类
 * @author dengyong
 *
 */
public class ConstructionServiceImpl implements IConstructionService {
   
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
	 * 分页查询施工企业信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param filed  排序字段
	 * @param order 排序方式
	 * @param name 企业名称
	 * @return
	 */
    @Override
	public GridDataModel<Construction> find(Integer page,Integer pagesize,String filed,String order,String name,String state){
		Construction con=new Construction();
		GridDataModel<Construction> gm=null;
		try
		{
			con.setFiled(filed);
			con.setSort(order);
            con.setConstructname(name);
            if(AuditStringUtils.isNotEmpty(state))
            {	
            con.setState(Integer.parseInt(state));
            }
			List<Construction> list=ibatisCommonDAO.executeForObjectList("selectConstructionpage",con,pagesize*(page-1),pagesize);
			Integer count=ibatisCommonDAO.executeForObject("selectconstructcount",con,Integer.class);
			gm=new GridDataModel<Construction>();
			gm.setRows(list);
			gm.setTotal(count);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gm;
	}
	/**
	 * 增加施工企业
	 * @param con 施工企业对象
	 * @param linklist 联系人集合
	 * @return
	 */
    @Override
	public Map<String, Object> add(Construction con,List<Link> linklist)throws Exception{
		Map<String, Object> map=null;
    	//获取id
    	Integer id=ibatisCommonDAO.executeForObject("selectconstructmaxid",null,Integer.class);
    	if(null==id)
    	{
    		id=0;
    	}
    	//设置id
    	con.setId(AuditStringUtils.getID(CommonConstant.STR_CONSTRUCTPRIMARYKEY, id, 3));
    	int row=ibatisCommonDAO.executeInsert("addconstruction", con);
    	//判断用户是否增加成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		//增加联系人
    		for(Link link:linklist){
    			link.setReferid(con.getId());
    			row=ibatisCommonDAO.executeInsert("addLink", link);
    		}
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("construct.add.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("construct.add.fail"));
    	}
    	return map;
	}
	
	/**
	 * 查询联系人集合信息
	 * @param id
	 * @return
	 */
    @Override
	public List<Link> findLink(String id){
    	List<Link> list=ibatisCommonDAO.executeForObjectList("getLinkList",id);
    	return list;
	}
    /**
	 * 查询联系人对象信息
	 * @param id
	 * @return
	 */
    @Override
	public Link findLinkObject(String id){
    	Link link=ibatisCommonDAO.executeForObject("selectlinkbyid", id, Link.class);
    	return link;
    }
	
	/**
	 * 查询施工企业对象
	 * @param id
	 * @return
	 */
    @Override
	public Construction findbyid(String id){
    	
    	Construction con= ibatisCommonDAO.executeForObject("getConstructById", id, Construction.class);
		return con;
	}
    
    /**
	 * 修改施工企业
	 * @param con 施工企业对象
	 * @param linklist 联系人集合
	 * @return
	 */
    @Override
	public Map<String, Object> update(Construction con,List<Link> linklist)throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updateconstruction", con);
    	//判断施工企业是否修改成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		//删除所有联系人
    		row=ibatisCommonDAO.executeDelete("deltelink", con.getId());
    		//增加联系人
    		for(Link link:linklist){
    			link.setReferid(con.getId());
    			row=ibatisCommonDAO.executeInsert("addLink", link);
    		}
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("construct.update.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("construct.update.fail"));
    	}
    	return map;
	}
	
	/**
	 * 删除施工企业
	 * @param id 施工企业id
	 * @return
	 * @throws Exception 
	 */
    @Override
	public Map<String, Object> delete(String id) throws Exception{
    	Map<String, Object> map=null;
    	Construction c=new Construction();
    	c.setId(id);
    	c.setState(CommonConstant.COMM_DELETE);
        Integer row=ibatisCommonDAO.executeDelete("deleteconstruction",c);
        map=new HashMap<String, Object>();
        if(row>0)
        {
        	row=ibatisCommonDAO.executeDelete("deltelink", id);
        	map.put("success", "success");
	    	map.put("msg", PropertiesGetValue.getContextProperty("construct.del.success"));
        	
        }else{
        	map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("construct.del.fail"));
        }	
		return map;
	}
	
	/**
	 * 销毁施工企业
	 * @param con 施工企业对象
	 * @return
	 * @throws Exception 
	 */
    @Override
	public Map<String, Object> destory(Construction con) throws Exception{
    	Map<String, Object> map=null;
        Integer row=ibatisCommonDAO.executeUpdate("destoryconstruction",con);
        map=new HashMap<String, Object>();
        if(row>0)
        {
        	map.put("success", "success");
	    	map.put("msg", PropertiesGetValue.getContextProperty("construct.destory.success"));
        	
        }else{
        	map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("construct.destory.fail"));
        }	
		return map;
	}
}
