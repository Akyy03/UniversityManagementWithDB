package services;

import controllers.StudentsController;
import models.StudentsModel;
import uis.StudentsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentsService {

    StudentsModel studentsModel = new StudentsModel();
    StudentsController studentsController = new StudentsController();

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter student's first name (or type 'quit' to cancel): ");
            String firstName = scanner.nextLine();
            if (firstName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter student's last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Enter student's email address: ");
            String email = scanner.nextLine();

            studentsModel.setFirstName(firstName);
            studentsModel.setLastName(lastName);
            studentsModel.setEmail(email);
            studentsController.addStudent(studentsModel);
            System.out.println("Student added successfully!\n");
        }
    }

    public void showStudents() {
        List<StudentsModel> studentsList = studentsController.showStudents(studentsModel);
        if (studentsList.isEmpty()) {
            System.out.println("No students found.\n");
        } else {
            System.out.println("All students: \n");
            for (StudentsModel student : studentsList) {
                System.out.println("Student personal code: " + student.getPersonalCode());
                System.out.println("First name: " + student.getFirstName() + " | Last name: " + student.getLastName());
                System.out.println("Email address: " + student.getEmail() + "\n");
            }
        }
    }

    public void updateStudent() {
        StudentsUI studentsUI = new StudentsUI();
        List<StudentsModel> studentsList = studentsController.showStudents(studentsModel);
        Scanner scanner = new Scanner(System.in);
        if (studentsList.isEmpty()) {
            System.out.println("No students found.\n");
        } else {
            showStudents();
            System.out.println("Enter student's personal code for update: ");
            int personalCode = scanner.nextInt();
            scanner.nextLine();

            StudentsModel studentToUpdate = null;
            for (StudentsModel student : studentsList) {
                if (student.getPersonalCode() == personalCode) {
                    studentToUpdate = student;
                    break;
                }
            }

            if (studentToUpdate == null) {
                System.out.println("Student with personal code " + personalCode + " not found.\n");
            } else {
                System.out.println("What do you want to update?: ");
                System.out.println("1. First name");
                System.out.println("2. Last name");
                System.out.println("3. Email address");
                System.out.println("0. Back");
                int update = scanner.nextInt();
                scanner.nextLine();

                if (update == 1) {
                    System.out.println("Enter new first name (or leave blank to keep current): ");
                    String newFirstName = scanner.nextLine();
                    if (!newFirstName.isEmpty()) {
                        studentToUpdate.setFirstName(newFirstName);
                    }
                    System.out.println("Student's first name updated successfully! \n");
                } else if (update == 2) {
                    System.out.println("Enter new last name (or leave blank to keep current): ");
                    String newLastName = scanner.nextLine();
                    if (!newLastName.isEmpty()) {
                        studentToUpdate.setLastName(newLastName);
                    }
                    System.out.println("Student's last name updated successfully! \n");
                } else if (update == 3) {
                    System.out.println("Enter new email address (or leave blank to keep current): ");
                    String newEmail = scanner.nextLine();
                    if (!newEmail.isEmpty()) {
                        studentToUpdate.setEmail(newEmail);
                    }
                    System.out.println("Student's email address updated successfully! \n");
                } else if (update == 0) {
                    studentsUI.studentsMenu();
                }
                studentsController.updateStudent(studentToUpdate);
            }
        }
    }

    public void removeStudent() {
        List<StudentsModel> studentsList = studentsController.showStudents(studentsModel);
        Scanner scanner = new Scanner(System.in);
        if (studentsList.isEmpty()) {
            System.out.println("No students found.\n");
        } else {
            showStudents();
            System.out.println("Enter student's personal code in order to remove: ");
            int personalCode = scanner.nextInt();
            scanner.nextLine();

            StudentsModel studentToRemove = null;
            for (StudentsModel student : studentsList) {
                if (student.getPersonalCode() == personalCode) {
                    studentToRemove = student;
                    break;
                }
            }

            if (studentToRemove == null) {
                System.out.println("Student with personal code " + personalCode + " not found.\n");
            } else {
                studentsController.removeStudent(studentToRemove);
            }
        }
    }

    public void findStudent() {
        StudentsUI studentsUI = new StudentsUI();
        List<StudentsModel> studentsList = studentsController.showStudents(studentsModel);
        Scanner scanner = new Scanner(System.in);
        if (studentsList.isEmpty()) {
            System.out.println("No students found.\n");
        } else {
            System.out.println("How do you want to find the student?: ");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by first name");
            System.out.println("3. Find by last name");
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter student's personal code: ");
                int personalCode = scanner.nextInt();
                scanner.nextLine();

                StudentsModel studentToFind = null;
                for (StudentsModel student : studentsList) {
                    if (student.getPersonalCode() == personalCode) {
                        studentToFind = student;
                        break;
                    }
                }
                if (studentToFind == null) {
                    System.out.println("Student with personal code " + personalCode + " not found.\n");
                } else {
                    System.out.println("Student personal code: " + studentToFind.getPersonalCode());
                    System.out.println("First name: " + studentToFind.getFirstName() + " | Last name: " + studentToFind.getLastName());
                    System.out.println("Email address: " + studentToFind.getEmail() + "\n");
                }
            } else if (choice == 2) {
                System.out.println("Enter student's first name: ");
                String firstName = scanner.nextLine();

                List<StudentsModel> matchingStudents = new ArrayList<>();

                for (StudentsModel studentsToFind : studentsList) {
                    if (studentsToFind.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                        matchingStudents.add(studentsToFind);
                    }
                }

                if (matchingStudents.isEmpty()) {
                    System.out.println("No students found for first names containing: " + firstName);
                } else {
                    System.out.println("All students with matching first name: ");
                    for (StudentsModel students : matchingStudents) {
                        System.out.println("Student personal code: " + students.getPersonalCode());
                        System.out.println("First name: " + students.getFirstName() + " | Last name: " + students.getLastName());
                        System.out.println("Email address: " + students.getEmail() + "\n");

                    }
                }
            } else if (choice == 3) {
                System.out.println("Enter student's last name: ");
                String lastName = scanner.nextLine();

                List<StudentsModel> matchingStudents = new ArrayList<>();

                for (StudentsModel studentsToFind : studentsList) {
                    if (studentsToFind.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                        matchingStudents.add(studentsToFind);
                    }
                }

                if (matchingStudents.isEmpty()) {
                    System.out.println("No students found for last names containing: " + lastName);
                } else {
                    System.out.println("All students with matching last name: ");
                    for (StudentsModel students : matchingStudents) {
                        System.out.println("Student personal code: " + students.getPersonalCode());
                        System.out.println("First name: " + students.getFirstName() + " | Last name: " + students.getLastName());
                        System.out.println("Email address: " + students.getEmail() + "\n");

                    }
                }
            } else if (choice == 0) {
                studentsUI.studentsMenu();
            }
        }
    }
}
