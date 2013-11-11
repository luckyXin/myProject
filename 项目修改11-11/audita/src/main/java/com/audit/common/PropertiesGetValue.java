package com.audit.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesGetValue extends PropertyPlaceholderConfigurer {

	private static Map<String, Object> propertiesMap;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {

		super.processProperties(beanFactoryToProcess, props);

		propertiesMap = new HashMap<String, Object>();

		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			propertiesMap.put(keyStr, value);
		}
	}

	public static String getContextProperty(String name) {
		String value = "";
		if (propertiesMap.get(name) instanceof String) {
			value = propertiesMap.get(name).toString();
		}
		return value;
	}

}
