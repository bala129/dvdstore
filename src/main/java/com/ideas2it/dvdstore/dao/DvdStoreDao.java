package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * The {@code DvdStoreDao} is used to represents the declarations of  
 * different action on the dvd store 
 * </p>
 * <p> 
 * Here we declare the functions of adding the dvd to the dvd store 
 * Removing the dvd from the dvd store
 * display the dvds and find the dvd from the dvd store
 * </p>
 * 
 * @version 1
 * @author Balamurugan
 * @see  com.ideas2it.dvdstore.model.Dvd 
 */
public interface DvdStoreDao {
    
    /**
     * The {@code addDvd} is used to add the dvd to dvd store
     *
     * @param dvd
     *        dvd is used to get the reference of the dvd class 
     */
    Boolean addDvd(Dvd dvd) throws DvdStoreException;
    
    /**
     * Used to restore the removed dvd from dvdstore
     *
     * @param dvdName and category, language
     *       it's used to find the dvd in dvdstore  
     */
    Boolean restoreDvd(Integer dvdId) throws DvdStoreException;
    
    /**
     * The {@code updateDvd} is used to update the dvd details to dvd
     *
     * @param dvd
     *        dvd is used to get the reference of the dvd class 
     */
    Boolean updateDvd(Dvd dvd) throws DvdStoreException;
    
    /**
     * The {@code displayDvd} is used to print the
     * all DVD in the store
     */
    List<Dvd> displayDvd(Boolean status) throws DvdStoreException;    
 
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
     * The {@code searchDvd} is used to find required dvd from dvdstore
     * </p>
     *
     * @param dvdName and dvdType
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
