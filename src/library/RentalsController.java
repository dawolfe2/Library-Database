
package library;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RentalsController implements Initializable {

    private Stage newstage;
    private Scene newscene;
    private Parent newroot;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    @FXML
    private void ButtonSearch(ActionEvent event) throws IOException {
    
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
    private void ButtonBack(ActionEvent event) throws IOException {
        newroot = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        newstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        newscene = new Scene(newroot);
        newstage.setScene(newscene);
        newstage.show();
    }
   
}
