package com.ideas2it.dvdstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;

/**
 * <p>
 * The {@code CategoryController} is used to perform the deferent action 
 * on dvd store by using the CategoryServiceImpl class
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    
    private String ADMIN_MENU_JSP = "adminMenu";
    private String CATEGORY_JSP = "category";
    private String CATEGORY_DISPLAY_JSP = "categoryDisplay";
    private String VIEWDVD_BYCATEGORY_JSP = "displayDvdbyCategory";
    private String RESTORE_CATEGORY_JSP = "restoreCategory";
    private String LABEL_DVDID = "dvdId";
    
    private CategoryService categoryService;
    
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    // Used to view the category menu
    @GetMapping(value ="/categoryMenu")
    public String viewCategoryMenu() {
        return CATEGORY_JSP;
    }
    
    // Used to view the admin menu
    @GetMapping(value ="/admin")
    public String viewAdminMenu() {
        return ADMIN_MENU_JSP;
    }
      
    /** 
     * The {@code displayCategory} is used to display the category
     * in tht dvd store
     */
    @PostMapping(value ="/display")
    public String displayCategory(ModelMap model) {
        try {
            model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                getCategory(Constants.LABEL_ACTIVE)); 
            return CATEGORY_DISPLAY_JSP;
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return CATEGORY_JSP;
        }
    }
    
    /** 
     * The {@code displayDVdbyCategory} is used to display the dvds 
     * in dvd store by category
     */
    @PostMapping(value ="/view")
    public String displayDvdbyCategory(HttpServletRequest request, 
            ModelMap model) {
        try {
            DvdCategory category = categoryService.
                searchCategoryById(Integer.parseInt(request.
                getParameter(Constants.LABEL_ID)));
            model.addAttribute(Constants.LABEL_CATEGORY, category);
            model.addAttribute(Constants.LABEL_DVDS, category.getDvds());
            return VIEWDVD_BYCATEGORY_JSP;
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return CATEGORY_JSP;
        }        
    }
    
    /**
     * Used to delete the category from the dvdstore
     */
    @PostMapping(value ="/delete")
    public String deleteCategory(HttpServletRequest request, ModelMap model) {
        try {
            if (categoryService.deleteCategory(Integer.
                    parseInt(request.getParameter(Constants.LABEL_ID)))) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.CATEGORY_DELETE_SUCCESS_MESSAGE);
                model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                    getCategory(Constants.LABEL_ACTIVE)); 
                return CATEGORY_DISPLAY_JSP;
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.CATEGORY_DELETE_FAIL_MESSAGE);
                model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                    getCategory(Constants.LABEL_ACTIVE)); 
                return CATEGORY_DISPLAY_JSP;
            }
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return CATEGORY_JSP;
        }         
    }
    
    /**
     * Used to remove the category from the dvd 
     */
    @PostMapping(value ="/remove")
    public String removeCategoryfromDvd(HttpServletRequest request, 
            ModelMap model) {
        try {
            if (categoryService.removeCategoryfromDvd(Integer.parseInt(request.
                getParameter(Constants.LABEL_ID)), Integer.parseInt(request.
                getParameter(LABEL_DVDID)))) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.CATEGORY_DELETE_SUCCESS_MESSAGE);
                DvdCategory category = categoryService.
                    searchCategoryById(Integer.parseInt(request.
                    getParameter(Constants.LABEL_ID)));
                model.addAttribute(Constants.LABEL_CATEGORY, category);
                model.addAttribute(Constants.LABEL_DVDS, category.getDvds());
                return VIEWDVD_BYCATEGORY_JSP;
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.CATEGORY_DELETE_FAIL_MESSAGE);
                DvdCategory category = categoryService.
                    searchCategoryById(Integer.parseInt(request.
                    getParameter(Constants.LABEL_ID)));
                model.addAttribute(Constants.LABEL_CATEGORY, category);
                model.addAttribute(Constants.LABEL_DVDS, category.getDvds());
                return VIEWDVD_BYCATEGORY_JSP;
            }
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return "error";
        }         
    }
    
    /**
     * Used to update the category in the dvdstore
     */
    @PostMapping(value ="/update")
    public String updateCategory(HttpServletRequest request, ModelMap model) {
        try {
            DvdCategory category = categoryService.
                searchCategoryById(Integer.parseInt(request.
                getParameter(Constants.LABEL_ID)));
            category.setCategory(request.getParameter(Constants.LABEL_CATEGORY));
            if (categoryIsExist(request, model, category.getCategory())) {
                if (categoryService.updateCategory(category)) {
                    model.addAttribute(Constants.LABEL_MESSAGE, Constants.
                        CATEGORY_UPDATE_SUCCESS_MESSAGE);
                    model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                        getCategory(Constants.LABEL_ACTIVE)); 
                    return CATEGORY_DISPLAY_JSP;
                } else {
                    model.addAttribute(Constants.LABEL_MESSAGE,
                        Constants.CATEGORY_UPDATE_FAIL_MESSAGE);
                    model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                        getCategory(Constants.LABEL_ACTIVE)); 
                    return CATEGORY_DISPLAY_JSP;
                }
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.UPDATE_ALERT_MESSAGE);
                model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                    getCategory(Constants.LABEL_ACTIVE)); 
                return CATEGORY_DISPLAY_JSP;
            }
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return CATEGORY_DISPLAY_JSP;
        }   
    }
    
    /**
     * Used to add the category in to the dvdstore
     */
    @PostMapping(value ="/create")
    public String createCategory(ModelMap model, HttpServletRequest request) {
        try {
            String category = request.getParameter(Constants.LABEL_CATEGORY);
            if (categoryIsExist(request, model, category)) {
                DvdCategory dvdCategory = new DvdCategory();
                dvdCategory.setCategory(category);
                dvdCategory.setStatus(Constants.LABEL_ACTIVE);
                if (categoryService.insertCategory(dvdCategory)) {
                    model.addAttribute(Constants.LABEL_MESSAGE, Constants.
                        ADD_CATEGORY_SUCCESS_MESSAGE);
                } else {
                    model.addAttribute(Constants.LABEL_MESSAGE,
                        Constants.ADD_CATEGORY_FAIL_MESSAGE);
                }
            }
            return CATEGORY_JSP;
        } catch (DvdStoreException dvdException) {
            return "error";
        }   
    }
    
    /**
     * Used to check the category already in the dvdstore
     */
    private Boolean categoryIsExist(HttpServletRequest request, ModelMap model,
            String category) throws DvdStoreException{
        if (null == categoryService.searchCategoryByName(category, 
                Constants.LABEL_INACTIVE)) {
            if (null == categoryService.searchCategoryByName(category, 
                    Constants.LABEL_ACTIVE)) {
                return Boolean.TRUE;
            } else  {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.ADD_CATEGORY_FAIL_MESSAGE);
            }
        } else {
            model.addAttribute(Constants.LABEL_MESSAGE, 
                Constants.UPDATE_ALERT_MESSAGE);
        }
        return Boolean.FALSE;
    }
    
    /** 
     * The {@code displayInactiveCategory} is used to display the inactive 
     * category in dvd store
     */
    @PostMapping(value ="/restoreForm")
    public String displayInactiveCategory(ModelMap model) {
        try {
            model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                getCategory(Constants.LABEL_INACTIVE)); 
            return RESTORE_CATEGORY_JSP;
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return CATEGORY_JSP;
        }
    }
    
    /**
     * Used to restore the category from the dvdstore
     */
    @PostMapping(value ="/restore")
    public String restoreCategory(HttpServletRequest request, ModelMap model) {
        try {
            if (categoryService.restoreCategory(Integer.parseInt(request.
                    getParameter(Constants.LABEL_ID)))) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.CATEGORY_RESTORE_SUCCESS_MESSAGE);
                model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                    getCategory(Constants.LABEL_INACTIVE)); 
                return RESTORE_CATEGORY_JSP;
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.CATEGORY_RESTORE_FAIL_MESSAGE);
                model.addAttribute(Constants.LABEL_CATEGORIES,categoryService.
                    getCategory(Constants.LABEL_INACTIVE)); 
                return RESTORE_CATEGORY_JSP;
            }
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE,
                dvdException.getMessage());
            return CATEGORY_JSP;
        }   
    }
}
