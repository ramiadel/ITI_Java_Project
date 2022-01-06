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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLStartController implements Initializable {
    private Stage stage;
    private Scene scene;
//    static String path="ChatRoom.mp4";
//    public static Media media= new Media(new File(path).toURI().toString());
//    public static MediaPlayer mediaplayer = new MediaPlayer(media);
    @FXML
    private Button btnPlay;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //mediaplayer.play();
    }    

    @FXML
    private void playsound(ActionEvent event) {
        //mediaplayer.play();
    }
    //to change to the next pane(FXMLName)
    @FXML
    public void btnAnyWhere(ActionEvent event) throws IOException{
        //flag=1;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLName.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    
}
