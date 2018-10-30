package com.ideas2it.dvdstore.model;

/**
 * <p>
 * Is contains the address of the customer in the dvdstore 
 * It store the sigle address of the customer
 * If customer have more then one address then more address object are created 
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class Address {
    private Integer addressId;
    private Integer customerId;
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private Integer pincode;
    
    /** 
     * The constructor with no paramater 
     */
    public Address() {
    
    } 
    
    /** 
     * The constructor with different paramater 
     * is used to set the dvd details 
     */ 
    public Address(String addressLine, String city, String state, String country,
            Integer pincode) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
    
    /**
     * The getter and setter method used get and set values
     */
    public Integer getAddressId() {
        return addressId;
    }
    
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public String getAddressLine() {
        return addressLine;
    }
    
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Integer getPincode() {
        return pincode;
    }
    
    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }
    
    /** 
     * Here {@code ToString} is overriding to 
     * convert class reference to string
     */
    @Override 
    public String toString() {
        StringBuilder addressInfo = new StringBuilder();
        addressInfo.append(addressLine).append("  ").append(city).append("  ")
            .append(state).append("  ").append(country).append("  ")
            .append(pincode);
        return addressInfo.toString();
    }    
}
