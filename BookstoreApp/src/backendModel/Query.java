package backendModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Query {
    private static Query instance;
    ArrayList<Account> customers = new ArrayList<>();
    ArrayList<Book> books = new ArrayList<>();
    private String customerFile = "";
    private String bookFile = "";

    private Query(String CF, String BF) {
        this.customerFile = CF;
        this.bookFile = BF;
        queryCustomer();
        queryBooks();
    }

    static public Query getInstance(String CF, String BF) {
        if (instance == null)
            instance = new Query(CF, BF);
        return instance;
    }

    /*
     * Get customers from txt file and put them in an array list
    */
    private void queryCustomer() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(customerFile));
            String line;
            // error string data is soze of 1
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                String customerName = data[0];
                String customerPassword = data[1];
                int customerPoints = Integer.parseInt(data[2]);
                Account customer = new Customer(customerName, customerPassword, customerPoints);
                customers.add(customer);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Get Books from txt file and put them in an array list
    */
    private void queryBooks() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(bookFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                String bookName = data[0];
                double bookPrice = Double.parseDouble(data[1]);
                Book book = new Book(bookName, bookPrice);
                books.add(book);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public ArrayList<Account> getCustomers() {
        return customers;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void saveToDataBase() {
        dangerouslyWipeDatabase(0);
        try {
            FileWriter bf = new FileWriter(bookFile, true);
            for (Book book : books) {
                bf.write(book.getName()+ ", " + String.valueOf(book.getPrice()) + "\n");
            }
            FileWriter cf = new FileWriter(customerFile, true); // append mode
            for (Account customer : customers) {
                cf.write(customer.getUserName()+ ", " + customer.getPassword() + ", " + String.valueOf(customer.getPoints())
                + "\n");
            }
            bf.close();
            cf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ONLY CLEAR IF YOU MESSED UP THE TXT FILES AND NEED TO RESET THEM
     * selection
     * 0 for wiping both customer and book database
     * 1 for customer database
     * 2 for book database
     */
    private void dangerouslyWipeDatabase(int selection) {
        try {
            switch (selection) {
                case 0:
                    wipeHandler(customerFile);
                    wipeHandler(bookFile);
                    break;
                case 1:
                    wipeHandler(customerFile);
                    break;
                case 2:
                    wipeHandler(bookFile);
                    break;
                default:
                    System.err.println("Not valid case");
            }
            System.out.println("Wipe Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wipeHandler(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write("");
        fw.close();
    }

    public static void main(String[] args) {
        //customer driver
        /*
        Query database = getInstance("Customers.txt", "Books.txt");
    
        for (int i = 0; i < 10; i++) {
            database.addCustomer(new Customer("Jimmy" + i, ""+i));
        }

        System.out.println(database.customers);

        for (int i = 0; i < 10; i++) {
            database.addBook(new Book("Book" + i, i));
        }

        System.out.println(database.books);

        database.saveToDataBase();
        */
    }
}
