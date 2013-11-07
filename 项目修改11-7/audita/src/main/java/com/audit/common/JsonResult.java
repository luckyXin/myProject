package com.audit.common;
/**
 * 封装消息json
 * @author dengyong
 *
 */
public class JsonResult {
	boolean result;         //判断
	Object json;            //数据
	String message;        //消息
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Object getJson() {
		return json;
	}
	public void setJson(Object json) {
		this.json = json;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
		
}