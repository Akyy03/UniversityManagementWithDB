package controllers;

import dao.GenericDao;
import models.ProfessorsModel;

import java.util.List;


public class ProfessorsController {
    private GenericDao<ProfessorsModel> genericDao = new GenericDao<>();

    public void addProfessor(ProfessorsModel professorsModel) {
        genericDao.add(professorsModel);
    }

    public List<ProfessorsModel> showProfessors(ProfessorsModel professorsModel) {
        List<ProfessorsModel> professors = genericDao.showAll(professorsModel);
        return professors;
    }

    public void updateProfessor(ProfessorsModel professorsModel) {
        genericDao.update(professorsModel);
    }

    public void removeProfessor(ProfessorsModel professorsModel) {
        genericDao.remove(professorsModel);
    }
}
