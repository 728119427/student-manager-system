package com.atguigu.studentmanager.controller;

import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.domain.Page;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 跳转到admin_list
     * @return
     */
    @GetMapping("list")
    public String to_admin_list(){
        return "admin/admin_list";
    }

    /**
     * 添加admin
     * @return
     */
    @PostMapping("/add")
    public @ResponseBody Map<String,String> add(Admin admin){
        Map<String,String> msgMap = new HashMap<>();
        if(admin==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(admin.getUsername())){
            msgMap.put("type","error");
            msgMap.put("msg","用户名不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(admin.getPassword())){
            msgMap.put("type","error");
            msgMap.put("msg","密码不能为空");
            return msgMap;
        }

        try {
            //添加成功
            adminService.addAdmin(admin);
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
     * @param username
     * @param page
     * @return
     */
    @GetMapping("/get_list")
    public @ResponseBody Map<String,Object> getList(
            @RequestParam(name = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> queryMap = new HashMap<>();
        Map<String,Object> returnMap = new HashMap<>();
        queryMap.put("username","%"+username+"%");
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        //分页信息
        returnMap.put("rows",adminService.getList(queryMap));
        returnMap.put("total",adminService.getTotal(queryMap));
        return returnMap;

    }

    /**
     * 编辑admin
     * @return
     */
    @PutMapping("/edit")
    public @ResponseBody Map<String,String> edit(Admin admin){
        Map<String,String> msgMap = new HashMap<>();
        if(admin==null){
            msgMap.put("type","error");
            msgMap.put("msg","系统出错请联系管理员！");
            return msgMap;
        }
        if(StringUtils.isEmpty(admin.getUsername())){
            msgMap.put("type","error");
            msgMap.put("msg","用户名不能为空");
            return msgMap;
        }

        if(StringUtils.isEmpty(admin.getPassword())){
            msgMap.put("type","error");
            msgMap.put("msg","密码不能为空");
            return msgMap;
        }

        try {
            //修改成功
            adminService.editAdmin(admin);
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
     * 删除admin
     * @param ids
     * @return
     */
    @DeleteMapping("delete")
    public @ResponseBody Map<String,String> delete(@RequestParam(name = "ids[]") Integer[] ids){
        Map<String,String> msgMap= new HashMap<>();
        Map<String,Object> criteria= new HashMap<>();
        criteria.put("ids",ids);
        adminService.deleteAdmin(criteria);
        msgMap.put("type","success");
        msgMap.put("msg","删除成功！");
        return msgMap;
    }
}
