package com.atguigu.studentmanager.dao;

import com.atguigu.studentmanager.domain.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao {

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 添加管理用户
     * @param admin
     */
    void add(Admin admin);

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
    void editAdmin(Admin admin);

    /**
     * 根据id删除用户
     * @param criteria
     * @return
     */
    Integer deleteAdmin(Map<String,Object> criteria);

}
