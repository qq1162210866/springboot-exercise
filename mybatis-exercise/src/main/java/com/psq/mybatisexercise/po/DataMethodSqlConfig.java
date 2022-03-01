package com.psq.mybatisexercise.po;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据(源)方法SQL配置表
 * </p>
 *
 * @author Peng Shiquan
 * @since 2021-08-09
 */
@Data
public class DataMethodSqlConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    private Integer id;
    /**
     * 方法名（英文名称）
     */
    private String methodName;
    /**
     * 方法中文描述
     */
    private String methodDesc;
    /**
     * sql模板
     */
    private String sqlTemplate;
    /**
     * 数据源(英文名称)
     */
    private String dataSourceName;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String modifiedBy;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 入参描述信息
     */
    private String inputParameter;
    /**
     * 出参描述信息
     */
    private String outputParameter;
}
