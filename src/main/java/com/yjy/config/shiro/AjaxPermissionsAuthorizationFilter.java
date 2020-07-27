package com.yjy.config.shiro;

import com.google.gson.JsonObject;
import com.yjy.common.enums.ErrorCodeEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author zhangjl
 * @description
 * @date 2020-07-21 10:52
 */
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", ErrorCodeEnum.ERROR_403.getCode());
        jsonObject.addProperty("msg", "无权限");
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }


}
