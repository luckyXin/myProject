package com.audit.entity;

import java.io.Serializable;
import java.util.List;

public class JsonMenu implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1307077154398119906L;

	private String id;
	private String text;
	private String iconCls;
	private String url;
	private boolean checked;
	private List<JsonMenu> children;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<JsonMenu> getChildren() {
		return children;
	}

	public void setChildren(List<JsonMenu> children) {
		this.children = children;
	}

}
