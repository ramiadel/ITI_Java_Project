/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomapp;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mo Sharshar
 */

public class ChatHandler extends Thread {
    
    DataInputStream dis;
    PrintStream ps;
    long myID;
    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();
    
    public ChatHandler(Socket cs) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            clientsVector.add(this);
            myID = this.getId();
            
            // send the ID to its Client one time at the beggining
            ps.println(myID);
            start();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    /*
        if received message begins with "action" > played, then we send the index of square played in
        else > it is a chat messaage, then we send the message 
    */
    public void run() {
        while(true) {
            try {
                String str=dis.readLine();
                System.out.println(this.getId() + " sent: " + str);
                if(str.startsWith("action")) {
                    sendMessageToAll(str);
                }
                else {
                    sendMessageToAll(str);
                }
            } catch (IOException ex) {
                ex.getStackTrace();
            }
        }
    }
    public void sendMessageToAll(String str) {
        for(ChatHandler ch:clientsVector){
            if(ch.getId() != this.getId())
                ch.ps.println(str + " " + this.getId());
        }
    }
}
