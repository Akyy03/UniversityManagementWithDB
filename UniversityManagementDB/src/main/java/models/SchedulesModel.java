package models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Schedules")
public class SchedulesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    private String scheduleName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "studentsInSchedule", catalog = "universityManagementDB",
            joinColumns = {
                    @JoinColumn(name = "scheduleId", nullable = true, updatable = true)},
            inverseJoinColumns = {
                    @JoinColumn(name = "StudentPersonalCode", nullable = false, updatable = true)
            }
    )
    private List<StudentsModel> studentsModels;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "professorsInSchedule", catalog = "universityManagementDB",
            joinColumns = {
                    @JoinColumn(name = "scheduleId", nullable = true, updatable = true)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ProfessorPersonalCode", nullable = false, updatable = true)
            }
    )
    private List<ProfessorsModel> professorsModels;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "classesInSchedule", catalog = "universityManagementDB",
            joinColumns = {
                    @JoinColumn(name = "scheduleId", nullable = true, updatable = true)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ClassId", nullable = false, updatable = true)
            }
    )
    private List<ClassesModel> classesModels;

    public List<StudentsModel> getStudentsModels() {
        return studentsModels;
    }

    public void setStudentsModels(List<StudentsModel> studentsModels) {
        this.studentsModels = studentsModels;
    }

    public List<ProfessorsModel> getProfessorsModels() {
        return professorsModels;
    }

    public void setProfessorsModels(List<ProfessorsModel> professorsModels) {
        this.professorsModels = professorsModels;
    }

    public List<ClassesModel> getClassesModels() {
        return classesModels;
    }

    public void setClassesModels(List<ClassesModel> classesModels) {
        this.classesModels = classesModels;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }
}
