/**
 * 
 */
package com.audit.entity.system;

import java.io.Serializable;

import com.audit.entity.Common;

/**
 * @author dengyong
 * 消息对象
 *
 */
public class MessageInfo extends Common implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;  
	
	private String messagetitle;  //消息主题
	
	private String messagecontent;  //消息内容
	
	private String messagetime;      //消息时间
	
	private String senduser;        //发件人
	
	private String acceptuser;     //收件人
	
	private String acceptusername;   //收件人姓名
	
	private String messagestate;     //消息阅读状态
	
	private String mehtodtype;          //方式

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessagetitle() {
		return messagetitle;
	}

	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}

	public String getMessagecontent() {
		return messagecontent;
	}

	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	public String getMessagetime() {
		return messagetime;
	}

	public void setMessagetime(String messagetime) {
		this.messagetime = messagetime;
	}

	public String getSenduser() {
		return senduser;
	}

	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}

	public String getAcceptuser() {
		return acceptuser;
	}

	public void setAcceptuser(String acceptuser) {
		this.acceptuser = acceptuser;
	}

	public String getMessagestate() {
		return messagestate;
	}

	public void setMessagestate(String messagestate) {
		this.messagestate = messagestate;
	}

	public String getMehtodtype() {
		return mehtodtype;
	}

	public void setMehtodtype(String mehtodtype) {
		this.mehtodtype = mehtodtype;
	}

	public String getAcceptusername() {
		return acceptusername;
	}

	public void setAcceptusername(String acceptusername) {
		this.acceptusername = acceptusername;
	}

	
	
	

}
