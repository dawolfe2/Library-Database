
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


public class BooksController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void ButtonAdd(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    private void ButtonNew(ActionEvent event) throws IOException {
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookAdd.fxml"));
            Parent newroot = (Parent) loader.load();
            Stage newstage = new Stage();
            newstage.setScene(new Scene(newroot));
            newstage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    @FXML
    private void ButtonBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
