package org.mycompany.myname.database;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mycompany.myname.accounts.UsersDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;
import java.util.function.Supplier;

public class Executor {
    DBService dbService =new DBService();
    SessionFactory sessionFactory = dbService.getSessionFactory();
    public <T> T exec(ResultHandler<T> handler) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T value = handler.handle(session);
        transaction.commit();
        session.close();
        return value;
    }
}
