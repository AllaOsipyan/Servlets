package org.mycompany.myname.accounts;

import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import org.hibernate.query.Query;
import org.mycompany.myname.database.Executor;
import org.mycompany.myname.database.ResultHandler;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    static Executor executor=new Executor();
    public static boolean addNewUser(UserProfile userProfile) throws HibernateException, SQLException {
        if (getUserByLogin(userProfile.getLogin()) == null) {
            return executor.exec((session)->{
                session.persist(userProfile);
                return false;
            });
        }
        else return true;

    }

    public static UserProfile getUserByLogin(String login) throws HibernateException, SQLException {
        return executor.exec((session) -> session.get(UserProfile.class, login));
    }

    public static void addSessionId(String sessionId, UserProfile userProfile) throws SQLException {

        executor.exec((session) -> {
            userProfile.setSessionId(sessionId);
            session.update(userProfile);
            return true;
        });
    }
    public static UserProfile getUserBySessionId(String sessionId) throws SQLException {
        return  executor.exec((session) -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserProfile> criteriaQuery = builder.createQuery(UserProfile.class);
            Root<UserProfile> root = criteriaQuery.from(UserProfile.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get("sessionId"), sessionId));
            Query<UserProfile> q=session.createQuery(criteriaQuery);
            List<UserProfile> result =q.getResultList();
            return (!result.isEmpty())? result.get(0):null;
        });



    }

    public static void deleteSessionId(String sessionId) throws SQLException {
        executor.exec((session)->{
            UserProfile userProfile = null;
            try {
                userProfile = getUserBySessionId(sessionId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            userProfile.setSessionId(null);
            session.update(userProfile);
            return userProfile;
        });

    }


}
