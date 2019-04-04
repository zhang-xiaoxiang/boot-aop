package com.example.boot.aop.myaop;

import java.util.Arrays;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AopLog
 *
 * @author 10905 2019/1/4
 * @version 1.0
 */
@Aspect
@Component
public class AopLog {
    //使用org.slf4j.Logger,这是Spring实现日志的方法
    private final static Logger logger = LoggerFactory.getLogger(AopLog.class);

    //	切入点注解的表达式：就是需要AOP的地方(一般是业务逻辑层service,当然服务接口调用层controller也行,两者一起打印日志也行
    //	这个类似正则表达式,可以控制日志的精度(包下,类下,方法下)和切面的类型(业务层面,服务接口层面)相当灵活)
    @Pointcut("execution(* com.example.boot.aop.serviceimpl..*(..))")
    //  @Pointcut("execution(* com.example.nba.repository.PlayerRep.*(..))")
    //切入点签名的方法，注意返回值必须是void,相当于切入点的无参构造
    public void mypointcut() {
    }

    //	前置增强
    @Before("mypointcut()")
    public void Mybefore(JoinPoint jp) {
        logger.info("*前置增强*调用了【" + jp.getTarget().getClass().getSimpleName() +
                "】的【" + jp.getSignature().getName() + "】的方法，方法入参为【"
                + Arrays.toString(jp.getArgs()) + "】");
        // 接收到请求，记录请求内容(这里同样可以在前置增强配置请求的相关信息)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求的地址URL : " + request.getRequestURL().toString());
        logger.info("请求的方式HTTP_METHOD : " + request.getMethod());
        logger.info("请求的IP : " + request.getRemoteAddr());
        logger.info("请求的全类名 : " + jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName());
        logger.info("请求的参数(数组形式) : " + Arrays.toString(jp.getArgs()));

    }

    //后置增强
    @AfterReturning(pointcut = "mypointcut()", returning = "result")
    public void MyafterReturing(JoinPoint jp, Object result) {
        logger.info("*后置增强*调用了【" + jp.getTarget().getClass().getSimpleName() +
                "】的【" + jp.getSignature().getName() + "】的方法，方法返回值【" + result + "】");
    }

    //	异常抛出增强
    @AfterThrowing(pointcut = "mypointcut()", throwing = "e")
    public void afterThrowing(JoinPoint jp, RuntimeException e) {
        logger.error("*异常增强*【" + jp.getSignature().getName().getClass().getSimpleName() + "】方法发生异常【" + e + "】");
    }

    //	最终增强
    @After("mypointcut()")
    public void afterLogger(JoinPoint jp) {
        logger.info("*最终增强*【" + jp.getSignature().getName() + "】方法结束执行。");
    }

    //环绕增强
    @Around("mypointcut()")
    public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable {
        logger.info("在==>>" + jp.getTarget().getClass().getName() + "类里面使用AOP环绕增强==");
        logger.info("*环绕增强*调用【" + jp.getTarget().getClass().getSimpleName() + "】的【 " + jp.getSignature().getName()
                + "】方法。方法入参【" + Arrays.toString(jp.getArgs()) + "】");
        try {
            Object result = jp.proceed();
            logger.info("*环绕增强*调用 " + jp.getTarget() + "的【 "
                    + jp.getSignature().getName() + "】方法。方法返回值【" + result + "】");
            return result;
        } catch (Throwable e) {
            logger.error(jp.getSignature().getName() + " 方法发生异常【" + e + "】");
            throw e;
        } finally {
            logger.info("*环绕增强*执行finally【" + jp.getSignature().getName() + "】方法结束执行<<==。");
        }
    }
}