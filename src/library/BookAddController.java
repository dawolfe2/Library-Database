
package library;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BookAddController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
    @FXML private TextField txtID;
    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtPublisher;
    @FXML private TextField txtPages;
    @FXML private DatePicker txtDate;
    @FXML private TextField txtGenre;
    @FXML private Button btnExit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void ButtonExit(ActionEvent event) throws IOException {
        newstage = (Stage) btnExit.getScene().getWindow();
        newstage.close();
    }
    
    @FXML
    private void ButtonAdd(ActionEvent event) {
        
        
        if(Integer.valueOf(txtPages.getText()) < 1 || txtID.getText().isEmpty() || txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty() ||
                txtPublisher.getText().isEmpty() || txtGenre.getText().isEmpty() || txtDate.getValue().toString().isEmpty()){
            
            System.out.println("bad input");
            
        }
        
        else{
        
            String isbn = txtID.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String publisher = txtPublisher.getText();
            String genre = txtGenre.getText();
            int pages = Integer.valueOf(txtPages.getText());
            String date = txtDate.getValue().toString();
            
            txtID.setText("");
            txtTitle.setText("");
            txtAuthor.setText("");
            txtPublisher.setText("");
            txtGenre.setText("");
            txtPages.setText("");
            
            Book newbook = new Book(isbn, title, author, publisher, date, genre, pages, 1, 1);
               
            try {
                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
//                
                String sql = "INSERT INTO book (isbn, title, author, publisher, genre, pages, quantity, available, datepublished) Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = connection.prepareStatement(sql);
                       
                p.setString(1, isbn);
                
                p.setString(2, title);
                p.setString(3, author);
                p.setString(4, publisher);
                p.setString(5, genre);
                p.setInt(6, pages);
                p.setInt(7, 1);
                p.setInt(8, 1);
                p.setString(9, date);
                    
                p.executeUpdate();
                p.close();
                
                System.out.println("Added to Database");
                } 
   
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        
    }
   
}
