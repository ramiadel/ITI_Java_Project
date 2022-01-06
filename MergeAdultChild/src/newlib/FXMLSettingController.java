/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

//onAction="#btnHighlight"
/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLSettingController implements Initializable {

      private Stage stage;
      private Scene scene;
//    private int flag=0;
//    static private int flagTurn=0;

    
    ////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    //the setting pane:
    @FXML
    private Text aboutTxt;
     @FXML
    void btnAbout(ActionEvent event) {
        aboutTxt.setVisible(true);
    }

    @FXML   
    void btnBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
 
      @FXML
    void soundOf(ActionEvent event) {
        FXMLStartController.mediaplayer.stop();
       
    }

    @FXML
    void soundOn(ActionEvent event) {
        FXMLStartController.mediaplayer.play();
    }

     @FXML
    private ImageView myImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), myImage);
       rotateTransition.setByAngle(360);
       rotateTransition.play();
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}