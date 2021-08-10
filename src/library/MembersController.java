
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

    //members controller to search the database for members by search and other functions
public class MembersController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
    @FXML private TextField txtSearch;
    @FXML private TableView<Member> table;
    @FXML private TableColumn<Member, String> IDColumn;
    @FXML private TableColumn<Member, String> firstColumn;
    @FXML private TableColumn<Member, String> lastColumn;
    @FXML private TableColumn<Member, String> dateColumn;
    @FXML private TableColumn<Member, String> addressColumn;
    @FXML private TableColumn<Member, Integer> rentalColumn;
    @FXML private TableColumn<Member, String> levelColumn;
    @FXML private TableColumn<Member, String> duesColumn;
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("last"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        rentalColumn.setCellValueFactory(new PropertyValueFactory<>("rentals"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        duesColumn.setCellValueFactory(new PropertyValueFactory<>("dues"));
        
    }   
    
   @FXML
    private void ButtonSearch(ActionEvent event) throws IOException {
 
            //checks for emtpy search text box
        if(txtSearch.getText().isEmpty()){
            
        }
        else{
            
            try {
                
                    //tries connecting to the database with localhost, user and pass
                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
                table.getItems().clear();
                String search = txtSearch.getText();
                
                    //prepares select sql statement for selecting matching searches
                String sql = "SELECT * FROM member WHERE memberid LIKE ? OR firstname Like ? OR lastname LIKE ?";
                PreparedStatement p = connection.prepareStatement(sql);
                p.setString(1, "%" + search + "%");
                p.setString(2, "%" + search + "%");
                p.setString(3, "%" + search + "%");
                ResultSet rs = p.executeQuery();

                ObservableList<Member> memberList; 
                memberList = FXCollections.observableArrayList();

                    //loops through resultset to add members to the list for the tableview
                while (rs.next()) {
                    
                    String id = rs.getString("memberid");
                    String first = rs.getString("firstname");
                    String last = rs.getString("lastname");
                    String date = rs.getString("dob");
                    int rentals = rs.getInt("rentals");
                    String address = rs.getString("address");
                    String level = rs.getString("memberlevel");
                    Double dues = rs.getDouble("dues"); 

                    Member newmember = new Member(id, first, last, address, date, rentals, level, dues);
                    memberList.add(newmember);
                }  
                
                table.setItems(memberList); 

            }
            catch(SQLException ex){

            }
        }    
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
