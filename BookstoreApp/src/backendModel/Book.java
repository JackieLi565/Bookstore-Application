package backendModel;


/**
 *
 * @author adria
 */
public class Book {
    private String name;
    private double price;
    private boolean isChecked;

    //Book constructor
    public Book(String name, double price){
        this.name = name;
        this.price = price;
        this.isChecked = false;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
    public boolean getIsChecked(){
        return this.isChecked;
    }
}
    
    

