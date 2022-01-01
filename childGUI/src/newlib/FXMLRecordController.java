/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLRecordController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> bestTimeC;

    @FXML
    private TableColumn<?, ?> drawC;

    @FXML
    private TableColumn<?, ?> loseC;

    @FXML
    private TableColumn<?, ?> matchesC;

    @FXML
    private TableColumn<?, ?> nameC;

    @FXML
    private TableColumn<?, ?> rankC;

    @FXML
    private TableColumn<?, ?> winC;
    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
