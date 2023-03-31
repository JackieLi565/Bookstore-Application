/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import backendModel.Book;
import backendModel.Bookstore;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lijac
 */
public class CustomerShoppingScreenController implements Initializable {
    private Stage stage;
    private Scene scene;
    protected ObservableList<Book> bookList = FXCollections.observableArrayList();
    Bookstore bookstore = Bookstore.getInstance();

    @FXML private Text points;
    @FXML private Text status;
    //table
    @FXML
    private javafx.scene.control.TableView<Book> bookTable;
    
    //columns
    @FXML
    private javafx.scene.control.TableColumn<Book, String> bookNameCol;
    
    @FXML
    private javafx.scene.control.TableColumn<Book, Double> bookPriceCol;
    
    @FXML
    private javafx.scene.control.TableColumn<Book, Boolean> check;
    
    /*
    * Really janky routing system for the Navbar, I didn't want to create smaller controller files
    */
    
    private void buyBooks() {
        System.out.println("Buy Books");
        
        
    }
    
    private void redeemWithPoints() {
        System.out.println("Buy Books");
        
        
    }
    
    private void Search() {
        System.out.println("Search");
    }
    
    @FXML
    public void handleSearchBooks(javafx.event.ActionEvent event) {
        try {
            Search();
        } catch (Exception e) {
        
        }
    }
    
    @FXML
    public void handleCustomerShoppingtoCheckoutBUY(javafx.event.ActionEvent event) {
        for(Book book: bookstore.loadBookData()) {
            if(book.getIsChecked()) {
                bookstore.addSelectedBook(book);
            }
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerCheckoutPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Customer Shopping Screen to Checkout");
            buyBooks();
        } catch (Exception e) {
        
        }
    }
    
    @FXML
    public void handleCustomerShoppingtoCheckoutREDEEM(javafx.event.ActionEvent event) {
        for(Book book: bookList) {
            if(book.getIsChecked()) {
                bookstore.addSelectedBook(book);
            }
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerCheckoutPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Customer Shopping Screen to Checkout");
            redeemWithPoints();
        } catch (Exception e) {
        
        }
    }
    
    @FXML
    public void onCustomerShoppingLogout(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Customer Shopping Screen logout");
        } catch (Exception e) {
        
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        check.setCellValueFactory(new PropertyValueFactory<>("isChecked"));
        check.setCellFactory(column -> new TableCell<Book, Boolean>() {
            private final CheckBox checkBox = new CheckBox();
            {
                // Add an event listener to the CheckBox control to update the Book's isChecked property
                checkBox.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    book.setIsChecked(checkBox.isSelected());
                    System.out.println(book.getIsChecked() + ", " + book.getName());
                });
            }
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    // Get the current Book instance for the current row and set the CheckBox control's selected state
                    Book book = getTableView().getItems().get(getIndex());
                    checkBox.setSelected(book.getIsChecked()); //get the true or false value of box to change the graphics
                    setGraphic(checkBox);
                }
            }
        });
        bookList.addAll(bookstore.loadBookData());
        bookTable.setItems(bookList);
        points.setText(Integer.toString(bookstore.getUser().getPoints()));
        status.setText(bookstore.getUser().getState().toString());
    }    
    
}
