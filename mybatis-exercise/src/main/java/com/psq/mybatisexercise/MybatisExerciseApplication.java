package com.psq.mybatisexercise;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan(basePackages = {"com.psq.mybatisexercise.dao"})
public class MybatisExerciseApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MybatisExerciseApplication.class, args);
    }

    public void test() throws Exception {
        /*
         * 1.加载mybatis的配置文件，初始化mybatis，创建出SqlSessionFactory，是创建SqlSession的工厂
         * 这里只是为了演示的需要，SqlSessionFactory临时创建出来，在实际的使用中，SqlSessionFactory只需要创建一次，当作单例来使用
         */
        InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);

        //2. 从SqlSession工厂 SqlSessionFactory中创建一个SqlSession，进行数据库操作
        SqlSession sqlSession = factory.openSession();
        //3.使用SqlSession查询
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("min_salary", 10000);
        sqlSession.selectList("com.louis.mybatis.dao.EmployeesMapper.selectByMinSalary", params);
    }

}
