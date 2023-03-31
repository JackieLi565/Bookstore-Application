package bookstoreapp;

import backendModel.Book;
import backendModel.Bookstore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShoppingUtils {
    protected ObservableList<Book> bookList = FXCollections.observableArrayList();
    protected ObservableList<Book> selectedList = FXCollections.observableArrayList();
    protected Bookstore bookstore = new Bookstore();;
}
