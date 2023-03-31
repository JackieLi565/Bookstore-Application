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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author lijac
 */
public class CustomerCheckoutPageController extends ShoppingUtils implements Initializable {
    protected Stage stage;
    protected Scene scene;

    //table
    @FXML
    private javafx.scene.control.TableView<Book> bookTable;
    
    //columns
    @FXML
    private javafx.scene.control.TableColumn<Book, String> bookNameCol;
    
    @FXML
    private javafx.scene.control.TableColumn<Book, Double> bookPriceCol;
    
    @FXML
    public void onOwnerCustomerLogout(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Customer Checkout Logout");
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
        for(Book book: selectedList) {
            System.out.println(book.getName());
        }
        bookList.addAll(selectedList);
        bookTable.setItems(bookList);
    }    
    
}
