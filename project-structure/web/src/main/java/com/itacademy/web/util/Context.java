package com.itacademy.web.util;

import com.itacademy.service.config.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ServiceConfig.class);


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}