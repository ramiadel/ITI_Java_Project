/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package newlib;

import java.io.DataInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Timer;
import javafx.scene.media.MediaPlayer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import static newlib.TicTacToeGame.findBestMove;

/**
 * FXML Controller class
 *
 * @author MahmoudElImbabi
 */
public class LoginController implements Initializable {
    char[][] board = new char[3][3]; 
    Timer time = new Timer();
    int sc = 0;
    int counter = 30;
    int i;
    int j;
    Thread th; 
    Vector<UserData> Ds = new Vector<UserData>();
    @FXML
    private AnchorPane LoginPane;

    @FXML
    private TableColumn<?, ?> Lose;

    @FXML
    private TableColumn<?, ?> Matches;

    @FXML
    private TableColumn<?, ?> Name;

    @FXML
    private TableColumn<?, ?> Rank;

    @FXML
    private AnchorPane SignupPane;

    @FXML
    private AnchorPane TablePane;

    @FXML
    private TableColumn<?, ?> Time;

    @FXML
    private Label Timelabel;

    @FXML
    private TableColumn<?, ?> Win;

    @FXML
    private Button btn0;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button exitbtn;

    @FXML
    private Label game;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private AnchorPane menuPane;

    @FXML
    private Button multiplayerbtn;

    @FXML
    private Button oneplayerbtn;

    @FXML
    private PasswordField password;

    @FXML
    private Button recordsbtn;

    @FXML
    private Button savebtn;

    @FXML
    private Button sbtn0;

    @FXML
    private Button sbtn1;

    @FXML
    private Button sbtn2;

    @FXML
    private Button sbtn3;

    @FXML
    private Button sbtn4;

    @FXML
    private Button sbtn5;

    @FXML
    private Button sbtn6;

    @FXML
    private Button sbtn7;

    @FXML
    private Button sbtn8;

    @FXML
    private Button settingsbtn;

    @FXML
    private Button sibtn;

    @FXML
    private AnchorPane singlePane;

    @FXML
    private Label sresult;

    @FXML
    private AnchorPane startPane;

    @FXML
    private Button startbtn;

    @FXML
    private Label stimelabel;

    @FXML
    private Label sturn;

    @FXML
    private Button subtn;

    @FXML
    private TextField suname;

    @FXML
    private PasswordField supassword;

    @FXML
    private Text turn;

    @FXML
    private TextField user;

    @FXML
    private Button aboutbtn;

    @FXML
    private Button childbtn;

    @FXML
    private Button mbackbtn;

    @FXML
    private Button sbackbtn;

    @FXML
    private Button rbackbtn;

    @FXML
    private Button setbackbtn;

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private Label musiclabel;

    @FXML
    private ProgressBar songProgressBar;

    @FXML
    private ProgressBar sprogressBar;

    @FXML
    private Button nextbtn;

    @FXML
    private Button pausebtn;

    @FXML
    private Button playbtn;

    @FXML
    private Button previousbtn;

    @FXML
    private Slider sound;

    @FXML
    private Label smusiclabel;
    @FXML
    private Button snextbtn;

    @FXML
    private Button spausebtn;

    @FXML
    private Button splaybtn;

    @FXML
    private Button spreviousbtn;

    @FXML
    private Slider ssound;

    @FXML
    private AnchorPane musicPane;
    
    @FXML
    private TextArea txtArea;
    
    @FXML
    private TextField txtMessage;
    
    @FXML
    private Button sendBtn;
    
    PrintStream ps;
    Socket mySocket;
    DataInputStream dis;
    boolean btn_flag=false;
    
    String msgrply;
    long myID;
    String symbol = "Z";
    String otherSymbol;
    String action;

   // Button game = new Button();

    int drawFlag = 0;
    private int flag2 = 0;
    
    private void btnDisable(boolean flag)
    {
        btn0.setDisable(flag);
        btn1.setDisable(flag);
        btn2.setDisable(flag);
        btn3.setDisable(flag);
        btn4.setDisable(flag);
        btn5.setDisable(flag);
        btn6.setDisable(flag);
        btn7.setDisable(flag);
        btn8.setDisable(flag);
    }

    private void sbtnDisable(boolean flag)
    {
        sbtn0.setDisable(flag);
        sbtn1.setDisable(flag);
        sbtn2.setDisable(flag);
        sbtn3.setDisable(flag);
        sbtn4.setDisable(flag);
        sbtn5.setDisable(flag);
        sbtn6.setDisable(flag);
        sbtn7.setDisable(flag);
        sbtn8.setDisable(flag);
    }

     void sc_lose_win(){
         
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            btnDisable(true);
            game.setStyle("-fx-text-inner-color: red;");
            game.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            btnDisable(true);
            sbtnDisable(true);
            game.setStyle("-fx-text-fill:#88e439");
            game.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            btnDisable(true);
            sbtnDisable(true);
            game.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
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
            sbtn0.setStyle("-fx-background-color:#f49632");
            sbtn0.setText("O");
            sbtn0.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 1)
        {
            sbtn1.setStyle("-fx-background-color:#f49632");
            sbtn1.setText("O");
            sbtn1.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 2)
        {
            sbtn2.setStyle("-fx-background-color:#f49632");
            sbtn2.setText("O");
            sbtn2.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 3)
        {
            sbtn3.setStyle("-fx-background-color:#f49632");
            sbtn3.setText("O");
            sbtn3.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 4)
        {
            sbtn4.setStyle("-fx-background-color:#f49632");
            sbtn4.setText("O");
            sbtn4.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 5)
        {
            sbtn5.setStyle("-fx-background-color:#f49632");
            sbtn5.setText("O");
            sbtn5.setDisable(true);
        }
        
        else if(3*bestMove.row+bestMove.col == 6)
        {
            sbtn6.setStyle("-fx-background-color:#f49632");
            sbtn6.setText("O");
            sbtn6.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 7)
        {
            sbtn7.setStyle("-fx-background-color:#f49632");
            sbtn7.setText("O");
            sbtn7.setDisable(true);
        }
                else if(3*bestMove.row+bestMove.col == 8)
        {
            sbtn8.setStyle("-fx-background-color:#f49632");
            sbtn8.setText("O");
            sbtn8.setDisable(true);
        }
        
        sc_lose_win();
      
    } 
    }
    @FXML
    public void next(ActionEvent event) {

        if (songNumber < songs.size() - 1) {

            songNumber++;
            mediaPlayer.stop();
            if (running) {

                cancelTimer();

            }

            media = new Media(songs.get(songNumber).toURI().toString());

            mediaPlayer = new MediaPlayer(media);

            musiclabel.setText(songs.get(songNumber).getName());
            smusiclabel.setText(songs.get(songNumber).getName());

            playMedia();
        } else {
            songNumber = 0;

            mediaPlayer.stop();
            if (running) {

                cancelTimer();

            }

            media = new Media(songs.get(songNumber).toURI().toString());

            mediaPlayer = new MediaPlayer(media);

            musiclabel.setText(songs.get(songNumber).getName());
        smusiclabel.setText(songs.get(songNumber).getName());

            playMedia();
        }

    }

    public void playMedia() {

        beginTimer();
//		mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();

    }

    @FXML
    public void pause(ActionEvent event) {
        cancelTimer();
        mediaPlayer.pause();
    }

    @FXML
    public void play(ActionEvent event) {
        playMedia();

    }

    @FXML
    public void previous(ActionEvent event) {

        if (songNumber > 0) {

            songNumber--;
            mediaPlayer.stop();

            if (running) {

                cancelTimer();

            }

            media = new Media(songs.get(songNumber).toURI().toString());

            mediaPlayer = new MediaPlayer(media);

            musiclabel.setText(songs.get(songNumber).getName());
        smusiclabel.setText(songs.get(songNumber).getName());

            playMedia();
        } else {
            songNumber = songs.size() - 1;

            mediaPlayer.stop();

            if (running) {

                cancelTimer();

            }

            media = new Media(songs.get(songNumber).toURI().toString());

            mediaPlayer = new MediaPlayer(media);

            musiclabel.setText(songs.get(songNumber).getName());
        smusiclabel.setText(songs.get(songNumber).getName());

            playMedia();
        }

    }

    @FXML
    public void snext(ActionEvent event) {

    }

    public void beginTimer() {

       /* timer = new Timer();
        task = new TimerTask() {
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();

                songProgressBar.setProgress(current / end);
                sprogressBar.setProgress(current / end);
                if (current / end == 1) {

                    cancelTimer();

                }

            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
*/
    }

    public void cancelTimer() {

        /*running = false;

        timer.cancel();*/

    }

    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean running;

    @FXML
    public void spause(ActionEvent event) {

    }

    @FXML
    public void splay(ActionEvent event) {

    }

    @FXML
    public void sprevious(ActionEvent event) {

    }
  

    public LoginController() {
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void fun0() {
        action = symbol;
        btn0.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
            btn0.setText(otherSymbol);
           
            btn_flag=false;
            btn0.setDisable(true);
        } 
        else {
            btn0.setText(symbol);
            ps.println("action 0");
            btnDisable(true);
            board[0][0] = symbol.charAt(0); 
            sc_lose_win();
        }
        
        System.out.println(board[0][0]);
        for(i = 0;i < 3;i++)
        {
            for(j = 0;j < 3;j++)
            {
                System.out.println(board[i][j]);
            }
        }    
    }

    @FXML
    void fun1() {
        action = symbol;
        btn1.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn1.setText(otherSymbol);
           
            btn_flag=false;
            btn1.setDisable(true);
        } 
        else {
            btn1.setText(symbol);
            ps.println("action 1");
            btnDisable(true);
            board[0][1] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[0][1]);
    }

    @FXML
    void fun2() {
        action = symbol;
        btn2.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn2.setText(otherSymbol);
           
            btn_flag=false;
            btn2.setDisable(true);
        } 
        else {
            btn2.setText(symbol);
            ps.println("action 2");
            btnDisable(true);
            board[0][2] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[0][2]);
    }

    @FXML
    void fun3() {
        action = symbol;
        btn3.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
                btn3.setText(otherSymbol);
            
            btn_flag=false;
            btn3.setDisable(true);
        } 
        else {
            btn3.setText(symbol);
            ps.println("action 3");
            btnDisable(true);
            board[1][0] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[1][0]);
    }

    @FXML
    void fun4() {
        action = symbol;
        btn4.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
            btn4.setText(otherSymbol);
           
            btn_flag=false;
            btn4.setDisable(true);
        } 
        else {
            btn4.setText(symbol);
            ps.println("action 4");
            btnDisable(true);
            board[1][1] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[1][1]);
    }

    @FXML
    void fun5() {
        action = symbol;
        btn5.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn5.setText(otherSymbol);
           
            btn_flag=false;
            btn5.setDisable(true);
        } 
        else {
            btn5.setText(symbol);
            ps.println("action 5");
            btnDisable(true);
            board[1][2] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[1][2]);
    }

    @FXML
    void fun6() {
        action = symbol;
        btn6.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
            btn6.setText(otherSymbol);
           
            btn_flag=false;
            btn6.setDisable(true);
        } 
        else {
            btn6.setText(symbol);
            ps.println("action 6");
            btnDisable(true);
            board[2][0] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[2][0]);
    }

    @FXML
    void fun7() {
        action = symbol;
        btn7.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn7.setText(otherSymbol);
           
            btn_flag=false;
            btn7.setDisable(true);
        } 
        else {
            btn7.setText(symbol);
            ps.println("action 7");
            btnDisable(true);
            board[2][1] = symbol.charAt(0);  
            sc_lose_win();
        }
        System.out.println(board[2][1]);
    }

    @FXML
    void fun8() {
        action = symbol;
        btn8.setStyle("-fx-background-color:#f49632");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn8.setText(otherSymbol);
           
            btn_flag=false;
            btn8.setDisable(true);
        } 
        else {
            btn8.setText(symbol);
            ps.println("action 8");
            btnDisable(true);
            board[2][2] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println(board[2][2]);
    }
@FXML
    void send(ActionEvent event) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(txtMessage.getText());
        String decoded_String = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(decoded_String);
        txtArea.appendText("\n" + decoded_String);
        ps.println(txtMessage.getText());
        txtMessage.setText("");
    } 

    @FXML
    void insert_name(ActionEvent event) {

    }

    @FXML
    void insert_password(ActionEvent event) {

    }

    @FXML
    void one_player(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        singlePane.setVisible(true);
        
        int sc = 0;
        int counter = 30;
        
        Runnable task = new Runnable() {
            @Override
            public void run() {               
                time.schedule(new LoginController.ReminderTask(), 1000); 
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

    @FXML
    void settings(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        settingsPane.setVisible(true);
        singlePane.setVisible(false);
    }

   @FXML
    void sfun0(ActionEvent event) {
        sbtn0.setStyle("-fx-background-color:#f49632");
        sbtn0.setText("X");
        board[0][0] = 'X';
        sbtn0.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun1(ActionEvent event) {
        sbtn1.setStyle("-fx-background-color:#f49632");
        sbtn1.setText("X");
        board[0][1] = 'X';
        sbtn1.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun2(ActionEvent event) {
        sbtn2.setStyle("-fx-background-color:#f49632");
        sbtn2.setText("X");
        board[0][2] = 'X';
        sbtn2.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

@FXML
    void sfun3(ActionEvent event) {
        sbtn3.setStyle("-fx-background-color:#f49632");
        sbtn3.setText("X");
        board[1][0] = 'X';
        sbtn3.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun4(ActionEvent event) {
        sbtn4.setStyle("-fx-background-color:#f49632");
        sbtn4.setText("X");
        board[1][1] = 'X';
        sbtn4.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun5(ActionEvent event) {
        sbtn5.setStyle("-fx-background-color:#f49632");
        sbtn5.setText("X");
        board[1][2] = 'X';
        sbtn5.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun6(ActionEvent event) {
        sbtn6.setStyle("-fx-background-color:#f49632");
        sbtn6.setText("X");
        board[2][0] = 'X';
        sbtn6.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun7(ActionEvent event) {
        sbtn7.setStyle("-fx-background-color:#f49632");
        sbtn7.setText("X");
        board[2][1] = 'X';
        sbtn7.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }

    @FXML
    void sfun8(ActionEvent event) {
        sbtn8.setStyle("-fx-background-color:#f49632");
        sbtn8.setText("X");
        board[2][2] = 'X';
        sbtn8.setDisable(true);
        //sc_lose_win();
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
            //btnInvisable.setVisible(true);
            //gameOver.setVisible(true);
            
            //System.out.println(flag);
            th.suspend();
            th.stop();
            time.cancel();
        }
        if(sc == -10)// you are the winner
        {
            sbtnDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            //happyImage.setVisible(true);
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
                flag2++;
            }
        }
        //System.out.println(drawFlag);
        System.out.println(flag2);
        if(flag2>=4)
        { 
            sbtnDisable(true);
            sresult.setText("Draw!!");
            //btnInvisable.setVisible(true);
            //drawImage.setVisible(true);
            th.suspend();
            time.cancel();
            th.stop();
         }          
       //System.out.println(sc);     
        compTurn();    
    }


    @FXML
    void multi_player(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(true);
        
        Runnable task1 = new Runnable() {
            @Override
            public void run() {               
                time.schedule(new LoginController.ReminderTask1(), 1000); 
            } 
        };
        th = new Thread(task1);
        th.start();
         new Thread(new Runnable() {
           @Override
           public void run() {
                while(true) {
                    
                    try {
                        msgrply = dis.readLine();
                        System.out.println("Recievd: " + msgrply);
                        //id = Integer.parseInt(msgrply.split(" ")[0]);
                        if(msgrply.startsWith("action")) {
                            int btn_index;
                            btn_index = Integer.parseInt(msgrply.split(" ")[1]);
                            System.out.println("Parsed Action: " + btn_index);
                            btnDisable(false);
                            switch(btn_index) {
                                case 0:
                                    board[0][0] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun0();
                                    break;
                                case 1:
                                    board[0][1] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun1();
                                    break;
                                case 2:
                                    board[0][2] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun2();
                                    break;
                                case 3:
                                    board[1][0] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun3();
                                    break;
                                case 4:
                                    board[1][1] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun4();
                                    break;
                                case 5:
                                    board[1][2] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun5();
                                    break;
                                case 6:
                                    board[2][0] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun6();
                                    break;
                                case 7:
                                    board[2][1] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun7();
                                    break;
                                case 8:
                                    board[2][2] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    fun8();
                                    break;
                                default:
                                    System.out.println("Invalid Index");
                            }
                        }
                        else {
                            txtArea.appendText("\n" + msgrply);
                        }  
                    } catch (IOException ex) {
                        try {
                            dis.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        ps.close();
                        try {
                            mySocket.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        
                        ex.getStackTrace();
                    }
                }
            }
        }).start();
        
        for (i = 0; i < 3; i++)
        {   
            for(j = 0;j < 3;j++)
            {
                board[i][j] = '_';
            } 
        }
    }

    @FXML
    void records(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(true);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
    }

    @FXML
    void start(ActionEvent event) {
        LoginPane.setVisible(true);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        sibtn.setDefaultButton(true);
    }

    @FXML
    void save(ActionEvent event) {
        LoginPane.setVisible(true);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        UserData Temp = new UserData();
        if (!suname.getText().isEmpty() && !supassword.getText().isEmpty()) {
            Temp.Name = suname.getText();
            Temp.Password = supassword.getText();
            Temp.Lose = 0;
            Temp.Matches = 0;
            Temp.Time = 0;
            Temp.Win = 0;

        }
        Ds.add(Temp);
        LoginPane.setVisible(true);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);

    }

    @FXML
    void sign_in(ActionEvent event) {
        for (UserData i : Ds) {
            if ((i.Name.equals(user.getText())) && i.Password.equals(password.getText())) {
                System.out.print("Logged In");
                LoginPane.setVisible(false);
                SignupPane.setVisible(false);
                TablePane.setVisible(false);
                menuPane.setVisible(true);
                startPane.setVisible(false);
                gamePane.setVisible(false);
                settingsPane.setVisible(false);
                singlePane.setVisible(false);
            }
        }
    }

    @FXML
    void sign_up(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(true);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        settingsPane.setVisible(false);
        singlePane.setVisible(false);
        savebtn.setDefaultButton(true);
    }

    @FXML
    void about(ActionEvent event) {

    }

    @FXML
    void child(ActionEvent event) {

    }

    @FXML
    void mback(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(true);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        settingsPane.setVisible(false);
        singlePane.setVisible(false);
    }

    @FXML
    void rback(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(true);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        settingsPane.setVisible(false);
        singlePane.setVisible(false);
    }

    @FXML
    void sback(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(true);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        settingsPane.setVisible(false);
        singlePane.setVisible(false);
    }

    @FXML
    void setback(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(true);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        settingsPane.setVisible(false);
        singlePane.setVisible(false);
    }
    @FXML
    void VolumeDrage(MouseEvent event) {
        mediaPlayer.setVolume(sound.getValue() * 0.01);			

    }
    @FXML
    void SVolumeDrage(MouseEvent event) {
        mediaPlayer.setVolume(ssound.getValue() * 0.01);			

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(true);
        gamePane.setVisible(false);
        settingsPane.setVisible(false);
        
        sendBtn.setDefaultButton(true);
        
        songs = new ArrayList<File>();

        directory = new File("music");

        files = directory.listFiles();

        if (files != null) {

            for (File file : files) {

                songs.add(file);
            }
        }
        media = new Media(songs.get(0).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(100);
        musiclabel.setText(songs.get(songNumber).getName());
        smusiclabel.setText(songs.get(songNumber).getName());
        songProgressBar.setStyle("-fx-accent: #00FF00;");
        sprogressBar.setStyle("-fx-accent: #00FF00;");
        
 try {
            mySocket = new Socket("127.0.0.1", 12345);    
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            // read one time to get my id from the server
            myID = Long.parseLong(dis.readLine());
            System.out.println("My ID = " + myID);
            if(myID%2 != 0){
                symbol = "X";
                otherSymbol = "O";
            } else{
                symbol = "O";
                otherSymbol = "X";
            }
            System.out.println("My Symbol: " + symbol);
            System.out.println("other Symbol: " + otherSymbol);

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    class ReminderTask extends TimerTask {
        String str;
        @Override
        public void run() {
            
            while(counter >= 0) {
                if(sc == 10 || sc == -10 || flag2>=4)
                {
                    break;     
                }
            if(counter >= 0 && counter <= 9)
            {
                str = "00:0" + counter;
                try {
                    if(counter == 0) 
                    {
                        sbtnDisable(true);
                    }
                } catch (Exception e) {
                }
            }
            else
            {
                str = "00:" + counter;
            }
            Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            
                            try {
                                stimelabel.setText(str);
                            } catch (Exception e) {
                            }
                        }     
                    });
            counter--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            time.cancel();
            th.stop();
        }
    }    
    class ReminderTask1 extends TimerTask {
        String str;
        @Override
        public void run() {
            
            while(counter >= 0) {
                if(sc == 10 || sc == -10)
                {
                    break;     
                }
            if(counter >= 0 && counter <= 9)
            {
                str = "00:0" + counter;
                try {
                    if(counter == 0) 
                    {
                        btnDisable(true);
                    }
                } catch (Exception e) {
                }
                
            }
            else
            {
                str = "00:" + counter;
            }
            Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            
                            try {
                                Timelabel.setText(str);
                            } catch (Exception e) {
                            }
                        }     
                    });
            counter--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            time.cancel();
            th.stop();
        }
    }    
}



