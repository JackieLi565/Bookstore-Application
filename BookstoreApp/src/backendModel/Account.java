
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendModel;

/**
 *
 * @author ishan
 */
public abstract class Account {
    
    private String username;
    private String password;
    
    public abstract String getUserName();
    public abstract void setUserName(String u);
    
    public abstract String getPassword();
    public abstract void setPassword(String p);
    
}

