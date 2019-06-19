//package com.itacademy.database.repository;
//
//import com.itacademy.database.entity.Category;
//import org.springframework.data.repository.Repository;
//
//public interface CategoryRepository extends Repository<Category, Long> {
//
////    @Autowired
////    SessionFactory getSessionFactory();
//
////    @Cacheable("categories")
////    default List<Category> getAll() {
////        Class<Category> clazz = Category.class;
////        return getSessionFactory().getCurrentSession()
////                .createQuery(String.format("select e from %s e", clazz.getSimpleName()), clazz)
////                .list();
////    }
//
//    Category findById(Long id);
//}