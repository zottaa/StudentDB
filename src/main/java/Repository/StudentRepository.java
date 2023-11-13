package Repository;

import Model.Student;

import java.util.List;

public class StudentRepository extends Repository.Abstract<Student, Integer> {
    public StudentRepository(Class<Student> entityClass) {
        super(entityClass);
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

    public List<Student> searchByClass(int _class) {
        return searchByField("_class", String.valueOf(_class));
    }
}
