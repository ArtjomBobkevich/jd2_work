package com.itacademy.database.config;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan("com.itacademy.database")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(@Value("${db.username}") String username,@Value("${db.url}") String url,
                                 @Value("${db.password}") String password,@Value("${db.driverClassName}") String driverClassName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(username);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.itacademy.database.entity");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public Properties hibernateProperties (@Value("classpath:application.properties")Resource resource) throws IOException {
        @Cleanup InputStream inputStream = resource.getInputStream();
        Properties properties = new Properties();
        properties.load(inputStream);

        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager (SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}