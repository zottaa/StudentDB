package Service;

import Model.Performance;
import Repository.PerformanceRepository;

import java.util.List;

public class PerformanceService extends Service.Abstract<Performance, Integer>{
    public PerformanceService() {
        super(new PerformanceRepository(Performance.class));
    }

    public List<Performance> searchBySubject(String keyword) {
        return ((PerformanceRepository) repository).searchBySubject(keyword);
    }

    public List<Performance> searchByYear(String keyword) {
        return ((PerformanceRepository) repository).searchByYear(keyword);
    }

    public List<Performance> searchByClass(int _class) {
        return ((PerformanceRepository) repository).searchByClass(_class);
    }

    public List<Performance> searchByAnnualGrade(int annualGrade) {
        return ((PerformanceRepository) repository).searchByAnnualGrade(annualGrade);
    }

    public List<Performance> searchBySemesterGrade(int semesterGrade) {
        return ((PerformanceRepository) repository).searchBySemesterGrade(semesterGrade);
    }

    public List<Performance> searchByQuarterlyGrade(int quarterlyGrade) {
        return ((PerformanceRepository) repository).searchByQuarterlyGrade(quarterlyGrade);
    }

    public List<Performance> searchByExaminationGrade(int examinationGrade) {
        return ((PerformanceRepository) repository).searchByExaminationGrade(examinationGrade);
    }

    public List<Performance> searchByFinalGrade(int finalGrade) {
        return ((PerformanceRepository) repository).searchByFinalGrade(finalGrade);
    }
}
