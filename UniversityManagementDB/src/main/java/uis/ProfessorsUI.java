package uis;

import services.ProfessorsService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProfessorsUI {

    ProfessorsService professorsService = new ProfessorsService();

    public void professorsMenu() {
        UniversityUI universityUI = new UniversityUI();
        Scanner scanner = new Scanner(System.in);

        int professorsChoice = -1;
        while (professorsChoice != 0) {

            System.out.println("\nProfessors Menu");
            System.out.println("Please choose an option to continue: \n");

            System.out.println("1. Show professors");
            System.out.println("2. Add a professor");
            System.out.println("3. Update professor's information");
            System.out.println("4. Remove a professor");
            System.out.println("5. Find a professor");
            System.out.println("0. Back");

            try {
                professorsChoice = scanner.nextInt();
                //scanner.nextLine();

                switch (professorsChoice) {
                    case 1 -> professorsService.showProfessors();

                    case 2 -> professorsService.addProfessor();

                    case 3 -> professorsService.updateProfessor();

                    case 4 -> professorsService.removeProfessor();

                    case 5 -> professorsService.findProfessor();

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

