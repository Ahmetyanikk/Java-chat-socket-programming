/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server: "+ clientUsername+" konuşmaya katıldı");//burası ingilizce değişecek   
        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    
    
    
    @Override
    public void run() {
        String messageFromClient;
        
        while(socket.isConnected()){
        try{
            messageFromClient = bufferedReader.readLine();
            broadcastMessage(messageFromClient);
        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
            break;
        }
    }
   }
    
    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler: clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                    
                }
            }catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: "+clientUsername+" ayrıldı");//ingilizce yapılacak
    }
    public void closeEverything(Socket socket,BufferedReader bufferedReader,BufferedWriter bufferedWriter ){
        removeClientHandler();
        try{
            if(bufferedReader!= null ){
                bufferedReader.close();
            }
            if(bufferedWriter!= null ){
                bufferedWriter.close();
            }
            if(socket!= null ){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
