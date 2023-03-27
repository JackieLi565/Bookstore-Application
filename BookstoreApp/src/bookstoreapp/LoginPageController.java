/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

 
import java.net.URL;
import java.util.ResourceBundle;

//fx imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Model imports
import backendModel.Bookstore;

/**
 * FXML Controller class
 *
 * @author lijac
 */
public class LoginPageController implements Initializable {
    
    Bookstore bookstore;
    private Stage stage;
    private Scene scene;

    @FXML
    private javafx.scene.control.TextField username;
    @FXML
    private javafx.scene.control.PasswordField password;

    @FXML
    public void login(javafx.event.ActionEvent event) {
        String name = username.getText();
        String pass = password.getText();
        boolean isLoggedIn = bookstore.login(name, pass);
        if (isLoggedIn) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("OwnerStartScreen.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println("Could not load customer screen");
            }
        } else if (true) {
            //TODO: implement owner
        } else {
            System.out.println("is neither owner or memeber");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookstore = new Bookstore();
        //TODO: inst owner instance
    }    



    
}
