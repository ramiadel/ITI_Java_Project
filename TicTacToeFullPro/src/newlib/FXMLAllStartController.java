/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.io.File;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import static newlib.FXMLStartController.path;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLAllStartController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private ToggleGroup Mode;

    @FXML
    private RadioButton aMode;

    @FXML
    private RadioButton cMode;
     @FXML
    private RadioButton teenAger;


    @FXML
    void adultM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument_1.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void childMode(ActionEvent event) throws IOException {
        //String path="./application/gui/FXMLStart.fxml";
        //Parent root = FXMLLoader.load(getClass().getResource(Parent root = FXMLLoader.load(getClass().getResource("FXMLStart.fxml"));));
        Parent root = FXMLLoader.load(getClass().getResource("FXMLStart.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
      
    @FXML
    void teenMode(ActionEvent event) throws IOException {
//        String path="./application/gui/main/Main.fxml";
//        Parent root = FXMLLoader.load(getClass().getResource(new File(path).toURI().toString()));
//        
//resourcesloader.class.getClassLoader().getResource("package1/resources/repository/SSL-Key/cert.jks").toString();
       //Parent root = FXMLLoader.load(getClass().getResource("../application/gui/main/Main.fxml").);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
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
