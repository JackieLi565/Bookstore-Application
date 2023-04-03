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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerCheckoutPageController implements Initializable {
    protected Stage stage;
    protected Scene scene;
    
    protected ObservableList<Book> bookList = FXCollections.observableArrayList();
    Bookstore bookstore = Bookstore.getInstance();
    
    @FXML
    private Text name;
    
    //table
    @FXML
    private javafx.scene.control.TableView<Book> bookTable;
    
    //columns
    @FXML
    private javafx.scene.control.TableColumn<Book, String> bookNameCol;
    
    @FXML
    private javafx.scene.control.TableColumn<Book, Double> bookPriceCol;
    
    @FXML
    private Text total;
    
    @FXML
    private Text discount;
    
    @FXML 
    private Text amountDue;
    
    @FXML
    private Text accumlatedPoints;
    
    @FXML 
    private Text memberType;
    
    @FXML
    public void displayTotal(double totalAmount, double discountAmount)
    {
        total.setText("Total Price: $" + String.format("%.2f", totalAmount));
        discount.setText("Discount: $" + String.format("%.2f", discountAmount));
        amountDue.setText("Amount Due: $" + String.format("%.2f", totalAmount-discountAmount));
    }
    
    @FXML
    public void displayMemberInformation(double totalAmount, double discountAmount)
    {
        int points = (int)(totalAmount-discountAmount)*10;
        accumlatedPoints.setText("Accumlated Points: " + Integer.toString(points));
        
        bookstore.getUser().setPoints(bookstore.getUser().getPoints() + points);
        memberType.setText("Member Status: " + bookstore.getUser().getState());
    }

    @FXML
    public void onOwnerCustomerLogout(javafx.event.ActionEvent event) {
        try {
            bookstore.logout();
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
        bookList.addAll(bookstore.loadSelectedBooks());
        bookTable.setItems(bookList);
        
        // Loops get the total price of the checked books
        double totalPrice = 0.0;
        for (Book book : bookList) {
            if (book.getIsChecked()) {
                totalPrice += book.getPrice();
            }
        }
        
        String formattedPrice = String.format("%.2f", totalPrice);
        name.setText(bookstore.getUser().getUserName());
        //total.setText("Total Price: $" + formattedPrice);
    }    
    
}
