package com.ideas2it.dvdstore.session;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException; 
import org.hibernate.SessionFactory;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;

/**
 * <p>
 * Is used to create the session factory 
 * </p>
 * 
 * @version 1
 * @auther Balamurugan
 */
public class DvdStoreSessionFactory {
    
    private SessionFactory factory = null;
    private static DvdStoreSessionFactory sessionFactory = null;
    
    private DvdStoreSessionFactory() {

    }
    
    // this fuction is used to get the factory reference
    public SessionFactory getSessionFactory() throws DvdStoreException {
        try{  
            if (null == factory) {
                factory = new Configuration().
                 configure("hibernate.cfg.xml").
                 buildSessionFactory();
            }
        } catch(HibernateException e) { 
            DvdStoreLogger.error("connection probleam", e);
            throw new DvdStoreException(Constants.ERROR_MESSAGE);
        }
        return factory;  
    }
    
    // this fuction is used to get the class reference
    public static DvdStoreSessionFactory getInstance()
    {
        if (null == sessionFactory) {
            sessionFactory = new DvdStoreSessionFactory();
        }
        return sessionFactory;
    }
    
}
