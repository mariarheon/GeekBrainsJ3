package Client;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

/**
 * Java 3. Lesson3. I/O
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */
public class Client extends JFrame {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    private static final File FILE_NAME = new File("C:\\Users\\Univer\\Desktop\\4курс\\Java\\GeekBrainsJ3\\Task3\\chat_log.txt");

    private Socket clientSocket;
    private Scanner inMsg;
    private PrintWriter outMsg;

    private String username;

    private JTextField jtfMsg;
    private JTextArea jtaAreaMsg;

    private FileInputStream fis;
    private FileOutputStream fos;
    
    public Client(String username) throws HeadlessException {
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMsg = new Scanner(clientSocket.getInputStream());
            outMsg = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            fos = new FileOutputStream(FILE_NAME, true);
            fis = new FileInputStream(FILE_NAME);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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

        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String str;
        try {
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int size = (lines.size() > 100) ? lines.size() - 100 : 0; 
        for (int i = size; i < lines.size(); i++){
            jtaAreaMsg.append(lines.get(i) + "\n");
        }
       
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
        try {
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String msg_n = msg + "\n";
        outMsg.println(msg);
        outMsg.flush();
        try {
            fos.write(msg_n.getBytes());
            fos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtfMsg.setText("");
    }

}
