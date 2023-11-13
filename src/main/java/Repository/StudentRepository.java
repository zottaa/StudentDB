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

public class StudentRepository extends Repository.Abstract<Student, Integer> {
    public List<Student> searchByLastname(String keyword) {
        return searchByField(Student.class, "lastname", keyword);
    }

    public List<Student> searchByName(String keyword) {
        return searchByField(Student.class, "name", keyword);
    }

    public List<Student> searchBySurname(String keyword) {
        return searchByField(Student.class, "surname", keyword);
    }

    public List<Student> searchByAddress(String keyword) {
        return searchByField(Student.class, "address", keyword);
    }

    public List<Student> searchByBirthDate(String keyword) {
        return searchByField(Student.class, "birthDate", keyword);
    }

    public List<Student> searchByEntryYear(String keyword) {
        return searchByField(Student.class, "entryYear", keyword);
    }

    public List<Student> searchByClass(String keyword) {
        return searchByField(Student.class, "_class", keyword);
    }
}
