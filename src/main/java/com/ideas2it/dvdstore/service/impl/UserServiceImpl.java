package com.ideas2it.dvdstore.service.impl;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.dao.impl.UserDaoImpl;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdstore.service.UserService;

/**
 * <p>
 * The {@code UserServiceImpl} is represents the collection of 
 * the user service functions
 * This act the intermediate between the controller class and Dao
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private CustomerService customerService;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    /** {@inheritDoc}*/
    public Boolean createUser(User user) throws DvdStoreException {
        return userDao.createUser(user);
    }
    
    /** {@inheritDoc}*/
    public User searchUser(String userName, String password, String role) 
            throws DvdStoreException {
        return userDao.searchUser(userName, password, role);
    }
    
    /** {@inheritDoc}*/
    public User searchName(String userName) throws DvdStoreException {
        return userDao.searchName(userName);
    }
    
    /** {@inheritDoc}*/
    public Customer checkCustomerDetail(Integer id) throws DvdStoreException {
        return customerService.searchCustomerByUserId(id);
    }
}
