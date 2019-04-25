package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Java 3. Lesson3. I/O
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */

class ClientHandler implements Runnable {

    private Socket clientSocket;
    private Server server;
    private PrintWriter outMsg;
    private Scanner inMsg;
    private String nick;
    private static int clientCount = 0;

    public ClientHandler(Socket clientSocket, Server server) {
        try {
            clientCount++;

            this.clientSocket = clientSocket;
            this.server = server;
            this.outMsg = new PrintWriter(clientSocket.getOutputStream());
            this.inMsg = new Scanner(clientSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error connecting client");
        }

    }

    @Override
    public void run() {
        try {

            server.notificationAllClientsWithNewMessage("New client in chat");
            server.notificationAllClientsWithNewMessage("In our chat client count = " + clientCount);
            while (true) {
                if (inMsg.hasNext()) {
                    String clientMsg = inMsg.nextLine();
                    if (clientMsg.equalsIgnoreCase("quit")) {
                        break;
                    }
                    System.out.println(clientMsg);
                    server.notificationAllClientsWithNewMessage(clientMsg);

                }
            }
            Thread.sleep(1000);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            exitFromChat();
            server.notificationAllClientsWithNewMessage("Client has left the chat");
        }

    }

    public void sendMessage(String msg) {
        try {
            outMsg.println(msg);
            outMsg.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void exitFromChat() {
        clientCount--;
        server.notificationAllClientsWithNewMessage("In our chat client count = " + clientCount);
        server.removeClient(this);
    }
}
