package com.atguigu.studentmanager.controller;

import com.atguigu.studentmanager.domain.Grade;
import com.atguigu.studentmanager.domain.Page;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    /**
     * 跳转到grade_list
     * @return
     */
    @GetMapping("list")
    public String to_grade_list(){
        return "grade/grade_list";
    }


    /**
     * 添加grade
     * @return
     */
    @PostMapping("/add")
    public @ResponseBody
    Map<String,String> add(Grade grade){
        Map<String,String> msgMap = new HashMap<>();
        if(grade==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(grade.getName())){
            msgMap.put("type","error");
            msgMap.put("msg","年级名不能为空");
            return msgMap;
        }
        
        try {
            //添加成功
            gradeService.addGrade(grade);
            msgMap.put("type","success");
            msgMap.put("msg","添加成功！");
        } catch (SystemException e) {
            //添加失败
            msgMap.put("type","error");
            msgMap.put("msg",e.getMessage());
        }
        return msgMap;
    }

    /**
     * 分页查询
     * @param name
     * @param page
     * @return
     */
    @GetMapping("/get_list")
    public @ResponseBody Map<String,Object> getList(
            @RequestParam(name = "name",required = false,defaultValue = "") String name,
            Page page
    ){
        Map<String,Object> queryMap = new HashMap<>();
        Map<String,Object> returnMap = new HashMap<>();
        queryMap.put("name","%"+name+"%");
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        //分页信息
        returnMap.put("rows",gradeService.getList(queryMap));
        returnMap.put("total",gradeService.getTotal(queryMap));
        return returnMap;

    }

    /**
     * 编辑grade
     * @return
     */
    @PutMapping("/edit")
    public @ResponseBody Map<String,String> edit(Grade grade){
        Map<String,String> msgMap = new HashMap<>();
        if(grade==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(grade.getName())){
            msgMap.put("type","error");
            msgMap.put("msg","年级名不能为空");
            return msgMap;
        }

        try {
            //修改成功
            gradeService.editGrade(grade);
            msgMap.put("type","success");
            msgMap.put("msg","修改成功！");
        } catch (SystemException e) {
            //修改失败
            msgMap.put("type","error");
            msgMap.put("msg",e.getMessage());
        }
        return msgMap;
    }

    /**
     * 删除grade
     * @param ids
     * @return
     */
    @DeleteMapping("delete")
    public @ResponseBody Map<String,String> delete(@RequestParam(name = "ids[]") Integer[] ids){
        Map<String,String> msgMap= new HashMap<>();
        Map<String,Object> criteria= new HashMap<>();
        criteria.put("ids",ids);
        try {
            gradeService.deleteGrade(criteria);
            msgMap.put("type","success");
            msgMap.put("msg","删除成功！");
        } catch (Exception e) {
            msgMap.put("type","error");
            msgMap.put("msg","该年级下存在班级信息无法删除！");
        }

        return msgMap;
    }
}
