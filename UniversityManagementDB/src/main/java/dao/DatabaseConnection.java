package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseConnection {
    private SessionFactory sessionFactory;

    private static DatabaseConnection databaseConnection;

    private DatabaseConnection(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/UniversityManagementDB?serverTimezone=UTC");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "VeryPowerfulPassword123");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS , true);
        configuration.setProperties(properties);

        // configuration.addAnnotatedClass(StudentModel.class); ++ clase Model

        sessionFactory = configuration.buildSessionFactory();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static DatabaseConnection getInstance(){
        if (databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }
}
