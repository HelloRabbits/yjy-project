package com.yjy.controller;


import com.yjy.common.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhangjl
 * @description
 * @date 2020-07-21 13:23
 */
@RequestMapping("auth")
@RestController
public class LogonDemoController {

    /**
     * 登陆
     *
     * @param account 账号
     * @param password 密码
     * @return
     */
    @GetMapping("/login")
    public Response<String> login(String account, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Response.success("login success");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        subject.login(token);
        return Response.success("login success");
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/logout")
    public Response<String> logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return Response.success("login out success");
    }
}
