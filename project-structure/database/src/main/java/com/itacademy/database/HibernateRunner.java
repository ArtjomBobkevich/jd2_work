package com.itacademy.database;

import com.itacademy.database.util.SessionManager;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateRunner {

    private static SessionFactory factory = SessionManager.getFactory();

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Object dataSource = classPathXmlApplicationContext.getBean("dataSource");
        System.out.println();
    }
}