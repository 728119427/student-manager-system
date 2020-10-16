package com.atguigu.studentmanager.service.impl;

import com.atguigu.studentmanager.dao.ClazzDao;
import com.atguigu.studentmanager.domain.Clazz;
import com.atguigu.studentmanager.domain.Grade;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    /**
     * 查找所有clazz
     * @return
     */
    @Override
    public List<Clazz> getAllClazz() {
        return clazzDao.getAllClazz();
    }

    /**
     * 添加clazz
     * @param clazz
     * @throws SystemException
     */
    @Override
    public void addClazz(Clazz clazz) throws SystemException {
        Clazz _clazz = clazzDao.findByName(clazz.getName());
        if(_clazz!=null)throw new SystemException(("年级名已存在！"));
        clazzDao.add(clazz);
    }

    /**
     * 根据条件查找clazz
     * @param queryMap
     * @return
     */
    @Override
    public List<Clazz> getList(Map<String, Object> queryMap) {
        return clazzDao.getList(queryMap);
    }

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return clazzDao.getTotal(queryMap);
    }

    /**
     * 修改clazz
     * @param clazz
     * @throws SystemException
     */
    @Override
    public void editClazz(Clazz clazz) throws SystemException {
        Clazz _clazz = clazzDao.findByName(clazz.getName());
        if(_clazz!=null && _clazz.getId()!=clazz.getId())throw new SystemException(("班级名已存在！"));
        clazzDao.editClazz(clazz);
    }

    /**
     * 删除clazz
     * @param criteria
     */
    @Override
    public void deleteClazz(Map<String, Object> criteria) {
        clazzDao.deleteClazz(criteria);
    }
}
