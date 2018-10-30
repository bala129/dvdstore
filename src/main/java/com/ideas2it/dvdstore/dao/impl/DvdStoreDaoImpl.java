package com.ideas2it.dvdstore.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.DvdStoreDao;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.session.DvdStoreSessionFactory;

/**
 * <p>
 * The {@code DvdStoreDaoImpl} is used to perform the 
 * action on the dvd store 
 * </p>
 * <p> 
 * Here we perform the adding the dvd to the dvd store 
 * Removing the dvd from the dvd store
 * display the dvds and find the dvd from the dvd store
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.connection.DvdStoreConnection
 * @see com.ideas2it.dvdstore.dao.DvdStoreDao
 * @see com.ideas2it.dvdstore.model.Dvd 
 */
public class DvdStoreDaoImpl implements DvdStoreDao {
    
    private DvdStoreSessionFactory dvdStoreSessionFactory 
        = DvdStoreSessionFactory.getInstance();
    private SessionFactory factory;
    private Session session;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean addDvd(Dvd dvd) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(dvd); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.DVD_ADD_ERROR+dvd.
                getDvdType()+dvd.getLanguage(), e);
            throw new DvdStoreException(Constants.DVD_ADD_ERROR+dvd.
                getDvdType()+dvd.getLanguage());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateDvd(Dvd dvd) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(dvd); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.DVD_UPDATE_ERROR+dvd.
                getDvdType()+dvd.getLanguage(), e);
            throw new DvdStoreException(Constants.DVD_UPDATE_ERROR+dvd.
                getDvdType()+dvd.getLanguage());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<Dvd> displayDvd(Boolean status) throws DvdStoreException {
        Dvd dvd = null;
        List<Dvd> dvds = new ArrayList<Dvd>();
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            session.enableFilter("statusFilter").
                setParameter("status",Constants.LABEL_ACTIVE);
            String query = "FROM Dvd WHERE status = :status";
            return session.createQuery(query).
                setParameter("status", status).list();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.ERROR_MESSAGE, e);
            throw new DvdStoreException(Constants.ERROR_MESSAGE);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteDvd(Dvd dvd) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            Dvd result = (Dvd)session.get(Dvd.class, dvd.getDvdId());
            result.setStatus(Boolean.FALSE);
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                DVD_DELETE_ERROR+dvd.getDvdName(), e);
            throw new DvdStoreException(Constants.
                DVD_DELETE_ERROR+dvd.getDvdName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean restoreDvd(Integer dvdId) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            Dvd result = (Dvd)session.get(Dvd.class, dvdId);
            result.setStatus(Boolean.TRUE);
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.DVD_RESTORE_ERROR+dvdId, e);
            throw new DvdStoreException(Constants.DVD_RESTORE_ERROR+dvdId);
        } finally {
            session.close(); 
        }
    }
   
    /** {@inheritDoc}*/
    public Dvd searchDvd(String dvdName, String dvdType, String language, 
            Boolean status) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> query = criteriaBuilder.createQuery(Dvd.class);
            Root<Dvd> root = query.from(Dvd.class);
            Predicate name = criteriaBuilder.equal(root.get("dvdName"), dvdName);
            Predicate type = criteriaBuilder.equal(root.get("dvdType"), dvdType);
            Predicate lang = criteriaBuilder.equal(root.get("language"), 
                language);
            Predicate active = criteriaBuilder.equal(root.get("status"), status);
            query.select(root).where(criteriaBuilder.and(name, type, lang, 
                active));            
            return session.createQuery(query).uniqueResult();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.DVD_SEARCH_ERROR+dvdName, e);
            throw new DvdStoreException(Constants.DVD_SEARCH_ERROR+dvdName);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Dvd searchDvdById(Integer choice, Boolean status) 
            throws DvdStoreException { 
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> query = criteriaBuilder.createQuery(Dvd.class);
            Root<Dvd> root = query.from(Dvd.class);
            Predicate active = criteriaBuilder.equal(root.get("status"), status);
            Predicate id = criteriaBuilder.equal(root.get("dvdId"), choice);
            query.select(root).where(criteriaBuilder.and(active, id));            
            return (Dvd)session.createQuery(query).uniqueResult();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.DVD_SEARCH_ERROR+choice, e);
            throw new DvdStoreException(Constants.DVD_SEARCH_ERROR+choice);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<Dvd> searchDvdByDetail(String detail, Boolean status) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> query = criteriaBuilder.createQuery(Dvd.class);
            Root<Dvd> root = query.from(Dvd.class);
            Predicate active = criteriaBuilder.equal(root.get("status"), status);
            Predicate name = criteriaBuilder.like(root.get("dvdName"), 
                "%"+detail+"%");
            query.select(root).where(criteriaBuilder.and(active, name));            
            return session.createQuery(query).list();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.DVD_SEARCH_ERROR+detail, e);
            throw new DvdStoreException(Constants.DVD_SEARCH_ERROR+detail);
        } finally {
            session.close(); 
        }
    }
}
