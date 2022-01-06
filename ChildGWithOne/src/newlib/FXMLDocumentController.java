/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLDocumentController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane root;
     @FXML
    private Label yourName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        yourName.setText(FXMLNameController.sName);
    }    
    @FXML
    public void btnSingle(ActionEvent event) throws IOException{
        //flag=1;
        
        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLOne.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        /*try {
                nameLabel.setText(sName);
                } catch (Exception e) {
                 //System.out.println("s");
                }*/
        scene=new Scene(root1);
        stage.setScene(scene);       
        stage.show();  
    }

    @FXML
    private void btnMulti(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMulti.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnRecord(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLRecord.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSetting.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

   @FXML
    void btnExit(ActionEvent event) {
        System.exit(1);
    }
}
