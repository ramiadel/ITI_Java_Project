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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLNameController implements Initializable {
    private Stage stage;
    private Scene scene;
    public static String sName; 
    @FXML
    private ToggleGroup toggleGroup1;
    @FXML
    private PasswordField passArea;
    @FXML
    private TextField nameArea;
    @FXML
    private Button continueB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void btnContinue(ActionEvent event) throws IOException{       
        sName=nameArea.getText();//save the name which user write in the sname string
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));// create and load() view
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);    
        stage.setScene(scene);
        stage.show();
    }
    
}
