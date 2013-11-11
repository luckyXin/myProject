/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.util.Date;

import com.audit.entity.Common;

/**
 * @author dengyong 会议纪要
 *
 */
public class ProConferenceinfo extends Common implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;   //id
	
	private String datapreId; //资料预审项目id
	
	private Date conferencetime;//会议时间
	
	private String conferenceaddress; //会议地点
	
	private String conferenunit;   //参会单位
	
	private String conferenpeople;  //参会人员
	
	private String conferentitle;  //会议议题
	
	private String mainpeople;   //会议主持人
	
	private String conferencontent;  //会议内容

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
	 * @return the datapreId
	 */
	public String getDatapreId() {
		return datapreId;
	}

	/**
	 * @param datapreId the datapreId to set
	 */
	public void setDatapreId(String datapreId) {
		this.datapreId = datapreId;
	}

	/**
	 * @return the conferencetime
	 */
	public Date getConferencetime() {
		return conferencetime;
	}

	/**
	 * @param conferencetime the conferencetime to set
	 */
	public void setConferencetime(Date conferencetime) {
		this.conferencetime = conferencetime;
	}

	/**
	 * @return the conferenceaddress
	 */
	public String getConferenceaddress() {
		return conferenceaddress;
	}

	/**
	 * @param conferenceaddress the conferenceaddress to set
	 */
	public void setConferenceaddress(String conferenceaddress) {
		this.conferenceaddress = conferenceaddress;
	}

	/**
	 * @return the conferenunit
	 */
	public String getConferenunit() {
		return conferenunit;
	}

	/**
	 * @param conferenunit the conferenunit to set
	 */
	public void setConferenunit(String conferenunit) {
		this.conferenunit = conferenunit;
	}

	/**
	 * @return the conferentitle
	 */
	public String getConferentitle() {
		return conferentitle;
	}

	/**
	 * @param conferentitle the conferentitle to set
	 */
	public void setConferentitle(String conferentitle) {
		this.conferentitle = conferentitle;
	}

	/**
	 * @return the mainpeople
	 */
	public String getMainpeople() {
		return mainpeople;
	}

	/**
	 * @param mainpeople the mainpeople to set
	 */
	public void setMainpeople(String mainpeople) {
		this.mainpeople = mainpeople;
	}

	/**
	 * @return the conferencontent
	 */
	public String getConferencontent() {
		return conferencontent;
	}

	/**
	 * @param conferencontent the conferencontent to set
	 */
	public void setConferencontent(String conferencontent) {
		this.conferencontent = conferencontent;
	}

	/**
	 * @return the conferenpeople
	 */
	public String getConferenpeople() {
		return conferenpeople;
	}

	/**
	 * @param conferenpeople the conferenpeople to set
	 */
	public void setConferenpeople(String conferenpeople) {
		this.conferenpeople = conferenpeople;
	}
	
	
    
}
