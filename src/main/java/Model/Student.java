package Model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student {
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

    @Column(name = "class")
    private int _class;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "entry_year")
    private String entryYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Performance> performanceList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_to_parents",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "parent_id")}
    )
    private List<Parent> parentList;

    public Student() {

    }

    public Student(String lastname, String name, String surname, int _class, String address, String birthDate, String entryYear) {
        this.lastname = lastname;
        this.name = name;
        this.surname = surname;
        this._class = _class;
        this.address = address;
        this.birthDate = birthDate;
        this.entryYear = entryYear;
        this.parentList = new ArrayList<Parent>();
        this.performanceList = new ArrayList<Performance>();
    }

    public void addPerformance(Performance performance) {
        performance.setStudent(this);
        performanceList.add(performance);
    }

    public void addParent(Parent parent) {
        if (!parentList.contains(parent)) {
            parentList.add(parent);
            parent.addStudent(this);
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

    public int get_class() {
        return _class;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEntryYear() {
        return entryYear;
    }

    public List<Performance> getPerformanceList() {
        return performanceList;
    }

    public List<Parent> getParentList() {
        return parentList;
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

    public void set_class(int _class) {
        this._class = _class;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    public void setPerformanceList(ArrayList<Performance> performanceList) {
        this.performanceList = performanceList;
    }

   public void setParentList(ArrayList<Parent> parentList) {
        this.parentList = parentList;
    }

    @Override
    public String toString() {
        return "Model.Student{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", _class=" + _class +
                ", address='" + address + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", entryYear='" + entryYear + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && _class == student._class && Objects.equals(lastname, student.lastname) && Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(address, student.address) && Objects.equals(birthDate, student.birthDate) && Objects.equals(entryYear, student.entryYear) && Objects.equals(performanceList, student.performanceList) && Objects.equals(parentList, student.parentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, name, surname, _class, address, birthDate, entryYear, performanceList, parentList);
    }
}
