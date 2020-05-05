package com.fuyang.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class Myexception implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //日志处理
        System.out.println("程序出现异常。。。。");
        System.out.println("异常名称："+e.getClass().getName());
        //友好响应页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","请求失败，请稍后");
        String viewName = "/WEB-INF/views/error.jsp";
        mv.setViewName(viewName);
        return mv;
    }
}
