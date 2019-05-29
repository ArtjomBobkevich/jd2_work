package com.itacademy.database.dao;

        import com.itacademy.database.entity.Resource;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.cfg.Configuration;
        import org.junit.Test;

        import java.util.List;

        import static java.util.stream.Collectors.toList;

public class ResourceDaoTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @Test
    public void testAllFindByOrganizationName() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            List<Resource> results = ResourceDao.getResourceDao().findResourcesOrderByAuthor("2",0,2);
            List<String> fullNames = results.stream().map(Resource::getResourceName).collect(toList());
            System.out.println(fullNames);
            session.getTransaction().commit();
        }
    }
}