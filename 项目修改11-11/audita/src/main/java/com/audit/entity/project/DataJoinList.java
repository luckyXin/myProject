/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;

/**
 * 资料交接表
 * @author dengyong
 *
 */
public class DataJoinList implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String datapreId;   //资料id
	
	private String auditapply;   //审计申请、建施双方承诺书、财务收支表
	
	private String projectlixiangfile;	 //工程立项批复文件
	
	private String projectgaisuanfile;   //工程概算审批文件
	
	private String projectconstructfile; //工程施工合同
	
	private String projianlifile;        //工程设计、监理、勘察合同
	
	private String proconstructtuzi;    //工程施工图纸
	
	private String profinishtuzi;        //工程竣工图纸
	
	private String proinvitefile;       //工程招标相关文件
	
	private String procomparisionfile;  //工程比选相关文件
	
	private String designchangetuzi;     //设计变更图纸、设计变更签证单
	
	private String constructgroupdesign;  //施工组织设计
	
	private String hideprolog;          //隐蔽工程记录
	
	private String visadata;           //施工过程中双方签证资料
	
	private String dizilook;           //地质勘探报告
	
	private String profinishsettlement;    //工程竣工结算书
	
	private String ratemeasurementtable;  //安全文明施工措施评价及费率测定表
	
	private String stuffzmfile;           //材料价格证明文件
	
	private String feescertificate;       //施工单位资质证明、规费取费证书
	
	private String probiangenvisaconfirm;  //金牛区国家投资项目工程变更签证确认函
	
	private String advanceforpro;          //建设单位预付工程款、垫付款项明细表
	
	private String proquality;            //工程质量评定表
	
	private String prostartend;           //工程开工、竣工报告
	
	private String quantities;            // 工程量计算表

	/**
	 * @return the quantities
	 */
	public String getQuantities() {
		return quantities;
	}

	/**
	 * @param quantities the quantities to set
	 */
	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}

	public String getDatapreId() {
		return datapreId;
	}

	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

	public String getAuditapply() {
		return auditapply;
	}

	public void setAuditapply(String auditapply) {
		this.auditapply = auditapply;
	}

	public String getProjectlixiangfile() {
		return projectlixiangfile;
	}

	public void setProjectlixiangfile(String projectlixiangfile) {
		this.projectlixiangfile = projectlixiangfile;
	}

	public String getProjectgaisuanfile() {
		return projectgaisuanfile;
	}

	public void setProjectgaisuanfile(String projectgaisuanfile) {
		this.projectgaisuanfile = projectgaisuanfile;
	}

	public String getProjectconstructfile() {
		return projectconstructfile;
	}

	public void setProjectconstructfile(String projectconstructfile) {
		this.projectconstructfile = projectconstructfile;
	}

	public String getProjianlifile() {
		return projianlifile;
	}

	public void setProjianlifile(String projianlifile) {
		this.projianlifile = projianlifile;
	}

	public String getProconstructtuzi() {
		return proconstructtuzi;
	}

	public void setProconstructtuzi(String proconstructtuzi) {
		this.proconstructtuzi = proconstructtuzi;
	}

	public String getProfinishtuzi() {
		return profinishtuzi;
	}

	public void setProfinishtuzi(String profinishtuzi) {
		this.profinishtuzi = profinishtuzi;
	}

	public String getProinvitefile() {
		return proinvitefile;
	}

	public void setProinvitefile(String proinvitefile) {
		this.proinvitefile = proinvitefile;
	}

	public String getProcomparisionfile() {
		return procomparisionfile;
	}

	public void setProcomparisionfile(String procomparisionfile) {
		this.procomparisionfile = procomparisionfile;
	}

	public String getDesignchangetuzi() {
		return designchangetuzi;
	}

	public void setDesignchangetuzi(String designchangetuzi) {
		this.designchangetuzi = designchangetuzi;
	}

	public String getConstructgroupdesign() {
		return constructgroupdesign;
	}

	public void setConstructgroupdesign(String constructgroupdesign) {
		this.constructgroupdesign = constructgroupdesign;
	}

	public String getHideprolog() {
		return hideprolog;
	}

	public void setHideprolog(String hideprolog) {
		this.hideprolog = hideprolog;
	}

	public String getVisadata() {
		return visadata;
	}

	public void setVisadata(String visadata) {
		this.visadata = visadata;
	}

	public String getDizilook() {
		return dizilook;
	}

	public void setDizilook(String dizilook) {
		this.dizilook = dizilook;
	}

	public String getProfinishsettlement() {
		return profinishsettlement;
	}

	public void setProfinishsettlement(String profinishsettlement) {
		this.profinishsettlement = profinishsettlement;
	}

	public String getRatemeasurementtable() {
		return ratemeasurementtable;
	}

	public void setRatemeasurementtable(String ratemeasurementtable) {
		this.ratemeasurementtable = ratemeasurementtable;
	}

	public String getStuffzmfile() {
		return stuffzmfile;
	}

	public void setStuffzmfile(String stuffzmfile) {
		this.stuffzmfile = stuffzmfile;
	}

	public String getFeescertificate() {
		return feescertificate;
	}

	public void setFeescertificate(String feescertificate) {
		this.feescertificate = feescertificate;
	}

	public String getProbiangenvisaconfirm() {
		return probiangenvisaconfirm;
	}

	public void setProbiangenvisaconfirm(String probiangenvisaconfirm) {
		this.probiangenvisaconfirm = probiangenvisaconfirm;
	}

	public String getAdvanceforpro() {
		return advanceforpro;
	}

	public void setAdvanceforpro(String advanceforpro) {
		this.advanceforpro = advanceforpro;
	}

	public String getProquality() {
		return proquality;
	}

	public void setProquality(String proquality) {
		this.proquality = proquality;
	}

	public String getProstartend() {
		return prostartend;
	}

	public void setProstartend(String prostartend) {
		this.prostartend = prostartend;
	}
	
	

	
	
}
