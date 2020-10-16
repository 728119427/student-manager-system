package com.atguigu.studentmanager.service;

import com.atguigu.studentmanager.domain.Clazz;
import com.atguigu.studentmanager.exception.SystemException;

import java.util.List;
import java.util.Map;

public interface ClazzService {


    /**
     * 查找所有班级
     * @return
     */
    List<Clazz> getAllClazz();


    /**
     * 添加班级
     * @param clazz
     * @throws SystemException
     */
    void addClazz(Clazz clazz) throws SystemException;

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
     * 修改班级
     * @param clazz
     */
    void editClazz(Clazz clazz) throws SystemException;

    /**
     * 根据条件删除用户
     * @param criteria
     * @return
     */
    void deleteClazz(Map<String, Object> criteria);

}
