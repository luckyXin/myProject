/**
 * 
 */
package com.audit.common;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.audit.component.IWorkFlowComponent;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.work.MyNoCompleteWorkInfo;

/**
 * @author User
 */
public class QuartzJob {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO = null;

	@Autowired
	private IWorkFlowComponent iWorkFlowComponent;

	/**
	 * 滞留法制科七天的项目，跳过法制科
	 */
	public void workOne() {
		String date = "";
		// 获取当前时间
		try {
			date = AuditStringUtils.getSystem(AuditStringUtils.DATE_YYYYMMMDD);

			// 获取法制科中滞留的项目
			List<MyNoCompleteWorkInfo> list = ibatisCommonDAO.executeForObjectList("getLegalDeptAllProject", null);

			// 获取超过三天的项目总数
			for (MyNoCompleteWorkInfo myNoCompleteWorkInfo : list) {
				long day = AuditStringUtils.twoDateSubtract(myNoCompleteWorkInfo.getStartTime(),date);
				// 超过七天的项目跳过法制科
				if (day >= 7) {
					iWorkFlowComponent.changeWorkFlow(myNoCompleteWorkInfo.getId(),
							PropertiesGetValue.getContextProperty("Flow.areaLeaderAudit.state"), "system");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 删除缓存文件
	}
}
