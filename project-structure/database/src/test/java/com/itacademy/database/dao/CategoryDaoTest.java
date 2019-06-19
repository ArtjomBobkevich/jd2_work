//package com.itacademy.database.dao;
//
//import com.itacademy.database.config.DatabaseConfigTest;
//import com.itacademy.database.entity.Category;
//import com.itacademy.database.repository.CategoryRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = DatabaseConfigTest.class)
//public class CategoryDaoTest {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Test
//    public void testGetAllCache() {
////        Category category = Category.builder()
////                .categoryName("test")
////                .fotoUrl("test")
////                .build();
////        categoryDao.save(category);
//
//        Category byId = categoryRepository.findById(1L);
//        Category byId1 = categoryRepository.findById(1L);
//    }
//}