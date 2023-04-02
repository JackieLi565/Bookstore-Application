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

    private double discount = 0;
    protected int pointsAccumulated;
    private double total;
    
    @FXML
    private Text ownerMsg;
    @FXML 
    private Text points;
    @FXML 
    private Text status;
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
    
    private void SaveSelectedBooks() {
        for(Book book: bookstore.loadBookData()) {
            if(book.getIsChecked()) {
                bookstore.addSelectedBook(book);
            }
        }
    }

    @FXML
    public void handleCustomerShoppingtoCheckoutBUY(javafx.event.ActionEvent event) {
        SaveSelectedBooks();
        try {
            //
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerCheckoutPage.fxml"));
            Parent root = loader.load();
            CustomerCheckoutPageController checkoutController = loader.getController();
            for (Book book : bookList) {
            if (book.getIsChecked()) {
                total += book.getPrice();
                }
            }
            discount = 0.00;
            
            checkoutController.displayTotal(total, discount);
            
            checkoutController.displayMemberInformation(total, discount);
            
            
            //Parent root = FXMLLoader.load(getClass().getResource("CustomerCheckoutPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Customer Shopping Screen to Checkout");
        } catch (Exception e) {
        
        }
    }
    
    @FXML
    public void handleCustomerShoppingtoCheckoutREDEEM(javafx.event.ActionEvent event) {
        SaveSelectedBooks();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerCheckoutPage.fxml"));
            Parent root = loader.load();
            CustomerCheckoutPageController checkoutController = loader.getController();
            
            for (Book book : bookList) {
            if (book.getIsChecked()) {
                total += book.getPrice();
                }
            }
            
            if(bookstore.getUser().getPoints() >= 100)
            {
                discount = Math.floor(bookstore.getUser().getPoints() / 100);
                
                if(discount > total)
                {
                    discount = total;
                }
            }
            
            else
            {
                discount = 0;
            }
            
            bookstore.getUser().setPoints(bookstore.getUser().getPoints() - (int)discount * 100);
            
            checkoutController.displayTotal(total, discount);
            
            checkoutController.displayMemberInformation(total, discount);
            
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Customer Shopping Screen to Checkout");
        } catch (Exception e) {
        
        }
    }
    
    @FXML
    public void onCustomerShoppingLogout(javafx.event.ActionEvent event) {
        SaveSelectedBooks();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            bookstore.logout();
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
                    ownerMsg.setText(book.getName() + " Good Choice!");
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
