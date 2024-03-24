package uis;

import services.SchedulesService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SchedulesUI {
    SchedulesService schedulesService = new SchedulesService();
    public void schedulesMenu(){

        UniversityUI universityUI = new UniversityUI();
        Scanner scanner = new Scanner(System.in);

        int schedulesChoice = -1;
        while (schedulesChoice != 0) {

            System.out.println("\nSchedules Menu");
            System.out.println("Please choose an option to continue: \n");

            System.out.println("1. Show all schedules");
            System.out.println("2. Add a new class schedule");
            System.out.println("3. Update an existing schedule's information");
            System.out.println("4. Remove a class schedule");
            System.out.println("5. Find a class schedule");
            System.out.println("6. Assign to schedule");
            System.out.println("0. Back");

            try {
                schedulesChoice = scanner.nextInt();

                switch (schedulesChoice) {
                    case 1 -> schedulesService.showSchedules();

                    case 2 -> schedulesService.addSchedule();

                    case 3 -> schedulesService.updateSchedule();

                    case 4 -> schedulesService.removeSchedule();

                    case 5 -> schedulesService.findSchedule();

                    case 6 -> schedulesService.makeSchedule();

                    case 0 -> universityUI.mainMenu();

                    default -> System.out.println("Please use a valid option (0 - 6).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }
}
