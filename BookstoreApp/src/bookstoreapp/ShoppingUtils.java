/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import backendModel.Book;
import backendModel.Bookstore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lijac
 */
public class ShoppingUtils {
    protected ObservableList<Book> bookList = FXCollections.observableArrayList();
    protected ObservableList<Book> selectedList = FXCollections.observableArrayList();
    protected Bookstore bookstore = new Bookstore();;
}
