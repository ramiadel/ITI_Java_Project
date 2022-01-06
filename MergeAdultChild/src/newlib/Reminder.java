/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newlib;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class Reminder {
    Timer time;
    int counter;
    
    public Reminder (int seconds) {
        time = new Timer();
        counter = seconds;
        while(counter >= 1) {
        time.schedule(new RemindTask(),1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        time.cancel();
    }
    
    class RemindTask extends TimerTask {
        @Override
        public void run() {
            if(counter >= 0 && counter <= 9)
            {
                System.out.println("00:0" + counter);
            }
            else {
                System.out.println("00:" + counter);
            }
            counter--;
           // time.cancel();
        }
    }
    
    /*public static void main(String args[]) {
     new Reminder(15);
     //System.out.println("Task scheduled.");
    }*/
}
