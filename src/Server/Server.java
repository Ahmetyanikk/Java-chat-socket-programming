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
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Ahmet
 */
public class Server {
    private  ServerSocket serverSocket;
    public static ArrayList<String>usernames = new ArrayList<>();
    private BufferedWriter bufferedWriter;
    

    
    public Server(ServerSocket serverSocket){
        this.serverSocket= serverSocket;
 
    }
    
    public void startServer(){
    

        try{
            while(!serverSocket.isClosed()){
                      int i=0;
               Socket socket = serverSocket.accept();
               bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                System.out.println("Yeni birisi katıldı muhabbete");//burası ingilizce olucak
                
                ClientHandler clientHandler = new ClientHandler(socket);
              
                while(i<ClientHandler.clientHandlers.size()){
                String messageToSend = ClientHandler.clientHandlers.get(i).clientUsername;
                    System.out.println(messageToSend);
                bufferedWriter.write("|"+messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                i++;
                }
               

                Thread thread= new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){
            
        }
            
        }
    public void sendUsernames(){
        
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
    
    
    
    
    

