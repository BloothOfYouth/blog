package com.hjf.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误信息处理并返回
 * @author hjf
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 自定义异常错误信息
     * @param request 请求对象
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e){
        //错误信息打印大控制台 , 双引号里面的{}  是占位符
        logger.error("Request URL : {},Exception : {}",request.getRequestURL(),e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("/error/error");
        return modelAndView;
    }
}
