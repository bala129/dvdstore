package com.ideas2it.dvdstore.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.dao.impl.OrderDaoImpl;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.DvdStoreService;
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.service.impl.DvdStoreServiceImpl;

/**
 * <p>
 * The {@code OrderServiceImpl} is represents the collection of 
 * the order service functions
 * This act the intermediate between the controller class and Dao
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    
    /** {@inheritDoc}*/
    public List<CustomerOrder> displayAllOrder() 
            throws DvdStoreException {
        return orderDao.fetchAllOrder();
    }
    
    /** {@inheritDoc}*/
    public List<CustomerOrder> displayOrder(Customer customer) 
            throws DvdStoreException {
        return orderDao.fetchOrder(customer);
    }
    
    /** {@inheritDoc}*/
    public Boolean addOrders(List<CustomerOrder> orders) 
            throws DvdStoreException {
        return orderDao.addOrders(orders);
    }
    
    /** {@inheritDoc}*/
    public Boolean removeOrder(Integer orderId) throws DvdStoreException {
        return orderDao.removeOrder(orderId);
    }
}
