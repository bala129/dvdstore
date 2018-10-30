package com.ideas2it.dvdstore.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.utils.DateUtils;

/**
 * Is contains the details of dvd in the dvdstore
 * It's means single dvd detail like dvd name, price ect
 * It's not store the detail of total dvds in the dvdstore 
 *
 * @version 1
 * @author Balamurugan
 */
public class Dvd {
    
    private Integer dvdId;
    private Integer price;
    private Double rating;
    private String dvdName;
    private String dvdType;
    private String language; 
    private Date releaseDate;
    private List<DvdCategory> categories = new ArrayList<DvdCategory>();
    private Boolean status;
    
    /** 
     * The constructor with different paramater 
     * is used to set the dvd details 
     */
    public Dvd(Integer price, Double rating,String dvdName, String dvdType, 
               String language) {
        this.dvdType = dvdType;
        this.price = price;
        this.rating = rating;
        this.dvdName = dvdName;
        this.language = language;
    }
    
    /** 
     * The constructor with no paramater 
     */
    public Dvd() {
    }
    
    public void setCategories(List<DvdCategory> categories) {
        this.categories = categories;
    }
    
    public List<DvdCategory> getCategories() {
        return categories;
    }
    
    public String getDvdName() {
        return dvdName;
    }
    
    public void setDvdName(String dvdName) {
        this.dvdName = dvdName;
    }
    
    public String getDvdType() {
        return dvdType;
    }
    
    public void setDvdType(String dvdType) {
        this.dvdType = dvdType;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public void setDvdId(Integer dvdId) {
        this.dvdId = dvdId;
    }
    
    public Integer getDvdId() {
        return dvdId;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getReleaseDate() {
        return releaseDate;
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
        StringBuilder dvdInfo = new StringBuilder();
        dvdInfo.append(Constants.LABEL_DVD_ID).append(dvdId)
            .append(Constants.LABEL_DVD_NAME).append(dvdName)
            .append(Constants.LABEL_DVDTYPE).append(dvdType)
            .append(Constants.LABEL_LANGUAGE).append(language)
            .append(Constants.LABEL_PRICE).append(price)
            .append(Constants.LABEL_RATING).append(rating)
            .append(Constants.LABEL_RELEASE_DATE)
            .append(releaseDate);
            if (! categories.isEmpty()) {
                dvdInfo.append(Constants.LABEL_CATEGORY);
                for (DvdCategory dvdCategory : categories) {
                    dvdInfo.append(dvdCategory.getCategory()).append("  ");
                }
            }
        return dvdInfo.toString();
    }
    
    /**
     * <p>
     * Here {@code equals} is overriding to 
     * compare the dvd details
     * </p>
     * 
     * @return true or flase
     *       return true when the dvd is equal
     *       return flase when the dvd is not equal
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return Boolean.TRUE;
        }
        if (!(object instanceof Dvd) || null == object) {
            return Boolean.FALSE;
        }
        Dvd dvd = (Dvd)object;
        return ((this.dvdType).equals(dvd.dvdType)
                && (this.language).equals(dvd.language)
                && (this.dvdName).equals(dvd.dvdName)); 
    }
} 
