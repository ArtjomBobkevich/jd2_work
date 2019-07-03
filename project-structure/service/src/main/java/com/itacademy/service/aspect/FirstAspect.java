package com.itacademy.service.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FirstAspect {

    /*@Around("execution(public * com.itacademy.service.service.*Service.*(..))")
    public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Logger sqlLogger = Logger.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.DEBUG);
        //Включение логирования параметров запросов
        Logger descLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.TRACE);
        //Выполнение аннотированного метода сервиса
        Object res = joinPoint.proceed();
        //Выключение логирования SQL
        sqlLogger = Logger.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.INFO);
        //Выключение логирования параметров
        descLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.INFO);

        return res;*/

        /*возможно сделать метод void там повырывать что то из метода и самому в какой то файл сливать*/

    @Around("execution(public * com.itacademy.service.service.*Service.*(..))")
    public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Logger sqlLogger = Logger.getLogger(FirstAspect.class);
        sqlLogger.info("begin method...");
        sqlLogger.info(joinPoint.getSignature());
        sqlLogger.info("method was completed");
        sqlLogger.info("all information about answer");
        sqlLogger.info(joinPoint.proceed());
        sqlLogger.info("it`s all....");
        Object result;
            result = joinPoint.proceed();
        return result;
    }

//    @Around("execution(public * com.itacademy.service.*.*.*(..))")
//    public Object logMethod2(final ProceedingJoinPoint joinPoint) throws Throwable {
//        Logger sqlLogger = Logger.getLogger(FirstAspect.class);
//        sqlLogger.info("qwert");
//        System.out.println("go fight...");
//        Object result;
//        result = joinPoint.proceed();
//        System.out.println("go go drink!!!!");
//        return result;
//    }
}
