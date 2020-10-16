package com.atguigu.studentmanager.service.impl;

import com.atguigu.studentmanager.dao.StudentDao;
import com.atguigu.studentmanager.domain.Student;
import com.atguigu.studentmanager.domain.Clazz;
import com.atguigu.studentmanager.domain.Student;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {


    @Override
    public Student login(String username, String password) throws SystemException {
        Student student = studentDao.findByUsername(username);
        if(student==null){
            throw new SystemException("学生不存在！");
        }
        if(!password.equals(student.getPassword())){
            throw new SystemException("用户名或密码不正确！");
        }
        return student;
    }

    @Autowired
    private StudentDao studentDao;

    @Override
    public void addStudent(Student student) throws SystemException {
        Student _student = studentDao.findByUsername(student.getUsername());
        if(_student!=null )throw new SystemException(("学生已存在！"));
        studentDao.add(student);
    }

    @Override
    public List<Student> getList(Map<String, Object> queryMap) {
        return studentDao.getList(queryMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return studentDao.getTotal(queryMap);
    }

    @Override
    public void editStudent(Student student) throws SystemException {
        Student _student = studentDao.findByUsername(student.getUsername());
        if(_student!=null && _student.getId()!=student.getId())throw new SystemException(("学生已存在！"));
        studentDao.editStudent(student);
    }

    @Override
    public void deleteStudent(Map<String, Object> criteria) {
        studentDao.deleteStudent(criteria);
    }
}
