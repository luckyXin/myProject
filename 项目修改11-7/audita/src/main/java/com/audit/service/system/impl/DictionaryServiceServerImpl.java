package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Dictionary;
import com.audit.entity.EditUser;
import com.audit.service.system.IDictionaryService;
/**
 * 字典实现类
 * @author dengyong
 *
 */
public class DictionaryServiceServerImpl implements IDictionaryService {
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
	 * 根据id查询对应字典
	 * @param id
	 * @return
	 */
	public List<Dictionary> findbyid(String id){
		List<Dictionary> list=null;
		try{
			list=ibatisCommonDAO.executeForObjectList("selectdicbyid", id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 分页查询字典信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @param id 字典id
	 * @return
	 */
	public GridDataModel<Dictionary> find(Integer page,Integer pagesize,String name,String order,String id){
		Dictionary dic=new Dictionary();
		GridDataModel<Dictionary> gm=null;
		try
		{
			dic.setFiled(name);
			dic.setSort(order);
			//判断
			if(AuditStringUtils.isNotEmpty(id))
			{
				dic.setId(id);
			}	
			List<Dictionary> list=ibatisCommonDAO.executeForObjectList("selectAllDicpage",dic,pagesize*(page-1),pagesize);
			Integer count=ibatisCommonDAO.executeForObject("selectDicAllcount",dic,Integer.class);
			gm=new GridDataModel<Dictionary>();
			gm.setRows(list);
			gm.setTotal(count);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gm;
	}
	/**
     * 增加字典方法
     */
    @Override
    public Map<String, Object> add(Dictionary dic) throws Exception{
    	Map<String, Object> map=null;
    	//获取字典id
    	Integer id=ibatisCommonDAO.executeForObject("selectdicmaxid",null,Integer.class);
    	if(null==id){
    		id=0;
    	}
    	//设置字典id
    	dic.setId(AuditStringUtils.getID(CommonConstant.STR_DICTIONARYPRIMARYKEY, id, 3));
    	int row=ibatisCommonDAO.executeInsert("adddic", dic);
    	//判断用户是否增加成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.add.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.add.fail"));
    	}
    	return map;
    }
    
    /**
	 * 查询字典对象
	 * @return
	 */
    @Override
	public Dictionary  finddicbyid(String id){
		Dictionary dic=null;
		try{
			dic=ibatisCommonDAO.executeForObject("selectdictionarybyid",id, Dictionary.class);
		}catch(Exception e){
		   e.printStackTrace();	
		}
		return dic;
	}
	
	/**
	 * 修改字典
	 * @param dic
	 * @return
	 */
    @Override
	public Map<String, Object> update(Dictionary dic)throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updatedic", dic);
    	//判断用户是否修改成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.update.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.update.fail"));
    	}
    	return map;
	}
	
	/**
	 * 删除字典
	 * @param id 字典id
	 * @return
	 */
    @Override
	public Map<String, Object> delete(String id)throws Exception{
    	Map<String, Object> map=null;
    	
		Dictionary dic=ibatisCommonDAO.executeForObject("selectdictionarybyid",id, Dictionary.class);
		map=new HashMap<String, Object>();
		//判断是否系统字典
		if("1".equals(dic.getIssystem().toString())){
			map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.del.system"));
		}else{
			//执行删除
			int row=ibatisCommonDAO.executeDelete("deletedic", id);
			//判断是否执行成功
			if(row>0){
				
				//修改表中相应对应的字典
				List<EditUser> listuser=ibatisCommonDAO.executeForObjectList("findusersexdic",id);
				for(EditUser user:listuser){
					//修改
					row=ibatisCommonDAO.executeUpdate("updateusersexdic", user.getId());
				}
				List<EditUser> listtype=ibatisCommonDAO.executeForObjectList("findusertypedic",id);
				for(EditUser usertype:listtype){
					//修改
					row=ibatisCommonDAO.executeUpdate("updateusertypedic", usertype.getId());
				}
	    		map.put("success", "success");
	    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.del.success"));
	    	}else{
	    		map.put("success", "fail");
	    		map.put("msg", PropertiesGetValue.getContextProperty("dictionary.del.fail"));
	    	}
			
		}
    	return map;
	}
    
    /**
	 * 根据字典名称查询字典信息
	 * @param name
	 * @return
	 */
	public Dictionary findbyName(String name){
		
		Dictionary dic=null;
		try{
			dic=ibatisCommonDAO.executeForObject("selectdictionarybyName",name, Dictionary.class);
		}catch(Exception e){
		   e.printStackTrace();	
		}
		return dic;
		
	}
	
}
