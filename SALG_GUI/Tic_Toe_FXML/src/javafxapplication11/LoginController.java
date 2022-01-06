/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication11;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import static javafxapplication11.TicTacToeGame.findBestMove;

/**
 * FXML Controller class
 *
 * @author MahmoudElImbabi
 */
public class LoginController implements Initializable {
    
    char[][] board = new char[3][3];
    Timer time = new Timer();
    int counter = 15;
    int sc = 0;
    int i;
    int j;
    Thread th; 
    String path = "motivation.mp3";
    Media media =  new Media(new File(path).toURI().toString());
    MediaPlayer mediaplayer = new MediaPlayer(media);
    
    Vector<UserData> Ds = new Vector<UserData>();
    
    @FXML
    private AnchorPane LoginPane;

    @FXML
    private TableColumn<?, ?> Lose;

    @FXML
    private TableColumn<?, ?> Matches;

    @FXML
    private Label Musiclabel;

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
    private Button nextbtn;

    @FXML
    private Button oneplayerbtn;

    @FXML
    private PasswordField password;

    @FXML
    private Button pausebtn;

    @FXML
    private Button playbtn;

    @FXML
    private Button previousbtn;

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
    private Button snextbtn;

    @FXML
    private Button spausebtn;

    @FXML
    private Button splaybtn;

    @FXML
    private Button spreviousbtn;

    @FXML
    private Label sresult;

    @FXML
    private Slider ssound;

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
    private Slider volume;

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
    
    void time() {
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
    
    int drawFlag=0;
    void sc_lose_win(){
        sc = TicTacToeGame.evaluate(board);
        if(sc == 10)// you are the loser
        {
            sbtn0.setDisable(true);
            sbtn1.setDisable(true);
            sbtn2.setDisable(true);
            sbtn3.setDisable(true);
            sbtn4.setDisable(true);
            sbtn5.setDisable(true);
            sbtn6.setDisable(true);
            sbtn7.setDisable(true);
            sbtn8.setDisable(true);
            sresult.setStyle("-fx-text-inner-color: red;");
            sresult.setText("Game Over");
          //  btnInvisable.setVisible(true);
            th.suspend();
            th.stop();
            time.cancel();
        }
        else if(sc == -10)// you are the winner
        {
            sbtn0.setDisable(true);
            sbtn1.setDisable(true);
            sbtn2.setDisable(true);
            sbtn3.setDisable(true);
            sbtn4.setDisable(true);
            sbtn5.setDisable(true);
            sbtn6.setDisable(true);
            sbtn7.setDisable(true);
            sbtn8.setDisable(true);
            sresult.setStyle("-fx-text-fill:#88e439");
            sresult.setText("Winner");
            time.cancel();
            th.stop();
        }
        for(int i = 0;i < 3;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                drawFlag=0;
                if(board[i][j] == '_'){
                    drawFlag=1;
            }   
        } 
    }
        if(drawFlag==0){
            sresult.setText("Draw!!");
        //  btnInvisable.setVisible(true);  
         }
    }  

    private void compTurn(){    
        TicTacToeGame.Move bestMove = findBestMove(board);
        board[bestMove.row][bestMove.col] = 'o';    
        if(3*bestMove.row+bestMove.col == 0)
        {
            sbtn0.setText("O");
            sbtn0.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 1)
        {
            sbtn1.setText("O");
            sbtn1.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 2)
        {
            sbtn2.setText("O");
            sbtn2.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 3)
        {
            sbtn3.setText("O");
            sbtn3.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 4)
        {
            sbtn4.setText("O");
            sbtn4.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 5)
        {
            sbtn5.setText("O");
            sbtn5.setDisable(true);
        }
        
        else if(3*bestMove.row+bestMove.col == 6)
        {
            sbtn6.setText("O");
            sbtn6.setDisable(true);
        }
        else if(3*bestMove.row+bestMove.col == 7)
        {
            sbtn7.setText("O");
            sbtn7.setDisable(true);
        }
                else if(3*bestMove.row+bestMove.col == 8)
        {
            sbtn8.setText("O");
            sbtn8.setDisable(true);
        }
        sc_lose_win();
      
    }  

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void fun0(ActionEvent event) {
        

    }

    @FXML
    void fun1(ActionEvent event) {

    }

    @FXML
    void fun2(ActionEvent event) {

    }

    @FXML
    void fun3(ActionEvent event) {

    }

    @FXML
    void fun4(ActionEvent event) {

    }

    @FXML
    void fun5(ActionEvent event) {

    }

    @FXML
    void fun6(ActionEvent event) {

    }

    @FXML
    void fun7(ActionEvent event) {

    }

    @FXML
    void fun8(ActionEvent event) {
        
    }

    @FXML
    void insert_name(ActionEvent event) {

    }

    @FXML
    void insert_password(ActionEvent event) {

    }

    @FXML
    void next(ActionEvent event) {
        
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
        time();
    }

    @FXML
    void pause(ActionEvent event) {
        mediaplayer.pause();
    }

    @FXML
    void play(ActionEvent event) {
        mediaplayer.play();
    }

    @FXML
    void previous(ActionEvent event) {

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
        sbtn0.setText("X");
        board[0][0] = 'x';
        sbtn0.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun1(ActionEvent event) {
        sbtn1.setText("X");
        board[0][1] = 'x';
        sbtn1.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun2(ActionEvent event) {
        sbtn2.setText("X");
        board[0][2] = 'x';
        sbtn2.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

@FXML
    void sfun3(ActionEvent event) {
        sbtn3.setText("X");
        board[1][0] = 'x';
        sbtn3.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun4(ActionEvent event) {
        sbtn4.setText("X");
        board[1][1] = 'x';
        sbtn4.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun5(ActionEvent event) {
        sbtn5.setText("X");
        board[1][2] = 'x';
        sbtn5.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun6(ActionEvent event) {
        sbtn6.setText("X");
        board[2][0] = 'x';
        sbtn6.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun7(ActionEvent event) {
        sbtn7.setText("X");
        board[2][1] = 'x';
        sbtn7.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void sfun8(ActionEvent event) {
        sbtn8.setText("X");
        board[2][2] = 'x';
        sbtn8.setDisable(true);
        sc_lose_win();
        compTurn();    
    }

    @FXML
    void snext(ActionEvent event) {
        
        
    }

    @FXML
    void spause(ActionEvent event) {
        mediaplayer.pause();
    }

    @FXML
    void splay(ActionEvent event) {
        mediaplayer.play();
    }

    @FXML
    void sprevious(ActionEvent event) {

    }

    @FXML
    void multi_player(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);   
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(true); 
        time();
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
        if(!suname.getText().isEmpty() && !supassword.getText().isEmpty()){
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
    for (UserData i : Ds ){
        if((i.Name.equals(user.getText()) ) && i.Password.equals(password.getText()) ){
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);   
        menuPane.setVisible(false);
        startPane.setVisible(true);
        gamePane.setVisible(false); 
        settingsPane.setVisible(false);
    }   
    
    class ReminderTask extends TimerTask {
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
                    sbtn0.setDisable(true);
                    sbtn1.setDisable(true);
                    sbtn2.setDisable(true);
                    sbtn3.setDisable(true);
                    sbtn4.setDisable(true);
                    sbtn5.setDisable(true);
                    sbtn6.setDisable(true);
                    sbtn7.setDisable(true);
                    sbtn8.setDisable(true);
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
}
 