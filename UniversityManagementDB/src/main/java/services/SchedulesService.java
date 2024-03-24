package services;

import controllers.ClassesController;
import controllers.ProfessorsController;
import controllers.SchedulesController;
import controllers.StudentsController;
import models.ClassesModel;
import models.ProfessorsModel;
import models.SchedulesModel;
import models.StudentsModel;
import uis.SchedulesUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchedulesService {
    ClassesController classesController = new ClassesController();
    ClassesModel classesModel = new ClassesModel();
    ClassesService classesService = new ClassesService();
    ProfessorsController professorsController = new ProfessorsController();
    ProfessorsModel professorsModel = new ProfessorsModel();
    ProfessorsService professorsService = new ProfessorsService();
    SchedulesController schedulesController = new SchedulesController();
    SchedulesModel schedulesModel = new SchedulesModel();
    StudentsController studentsController = new StudentsController();
    StudentsModel studentsModel = new StudentsModel();
    StudentsService studentsService = new StudentsService();
    List<ClassesModel> classesList = classesController.showClasses(classesModel);
    List<ProfessorsModel> professorsList = professorsController.showProfessors(professorsModel);
    List<SchedulesModel> schedulesList = schedulesController.showSchedules(schedulesModel);
    List<StudentsModel> studentsList = studentsController.showStudents(studentsModel);
    List<ClassesModel> classes = new ArrayList<>();
    List<StudentsModel> students = new ArrayList<>();
    List<ProfessorsModel> professors = new ArrayList<>();


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
        if (schedulesList.isEmpty()) {
            System.out.println("No schedule found.\n");
        } else {
            System.out.println("All schedules: \n");
            for (SchedulesModel schedule : schedulesList) {
                System.out.println("Schedule ID: " + schedule.getScheduleId() +
                        " | Name: " + schedule.getScheduleName());

                List<StudentsModel> students = schedule.getStudentsModels();
                if (!students.isEmpty()) {
                    System.out.println("\nStudents in this schedule:");
                    for (StudentsModel student : students) {
                        System.out.println("Student ID: " + student.getPersonalCode() +
                                " | Name: " + student.getFirstName() + " " + student.getLastName());
                        System.out.println("Email address: " + student.getEmail());
                    }
                } else {
                    System.out.println("No students in this schedule.\n");
                }

                List<ProfessorsModel> professors = schedule.getProfessorsModels();
                if (!professors.isEmpty()) {
                    System.out.println("\nProfessors in this schedule:");
                    for (ProfessorsModel professor : professors) {
                        System.out.println("Professor ID: " + professor.getPersonalCode() +
                                " | Name: " + professor.getFirstName() + " " + professor.getLastName());
                        System.out.println("Email address: " + professor.getEmail() + " | Subject: " + professor.getSubject());
                    }
                } else {
                    System.out.println("No professors in this schedule.\n");
                }

                List<ClassesModel> classesList2 = schedule.getClassesModels();
                if (!classesList2.isEmpty()) {
                    System.out.println("\nClasses in this schedule:");
                    for (ClassesModel classes : classesList2) {
                        System.out.println("Class ID: " + classes.getClassId() +
                                " | Name: " + classes.getClassName());
                        System.out.println("Credits worth: " + classes.getCredits());
                        System.out.println("Held in: " + classes.getDay() + ", " + classes.getStartHour() + " - " + classes.getEndHour());
                    }
                } else {
                    System.out.println("No classes in this schedule.\n");
                }
            }
        }
    }

    public void updateSchedule() {
        SchedulesUI schedulesUI = new SchedulesUI();
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

    public void makeSchedule() {
        SchedulesUI schedulesUI = new SchedulesUI();
        Scanner scanner = new Scanner(System.in);
        if (schedulesList.isEmpty()) {
            System.out.println("No schedule found.\n");
        } else {
            int choice = -1;
            while (choice != 0) {
                System.out.println("Schedule settings: \n");
                System.out.println("1. Assign students to schedule");
                System.out.println("2. Assign professors to schedule");
                System.out.println("3. Assign classes to schedule");
                System.out.println("0. Back");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> assignStudents();

                    case 2 -> assignProfessor();

                    case 3 -> assignClass();

                    case 0 -> schedulesUI.schedulesMenu();

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            }
        }
    }

    public void assignStudents() {
        Scanner scanner = new Scanner(System.in);
        if (studentsList.isEmpty()) {
            System.out.println("No students found.\n");
        } else {
            showSchedules();
            System.out.println("Enter schedule ID to assign students: ");
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
                studentsService.showStudents();
                System.out.println("Enter student personal code to assign: ");
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
                    students.add(studentToFind);
                }
            }
            scheduleToFind.setStudentsModels(students);
            schedulesController.updateSchedules(scheduleToFind);
            System.out.println("Student assigned to schedule successfully!");
        }
    }

    public void assignProfessor() {
        Scanner scanner = new Scanner(System.in);
        if (professorsList.isEmpty()) {
            System.out.println("No professors found.\n");
        } else {
            showSchedules();
            System.out.println("Enter schedule ID to assign professors: ");
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
                professorsService.showProfessors();
                System.out.println("Enter professor personal code to assign: ");
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
                    professors.add(professorToFind);
                }
            }
            scheduleToFind.setProfessorsModels(professors);
            schedulesController.updateSchedules(scheduleToFind);
            System.out.println("Professor assigned to schedule successfully!");
        }
    }

    public void assignClass() {
        Scanner scanner = new Scanner(System.in);
        if (classesList.isEmpty()) {
            System.out.println("No classes found.\n");
        } else {
            showSchedules();
            System.out.println("Enter schedule ID to assign students: ");
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
                classesService.showClasses();
                System.out.println("Enter class ID to assign: ");
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
                    classes.add(classToFind);
                }
            }
            scheduleToFind.setClassesModels(classes);
            schedulesController.updateSchedules(scheduleToFind);
            System.out.println("Class assigned to schedule successfully!");
        }
    }
}
