package com.itacademy.service.config;

import com.itacademy.database.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.itacademy.service.service")
@Import(DatabaseConfig.class)
public class SeviceConfig {
}