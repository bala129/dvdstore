package com.ideas2it.dvdstore.filter; 

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.Customer;

/**
 * <p>
 * Is used to prevent the unauthorized access in 
 * the admin page and customer page 
 * And also handle the login activities user using session
 * </p>
 *
 * @version 1
 * @author Balamurugan M
 */
public class DvdStoreFilter implements Filter {  
    
    private String DVDURI =  "/dvdstore/dvd";
    private String CATEGORYURI = "/dvdstore/category";
    private String CUSTOMERURI = "/dvdstore/customer";
    private String ORDERURI = "/dvdstore/order";
    private String DVDSTOREURI = "/dvdstore/";
    private String USERURI = "/dvdstore/user/";
    private String LOGIN = "login";
    private String ADMIN = "-admin";
    private String IMAGE = "/dvdstore/resources/";
    private String ADMIN_MENU_JSP = "/dvdstore/dvd/admin";
    private String CUSTOMER_MENU_JSP = "/dvdstore/customer/customerMenu";
    private String LOGIN_JSP = "/";
    
    /** Used to initializing the filter */
    public void init(FilterConfig config) throws ServletException {
    
    }
    
    /**
     * Is used to asign the access authentication to user
     * It's means user access to admin page or customer page
     */
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(Boolean.FALSE);  
        
        httpResponse.setHeader("Cache-Control", "no-cache no-store");
        httpResponse.setHeader("Pragma","no-cache"); //HTTP 1.0 implementation
        
        Boolean admin = Boolean.FALSE;
        Boolean customer = Boolean.FALSE;
        String role = null;
        
        if (null != session) {
            role = (String)session.getAttribute(Constants.LABEL_ROLE);
            if (null != role) {
                admin = role.equals(Constants.LABEL_ADMIN);
                customer = role.equals(Constants.LABEL_CUSTOMER);
            }
        }
        
        String uri = httpRequest.getRequestURI();
        Boolean adminUri = !uri.startsWith(CUSTOMERURI) || uri.endsWith(ADMIN);
        Boolean customerUri = uri.startsWith(DVDURI) || 
            uri.startsWith(CATEGORYURI) || uri.startsWith(ORDERURI) || 
            uri.endsWith(ADMIN);
        
        if (uri.endsWith(DVDSTOREURI) || uri.startsWith(IMAGE) || 
                uri.startsWith(USERURI)){
            
            if ((uri.endsWith(DVDSTOREURI) || uri.endsWith(LOGIN)) && 
                    (Boolean.TRUE == admin)) {
                httpResponse.sendRedirect(ADMIN_MENU_JSP);
            } else if ((uri.endsWith(DVDSTOREURI) || uri.endsWith(LOGIN)) && 
                    (Boolean.TRUE == customer)) {
                httpResponse.sendRedirect(CUSTOMER_MENU_JSP);
            } else {
                chain.doFilter(request, response);
            }
        
        } else if ((Boolean.TRUE == admin && adminUri)) {
            chain.doFilter(request, response); 
            
        } else if (Boolean.TRUE == customer && !(customerUri)) {
            chain.doFilter(request, response);
            
        } else if (null == session) {
            httpRequest.setAttribute(Constants.LABEL_MESSAGE, 
                Constants.TIMEOUT_MESSAGE);
            httpRequest.getRequestDispatcher(LOGIN_JSP).
                forward(request, response);
            
        } else {
            session.setAttribute(Constants.LABEL_MESSAGE, 
                Constants.UNAUTHORITY_MESSAGE);
            httpResponse.sendRedirect(DVDSTOREURI);
        }
    }
    
    public void destroy() {
	
	}

}
