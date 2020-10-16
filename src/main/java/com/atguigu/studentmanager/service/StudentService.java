package com.atguigu.studentmanager.service;

import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.domain.Student;
import com.atguigu.studentmanager.exception.SystemException;

import java.util.List;
import java.util.Map;

public interface StudentService {


    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     * @throws SystemException
     */
    Student login(String username, String password) throws SystemException;

    /**
     * 添加学生
     * @param student
     * @throws SystemException
     */
    void addStudent(Student student) throws SystemException;

    /**
     * 根据条件查询
     * @param queryMap
     * @return
     */
    List<Student> getList(Map<String,Object> queryMap);

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    Integer getTotal(Map<String,Object> queryMap);

    /**
     * 修改学生
     * @param student
     */
    void editStudent(Student student) throws SystemException;

    /**
     * 根据条件删除学生
     * @param criteria
     * @return
     */
    void deleteStudent(Map<String, Object> criteria);
}
