package com.ideas2it.dvdstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * The {@code DvdStoreService} is represents the collection of 
 * the DVD service functions declarations
 * </p>
 * <p>
 * Here we declare the functions are create the dvd, update the dvd, 
 * delete the dvd on dvd store 
 * And display the display the dvds in the dvd store  
 * </p>
 *
 * @version 1
 * @author Balamurugan
 * @see com.ideas2it.dvdstore.model.Dvd
 */
public interface DvdStoreService {
    
    /**
     * <p>
     * Here check the price is valid 
     * It's means the price is inbetween the 0 to 100
     * </p>
     * 
     * @param price
     *        the price is contain the value of the DVD 
     */
    Boolean validatePrice(Integer price);
     
    /**
     * <p>
     * Here check the rating is valid 
     * It's means the rating is inbetween the 0 to 10
     * </p>
     * 
     * @param rating
     *        the rating is contain the rating of the DVD 
     */
    Boolean validateRating( Double rating);
    
    /**
     * <p>
     * Here check the length of the DVD details
     * like dvdname, language, moviename
     * </p>
     * 
     * @param detail
     *        the detail is contain the detail of the DVD 
     */
    Boolean checkLength(String detail);
    
    /**
     * This function used to get the category from user
     */
    List<DvdCategory> getCategory() throws DvdStoreException;
    
    /**
     * This function used to validate the category
     * It's means the category is already there in the dvd detail 
     *
     * @param choice
     *       the choice can used to get category id 
     */
    Integer checkCategory(Integer choice,
            List<DvdCategory> categories) throws DvdStoreException;
    
    /**
     * <p>
     * The{@code createDvd} is used to get the
     * DVD details from user and store into the DVD store
     * </p>
     *
     * @param dvd
     *        getting the object of the Dvd class
     */
    Boolean createDvd(Dvd dvd) throws DvdStoreException;
    
    /**
     * The {@code displayDvd} is used to print the
     * all DVD in the store
     */
    List<Dvd> displayDvd(Boolean Status) throws DvdStoreException;
        
    /**
     * <p>
     * The{@code deleteDvd} is used to delete the 
     * dvd in dvd store
     * </p>
     *
     * @param dvd
     *        getting the object of the Dvd class as a parameter 
     */
    Boolean deleteDvd(Dvd dvd) throws DvdStoreException;
    
    /**
     * <p>
     * The{@code updateDvd} is used to update the 
     * dvd details in dvd store
     * </p>
     *
     * @param dvd
     *        getting the object of the Dvd class as a parameter 
     */
    Boolean updateDvd(Dvd dvd) throws DvdStoreException;
    
    /**
     * Used to restore the removed dvd from dvdstore
     *
     * @param dvdName and category, language
     *       it's used to find the dvd in dvdstore  
     */
    Boolean restoreDvd(Integer dvdId) throws DvdStoreException;
    
    /**
     * <p>
     * The {@code searchDvd} is used to find required dvd from dvdstore
     * </p>
     *
     * @param dvdName and category, language
     *       it's used to find the dvd in dvdstore  
     */
    Dvd searchDvd(String dvdName, String dvdType, String language,
            Boolean status) throws DvdStoreException;
    /**
     * <p>
     * It's used to check the dvd is there in the dvd store  
     * </p>
     *
     * @param choice, status
     *        Is used to get the dvd  
     */
    Dvd searchDvdById(Integer choice, Boolean status) throws DvdStoreException;
    
    /**
     * Is used to find the dvds by category
     *
     * @param id
     *       used to get the category id
     */
    DvdCategory searchDvdByCategory(Integer id) throws DvdStoreException;     
    
    /**
     * <p>
     * It's used to check the dvd is there in the dvd store  
     * </p>
     *
     * @param detail, status
     *        Is used to get the dvd  
     */
    List<Dvd> searchDvdByDetail(String detail, Boolean status) 
            throws DvdStoreException;
}
