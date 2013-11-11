package com.audit.entity.staff;



import java.io.Serializable;

/**
 * 联系信息对象
 * @author dengyong
 *
 */
public class Link implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;//id
	
	private String referid;//引用
	
	private String linkname;//联系人姓名
	
	private String linktellphone;//联系人电话
	
	private String number;   //第几联系人

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferid() {
		return referid;
	}

	public void setReferid(String referid) {
		this.referid = referid;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public String getLinktellphone() {
		return linktellphone;
	}

	public void setLinktellphone(String linktellphone) {
		this.linktellphone = linktellphone;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
    
	
	

}
