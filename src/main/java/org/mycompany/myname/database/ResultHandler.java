package org.mycompany.myname.database;

import org.hibernate.Session;
import org.mycompany.myname.accounts.UsersDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(Session session) throws SQLException;
}
