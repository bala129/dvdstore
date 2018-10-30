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
import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.session.DvdStoreSessionFactory;

/**
 * <p>
 * The {@code OrderDaoImpl} is used to perform the 
 * action on the orders
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class OrderDaoImpl implements OrderDao {
    
    private DvdStoreSessionFactory dvdStoreSessionFactory 
        = DvdStoreSessionFactory.getInstance();
    private SessionFactory factory;
    private Session session;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean addOrders(List<CustomerOrder> orders) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            for (CustomerOrder order : orders) {
                session.save(order); 
            }
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.ORDER_ADD_ERROR+orders.size(), e);
            throw new DvdStoreException(Constants.ORDER_ADD_ERROR+orders.size());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<CustomerOrder> fetchOrder(Customer customer) 
            throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            String query = "FROM CustomerOrder WHERE customerId=:id";
            Query resultQuery = session.createQuery(query);
            resultQuery.setParameter("id", customer.getCustomerId());
            return resultQuery.list();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.ORDER_FETCH_ERROR+
                customer.getCustomerName(), e);
            throw new DvdStoreException(Constants.ORDER_FETCH_ERROR+
                customer.getCustomerName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<CustomerOrder> fetchAllOrder() throws DvdStoreException {
        List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            orders = session.createQuery("FROM CustomerOrder").list();
            transaction.commit();
            return orders;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.ERROR_MESSAGE, e);
            throw new DvdStoreException(Constants.ERROR_MESSAGE);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean removeOrder(Integer orderId) throws DvdStoreException {
        try {
            factory = dvdStoreSessionFactory.getSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            CustomerOrder order 
                = (CustomerOrder)session.get(CustomerOrder.class, orderId);
            session.delete(order); 
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            DvdStoreLogger.error(Constants.ORDER_DELETE_ERROR+orderId, e);
            throw new DvdStoreException(Constants.ORDER_DELETE_ERROR+orderId);
        } finally {
            session.close(); 
        }
    }
}
