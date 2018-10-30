package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;

/**
 * <p>
 * The {@code UserService} is used to represents the declarations of  
 * different action on the user 
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 */
public interface UserService {
    
    /**
     * Used to create the User in the dvdstore 
     *
     * @param user
     *        Used to get the User reference
     */
    Boolean createUser(User user) throws DvdStoreException;
    
    /**
     * Used to find the user in the dvd store 
     *
     * @param userName, password, role
     *        Used to find the unique user
     */
    User searchUser(String userName, String password, String role) 
            throws DvdStoreException;
    
    /**
     * Used to check the user username is already exist
     *
     * @param userName
     *        Used to find the username
     */
    User searchName(String userName) throws DvdStoreException;
    
    /**
     * Used to check the customer details are exsit in the dvdstore
     * @param id
     *        Used to find the user
     */
    Customer checkCustomerDetail(Integer id) throws DvdStoreException;
}
