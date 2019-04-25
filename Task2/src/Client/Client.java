package Client;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/** Java 3. Lesson 2. Databases
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */

public class Client extends JFrame {

    private final String SERVER_HOST = "localhost";
    private final int SERVER_PORT = 8080;
    private Socket clientSocket;
    private Scanner inMsg;
    private PrintWriter outMsg;

    private boolean login;
    private long time;
    private String username;

    private JTextField jtfMsg;
    private JTextArea jtaAreaMsg;

    public Client(String username) throws HeadlessException {
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            time = System.currentTimeMillis();
            login = false;
            inMsg = new Scanner(clientSocket.getInputStream());
            outMsg = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.username = username;
        setBounds(500, 300, 500, 400);
        setTitle("client of chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jtaAreaMsg = new JTextArea();
        jtaAreaMsg.setEditable(false);
        jtaAreaMsg.setLineWrap(true);

        JScrollPane jScrollPane = new JScrollPane(jtaAreaMsg);
        add(jScrollPane, BorderLayout.CENTER);

        JLabel labelCountOfClients = new JLabel("In our chat client count = ");
        add(labelCountOfClients, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);

        JButton sendButton = new JButton("SEND");
        bottomPanel.add(sendButton, BorderLayout.EAST);

        jtfMsg = new JTextField("Enter your message:");
        bottomPanel.add(jtfMsg, BorderLayout.CENTER);

        jtfMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = jtfMsg.getText().trim();
                String name = username;

                if (!msg.isEmpty() && !name.isEmpty()) {
                    sendMsg();
                    jtfMsg.grabFocus();
                }
            }
        });

        
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = jtfMsg.getText().trim();
                String name = username;

                if (!msg.isEmpty() && !name.isEmpty()) {
                    sendMsg();
                    jtfMsg.grabFocus();
                }
            }
        });

        jtfMsg.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMsg.setText("");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (inMsg.hasNext()) {
                        String msg = inMsg.nextLine();
                        String clientInChat = "In our chat client count = ";
                        if (msg.indexOf(clientInChat) == 0) {
                            labelCountOfClients.setText(msg);
                        } else {
                            jtaAreaMsg.append(msg);
                            jtaAreaMsg.append("\n");
                        }
                    }
                }
            }
        }
        ).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                outMsg.println(username + " exited from chat");

                clientClose();
                try {
                    clientSocket.close();
                } catch (IOException ex) {

                    ex.printStackTrace();
                }

            }
        });
        setVisible(true);

    }

    private void clientClose() {

        outMsg.println("quit");
        outMsg.flush();
        outMsg.close();
        inMsg.close();
        try {
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Error closing socket");;
        }
    }

    private void sendMsg() {       
        DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
        Date date = new Date();

        String msg = dateFormat.format(date) + ". " + username + ": " + jtfMsg.getText();
        outMsg.println(msg);
        outMsg.flush();
        jtfMsg.setText("");
    }

}
