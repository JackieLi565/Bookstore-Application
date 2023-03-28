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
public class Owner extends Account{
    
    private static String username;
    private static String password;
    private static Owner instance;
    
    private Owner(String username, String password) {
        
        this.username = username;
        this.password = password;
        
    }
    
    public static Owner getInstance()
    {
        if(instance == null)
        {
            instance = new Owner(username, password);
        }
        
        return instance;
    }
    
    @Override
    public String getUserName()
    {
        return username;
    }
    
    @Override
    public void setUserName(String u)
    {
        username = u;
    }
    
    @Override
    public String getPassword()
    {
        return password;
    }
    @Override
    public void setPassword(String p)
    {
        password = p;
    }
    
    
}
