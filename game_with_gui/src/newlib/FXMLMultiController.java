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
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/*
 * FXML Controller class
 *
 * @author yasmi
 */
public class FXMLMultiController implements Initializable {
    PrintStream ps;
    Socket mySocket;
    DataInputStream dis;
    boolean btn_flag=false;
    int i,j;
    String msgrply;
    long myID;
    String symbol = "Z";
    String otherSymbol;
    String action;
    Timer time = new Timer();
    Thread th = new Thread(); 
    int counter = 100;
    Button game = new Button();
    char[][] board = new char[3][3];  
    int sc = 0;
    int drawFlag = 0;

    
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
    private TextField txtMessage;
    @FXML
    private Label timeLabel;
    @FXML
    private Label timeLabel1;
    @FXML
    private Label fNameL;
    @FXML
    private Text yNameL;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button sendBtn;
    @FXML
    private Label GameResult;
    
    void sc_lose_win(){
        sc = TicTacToeGame.evaluate(board, symbol.charAt(0));
        if(sc == -10)// you are the loser
        {
            btnDisable(true);
            GameResult.setStyle("-fx-text-inner-color: red;");
            GameResult.setText("Game Over");
            th.suspend();
            th.stop();
            time.cancel();
        }
        else if(sc == 10)// you are the winner
        {
            btnDisable(true);
            GameResult.setStyle("-fx-text-fill:#88e439");
            GameResult.setText("Winner");      
            time.cancel();
            th.stop();
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                    System.out.println("lw board: " + board[i][j]);                                 
            }
        }
  
       System.out.println("Score: " + sc);     
    }  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        sendBtn.setDefaultButton(true);
        Runnable task = new Runnable() {
            @Override
            public void run() {               
                time.schedule(new FXMLMultiController.ReminderTask(), 1000); 
            } 
        };
        th = new Thread(task);
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
                                    btnHighlight();
                                    break;
                                case 1:
                                    board[0][1] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight1();
                                    break;
                                case 2:
                                    board[0][2] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight2();
                                    break;
                                case 3:
                                    board[1][0] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight3();
                                    break;
                                case 4:
                                    board[1][1] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight4();
                                    break;
                                case 5:
                                    board[1][2] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight5();
                                    break;
                                case 6:
                                    board[2][0] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight6();
                                    break;
                                case 7:
                                    board[2][1] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight7();
                                    break;
                                case 8:
                                    board[2][2] = otherSymbol.charAt(0);
                                    sc_lose_win();
                                    btn_flag = true;
                                    btnHighlight8();
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
                            Logger.getLogger(FXMLMultiController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        ps.close();
                        try {
                            mySocket.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(FXMLMultiController.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        
                        ex.getStackTrace();
                    }
                }
            }
        }).start();
        
        // Initializing board values
        for (i = 0; i < 3; i++)
        {   
            for(j = 0;j < 3;j++)
            {
                board[i][j] = '_';
            }
        }     
    }    

    @FXML
    private void btnHighlight(){      
        action = symbol;
        btn0_0.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
            btn0_0.setText(otherSymbol);
           
            btn_flag=false;
            btn0_0.setDisable(true);
        } 
        else {
            btn0_0.setText(symbol);
            ps.println("action 0");
            btnDisable(true);
            board[0][0] = symbol.charAt(0); 
            sc_lose_win();
        }
        
        System.out.println("Board[0][0] = " + board[0][0] + "\nBoard: ");
        
        for(i = 0;i < 3;i++)
        {
            for(j = 0;j < 3;j++)
            {
                System.out.println(board[i][j]);
            }
        }    
    }

    @FXML
    private void btnHighlight3() {
        action = symbol;
        btn1_0.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
                btn1_0.setText(otherSymbol);
            
            btn_flag=false;
            btn1_0.setDisable(true);
        } 
        else {
            btn1_0.setText(symbol);
            ps.println("action 3");
            btnDisable(true);
            board[1][0] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[1][0] = " + board[1][0]);
    }

    @FXML
    private void btnHighlight6() {
        action = symbol;
        btn2_0.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
                btn2_0.setText(otherSymbol);
           
            btn_flag=false;
            btn2_0.setDisable(true);
        } 
        else {
            btn2_0.setText(symbol);
            ps.println("action 6");
            btnDisable(true);
            board[2][0] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[2][0] = " + board[2][0]);
    }

    @FXML
    private void btnHighlight1() {
        action = symbol;
        btn0_1.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn0_1.setText(otherSymbol);
           
            btn_flag=false;
            btn0_1.setDisable(true);
        } 
        else {
            btn0_1.setText(symbol);
            ps.println("action 1");
            btnDisable(true);
            board[0][1] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[0][1] = " + board[0][1]);
    }

    @FXML
    private void btnHighlight2() {
        action = symbol;
        btn0_2.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn0_2.setText(otherSymbol);
           
            btn_flag=false;
            btn0_2.setDisable(true);
        } 
        else {
            btn0_2.setText(symbol);
            ps.println("action 2");
            btnDisable(true);
            board[0][2] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[0][2]: " + board[0][2]);
    }

    @FXML
    private void btnHighlight4() {
        action = symbol;
        btn1_1.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 
            
            btn1_1.setText(otherSymbol);
           
            btn_flag=false;
            btn1_1.setDisable(true);
        } 
        else {
            btn1_1.setText(symbol);
            ps.println("action 4");
            btnDisable(true);
            board[1][1] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[1][1] = " + board[1][1]);
    }

    @FXML
    private void btnHighlight7() {
        action = symbol;
        btn2_1.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn2_1.setText(otherSymbol);
           
            btn_flag=false;
            btn2_1.setDisable(true);
        } 
        else {
            btn2_1.setText(symbol);
            ps.println("action 7");
            btnDisable(true);
            board[2][1] = symbol.charAt(0);  
            sc_lose_win();
        }
        System.out.println("Board[2][1] = " + board[2][1]);
    }

    @FXML
    private void btnHighlight5() {
        action = symbol;
        btn1_2.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn1_2.setText(otherSymbol);
           
            btn_flag=false;
            btn1_2.setDisable(true);
        } 
        else {
            btn1_2.setText(symbol);
            ps.println("action 5");
            btnDisable(true);
            board[1][2] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[1][2] = " + board[1][2]);
    }

    @FXML
    private void btnHighlight8() {
        action = symbol;
        btn2_2.setStyle("-fx-background-color:#cf4358");
        if(btn_flag) {
            action = msgrply.split(" ")[1];
            
            System.out.println("Msgrpy: " + msgrply);
            System.out.println("action: " + action); 

            btn2_2.setText(otherSymbol);
           
            btn_flag=false;
            btn2_2.setDisable(true);
        } 
        else {
            btn2_2.setText(symbol);
            ps.println("action 8");
            btnDisable(true);
            board[2][2] = symbol.charAt(0);
            sc_lose_win();
        }
        System.out.println("Board[2][2] = " + board[2][2]);
    }
    
    private void btnDisable(boolean flag)
    {
        btn0_0.setDisable(flag);
        btn0_1.setDisable(flag);
        btn0_2.setDisable(flag);
        btn1_0.setDisable(flag);
        btn1_1.setDisable(flag);
        btn1_2.setDisable(flag);
        btn2_0.setDisable(flag);
        btn2_1.setDisable(flag);
        btn2_2.setDisable(flag);
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
    void game(ActionEvent event) {
        
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
}
