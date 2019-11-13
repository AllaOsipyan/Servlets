package org.mycompany.myname.accounts;

import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import org.hibernate.query.Query;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

public class UsersDAO {
    private Session session;
    public UsersDAO(Session session) {
        this.session = session;
    }
    private EntityManager em;

    public boolean addNewUser(UserProfile userProfile) throws HibernateException {
        if (getUserByLogin(userProfile.getLogin()) == null) {
            session.persist(userProfile);
            return false;
        }
        else return true;

    }

    public UserProfile getUserByLogin(String login) throws HibernateException {
        return session.get(UserProfile.class, login);
    }



}
