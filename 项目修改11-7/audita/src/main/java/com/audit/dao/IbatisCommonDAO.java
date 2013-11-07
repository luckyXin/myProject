package com.audit.dao;

import java.util.List;
import java.util.Map;

public interface IbatisCommonDAO {

    /**
     * 检索单条数据
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @param clazz 返回数据类型
     * @return 数据
     */
    @SuppressWarnings("rawtypes")
    public <E> E executeForObject(String sqlID, Object bindParams, Class clazz);
    
    /**
     * 检索单条键值MAP数据
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @return Map数据
     */
    public Map<String, Object> executeForMap(String sqlID, Object bindParams);
    
    /**
     * 检索数组类型数据
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @param clazz 返回数据类型
     * @return 数组类型数据
     */
    @SuppressWarnings("rawtypes")
    public <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class clazz);
    
    /**
     * 多条数据检索
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @return 多条数据
     */
    public <E> List<E> executeForObjectList(String sqlID, Object bindParams);
    
    /**
     * 指定序列数据检索
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @param clazz 返回数据类型
     * @param beginIndex 开始count
     * @param maxCount 最大count
     * @return 指定序列数据
     */
    @SuppressWarnings("rawtypes")
    public <E> E[] executeForObjectArray(String sqlID, Object bindParams, 
            Class clazz, int beginIndex, int maxCount);
    
    /**
     * 多条键值数据获取
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @return 多条键值数据
     */
    public List<Map<String, Object>> executeForMapList(String sqlID, Object bindParams);
    
    /**
     * 指定序列List数据检索
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @param beginIndex 开始数值
     * @param maxCount 最大数值
     * @return 指定序列List数据
     */
    public <E> List<E> executeForObjectList(String sqlID, Object bindParams,
            int beginIndex, int maxCount);
    
    /**
     * 指定序列List键值数据检索
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @param beginIndex 开始数值
     * @param maxCount 最大数值
     * @return 指定序列List键值数据
     */
    public List<Map<String, Object>> executeForMapList(String sqlID,
            Object bindParams, int beginIndex, int maxCount);
    
    /**
     * 更新SQL执行
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @return 更新条数
     */
    public int executeUpdate(String sqlID, Object bindParams)throws Exception;
    
    /**
     * 插入SQL语句执行
     * @param sqlID 执行SQL
     * @param bindParams 入力参数
     * @return 插入条数
     */
    public int executeInsert(String sqlID, Object bindParams) throws Exception;
    
    /**
     * 删除SQL语句执行
     * @param statementName 执行SQL
     * @param parameterObject 入力参数
     * @return 删除条数
     */
    public int executeDelete(String sqlID, Object bindParams)throws Exception;
}
