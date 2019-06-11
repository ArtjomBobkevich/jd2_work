package com.itacademy.web.config;

import com.itacademy.service.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.itacademy.web")
@EnableWebMvc
@Import(ServiceConfig.class)
public class WebConfig {

    /*не понятно этот веб конфиг должен импортиться или нет*/
/*25:11*/
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}