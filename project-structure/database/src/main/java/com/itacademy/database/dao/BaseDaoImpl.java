//package com.itacademy.database.dao;
//
//import com.itacademy.database.entity.BaseEntity;
//import lombok.Cleanup;
//import org.hibernate.Session;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.Optional;
//
//import static com.itacademy.database.util.SessionManager.getSession;
//
//public class BaseDaoImpl<T extends Serializable, E extends BaseEntity<T>> implements BaseDao<T, E> {
//
//    private Class<E> clazz;
//
//    @Override
//    public T save(E entity) {
//        @Cleanup Session session = getSession();
//        session.getTransaction();
//        session.save(entity);
//        session.getTransaction().commit();
//        return entity.getId();
//    }
//
//    @Override
//    public Optional<E> get(T id) {
//        @Cleanup Session session = getSession();
//        return Optional.ofNullable(session.get(clazz, id));
//    }
//
//    @Override
//    public void update(E entity) {
//        @Cleanup Session session = getSession();
//        session.beginTransaction();
//        session.update(entity);
//        session.getTransaction().commit();
//    }
//
//    @Override
//    public void delete(E entity) {
//        @Cleanup Session session = getSession();
//        session.beginTransaction();
//        session.delete(entity);
//        session.getTransaction().commit();
//    }
//
//    @Override
//    public List<E> getAll() {
//        @Cleanup Session session = getSession();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<E> criteria = cb.createQuery(clazz);
//        Root<E> root = criteria.from(clazz);
//        criteria.select(root);
//        return session.createQuery(criteria).list();
//    }
//
//    @SuppressWarnings("unchecked")
//    public BaseDaoImpl() {
//        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//        this.clazz = (Class<E>) type;
//    }
//}
