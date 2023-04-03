package backendModel;

public class goldMember implements State{
    private double discount;
    @Override
    public double calculateDiscount(double total, Bookstore bookstore) {
        //check if the customer can buy the book
        if(bookstore.getUser().getPoints() >= 100)
        {
            discount = Math.floor(bookstore.getUser().getPoints() / 100);

            if(discount > total)
            {
                discount = total;
            }
        }else
        {
            discount = 0;
        }
        return discount;
    }  
    
    @Override 
    public String toString() {
        return "Gold";
    }
}
