package org.mycompany.myname.database;

import org.hibernate.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.mycompany.myname.accounts.UserProfile;
import org.mycompany.myname.accounts.UsersDAO;

public class DBService {

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";
    SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getNewConnection();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getNewConnection() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserProfile.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }


    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public UserProfile getUser(String login)  {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UserProfile dataSet = dao.getUserByLogin(login);
            session.close();
            return dataSet;

    }
    public UserProfile getBySession(String sessionId)  {
        Session session = sessionFactory.openSession();
        UsersDAO dao = new UsersDAO(session);
        UserProfile dataSet = dao.getUserBySessionId(sessionId);
        session.close();
        return dataSet;

    }

    public boolean addUser(UserProfile userProfile) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        boolean isAdded = dao.addNewUser(userProfile);
        transaction.commit();
        session.close();
        return isAdded;
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        dao.addSessionId(sessionId,userProfile);
        transaction.commit();
        session.close();
    }

    public void deleteSession(String sessionId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO dao = new UsersDAO(session);
        dao.deleteSessionId(sessionId);
        transaction.commit();
        session.close();
    }

}
