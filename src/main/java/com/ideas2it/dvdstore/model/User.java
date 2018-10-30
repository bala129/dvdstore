package com.ideas2it.dvdstore.model;

/**
 * <p>
 * It's contains the deatils of the user in the dvdstore 
 * And it's not store the personal details of user
 * It's only store the login details of the user like user name, password
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class User {
    
    private Integer userId;
    private String userName;
    private String password;
    private String role;
    
    /** 
     * The constructor with no paramater 
     */
    public User() {
    
    }
    
    /** 
     * The constructor with different paramater 
     * is used to set the dvd details 
     */
    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}
