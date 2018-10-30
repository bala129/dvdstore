package com.ideas2it.dvdstore.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;

/**
 * <p>
 * The {@code OrderService} is used to represents the declarations of  
 * different action on the Order 
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 */
public interface OrderService {
    
    /**
     * Used to add the orderlist to customer detail 
     *
     * @param orders
     *        Used to get the order details
     */
    Boolean addOrders(List<CustomerOrder> orders) throws DvdStoreException;
    
    /**
     * Used to remove the order customer detail 
     *
     * @param orderId
     *        Used to get the order details
     */
    Boolean removeOrder(Integer orderId) throws DvdStoreException;
    
    /**
     * <p>
     * Is used to display order details from the dvd store
     * </p>
     * 
     * @param customer
     *        getting the object of the Customer class  
     */ 
    List<CustomerOrder> displayOrder(Customer customer) throws DvdStoreException;
    
    /**
     * <p>
     * Is used to display all order details in the dvd store
     * </p>
     */ 
    List<CustomerOrder> displayAllOrder() throws DvdStoreException;
}
