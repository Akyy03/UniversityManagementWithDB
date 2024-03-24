package services;

import controllers.SchedulesController;
import models.SchedulesModel;
import uis.SchedulesUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchedulesService {
    SchedulesController schedulesController = new SchedulesController();
    SchedulesModel schedulesModel = new SchedulesModel();

    public void addSchedule() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter schedule's group name (or type 'quit' to cancel): ");
            String scheduleName = scanner.nextLine();
            if (scheduleName.equalsIgnoreCase("quit")) {
                break;
            }

            schedulesModel.setScheduleName(scheduleName);
            schedulesController.addSchedule(schedulesModel);
            System.out.println("Schedule added successfully!\n");
        }
    }

    public void showSchedules() {
        List<SchedulesModel> schedulesList = schedulesController.showSchedules(schedulesModel);
        if (schedulesList.isEmpty()) {
            System.out.println("No schedule found.\n");
        } else {
            System.out.println("All schedules: \n");
            for (SchedulesModel schedules : schedulesList) {
                System.out.println("Schedule ID: " + schedules.getScheduleId() +
                        " | Name: " + schedules.getScheduleName());
            }
        }
    }

    public void updateSchedule() {
        SchedulesUI schedulesUI = new SchedulesUI();
        List<SchedulesModel> schedulesList = schedulesController.showSchedules(schedulesModel);
        Scanner scanner = new Scanner(System.in);
        if (schedulesList.isEmpty()) {
            System.out.println("No schedule found.\n");
        } else {
            showSchedules();
            System.out.println("Enter schedule ID for update: ");
            int scheduleId = scanner.nextInt();
            scanner.nextLine();

            SchedulesModel scheduleToUpdate = null;
            for (SchedulesModel schedule : schedulesList) {
                if (schedule.getScheduleId() == scheduleId) {
                    scheduleToUpdate = schedule;
                    break;
                }
            }

            if (scheduleToUpdate == null) {
                System.out.println("Schedule with ID " + scheduleId + " not found.\n");
            } else {
                System.out.println("What do you want to update?: ");
                System.out.println("1. Schedule name");
                System.out.println("0. Back");
                int update = scanner.nextInt();
                scanner.nextLine();

                if (update == 1) {
                    System.out.println("Enter new schedule name (or leave blank to keep current): ");
                    String newScheduleName = scanner.nextLine();
                    if (!newScheduleName.isEmpty()) {
                        scheduleToUpdate.setScheduleName(newScheduleName);
                    }
                    System.out.println("Schedule name updated successfully!");
                } else if (update == 0) {
                    schedulesUI.schedulesMenu();
                }
                schedulesController.updateSchedules(scheduleToUpdate);
            }
        }
    }

    public void removeSchedule() {
        List<SchedulesModel> schedulesList = schedulesController.showSchedules(schedulesModel);
        Scanner scanner = new Scanner(System.in);
        if (schedulesList.isEmpty()) {
            System.out.println("No schedules found.\n");
        } else {
            showSchedules();
            System.out.println("Enter schedule ID in order to remove: ");
            int scheduleId = scanner.nextInt();
            scanner.nextLine();

            SchedulesModel scheduleToRemove = null;
            for (SchedulesModel schedules : schedulesList) {
                if (schedules.getScheduleId() == scheduleId) {
                    scheduleToRemove = schedules;
                    break;
                }
            }

            if (scheduleToRemove == null) {
                System.out.println("Schedule with ID " + scheduleId + " not found.\n");
            } else {
                schedulesController.removeSchedules(scheduleToRemove);
            }
        }
    }

    public void findSchedule() {
        SchedulesUI schedulesUI = new SchedulesUI();
        List<SchedulesModel> schedulesList = schedulesController.showSchedules(schedulesModel);
        Scanner scanner = new Scanner(System.in);
        if (schedulesList.isEmpty()) {
            System.out.println("No schedule found.\n");
        } else {
            System.out.println("How do you want to find the student?: ");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by schedule name");
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter schedule ID: ");
                int scheduleId = scanner.nextInt();
                scanner.nextLine();

                SchedulesModel scheduleToFind = null;
                for (SchedulesModel schedule : schedulesList) {
                    if (schedule.getScheduleId() == scheduleId) {
                        scheduleToFind = schedule;
                        break;
                    }
                }
                if (scheduleToFind == null) {
                    System.out.println("Schedule with ID " + scheduleId + " not found.\n");
                } else {
                    System.out.println("Schedule ID: " + scheduleToFind.getScheduleId() +
                            " | Name: " + scheduleToFind.getScheduleName());
                }
            } else if (choice == 2) {
                System.out.println("Enter schedule name: ");
                String scheduleName = scanner.nextLine();

                List<SchedulesModel> matchingSchedules = new ArrayList<>();

                for (SchedulesModel schedulesToFind : schedulesList) {
                    if (schedulesToFind.getScheduleName().toLowerCase().contains(scheduleName.toLowerCase())) {
                        matchingSchedules.add(schedulesToFind);
                    }
                }

                if (matchingSchedules.isEmpty()) {
                    System.out.println("No schedules found for names containing: " + scheduleName);
                } else {
                    System.out.println("All schedules with matching name: ");
                    for (SchedulesModel schedules : matchingSchedules) {
                        System.out.println("Schedule ID: " + schedules.getScheduleId() +
                                " | Name: " + schedules.getScheduleName());
                    }
                }
            } else if (choice == 0) {
                schedulesUI.schedulesMenu();
            }
        }
    }

    public void makeSchedule(){

        SchedulesUI schedulesUI = new SchedulesUI();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0){
            System.out.println("Schedule settings: \n");
            System.out.println("1. Assign students to schedule");
            System.out.println("2. Assign professors to schedule");
            System.out.println("3. Assign classes to schedule");
            System.out.println("0. Back");

            choice = scanner.nextInt();

            switch(choice){
                case 1 -> assignStudents();

                case 2 -> assignProfessor();

                case 3 -> assignClass();

                case 0 -> schedulesUI.schedulesMenu();

                default -> System.out.println("Please use a valid option (0 - 3).");
            }

        }
    }

    public void assignStudents(){

    }

    public void assignProfessor(){

    }

    public void assignClass(){

    }

}
