/**
 * 
 */
package com.audit.entity.project;

import java.io.Serializable;
import java.util.Date;

import com.audit.entity.Common;

/**
 * @author dengyong
 * 文件
 *
 */
public class FileBelongRelate extends Common  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;   //主键
	
	private String belongToId;  //引用id
	
	private String url;      //文件url
	
	private String fileName;// 文件名称
	
	private String state;   //状态
	
	private Date uploadTime;  //上传时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBelongToId() {
		return belongToId;
	}

	public void setBelongToId(String belongToId) {
		this.belongToId = belongToId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the uploadTime
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	

}
