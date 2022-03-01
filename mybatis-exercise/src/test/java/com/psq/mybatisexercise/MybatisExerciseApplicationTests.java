package com.psq.mybatisexercise;

import com.psq.mybatisexercise.dao.DataMethodSqlConfigDao;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = MybatisExerciseApplication.class)
class MybatisExerciseApplicationTests {
    @Autowired
    private DataMethodSqlConfigDao dataMethodSqlConfigDao;

    @Test
    void test() {
        String sql = dataMethodSqlConfigDao.selectSqlByMethodName("testPage");
        System.err.println(sql);
    }

    @Test
    void test2() {
        String script = "<script>select * from table where 1 = 1<if test='id != null'>and id = #{id} </if></script>";
        LanguageDriver languageDriver = new XMLLanguageDriver();

        Configuration configuration = new Configuration();

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, script, Object.class);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "1");

        BoundSql boundSql = sqlSource.getBoundSql(parameters);
        System.err.println(boundSql.getSql());
    }

    @Test
    void test3() throws Exception {
        // 通过DriverManager获取数据库连接
        String url = "jdbc:mysql://localhost:3307/test?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useAffectedRows=true";
        String user = "test";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, user, password);
        // 通过Connection对象获取Statement对象、Statement执行SQL语句
        Statement statement = (Statement) connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from data_method_sql_config");
        System.err.println(resultSet.toString());
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " + resultSet.getString(3));
        }
    }

}
