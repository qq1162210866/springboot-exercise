package com.psq.mockitotrain.service;

import com.psq.mockitotrain.dao.StudentDao;
import com.psq.mockitotrain.pojo.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * StudentServiceTest.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/11/4
 */
public class StudentServiceTest {
    @Mock
    private static Student student;

    private static StudentService studentService;
    @Mock
    private static StudentDao studentDao;

    @BeforeAll
    static void getStudent() {
        student = mock(Student.class);
        student.setId(1L);
        student.setAddress("测试地址");
        student.setName("张三");
        studentDao = mock(StudentDao.class);
        when(studentDao.insert(isA(Student.class))).thenReturn(1);
        when(studentDao.delete(1L)).thenThrow(Throwable.class);
        studentService = new StudentService(studentDao);
    }

    @Test
    void insertStudent() {
        System.err.println(studentService.insertStudent(student));
    }

    @Test
    void deleteStudent() {
        System.err.println(studentService.deleteStudent(1L));
    }
}