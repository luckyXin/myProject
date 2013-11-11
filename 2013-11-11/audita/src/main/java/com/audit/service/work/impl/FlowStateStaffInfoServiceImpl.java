package com.audit.service.work.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.audit.common.GridDataModel;
import com.audit.dao.IbatisCommonDAO;
import com.audit.entity.work.AuditState;
import com.audit.service.work.IFlowStateStaffInfoService;

/**
 * @author dengXin
 */
@Service
public class FlowStateStaffInfoServiceImpl implements IFlowStateStaffInfoService {

	@Autowired
	private IbatisCommonDAO ibatisCommonDAO = null;

	/**
	 * 分页查询机构信息
	 * 
	 * @param page 当前页数
	 * @param pagesize 每页条数
	 * @param name 排序字段
	 * @param order 排序方式
	 * @return
	 */
	public GridDataModel<AuditState> find(Integer page, Integer pagesize, String name, String order) throws Exception {

		AuditState auditState = new AuditState();
		auditState.setPageno(page);
		auditState.setPagesize(pagesize);
		auditState.setSort(order);
		auditState.setFiled(name);

		// 查询审计状态总数量
		Integer count = ibatisCommonDAO.executeForObject("getFindAuditStateCount", null, Integer.class);

		// 获取审计状态信息
		List<AuditState> list = ibatisCommonDAO.executeForObjectList("findAuditStateInfo", auditState);

		GridDataModel<AuditState> grid = new GridDataModel<AuditState>();
		grid.setRows(list);
		grid.setTotal(count);
		return grid;
	}
}
