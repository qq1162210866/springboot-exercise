package com.psq.mybatisexercise.service;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
public class AutoApiSqlMapper {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private SqlBuilderStatement SqlBuilderStatement;

    @PostConstruct
    public void init() {
        this.SqlBuilderStatement = new SqlBuilderStatement(sqlSessionFactory.getConfiguration());
    }

    public Map<String, Object> sqlSelectOne(String sql) {
        List<Map<String, Object>> list = this.selectList(sql);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public List<Map<String, Object>> selectList(String sql) {
        String msId = this.SqlBuilderStatement.select(sql);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            return sqlSession.selectList(msId);
        } finally {
            sqlSession.close();
        }
    }

    public <T> List<T> selectList(String sql, Object value, Class<T> resultType) {
        Class<?> parameterType = value != null ? value.getClass() : null;
        String msId;
        if (resultType == null) {
            msId = this.SqlBuilderStatement.selectDynamic(sql, parameterType);
        } else {
            msId = this.SqlBuilderStatement.selectDynamic(sql, parameterType, resultType);
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            return sqlSession.selectList(msId, value);
        } finally {
            sqlSession.close();
        }
    }

    private static class SqlBuilderStatement {
        private Configuration configuration;
        private LanguageDriver languageDriver;

        private SqlBuilderStatement(Configuration configuration) {
            this.configuration = configuration;
            this.languageDriver = configuration.getDefaultScriptingLanguageInstance();
        }

        private String newMsId(String sql, SqlCommandType sqlCommandType) {
            return sqlCommandType.toString() + "." + sql.hashCode();
        }

        private boolean hasMappedStatement(String msId) {
            return this.configuration.hasStatement(msId, false);
        }

        private void newSelectMappedStatement(String msId, SqlSource sqlSource, final Class<?> resultType) {
            MappedStatement ms = (new MappedStatement.Builder(this.configuration, msId, sqlSource, SqlCommandType.SELECT)).resultMaps(new ArrayList<ResultMap>() {
                {
                    this.add((new ResultMap.Builder(SqlBuilderStatement.this.configuration, "defaultResultMap", resultType, new ArrayList(0))).build());
                }
            }).build();
            this.configuration.addMappedStatement(ms);
        }

        private String select(String sql, Class<?> resultType) {
            String msId = this.newMsId(resultType + sql, SqlCommandType.SELECT);
            if (!this.hasMappedStatement(msId)) {
                StaticSqlSource sqlSource = new StaticSqlSource(this.configuration, sql);
                this.newSelectMappedStatement(msId, sqlSource, resultType);
            }
            return msId;
        }

        private String select(String sql) {
            String msId = this.newMsId(sql, SqlCommandType.SELECT);
            if (!this.hasMappedStatement(msId)) {
                StaticSqlSource sqlSource = new StaticSqlSource(this.configuration, sql);
                this.newSelectMappedStatement(msId, sqlSource, Map.class);
            }
            return msId;
        }

        private String selectDynamic(String sql, Class<?> parameterType) {
            String msId = this.newMsId(sql + parameterType, SqlCommandType.SELECT);
            if (!this.hasMappedStatement(msId)) {
                SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, parameterType);
                this.newSelectMappedStatement(msId, sqlSource, Map.class);
            }
            return msId;
        }

        private String selectDynamic(String sql, Class<?> parameterType, Class<?> resultType) {
            String msId = this.newMsId(resultType + sql + parameterType, SqlCommandType.SELECT);
            if (!this.hasMappedStatement(msId)) {
                SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, parameterType);
                this.newSelectMappedStatement(msId, sqlSource, resultType);
            }
            return msId;
        }
    }
}