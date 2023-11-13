package Repository;

import Model.Performance;

import java.util.List;

public class PerformanceRepository extends Repository.Abstract<Performance, Integer> {
    public List<Performance> searchByClass(int _class) {
        return searchByField(Performance.class, "_class", String.valueOf(_class));
    }

    public List<Performance> searchByYear(String year) {
        return searchByField(Performance.class, "year", year);
    }

    public List<Performance> searchBySubject(String subject) {
        return searchByField(Performance.class, "subject", subject);
    }

    public List<Performance> searchByAnnualGrade(int annualGrade) {
        return searchByField(Performance.class, "annualGrade", String.valueOf(annualGrade));
    }

    public List<Performance> searchByExaminationGrade(int examinationGrade) {
        return searchByField(Performance.class, "examinationGrade", String.valueOf(examinationGrade));
    }

    public List<Performance> searchByQuarterlyGrade(int quarterlyGrade) {
        return searchByField(Performance.class, "quarterlyGrade", String.valueOf(quarterlyGrade));
    }

    public List<Performance> searchBySemesterGrade(int semesterGrade) {
        return searchByField(Performance.class, "semesterGrade", String.valueOf(semesterGrade));
    }

    public List<Performance> searchByFinalGrade(int finalGrade) {
        return searchByField(Performance.class, "finalGrade", String.valueOf(finalGrade));
    }
}
