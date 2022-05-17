/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Ahmet
 */
public class ClientHandler implements Runnable {
    
    public static ArrayList<ClientHandler>clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    
    
    public ClientHandler(Socket socket){
        try{
            this.socket=socket;
        }
    }
    
    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
