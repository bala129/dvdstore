package com.ideas2it.dvdstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;

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
 * @see  com.ideas2it.dvdstore.model.DvdCategory
 */
public interface CategoryDao {
    
    /**
     * The {@code fetchCategory} is used to get the
     * all category from the store
     */
    List<DvdCategory> fetchCategory(Boolean status) throws DvdStoreException;
    
    /**
     * <p>
     * The {@code insrtCategory} is used to insert the
     * category into the store
     * </p>
     *
     * @param category
     *       the category can used to get the category of the dvd   
     */
    Boolean insertCategory(DvdCategory category) throws DvdStoreException;
    
    /**
     * The {@code updateCategory} is used to update the category in dvdstore
     *
     * @param category
     *        Is used to get the reference of the DvdCategory class 
     */
    Boolean updateCategory(DvdCategory category) throws DvdStoreException;
   
    /**
     * <p>
     * Used to remove the category from dvd store 
     * </p>
     *
     * @param id
     *       It's used to get category 
     */
    Boolean removeCategory(Integer id) throws DvdStoreException;
    
    
    /**
     * Used to restore the removed category from dvdstore
     *
     * @param id
     *       it's used to find the dvd in dvdstore  
     */
    Boolean restoreCategory(Integer id) throws DvdStoreException;
    
    /**
     * <p>
     * Used to validate the category 
     * It's means check the category is there in the dvd detail  
     * </p>
     *
     * @param choice
     *       the choice can used to get category id 
     */
    DvdCategory checkChoice(Integer choice, Boolean status) 
            throws DvdStoreException;
    /**
     * <p>
     * The {@code searchCategoryByName} is used to find the 
     * category from the dvdstore
     * </p>
     *
     * @param category, status
     *       Used to get the DvdCategory object  
     */
    DvdCategory searchCategoryByName(String category, Boolean status) 
            throws DvdStoreException;
    
    /**
     * <p>
     * The {@code searchCategoryById} is used to find the 
     * category from the dvdstore
     * </p>
     *
     * @param id
     *       the id can used to get the DvdCategory object   
     */
    DvdCategory searchCategoryById(Integer id)
            throws DvdStoreException;
    
}
