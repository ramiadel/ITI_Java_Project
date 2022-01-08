/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Mo Sharshar
 */

public class Server {
    
    ServerSocket serverSocket;
    
    public Server() {
        try {
            serverSocket=new ServerSocket(12345);       
            while(true){
                Socket s = serverSocket.accept();
                
                new ChatHandler(s);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }  
    }
    
    public static void main(String[] args) {
        new Server();
    }
}