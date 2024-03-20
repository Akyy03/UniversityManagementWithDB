package services;

import controllers.ClassesController;
import models.ClassesModel;
import uis.ClassesUI;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClassesService {
    ClassesModel classesModel = new ClassesModel();
    ClassesController classesController = new ClassesController();

    public void addClass() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter class' subject (or type 'quit' to cancel): ");
            String className = scanner.nextLine();
            if (className.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter class' credits worth: ");
            int credits = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter class' day: ");
            String classDay = scanner.nextLine();

            boolean validStartHour = false;
            while (!validStartHour) {
                System.out.println("Enter class' start hour in the selected day (HH:mm): ");
                String startHour = scanner.nextLine();
                SimpleDateFormat startHourFormat = new SimpleDateFormat("HH:mm");
                try {
                    Date date = startHourFormat.parse(startHour);
                    Time startHourInput = new Time(date.getTime());
                    classesModel.setStartHour(startHourInput);
                    validStartHour = true;
                } catch (ParseException e) {
                    System.out.println("Invalid time format. Please enter time in HH:mm format.");
                }
            }

            boolean validEndHour = false;
            while (!validEndHour) {
                System.out.println("Enter class' end hour in the selected day (HH:mm): ");
                String endHour = scanner.nextLine();
                SimpleDateFormat endHourFormat = new SimpleDateFormat("HH:mm");
                try {
                    Date date = endHourFormat.parse(endHour);
                    Time endHourInput = new Time(date.getTime());
                    classesModel.setEndHour(endHourInput);
                    validEndHour = true;
                } catch (ParseException e) {
                    System.out.println("Invalid time format. Please enter time in HH:mm format.");
                }
            }
            classesModel.setClassName(className);
            classesModel.setCredits(credits);
            classesModel.setDay(classDay);
            classesController.addClass(classesModel);
            System.out.println("Class added successfully!\n");
        }
    }

    public void showClasses() {
        List<ClassesModel> classesList = classesController.showClasses(classesModel);
        if (classesList.isEmpty()) {
            System.out.println("No classes found.\n");
        } else {
            System.out.println("All classes: \n");
            for (ClassesModel classes : classesList) {
                System.out.println("Class ID: " + classes.getClassId());
                System.out.println("Class subject: " + classes.getClassName() + " | Credits worth: " + classes.getCredits());
                System.out.println("Day: " + classes.getDay() + " | Hours: " + classes.getStartHour() + " - " + classes.getEndHour());
            }
        }
    }

    public void updateClass() {
        ClassesUI classesUI = new ClassesUI();
        List<ClassesModel> classesList = classesController.showClasses(classesModel);
        Scanner scanner = new Scanner(System.in);
        if (classesList.isEmpty()) {
            System.out.println("No classes found.\n");
        } else {
            showClasses();
            System.out.println("Enter class ID to update: ");
            int classId = scanner.nextInt();
            scanner.nextLine();

            ClassesModel classToUpdate = null;
            for (ClassesModel classes : classesList) {
                if (classes.getClassId() == classId) {
                    classToUpdate = classes;
                    break;
                }
            }

            if (classToUpdate == null) {
                System.out.println("Class with ID " + classId + " not found.\n");
            } else {
                System.out.println("What do you want to update?: ");
                System.out.println("1. Subject name");
                System.out.println("2. Credits worth");
                System.out.println("3. Day held in");
                System.out.println("4. Start Hour");
                System.out.println("5. End Hour");
                System.out.println("0. Back");
                int update = scanner.nextInt();
                scanner.nextLine();

                if (update == 1) {
                    System.out.println("Enter new subject name (or leave blank to keep current): ");
                    String newSubject = scanner.nextLine();
                    if (!newSubject.isEmpty()) {
                        classToUpdate.setClassName(newSubject);
                    }
                    System.out.println("Subject name updated successfully! \n");
                } else if (update == 2) {
                    System.out.println("Enter a new credit count (or leave blank to keep current): ");
                    String newCredits = scanner.nextLine();
                    if (!newCredits.isEmpty()) {
                        try {
                            int credits = Integer.parseInt(newCredits);
                            classToUpdate.setCredits(credits);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. It should be a number. Keeping current.");
                        }
                    }
                } else if (update == 3) {
                    System.out.println("Enter a new day (or leave blank to keep current): ");
                    String newDay = scanner.nextLine();
                    if (!newDay.isEmpty()) {
                        classToUpdate.setDay(newDay);
                    }
                } else if (update == 4) {
                    System.out.println("Enter a new start hour (HH:mm)(or leave blank to keep current): ");
                    boolean validStartHour = false;
                    while (!validStartHour) {
                        String startHour = scanner.nextLine();
                        SimpleDateFormat startHourFormat = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = startHourFormat.parse(startHour);
                            Time startHourInput = new Time(date.getTime());
                            classToUpdate.setStartHour(startHourInput);
                            validStartHour = true;
                        } catch (ParseException e) {
                            System.out.println("Invalid time format. Please enter time in HH:mm format.");
                        }
                    }
                } else if (update == 5) {
                    System.out.println("Enter a new end hour (HH:mm)(or leave blank to keep current): ");
                    boolean validEndHour = false;
                    while (!validEndHour) {
                        String endHour = scanner.nextLine();
                        SimpleDateFormat endHourFormat = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = endHourFormat.parse(endHour);
                            Time endHourInput = new Time(date.getTime());
                            classToUpdate.setEndHour(endHourInput);
                            validEndHour = true;
                        } catch (ParseException e) {
                            System.out.println("Invalid time format. Please enter time in HH:mm format.");
                        }
                    }
                } else if (update == 0) {
                    classesUI.classesMenu();
                }
                classesController.updateClass(classToUpdate);
            }
        }
    }

    public void removeClass() {
        List<ClassesModel> classesList = classesController.showClasses(classesModel);
        Scanner scanner = new Scanner(System.in);
        if (classesList.isEmpty()) {
            System.out.println("No classes found.\n");
        } else {
            showClasses();
            System.out.println("Enter class ID in order to remove: ");
            int classId = scanner.nextInt();
            scanner.nextLine();

            ClassesModel classToRemove = null;
            for (ClassesModel classes : classesList) {
                if (classes.getClassId() == classId) {
                    classToRemove = classes;
                    break;
                }
            }

            if (classToRemove == null) {
                System.out.println("Class with ID " + classId + " not found.\n");
            } else {
                classesController.removeClass(classToRemove);
            }
        }
    }

    public void findClass() {
        ClassesUI classesUI = new ClassesUI();
        List<ClassesModel> classesList = classesController.showClasses(classesModel);
        Scanner scanner = new Scanner(System.in);
        if (classesList.isEmpty()) {
            System.out.println("No classes found.\n");
        } else {
            System.out.println("How do you want to find the class?: ");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by subject");
            System.out.println("3. Find by credits");
            System.out.println("4. Find by day");
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter class' ID: ");
                int classId = scanner.nextInt();
                scanner.nextLine();

                ClassesModel classToFind = null;
                for (ClassesModel classes : classesList) {
                    if (classes.getClassId() == classId) {
                        classToFind = classes;
                        break;
                    }
                }
                if (classToFind == null) {
                    System.out.println("Class with ID " + classId + " not found.\n");
                } else {
                    System.out.println("Class ID: " + classToFind.getClassId());
                    System.out.println("Class subject: " + classToFind.getClassName() + " | Credits worth: " + classToFind.getCredits());
                }
            } else if (choice == 2) {
                System.out.println("Enter class' subject: ");
                String subject = scanner.nextLine();

                List<ClassesModel> matchingClasses = new ArrayList<>();

                for (ClassesModel classToFind : classesList) {
                    if (classToFind.getClassName().toLowerCase().contains(subject.toLowerCase())) {
                        matchingClasses.add(classToFind);
                    }
                }

                if (matchingClasses.isEmpty()) {
                    System.out.println("No classes found by subject: " + subject);
                } else {
                    System.out.println("All classes matching subject " + subject + ": ");
                    for (ClassesModel classes : matchingClasses) {
                        System.out.println("Class ID: " + classes.getClassId());
                        System.out.println("Class subject: " + classes.getClassName() + " | Credits worth: " + classes.getCredits());
                        System.out.println("Day: " + classes.getDay() + " | Hours: " + classes.getStartHour() + " - " + classes.getEndHour());
                    }
                }
            } else if (choice == 3) {
                System.out.println("Enter credits number that you're looking for: ");
                int credits = scanner.nextInt();
                scanner.nextLine();

                List<ClassesModel> matchingClasses = new ArrayList<>();

                for (ClassesModel classes : classesList) {
                    if (classes.getCredits() == credits) {
                        matchingClasses.add(classes);
                    }
                }
                if (matchingClasses.isEmpty()) {
                    System.out.println("No classes worth: " + credits + " credits.");
                } else {
                    System.out.println("All classes matching " + credits + " credits: ");
                    for (ClassesModel classes : matchingClasses) {
                        System.out.println("Class ID: " + classes.getClassId());
                        System.out.println("Class subject: " + classes.getClassName() + " | Credits worth: " + classes.getCredits());
                        System.out.println("Day: " + classes.getDay() + " | Hours: " + classes.getStartHour() + " - " + classes.getEndHour());
                    }
                }
            } else if (choice == 4) {
                System.out.println("Enter the day you are looking for: ");
                String day = scanner.nextLine();

                List<ClassesModel> matchingClasses = new ArrayList<>();

                for (ClassesModel classToFind : classesList) {
                    if (classToFind.getClassName().toLowerCase().contains(day.toLowerCase())) {
                        matchingClasses.add(classToFind);
                    }
                }

                if (matchingClasses.isEmpty()) {
                    System.out.println("No classes found in day: " + day);
                } else {
                    System.out.println("All classes in the day " + day + ": ");
                    for (ClassesModel classes : matchingClasses) {
                        System.out.println("Class ID: " + classes.getClassId());
                        System.out.println("Class subject: " + classes.getClassName() + " | Credits worth: " + classes.getCredits());
                        System.out.println("Day: " + classes.getDay() + " | Hours: " + classes.getStartHour() + " - " + classes.getEndHour());
                    }
                }
            } else if (choice == 0) {
                classesUI.classesMenu();
            }
        }
    }
}
