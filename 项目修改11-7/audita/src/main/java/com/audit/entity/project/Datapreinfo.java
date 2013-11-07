/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.audit.entity.Common;

/**资料预审
 * @author dengyong
 *
 */
public class Datapreinfo extends Common implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;             //id
	
	private String datapreno;      //工程编号
	
	private String reportNo;        //报告文号
	
	private String projectNo;      //立项文号唯一
	
	private BigDecimal auditMoney;    //批复金额
	
	private Date  projectTime;    //立项时间
	
	private String projectName;   //项目名称
	
	private String proownerid;   //项目业主id
	
	private String ownerName;  //项目业主名称
	
	
	private String proownerlink;  //项目业主联系人
	
	private String proownertelphone;//项目业主联系电话
	
	
	private String pid;          //父id
	
	private String constructId;   //施工企业id
	
	private BigDecimal zjMoney;   //直接费用
	
	private BigDecimal zbMoney;    //中标价
	
	private String htmoney;    //合同价
	
	private String zhanliemoney;   //其他暂列金额
	
	private String constructName;  //施工企业名称
	
	private String constructlink;  //施工企业联系人
	
	private String constructtelphone; //施工企业联系人电话
	
	private String audittype;  ///审计类型
	
	private BigDecimal sentAmount;  //送审金额
	
	private Date datapretime;   //资料预审时间
	
	private Integer isExpedited;  //是否加急 1表示是0表示否
	
	private String datapreopinion; //资料预审意见
	
	private String datapreOperate; //资料预审人
	
	
	
	private Integer state;    //状态
	
	private Integer constructstate;   //状态
	
	private Integer isconfirmSubmit;//是否确认提交 1表示提交0表示未提交
	
	private Integer isArrangement;//是否被安排 1表示安排0表示未安排
	
	
	private String budgetInfo;    //概算文号 
	
	private String   budgetTotalMoney;              //概算总金额
	
	private String budgetDirectMoney;         //概算工程直接费用 
	
	private String budgetUpdateTime;      //概算批改时间
	
	private String budgetInfoFile;      //概算附件
	
	private String _parentId;    //父资料id
	
	private String beginTime;     //预审开始时间
	
	private String endTime;         //预审结束时间
	
	
	/**
	 * 安排id
	 */
	private String arrangeid;
	
	/**
	 * 主审
	 */
	private String mainAuditId;
	
	/**
	 * 事务所
	 */
	private String intermediaryId;
	
	/**
	 * 复核工程师
	 */
	private String employees;
	
	
	/**
	 * 中介审定金额
	 */
	private String interauditmoney;
	
	/**
	 * 中介审减金额
	 */
	private String intercutmoney;
	
	
	/**
	 * 中介审减率
	 */
	private String intercutmoneylv;
	
	/**
	 * 雇员审定金额
	 */
	private String empauditMoney;
	
	
	/**
	 * 雇员审减金额
	 */
	private String reduceMoney;
	
	
	/**
	 * 雇员审减金率
	 */
	private String reduceMoneylv;
	
	/**
	 * 是否自审
	 */
	private String isMySelfState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	

	public BigDecimal getAuditMoney() {
		return auditMoney;
	}

	public void setAuditMoney(BigDecimal auditMoney) {
		this.auditMoney = auditMoney;
	}

	public Date getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(Date projectTime) {
		this.projectTime = projectTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProownerid() {
		return proownerid;
	}

	public void setProownerid(String proownerid) {
		this.proownerid = proownerid;
	}

	

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getProownerlink() {
		return proownerlink;
	}

	public void setProownerlink(String proownerlink) {
		this.proownerlink = proownerlink;
	}

	public String getProownertelphone() {
		return proownertelphone;
	}

	public void setProownertelphone(String proownertelphone) {
		this.proownertelphone = proownertelphone;
	}

	public String getConstructId() {
		return constructId;
	}

	public void setConstructId(String constructId) {
		this.constructId = constructId;
	}

	public String getConstructName() {
		return constructName;
	}

	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}

	public String getConstructlink() {
		return constructlink;
	}

	public void setConstructlink(String constructlink) {
		this.constructlink = constructlink;
	}

	public String getConstructtelphone() {
		return constructtelphone;
	}

	public void setConstructtelphone(String constructtelphone) {
		this.constructtelphone = constructtelphone;
	}

	public String getAudittype() {
		return audittype;
	}

	public void setAudittype(String audittype) {
		this.audittype = audittype;
	}

	

	public BigDecimal getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(BigDecimal sentAmount) {
		this.sentAmount = sentAmount;
	}

	public Date getDatapretime() {
		return datapretime;
	}

	public void setDatapretime(Date datapretime) {
		this.datapretime = datapretime;
	}

	public Integer getIsExpedited() {
		return isExpedited;
	}

	public void setIsExpedited(Integer isExpedited) {
		this.isExpedited = isExpedited;
	}

	public String getDatapreopinion() {
		return datapreopinion;
	}

	public void setDatapreopinion(String datapreopinion) {
		this.datapreopinion = datapreopinion;
	}

	public String getDatapreOperate() {
		return datapreOperate;
	}

	public void setDatapreOperate(String datapreOperate) {
		this.datapreOperate = datapreOperate;
	}

	

	public Integer getIsconfirmSubmit() {
		return isconfirmSubmit;
	}

	public void setIsconfirmSubmit(Integer isconfirmSubmit) {
		this.isconfirmSubmit = isconfirmSubmit;
	}

	public Integer getIsArrangement() {
		return isArrangement;
	}

	public void setIsArrangement(Integer isArrangement) {
		this.isArrangement = isArrangement;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String parentId) {
		_parentId = parentId;
	}

	public Integer getConstructstate() {
		return constructstate;
	}

	public void setConstructstate(Integer constructstate) {
		this.constructstate = constructstate;
	}

	/**
	 * @return the zjMoney
	 */
	public BigDecimal getZjMoney() {
		return zjMoney;
	}

	/**
	 * @param zjMoney the zjMoney to set
	 */
	public void setZjMoney(BigDecimal zjMoney) {
		this.zjMoney = zjMoney;
	}

	/**
	 * @return the zbMoney
	 */
	public BigDecimal getZbMoney() {
		return zbMoney;
	}

	/**
	 * @param zbMoney the zbMoney to set
	 */
	public void setZbMoney(BigDecimal zbMoney) {
		this.zbMoney = zbMoney;
	}

	/**
	 * @return the datapreno
	 */
	public String getDatapreno() {
		return datapreno;
	}

	/**
	 * @param datapreno the datapreno to set
	 */
	public void setDatapreno(String datapreno) {
		this.datapreno = datapreno;
	}

	/**
	 * @return the htmoney
	 */
	public String getHtmoney() {
		return htmoney;
	}

	/**
	 * @param htmoney the htmoney to set
	 */
	public void setHtmoney(String htmoney) {
		this.htmoney = htmoney;
	}

	/**
	 * @return the zhanliemoney
	 */
	public String getZhanliemoney() {
		return zhanliemoney;
	}

	/**
	 * @param zhanliemoney the zhanliemoney to set
	 */
	public void setZhanliemoney(String zhanliemoney) {
		this.zhanliemoney = zhanliemoney;
	}

	public String getBudgetInfo() {
		return budgetInfo;
	}

	public void setBudgetInfo(String budgetInfo) {
		this.budgetInfo = budgetInfo;
	}

	public String getBudgetTotalMoney() {
		return budgetTotalMoney;
	}

	public void setBudgetTotalMoney(String budgetTotalMoney) {
		this.budgetTotalMoney = budgetTotalMoney;
	}

	public String getBudgetDirectMoney() {
		return budgetDirectMoney;
	}

	public void setBudgetDirectMoney(String budgetDirectMoney) {
		this.budgetDirectMoney = budgetDirectMoney;
	}

	public String getBudgetUpdateTime() {
		return budgetUpdateTime;
	}

	public void setBudgetUpdateTime(String budgetUpdateTime) {
		this.budgetUpdateTime = budgetUpdateTime;
	}

	public String getBudgetInfoFile() {
		return budgetInfoFile;
	}

	public void setBudgetInfoFile(String budgetInfoFile) {
		this.budgetInfoFile = budgetInfoFile;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the reportNo
	 */
	public String getReportNo() {
		return reportNo;
	}

	/**
	 * @param reportNo the reportNo to set
	 */
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getMainAuditId() {
		return mainAuditId;
	}

	public void setMainAuditId(String mainAuditId) {
		this.mainAuditId = mainAuditId;
	}

	public String getIntermediaryId() {
		return intermediaryId;
	}

	public void setIntermediaryId(String intermediaryId) {
		this.intermediaryId = intermediaryId;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

	public String getInterauditmoney() {
		return interauditmoney;
	}

	public void setInterauditmoney(String interauditmoney) {
		this.interauditmoney = interauditmoney;
	}

	public String getIntercutmoney() {
		return intercutmoney;
	}

	public void setIntercutmoney(String intercutmoney) {
		this.intercutmoney = intercutmoney;
	}

	public String getIntercutmoneylv() {
		return intercutmoneylv;
	}

	public void setIntercutmoneylv(String intercutmoneylv) {
		this.intercutmoneylv = intercutmoneylv;
	}

	public String getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(String reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getReduceMoneylv() {
		return reduceMoneylv;
	}

	public void setReduceMoneylv(String reduceMoneylv) {
		this.reduceMoneylv = reduceMoneylv;
	}

	public String getArrangeid() {
		return arrangeid;
	}

	public void setArrangeid(String arrangeid) {
		this.arrangeid = arrangeid;
	}

	public String getEmpauditMoney() {
		return empauditMoney;
	}

	public void setEmpauditMoney(String empauditMoney) {
		this.empauditMoney = empauditMoney;
	}

	public String getIsMySelfState() {
		return isMySelfState;
	}

	public void setIsMySelfState(String isMySelfState) {
		this.isMySelfState = isMySelfState;
	}

	
	
	

}
