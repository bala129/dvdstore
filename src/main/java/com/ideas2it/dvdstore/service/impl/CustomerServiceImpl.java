package com.ideas2it.dvdstore.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.dao.DvdStoreDao;
import com.ideas2it.dvdstore.dao.impl.CustomerDaoImpl;
import com.ideas2it.dvdstore.dao.impl.DvdStoreDaoImpl;
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
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;

/**
 * <p>
 * The {@code CustomerServiceImpl} is represents the collection of 
 * the customer service functions
 * This act the intermediate between the controller class and model
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class CustomerServiceImpl implements CustomerService {
    
    private CustomerDao customerDao;
    private CategoryService categoryService;
    private OrderService orderService;
    private DvdStoreService dvdService;
    
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    
    public void setDvdService(DvdStoreService dvdService) {
        this.dvdService = dvdService;
    }
    
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    /** {@inheritDoc}*/ 
    public Boolean createCustomer(Customer customer) throws DvdStoreException {
        return customerDao.addCustomer(customer);
    }
    
    /** {@inheritDoc}*/
    public Customer searchCustomerByUserId(Integer id) throws DvdStoreException {
        return customerDao.searchCustomerByUserId(id);
    }
    
    /** {@inheritDoc}*/ 
    public Boolean updateCustomer(Customer customer) throws DvdStoreException {
        return customerDao.updateCustomer(customer);
    }
    
    /** {@inheritDoc}*/
    public List<Customer> displayCustomer() throws DvdStoreException {
        return customerDao.fetchCustomer();
    }
    
    /** {@inheritDoc}*/
    public Customer searchCustomer(Integer id) throws DvdStoreException {
        return customerDao.searchCustomer(id);
    }
    
    /** {@inheritDoc}*/
    public Boolean purchaseDvd(List<CustomerOrder> orders) 
            throws DvdStoreException {
        return orderService.addOrders(orders);
    }
    
    /** {@inheritDoc}*/
    public List<CustomerOrder> displayOrder(Customer customer) 
            throws DvdStoreException {
        return orderService.displayOrder(customer);
    }
    
    /** {@inheritDoc}*/
    public Boolean removeOrder(Integer orderId) throws DvdStoreException {
        return orderService.removeOrder(orderId);
    }
    
    /** {@inheritDoc}*/
    public Dvd checkChoice(Integer choice) throws DvdStoreException {
        return dvdService.searchDvdById(choice, Constants.LABEL_ACTIVE);
    }
    
    /** {@inheritDoc}*/
    public List<Dvd> displayAllDvd() throws DvdStoreException {
        return dvdService.displayDvd(Constants.LABEL_ACTIVE);
    }
    
    /** {@inheritDoc}*/
    public List<Dvd> displayDvdByInfo(String detail) throws DvdStoreException {
        return dvdService.searchDvdByDetail(detail, Constants.LABEL_ACTIVE);
    }
    
    /** {@inheritDoc}*/
    public Boolean validateChoice(Integer choice) throws DvdStoreException {
         return (null != (categoryService.validateChoice(choice)));
    }
    
    /** {@inheritDoc}*/
    public List<DvdCategory> getCategory(Boolean status) 
            throws DvdStoreException {
        return categoryService.getCategory(status);
    }
    
    /** {@inheritDoc}*/
    public DvdCategory displayDvdbyCategory(Integer choice) 
            throws DvdStoreException {
        return categoryService.searchCategoryById(choice);
    }
    
    /** {@inheritDoc}*/
    public Boolean addAddress(Address address) throws DvdStoreException {
        return customerDao.addAddress(address);
    }
}
