package uis;

import services.ClassesService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClassesUI {

    ClassesService classesService = new ClassesService();

    public void classesMenu() {
        UniversityUI universityUI = new UniversityUI();
        Scanner scanner = new Scanner(System.in);

        int classesChoice = -1;
        while (classesChoice != 0) {

            System.out.println("\nClasses Menu");
            System.out.println("Please choose an option to continue: \n");

            System.out.println("1. Show all classes");
            System.out.println("2. Add a new class");
            System.out.println("3. Update an existing class' information");
            System.out.println("4. Remove a class");
            System.out.println("5. Find a class");
            System.out.println("0. Back");

            try {
                classesChoice = scanner.nextInt();

                switch (classesChoice) {
                    case 1 -> classesService.showClasses();

                    case 2 -> classesService.addClass();

                    case 3 -> classesService.updateClass();

                    case 4 -> classesService.removeClass();

                    case 5 -> classesService.findClass();

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

