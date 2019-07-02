package com.itacademy.service.aspect;

import org.apache.log4j.Level;
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

        return res;

        /*возможно сделать метод void там повырывать что то из метода и самому в какой то файл сливать*/
    }

}