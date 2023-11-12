import Model.Parent;
import Model.Performance;
import Model.Student;
import Util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List arrayList = session.createQuery("From Parent").list();
            for (Object parent : arrayList) {
                Parent temp = (Parent) parent;
                System.out.println(parent.toString());
                List<Student> studentList = temp.getStudentList();
                if (!studentList.isEmpty()) {
                    for (Object student : studentList) {
                        Student temp2 = (Student)student;
                        List<Performance> performances = temp2.getPerformanceList();
                        for (Performance performance : performances) {
                            System.out.println(performance.toString());
                        }
                        System.out.println(student.toString());
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
