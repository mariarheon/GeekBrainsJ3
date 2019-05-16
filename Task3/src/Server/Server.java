
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Java 3. Lesson3. I/O
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */

public class Server {
    
    private List<ClientHandler> clientHandlers = new ArrayList<>();
    
    public Server() {
        ServerSocket serverSocket = null;
         Socket clientSocket = null;
        try{
            serverSocket = new ServerSocket(8080);
            System.out.println("Server launched");
        
            while (true){
               clientSocket = serverSocket.accept();
               ClientHandler client = new ClientHandler(clientSocket, this);
              clientHandlers.add(client);
              new Thread(client).start();
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                serverSocket.close();
                clientSocket.close();
                System.out.println("Server stopped");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void notificationAllClientsWithNewMessage(String msg){
        for(ClientHandler clientHandler : clientHandlers){
            clientHandler.sendMessage(msg);
        }

    }
    public void removeClient(ClientHandler clientHandler){
        clientHandlers.remove(clientHandler);
    }

}
