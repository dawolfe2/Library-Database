
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class RentalAddController implements Initializable {

    @FXML private TextField txtMemberID;
    @FXML private TextField txtISBN;
    @FXML private DatePicker txtDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    private void ButtonCheckOut(ActionEvent event) throws IOException {
        try{
            
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
    
            if(txtMemberID.getText().isEmpty() || txtISBN.getText().isEmpty() || txtDate.getValue().toString().isEmpty()){
                System.out.println("bad input");
            }  
            else{
                
                String memberIN = txtMemberID.getText();
                String isbnIN = txtISBN.getText();
                String dateIN = txtDate.getValue().toString();
                int available = 0;

                String sql = "SELECT Available FROM book WHERE isbn=?";

                PreparedStatement p = connection.prepareStatement(sql);
                p.setString(1, isbnIN);
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    available = rs.getInt("available");
                }
                
                if(available > 0){
                    
                    available = available - 1;

                    sql = "SELECT memberid,firstname,lastname FROM member where memberid=?";
                    p = connection.prepareStatement(sql);
                    p.setString(1, memberIN);
                    ResultSet rs1 = p.executeQuery();

                    String id = "";
                    String first = "";
                    String last = "";
                    String name = "";
                    String isbn = "";
                    String title = "";

                    while(rs1.next()){
                        id = rs1.getString("memberid");
                        first = rs1.getString("firstname");
                        last = rs1.getString("lastname");
                        name = first + " " + last;
                    }

                    sql = "SELECT isbn,title FROM book where isbn=?";
                    p = connection.prepareStatement(sql);
                    p.setString(1, isbnIN);
                    ResultSet rs2 = p.executeQuery();

                    while(rs2.next()){
                        isbn = rs2.getString("isbn");
                        title = rs2.getString("title");
                    }

                    String dateDue = dateIN;
                    String month;
                    month = "" + dateDue.charAt(5) + dateDue.charAt(6);
                    int intMonth;
                    intMonth = Integer.valueOf(month);
                    intMonth = intMonth + 1;
                    month = "0" + String.valueOf(intMonth);
                    dateDue = dateDue.substring(0,5) + month + dateDue.substring(7,10);

                    sql = "INSERT INTO rentals (memberid, name, isbn, title, due, rented) Values (?, ?, ?, ?, ?, ?)";
                    p = connection.prepareStatement(sql);
                    p.setString(1, id);
                    p.setString(2, name);
                    p.setString(3, isbn);
                    p.setString(4, title);
                    p.setString(5, dateDue);
                    p.setString(6, dateIN);
                    p.executeUpdate();

                    sql = "UPDATE book SET available=? WHERE isbn=?";
                    p = connection.prepareStatement(sql);
                    p.setInt(1, available);
                    p.setString(2, isbn);
                    p.executeUpdate();
                    p.close();
                }    
            }
        }
        catch(SQLException ex){
            System.out.println("bad database input");
        }
    }
    
}
