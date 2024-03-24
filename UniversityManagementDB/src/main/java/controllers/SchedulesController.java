package controllers;

import dao.GenericDao;
import models.SchedulesModel;

import java.util.List;

public class SchedulesController {
    private GenericDao<SchedulesModel> genericDao = new GenericDao<>();


    public void addSchedule(SchedulesModel schedulesModel) {
        genericDao.add(schedulesModel);
    }

    public List<SchedulesModel> showSchedules(SchedulesModel schedulesModel) {
        List<SchedulesModel> schedules = genericDao.showAll(schedulesModel);
        return schedules;
    }

    public void updateSchedules(SchedulesModel schedulesModel) {
        genericDao.update(schedulesModel);
    }

    public void removeSchedules(SchedulesModel schedulesModel) {
        genericDao.remove(schedulesModel);
    }
}
