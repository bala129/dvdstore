package com.ideas2it.dvdstore.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.utils.DateUtils;

/**
 * <p>
 * Is contains the single category of the dvd 
 * If have a more categories then create the 
 * DvdCategory object for each category
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class DvdCategory {
    
    private Integer id;
    private String category;
    private List<Dvd> dvds = new ArrayList<Dvd>();
    private Boolean status;
    
    /** 
     * The constructor with different paramater 
     * is used to set the category details 
     */
    public DvdCategory(Integer id, String category) {
    this.category = category;
    this.id = id;
    }
    
    /** 
     * The constructor with no paramater 
     */
    public DvdCategory() {
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<Dvd> getDvds() {
        return dvds;
    }
    
    public void setDvds(List<Dvd> dvds) {
        this.dvds = dvds;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public Boolean getStatus() {
        return status;
    }  
    
    /** 
     * Here {@code ToString} is overriding to 
     * convert class reference to string
     */
    @Override 
    public String toString() {
        DateUtils dateUtils = new DateUtils();
        StringBuilder dvdInfo = new StringBuilder(category).append("\n");
        return dvdInfo.toString();
    }
    
   /**
     * <p>
     * Here {@code equals} is overriding to 
     * compare the category details
     * </p>
     * 
     * @return true or flase
     *       return true when the dvd is equal
     *       return flase whem the dvd is not equal
     */
    @Override 
    public boolean equals(Object object) {
        if (this == object) {
            return Boolean.TRUE;
        }
        if (!(object instanceof DvdCategory) || null == object) {
            return Boolean.FALSE;
        }
        DvdCategory dvdCategory = (DvdCategory)object;
        return ((this.id).equals(dvdCategory.id)
                && (this.category).equals(dvdCategory.category)); 
    }
}
