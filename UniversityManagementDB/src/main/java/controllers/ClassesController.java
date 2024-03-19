package controllers;

import dao.GenericDao;
import models.ClassesModel;

import java.util.List;
import java.util.Optional;

public class ClassesController {
    private GenericDao<ClassesModel> genericDao = new GenericDao<>();

    public void addClass(ClassesModel classesModel) {
        genericDao.add(classesModel);
    }

    public List<ClassesModel> showClasses(ClassesModel classesModel) {
        List<ClassesModel> classes = genericDao.showAll(classesModel);
        return classes;
    }

    /*public Optional<ClassesModel> findById(ClassesModel classesModel, int id) {
        List<ClassesModel> classesModels = genericDao.showAll(classesModel);
        return classesModels.stream().filter(studentsModel1 -> studentsModel1.getPersonalCode() == id).findFirst();
    }*/

    public void updateClass(ClassesModel classesModel) {
        genericDao.update(classesModel);
    }

    public void removeClass(ClassesModel classesModel) {
        genericDao.remove(classesModel);
    }
}
