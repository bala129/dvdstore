package com.ideas2it.dvdstore.dao.impl;

import java.util.ArrayList;
import java.util.List;
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
import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.session.DvdStoreSessionFactory;

/**
 * <p>
 * The {@code CustomerDaoImpl} is used to perform the 
 * action on the dvd store 
 * </p>
 * <p> 
 * Here we perform add the customer, update the customer, display customer and
 * search the customer on the dvdstore
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 */
public class CustomerDaoImpl implements CustomerDao {
    
    private DvdStoreSessionFactory dvdStoreSessionFactory 
        = DvdStoreSessionFactory.getInstance();
    private SessionFactory factory;
    private Session session;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean addCustomer(Customer customer) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(customer); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                ADD_ERROR_MESSAGE+customer.getMailId(), e);
            throw new DvdStoreException(Constants.
                ADD_ERROR_MESSAGE+customer.getMailId());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Customer searchCustomer(Integer id) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query 
                = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            query.select(root).where(criteriaBuilder
                .equal(root.get("customerId"), id));
            Customer customer = session.createQuery(query).uniqueResult();
            return customer;
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.SEARCH_ERROR_MESSAGE+id, e);
            throw new DvdStoreException(Constants.SEARCH_ERROR_MESSAGE+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateCustomer(Customer customer) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(customer); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                CUSTOMER_UPDATE_ERROR+customer.getMailId(), e);
            throw new DvdStoreException(Constants.
                CUSTOMER_UPDATE_ERROR+customer.getMailId());
        } finally {
            session.close();
        }
    }
    
    /** {@inheritDoc}*/
    public Customer searchCustomerByUserId(Integer userId) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query 
                = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = query.from(Customer.class);
            query.select(root).where(criteriaBuilder
                .equal(root.get("user"), userId));
            Customer customer = session.createQuery(query).uniqueResult();
            return customer;
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.SEARCH_ERROR_MESSAGE+userId, e);
            throw new DvdStoreException(Constants.SEARCH_ERROR_MESSAGE+userId);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<Customer> fetchCustomer() throws DvdStoreException {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            customers = session.createQuery("FROM Customer").list();
            return customers;
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.ERROR_MESSAGE, e);
            throw new DvdStoreException(Constants.ERROR_MESSAGE);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean addAddress(Address address) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(address); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                ADDRESS_ADD_ERROR+address.getAddressId(), e);
            throw new DvdStoreException(Constants.
                ADDRESS_ADD_ERROR+address.getAddressId());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean removeAddress(Address address) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.delete(address); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.
                DELETE_ERROR_MESSAGE+address.getAddressId(), e);
            throw new DvdStoreException(Constants.
                DELETE_ERROR_MESSAGE+address.getAddressId());
        } finally {
            session.close(); 
        }
    }
}
