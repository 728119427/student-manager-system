package com.atguigu.studentmanager.controller;

import com.atguigu.studentmanager.domain.Clazz;
import com.atguigu.studentmanager.domain.Page;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.ClazzService;
import com.atguigu.studentmanager.service.GradeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private GradeService gradeService;

    /**
     * 跳转到clazz_list
     * @return
     */
    @GetMapping("/list")
    public String to_clazz_list(Model model){
        try {
            //保存gradeList
            model.addAttribute("gradeList",gradeService.getAllGrade());
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("gradeListJson", mapper.writeValueAsString(gradeService.getAllGrade()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "clazz/clazz_list";
    }


    /**
     * 添加clazz
     * @return
     */
    @PostMapping("/add")
    public @ResponseBody
    Map<String,String> add(Clazz clazz){
        Map<String,String> msgMap = new HashMap<>();
        if(clazz==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(clazz.getName())){
            msgMap.put("type","error");
            msgMap.put("msg","班级名不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(clazz.getGradeId())){
            msgMap.put("type","error");
            msgMap.put("msg","所属年级不能为空");
            return msgMap;
        }

        try {
            //添加成功
            clazzService.addClazz(clazz);
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
            @RequestParam(name = "gradeId",required = false) Integer gradeId,
            Page page
    ){
        Map<String,Object> queryMap = new HashMap<>();
        Map<String,Object> returnMap = new HashMap<>();
        queryMap.put("name","%"+name+"%");
        queryMap.put("gradeId",gradeId);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        //分页信息
        returnMap.put("rows",clazzService.getList(queryMap));
        returnMap.put("total",clazzService.getTotal(queryMap));
        return returnMap;

    }

    /**
     * 编辑clazz
     * @return
     */
    @PutMapping("/edit")
    public @ResponseBody Map<String,String> edit(Clazz clazz){
        Map<String,String> msgMap = new HashMap<>();
        if(clazz==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(clazz.getName())){
            msgMap.put("type","error");
            msgMap.put("msg","班级名不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(clazz.getGradeId())){
            msgMap.put("type","error");
            msgMap.put("msg","所属年级不能为空");
            return msgMap;
        }

        try {
            //修改成功
            clazzService.editClazz(clazz);
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
     * 删除clazz
     * @param ids
     * @return
     */
    @DeleteMapping("delete")
    public @ResponseBody Map<String,String> delete(@RequestParam(name = "ids[]") Integer[] ids){
        Map<String,String> msgMap= new HashMap<>();
        Map<String,Object> criteria= new HashMap<>();
        criteria.put("ids",ids);
        try {
            clazzService.deleteClazz(criteria);
            msgMap.put("type", "success");
            msgMap.put("msg", "删除成功！");
        } catch (Exception e) {
            msgMap.put("type", "error");
            msgMap.put("msg", "该班级下存在学生信息无法删除！");
        }

        return msgMap;
    }
}
