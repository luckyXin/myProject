/**
 * 
 */
package com.audit.entity.system;

import java.io.Serializable;

/**
 * @author User
 * 
 */
public class TreeMenuResult implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6458227540342246780L;

	private String menuId;

	private String menuName;

	private String menuState;

	private String remark;

	private String _parentId;

	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the menuState
	 */
	public String getMenuState() {
		return menuState;
	}

	/**
	 * @param menuState the menuState to set
	 */
	public void setMenuState(String menuState) {
		this.menuState = menuState;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the _parentId
	 */
	public String get_parentId() {
		return _parentId;
	}

	/**
	 * @param _parentId the _parentId to set
	 */
	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
}
