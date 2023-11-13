package Model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table (name = "performance")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "annual_grade")
    private int annualGrade;

    @Column(name = "examination_grade")
    private int examinationGrade;

    @Column(name = "quarterly_grade")
    private int quarterlyGrade;

    @Column(name = "semester_grade")
    private int semesterGrade;

    @Column(name = "final_grade")
    private int finalGrade;

    @Column(name = "class")
    private int _class;

    @Column(name = "year")
    private String year;

    @Column(name = "subject")
    private String subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    public Performance() {

    }

    public Performance(int annualGrade, int examinationGrade, int quarterlyGrade, int semesterGrade, int finalGrade, int _class, String year, String subject) {
        this.annualGrade = annualGrade;
        this.examinationGrade = examinationGrade;
        this.quarterlyGrade = quarterlyGrade;
        this.semesterGrade = semesterGrade;
        this.finalGrade = finalGrade;
        this._class = _class;
        this.year = year;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public int getAnnualGrade() {
        return annualGrade;
    }

    public int getExaminationGrade() {
        return examinationGrade;
    }

    public int getQuarterlyGrade() {
        return quarterlyGrade;
    }

    public int getSemesterGrade() {
        return semesterGrade;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public int get_class() {
        return _class;
    }

    public String getYear() {
        return year;
    }

    public String getSubject() {
        return subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setAnnualGrade(int annualGrade) {
        this.annualGrade = annualGrade;
    }

    public void setExaminationGrade(int examinationGrade) {
        this.examinationGrade = examinationGrade;
    }

    public void setQuarterlyGrade(int quarterlyGrade) {
        this.quarterlyGrade = quarterlyGrade;
    }

    public void setSemesterGrade(int semesterGrade) {
        this.semesterGrade = semesterGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void set_class(int _class) {
        this._class = _class;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Model.Performance{" +
                "id=" + id +
                ", annualGrade=" + annualGrade +
                ", examinationGrade=" + examinationGrade +
                ", quarterlyGrade=" + quarterlyGrade +
                ", semesterGrade=" + semesterGrade +
                ", finalGrade=" + finalGrade +
                ", _class=" + _class +
                ", year='" + year + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performance that = (Performance) o;
        return id == that.id && annualGrade == that.annualGrade && examinationGrade == that.examinationGrade && quarterlyGrade == that.quarterlyGrade && semesterGrade == that.semesterGrade && finalGrade == that.finalGrade && _class == that._class && Objects.equals(year, that.year) && Objects.equals(subject, that.subject) && Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, annualGrade, examinationGrade, quarterlyGrade, semesterGrade, finalGrade, _class, year, subject, student);
    }
}
