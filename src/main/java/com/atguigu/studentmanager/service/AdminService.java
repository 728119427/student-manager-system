package com.atguigu.studentmanager.service;

import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.exception.SystemException;
import com.sun.org.apache.xpath.internal.objects.XObject;

import java.util.List;
import java.util.Map;

public interface AdminService {

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     * @throws SystemException
     */
    Admin login(String username,String password) throws SystemException;

    /**
     * 添加用户
     * @param admin
     * @throws SystemException
     */
    void addAdmin(Admin admin) throws SystemException;

    /**
     * 根据条件查询
     * @param queryMap
     * @return
     */
    List<Admin> getList(Map<String,Object> queryMap);

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    Integer getTotal(Map<String,Object> queryMap);

    /**
     * 修改admin
     * @param admin
     */
    void editAdmin(Admin admin) throws SystemException;

    /**
     * 根据条件删除用户
     * @param criteria
     * @return
     */
    Integer deleteAdmin(Map<String, Object> criteria);
}
