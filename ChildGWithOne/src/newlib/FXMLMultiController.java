/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLMultiController implements Initializable {
    int turnFlag=0;
    PrintStream ps;
    
    Socket mySocket;
    DataInputStream dis;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button btn0_0;
    @FXML
    private Button btn1_0;
    @FXML
    private Button btn2_0;
    @FXML
    private Button btn0_1;
    @FXML
    private Button btn0_2;
    @FXML
    private Button btn1_1;
    @FXML
    private Button btn2_1;
    @FXML
    private Button btn1_2;
    @FXML
    private Button btn2_2;
    @FXML
    private Button btnInvisable;
    @FXML
    private TextField txtMessage;
    @FXML
    private Text stateLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label turnLabel;  
    @FXML
    private TextArea txtArea;
    ////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turnLabel.setText(FXMLNameController.sName);
        // TODO    
    } 
      @FXML
    void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);       
        stage.show();

    }
    @FXML
    void btnRestart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMulti.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);       
        stage.show();  
    }

    @FXML
    private void btnHighlight() throws IOException{
        
    }
    @FXML
    private void btnHighlight1(ActionEvent event) {
        
    }

    @FXML
    private void btnHighlight2(ActionEvent event) {
       
    }

    @FXML
    private void btnHighlight3(ActionEvent event) {
        
    }
     @FXML
    private void btnHighlight4(ActionEvent event) {
       
    }
    @FXML
    private void btnHighlight5(ActionEvent event) {
        
    }

    @FXML
    private void btnHighlight6(ActionEvent event) {
       
    
    }
   @FXML
    private void btnHighlight7(ActionEvent event) {
      
    }
    @FXML
    private void btnHighlight8(ActionEvent event) {
        
    }
     @FXML
    void send(ActionEvent event) {
       
    }
    
}
