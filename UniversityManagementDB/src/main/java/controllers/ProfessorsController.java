package controllers;

import dao.GenericDao;
import models.ProfessorsModel;

import java.util.List;
import java.util.Optional;

public class ProfessorsController {
    private GenericDao<ProfessorsModel> genericDao = new GenericDao<>();

    public void addProfessor(ProfessorsModel professorsModel) {
        genericDao.add(professorsModel);
    }

    public List<ProfessorsModel> showProfessors(ProfessorsModel professorsModel) {
        List<ProfessorsModel> professors = genericDao.showAll(professorsModel);
        return professors;
    }

    public Optional<ProfessorsModel> findById(ProfessorsModel professorsModel, int id) {
        List<ProfessorsModel> professorsModels = genericDao.showAll(professorsModel);
        return professorsModels.stream().filter(studentsModel1 -> studentsModel1.getPersonalCode() == id).findFirst();
    }

    public void updateProfessor(ProfessorsModel professorsModel) {
        genericDao.update(professorsModel);
    }

    public void removeProfessor(ProfessorsModel professorsModel) {
        genericDao.remove(professorsModel);
    }
}
