package com.jinhao.crowd.mvc.config;

import com.google.gson.Gson;
import com.jinhao.crowd.exception.LoginAcctRepeatException;
import com.jinhao.crowd.exception.LoginAcctRepeatForUpdateException;
import com.jinhao.crowd.exception.LoginFailedException;
import com.jinhao.crowd.util.CrowdConstant;
import com.jinhao.crowd.util.CrowdUtil;
import com.jinhao.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常映射处理类
 *
 * Created on 2020/10/12.
 *
 * @author Valar Morghulis
 */
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginAcctRepeatForUpdateException.class)
    public ModelAndView resolerLoginAcctRepeatForUpdateException(HttpServletRequest request,
                                                        LoginAcctRepeatForUpdateException exception,
                                                        HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        ModelAndView modelAndView = commonResolver(viewName, request, response, exception);
        return modelAndView;
    }

    @ExceptionHandler(value = LoginAcctRepeatException.class)
    public ModelAndView resolerLoginAcctRepeatException(HttpServletRequest request,
                                                    LoginAcctRepeatException exception,
                                                    HttpServletResponse response) throws IOException {
        String viewName = "admin-add";
        ModelAndView modelAndView = commonResolver(viewName, request, response, exception);
        return modelAndView;
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(HttpServletRequest request,
                                                  LoginFailedException exception,
                                                  HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        ModelAndView modelAndView = commonResolver(viewName, request, response, exception);
        return modelAndView;
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointException(HttpServletRequest request,
                                                  NullPointerException exception,
                                                  HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        ModelAndView modelAndView = commonResolver(viewName, request, response, exception);
        return modelAndView;
    }


    /**
     *  对相同的代码块就行封装
     */
    private ModelAndView commonResolver(String viewName,
                                        HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception exception) throws IOException {
        /**
         * 判断是普通请求还是Ajax请求
         */
        boolean isAjax = CrowdUtil.judgeResultType(request);
        if (isAjax) {
            /**
             * 如果是Ajax请求，将返回结果装换成json字符串返回
             */
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            response.getWriter().write(json);
            return null;
        }

        /**
         * 如果是普通请求，跳转到错误目标页面
         */
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
