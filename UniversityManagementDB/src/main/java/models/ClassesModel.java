package models;

import jakarta.persistence.*;

@Entity
@Table(name = "Classes")
public class ClassesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;
    private String className;
    private int credits;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
