
package library;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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


public class RentalsController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
    @FXML private TextField txtSearch;
    @FXML private TableView<Rental> table;
    @FXML private TableColumn<Rental, String> memberIDColumn;
    @FXML private TableColumn<Rental, String> nameColumn;
    @FXML private TableColumn<Rental, String> isbnColumn;
    @FXML private TableColumn<Rental, String> titleColumn;
    @FXML private TableColumn<Rental, String> dueColumn;
    @FXML private TableColumn<Rental, String> dateColumn;
    @FXML private TableColumn<Rental, String> returnColumn;
    @FXML private TableColumn<Rental, Double> feeColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberIDColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dueColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }    
    
    
    @FXML
    private void ButtonSearch(ActionEvent event) throws IOException {
        
        if(txtSearch.getText().isEmpty()){

            }
        else{

            try {

                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
                table.getItems().clear();
                String search = txtSearch.getText();

                String sql = "SELECT * FROM rentals WHERE memberid LIKE ? OR isbn LIKE ? OR name LIKE ? OR title LIKE ?";
                PreparedStatement p = connection.prepareStatement(sql);
                p.setString(1, "%" + search + "%");
                p.setString(2, "%" + search + "%");
                p.setString(3, "%" + search + "%");
                p.setString(4, "%" + search + "%");
                ResultSet rs = p.executeQuery();

                ObservableList<Rental> rentalList; 
                rentalList = FXCollections.observableArrayList();

                while (rs.next()) {

                    String Mid = rs.getString("memberid");
                    String name = rs.getString("name");
                    String isbn = rs.getString("isbn");
                    String title = rs.getString("title");
                    String due = rs.getString("due");
                    String rented = rs.getString("rented");
                    String returned = rs.getString("returned");
                    Double fee = rs.getDouble("fee");

                    Rental newrental = new Rental(Mid, name, isbn, title, due, rented, returned, fee);
                    rentalList.add(newrental);
                }  

                table.setItems(rentalList); 

            }
            catch(SQLException ex){

            }
        }    
    }


        @FXML
        private void ButtonNew(ActionEvent event) throws IOException {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("RentalAdd.fxml"));
                Parent windowroot = (Parent) loader.load();
                Stage windowstage = new Stage();
                windowstage.setScene(new Scene(windowroot));
                windowstage.show();

                } 
            catch (IOException e) {
                }
    }
    
     @FXML
    private void ButtonReturn(ActionEvent event) throws IOException {
        
    
        ObservableList<Rental> selected;
        selected = table.getSelectionModel().getSelectedItems();
        
        if(selected.get(0) == null){
            
        }
        else{     
            try {
                
                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
                
                String idSelect = selected.get(0).getMemberID();
                String isbnSelect = selected.get(0).getIsbn();

                String sql = "SELECT * FROM rentals WHERE memberid=? AND isbn=?";
                PreparedStatement p = connection.prepareStatement(sql);
                p.setString(1, idSelect);
                p.setString(2, isbnSelect);
                ResultSet rs = p.executeQuery();

                String due = "";
                String id = "";
                String isbn = "";
                String inReturned = null;
                
                while(rs.next()){
                    id = rs.getString("memberid");
                    String name = rs.getString("name");
                    isbn = rs.getString("isbn");
                    String title = rs.getString("title");
                    due = rs.getString("due");
                    String rented = rs.getString("rented");
                    inReturned = rs.getString("returned");
                    Double inFee = rs.getDouble("fee");
                }

                if(inReturned != null){
                    System.out.println("already returned");
                }
                else{
                    
                    double fee = 0;
                    LocalDate today = LocalDate.now();
                    LocalDate duedate = LocalDate.parse(due);
                    String strToday = today.toString();
                    String strDue = duedate.toString();

                    if(duedate.isBefore(today)){
                        while(!strToday.equals(strDue)){
                            fee += .5;
                            duedate = duedate.plusDays(1);
                            strDue = duedate.toString();
                            if(fee > 99.5){
                                strToday = strDue;
                            }
                        }
                    }

                    String returnDate = today.toString();
                    
                    sql = "UPDATE rentals SET returned=?, fee=? WHERE memberid=? AND isbn=?";
                    p = connection.prepareStatement(sql);
                    p.setString(1, returnDate);
                    p.setDouble(2, fee);
                    p.setString(3, id);
                    p.setString(4, isbn);
                    p.executeUpdate();

                    sql = "SELECT Available FROM book WHERE isbn=?";
                    p = connection.prepareStatement(sql);
                    p.setString(1, isbnSelect);
                    rs = p.executeQuery();

                    int available = 0;

                    while(rs.next()){
                        available = rs.getInt("available");
                    }
                    available = available + 1;

                    sql = "UPDATE book SET available=? WHERE isbn=?";
                    p = connection.prepareStatement(sql);
                    p.setInt(1, available);
                    p.setString(2, isbn);
                    p.executeUpdate();

                    if(fee > 0){
                        sql = "SELECT dues FROM member WHERE memberid=?";
                        p = connection.prepareStatement(sql);
                        p.setString(1, id);
                        rs = p.executeQuery();

                        double dues = 0;

                        while(rs.next()){
                            dues = rs.getDouble("dues");
                        }
                        
                        dues = dues + fee;
                        
                        sql = "UPDATE member SET dues=? WHERE memberid=?";
                        p = connection.prepareStatement(sql);
                        p.setDouble(1, dues);
                        p.setString(2, id);
                        p.executeUpdate();

                        System.out.println("end");
                    }
                }
            }   
            catch (SQLException ex) {
            }
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
