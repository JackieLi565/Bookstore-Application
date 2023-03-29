package backendModel;

/**
 *
 * @author ishan
 */
public class Owner extends Account{
    
    private static Owner instance;
    
    private Owner() {
        super("admin", "admin");     
    }
    
    public Owner getInstance()
    {
        if(instance == null)
        {
            instance = new Owner();
        }
        return instance;
    }
    
    @Override
    public String getUserName()
    {
        return this.username;
    }
    
    @Override
    public String getPassword()
    {
        return password;
    }
    
    @Override
    public void setUserName(String u)
    {
        System.err.println("Error, no access");
    }
    
    @Override
    public void setPassword(String p)
    {
       System.err.println("Error, no access");
    }

    @Override
    public int getPoints() {
        System.err.println("No points handled");
        return -1;
    }

    @Override
    public void setPoints(int p) {
        System.err.println("No points handled");
    }
    
    
}
