package UserAction;

import Model.Parent;
import Model.Performance;
import Model.Student;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final UserAction userAction;

    public Menu() {
        this.userAction = new UserAction();
    }

    public void displayMainMenu() {
        String[] mainMenuOptions = {
                "Show Students",
                "Show Parents",
                "Show Performances",
                "Delete Student by ID",
                "Delete Parent by ID",
                "Delete Performance by ID",
                "Create and Save Student",
                "Create and Save Parent",
                "Create and Save Performance",
                "Update Student from Console by ID",
                "Update Parent from Console by ID",
                "Update Performance from Console by ID",
                "Perform Student Search",
                "Perform Parent Search",
                "Perform Performance Search",
                "Exit"
        };

        int choice;
        do {
            choice = displayMenuAndGetChoice(mainMenuOptions);

            switch (choice) {
                case 1:
                    userAction.showStudents(new ShowOptions(true, true));
                    break;

                case 2:
                    userAction.showParents(new ShowOptions(true));
                    break;

                case 3:
                    userAction.showPerformance();
                    break;

                case 4:
                    userAction.deleteStudentById();
                    break;

                case 5:
                    userAction.deleteParentById();
                    break;

                case 6:
                    userAction.deletePerformanceById();
                    break;

                case 7:
                    userAction.createAndSaveStudent();
                    break;

                case 8:
                    userAction.createAndSaveParent();
                    break;

                case 9:
                    userAction.createAndSavePerformance();
                    break;

                case 10:
                    userAction.updateStudentFromConsole();
                    break;

                case 11:
                    userAction.updateParentFromConsoleById();
                    break;

                case 12:
                    userAction.updatePerformanceFromConsoleById();
                    break;

                case 13:
                    userAction.performStudentSearch();
                    break;

                case 14:
                    userAction.performParentSearch();
                    break;

                case 15:
                    userAction.performPerformanceSearch();
                    break;

                case 16:
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 16.");
            }
        } while (choice != 16);
    }

    private int displayMenuAndGetChoice(String[] options) {
        System.out.println("Choose an option:");

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        return readIntInput("Enter the number of your choice: ");
    }

    private int readIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
