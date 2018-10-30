package com.ideas2it.dvdstore.controller;

import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.UserService;
import com.ideas2it.dvdstore.service.impl.UserServiceImpl;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;

/**
 * <p>
 * The {@code UserController} is used to perform the deferent action 
 * on user details by using the UserServiceImpl class
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
@Controller
public class UserController {
    
    private String LOGIN_JSP = "login";
    private String ADMIN_JSP = "adminMenu";
    private String CUSTOMER_MENU_JSP = "customerMenu";
    private String CUSTOMERCREATE_JSP = "customerCreate";
    private String ERROR_JSP = "error";
    private String LABEL_TODAY = "today";
    private String LABEL_ADMIN = "admin";
    
    private UserService userService;
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    /** Used to open the login page */
    @GetMapping("/")
    public ModelAndView viewLogin() {
        return new ModelAndView(LOGIN_JSP, "command", new User());
    }
      
    /** Is used to create the user in dvdstore*/
    @PostMapping("user/register")
    public ModelAndView createUser(@ModelAttribute("user")User user, 
            HttpServletRequest request) {     
        try {
            ModelAndView modelAndView = new ModelAndView(LOGIN_JSP, "command",
                new User());
            if (null == userService.searchName(user.getUserName())) {
                user.setRole(request.getParameter(Constants.LABEL_ROLE));
                if (userService.createUser(user)) {
                    return modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.REGISTER_SUCCESS_MESSAGE);
                } else {
                    return modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.REGISTER_FAIL_MESSAGE);
                }
            } else {
                return modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.USERNAME_ALREADY_EXIST);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, Constants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }  
    } 
    
    /**
     * Used to find the user in the dvd store
     */
    @PostMapping("user/login")
    public ModelAndView login(@ModelAttribute("user")User user, 
            HttpServletRequest request) {
        try {
            ModelAndView modelAndView = new ModelAndView(LOGIN_JSP, "command",
                new User());
            User oldUser = userService.searchUser(user.getUserName(), 
                user.getPassword(), user.getRole());
            if (null != oldUser) {
                    return setUserRole(oldUser, request);
            } else {
                return modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.INVALID_LOGIN);
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, Constants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }  
    }
    
    /**
     * Used to decide the user is admin or customer
     * 
     * @param user
     *       Used to get the user class referense
     */
    private ModelAndView setUserRole(User user, HttpServletRequest request) {
        try {
            String role = request.getParameter(Constants.LABEL_ROLE);
            HttpSession session = request.getSession(Boolean.TRUE);
            session.setMaxInactiveInterval(60); 
            session.setAttribute(Constants.LABEL_ROLE, role);
            if (role.equals(LABEL_ADMIN)) {
                session.setAttribute(Constants.LABEL_ID, user.getUserId());
                return new ModelAndView(ADMIN_JSP);
            } else { 
                Customer customer 
                    = userService.checkCustomerDetail(user.getUserId());
                if (null != customer) {
                    session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                    session.setAttribute(LABEL_TODAY, LocalDate.now().toString());
                    return new ModelAndView(CUSTOMER_MENU_JSP);
                } else {
                    ModelAndView modelAndView = new ModelAndView(
                        CUSTOMERCREATE_JSP, "command", new Customer());
                    return modelAndView.addObject(Constants.LABEL_ID, 
                        user.getUserId());
                }
            }
        } catch (DvdStoreException dvdException) {
            return new ModelAndView(ERROR_JSP, Constants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }
    }
    
    // Used to invalidate the session
    @GetMapping("user/logout")
    public ModelAndView logOut(HttpServletRequest request) {
        request.getSession(Boolean.FALSE).invalidate();
        return new ModelAndView(LOGIN_JSP, "command", new User());
    }
}
