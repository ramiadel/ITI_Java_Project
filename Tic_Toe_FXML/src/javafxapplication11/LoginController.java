/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication11;

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
import java.util.ArrayList;
import java.util.Timer;
import javafx.scene.media.MediaPlayer;
import java.util.TimerTask;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MahmoudElImbabi
 */
public class LoginController implements Initializable {

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

        timer = new Timer();
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

    }

    public void cancelTimer() {

        running = false;

        timer.cancel();

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
    void one_player(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(false);
        singlePane.setVisible(true);
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

    }

    @FXML
    void sfun1(ActionEvent event) {

    }

    @FXML
    void sfun2(ActionEvent event) {

    }

    @FXML
    void sfun3(ActionEvent event) {

    }

    @FXML
    void sfun4(ActionEvent event) {

    }

    @FXML
    void sfun5(ActionEvent event) {

    }

    @FXML
    void sfun6(ActionEvent event) {

    }

    @FXML
    void sfun7(ActionEvent event) {

    }

    @FXML
    void sfun8(ActionEvent event) {

    }

    @FXML
    void multi_player(ActionEvent event) {
        LoginPane.setVisible(false);
        SignupPane.setVisible(false);
        TablePane.setVisible(false);
        menuPane.setVisible(false);
        startPane.setVisible(false);
        gamePane.setVisible(true);
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

    }


}
