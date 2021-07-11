
package library;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BookAddController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML private TextField txtID;
    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtPublisher;
    @FXML private TextField txtPages;
    @FXML private TextField txtDate;
    @FXML private TextField txtGenre;
    @FXML private Button btnExit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void ButtonExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void ButtonAdd(ActionEvent event) throws IOException {
        
    }
    
}
