package com.audit.entity;

import java.io.Serializable;

/**
 * 动态参数实体类
 * @author dengyong
 *
 */
public class Common implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3741053598865901491L;

	private int pageno;      //当前页数
                              
    private int pagesize;    //每页条数
    
    
    private String filed;       //排序字段
    
    private String  sort;        //排序方式
    
    private String userAccount;  // 用户帐号
    
    private String moduleId;     // 模块ID
    
	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @param pageno the pageno to set
	 */
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * @return the filed
	 */
	public String getFiled() {
		return filed;
	}

	/**
	 * @param filed the filed to set
	 */
	public void setFiled(String filed) {
		this.filed = filed;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the pageno
	 */
	public int getPageno() {
		return pageno;
	}

	/**
	 * @return the pagesize
	 */
	public int getPagesize() {
		return pagesize;
	}
}
