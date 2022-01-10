/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.io.File;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import newlib.Main;
import static newlib.Main.scene;
import static newlib.Main.screenBounds;
import newlib.Settings;

/**
 *
 * @author yasmi
 */
public class NewLib extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAllStart.fxml"));
       
        Scene scene = new Scene(root);
        
        File f = new File("C:\\Users\\moush\\Desktop\\newLib_1\\build\\classes\\newlib\\btnHighlight.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        

                    stage.setScene(scene);
                    stage.show();

                    // When the root window 
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                            @Override
                            public void handle(WindowEvent event) {
                                    Main.closeOthersWindows();					
                            }
                    });

            
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
