package com.audit.dao.impl;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.audit.dao.IbatisCommonDAO;

public class IbatisCommonDAOImpl extends SqlMapClientDaoSupport
		implements
			IbatisCommonDAO {

	/**
	 * Log
	 */
	private static Log log = LogFactory.getLog(IbatisCommonDAOImpl.class);

	/**
	 * 检索单条数据
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public <E> E executeForObject(String sqlID, Object bindParams, Class clazz) {
		if (log.isDebugEnabled()) {
			log.debug("executeForObject Start.");
		}

		// SqlMap操作模板获取
		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		// 执行检索SQL
		Object obj = sqlMapTemp.queryForObject(sqlID, bindParams);
		if (log.isDebugEnabled() && obj != null) {
			log.debug("Return type:" + obj.getClass().getName());
		}

		E rObj = null;

		try {
			if (clazz != null && obj != null) {
				rObj = (E) clazz.cast(obj);
			}
		} catch (Exception e) {
			log.error("类型转换有误");
			return null;
		}

		if (log.isDebugEnabled()) {
			log.debug("executeForObject End.");
		}
		return rObj;
	}

	/**
	 * 检索单条键值MAP数据
	 */
	@Override
	public Map<String, Object> executeForMap(String sqlID, Object bindParams) {
		if (log.isDebugEnabled()) {
			log.debug("executeForMap Start.");
		}

		Map<String, Object> rObj = this.executeForObject(sqlID, bindParams,
				Map.class);

		if (log.isDebugEnabled()) {
			log.debug("executeForMap End.");
		}

		return rObj;
	}

	/**
	 * 数组类型数据
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public <E> E[] executeForObjectArray(String sqlID, Object bindParams,
			Class clazz) {
		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray Start.");
		}

		if (clazz == null) {
			log.error("无效的不能为空");
			return null;
		}

		// SqlMap操作模板获取
		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		// 检索多条数据
		List<E> list = sqlMapTemp.queryForList(sqlID, bindParams);

		// 转换为数组类型
		E[] retArray = (E[]) Array.newInstance(clazz, list.size());

		try {
			list.toArray(retArray);
		} catch (Exception e) {
			log.error("数组转换有误");
			return null;
		}

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray End.");
		}

		return retArray;
	}

	/**
	 * 指定序列数据检索
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public <E> E[] executeForObjectArray(String sqlID, Object bindParams,
			Class clazz, int beginIndex, int maxCount) {
		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray Start.");
		}

		if (clazz == null) {
			log.error("返回类型不能为空");
			return null;
		}

		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		List<E> list = sqlMapTemp.queryForList(sqlID, bindParams, beginIndex,
				maxCount);

		E[] retArray = (E[]) Array.newInstance(clazz, list.size());
		try {
			list.toArray(retArray);
		} catch (ArrayStoreException e) {
			log.error("数据类型转换有误");
			return null;
		}

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectArray End.");
		}
		return retArray;
	}

	/**
	 * 多条数据检索
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> executeForObjectList(String sqlID, Object bindParams) {
		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList Start.");
		}
		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		List<E> list = sqlMapTemp.queryForList(sqlID, bindParams);

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList End.");
		}
		return list;
	}

	/**
	 * 多条键值数据获取
	 */
	@Override
	public List<Map<String, Object>> executeForMapList(String sqlID,
			Object bindParams) {
		if (log.isDebugEnabled()) {
			log.debug("executeForMapList Start.");
		}

		List<Map<String, Object>> mapList = executeForObjectList(sqlID,
				bindParams);

		if (log.isDebugEnabled()) {
			log.debug("executeForMapList End.");
		}

		return mapList;
	}

	/**
	 * 指定序列List数据检索
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> executeForObjectList(String sqlID, Object bindParams,
			int beginIndex, int maxCount) {
		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList Start.");
		}

		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		List<E> list = sqlMapTemp.queryForList(sqlID, bindParams, beginIndex,
				maxCount);

		if (log.isDebugEnabled()) {
			log.debug("executeForObjectList End.");
		}
		return list;
	}

	/**
	 * 指定序列List键值数据检索
	 */
	@Override
	public List<Map<String, Object>> executeForMapList(String sqlID,
			Object bindParams, int beginIndex, int maxCount) {
		if (log.isDebugEnabled()) {
			log.debug("executeForMapList Start.");
		}

		List<Map<String, Object>> mapList = executeForObjectList(sqlID,
				bindParams, beginIndex, maxCount);

		if (log.isDebugEnabled()) {
			log.debug("executeForMapList End.");
		}
		return mapList;
	}

	@Override
	public int executeUpdate(String sqlID, Object bindParams) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("executeUpdate Start.");
		}

		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();
		Integer row = 0;
		try {
		    row = sqlMapTemp.update(sqlID, bindParams);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

		if (log.isDebugEnabled()) {
			log.debug("executeUpdate End. success count:" + row);
		}

		return row;
	}

	/**
	 * insert语句
	 */
	@Override
	public int executeInsert(String sqlID, Object bindParams) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("executeInsert Start.");
		}

		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		Integer row = 0;
		try {
			sqlMapTemp.insert(sqlID, bindParams);
			row = 1;
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

		if (log.isDebugEnabled()) {
			log.debug("executeInsert End. success count:" + row);
		}

		return row;
	}

	/**
	 * 删除语句执行
	 */
	@Override
	public int executeDelete(String sqlID, Object bindParams) throws Exception {
		SqlMapClientTemplate sqlMapTemp = getSqlMapClientTemplate();

		Integer row = 0;
		try {
		    row = sqlMapTemp.delete(sqlID, bindParams);
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			return 0;

		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}

		return row;
	}
}
