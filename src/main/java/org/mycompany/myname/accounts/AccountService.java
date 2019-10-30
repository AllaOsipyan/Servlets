package org.mycompany.myname.accounts;

import org.mycompany.myname.database.DBConnection;
import org.mycompany.myname.database.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    Executor executor = new Executor();

    Connection conn;
    public AccountService(){
        this.conn =DBConnection.getNewConnection();
    }

    public void createTable() throws SQLException {
        executor.execUpdate(conn,"create table if not exists usersAccounts (login varchar(256), session_id varchar(256), pass varchar(256), email varchar(256), primary key (login))");
    }


    public boolean addNewUser(UserProfile userProfile) throws SQLException {
        if (getUserByLogin(userProfile.getLogin()) == null) {
            executor.execUpdate(conn, "insert into usersAccounts (login, pass, email) values ('" + userProfile.getLogin() + "','" + userProfile.getPass() + "','" + userProfile.getEmail() + "')");
            return false;
        }
        else return true;

    }

    public UserProfile getUserByLogin(String login) throws SQLException {
        return executor.execQuery(conn,"select login, pass, email from usersAccounts where login='" + login+"'",  result -> {

            if( result.next()){
                return new UserProfile(result.getString(1), result.getString(2), result.getString(3));}
            return null;
        });

    }

    public UserProfile getUserBySessionId(String sessionId) throws SQLException {
        return executor.execQuery(conn,"select login, pass, email from usersAccounts where session_id='" + sessionId+"'",  result -> {

            if( result.next()){
                return new UserProfile(result.getString(1), result.getString(2), result.getString(3));}
            return null;
        });
    }

    public  void addSession(String sessionId, UserProfile userProfile) throws SQLException {
        if (getUserByLogin(userProfile.getLogin()) != null)
            executor.execUpdate(conn, "update usersAccounts set session_id='" + sessionId + "' where login='"+ userProfile.getLogin()+"'");

        //sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) throws SQLException {
            executor.execUpdate(conn, "update usersAccounts set session_id=null where session_id='"+ sessionId+"'") ;

        //sessionIdToProfile.remove(sessionId);
    }
}
