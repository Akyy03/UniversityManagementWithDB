package dao;

import models.ClassesModel;
import models.ProfessorsModel;
import models.SchedulesModel;
import models.StudentsModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;
    private SessionFactory sessionFactory;

    private DatabaseConnection() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/UniversityManagementDB?serverTimezone=UTC");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "pineaple001");
        properties.put(Environment.DIALECT, "");
        properties.put(Environment.SHOW_SQL, "false");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "false");
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(StudentsModel.class);
        configuration.addAnnotatedClass(ProfessorsModel.class);
        configuration.addAnnotatedClass(ClassesModel.class);
        configuration.addAnnotatedClass(SchedulesModel.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
