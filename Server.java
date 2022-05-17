/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ahmet
 */
public class Server {
    private  ServerSocket serverSocket;
    
    public Server(ServerSocket serverSocket){
        this.serverSocket= serverSocket;
    }
    
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                
               Socket socket = serverSocket.accept();
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
    public static void main(String[] args) {
        int port=1234;
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        server.startServer();
    }
    }
    
    
    
    
    

