
package library;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


public class MemberAddController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
    @FXML private TextField txtFirst;
    @FXML private TextField txtLast;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhoneOne;
    @FXML private TextField txtPhoneTwo;
    @FXML private TextField txtEmailOne;
    @FXML private TextField txtEmailTwo;
    @FXML private DatePicker txtDate; 
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
    private void ButtonAdd(ActionEvent event) throws IOException {
        
        
        if( txtFirst.getText().isEmpty() || txtLast.getText().isEmpty() || txtAddress.getText().isEmpty() ||
                txtPhoneOne.getText().isEmpty() || txtEmailOne.getText().isEmpty() || txtDate.getValue().toString().isEmpty()){
            
            System.out.println("bad input");
            
        }
        
        else{
        
            String first = txtFirst.getText();
            String last = txtLast.getText();
            String address = txtAddress.getText();
            String phoneOne = txtPhoneOne.getText();
            String emailOne = txtEmailOne.getText();
            String date = txtDate.getValue().toString();
            
            String phoneTwo = txtPhoneTwo.getText();
            String emailTwo = txtEmailTwo.getText();
             
            txtFirst.setText("");
            txtLast.setText("");
            txtAddress.setText("");
            txtPhoneOne.setText("");
            txtPhoneTwo.setText("");
            txtEmailOne.setText("");
            txtEmailTwo.setText("");
                   
            try {
                
                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");             
                
                String sql = "SELECT memberid FROM member";
                PreparedStatement p = connection.prepareStatement(sql);
                ResultSet rsID = p.executeQuery();
                
                int count = 1250;
                while(rsID.next()){
                    count = count + 1;
                }
                String id = "M0" + String.valueOf(count);
                
                sql = "INSERT INTO member (memberid, firstname, lastname, dob, rentals, address, memberlevel, dues) Values (?, ?, ?, ?, ?, ?, ?, ? )";
                p = connection.prepareStatement(sql);
                         
                p.setString(1, id);
                p.setString(2, first);
                p.setString(3, last);
                p.setString(4, date);
                p.setInt(5, 0);
                p.setString(6, address);
                p.setString(7, "Basic");
                p.setDouble(8, 0);
                  
                p.executeUpdate();
                p.close();
                
                } 
   
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        
    }
    
}
