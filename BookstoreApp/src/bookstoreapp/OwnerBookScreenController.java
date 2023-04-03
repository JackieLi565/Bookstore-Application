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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import backendModel.Book;
import backendModel.Bookstore;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;

public class OwnerBookScreenController implements Initializable {
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private Bookstore bookstore;
    
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Text error;
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
    
    @FXML
    private javafx.scene.control.TextField bookTitle;
    
    @FXML
    private javafx.scene.control.TextField bookPrice;
    
    @FXML
    public void addBook(javafx.event.ActionEvent event) {
        //creates the new book
        try {
            Book book = new Book(bookTitle.getText(), Double.parseDouble(bookPrice.getText()));
            bookstore.loadBookData().add(book); //adds the book to the book store database
            bookList.clear(); //clears the current list of books inside the table
            bookList.addAll(bookstore.loadBookData()); //readds all the book data from the book store databse (incliding the new book) and adds it to the Observ list

            bookTitle.clear(); //clear input box
            bookPrice.clear(); //clear input box
            error.setText("");
            bookTable.setItems(bookList); // add the observ list to the table view
        } catch (Exception e) {
            error.setText("Not Valid Book Price");
        }
    }
    
    @FXML
    public void removeBook(javafx.event.ActionEvent event) {
        ObservableList<Book> selectedBooks = FXCollections.observableArrayList(); //list for selecting the books
        // for each book if the book isChecked add it to the lsit of checked books
        for (Book book : bookList) {
            if (book.getIsChecked()) {
                selectedBooks.add(book);
            }
        }
        bookList.removeAll(selectedBooks); // removes all selected books
        bookstore.loadBookData().clear(); // clear the db book data
        bookstore.loadBookData().addAll(bookList);
        bookList.clear(); //clears the list
        bookList.addAll(bookstore.loadBookData()); // updates the book data
        bookTable.setItems(bookList); //loads it to the table
    }
    
    /*
    * Really janky routing system for the Navbar, I didn't want to create smaller controller files
    */
    @FXML
    public void handleOwnerBooktoCustomer(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OwnerCustomerScreen.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Owner Book Screen to Customer Screen");
        } catch (Exception e) {
        
        }
    }
    
    @FXML 
    public void onOwnerBookLogout(javafx.event.ActionEvent event) {
        bookstore.logout();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Owner Book Logout");
        } catch (Exception e) {
        
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bookstore = Bookstore.getInstance();
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
    }    
    
}
