package Repository;

import Util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public interface Repository<T, ID extends Serializable> {
    public T findById(ID entityId);

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

    public List<T> findAll();

    public List<T> searchByField(String field, String keyword);

    abstract class Abstract<T, ID extends Serializable> implements Repository<T, ID> {
        private final Class<T> entityClass;

        Abstract(Class<T> entityClass) {
            this.entityClass = entityClass;
        }
        @Override
        public T findById(ID entityId) {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                return session.get(entityClass, entityId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void save(T entity) {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void update(T entity) {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void delete(T entity) {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(entity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public List<T> findAll() {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                String hql = "From " + entityClass.getSimpleName();
                return session.createQuery(hql).list();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public List<T> searchByField(String field, String keyword) {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                String hql = "FROM " + entityClass.getSimpleName() +
                        " WHERE lower(" + field + ") LIKE :keyword";
                Query query = session.createQuery(hql, entityClass);
                query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
                return query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
