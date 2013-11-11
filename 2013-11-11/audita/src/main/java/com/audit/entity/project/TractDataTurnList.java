/**
 * 
 */
package com.audit.entity.project;

/**
 * @author dengyong
 *跟踪审计相关资料移交,留存目录
 */
public class TractDataTurnList {
	
	/**
	 * id
	 */
	private String id;   //id
	
	/**
	 * 标段
	 */
	private String biaoDuanId; 
	
	/**
	 * 序号
	 */
	private Integer xuhao;    
	
	/**
	 * 资料名称
	 */
	private String dataname;    
	
	/**
	 * 有无
	 */
	private String have;         
	
	/**
	 * 份数
	 */
	private String scores;       
	
	/**
	 * 页数
	 */
	private String pagenumber;      
	
	/**
	 * 页码
	 */
	private String pagination;       
	
	/**
	 * 大类型
	 */
	private String bigtype;          
	
	/**
	 * 小类型
	 */
	private String smalltype;         

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

	public Integer getXuhao() {
		return xuhao;
	}

	public void setXuhao(Integer xuhao) {
		this.xuhao = xuhao;
	}

	public String getDataname() {
		return dataname;
	}

	public void setDataname(String dataname) {
		this.dataname = dataname;
	}

	public String getHave() {
		return have;
	}

	public void setHave(String have) {
		this.have = have;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(String pagenumber) {
		this.pagenumber = pagenumber;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getBigtype() {
		return bigtype;
	}

	public void setBigtype(String bigtype) {
		this.bigtype = bigtype;
	}

	public String getSmalltype() {
		return smalltype;
	}

	public void setSmalltype(String smalltype) {
		this.smalltype = smalltype;
	}
	
	
	

}
