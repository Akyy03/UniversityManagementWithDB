package uis;

import services.StudentsService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentsUI {

    StudentsService studentsService = new StudentsService();

    public void studentsMenu() {
        UniversityUI universityUI = new UniversityUI();
        Scanner scanner = new Scanner(System.in);

        int studentsChoice = -1;
        while (studentsChoice != 0) {

            System.out.println("\nStudents Menu");
            System.out.println("Please choose an option to continue: \n");

            System.out.println("1. Show students");
            System.out.println("2. Add a student");
            System.out.println("3. Update student's information");
            System.out.println("4. Remove a student");
            System.out.println("5. Find a student");
            System.out.println("0. Back");

            try {
                studentsChoice = scanner.nextInt();

                switch (studentsChoice) {
                    case 1 -> studentsService.showStudents();

                    case 2 -> studentsService.addStudent();

                    case 3 -> studentsService.updateStudent();

                    case 4 -> studentsService.removeStudent();

                    case 5 -> studentsService.findStudent();

                    case 0 -> universityUI.mainMenu();

                    default -> System.out.println("Please use a valid option (0 - 5).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }
}

