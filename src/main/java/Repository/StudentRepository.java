package Repository;

import Model.Student;
import Util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentRepository {
    public Student findById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Student student) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Student student) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> findAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("From Student").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> searchStudents(String keyword) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);

            Predicate[] predicates = {
                    builder.like(builder.lower(root.get("lastname")), "%" + keyword.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("surname")), "%" + keyword.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("address")), "%" + keyword.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("birthDate")), "%" + keyword.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("entryYear")), "%" + keyword.toLowerCase() + "%")
            };

            criteriaQuery.where(builder.or(predicates));

            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> searchByLastname(String keyword) {
        return searchByField("lastname", keyword);
    }

    public List<Student> searchByName(String keyword) {
        return searchByField("name", keyword);
    }

    public List<Student> searchBySurname(String keyword) {
        return searchByField("surname", keyword);
    }

    public List<Student> searchByAddress(String keyword) {
        return searchByField("address", keyword);
    }

    public List<Student> searchByBirthDate(String keyword) {
        return searchByField("birthDate", keyword);
    }

    public List<Student> searchByEntryYear(String keyword) {
        return searchByField("entryYear", keyword);
    }

    private List<Student> searchByField(String field, String keyword) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);

            Predicate predicate = builder.like(builder.lower(root.get(field)), "%" + keyword.toLowerCase() + "%");
            criteriaQuery.where(predicate);

            Query query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
