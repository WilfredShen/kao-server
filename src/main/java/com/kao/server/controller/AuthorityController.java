package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.json.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
所有的访问必须经过权限分配
相当于过滤器
 */
@RequestMapping("/*")
public class AuthorityController {

    private static final String[] urls = new String[]{
            "/Visitor",
            "/Student",
            "/Teacher",
            "Admin"
    };
    private static final String  visitor = "游客";
    private static final String  student = "学生";
    private static final String  teacher = "老师";
    private static final String  admin = "管理员";

    public static String getVisitor() {
        return visitor;
    }

    public static String getStudent() {
        return student;
    }

    public static String getTeacher() {
        return teacher;
    }

    public static String getAdmin() {
        return admin;
    }

    public String distributeAuthority(String authorityMessage){

        JsonResult<String> authorityResponse = new JsonResult<>();
        authorityResponse.state = 0;
        if (authorityMessage!=null){
            if (authorityMessage.equals(AuthorityController.getVisitor())){
                return AuthorityController.getVisitor();
            }else if (authorityMessage.equals(AuthorityController.getStudent())){
                return AuthorityController.getStudent();
            }else if (authorityMessage.equals(AuthorityController.getTeacher())){
                return  AuthorityController.getTeacher();
            }else if (authorityMessage.equals(AuthorityController.getAdmin())){
                return AuthorityController.getAdmin();
            }else {
                return  null;
            }
        }
            return  null;
    }
}
