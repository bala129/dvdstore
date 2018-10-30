package com.ideas2it.dvdstore.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * The {@code CategoryService} is represents the collection of 
 * the category service functions declarations
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
public interface CategoryService {

    /**
     * Used to get the categories from user
     */
    List<DvdCategory> getCategory(Boolean status) throws DvdStoreException;
    
    /**
     * <p>
     * Used to insert the categories in to category table
     * </p>
     *
     * @param category
     *       the category can used to get the category of the dvd   
     */
    Boolean insertCategory(DvdCategory category) throws DvdStoreException;
    
    /**
     * Used to restore the removed category from dvdstore
     *
     * @param category_Id
     *       it's used to find the dvd in dvdstore  
     */
    Boolean restoreCategory(Integer category_Id) throws DvdStoreException;
    
    /**
     * <p>
     * Here check the detail is valid 
     * It's means the deatail is empty or not
     * </p>
     * 
     * @param detail
     *        the detail is contain the detail of the DVD
     */ 
    Integer validateDetail(String detail) throws DvdStoreException;
    
    /**
     * <p>
     * Used to delete the category from dvd store 
     * </p>
     *
     * @param category_Id
     *       It's used to get category 
     */
    Boolean deleteCategory(Integer category_Id) throws DvdStoreException;
    
    /**
     * <p>
     * Used to delete the category from dvd store 
     * </p>
     *
     * @param category
     *       the category can used to get the category of the dvd   
     */
    Boolean updateCategory(DvdCategory category) throws DvdStoreException;
    /**
     * <p>
     * Here check the choice is valid 
     * It's means the choice is matched or not
     * </p>
     * 
     * @param choice
     *        the choice is used to get the choice
     */ 
    DvdCategory validateChoice(Integer choice) throws DvdStoreException;

    /**
     * <p>
     * Here check the length of the DVD details
     * </p>
     * 
     * @param detail
     *        the detail is contain the detail of the DVD 
     */
    Boolean checkLength(String detail);
    
    /**
     * <p>
     * Used to validate the category
     * It's means the category is already there in the dvd detail  
     * </p>
     *
     * @param choice
     *       the choice can used to get category id 
     */
    Integer checkCategory(Integer choice, 
            List<DvdCategory> categories) throws DvdStoreException;
    
    /**
     * <p>
     * Used to find the category from dvd store
     * </p>
     *
     * @param id
     *       the id can used to get category id 
     */
    DvdCategory searchCategoryById(Integer id) throws DvdStoreException;
    
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
     * Used to remove the category from dvd 
     * </p>
     *
     * @param category_Id, dvdId
     *       It's used to find the particular dvd and category
     */
    Boolean removeCategoryfromDvd(Integer category_Id, Integer dvdId) 
            throws DvdStoreException;
}
