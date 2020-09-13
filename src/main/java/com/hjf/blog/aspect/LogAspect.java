package com.hjf.blog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面
 * @author hjf
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * com.hjf.blog.controller下的所有方法
     */
    @Pointcut("execution(* com.hjf.blog.controller.*.*(..))")
    private void log(){};


    /**
     * 环绕通知,日志记录请求信息
     * @param point 切入点
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object aroundPrintLog(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName();
        Object[] args = point.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        //前置通知
        logger.info("Request : {}", requestLog);

        Object result = point.proceed(args);

        //后置通知
        logger.info("Result : {}",result);

        return result;
    }

    /**
     * 存放请求信息的内部类
     */
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
