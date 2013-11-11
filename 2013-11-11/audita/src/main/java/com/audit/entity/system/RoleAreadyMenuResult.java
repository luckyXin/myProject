/**
 * 
 */
package com.audit.entity.system;

/**
 * @author User
 * 
 */
public class RoleAreadyMenuResult {

	/**
	 * 菜单ID
	 */
	private String id;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单状态
	 */
	private String menuState;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 父项目ID
	 */
	private String _parentId;

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
