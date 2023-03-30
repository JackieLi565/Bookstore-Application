package backendModel;

import javafx.scene.control.CheckBox;

/**
 *
 * @author ishan
 */
public class Customer extends Account {
    private int points;
    private State memberType;
    private CheckBox isChecked;
    
    public Customer(String user, String pass, int points)
    {
        super(user, pass);
        this.points = points;
        this.isChecked = new CheckBox();
        changeState();
    }
    
    private void changeState()
    {
        if(points >= 1000)
        {
            this.memberType = new goldMember();
        }
        
        else {
            this.memberType = new silverMember();
        }
    }
    
    public State getState() {
        changeState();
        return this.memberType;
    }
    
    public void setCheck(CheckBox check){
        this.isChecked = check;
    }
    public CheckBox getCheck(){
        return this.isChecked;
    }
    
    @Override
    public int getPoints()
    {
        return this.points;
    }
    
    @Override
    public void setPoints(int p)
    {
        this.points = p;
    }
    
    @Override
    public String getUserName()
    {
        return this.username;
    }
    
    @Override
    public void setUserName(String u)
    {
        this.username = u;
    }
    
    @Override
    public String getPassword()
    {
        return this.password;
    }
    
    @Override
    public void setPassword(String p)
    {
        this.password = p;
    }
}
