package com.audit.entity;

import java.io.Serializable;

public class ComboboxJson implements Serializable{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5350315063933410330L;

	/**
     * 下拉框id
     */
    private String id;

    /**
     * 下拉框文本
     */
    private String text;
    
    /**
     * 是否选中
     */
    private boolean selected;

    /**
     * @return the selected
     */
    public boolean isSelected()
    {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id  the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }
}
