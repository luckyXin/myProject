package com.audit.common;

import org.springframework.context.ApplicationEvent;

/**
 * 监听事件定义
 * 
 * @param
 * @return
 */
public class IDPoolsInitilizedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 646140097162842368L;

	public IDPoolsInitilizedEvent(Object source) {
		super(source);
	}

}
