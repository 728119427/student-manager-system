package com.atguigu.studentmanager.service;

import com.atguigu.studentmanager.domain.Grade;
import com.atguigu.studentmanager.exception.SystemException;

import java.util.List;
import java.util.Map;

public interface GradeService {

    /**
     * 添加年级
     * @param grade
     * @throws SystemException
     */
    void addGrade(Grade grade) throws SystemException;

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
     * 修改年级
     * @param grade
     */
    void editGrade(Grade grade) throws SystemException;

    /**
     * 根据条件删除年级
     * @param criteria
     * @return
     */
    void deleteGrade(Map<String, Object> criteria);

    /**
     * 查询所有grade
     * @return
     */
    List<Grade> getAllGrade();
}
