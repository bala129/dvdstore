package com.ideas2it.dvdstore.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Query;  
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.session.DvdStoreSessionFactory;

/**
 * <p>
 * The {@code CategoryDaoImpl} is used to perform the 
 * action on the dvd store 
 * </p>
 * <p> 
 * Here we perform the adding the category to the dvd 
 * Removing the category from the dvd category
 * display the dvds and find the dvd from the dvd store by category
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.connection.DvdStoreConnection
 * @see com.ideas2it.dvdstore.dao.DvdStoreDao
 * @see com.ideas2it.dvdstore.model.Dvd 
 */
public class CategoryDaoImpl implements CategoryDao {

    private DvdStoreSessionFactory dvdStoreSessionFactory 
        = DvdStoreSessionFactory.getInstance();
    private SessionFactory factory;
    private Session session;
    private Transaction transaction = null;
        
    /** {@inheritDoc}*/
    public List<DvdCategory> fetchCategory(Boolean status) 
            throws DvdStoreException { 
        List<DvdCategory> categories = new ArrayList<DvdCategory>();
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            session.enableFilter("statusFilter").setParameter("status",status);
            String query = "FROM DvdCategory WHERE status = :status";
            return session.createQuery(query)
                .setParameter("status", status).list();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.ERROR_MESSAGE, e);
            throw new DvdStoreException(Constants.ERROR_MESSAGE);
        } finally {
            session.close(); 
        }
    }

    /** {@inheritDoc}*/
    public DvdCategory checkChoice(Integer choice, Boolean status) 
            throws DvdStoreException { 
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<DvdCategory> query 
                = criteriaBuilder.createQuery(DvdCategory.class);
            Root<DvdCategory> root = query.from(DvdCategory.class);
            Predicate active = criteriaBuilder.equal(root.get("status"), status);
            Predicate id = criteriaBuilder.equal(root.get("id"), choice);
            query.select(root).where(criteriaBuilder.and(active, id));            
            return session.createQuery(query).uniqueResult();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.CATEGORY_CHECK_ERROR+choice, e);
            throw new DvdStoreException(Constants.CATEGORY_CHECK_ERROR+choice);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean insertCategory(DvdCategory category) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(category); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                CATEGORY_ADD_ERROR+category.getCategory(), e);
            throw new DvdStoreException(Constants.
                CATEGORY_ADD_ERROR+category.getCategory());
        } finally {
            session.close(); 
        }
    }
        
    /** {@inheritDoc}*/
    public Boolean restoreCategory(Integer id) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            DvdCategory result
                = (DvdCategory)session.get(DvdCategory.class, id);
            result.setStatus(Boolean.TRUE);
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.CATEGORY_RESTORE_ERROR+id, e);
            throw new DvdStoreException(Constants.CATEGORY_RESTORE_ERROR+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateCategory(DvdCategory category) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                CATEGORY_UPDATE_ERROR+category.getCategory(), e);
            throw new DvdStoreException(Constants.
                CATEGORY_UPDATE_ERROR+category.getCategory());
        } finally {
            session.close(); 
        }
    }
       
    /** {@inheritDoc}*/    
    public Boolean removeCategory(Integer id) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            DvdCategory result 
                = (DvdCategory)session.get(DvdCategory.class, id);
            result.setStatus(Boolean.FALSE);
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.CATEGORY_DELETE_ERROR+id, e);
            throw new DvdStoreException(Constants.CATEGORY_DELETE_ERROR+id);
        } finally {
            session.close(); 
        }
    }
   
    /** {@inheritDoc}*/
    public DvdCategory searchCategoryById(Integer id) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            session.enableFilter("statusFilter").setParameter("status",true);
            return (DvdCategory)session.get(DvdCategory.class, id);
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.CATEGORY_SEARCH_BYID_ERROR+id, e);
            throw new DvdStoreException(Constants.CATEGORY_SEARCH_BYID_ERROR+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public DvdCategory searchCategoryByName(String category, Boolean status) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            session.enableFilter("statusFilter").setParameter("status",true);
            String query = "FROM DvdCategory WHERE status = :status AND"+
                " category= :category";
            Query resultQuery = session.createQuery(query);
            resultQuery.setParameter("status", status);
            resultQuery.setParameter("category", category);
            return (DvdCategory)resultQuery.uniqueResult();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.CATEGORY_SEARCH_BYNAME_ERROR+
                category, e);
            throw new DvdStoreException(Constants.CATEGORY_SEARCH_BYNAME_ERROR+
                category);
        } finally {
            session.close(); 
        }
    }
}
