package com.itheima.log;

import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 注解配置aop
 * 提供一个类配置为切面类
 * 切面=切入点+通知
 * 通知
 *      前置增强
 *      后置增强
 *      最终增强
 *      异常增强
 *      环绕增强
 *
 *
 */

@Component
@Aspect
public class LogController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LogService logService;

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void pointcut(){}

    /**
     *ProceedingJoinPoint  连接点对象:--可以执行真实对象的真实方法  --只在环绕增强中使用
     *
     * JoinPoint   连接点对象 --不可以执行真实对象的真实方法
     *
     *
     * @param joinPoint :连接点对象
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        //创建日志对象
        SysLog log = new SysLog();

        log.setVisitTime(new Date());
        //获取securityContext对象
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        //获取用户名对象
        User user = (User) authentication.getPrincipal();
        log.setUsername(user.getUsername());
        //获取ip地址
        String ip = request.getRemoteAddr();

        log.setIp(ip);

        //4访问的权限类名,方法

        Object target = joinPoint.getTarget();
        //被拦截的权限类名
        String name = target.getClass().getName();
        //获取不被拦截的方法
        Signature signature = joinPoint.getSignature();
        //获取方法的名称
        String methodName = signature.getName();

        log.setMethod(name+"."+methodName);
        //将日志对象存储到数据库中
        //System.out.println(log);
        logService.save(log);

        try {

            //执行真实的方法  需要把真实的方法,如果不返回,所有的方法将被拦截,没有执行返回值
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;

    }


}
