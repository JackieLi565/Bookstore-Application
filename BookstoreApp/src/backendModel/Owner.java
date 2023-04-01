package backendModel;

public class Owner extends Account{
    
    private static Owner instance;
    
    private Owner() {
        super("admin", "admin");     
    }
    
    public static Owner getInstance()
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
        throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }
    
    @Override
    public void setPassword(String p)
    {
      throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }

    @Override
    public int getPoints() {
        throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }

    @Override
    public void setPoints(int p) {
        throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }

    @Override
    public void setIsChecked(boolean isChecked) {
        throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }

    @Override
    public boolean getIsChecked() {
        throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }

    @Override
    public State getState() {
        throw new UnsupportedOperationException("Unsupported Feature In V1.01."); 
    }
    
    
}
