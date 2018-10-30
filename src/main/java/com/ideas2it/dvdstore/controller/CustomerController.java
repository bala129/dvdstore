package com.ideas2it.dvdstore.controller;

import java.sql.Date;
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
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.CustomerOrder;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.DvdCategory;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdstore.utils.DateUtils;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;


/**
 * <p>
 * The {@code customerController} is used to perform the deferent action 
 * on dvd store by using the CustomerServiceImpl class
 * </p>
 * <p>
 * Here we perform the create, update, delete, display functions  
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    private String CUSTOMERCREATE_JSP = "customerCreate";
    private String UPDATEADDRESS_JSP = "updateAddress";
    private String CUSTOMER_MENU_JSP = "customerMenu";
    private String ADMIN_MENU_JSP = "adminMenu";
    private String LOGIN_JSP = "login";
    private String VIEWCUSTOMER_JSP = "viewCustomer";
    private String DISPLAYCUSTOMER_JSP = "displayCustomer";
    private String PURCHASE_JSP = "purchaseDvd";
    private String ERROR_JSP = "error";
    private String CUSTOMERS = "customers";
    
    private CustomerService customerService;
    
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    // Used to view address create page
    @GetMapping(value ="/addAddress")
    public ModelAndView createaddress() {
        return new ModelAndView(UPDATEADDRESS_JSP, 
            "command", new Address());
    }
    
    // Used to view customer menu page
    @GetMapping(value ="/customerMenu")
    public String viewCustomerMenu() {
        return CUSTOMER_MENU_JSP;
    }
    
    // Used to view admin menu page
    @GetMapping(value ="/adminMenu")
    public String viewAdminMenu() {
        return ADMIN_MENU_JSP;
    }
    
    // Used to view customer edit page
    @GetMapping(value ="/editProfile")
    public ModelAndView modifyCustomer(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);  
            Customer oldCustomer 
                = (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);
        return new ModelAndView(CUSTOMERCREATE_JSP, 
            "command", oldCustomer);
    }
    
    // Used to view address edit page
    @PostMapping(value ="/editAddressForm")
    public ModelAndView modifyAddress(HttpServletRequest request) {
        Integer addressId 
            = Integer.parseInt(request.getParameter(Constants.LABEL_ADDRESSID));
        ModelAndView modelAndView = new ModelAndView(UPDATEADDRESS_JSP, 
            "command", new Address());
        return modelAndView.addObject("address", getAddress(request, addressId));
    }
        
    /**
     * Is used to display the all customer in the dvdstore
     */
    @GetMapping(value ="/viewCustomers-admin")
    public ModelAndView displayCustomer(HttpServletRequest request) { 
        try {
            return new ModelAndView(VIEWCUSTOMER_JSP, 
                CUSTOMERS, customerService.displayCustomer());
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }   
    }
    
    /**
     * Used to display the particular customer details
     */
    @PostMapping(value ="/viewDetail-admin")
    public ModelAndView viewCustomer(HttpServletRequest request) { 
        try {
            Integer id = Integer.parseInt(
                request.getParameter(Constants.LABEL_CUSTOMER_ID));
            return new ModelAndView(DISPLAYCUSTOMER_JSP, 
                Constants.LABEL_CUSTOMER, customerService.searchCustomer(id));
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        } 
    }
    
    /**
     * <p>
     * The {@code createCustomer} is used to get the
     * customer details from user and store into the DVD store
     * </p>
     */
    @PostMapping(value ="/createCustomer")
    public ModelAndView registerCustomer(HttpServletRequest request, 
            @ModelAttribute("customer")Customer customer) {     
        try {
            if (customerService.createCustomer(customer)) {
                return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                    LABEL_MESSAGE, Constants.REGISTER_SUCCESS_MESSAGE);
            } else {   
                return new ModelAndView(CUSTOMERCREATE_JSP, 
                    Constants.LABEL_MESSAGE, Constants.REGISTER_FAIL_MESSAGE);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }    
    }
    
    /** Is used to display the dvds in the dvd store*/
    @GetMapping(value ="/purchase")
    public ModelAndView showDvd(HttpServletRequest request) {
        try {
            ModelAndView modelAndView = new ModelAndView(PURCHASE_JSP, 
                Constants.LABEL_DVDS, customerService.displayAllDvd());
            modelAndView.addObject(Constants.LABEL_CATEGORIES, 
                customerService.getCategory(Constants.LABEL_ACTIVE));
            return modelAndView;
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    }
    
    /**
     * <p>
     * The {@code updateCustomer} is used to update the 
     * customer details in dvd store
     * </p>
     */
    @PostMapping(value ="/updateCustomer")
    public ModelAndView updateCustomer(HttpServletRequest request, 
            @ModelAttribute("customer")Customer customer) {     
        try {
            HttpSession session = request.getSession(Boolean.FALSE);  
            Customer oldCustomer 
                = (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);
            customer.setOrders(oldCustomer.getOrders());
            customer.setAddress(oldCustomer.getAddress());
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_CUSTOMER, 
                    customerService.searchCustomer(oldCustomer.getCustomerId()));
                return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                    LABEL_MESSAGE, Constants.CUSTOMER_UPDATE_MESSAGE);
            } else {   
                return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                    LABEL_MESSAGE, Constants.CUSTOMER_UPDATE_FAIL_MESSAGE);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
              
        }    
    }
    
    /** 
     * <p>
     * The {@code getAddress} is used to get the customer address  
     * </p>
     */
    @GetMapping(value ="/saveAddress")
    public ModelAndView addAddress(@ModelAttribute("address")Address address,
            HttpServletRequest request) {   
        HttpSession session = request.getSession(Boolean.FALSE);  
        Customer customer = 
            (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);
        address.setCustomerId(customer.getCustomerId());
        try {
            if (customerService.addAddress(address)) {
                customer.getAddress().add(address);
                session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.ADDRESS_ADD_SUCCESS);
                return new ModelAndView("redirect:"+CUSTOMER_MENU_JSP);
            } else {   
                return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                    LABEL_MESSAGE, Constants.ADDRESS_ADD_FAIL);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }    
    }
    
    /**
     * Is used to update the customr address details 
     */
    @GetMapping(value ="/editAddress")
    public ModelAndView updateAddress(@ModelAttribute("address")Address address,
            HttpServletRequest request) {     
        HttpSession session=request.getSession(Boolean.FALSE);  
        Customer customer 
            = (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);  
        Integer addressId 
            = Integer.parseInt(request.getParameter(Constants.LABEL_ADDRESSID));
        customer.getAddress().remove( getAddress(request, addressId));
        customer.getAddress().add(address);
        try {
            if (customerService.updateCustomer(customer)) {
                return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                    LABEL_MESSAGE, Constants.ADDRESS_UPDATE_SUCCESS);
            } else {   
                return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                    LABEL_MESSAGE, Constants.ADDRESS_UPDATE_FAIL);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    }
    
    /**
     * Is used to update the customr address details 
     */
    @PostMapping(value ="/removeAddress")
    public ModelAndView removeAddress(HttpServletRequest request) {     
        HttpSession session=request.getSession(Boolean.FALSE);  
        Customer customer = 
             (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);  
        if (1 < customer.getAddress().size()) {
            customer.getAddress().remove(getAddress(request, Integer.parseInt(
                request.getParameter(Constants.LABEL_ADDRESSID))));
            try {
                if (customerService.updateCustomer(customer)) {
                    return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                        LABEL_MESSAGE, Constants.ADDRESS_REMOVE_SUCCESS);
                }
            } catch (DvdStoreException dvdException) {
                return new ModelAndView(ERROR_JSP, 
                    Constants.LABEL_MESSAGE, dvdException.getMessage());
            }
        }
        return new ModelAndView(CUSTOMER_MENU_JSP, 
            Constants.LABEL_MESSAGE, Constants.ADDRESS_REMOVE_FAIL);
    }
    
    /**
     * <p>
     * Is used to purchase the dvd from the dvd store
     * </p>
     */
    @GetMapping(value ="/purchaseDvd")
    public ModelAndView purchaseDvd(HttpServletRequest request) {     
        HttpSession session = request.getSession(Boolean.FALSE);  
        Customer customer = 
            (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);  
        try {
            if (customerService.purchaseDvd(getOrder(customer, request))) {
                customer.setOrders(customerService.displayOrder(customer));
                session.setAttribute(Constants.LABEL_MESSAGE, 
                    Constants.PURCHASE_SUCCESS_MESSAGE);
                return new ModelAndView("redirect:"+CUSTOMER_MENU_JSP);
            }
            return new ModelAndView(CUSTOMER_MENU_JSP, Constants.LABEL_MESSAGE, 
                Constants.PURCHASE_FAIL_MESSAGE);
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }
    }
    
    /**
     * <p>
     * Is used to get the orders from customer
     * </p>
     */
    private List<CustomerOrder> getOrder(Customer customer, 
            HttpServletRequest request) {     
        List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
        Address address = new Address();
        address.setAddressId(Integer.parseInt(request.
            getParameter(Constants.LABEL_ADDRESSID)));
        for (String dvdId : request.getParameterValues("dvdIds")) {
            Dvd dvd = new Dvd();
            dvd.setDvdId(Integer.parseInt(dvdId));
            CustomerOrder order = new CustomerOrder(customer.getCustomerId(),
            Date.valueOf(LocalDate.now()));
            order.setDvd(dvd);
            order.setAddress(address);
            orders.add(order);
        }
        return orders;
    }
    
    /**
     * Is used to remove the order from customer details 
     */
    @PostMapping(value ="/removeOrder")
    public ModelAndView removeOrder(HttpServletRequest request) {     
        try {
            HttpSession session = request.getSession(Boolean.FALSE);  
            Customer customer = 
               (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);  
            if (DateUtils.checkDateEqual(request.getParameter("orderDate"))) {
                
                if (customerService.removeOrder(Integer.parseInt(request.
                        getParameter("orderId")))) {
                    customer.setOrders(customerService.displayOrder(customer));
                    return new ModelAndView(CUSTOMER_MENU_JSP, Constants.
                        LABEL_MESSAGE, Constants.ORDER_REMOVE_SUCCESS);
                }
            } 
            return new ModelAndView(CUSTOMER_MENU_JSP, Constants.LABEL_MESSAGE,
                Constants.ORDER_REMOVE_FAIL);
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }
    }
    
    
    /** Is used to display the dvds in the dvd store by dvd name*/
    @GetMapping(value ="/searchDvd")
    public ModelAndView searchDvd(HttpServletRequest request) {     
        try {
            List<Dvd> dvds = customerService.displayDvdByInfo(request.
                getParameter("detail"));
            ModelAndView modelAndview = new ModelAndView(PURCHASE_JSP,
                Constants.LABEL_CATEGORIES, customerService.
                getCategory(Constants.LABEL_ACTIVE));
            if (!dvds.isEmpty()) {
                return modelAndview.addObject(Constants.LABEL_DVDS, dvds); 
            } else {
                modelAndview.addObject(Constants.LABEL_DVDS, 
                    customerService.displayAllDvd()); 
                return modelAndview.addObject(Constants.LABEL_MESSAGE, 
                    Constants.FIND_ERROR_MESSAGE); 
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }  
    }
    
    /** 
     * The {@code displayDVdbyCategory} is used to display the dvds 
     * in dvd store by category
     */
    @GetMapping(value ="/displayDvdByCategory")
    public ModelAndView displayDvdbyCategory(HttpServletRequest request) {
        try {
            DvdCategory category = customerService.displayDvdbyCategory(Integer.
                parseInt(request.getParameter(Constants.LABEL_CATEGORY)));
            ModelAndView modelAndview = new ModelAndView(PURCHASE_JSP, 
                Constants.LABEL_CATEGORIES, customerService.
                getCategory(Constants.LABEL_ACTIVE));
            modelAndview.addObject(Constants.LABEL_ID, category.getId());
            return modelAndview.addObject(Constants.LABEL_DVDS, 
                category.getDvds());
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, 
                Constants.LABEL_MESSAGE, dvdException.getMessage());
        }        
    }
    
    /**
     * Is used to get the Address class object
     *
     * @param Id
     *       used to get the address id
     */
    private Address getAddress(HttpServletRequest request, Integer id) {
        HttpSession session=request.getSession(Boolean.FALSE);  
        Customer customer = 
            (Customer)session.getAttribute(Constants.LABEL_CUSTOMER);
        for (Address address : customer.getAddress()) {
            if (address.getAddressId() == id) {
                return address;
            }
        }
        return null;
    }
}
