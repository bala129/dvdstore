package com.ideas2it.dvdstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;

/**
 * <p>
 * The {@code CustomerDao} is used to represents the declarations of  
 * different action on the customer 
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 */
public interface CustomerDao {

    /**
     * The {@code addCustomer} is used to add the customer to dvd store
     *
     * @param customer
     *        customer is used to get the reference of the customer class 
     */
    Boolean addCustomer(Customer customer) throws DvdStoreException;
    
    /**
     * <p>
     * The {@code searchCustomer} is used to find required customer
     * from the dvdstore
     * </p>
     *
     * @param id
     *       it's used to find the customer in dvdstore  
     */
    Customer searchCustomer(Integer id) throws DvdStoreException;
   
    /**
     * The {@code updateCustomer} is used to update the customer details
     * to dvd store
     *
     * @param customer
     *        customer is used to get the reference of the customer class 
     */
    Boolean updateCustomer(Customer customer) throws DvdStoreException;
    
    /**
     * <p>
     * The {@code searchCustomerByUserId} is used to find required customer
     * from the dvdstore
     * </p>
     *
     * @param userid
     *       it's used to find the customer in dvdstore  
     */
    Customer searchCustomerByUserId(Integer userId) throws DvdStoreException;
    
    /**
     * The {@code fetchCustomer} is used to print the
     * all customer in the dvdstore
     */
    List<Customer> fetchCustomer() throws DvdStoreException;
    
    /**
     * Used to add the address details to customer 
     *
     * @param address
     *        Used to get the customer address
     */
    Boolean addAddress(Address address) throws DvdStoreException;
}
