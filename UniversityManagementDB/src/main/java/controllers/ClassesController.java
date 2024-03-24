package controllers;

import dao.GenericDao;
import models.ClassesModel;

import java.util.List;


public class ClassesController {
    private GenericDao<ClassesModel> genericDao = new GenericDao<>();

    public void addClass(ClassesModel classesModel) {
        genericDao.add(classesModel);
    }

    public List<ClassesModel> showClasses(ClassesModel classesModel) {
        List<ClassesModel> classes = genericDao.showAll(classesModel);
        return classes;
    }

    public void updateClass(ClassesModel classesModel) {
        genericDao.update(classesModel);
    }

    public void removeClass(ClassesModel classesModel) {
        genericDao.remove(classesModel);
    }
}
