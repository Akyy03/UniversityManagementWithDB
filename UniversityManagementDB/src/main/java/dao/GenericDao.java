package dao;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class GenericDao<G> {
    private SessionFactory sessionFactory;

    public GenericDao() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        sessionFactory = databaseConnection.getSessionFactory();
    }

    public void add(G object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
        if (session != null) {
            session.close();
        }
    }

    public List<G> showAll(G object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from " + object.getClass().getName());
        List<G> results = query.getResultList();
        transaction.commit();
        if (session != null) {
            session.close();
        }
        return results;
    }

    public Optional<G> findById(G object, int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<G> searchedOptional = (Optional<G>) session.find(object.getClass(), id);
        transaction.commit();
        if (session != null) {
            session.close();
        }
        return searchedOptional;
    }

    public void update(G object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
        if (session != null) {
            session.close();
        }
    }

    public void remove(G object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(object);
        transaction.commit();
        if (session != null) {
            session.close();
        }
    }
}
