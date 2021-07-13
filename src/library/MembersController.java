
package library;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MembersController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
//    @FXML private TextField txtSearch;
//    @FXML private TableView<Book> table;
//    @FXML private TableColumn<Book, String> isbnColumn;
//    @FXML private TableColumn<Book, String> titleColumn;
//    @FXML private TableColumn<Book, String> authorColumn;
//    @FXML private TableColumn<Book, String> publisherColumn;
//    @FXML private TableColumn<Book, String> genreColumn;
//    @FXML private TableColumn<Book, Integer> pagesColumn;
//    @FXML private TableColumn<Book, String> dateColumn;
//    @FXML private TableColumn<Book, Integer> availableColumn;
//    @FXML private TableColumn<Book, Integer> quantityColumn;
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
//        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
//        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
//        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
//        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
    }   
    
   @FXML
    private void ButtonSearch(ActionEvent event) throws IOException {
// 
//        if(txtSearch.getText().isEmpty()){
//            
//        }
//        else{
//            
//            try {
//
//                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
//                table.getItems().clear();
//                String search = txtSearch.getText();
//
//                String sql = "SELECT * FROM book WHERE title LIKE ? OR author Like ? OR genre LIKE ? OR publisher LIKE ? OR isbn LIKE ?";
//                PreparedStatement p = connection.prepareStatement(sql);
//                p.setString(1, "%" + search + "%");
//                p.setString(2, "%" + search + "%");
//                p.setString(3, "%" + search + "%");
//                p.setString(4, "%" + search + "%");
//                p.setString(5, search + "%");
//                ResultSet rs = p.executeQuery();
//                ObservableList<Book> bookList; 
//                bookList = FXCollections.observableArrayList();
//
//                while (rs.next()) {
//
//                    String isbn = rs.getString("isbn");
//                    String title = rs.getString("title");
//                    String author = rs.getString("author");
//                    String publisher = rs.getString("publisher");
//                    String genre = rs.getString("genre");
//                    int pages = rs.getInt("pages");
//                    int quantity = rs.getInt("quantity");
//                    int available = rs.getInt("available"); 
//                    String date = rs.getString("datepublished");
//
//
//                    Book newbook = new Book(isbn, title, author, publisher, date, genre, pages, quantity, available);
//                    bookList.add(newbook);
//
//                }  
//
//                table.setItems(bookList); 
//
//            }
//            catch(SQLException ex){
//
//            }
//        }    
    } 
    
    @FXML
    private void ButtonNew(ActionEvent event) throws IOException {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberAdd.fxml"));
            Parent windowroot = (Parent) loader.load();
            Stage windowstage = new Stage();
            windowstage.setScene(new Scene(windowroot));
            windowstage.show();

            } 
        catch (IOException e) {
            }
        
    } 
    
    @FXML
    private void ButtonBack(ActionEvent event) throws IOException {
        
        newroot = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newscene = new Scene(newroot);
        newstage.setScene(newscene);
        newstage.show();
        
    } 
    
}
