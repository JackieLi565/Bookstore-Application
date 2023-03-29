package backendModel;

/**
 *
 * @author ishan
 */
public abstract class Account {
    
    protected String username;
    protected String password;
    
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public abstract int getPoints();
    public abstract void setPoints(int p);
    
    public abstract String getUserName();
    public abstract void setUserName(String u);
    
    public abstract String getPassword();
    public abstract void setPassword(String p);
    
}

