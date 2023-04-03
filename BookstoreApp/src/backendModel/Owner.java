package backendModel;

public class Owner extends Account{
    
    private static Owner instance;
    
    public Owner() {
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
        return this.password;
    }
    
    @Override
    public void setUserName(String u) {
        throw new UnsupportedOperationException("Not Supported in V0.1.1");
    }
    
    @Override
    public void setPassword(String p) {
       throw new UnsupportedOperationException("Not Supported in V0.1.1");
    }

    @Override
    public int getPoints() {
        throw new UnsupportedOperationException("Not Supported in V0.1.1");
    }

    @Override
    public void setPoints(int p) {
        throw new UnsupportedOperationException("Not Supported in V0.1.1");
    }

    @Override
    public void setIsChecked(boolean isChecked) {
        throw new UnsupportedOperationException("Not Supported in V0.1.1");
    }

    @Override
    public boolean getIsChecked() {
        throw new UnsupportedOperationException("Not Supported in V0.1.1");
    }

    @Override
    public State getState() {
        throw new UnsupportedOperationException("Not Supported in V0.1.1"); 
    }
    
    
}
