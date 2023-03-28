/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendModel;

import java.util.ArrayList;

/**
 *
 * @author lijac
 */
public class Bookstore {
    private final Query database;
    //private static Bookstore instance = new Bookstore();
    private static final String CF = "Customers.txt";
    private static final String BF = "Books.txt";
    private Account currentUser;
    public Bookstore() {
        database = Query.getInstance(CF, BF);
        this.currentUser = null;
    }
    
    /*
        public Bookstore getInstance() {
        if(instance == null) {
            instance = new Bookstore();
        }
        return instance;
    }
    */
    
    private void setUser(Account user) {
        this.currentUser = user;
    }
    
    public void logout() {
        this.currentUser = null;
    }
    
    public boolean login(String username, String password) {
        ArrayList<Account> list = database.getCustomers();
        for(Account customer: list) {
            if(customer.getName().equals(username) && customer.getPassword().equals(password)){
                System.out.println("is");
                setUser(customer);
                return true;
            }
        }
        System.out.println("isnt");
        return false;
    }
    
}
