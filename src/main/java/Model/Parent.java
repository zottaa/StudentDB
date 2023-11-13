package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @Column(name = "relation_degree")
    private String relationDegree;

    @ManyToMany(mappedBy = "parentList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> studentList;

    public Parent() {

    }

    public Parent(String lastname, String name, String surname, String address, String relationDegree) {
        this.lastname = lastname;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.relationDegree = relationDegree;
        this.studentList = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        if (!studentList.contains(student)) {
            studentList.add(student);
            student.addParent(this);
        }
    }


    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getRelationDegree() {
        return relationDegree;
    }

    public List<Student> getStudentList() {
        return studentList;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRelationDegree(String relationDegree) {
        this.relationDegree = relationDegree;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }


    @Override
    public String toString() {
        return "Model.Parent{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", relationDegree='" + relationDegree + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return id == parent.id && Objects.equals(lastname, parent.lastname) && Objects.equals(name, parent.name) && Objects.equals(surname, parent.surname) && Objects.equals(address, parent.address) && Objects.equals(relationDegree, parent.relationDegree) && Objects.equals(studentList, parent.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, name, surname, address, relationDegree, studentList);
    }
}
