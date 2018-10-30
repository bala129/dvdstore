package com.ideas2it.dvdstore.utils;

public class DvdStoreUtils {
    
    /**
     * <p>
     * Used to validate the mail id formate 
     * </p>
     * 
     * @param mailId
     *        the mailId is contain the mailid of the customer 
     */
    public Boolean validateMailId(String mailId) {
        return (mailId.matches(
                "([a-zA-Z0-9.]{1,30})@([a-zA-Z0-9]{1,10})[.]([a-zA-Z]{2,5})"));
    }
    
    /**
     * <p>
     * Here check the length of the DVD details
     * </p>
     * 
     * @param detail
     *        the detail is contain the detail of the DVD 
     */
    public Boolean checkLength(String detail) {
        return (detail.length() <= 50);
    }
    
    /**
     * <p>
     * Here check the detail contains only character or not
     * </p>
     * 
     * @param detail
     *        the detail is contain the detail of the DVD 
     */
    public Boolean checkDetail(String detail) {
        return (detail.matches("[a-zA-Z]+"));
    }
    
    /**
     * <p>
     * Used to validate the mobileNo
     * </p>
     * 
     * @param mobileNo
     *        Is contain the mobileNo of the customer 
     */
    public Boolean validateMobileNo(String mobileNo) {
        return (mobileNo.matches("[0-9]{10}"));
    }
}
