package com.ideas2it.dvdstore.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.model.User;


/**
 * <p>
 * Is contains the details of customer in the dvdstore 
 * It's means it's contains personal details only
 * And it's not contains the order details of the customer
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class Customer {
    
    private Integer customerId;
    private String customerName;
    private String mailId;
    private String mobileNo;
    private List<Address> address;
    private List<CustomerOrder> orders;
    private User user;
    
    /** 
     * The constructor with different paramater 
     * is used to set the dvd details 
     */
    public Customer(String customerName, String mobileNo, String mailId) {
        this.customerName = customerName;
        this.mobileNo = mobileNo;
        this.mailId = mailId;
    }
    
    /** 
     * The constructor with no paramater 
     */
    public Customer() {
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getMailId() {
        return mailId;
    }
    
    public void setMailId(String mailId) {
        this.mailId = mailId;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public List<CustomerOrder> getOrders() {
        return orders;
    }
    
    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }
    
    public List<Address> getAddress() {
        return address;
    }
    
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    
}
