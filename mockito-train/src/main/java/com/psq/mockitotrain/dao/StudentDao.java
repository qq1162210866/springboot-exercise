package com.psq.mockitotrain.dao;

import com.psq.mockitotrain.pojo.Student;

/**
 * StudentDao.java
 * Description: 学生dao层方法
 *
 * @author Peng Shiquan
 * @date 2021/11/3
 */
public interface StudentDao {

    int insert(Student student);

    int delete(Long id);
}
