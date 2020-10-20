package com.jinhao.crowd.interceptor;

import com.jinhao.crowd.entity.Admin;
import com.jinhao.crowd.exception.AccessForbiddenException;
import com.jinhao.crowd.util.CrowdConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created on 2020/10/14.
 *
 * @author Valar Morghulis
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取session域
        HttpSession session = request.getSession();

        // 获取session中的admin对象，判断是否存在
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if (admin == null) {
            // admin对象不存在，处于未登录状态，抛出异常，不允许访问
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_LOGIN_FAILED_NOLOGIN);
        }

        // 处于登录状态，可以访问
        return true;
    }
}
