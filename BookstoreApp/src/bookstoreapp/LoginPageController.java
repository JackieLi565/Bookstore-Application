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
import backendModel.Owner;
import javafx.scene.text.Text;

public class LoginPageController implements Initializable {
    Bookstore bookstore;
    Owner owner;
    private Stage stage;
    private Scene scene;

    @FXML
    private Text error;
    @FXML
    private javafx.scene.control.TextField username;
    @FXML
    private javafx.scene.control.PasswordField password;

    @FXML
    public void login(javafx.event.ActionEvent event) {
        Parent root;
        String name = username.getText();
        String pass = password.getText();
        try {
            if (name.equals(owner.getUserName()) && pass.equals(owner.getPassword())) {
                root = FXMLLoader.load(getClass().getResource("OwnerStartScreen.fxml"));
                scene = new Scene(root);
                System.out.println("Directing to Owner Start Screen");
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (bookstore.login(name, pass)) {
                root = FXMLLoader.load(getClass().getResource("CustomerStartScreen.fxml"));
                scene = new Scene(root);
                System.out.println("Directing to Customer Start Screen");
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                scene = new Scene(root);
                error.setText("invalid login credentials");
                System.out.println("Could not find user in the database");
            }
        }catch(Exception e) {
        
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO: inst owner instance
        bookstore = Bookstore.getInstance();
        owner = new Owner(); //owner.getInstance();
    }    



    
}
