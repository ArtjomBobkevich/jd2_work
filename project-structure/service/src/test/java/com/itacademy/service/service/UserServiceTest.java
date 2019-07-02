package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class UserServiceTest {

}