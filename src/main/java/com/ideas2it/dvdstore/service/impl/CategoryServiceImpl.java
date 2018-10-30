package com.ideas2it.dvdstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;  

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.impl.DvdStoreServiceImpl;

/**
 * <p>
 * The {@code CategoryServiceImpl} is represents the collection of 
 * the DVD service functions
 * This act the intermediate between the controller class and model
 * </p>
 * <p>
 * Here we perform the create the dvd, update the dvd, 
 * delete the dvd on dvd store 
 * And display the display the dvds in the dvd store  
 * </p>
 *
 * @version 1
 * @author Balamurugan
 * @see com.ideas2it.dvdstore.dao.CategoryDao;
 * @see com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl;
 * @see com.ideas2it.dvdstore.model.Dvd
 * @see com.ideas2it.dvdstore.service.CategoryService;
 */
public class CategoryServiceImpl implements CategoryService {
    
    private CategoryDao categoryDao;
    
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    
    
    /** {@inheritDoc}*/
    public List<DvdCategory> getCategory(Boolean status)
            throws DvdStoreException {
        return categoryDao.fetchCategory(status);
    }
    
    /** {@inheritDoc}*/    
    public Integer checkCategory(Integer choice, 
            List<DvdCategory> categories) throws DvdStoreException {
        DvdCategory dvdCategory = categoryDao.checkChoice(choice, 
            Constants.LABEL_ACTIVE);
        if (null != dvdCategory) {
            if (categories.contains(dvdCategory)) {
                return 0;
            } else {
                categories.add(dvdCategory);
                return 1;
            }
        }
        return 2;
    }
    
    /** {@inheritDoc}*/
    public Integer validateDetail(String detail) throws DvdStoreException {
        if (Pattern.matches("[a-zA-Z]+", detail)) {
            for (DvdCategory dvdCategory : 
                    categoryDao.fetchCategory(Constants.LABEL_ACTIVE)) {
                if (dvdCategory.getCategory().equals(detail)) {
                    return 1; 
                }
            }
            return 0;
        } else {
            return 2;
        }
    }
    
    /** {@inheritDoc}*/
    public DvdCategory validateChoice(Integer choice) 
            throws DvdStoreException {
        return categoryDao.checkChoice(choice, Constants.LABEL_ACTIVE);
    }
    
    /** {@inheritDoc}*/
    public Boolean checkLength(String detail) {
       return (detail.length() <= 50);
    }
    
    /** {@inheritDoc}*/
    public Boolean restoreCategory(Integer category_Id) throws DvdStoreException {
        return categoryDao.restoreCategory(category_Id);
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteCategory(Integer category_Id)
            throws DvdStoreException {
        return categoryDao.removeCategory(category_Id);
    }
    
    /** {@inheritDoc}*/
    public Boolean updateCategory(DvdCategory category) throws DvdStoreException {
        return categoryDao.updateCategory(category);
    }
    
    /** {@inheritDoc}*/
    public Boolean insertCategory(DvdCategory category)
            throws DvdStoreException {
        return categoryDao.insertCategory(category);
    }
    
    /** {@inheritDoc}*/    
    public DvdCategory searchCategoryById(Integer id)
            throws DvdStoreException{
        return categoryDao.searchCategoryById(id);
    }
    
    /** {@inheritDoc}*/
    public DvdCategory searchCategoryByName(String category, Boolean status) 
            throws DvdStoreException {
        return categoryDao.searchCategoryByName(category, status);
    }
    
    /** {@inheritDoc}*/
    public Boolean removeCategoryfromDvd(Integer category_Id, Integer dvdId) 
            throws DvdStoreException {
        DvdCategory category = categoryDao.searchCategoryById(category_Id);
        List<Dvd> dvds = category.getDvds();
        for (Dvd dvd : dvds) {
            if (dvd.getDvdId() == dvdId) {
            dvds.remove(dvd);
            break;
            }
        }
        return categoryDao.updateCategory(category);
    }
}
