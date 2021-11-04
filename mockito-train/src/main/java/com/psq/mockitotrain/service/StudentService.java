package com.psq.mockitotrain.service;

import com.psq.mockitotrain.dao.StudentDao;
import com.psq.mockitotrain.pojo.Student;

/**
 * StudentService.java
 * Description: 学生服务层
 *
 * @author Peng Shiquan
 * @date 2021/11/3
 */
public class StudentService {

    private StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String insertStudent(Student student) {
        int r = studentDao.insert(student);
        return r == 0 ? "fail" : "success";
    }

    public String deleteStudent(Long id) {
        int r = studentDao.delete(id);
        return r == 0 ? "fail" : "success";
    }
}
