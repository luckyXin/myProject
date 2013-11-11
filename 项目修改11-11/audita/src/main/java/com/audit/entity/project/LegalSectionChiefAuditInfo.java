/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * @author User
 *
 */
public class LegalSectionChiefAuditInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 项目基本信息
	 */
	private ArrangeProject arrangeProject;

	/**
	 * 中介基本信息
	 */
	private ProIntermediaryAudit IntermediaryAudit;

	/**
	 * 政府雇员审核基本信息
	 */
	private GovermentEmployeeAudit govermentEmployeeAudit;

	/**
	 * @return the arrangeProject
	 */
	public ArrangeProject getArrangeProject() {
		return arrangeProject;
	}

	/**
	 * @param arrangeProject
	 *            the arrangeProject to set
	 */
	public void setArrangeProject(ArrangeProject arrangeProject) {
		this.arrangeProject = arrangeProject;
	}

	/**
	 * @return the govermentEmployeeAudit
	 */
	public GovermentEmployeeAudit getGovermentEmployeeAudit() {
		return govermentEmployeeAudit;
	}

	/**
	 * @param govermentEmployeeAudit
	 *            the govermentEmployeeAudit to set
	 */
	public void setGovermentEmployeeAudit(
			GovermentEmployeeAudit govermentEmployeeAudit) {
		this.govermentEmployeeAudit = govermentEmployeeAudit;
	}

	/**
	 * @return the intermediaryAudit
	 */
	public ProIntermediaryAudit getIntermediaryAudit() {
		return IntermediaryAudit;
	}

	/**
	 * @param intermediaryAudit
	 *            the intermediaryAudit to set
	 */
	public void setIntermediaryAudit(ProIntermediaryAudit intermediaryAudit) {
		IntermediaryAudit = intermediaryAudit;
	}
}
