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
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;
import com.ideas2it.dvdstore.utils.DvdStoreUtils;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;


/**
 * <p>
 * The {@code OrderController} is used to perform the deferent action 
 * on dvd store by using the OrderServiceImpl class
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    
    private String ADMIN_MENU_JSP = "adminMenu";
    private String VIEWORDERS_JSP = "viewOrders";
    private String ERROR_JSP = "error";
    private DvdStoreUtils dvdStoreUtil = new DvdStoreUtils();

    private OrderService orderService;
    
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    // Used to view the admin page
    @GetMapping(value ="/admin")
    public String viewAdminMenu() {
        return ADMIN_MENU_JSP;
    }
    
    /**
     * Is used to display the all customer in the dvdstore
     */
    @GetMapping(value ="/viewOrders")
    public ModelAndView displayAllOrder() { 
        try {
            return new ModelAndView(VIEWORDERS_JSP, 
                "orders", orderService.displayAllOrder());
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, Constants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }  
    }
}
