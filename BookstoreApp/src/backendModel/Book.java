/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendModel;

/**
 *
 * @author lijac
 */
public class Book {
    private String book;
    private int price;

    public Book(String book, int price) {
        this.book = book;
        this.price = price;
    }

    public String getBook() {
        return this.book;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "" + this.book + ", " + this.price;
    }
}
