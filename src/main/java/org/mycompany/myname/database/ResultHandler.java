package org.mycompany.myname.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(ResultSet resultset) throws SQLException;
}
