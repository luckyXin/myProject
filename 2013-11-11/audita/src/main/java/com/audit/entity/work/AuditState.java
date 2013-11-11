/**
 * 
 */
package com.audit.entity.work;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author dengXin
 */
public class AuditState extends Common implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3167158330218173017L;

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 审计状态名称
	 */
	private String name;
	
	/**
	 * 审计状态值
	 */
	private String value;
	
	/**
	 * 审计操作模块
	 */
	private String module;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}
}
