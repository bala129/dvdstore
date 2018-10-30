package com.ideas2it.dvdstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;

/**
 * <p>
 * The {@code OrderDao} is used to represents the declarations of  
 * different action on the order 
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 */
public interface OrderDao {
    
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
     * Used to display the order details of the customer
     *
     * @param customer
     *        customer is used to get the reference of the customer class 
     */
    List<CustomerOrder> fetchOrder(Customer customer) throws DvdStoreException;
    
    /**
     * <p>
     * Is used to display all order details in the dvd store
     * </p>
     */ 
    List<CustomerOrder> fetchAllOrder() throws DvdStoreException;
}
