package com.psq.mybatisexercise.dao;

import org.apache.ibatis.annotations.Param;

/**
 * DataMethodSqlConfigDao.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/2/28
 */
public interface DataMethodSqlConfigDao {
    /**
     * Description: 根据方法名查询对应的SQL模板
     *
     * @param methodName
     * @return java.lang.String
     * @Author Peng Shiquan
     * @Date 2021-08-10
     */
    String selectSqlByMethodName(@Param("methodName") String methodName);
}
