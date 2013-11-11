package com.audit.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.audit.dao.IbatisCommonDAO;

/**
 * IOC容器初始化监听器
 * @param IbatisCommonDAO
 * @return
 */
@SuppressWarnings("rawtypes")
@Service
public class JcIDPoolsInitilizedListener implements ApplicationListener {
	
	@Autowired
    private IbatisCommonDAO ibatisCommonDAO = null;

	
	public IbatisCommonDAO getIbatisCommonDAO() {
		return ibatisCommonDAO;
	}


	public void setIbatisCommonDAO(IbatisCommonDAO ibatisCommonDAO) {
		this.ibatisCommonDAO = ibatisCommonDAO;
	}


	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		
		 if (arg0 instanceof IDPoolsInitilizedEvent) {
			Global.FunctionList = ibatisCommonDAO.executeForObjectList("getFunction", null);	
		 }
	}
}
