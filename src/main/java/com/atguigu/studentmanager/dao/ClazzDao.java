package com.atguigu.studentmanager.dao;

import com.atguigu.studentmanager.domain.Clazz;
import com.atguigu.studentmanager.domain.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClazzDao {

    /**
     * 查找所有班级
     * @return
     */
    List<Clazz> getAllClazz();

    /**
     * 根据班级名查找
     * @param name
     * @return
     */
    Clazz findByName(String name);


    /**
     * 添加班级
     * @param clazz
     */
    void add(Clazz clazz);

    /**
     * 根据条件查询
     * @param queryMap
     * @return
     */
    List<Clazz> getList(Map<String,Object> queryMap);

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    Integer getTotal(Map<String,Object> queryMap);

    /**
     * 修改clazz
     * @param clazz
     */
    void editClazz(Clazz clazz);

    /**
     * 根据id删除用户
     * @param criteria
     * @return
     */
    Integer deleteClazz(Map<String,Object> criteria);
}
