
/**
 *
 * @author adria
 */
public class Book {
    private String name;
    private double price;
    public static int id;
    private boolean isChecked;
    
    
    //Book constructor
    public Book(String name, double price){
        this.name = name;
        this.price = price;
        this.isChecked = false;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setCheck(){
        this.isChecked = !isChecked;
    }
    public boolean getCheck(){
        return isChecked;
    }
}
    
    

