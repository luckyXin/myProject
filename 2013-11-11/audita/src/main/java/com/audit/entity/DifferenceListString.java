/**
 * 两个String List的差分
 */
package com.audit.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author xinDeng
 */
public class DifferenceListString implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6706479565890721810L;
	
	/**
	 * 删除的String
	 */
	private List<String> deleteString;
	
	/**
	 * 增加的String
	 */
	private List<String> addString;

	/**
	 * @return the deleteString
	 */
	public List<String> getDeleteString() {
		return deleteString;
	}

	/**
	 * @param deleteString the deleteString to set
	 */
	public void setDeleteString(List<String> deleteString) {
		this.deleteString = deleteString;
	}

	/**
	 * @return the addString
	 */
	public List<String> getAddString() {
		return addString;
	}

	/**
	 * @param addString the addString to set
	 */
	public void setAddString(List<String> addString) {
		this.addString = addString;
	}

}
