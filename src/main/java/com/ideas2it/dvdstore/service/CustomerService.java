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
 * The {@code CustomerService} is used to represents the declarations of  
 * different action on the customer 
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 */
public interface CustomerService {
    
    /**
     * <p>
     * The{@code createCustomer} is used to get the
     * customer details from user and store into the DVD store
     * </p>
     *
     * @param customer
     *        getting the object of the Customer class
     */
    Boolean createCustomer(Customer customer) throws DvdStoreException;
    
    /**
     * <p>
     * The{@code updateDvd} is used to update the 
     * dvd details in dvd store
     * </p>
     *
     * @param customer
     *        getting the object of the Customer class  
     */
    Boolean updateCustomer(Customer customer) throws DvdStoreException;
    
    /**
     * The {@code displayDisplay} is used to print the
     * all customer in the store
     */
    List<Customer> displayCustomer() throws DvdStoreException;
    
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
     * <p>
     * Here check the choice is valid 
     * It's means the choice is matched or not
     * </p>
     * 
     * @param choice
     *        the choice is used to get the choice
     */ 
    Dvd checkChoice(Integer choice) throws DvdStoreException;
    
    /**
     * <p>
     * The {@code searchCustomerByUserId} is used to find required customer
     * from the dvdstore
     * </p>
     *
     * @param id
     *       it's used to find the customer in dvdstore  
     */
    Customer searchCustomerByUserId(Integer id) throws DvdStoreException;
    
    /**
     * <p>
     * Is used to display dvds from the dvd store
     * </p>
     *
     * @param detail 
     *       it's used to find the dvds in dvdstore  
     */
    List<Dvd> displayDvdByInfo(String detail) throws DvdStoreException;
    
    /**
     * Used to display the dvds in the dvd store
     */
    List<Dvd> displayAllDvd() throws DvdStoreException;
    
    /**
     * <p>
     * Here check the choice is valid 
     * It's means the choice is matched or not
     * </p>
     * 
     * @param choice
     *        the choice is used to get the choice
     */ 
    Boolean validateChoice(Integer choice) throws DvdStoreException;
    
    /**
     * <p>
     * Used to display the dvd from dvd store by category
     * </p>
     *
     * @param choice
     *       the choice can used to get category id 
     */
    DvdCategory displayDvdbyCategory(Integer choice) throws DvdStoreException;
    
    /**
     * Used to get the categories from user
     */
    List<DvdCategory> getCategory(Boolean status) throws DvdStoreException;
    
    /**
     * Used to add the address details to customer 
     *
     * @param address
     *        Used to get the customer address
     */
    Boolean addAddress(Address address) throws DvdStoreException;
    
    /**
     * <p>
     * Is used to purchase the dvd from the dvd store
     * </p>
     * 
     * @param orders
     *        Used to get the order details  
     */ 
    Boolean purchaseDvd(List<CustomerOrder> orders) throws DvdStoreException;
    
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
     * Used to remove the order customer detail 
     *
     * @param orderId
     *        Used to get the order details
     */
    Boolean removeOrder(Integer orderId) throws DvdStoreException;
}
