/**
 * 
 */
package com.audit.entity.project;

import com.audit.entity.Common;

/**
 * @author dengyong 材料询价
 * 
 */
public class TractProjectDataEnquiry extends Common{
	
	private static final long serialVersionUID = 1L;

	private String id; // id

	private String biaoDuanId; // 标段

	private String datafile; // 材料询价文件
	
	private String dataname;   //材料名称

	private String createuser; // 创建人

	private String createtime; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	public String getDatafile() {
		return datafile;
	}

	public void setDatafile(String datafile) {
		this.datafile = datafile;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDataname() {
		return dataname;
	}

	public void setDataname(String dataname) {
		this.dataname = dataname;
	}
	
	

}
