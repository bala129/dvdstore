package com.ideas2it.dvdstore.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * Is  contains the order details of customer 
 * It's hold the single order details of the customer only 
 * It's not hold the whole order list of the customer
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
 public class CustomerOrder {
     
    private Integer orderId;
    private Integer customerId;
    private Date orderDate;
    private Dvd dvd;
    private Address address;
     
    /** 
     * The constructor with different paramater 
     * is used to set the dvd details 
     */ 
    public CustomerOrder(Integer customerId, Date orderDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;         
    }
    
    /** 
     * The constructor with no paramater 
     */
    public CustomerOrder() {
    }
    
    public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public Dvd getDvd() {
        return dvd;
    }  
    
    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }
    
    public Address getAddress() {
        return address;
    }  
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    /** 
     * Here {@code toString} is overriding to 
     * convert class reference to string
     */
    @Override 
    public String toString() {
        StringBuilder orderInfo = new StringBuilder();
        orderInfo.append(Constants.LABEL_ORDER_ID).append(orderId)
            .append(Constants.LABEL_CUSTOMERID).append(customerId)
            .append(Constants.DVD_ID)
            .append(Constants.LABEL_ORDER_DATE).append(orderDate).append("\n");
        return orderInfo.toString();
    }
 }
