/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;

/**
 *
 * @author Ahmet
 */
public class Server {
    private  ServerSocket serverSocket;
    public static ArrayList<String>usernames = new ArrayList<>();
    private BufferedReader bufferedReader;
    

    
    public Server(ServerSocket serverSocket){
        this.serverSocket= serverSocket;
    }
    
    public void startServer(){
        

        try{
            while(!serverSocket.isClosed()){
               Socket socket = serverSocket.accept();
             String  msgFromGroupChat = bufferedReader.readLine();
             usernames.set(usernames.size(),msgFromGroupChat);
                System.out.println("Yeni birisi katıldı muhabbete");//burası ingilizce olucak
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread= new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){
            
        }
            
        }
    
    public void closeServerSocket(){
        try{
            if(serverSocket !=null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
    }
    
    
    
    
    

