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
public class Customer extends Account {
    
    private String username;
    private String password;
    private int points;
    private State memberType;
    
    public Customer(String user, String pass)
    {
        this.username = user;
        this.password = pass;
        this.points = 0;
        //this.memberType = silver;
    }
    
    public void changeState()
    {
        if(points >= 1000)
        {
            //memberType = gold;
        }
        
        else {
            //memberType = silver;
        }
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public void setPoints(int p)
    {
        points = p;
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
