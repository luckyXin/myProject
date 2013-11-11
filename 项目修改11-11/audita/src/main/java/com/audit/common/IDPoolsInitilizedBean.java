package com.audit.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 监听器事件抛出点
 * 
 * @param applicationContext
 * @return
 */
public class IDPoolsInitilizedBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.applicationContext = applicationContext;

		IDPoolsInitilizedEvent event = new IDPoolsInitilizedEvent("IDPoolsInitilized");

		this.applicationContext.publishEvent(event);
	}
}
