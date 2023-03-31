package bookstoreapp;

import backendModel.Account;
import backendModel.Book;
import backendModel.Bookstore;
import backendModel.Customer;
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
import javafx.stage.Stage;

public class OwnerCustomerScreenController implements Initializable {
    private ObservableList<Account> customerList = FXCollections.observableArrayList();
    private Bookstore bookstore;
    
    private Stage stage;
    private Scene scene;
    
    //table
    @FXML
    private javafx.scene.control.TableView<Account> customerTable;
    
    //columns
    @FXML
    private javafx.scene.control.TableColumn<Account, String> customerNameCol;
    
    @FXML
    private javafx.scene.control.TableColumn<Account, String> customerPassCol;
    
    @FXML
    private javafx.scene.control.TableColumn<Account, Boolean> check;
    
    @FXML
    private javafx.scene.control.TextField customerName;
    
    @FXML
    private javafx.scene.control.TextField customerPrice;
    
    @FXML
    public void addCustomer(javafx.event.ActionEvent event) {
        //creates the new book
        Account customer = new Customer(customerName.getText(), customerPrice.getText(), 0);
        bookstore.loadUserData().add(customer); //adds the customer to the book store database
        customerList.clear(); //clears the current list of books inside the table
        customerList.addAll(bookstore.loadUserData()); //readds all the book data from the book store databse (incliding the new book) and adds it to the Observ list

        customerName.clear(); //clear input box
        customerPrice.clear(); //clear input box
        
        customerTable.setItems(customerList); // add the observ list to the table view
    }
    
    @FXML
    public void removeCustomer(javafx.event.ActionEvent event) {
        ObservableList<Account> selectedCustomers = FXCollections.observableArrayList(); //list for selecting the books
        // for each book if the book isChecked add it to the lsit of checked books
        for (Account customer : customerList) {
            if (customer.getIsChecked()) {
                selectedCustomers.add(customer);
            }
        }
        
        customerList.removeAll(selectedCustomers); // removes all selected books
        bookstore.loadUserData().clear(); // clear the db book data
        bookstore.loadUserData().addAll(customerList);
        customerList.clear(); //clears the list
        customerList.addAll(bookstore.loadUserData()); // updates the book data
        customerTable.setItems(customerList); //loads it to the table
    }
    
    
    /*
    * Really janky routing system for the Navbar, I didn't want to create smaller controller files
    */
    @FXML
    public void onOwnerCustomerLogout(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Owner Customer Logout");
        } catch (Exception e) {
        
        }
    }
    
    @FXML 
    public void handleOwnerCustomertoBook(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OwnerBookScreen.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Owner Customer Screen to Book");
        } catch (Exception e) {
        
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookstore = Bookstore.getInstance();
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        customerPassCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        check.setCellValueFactory(new PropertyValueFactory<>("isChecked"));
        check.setCellFactory(column -> new TableCell<Account, Boolean>() {
            private final CheckBox checkBox = new CheckBox();
            {
                // Add an event listener to the CheckBox control to update the Book's isChecked property
                checkBox.setOnAction(event -> {
                    Account customer = getTableView().getItems().get(getIndex());
                    customer.setIsChecked(checkBox.isSelected());
                    System.out.println(customer.getIsChecked() + ", " + customer.getUserName());
                });
            }
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    // Get the current Book instance for the current row and set the CheckBox control's selected state
                    Account customer = getTableView().getItems().get(getIndex());
                    checkBox.setSelected(customer.getIsChecked()); //get the true or false value of box to change the graphics
                    setGraphic(checkBox);
                }
            }
        });
        customerList.addAll(bookstore.loadUserData());
        customerTable.setItems(customerList);
    }    
    
}
