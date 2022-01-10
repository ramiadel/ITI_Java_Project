/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newlib;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

//////////////////////
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/////////////////////

/**
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
    private int flag = 0;
    
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
    private Button sendBtn;
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
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
   
    void sc_lose_win(){
        sc = TicTacToeGame.evaluate(board,symbol.charAt(0));
        if(sc == -10)// you are the loser
        {
            btnDisable(true);
            stateLabel.setStyle("-fx-text-inner-color: red;");
            stateLabel.setText("Game Over");
            stateLabel.setVisible(true);
           //btnInvisable.setVisible(true);
            th.suspend();
            th.stop();
            time.cancel();
        }
        else if(sc == 10)// you are the winner
        {
            btnDisable(true);
            stateLabel.setStyle("-fx-text-fill:#88e439");
            stateLabel.setText("Winner");
            stateLabel.setVisible(true);
            time.cancel();
            th.stop();
        }
        for(int i = 0;i < 3;i++)
        {
            for(int j = 0;j < 3;j++)
            {
                    System.out.println("board" + board[i][j]);                                 
            }
        }
//        System.out.println(drawFlag);
//        System.out.println(flag);
//        if(flag == 4)
//        { 
//            btnDisable(true);
//            stateLabel.setText("Draw!!");
//           // btnInvisable.setVisible(true);
//            time.cancel();
//            th.stop();
//         }  
       System.out.println(sc);     
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
                        
                        //if(msgrply){
                        //}
                        /*
                        else{
                            btn00_flag = true;
                            
                            try{
                            //btn0_0.setText(msgrply);
                            } catch(IllegalStateException ex){
                                Logger.getLogger(FXMLMultiController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //btn0_0.setText(msgrply);
                        } 
                        */   
                        
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
        
        for (i = 0; i < 3; i++)
        {   
            for(j = 0;j < 3;j++)
            {
                board[i][j] = '_';
            }
        }     
    }    
      @FXML
    void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);       
        stage.show();

    }
//throws IOException
    @FXML
    private void btnHighlight()throws IOException{      
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
    private void btnHighlight3()throws IOException {
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
        System.out.println(board[1][0]);
    }

    @FXML
    private void btnHighlight1() throws IOException{
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
        System.out.println(board[0][1]);
    }

    @FXML
    private void btnHighlight2() throws IOException{
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
        System.out.println(board[0][2]);
    }

    @FXML
    private void btnHighlight4()throws IOException {
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
        System.out.println(board[1][1]);
    }
    
    @FXML
    private void btnHighlight5()throws IOException {
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
        System.out.println(board[1][2]);
    }

    @FXML
    private void btnHighlight6() throws IOException{
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
        System.out.println(board[2][0]);
    }
    
    @FXML
    private void btnHighlight7()throws IOException {
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
        System.out.println(board[2][1]);
    }

    @FXML
    private void btnHighlight8()throws IOException {
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
        System.out.println(board[2][2]);
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
        //txtArea.appendText("\n" + decoded_String);
        ps.println(txtMessage.getText());
      
        txtMessage.setText("");

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
