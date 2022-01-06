 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static newlib.TicTacToeGame.findBestMove;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLOneController implements Initializable {

    char[][] board = new char[3][3];    
    Timer time = new Timer();
    int sc = 0;
    int counter = 30;
    int i;
    int j;
    private Stage stage;
    private Scene scene;
    private int flag=0;
    int drawFlag=0;
    Thread th; 
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
    private Label timeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Text StateLabel;
    @FXML
    private Button btnInvisable;
     @FXML
    private ImageView drawImage;

    @FXML
    private ImageView gameOver;

    @FXML
    private ImageView happyImage;
    @FXML
    private Text tryTxt;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameLabel.setText(FXMLNameController.sName);
        Runnable task = new Runnable() {
            @Override
            public void run() {               
                time.schedule(new ReminderTask(), 1000); 
            } 
        };
        th = new Thread(task);
        
        th.start();
        
             for (i = 0; i < 3; i++)
        {   
            for(j = 0;j < 3;j++)
            {
                board[i][j] = '_';
            }
        }     
    }  



    class ReminderTask extends TimerTask {
        String str;
        @Override
        public void run() {
            
            while(counter >= 0) {
                if(sc == 10 || sc == -10 || flag>=4)
                            {
                                break;     
                            }
            if(counter >= 0 && counter <= 9)
            {
                str = "00:0" + counter;
                try {
                    if(counter == 0) 
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
                } catch (Exception e) {
                }
                
            }
            else
            {
                str = "00:" + counter;
            }
            //System.out.println(str);
            Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            
                            try {
                                timeLabel.setText(str);
                            } catch (Exception e) {
                                //System.out.println("s");
                            }
                        }     
                    });
            counter--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            time.cancel();
            th.stop();
        }
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
    void sc_lose_win(){
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            disableBtn();
            StateLabel.setStyle("-fx-text-inner-color: red;");
            StateLabel.setText("Game Over");
            btnInvisable.setVisible(true);
            gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        else if(sc == -10)// you are the winner
        {
            disableBtn();
            
            StateLabel.setStyle("-fx-text-fill:#88e439");
            StateLabel.setText("Winner");
            happyImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
        }
        for(int i = 0;i < 3;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                if(board[i][j] == '_'){
                    drawFlag=drawFlag+1;
                    //System.out.println(board[i][j]);                   
                }               
            }
            if(drawFlag==36){
                flag++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag);
        if(flag>=4)
        { 
            disableBtn();
            StateLabel.setText("Draw!!");
            btnInvisable.setVisible(true);
            drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
    }  

    private void compTurn(){
        
        TicTacToeGame.Move bestMove = findBestMove(board);
        if(bestMove.row!=-1){
        //System.out.println(bestMove.row);
        //System.out.println(bestMove.col);
        board[bestMove.row][bestMove.col] = 'O';    
        if(3*bestMove.row+bestMove.col == 0)
        {
            btn0_0.setStyle("-fx-background-color:#cf4358");
            btn0_0.setText("O");
            btn0_0.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 1)
        {
            btn0_1.setStyle("-fx-background-color:#cf4358");
            btn0_1.setText("O");
            btn0_1.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 2)
        {
            btn0_2.setStyle("-fx-background-color:#cf4358");
            btn0_2.setText("O");
            btn0_2.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 3)
        {
            btn1_0.setStyle("-fx-background-color:#cf4358");
            btn1_0.setText("O");
            btn1_0.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 4)
        {
            btn1_1.setStyle("-fx-background-color:#cf4358");
            btn1_1.setText("O");
            btn1_1.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 5)
        {
            btn1_2.setStyle("-fx-background-color:#cf4358");
            btn1_2.setText("O");
            btn1_2.setDisable(true);
        }
        
        else if(3*bestMove.row+bestMove.col == 6)
        {
            btn2_0.setStyle("-fx-background-color:#cf4358");
            btn2_0.setText("O");
            btn2_0.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 7)
        {
            btn2_1.setStyle("-fx-background-color:#cf4358");
            btn2_1.setText("O");
            btn2_1.setDisable(true);
        }
                else if(3*bestMove.row+bestMove.col == 8)
        {
            btn2_2.setStyle("-fx-background-color:#cf4358");
            btn2_2.setText("O");
            btn2_2.setDisable(true);
        }
        
        sc_lose_win();
      
    } 
    }
    @FXML
    private void btnHighlight(){      
        btn0_0.setStyle("-fx-background-color:#cf4358");
        btn0_0.setText("X");
        board[0][0] = 'X';
        btn0_0.setDisable(true);
        sc_lose_win();
        compTurn();    
    }
    @FXML
    private void btnHighlight1(){
        
        btn0_1.setStyle("-fx-background-color:#cf4358");
        btn0_1.setText("X");
        board[0][1] = 'X';
        btn0_1.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight2(){
        
        btn0_2.setStyle("-fx-background-color:#cf4358");
         btn0_2.setText("X");
        board[0][2] = 'X';
        btn0_2.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight3(){
        
        btn1_0.setStyle("-fx-background-color:#cf4358");
         btn1_0.setText("X");
        board[1][0] = 'X';
        btn1_0.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight4(){
        
        btn1_1.setStyle("-fx-background-color:#cf4358");
         btn1_1.setText("X");
        board[1][1] = 'X';
        btn1_1.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight5(){
        
        btn1_2.setStyle("-fx-background-color:#cf4358");
         btn1_2.setText("X");
        board[1][2] = 'X';
        btn1_2.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight6(){       
        btn2_0.setStyle("-fx-background-color:#cf4358");
         btn2_0.setText("X");
        board[2][0] = 'X';
        btn2_0.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight7(){
        
        btn2_1.setStyle("-fx-background-color:#cf4358");
         btn2_1.setText("X");
        board[2][1] = 'X';
        btn2_1.setDisable(true);
        sc_lose_win();
        compTurn();
    }
    @FXML
    private void btnHighlight8(){
        
        btn2_2.setStyle("-fx-background-color:#cf4358");
         btn2_2.setText("X");
         btn2_2.setDisable(true);
        board[2][2] = 'X';
        sc_lose_win();
        compTurn();
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
