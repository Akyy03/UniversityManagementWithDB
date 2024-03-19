package services;

import controllers.ProfessorsController;
import models.ProfessorsModel;
import models.StudentsModel;
import uis.ProfessorsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorsService {
    ProfessorsModel professorsModel = new ProfessorsModel();
    ProfessorsController professorsController = new ProfessorsController();

    public void addProfessor() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter professor's first name (or type 'quit' to cancel): ");
            String firstName = scanner.nextLine();
            if (firstName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter professor's last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Enter professor's email address: ");
            String email = scanner.nextLine();
            System.out.println("Enter professor's subject: ");
            String subject = scanner.nextLine();

            professorsModel.setFirstName(firstName);
            professorsModel.setLastName(lastName);
            professorsModel.setEmail(email);
            professorsModel.setSubject(subject);
            professorsController.addProfessor(professorsModel);
            System.out.println("Professor added successfully!\n");
        }
    }

    public void showProfessors() {
        List<ProfessorsModel> professorsList = professorsController.showProfessors(professorsModel);
        if (professorsList.isEmpty()) {
            System.out.println("No professors found.\n");
        } else {
            System.out.println("All professors: \n");
            for (ProfessorsModel professor : professorsList) {
                System.out.println("Professor personal code: " + professor.getPersonalCode() + " | Subject: " + professor.getSubject());
                System.out.println("First name: " + professor.getFirstName() + " | Last name: " + professor.getLastName());
                System.out.println("Email address: " + professor.getEmail() + "\n");
            }
        }
    }

    public void updateProfessor() {
        ProfessorsUI professorsUI = new ProfessorsUI();
        List<ProfessorsModel> professorsList = professorsController.showProfessors(professorsModel);
        Scanner scanner = new Scanner(System.in);
        if (professorsList.isEmpty()) {
            System.out.println("No professors found.\n");
        } else {
            showProfessors();
            System.out.println("Enter professor's personal code for update: ");
            int personalCode = scanner.nextInt();
            scanner.nextLine();

            ProfessorsModel professorToUpdate = null;
            for (ProfessorsModel professor : professorsList) {
                if (professor.getPersonalCode() == personalCode) {
                    professorToUpdate = professor;
                    break;
                }
            }

            if (professorToUpdate == null) {
                System.out.println("Professor with personal code " + personalCode + " not found.\n");
            } else {
                System.out.println("What do you want to update?: ");
                System.out.println("1. First name");
                System.out.println("2. Last name");
                System.out.println("3. Email address");
                System.out.println("4. Subject");
                System.out.println("0. Back");
                int update = scanner.nextInt();
                scanner.nextLine();

                if (update == 1) {
                    System.out.println("Enter new first name (or leave blank to keep current): ");
                    String newFirstName = scanner.nextLine();
                    if (!newFirstName.isEmpty()) {
                        professorToUpdate.setFirstName(newFirstName);
                    }
                    System.out.println("Professor's first name updated successfully! \n");
                } else if (update == 2) {
                    System.out.println("Enter new last name (or leave blank to keep current): ");
                    String newLastName = scanner.nextLine();
                    if (!newLastName.isEmpty()) {
                        professorToUpdate.setLastName(newLastName);
                    }
                    System.out.println("Professor's last name updated successfully! \n");
                } else if (update == 3) {
                    System.out.println("Enter new email address (or leave blank to keep current): ");
                    String newEmail = scanner.nextLine();
                    if (!newEmail.isEmpty()) {
                        professorToUpdate.setEmail(newEmail);
                    }
                    System.out.println("Professor's email address updated successfully! \n");
                } else if (update == 4) {
                    System.out.println("Enter new subject (or leave blank to keep current): ");
                    String newSubject = scanner.nextLine();
                    if (!newSubject.isEmpty()) {
                        professorToUpdate.setSubject(newSubject);
                    }
                } else if (update == 0) {
                    professorsUI.professorsMenu();
                }
                professorsController.updateProfessor(professorToUpdate);
            }
        }
    }

    public void removeProfessor() {
        List<ProfessorsModel> professorsList = professorsController.showProfessors(professorsModel);
        Scanner scanner = new Scanner(System.in);
        if (professorsList.isEmpty()) {
            System.out.println("No professors found.\n");
        } else {
            showProfessors();
            System.out.println("Enter professor's personal code in order to remove: ");
            int personalCode = scanner.nextInt();
            scanner.nextLine();

            ProfessorsModel professorToRemove = null;
            for (ProfessorsModel professor : professorsList) {
                if (professor.getPersonalCode() == personalCode) {
                    professorToRemove = professor;
                    break;
                }
            }

            if (professorToRemove == null) {
                System.out.println("Professor with personal code " + personalCode + " not found.\n");
            } else {
                professorsController.removeProfessor(professorToRemove);
            }
        }
    }

    public void findProfessor() {
        ProfessorsUI professorsUI = new ProfessorsUI();
        List<ProfessorsModel> professorsList = professorsController.showProfessors(professorsModel);
        Scanner scanner = new Scanner(System.in);
        if (professorsList.isEmpty()) {
            System.out.println("No professors found.\n");
        } else {
            System.out.println("How do you want to find the professor?: ");
            System.out.println("1. Find by ID");
            System.out.println("2. Find by first name");
            System.out.println("3. Find by last name");
            System.out.println("4. Find by subject");
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter professor's personal code: ");
                int personalCode = scanner.nextInt();
                scanner.nextLine();

                ProfessorsModel professorToFind = null;
                for (ProfessorsModel professor : professorsList) {
                    if (professor.getPersonalCode() == personalCode) {
                        professorToFind = professor;
                        break;
                    }
                }
                if (professorToFind == null) {
                    System.out.println("Professor with personal code " + personalCode + " not found.\n");
                } else {
                    System.out.println("Professor personal code: " + professorToFind.getPersonalCode() + " | Subject: " + professorToFind.getSubject());
                    System.out.println("First name: " + professorToFind.getFirstName() + " | Last name: " + professorToFind.getLastName());
                    System.out.println("Email address: " + professorToFind.getEmail() + "\n");
                }
            } else if (choice == 2) {
                System.out.println("Enter professor's first name: ");
                String firstName = scanner.nextLine();

                List<ProfessorsModel> matchingProfessors = new ArrayList<>();

                for (ProfessorsModel professorToFind : professorsList) {
                    if (professorToFind.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                        matchingProfessors.add(professorToFind);
                    }
                }

                if (matchingProfessors.isEmpty()) {
                    System.out.println("No professors found for first names containing: " + firstName);
                } else {
                    System.out.println("All professors with matching first name: ");
                    for (ProfessorsModel professors : matchingProfessors) {
                        System.out.println("Professor personal code: " + professors.getPersonalCode() + " | Subject: " + professors.getSubject());
                        System.out.println("First name: " + professors.getFirstName() + " | Last name: " + professors.getLastName());
                        System.out.println("Email address: " + professors.getEmail() + "\n");

                    }
                }
            } else if (choice == 3) {
                System.out.println("Enter professor's last name: ");
                String lastName = scanner.nextLine();

                List<ProfessorsModel> matchingProfessors = new ArrayList<>();

                for (ProfessorsModel professorToFind : professorsList) {
                    if (professorToFind.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                        matchingProfessors.add(professorToFind);
                    }
                }

                if (matchingProfessors.isEmpty()) {
                    System.out.println("No professors found for last names containing: " + lastName);
                } else {
                    System.out.println("All professors with matching last name: ");
                    for (ProfessorsModel professors : matchingProfessors) {
                        System.out.println("Professor personal code: " + professors.getPersonalCode() + " | Subject: " + professors.getSubject());
                        System.out.println("First name: " + professors.getFirstName() + " | Last name: " + professors.getLastName());
                        System.out.println("Email address: " + professors.getEmail() + "\n");

                    }
                }
            } else if (choice == 4) {
                System.out.println("Enter professor's subject: ");
                String subject = scanner.nextLine();

                List<ProfessorsModel> matchingProfessors = new ArrayList<>();

                for (ProfessorsModel professorToFind : professorsList) {
                    if (professorToFind.getSubject().toLowerCase().contains(subject.toLowerCase())) {
                        matchingProfessors.add(professorToFind);
                    }
                }

                if (matchingProfessors.isEmpty()) {
                    System.out.println("No professors found for subject: " + subject);
                } else {
                    System.out.println("All professors teaching subject " + subject + ": ");
                    for (ProfessorsModel professors : matchingProfessors) {
                        System.out.println("Professor personal code: " + professors.getPersonalCode() + " | Subject: " + professors.getSubject());
                        System.out.println("First name: " + professors.getFirstName() + " | Last name: " + professors.getLastName());
                        System.out.println("Email address: " + professors.getEmail() + "\n");

                    }
                }
            } else if (choice == 0) {
                professorsUI.professorsMenu();
            }
        }
    }
}
