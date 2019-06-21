package com.itacademy.service.aspect;

import com.itacademy.service.service.CategoryService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FirstAspect {

    private final static Logger LOG =
            LoggerFactory.getLogger(CategoryService.class);

    @Pointcut("execution(public * com.itacademy.service.service.*Service.*(..))")
    public void addLogging () {}

    @Before("addLogging()")
    public void before () {
        LOG.debug("Begin Logging... ");
        System.out.println("Begin Logging...");
    }

    @AfterThrowing("addLogging()")
    public void afterLikeFinally () {
        System.out.println("Problem was at Service...");
    }

    @AfterReturning("addLogging()")
    public void after () {
        System.out.println("Logging was completed!");
    }
}
