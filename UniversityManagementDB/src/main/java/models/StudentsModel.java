package models;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class StudentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personalCode;
    private String firstName;
    private String lastName;
    private String email;

    public int getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(int personalCode) {
        this.personalCode = personalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
