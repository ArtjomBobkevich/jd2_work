package com.itacademy.service.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FirstAspect {

    @Around("execution(public * com.itacademy.service.service.*Service.*(..))")
    public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Logger sqlLogger = Logger.getLogger(FirstAspect.class);
        sqlLogger.info("begin method...");
        sqlLogger.info(joinPoint.getSignature());
        sqlLogger.info("method was completed");
        sqlLogger.info("all information about answer");
//        sqlLogger.info(joinPoint.proceed());
        sqlLogger.info("it`s all....");
        Object result;
            result = joinPoint.proceed();
        return result;
    }
}