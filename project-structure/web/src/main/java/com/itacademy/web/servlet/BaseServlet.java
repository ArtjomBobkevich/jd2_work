package com.itacademy.web.servlet;

import com.itacademy.service.config.SeviceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SeviceConfig.class);

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
