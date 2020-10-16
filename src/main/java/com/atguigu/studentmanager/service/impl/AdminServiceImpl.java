package com.atguigu.studentmanager.service.impl;

import com.atguigu.studentmanager.dao.AdminDao;
import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.atguigu.studentmanager.dao.AdminDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws SystemException
     */
    @Override
    public Admin login(String username, String password) throws SystemException {
        Admin admin = adminDao.findByUsername(username);
        if(admin==null){
            throw new SystemException("用户名不存在！");
        }
        if(!password.equals(admin.getPassword())){
            throw new SystemException("用户名或密码不正确！");
        }
        return admin;
    }

    /**
     * 添加admin
     * @param admin
     * @throws SystemException
     */
    @Override
    public void addAdmin(Admin admin) throws SystemException {
        Admin _admin = adminDao.findByUsername(admin.getUsername());
        if(_admin!=null)throw new SystemException(("用户名已存在！"));
        adminDao.add(admin);
    }

    /**
     * 根据条件查询
     * @param queryMap
     * @return
     */
    @Override
    public List<Admin> getList(Map<String, Object> queryMap) {
        return adminDao.getList(queryMap);
    }

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return adminDao.getTotal(queryMap);
    }

    /**
     * 修改admin
     * @param admin
     */
    @Override
    public void editAdmin(Admin admin) throws SystemException {
        Admin _admin = adminDao.findByUsername(admin.getUsername());
        if(_admin!=null && _admin.getId()!=admin.getId())throw new SystemException("用户名已存在！");
        adminDao.editAdmin(admin);
    }

    @Override
    public Integer deleteAdmin(Map<String, Object> criteria) {
        return adminDao.deleteAdmin(criteria);
    }
}
