/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lijac
 */
public class CustomerShoppingScreenController implements Initializable {
    private Stage stage;
    private Scene scene;
    
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
        // TODO
    }    
    
}
