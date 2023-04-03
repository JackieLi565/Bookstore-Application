package bookstoreapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import backendModel.Bookstore;

public class BookstoreApp extends Application {
    static Bookstore bookstore;
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store");
        stage.setOnCloseRequest(event -> {
            bookstore.logout();
        });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bookstore = Bookstore.getInstance();
        launch(args);
    }
    
}
