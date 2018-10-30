package com.ideas2it.dvdstore.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.ideas2it.dvdstore.service.DvdStoreService;
import com.ideas2it.dvdstore.service.impl.DvdStoreServiceImpl;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;

/**
 * <p>
 * The {@code DvdStoreController} is used to perform the deferent action 
 * on dvd store by using the DvdStoreServiceImpl class
 * </p>
 * <p>
 * Here we perform the create the dvd, update the dvd, 
 * delete the dvd on dvd store via the service class
 * And display the dvds in the dvd store  
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
@Controller
@RequestMapping("/dvd")
public class DvdController {
    
    private String DVDCREATE_JSP = "dvdCreate";
    private String DVD_JSP = "dvd";
    private String SEARCHDVD_JSP = "searchDvd";
    private String DVD_DISPLAY_JSP = "dvdDisplay";
    private String RESTORE_JSP = "restoreDvd";
    private String ADMIN_MENU_JSP = "adminMenu";
    private String LABEL_DVD = "dvd";
    private String LABEL_TODAY = "today";
    private String ERROR_JSP = "error";
    
    private DvdStoreService dvdService;

    public void setDvdService(DvdStoreService dvdService) {
        this.dvdService = dvdService;
    }
    
    // Used to view the admin menu
    @GetMapping(value ="/admin")
    public String viewAdminMenu() {
        return ADMIN_MENU_JSP;
    }
    
    // Used to view the dvd menu
    @GetMapping(value ="/dvdMenu")
    public String viewDvdMenu() {
        return DVD_JSP;
    }
    
    // Used to view the dvd create page with dvd reference
    @GetMapping(value ="/createForm")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView(DVDCREATE_JSP, 
            "command", new Dvd());
        try {
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                dvdService.getCategory());
            modelAndView.addObject(LABEL_TODAY, LocalDate.now());
            return modelAndView;
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(DVD_JSP, Constants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }  
    }
    
    // Used to view the dvd update page with dvd object and category list
    @PostMapping(value ="/updateForm")
    public ModelAndView updateForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(DVDCREATE_JSP, 
            "command", new Dvd());
        try {
            Dvd dvd = dvdService.searchDvdById(Integer.
                parseInt(request.getParameter(Constants.LABEL_ID)), 
                Constants.LABEL_ACTIVE);
            modelAndView.addObject(Constants.LABEL_CATEGORIES,
                dvdService.getCategory());
            modelAndView.addObject(LABEL_TODAY, LocalDate.now()); 
            return modelAndView.addObject(LABEL_DVD, dvd);
        }  catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }
    }
    
    /** Is used to display the all dvds in the dvd store */
    @GetMapping(value ="/display")
    public ModelAndView display(ModelMap model) {     
        try {
            ModelAndView modelAndView = new ModelAndView(DVD_DISPLAY_JSP, 
                Constants.LABEL_CATEGORIES, dvdService.getCategory());
            return modelAndView.addObject(Constants.LABEL_DVDS,
                dvdService.displayDvd(Constants.LABEL_ACTIVE)); 
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    } 
    
    /** Is used to find the dvd in the dvdstore */
    @GetMapping(value ="/search")
    public ModelAndView searchDvd(HttpServletRequest request, ModelMap model) {     
        try {
            ModelAndView modelAndView = new ModelAndView(DVD_DISPLAY_JSP, 
                Constants.LABEL_CATEGORIES, dvdService.getCategory());
            return modelAndView.addObject(Constants.LABEL_DVDS,
                dvdService.searchDvdByDetail(request.
                getParameter("detail"), Constants.LABEL_ACTIVE)); 
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    } 
    
    /** 
     * <p>
     * The {@code getCategory} is used to get the category of dvd  
     * </p>
     */
    private List<DvdCategory> getCategory(HttpServletRequest request) 
             throws DvdStoreException {  
        List<DvdCategory> categories = new ArrayList<DvdCategory>();
        for (DvdCategory dvdcategory : dvdService.getCategory()) {
            for (String category : request.getParameterValues(
                    Constants.LABEL_CATEGORY)) {
                if (dvdcategory.getCategory().equals(category)) {
                    categories.add(dvdcategory);
                }
            }
        }
        return categories;
    }
    
    /**
     * <p>
     * The {@code createDvd} is used to get the
     * DVD details from user and store into the DVD store
     * </p>
     */
    @PostMapping(value ="/create")
    public String createDvd(@ModelAttribute("dvd")Dvd dvd, ModelMap model, 
            HttpServletRequest request) {     
        try {
            if (dvdisExist(dvd, model)) {
                dvd.setCategories(getCategory(request));
                dvd.setDvdType(request.getParameter(Constants.LABEL_TYPE));
                dvd.setStatus(Boolean.TRUE);
                if (dvdService.createDvd(dvd)) {
                    model.addAttribute(Constants.LABEL_MESSAGE, 
                        Constants.ADD_MESSAGE);
                }
            }
            return DVD_JSP;
        } catch (DvdStoreException dvdException) {
            model.addAttribute(Constants.LABEL_MESSAGE, 
                dvdException.getMessage());
            return ERROR_JSP;
        }  
    }
    
    /**
     * <p>
     * Is used to check the dvd is already presence in the dvd store
     * </p>
     *
     * @param dvd
     *        getting the object of the Dvd class  
     */
    private Boolean dvdisExist(Dvd dvd, ModelMap model) throws DvdStoreException { 
        List<Dvd> dvds = new ArrayList<Dvd>();
        Dvd oldDvd = dvdService.searchDvd(dvd.getDvdName(), 
            dvd.getDvdType(), dvd.getLanguage(), Constants.LABEL_INACTIVE);
        if (null == oldDvd) {
            if (null == dvdService.searchDvd(dvd.getDvdName(), 
                    dvd.getDvdType(), dvd.getLanguage(), Constants.LABEL_ACTIVE)) {
                return Boolean.TRUE;
            } else {   
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.ADD_FAIL_MESSAGE);
                return Boolean.FALSE;
            }
        } else {
            model.addAttribute(Constants.LABEL_MESSAGE, 
                Constants.RESTORE_MESSAGE);
            return Boolean.FALSE;
        }
    }
    
    /**
     * <p>
     * The{@code deleteDvd} is used to delete the 
     * dvd in dvd store
     * </p>
     *
     */
    @PostMapping(value ="/delete")
    public ModelAndView deleteDvd(HttpServletRequest request, ModelMap model) {
        try {
            Dvd dvd = dvdService.searchDvdById(
                Integer.parseInt(request.getParameter(Constants.LABEL_ID)), 
                Constants.LABEL_ACTIVE);
            if (dvdService.deleteDvd(dvd)) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.DELETE_MESSAGE);
                return display(model);
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.DELETE_FAIL_MESSAGE);
                return display(model);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        } 
    }
    
    /**
     * <p>
     * Is used to update the dvd details 
     * the in dvd store
     * </p>
     */
    @PostMapping(value ="/update")
    public ModelAndView updateDvd(@ModelAttribute("dvd")Dvd dvd, ModelMap model, 
            HttpServletRequest request) {
        try {
            dvd.setStatus(Boolean.TRUE);
            dvd.setDvdId(Integer.parseInt(request.
                 getParameter(Constants.LABEL_ID)));
            dvd.setCategories(getCategory(request));
            dvd.setDvdType(request.getParameter(Constants.LABEL_TYPE));
            if (dvdService.updateDvd(dvd)) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.UPDATE_SUCCESS_MESSAGE);
                return display(model);
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.UPDATE_FAIL_MESSAGE);
                return display(model);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        } 
    }
    
    /** Is used to display the inactive dvds in the dvd store*/
    @GetMapping(value ="/showInactiveDvd")
    public ModelAndView showInactiveDvd(ModelMap model) {     
        try {
            return new ModelAndView(RESTORE_JSP, Constants.LABEL_DVDS,
                dvdService.displayDvd(Constants.LABEL_INACTIVE));
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    }
    
    /**
     * Used to restore the restored the dvds from dvdstore
     */
    @PostMapping(value ="/restore")
    public ModelAndView restoreDvd(ModelMap model, HttpServletRequest request) {     
        try {
            if (dvdService.restoreDvd(Integer.
                    parseInt(request.getParameter(Constants.LABEL_ID)))) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.RESTORE_SUCCESS_MESSAGE);
                return showInactiveDvd(model);
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.FIND_ERROR_MESSAGE);
                return showInactiveDvd(model);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    }
    
    /** Is used to display the dvds by category from the dvd store */
    @PostMapping(value ="/showDvdByCategory")
    public ModelAndView showDvdByCategory(HttpServletRequest request) {     
        try {
            Integer id = Integer.parseInt(request.
                getParameter(Constants.LABEL_CATEGORY));
            ModelAndView modelAndView = new ModelAndView(DVD_DISPLAY_JSP, 
                Constants.LABEL_CATEGORIES, dvdService.getCategory());
            modelAndView.addObject(Constants.LABEL_ID, id);
            return modelAndView.addObject(Constants.LABEL_DVDS,
                (dvdService.searchDvdByCategory(id)).getDvds()); 
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    }
}
