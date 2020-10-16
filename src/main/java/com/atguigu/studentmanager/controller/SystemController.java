package com.atguigu.studentmanager.controller;

import com.atguigu.studentmanager.domain.Admin;
import com.atguigu.studentmanager.domain.Student;
import com.atguigu.studentmanager.exception.SystemException;
import com.atguigu.studentmanager.service.AdminService;
import com.atguigu.studentmanager.service.StudentService;
import com.atguigu.studentmanager.utils.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;

    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping("/index")
    public  String loginToIndex(){
        return "system/index";
    }

    @PostMapping("/login")
    public @ResponseBody Map<String,String> login(
            String username,String password,
            String vcode,Integer type,
            HttpServletRequest request){
        Map<String,String> msgMap = new HashMap<>();
        if(StringUtils.isEmpty(username)){
            msgMap.put("type","error");
            msgMap.put("msg","用户名不能为空");
            return msgMap;
        }
        if(StringUtils.isEmpty(password)){
            msgMap.put("type","error");
            msgMap.put("msg","密码不能为空");
            return msgMap;
        }
        if(StringUtils.isEmpty(vcode)){
            msgMap.put("type","error");
            msgMap.put("msg","验证码不能为空");
            return msgMap;
        }
        //比较验证码是否正确
        String session_vcode = (String) request.getSession().getAttribute("session_vcode");
        if(!session_vcode.equalsIgnoreCase(vcode)){
            msgMap.put("type","error");
            msgMap.put("msg","验证码不正确！");
            return msgMap;
        }
        if(type==1){
            //管理员
            try {
                Admin admin = adminService.login(username, password);
                //登录成功，保存用户
                request.getSession().setAttribute("user",admin);
                msgMap.put("type","success");
                msgMap.put("mag","登录成功！");
            } catch (SystemException e) {
                //登录失败
                msgMap.put("type","error");
                msgMap.put("msg",e.getMessage());
            }
        }else {
            //学生
            try {
                Student student = studentService.login(username, password);
                //登录成功，保存用户
                request.getSession().setAttribute("user",student);
                msgMap.put("type","success");
                msgMap.put("mag","登录成功！");
            } catch (SystemException e) {
                //登录失败
                msgMap.put("type","error");
                msgMap.put("msg",e.getMessage());
            }
        }
        request.getSession().setAttribute("userType",type);
        return msgMap;
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @GetMapping("/getVcode")
    public void getVcode(HttpServletRequest request, HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil();
        //生成验证码文字
        String vCode = cpachaUtil.generatorVCode();
        //保存到session中
        request.getSession().setAttribute("session_vcode",vCode);
        //生成验证码图片
        BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(vCode, true);
        //响应客户端
        try {
            ImageIO.write(bufferedImage,"gif",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @GetMapping("/login_out")
    public String logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }


}
