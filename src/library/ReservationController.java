
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

public class ReservationController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
    @FXML private TextField txtSearch;
    @FXML private TableView<Reservation> table;
    @FXML private TableColumn<Reservation, String> memberIDColumn;
    @FXML private TableColumn<Reservation, String> nameColumn;
    @FXML private TableColumn<Reservation, String> isbnColumn;
    @FXML private TableColumn<Reservation, String> titleColumn;
    @FXML private TableColumn<Reservation, String> dateColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        memberIDColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
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

                String sql = "SELECT * FROM reservation WHERE memberid LIKE ? OR isbn LIKE ? OR name LIKE ? OR title LIKE ?";
                PreparedStatement p = connection.prepareStatement(sql);
                p.setString(1, "%" + search + "%");
                p.setString(2, "%" + search + "%");
                p.setString(3, "%" + search + "%");
                p.setString(4, "%" + search + "%");
                ResultSet rs = p.executeQuery();

                ObservableList<Reservation> reservationList; 
                reservationList = FXCollections.observableArrayList();

                while (rs.next()) {

                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String isbn = rs.getString("isbn");
                    String title = rs.getString("title");
                    String enddate = rs.getString("enddate");

                    Reservation newres = new Reservation(id, name, isbn, title, enddate);
                    reservationList.add(newres);
                }  

                table.setItems(reservationList); 

            }
            catch(SQLException ex){

            }
        }    
    }
    
    @FXML
    private void ButtonReserve(ActionEvent event) throws IOException {
        
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationAdd.fxml"));
                Parent windowroot = (Parent) loader.load();
                Stage windowstage = new Stage();
                windowstage.setScene(new Scene(windowroot));
                windowstage.show();

                } 
            catch (IOException e) {
                }
    }
    
    @FXML
    private void ButtonRemove(ActionEvent event) throws IOException {
        
        ObservableList<Reservation> selected;
        selected = table.getSelectionModel().getSelectedItems();
        
        if(selected.get(0) == null){
            
        }
        else{     
            try {
               Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/contact", "nbuser", "nbuser");
                
                String idSelect = selected.get(0).getMemberID();
                String isbnSelect = selected.get(0).getIsbn();

                String sql = "DELETE FROM reservation WHERE memberid=? AND isbn=?";
                PreparedStatement p = connection.prepareStatement(sql);
                p.setString(1, idSelect);
                p.setString(2, isbnSelect);
                ResultSet rs = p.executeQuery();
                
                table.getItems().remove(selected.get(0));
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
