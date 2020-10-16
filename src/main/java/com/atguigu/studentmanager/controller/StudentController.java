package com.atguigu.studentmanager.controller;

import com.atguigu.studentmanager.domain.Student;
import com.atguigu.studentmanager.domain.Page;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.ClazzService;
import com.atguigu.studentmanager.service.StudentService;
import com.atguigu.studentmanager.utils.MyStringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;

    /**
     * 跳转到student_list
     * @return
     */
    @GetMapping("/list")
    public String to_clazz_list(Model model){
        try {
            //保存gradeList
            model.addAttribute("clazzList",clazzService.getAllClazz());
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("clazzListJson", mapper.writeValueAsString(clazzService.getAllClazz()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "student/student_list";
    }


    /**
     * 添加student
     * @return
     */
    @PostMapping("/add")
    public @ResponseBody
    Map<String,String> add(Student student){
        Map<String,String> msgMap = new HashMap<>();
        if(student==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(student.getUsername())){
            msgMap.put("type","error");
            msgMap.put("msg","名字不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(student.getPassword())){
            msgMap.put("type","error");
            msgMap.put("msg","密码不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(student.getClazzId())){
            msgMap.put("type","error");
            msgMap.put("msg","所属班级不能为空");
            return msgMap;
        }
        //设置学号
        student.setSn(MyStringUtil.generateSN());
        try {
            //添加成功
            studentService.addStudent(student);
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
            HttpServletRequest request,
            @RequestParam(name = "name",required = false,defaultValue = "") String name,
            @RequestParam(name = "clazzId",required = false) Integer clazzId,
            Page page
    ){
        Map<String,Object> queryMap = new HashMap<>();
        Map<String,Object> returnMap = new HashMap<>();
        queryMap.put("username","%"+name+"%");
        //判断是否是学生登录
        Integer userType = (Integer) request.getSession().getAttribute("userType");
        if(userType==2){
            Student student = (Student) request.getSession().getAttribute("user");
            queryMap.put("username",student.getUsername());
        }

        queryMap.put("clazzId",clazzId);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        //分页信息
        returnMap.put("rows",studentService.getList(queryMap));
        returnMap.put("total",studentService.getTotal(queryMap));
        return returnMap;

    }

    /**
     * 编辑student
     * @return
     */
    @PutMapping("/edit")
    public @ResponseBody Map<String,String> edit(Student student){
        Map<String,String> msgMap = new HashMap<>();
        if(student==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(student.getUsername())){
            msgMap.put("type","error");
            msgMap.put("msg","名字不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(student.getClazzId())){
            msgMap.put("type","error");
            msgMap.put("msg","所属班级不能为空");
            return msgMap;
        }

        try {
            //修改成功
            studentService.editStudent(student);
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
     * 删除student
     * @param ids
     * @return
     */
    @DeleteMapping("delete")
    public @ResponseBody Map<String,String> delete(@RequestParam(name = "ids[]") Integer[] ids){
        Map<String,String> msgMap= new HashMap<>();
        Map<String,Object> criteria= new HashMap<>();
        criteria.put("ids",ids);
        studentService.deleteStudent(criteria);
        msgMap.put("type", "success");
        msgMap.put("msg", "删除成功！");
        return msgMap;
    }

    @PostMapping("/upload_photo")
    public @ResponseBody Map<String,Object> upload(HttpServletRequest request, MultipartFile photo){
        Map<String,Object> msgMap = new HashMap<>();
        //判断图片格式是否正确
        String originalFilename = photo.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        if(!"jpg,gif,jpeg,png".contains(suffix)){
            msgMap.put("type","error");
            msgMap.put("msg","图片格式不正确,请上传jpg,gif,jpeg,png格式的图片！");
            return msgMap;
        }
        //判断图片大小是否合适
        if(photo.getSize()>512000){
            msgMap.put("type","error");
            msgMap.put("msg","图片大小上限为500KB！");
            return msgMap;
        }

        //设置图片保存路径
        String savePath = request.getSession().getServletContext().getRealPath("/upload");
        File savePathFile = new File(savePath);
        if(!savePathFile.exists())savePathFile.mkdirs();
        //设置图片名称
        String photoName = UUID.randomUUID().toString().replace("-","").substring(0,8)
                +"_"+originalFilename;
        //上传图片
        try {
            photo.transferTo(new File(savePathFile,photoName));
            msgMap.put("type","success");
            msgMap.put("msg","图片上传成功！");
            //保存图片的保存路径
            msgMap.put("src",request.getContextPath()+"/upload/"+photoName);
        } catch (IOException e) {
            msgMap.put("type","error");
            msgMap.put("msg","图片上传失败！");
        }
        return msgMap;
    }

}
