package backendModel;

import java.util.ArrayList;

public class Bookstore {
    private final Query database;
    private static Bookstore instance;
    private static final String CF = "Customers.txt";
    private static final String BF = "Books.txt";
    private Account currentUser;
    private ArrayList<Book> selectedBooks;
    
    private Bookstore() {
        database = Query.getInstance(CF, BF);
        selectedBooks = new ArrayList<>();
        this.currentUser = null;
    }
    
    public static Bookstore getInstance() {
        if(instance == null) {
            instance = new Bookstore();
        }
        return instance;
    }
    
    private void updateDatabase() {
        database.saveToDataBase();
    }
    
    private void setUser(Account user) {
        this.currentUser = user;
    }
    
    public Account getUser() {
        return this.currentUser;
    }
    
    public ArrayList<Account> loadUserData() {
        return database.getCustomers();
    }
    
    public ArrayList<Book> loadBookData() {
        return database.getBooks();
    }
    
    public void addBook(Book book) {
        database.addBook(book);
    }
    
    public void addCustomer(Customer customer) {
        database.addCustomer(customer);
    }
 
    public void addSelectedBook(Book book) {
        this.selectedBooks.add(book);
    }
    
    public ArrayList<Book> loadSelectedBooks() {
        return this.selectedBooks;
    }
    
    public boolean login(String username, String password) {
        ArrayList<Account> list = database.getCustomers();
        for(Account customer: list) {
            if(customer.getUserName().equals(username) && customer.getPassword().equals(password)){
                setUser(customer);
                return true;
            }
        }
        return false;
    }
    
    public void logout() {
        //clears the checked books
        for(Book book: selectedBooks) {
            book.setIsChecked(false);
        }
        updateDatabase();
        this.currentUser = null;
        //clears the selected books
        loadSelectedBooks().clear();
    }
}
