package Service;

import Model.Student;
import Repository.StudentRepository;

import java.util.List;

public class StudentService extends Service.Abstract<Student, Integer> {
    public StudentService() {
        super(new StudentRepository(Student.class));
    }

    public List<Student> searchByLastname(String keyword) {
        return ((StudentRepository) repository).searchByLastname(keyword);
    }

    public List<Student> searchByName(String keyword) {
        return ((StudentRepository) repository).searchByName(keyword);
    }

    public List<Student> searchBySurname(String keyword) {
        return ((StudentRepository) repository).searchBySurname(keyword);
    }

    public List<Student> searchByAddress(String keyword) {
        return ((StudentRepository) repository).searchByAddress(keyword);
    }

    public List<Student> searchByBirthDate(String keyword) {
        return ((StudentRepository) repository).searchByBirthDate(keyword);
    }

    public List<Student> searchByEntryYear(String keyword) {
        return ((StudentRepository) repository).searchByEntryYear(keyword);
    }

    public List<Student> searchByClass(int _class) {
        return ((StudentRepository) repository).searchByClass(_class);
    }
}
