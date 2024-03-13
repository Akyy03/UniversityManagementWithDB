package uis;

import java.util.Scanner;

public class UniversityUI {

    StudentsUI studentsUI = new StudentsUI();
    ProfessorsUI professorsUI = new ProfessorsUI();
    ClassesUI classesUI = new ClassesUI();
    SchedulesUI schedulesUI = new SchedulesUI();

    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while (choice != 0) {
            try {
                System.out.print("Loading");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(400);
                System.out.print(".");
                Thread.sleep(400);
                System.out.println(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\nWelcome to our University!");
            System.out.println("Please use one of the following options:\n");

            System.out.println("1. Students Menu");
            System.out.println("2. Professors Menu");
            System.out.println("3. Classes");
            System.out.println("4. Schedules");

            System.out.println("0. Close");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> studentsUI.studentsMenu();

                    case 2 -> professorsUI.professorsMenu();

                    case 3 -> classesUI.classesMenu();

                    case 4 -> schedulesUI.schedulesMenu();

                    case 0 -> System.exit(0);

                    default -> System.out.println("Please use a valid option (0 - 4).");
                }
            } else {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.next();
            }
        }
    }
}
