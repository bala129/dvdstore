package com.ideas2it.dvdstore.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.DvdStoreDao;
import com.ideas2it.dvdstore.dao.impl.DvdStoreDaoImpl;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.DvdStoreService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.utils.DateUtils;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;

/**
 * <p>
 * The {@code DvdStoreServiceImpl} is represents the collection of 
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
 * @see com.ideas2it.dvdstore.dao.DvdStoreDao;
 * @see com.ideas2it.dvdstore.dao.impl.DvdStoreDaoImpl;
 * @see com.ideas2it.dvdstore.model.Dvd
 * @see com.ideas2it.dvdstore.service.DvdStoreService;
 */
public class DvdStoreServiceImpl implements DvdStoreService {
   
    private DvdStoreDao dvdDao;
    private CategoryService categoryService;
    
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    public void setDvdDao(DvdStoreDao dvdDao) {
        this.dvdDao = dvdDao;
    }
    
    /** {@inheritDoc}*/
    public Boolean validatePrice(Integer price) {       
       return  ((Constants.MINIMUM_PRICE < price) 
               && (Constants.MAXIMUM_PRICE >= price));
    }
     
    /** {@inheritDoc}*/
    public Boolean validateRating( Double rating) {
       return  ((Constants.MINIMUM_RATING < rating) 
               && (Constants.MAXIMUM_RATING >= rating));
    }
    
    /** {@inheritDoc}*/
    public Dvd searchDvdById(Integer choice, Boolean status) 
            throws DvdStoreException {
        return dvdDao.searchDvdById(choice, status);
    }
    
    /** {@inheritDoc}*/
    public List<Dvd> displayDvd(Boolean status) throws DvdStoreException {
        return dvdDao.displayDvd(status);
    }
     
    /** {@inheritDoc}*/
    public Boolean restoreDvd(Integer dvdId) throws DvdStoreException {
        return dvdDao.restoreDvd(dvdId);
    }
    
    /** {@inheritDoc}*/
    public Boolean checkLength(String detail) {
        return (50 >= detail.length());
    }
    
    /** {@inheritDoc}*/
    public Boolean createDvd(Dvd dvd) throws DvdStoreException {
        return dvdDao.addDvd(dvd);
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteDvd(Dvd dvd) throws DvdStoreException {
        return dvdDao.deleteDvd(dvd);
    }
    
    /** {@inheritDoc}*/
    public List<DvdCategory> getCategory() throws DvdStoreException {
        return categoryService.getCategory(Constants.LABEL_ACTIVE);
    }
    
    /** {@inheritDoc}*/
    public Integer checkCategory(Integer choice, 
            List<DvdCategory> categories) throws DvdStoreException {
        return categoryService.checkCategory(choice, categories);
    }
    
    /** {@inheritDoc}*/
    public Boolean updateDvd(Dvd dvd) throws DvdStoreException {
        return dvdDao.updateDvd(dvd);
    }

    /** {@inheritDoc}*/
    public Dvd searchDvd(String dvdName, String dvdType, String language, 
            Boolean status) throws DvdStoreException {
        return dvdDao.searchDvd(dvdName, dvdType, language, status);
    }
    
    /** {@inheritDoc}*/
    public List<Dvd> searchDvdByDetail(String detail, Boolean status) 
            throws DvdStoreException {
        return dvdDao.searchDvdByDetail(detail, status);
    }
   
    /** {@inheritDoc}*/
    public DvdCategory searchDvdByCategory(Integer id) throws DvdStoreException {
        return categoryService.validateChoice(id);
    }
}
