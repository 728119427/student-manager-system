package com.atguigu.studentmanager.service.impl;

import com.atguigu.studentmanager.dao.GradeDao;
import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.domain.Grade;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    /**
     * 添加grade
     * @param grade
     * @throws SystemException
     */
    @Override
    public void addGrade(Grade grade) throws SystemException {
        Grade _grade = gradeDao.findByName(grade.getName());
        if(_grade!=null)throw new SystemException(("年级名已存在！"));
        gradeDao.add(grade);
    }

    /**
     * 根据条件查找grade
     * @param queryMap
     * @return
     */
    @Override
    public List<Grade> getList(Map<String, Object> queryMap) {
        return gradeDao.getList(queryMap);
    }

    /**
     * 获取查询结果个数
     * @param queryMap
     * @return
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return gradeDao.getTotal(queryMap);
    }

    /**
     * 修改grade信息
     * @param grade
     * @throws SystemException
     */
    @Override
    public void editGrade(Grade grade) throws SystemException {
        Grade _grade = gradeDao.findByName(grade.getName());
        if(_grade!=null && _grade.getId()!=grade.getId())throw new SystemException(("年级名已存在！"));
        gradeDao.editGrade(grade);
    }

    /**
     * 删除grade
     * @param criteria
     */
    @Override
    public void deleteGrade(Map<String, Object> criteria) {
        gradeDao.deleteGrade(criteria);
    }

    /**
     * 查找所有grade
     * @return
     */
    @Override
    public List<Grade> getAllGrade() {
        return gradeDao.getAllGrade();
    }
}
