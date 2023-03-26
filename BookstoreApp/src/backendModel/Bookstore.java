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
    
    public Bookstore() {
        database = Query.getInstance(CF, BF);
    }
    
    /*
        public Bookstore getInstance() {
        if(instance == null) {
            instance = new Bookstore();
        }
        return instance;
    }
    */
    
    
    public boolean login(String username, String password) {
        ArrayList<Account> list = database.getCustomers();
        for(Account customer: list) {
            if(customer.getName().equals(username) && customer.getPassword().equals(password)){
                System.out.println("is");
                return true;
            }
        }
        System.out.println("isnt");
        return false;
    }
    
}
