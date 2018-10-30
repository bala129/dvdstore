package com.ideas2it.dvdstore.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Query;  
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.session.DvdStoreSessionFactory;

/**
 * <p>
 * The {@code UserDaoImpl} is used to perform the 
 * action on the user
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class UserDaoImpl implements UserDao {

    private String insertQuery = 
        "INSERT INTO USER(USER_NAME, PASSWORD, ROLE)"+
        " VALUES(:name, SHA1(:password), :role)";
    
    private DvdStoreSessionFactory dvdStoreSessionFactory 
        = DvdStoreSessionFactory.getInstance();
    private SessionFactory factory;
    private Session session;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean createUser(User user) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery(insertQuery);
            query.setParameter("name", user.getUserName());
            query.setParameter("password", user.getPassword());
            query.setParameter("role", user.getRole());
            query.executeUpdate();
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.USER_CREATE_ERROR+
                user.getUserName(), e);
            throw new DvdStoreException(Constants.USER_CREATE_ERROR+
                user.getUserName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public User searchUser(String userName, String password, String role) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            String query = "FROM User WHERE userName=:name AND"+
                " password=sha1(:password) AND role=:role";
            Query resultQuery = session.createQuery(query);
            resultQuery.setParameter("name", userName);
            resultQuery.setParameter("password", password);
            resultQuery.setParameter("role", role);
            return (User)resultQuery.uniqueResult();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.USER_SEARCH_ERROR+userName, e);
            throw new DvdStoreException(Constants.USER_SEARCH_ERROR+userName);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public User searchName(String userName) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            String query = "FROM User WHERE userName=:name";
            Query resultQuery = session.createQuery(query);
            resultQuery.setParameter("name", userName);
            return (User)resultQuery.uniqueResult();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.USER_SEARCH_BYNAME_ERROR+userName, e);
            throw new DvdStoreException(Constants.
                USER_SEARCH_BYNAME_ERROR+userName);
        } finally {
            session.close(); 
        }
    }
}
