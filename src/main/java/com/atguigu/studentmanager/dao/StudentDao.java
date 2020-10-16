package com.atguigu.studentmanager.dao;

import com.atguigu.studentmanager.domain.Clazz;
import com.atguigu.studentmanager.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface StudentDao {


    /**
     * 根据学生名查找
     * @param username
     * @return
     */
    Student findByUsername(String username);
    
    /**
     * 添加管理学生
     * @param student
     */
    void add(Student student);

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
    void editStudent(Student student);

    /**
     * 根据id删除学生
     * @param criteria
     * @return
     */
    void deleteStudent(Map<String,Object> criteria);

}
