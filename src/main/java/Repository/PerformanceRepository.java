package Repository;

import Model.Performance;

import java.util.List;

public class PerformanceRepository extends Repository.Abstract<Performance, Integer> {
    public PerformanceRepository(Class<Performance> entityClass) {
        super(entityClass);
    }

    public List<Performance> searchByClass(int _class) {
        return searchByField("_class", String.valueOf(_class));
    }

    public List<Performance> searchByYear(String year) {
        return searchByField("year", year);
    }

    public List<Performance> searchBySubject(String subject) {
        return searchByField("subject", subject);
    }

    public List<Performance> searchByAnnualGrade(int annualGrade) {
        return searchByField("annualGrade", String.valueOf(annualGrade));
    }

    public List<Performance> searchByExaminationGrade(int examinationGrade) {
        return searchByField("examinationGrade", String.valueOf(examinationGrade));
    }

    public List<Performance> searchByQuarterlyGrade(int quarterlyGrade) {
        return searchByField("quarterlyGrade", String.valueOf(quarterlyGrade));
    }

    public List<Performance> searchBySemesterGrade(int semesterGrade) {
        return searchByField("semesterGrade", String.valueOf(semesterGrade));
    }

    public List<Performance> searchByFinalGrade(int finalGrade) {
        return searchByField("finalGrade", String.valueOf(finalGrade));
    }
}
