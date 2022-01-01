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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLOneController implements Initializable {
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
    private Button backBtn;
    @FXML
    private Label timeLabel;
    @FXML
    private Label nameLabel;
    //it will appear when any one win
    @FXML
    private Text StateLabel;
    //it will appear when the game status is draw
    @FXML
    private ImageView drawImage;
    //it will appear when the player lose
    @FXML
    private ImageView gameOver;
    //it will appear when the player win
    @FXML
    private ImageView happyImage;

    ////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameLabel.setText(FXMLNameController.sName);
        
    }  

    private void disableBtn()
    {
        btn0_0.setDisable(true);
        btn0_1.setDisable(true);
        btn0_2.setDisable(true);
        btn1_0.setDisable(true);
        btn1_1.setDisable(true);
        btn1_2.setDisable(true);
        btn2_0.setDisable(true);
        btn2_1.setDisable(true);
        btn2_2.setDisable(true);
    }
    
    @FXML
    private void btnHighlight(){      
      
    }
    @FXML
    private void btnHighlight1(){

    }
    @FXML
    private void btnHighlight2(){

    }
    @FXML
    private void btnHighlight3(){

    }
    @FXML
    private void btnHighlight4(){
        
    }
    @FXML
    private void btnHighlight5(){

    }
    @FXML
    private void btnHighlight6(){       

    }
    @FXML
    private void btnHighlight7(){
        
 
    }
    @FXML
    private void btnHighlight8(){
   
    }

     @FXML
    void btnRestart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLOne.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);       
        stage.show();  
    }
      @FXML
    void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);       
        stage.show();

    }
    
}
