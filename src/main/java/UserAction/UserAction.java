package UserAction;

import Model.Parent;
import Model.Performance;
import Model.Student;
import Service.ParentService;
import Service.PerformanceService;
import Service.StudentService;

import java.util.List;
import java.util.Scanner;

public class UserAction {


    private final StudentService studentService;
    private final ParentService parentService;
    private final PerformanceService performanceService;

    UserAction() {
        this.studentService = new StudentService();
        this.parentService = new ParentService();
        this.performanceService = new PerformanceService();
    }

    public void showStudents(ShowOptions options) {
        List<Student> studentList = studentService.findAll();
        printStudents(studentList, options);
    }

    private void printStudents(List<Student> students, ShowOptions options) {
        System.out.println("Students:");
        String separator = "-".repeat(200);
        System.out.println(separator);
        for (Student student : students) {
            System.out.println(student.toString());
            if (options.isShowParents()) {
                System.out.println("Related Parents:");
                for (Parent parent : student.getParentList()) {
                    System.out.println(parent.toString());
                }
            }
            if (options.isShowPerformances()) {
                System.out.println("Related Performances:");
                for (Performance performance : student.getPerformanceList()) {
                    System.out.println(performance.toString());
                }
            }
            System.out.println(separator);
        }
    }

    public void showParents(ShowOptions options) {
        List<Parent> parentList = parentService.findAll();
        printParents(parentList, options);
    }

    private void printParents(List<Parent> parents, ShowOptions options) {
        System.out.println("Parents:");
        String separator = "-".repeat(200);
        System.out.println(separator);
        for (Parent parent : parents) {
            System.out.println(parent.toString());
            if (options.isShowStudents()) {
                System.out.println("Related Students:");
                for (Student student : parent.getStudentList()) {
                    System.out.println(student.toString());
                }
            }
            System.out.println(separator);
        }
    }

    public void showPerformance() {
        List<Performance> performancesList = performanceService.findAll();
        printPerformance(performancesList);
    }

    private void printPerformance(List<Performance> performances) {
        System.out.println("Performances:");
        String separator = "-".repeat(200);
        System.out.println(separator);
        for (Performance performance : performances) {
            System.out.println(performance.toString());
            System.out.println("Related Student:");
            System.out.println(performance.getStudent().toString());
            System.out.println(separator);
        }
    }

    public void deleteStudentById() {
        showStudents(new ShowOptions(true, true));

        int studentIdToDelete = readIdFromConsole();

        Student studentToDelete = studentService.findById(studentIdToDelete);

        if (studentToDelete != null) {
            studentService.delete(studentToDelete);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student with such id not found.");
        }
    }

    public void deleteParentById() {
        showParents(new ShowOptions(true));

        int parentIdToDelete = readIdFromConsole();

        Parent parentToDelete = parentService.findById(parentIdToDelete);

        if (parentToDelete != null) {
            parentService.delete(parentToDelete);
            System.out.println("Parent deleted.");
        } else {
            System.out.println("Parent with such id not found.");
        }
    }

    public void deletePerformanceById() {
        showPerformance();

        int performanceIdToDelete = readIdFromConsole();

        Performance performanceToDelete = performanceService.findById(performanceIdToDelete);

        if (performanceToDelete != null) {
            performanceService.delete(performanceToDelete);
            System.out.println("Performance deleted.");
        } else {
            System.out.println("Performance with such id not found.");
        }
    }

    public void createAndSaveStudent() {
        Student newStudent = createStudentFromConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to add parents? (yes/no): ");
        String addParentChoice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(addParentChoice)) {
            do {
                Parent parent = createParentFromConsoleOrChooseExisting();
                newStudent.addParent(parent);

                System.out.print("Do you want to add another parent? (yes/no): ");
                addParentChoice = scanner.nextLine();
            } while ("yes".equalsIgnoreCase(addParentChoice));
        }

        System.out.print("Do you want to add performances? (yes/no): ");
        String addPerformanceChoice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(addPerformanceChoice)) {
            do {
                Performance performance = createPerformanceFromConsole();
                newStudent.addPerformance(performance);

                System.out.print("Do you want to add another performance? (yes/no): ");
                addPerformanceChoice = scanner.nextLine();
            } while ("yes".equalsIgnoreCase(addPerformanceChoice));
        }
        studentService.save(newStudent);
    }

    private Student createStudentFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add new student data:");

        System.out.print("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Class: ");
        int _class = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Birth Date: ");
        String birthDate = scanner.nextLine();

        System.out.print("Entry Year: ");
        String entryYear = scanner.nextLine();

        return new Student(lastname, name, surname, _class, address, birthDate, entryYear);
    }

    private Parent createParentFromConsoleOrChooseExisting() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to create a new parent or choose an existing one? (new/choose): ");
        String createOrChoose = scanner.nextLine();

        if ("new".equalsIgnoreCase(createOrChoose)) {
            return createParentFromConsole();
        } else if ("choose".equalsIgnoreCase(createOrChoose)) {
            return chooseExistingParent();
        } else {
            System.out.println("Invalid choice. Creating a new parent by default.");
            return createParentFromConsole();
        }
    }

    private Parent chooseExistingParent() {
        showParents(new ShowOptions(true));
        Parent parent;
        do {
            int parentId = readIdFromConsole();
            parent = parentService.findById(parentId);
        } while (parent == null);
        return parent;
    }

    public void createAndSaveParent() {
        Parent newParent = createParentFromConsole();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to add students? (yes/no): ");
        String addStudentChoice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(addStudentChoice)) {
            do {
                Student student = createStudentFromConsoleOrChooseExisting();
                newParent.addStudent(student);

                System.out.print("Do you want to add another student? (yes/no): ");
                addStudentChoice = scanner.nextLine();
            } while ("yes".equalsIgnoreCase(addStudentChoice));
        }

        parentService.save(newParent);
    }

    private Student createStudentFromConsoleOrChooseExisting() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to create a new student or choose an existing one? (new/choose): ");
        String createOrChoose = scanner.nextLine();

        if ("new".equalsIgnoreCase(createOrChoose)) {
            return createStudentFromConsole();
        } else if ("choose".equalsIgnoreCase(createOrChoose)) {
            return chooseExistingStudent();
        } else {
            System.out.println("Invalid choice. Creating a new student by default.");
            return createStudentFromConsole();
        }
    }

    private Parent createParentFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add new parent data:");

        System.out.print("Lastname: ");
        String lastname = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Surname: ");
        String surname = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Relation Degree: ");
        String relationDegree = scanner.nextLine();

        return new Parent(lastname, name, surname, address, relationDegree);
    }

    private Student chooseExistingStudent() {
        showStudents(new ShowOptions(true, true));
        Student student;
        do {
            int studentId = readIdFromConsole();
            student = studentService.findById(studentId);
        } while (student == null);
        return student;
    }

    public void createAndSavePerformance() {
        Performance newPerformance = createPerformanceFromConsole();

        Student student = createStudentFromConsoleOrChooseExisting();
        newPerformance.setStudent(student);

        performanceService.save(newPerformance);
    }

    private Performance createPerformanceFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add new performance data:");

        System.out.print("Annual Grade: ");
        int annualGrade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Examination Grade: ");
        int examinationGrade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Quarterly Grade: ");
        int quarterlyGrade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Semester Grade: ");
        int semesterGrade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Final Grade: ");
        int finalGrade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Class: ");
        int _class = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Year: ");
        String year = scanner.nextLine();

        System.out.print("Subject: ");
        String subject = scanner.nextLine();

        return new Performance(annualGrade, examinationGrade, quarterlyGrade, semesterGrade, finalGrade, _class, year, subject);
    }

    public void updateStudentFromConsole() {
        showStudents(new ShowOptions(true, true));

        Student student;
        do {
            int studentId = readIdFromConsole();
            student = studentService.findById(studentId);
        } while (student == null);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update student data:");

        System.out.print("Lastname (" + student.getLastname() + "): ");
        String lastname = scanner.nextLine();
        if (!lastname.isEmpty()) {
            student.setLastname(lastname);
        }

        System.out.print("Name (" + student.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Surname (" + student.getSurname() + "): ");
        String surname = scanner.nextLine();
        if (!surname.isEmpty()) {
            student.setSurname(surname);
        }

        System.out.print("Class (" + student.get_class() + "): ");
        String classInput = scanner.nextLine();
        if (!classInput.isEmpty()) {
            int _class = Integer.parseInt(classInput);
            student.set_class(_class);
        }

        System.out.print("Address (" + student.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            student.setAddress(address);
        }

        System.out.print("Birth Date (" + student.getBirthDate() + "): ");
        String birthDate = scanner.nextLine();
        if (!birthDate.isEmpty()) {
            student.setBirthDate(birthDate);
        }

        System.out.print("Entry Year (" + student.getEntryYear() + "): ");
        String entryYear = scanner.nextLine();
        if (!entryYear.isEmpty()) {
            student.setEntryYear(entryYear);
        }

        System.out.print("Do you want to update parents? (yes/no): ");
        String updateParentsChoice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(updateParentsChoice)) {
            for (Parent parent : student.getParentList()) {
                updateParentFromConsole(parent);
            }
        }

        System.out.print("Do you want to update performances? (yes/no): ");
        String updatePerformancesChoice = scanner.nextLine();

        if ("yes".equalsIgnoreCase(updatePerformancesChoice)) {
            for (Performance performance : student.getPerformanceList()) {
                updatePerformanceFromConsole(performance);
            }
        }
        studentService.update(student);
    }

    private void updateParentFromConsole(Parent parent) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update parent data::");

        System.out.print("Lastname (" + parent.getLastname() + "): ");
        String lastname = scanner.nextLine();
        if (!lastname.isEmpty()) {
            parent.setLastname(lastname);
        }

        System.out.print("Name (" + parent.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            parent.setName(name);
        }

        System.out.print("Surname (" + parent.getSurname() + "): ");
        String surname = scanner.nextLine();
        if (!surname.isEmpty()) {
            parent.setSurname(surname);
        }

        System.out.print("Address (" + parent.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            parent.setAddress(address);
        }

        System.out.print("Relation Degree (" + parent.getRelationDegree() + "): ");
        String relationDegree = scanner.nextLine();
        if (!relationDegree.isEmpty()) {
            parent.setRelationDegree(relationDegree);
        }
    }

    private void updatePerformanceFromConsole(Performance performance) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update performance data:");

        System.out.print("Annual Grade (" + performance.getAnnualGrade() + "): ");
        String annualGradeInput = scanner.nextLine();
        if (!annualGradeInput.isEmpty()) {
            int annualGrade = Integer.parseInt(annualGradeInput);
            performance.setAnnualGrade(annualGrade);
        }

        System.out.print("Examination Grade (" + performance.getExaminationGrade() + "): ");
        String examinationGradeInput = scanner.nextLine();
        if (!examinationGradeInput.isEmpty()) {
            int examinationGrade = Integer.parseInt(examinationGradeInput);
            performance.setExaminationGrade(examinationGrade);
        }

        System.out.print("Quarterly Grade (" + performance.getQuarterlyGrade() + "): ");
        String quarterlyGradeInput = scanner.nextLine();
        if (!quarterlyGradeInput.isEmpty()) {
            int quarterlyGrade = Integer.parseInt(quarterlyGradeInput);
            performance.setQuarterlyGrade(quarterlyGrade);
        }

        System.out.print("Semester Grade (" + performance.getSemesterGrade() + "): ");
        String semesterGradeInput = scanner.nextLine();
        if (!semesterGradeInput.isEmpty()) {
            int semesterGrade = Integer.parseInt(semesterGradeInput);
            performance.setSemesterGrade(semesterGrade);
        }

        System.out.print("Final Grade (" + performance.getFinalGrade() + "): ");
        String finalGradeInput = scanner.nextLine();
        if (!finalGradeInput.isEmpty()) {
            int finalGrade = Integer.parseInt(finalGradeInput);
            performance.setFinalGrade(finalGrade);
        }

        System.out.print("Class (" + performance.get_class() + "): ");
        String classInput = scanner.nextLine();
        if (!classInput.isEmpty()) {
            int _class = Integer.parseInt(classInput);
            performance.set_class(_class);
        }

        System.out.print("Year (" + performance.getYear() + "): ");
        String year = scanner.nextLine();
        if (!year.isEmpty()) {
            performance.setYear(year);
        }

        System.out.print("Subject (" + performance.getSubject() + "): ");
        String subject = scanner.nextLine();
        if (!subject.isEmpty()) {
            performance.setSubject(subject);
        }
    }

    public void updateParentFromConsoleById() {
        showParents(new ShowOptions(true));

        Parent parent;
        do {
            int parentId = readIdFromConsole();
            parent = parentService.findById(parentId);
        } while (parent == null);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update parent data:");

        System.out.print("Lastname (" + parent.getLastname() + "): ");
        String lastname = scanner.nextLine();
        if (!lastname.isEmpty()) {
            parent.setLastname(lastname);
        }

        System.out.print("Name (" + parent.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            parent.setName(name);
        }

        System.out.print("Surname (" + parent.getSurname() + "): ");
        String surname = scanner.nextLine();
        if (!surname.isEmpty()) {
            parent.setSurname(surname);
        }

        System.out.print("Address (" + parent.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            parent.setAddress(address);
        }

        System.out.print("Relation Degree (" + parent.getRelationDegree() + "): ");
        String relationDegree = scanner.nextLine();
        if (!relationDegree.isEmpty()) {
            parent.setRelationDegree(relationDegree);
        }
        parentService.update(parent);
    }


    public void updatePerformanceFromConsoleById() {
        showPerformance();

        Performance performance;
        do {
            int performanceId = readIdFromConsole();
            performance = performanceService.findById(performanceId);
        } while (performance == null);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update performance data:");

        System.out.print("Annual Grade (" + performance.getAnnualGrade() + "): ");
        String annualGradeInput = scanner.nextLine();
        if (!annualGradeInput.isEmpty()) {
            int annualGrade = Integer.parseInt(annualGradeInput);
            performance.setAnnualGrade(annualGrade);
        }

        System.out.print("Examination Grade (" + performance.getExaminationGrade() + "): ");
        String examinationGradeInput = scanner.nextLine();
        if (!examinationGradeInput.isEmpty()) {
            int examinationGrade = Integer.parseInt(examinationGradeInput);
            performance.setExaminationGrade(examinationGrade);
        }

        System.out.print("Quarterly Grade (" + performance.getQuarterlyGrade() + "): ");
        String quarterlyGradeInput = scanner.nextLine();
        if (!quarterlyGradeInput.isEmpty()) {
            int quarterlyGrade = Integer.parseInt(quarterlyGradeInput);
            performance.setQuarterlyGrade(quarterlyGrade);
        }

        System.out.print("Semester Grade (" + performance.getSemesterGrade() + "): ");
        String semesterGradeInput = scanner.nextLine();
        if (!semesterGradeInput.isEmpty()) {
            int semesterGrade = Integer.parseInt(semesterGradeInput);
            performance.setSemesterGrade(semesterGrade);
        }

        System.out.print("Final Grade (" + performance.getFinalGrade() + "): ");
        String finalGradeInput = scanner.nextLine();
        if (!finalGradeInput.isEmpty()) {
            int finalGrade = Integer.parseInt(finalGradeInput);
            performance.setFinalGrade(finalGrade);
        }

        System.out.print("Class (" + performance.get_class() + "): ");
        String classInput = scanner.nextLine();
        if (!classInput.isEmpty()) {
            int _class = Integer.parseInt(classInput);
            performance.set_class(_class);
        }

        System.out.print("Year (" + performance.getYear() + "): ");
        String year = scanner.nextLine();
        if (!year.isEmpty()) {
            performance.setYear(year);
        }

        System.out.print("Subject (" + performance.getSubject() + "): ");
        String subject = scanner.nextLine();
        if (!subject.isEmpty()) {
            performance.setSubject(subject);
        }

        performanceService.update(performance);
    }


    private int readIdFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter id: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please, enter integer: ");
            scanner.next();
        }

        return scanner.nextInt();
    }

    public void performStudentSearch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose search criteria:");
        System.out.println("1. By Lastname");
        System.out.println("2. By Name");
        System.out.println("3. By Surname");
        System.out.println("4. By Address");
        System.out.println("5. By Birth Date");
        System.out.println("6. By Entry Year");
        System.out.println("7. By Class");
        System.out.print("Enter the number of your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Student> searchResult = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Lastname: ");
                String lastname = scanner.nextLine();
                searchResult = studentService.searchByLastname(lastname);
                break;

            case 2:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                searchResult = studentService.searchByName(name);
                break;

            case 3:
                System.out.print("Enter Surname: ");
                String surname = scanner.nextLine();
                searchResult = studentService.searchBySurname(surname);
                break;

            case 4:
                System.out.print("Enter Address: ");
                String address = scanner.nextLine();
                searchResult = studentService.searchByAddress(address);
                break;

            case 5:
                System.out.print("Enter Birth Date: ");
                String birthDate = scanner.nextLine();
                searchResult = studentService.searchByBirthDate(birthDate);
                break;

            case 6:
                System.out.print("Enter Entry Year: ");
                String entryYear = scanner.nextLine();
                searchResult = studentService.searchByEntryYear(entryYear);
                break;

            case 7:
                System.out.print("Enter Class: ");
                int _class = scanner.nextInt();
                searchResult = studentService.searchByClass(_class);
                break;

            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
        }
        if (searchResult != null) {
            printStudents(searchResult, new ShowOptions(true, true));
        }
    }

    public void performParentSearch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose search criteria:");
        System.out.println("1. By Lastname");
        System.out.println("2. By Name");
        System.out.println("3. By Surname");
        System.out.println("4. By Address");
        System.out.println("5. By Relation Degree");
        System.out.print("Enter the number of your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Parent> searchResult = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Lastname: ");
                String lastname = scanner.nextLine();
                searchResult = parentService.searchByLastname(lastname);
                break;

            case 2:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                searchResult = parentService.searchByName(name);
                break;

            case 3:
                System.out.print("Enter Surname: ");
                String surname = scanner.nextLine();
                searchResult = parentService.searchBySurname(surname);
                break;

            case 4:
                System.out.print("Enter Address: ");
                String address = scanner.nextLine();
                searchResult = parentService.searchByAddress(address);
                break;

            case 5:
                System.out.print("Enter Relation Degree: ");
                String relationDegree = scanner.nextLine();
                searchResult = parentService.searchByRelationDegree(relationDegree);
                break;

            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
        }

        if (searchResult != null) {
            printParents(searchResult, new ShowOptions(true));
        }
    }

    public void performPerformanceSearch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose search criteria:");
        System.out.println("1. By Subject");
        System.out.println("2. By Year");
        System.out.println("3. By Class");
        System.out.println("4. By Annual Grade");
        System.out.println("5. By Semester Grade");
        System.out.println("6. By Quarterly Grade");
        System.out.println("7. By Examination Grade");
        System.out.println("8. By Final Grade");
        System.out.print("Enter the number of your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Performance> searchResult = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Subject: ");
                String subject = scanner.nextLine();
                searchResult = performanceService.searchBySubject(subject);
                break;

            case 2:
                System.out.print("Enter Year: ");
                String year = scanner.nextLine();
                searchResult = performanceService.searchByYear(year);
                break;

            case 3:
                System.out.print("Enter Class: ");
                int _class = scanner.nextInt();
                searchResult = performanceService.searchByClass(_class);
                break;

            case 4:
                System.out.print("Enter Annual Grade: ");
                int annualGrade = scanner.nextInt();
                searchResult = performanceService.searchByAnnualGrade(annualGrade);
                break;

            case 5:
                System.out.print("Enter Semester Grade: ");
                int semesterGrade = scanner.nextInt();
                searchResult = performanceService.searchBySemesterGrade(semesterGrade);
                break;

            case 6:
                System.out.print("Enter Quarterly Grade: ");
                int quarterlyGrade = scanner.nextInt();
                searchResult = performanceService.searchByQuarterlyGrade(quarterlyGrade);
                break;

            case 7:
                System.out.print("Enter Examination Grade: ");
                int examinationGrade = scanner.nextInt();
                searchResult = performanceService.searchByExaminationGrade(examinationGrade);
                break;

            case 8:
                System.out.print("Enter Final Grade: ");
                int finalGrade = scanner.nextInt();
                searchResult = performanceService.searchByFinalGrade(finalGrade);
                break;

            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
        }

        if (searchResult != null) {
            printPerformance(searchResult);
        }
    }
}
