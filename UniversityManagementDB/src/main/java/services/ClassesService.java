package services;

import controllers.ClassesController;
import models.ClassesModel;
import uis.ClassesUI;

import java.util.ArrayList;
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

            classesModel.setClassName(className);
            classesModel.setCredits(credits);
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
            } else if (choice == 2){
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
                    }
                }
            } else if (choice == 0) {
                classesUI.classesMenu();
            }
        }
    }
}
