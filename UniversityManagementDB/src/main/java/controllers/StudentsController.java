package controllers;

import dao.GenericDao;
import models.StudentsModel;

import java.util.List;


public class StudentsController {
    private GenericDao<StudentsModel> genericDao = new GenericDao<>();

    public void addStudent(StudentsModel studentsModel) {
        genericDao.add(studentsModel);
    }

    public List<StudentsModel> showStudents(StudentsModel studentsModel) {
        List<StudentsModel> students = genericDao.showAll(studentsModel);
        return students;
    }


    public void updateStudent(StudentsModel studentsModel) {
        genericDao.update(studentsModel);
    }

    public void removeStudent(StudentsModel studentsModel) {
        genericDao.remove(studentsModel);
    }


}
