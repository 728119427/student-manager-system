package com.atguigu.studentmanager.dao;

import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.domain.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeDao {

    /**
     * 查找所有年级
     * @return
     */
    List<Grade> getAllGrade();

    /**
     * 根据用户名查找
     * @param name
     * @return
     */
    Grade findByName(String name);


    /**
     * 添加管理用户
     * @param grade
     */
    void add(Grade grade);

    /**
     * 根据条件查询
     * @param queryMap
     * @return
     */
    List<Grade> getList(Map<String,Object> queryMap);

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    Integer getTotal(Map<String,Object> queryMap);

    /**
     * 修改admin
     * @param grade
     */
    void editGrade(Grade grade);

    /**
     * 根据id删除用户
     * @param criteria
     * @return
     */
    Integer deleteGrade(Map<String,Object> criteria);
}
