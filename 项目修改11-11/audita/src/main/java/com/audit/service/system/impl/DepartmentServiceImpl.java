package com.audit.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.audit.common.AuditStringUtils;
import com.audit.common.CommonConstant;
import com.audit.common.GridDataModel;
import com.audit.common.PropertiesGetValue;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.Department;
import com.audit.entity.EditUser;
import com.audit.service.system.IDepartmentService;

/**
 * 机构业务层
 * @author dengyong
 *
 */
public class DepartmentServiceImpl implements IDepartmentService{
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
    
    @Override
    public String findbypid(String id){
    	
		// 定义返回的json字符串
 		String strJson = "";
 		try {		
				StringBuilder pstrJson1=new StringBuilder();
				pstrJson1.append("[");
				pstrJson1.append("{").append("'deprtId':'").append("").append("',").
				append("'name':'").append("").append("',").append("'nodes':[]},");
				this.finddeprtmentId("0",pstrJson1);
				strJson=pstrJson1.toString();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return strJson;
    	
    }
    /**
	 * 循环单位下面的所有部门
	 * dengyong
	 * @param pstrJson字符串
	 */
	 public void finddeprtmentId(String id,StringBuilder pstrJson) {
		 List<Department> deprtallment = null;
			try {
				deprtallment = ibatisCommonDAO.executeForObjectList("selectdeptbypid", id);
				for(int i = 0; i < deprtallment.size(); i++){
						pstrJson.append("{").append("'deprtId':'").append(deprtallment.get(i).getId().toString()).append("',").
						append("'name':'").append(deprtallment.get(i).getDeptname()).append("',").append("'nodes':[");
						finddeprtmentId(deprtallment.get(i).getId().toString(), pstrJson);
						pstrJson.append("},");
				}
				if(pstrJson.toString().endsWith(",")) {
					pstrJson = pstrJson.replace(pstrJson.length() - 1, pstrJson.length(), "").append("]");
				} else {
					pstrJson.append("]");
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
	  }
	 
	 /**
	 * 分页查询机构信息
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name  排序字段
	 * @param order 排序方式
	 * @param id 机构id
	 * @return
	 */
	@Override
	public GridDataModel<Department> find(Integer page,Integer pagesize,String name,String order,String id){
		Department dept=new Department();
		GridDataModel<Department> gm=null;
		try
		{
			dept.setFiled(name);
			dept.setSort(order);
			//判断
			if(AuditStringUtils.isNotEmpty(id))
			{
				dept.setId(id);
			}	
			List<Department> list=ibatisCommonDAO.executeForObjectList("selectAllDeptpage",dept,pagesize*(page-1),pagesize);
			Integer count=ibatisCommonDAO.executeForObject("selectdeptAllcount",dept,Integer.class);
			gm=new GridDataModel<Department>();
			gm.setRows(list);
			gm.setTotal(count);
		}catch(Exception e){
			e.printStackTrace();
		}
		return gm;
	}

	/**
	 * 按id查询子级机构
	 * @param id
	 * @return
	 */
	 @Override
	public List<Department> findbychildid(String id){
		 List<Department> deprtallment = null;
			try {
				deprtallment = ibatisCommonDAO.executeForObjectList("selectdeptbypid", id);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return deprtallment;
	}
	 
	/**
	* 添加机构
	* @param dept
	* @return
	*/
	 @Override
	public Map<String, Object> add(Department dept)throws Exception{
			Map<String, Object> map=null;
	    	//获取字典id
	    	Integer id=ibatisCommonDAO.executeForObject("selectdeptmaxid",null,Integer.class);
	    	if(null==id){
	    		id=0;
	    	}
	    	//设置字典id
	    	dept.setId(AuditStringUtils.getID(CommonConstant.STR_DEPTPRIMARYKEY, id, 3));
	    	int row=ibatisCommonDAO.executeInsert("adddept", dept);
	    	//判断用户是否增加成功
	    	map=new HashMap<String, Object>();
	    	if(row>0){
	    		map.put("success", "success");
	    		map.put("msg", PropertiesGetValue.getContextProperty("dept.add.success"));
	    	}else{
	    		map.put("success", "fail");
	    		map.put("msg", PropertiesGetValue.getContextProperty("dept.add.fail"));
	    	}
	    	return map;
	}
	 
	 /**
	  * 根据id查询部门对象
	  * @param id
	  * @return
	  */
	public  Department  findbyid(String id){
		Department dept=null;
		try {
			dept=ibatisCommonDAO.executeForObject("selectdeptobject",id, Department.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dept;
	}
		
	/**
	* 修改机构
	* @param dic
	* @return
	*/
	public Map<String, Object> update(Department dept)throws Exception{
		Map<String, Object> map=null;
    	int row=ibatisCommonDAO.executeUpdate("updatedept", dept);
    	//判断用户是否修改成功
    	map=new HashMap<String, Object>();
    	if(row>0){
    		map.put("success", "success");
    		map.put("msg", PropertiesGetValue.getContextProperty("dept.update.success"));
    	}else{
    		map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("dept.update.fail"));
    	}
    	return map;
	}
	
	/**
	 * 删除机构
	 * @param id 机构id
	 * @return
	 */
	public Map<String, Object> delete(String id)throws Exception{
		Map<String, Object> map=null;
		map=new HashMap<String, Object>();
	    List<Department>	deprtallment = ibatisCommonDAO.executeForObjectList("selectdeptbypid", id);
	    if(null!=deprtallment && deprtallment.size()!=0)
	    {
	    	map.put("success", "fail");
    		map.put("msg", PropertiesGetValue.getContextProperty("dept.del.title"));
	    }else{
	    	//执行删除
			int row=ibatisCommonDAO.executeDelete("deletedept", id);
			//判断是否执行成功
			if(row>0){
				//修改表中相应对应的部门
				List<EditUser> listuser=ibatisCommonDAO.executeForObjectList("finduserdept",id);
				for(EditUser user:listuser){
					//修改
					row=ibatisCommonDAO.executeUpdate("updateuserdept", user.getId());
				}
	    		map.put("success", "success");
	    		map.put("msg", PropertiesGetValue.getContextProperty("dept.del.success"));
	    	}else{
	    		map.put("success", "fail");
	    		map.put("msg", PropertiesGetValue.getContextProperty("dept.del.fail"));
	    	}
	    }	
    	return map;
	}
}
