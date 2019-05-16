package Authotization;

import Client.Client;
import Database.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/** Java 3. Lesson3. I/O
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */

public class SignIn extends JFrame {

    String username, pass;
    JLabel jlUsername, jlPassword;
    JTextField jtfUsername;
    JPasswordField jpfPassword;
    JButton jbSignIn, jbBack;

    public SignIn() {
        JFrame frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jbBack = new JButton("Back");
        jbBack.setBounds(10, 10, 80, 40);
        frame.add(jbBack);

        jlUsername = new JLabel("Username:");
        jlUsername.setBounds(20, 60, 80, 40);
        frame.add(jlUsername);

        jlPassword = new JLabel("Password:");
        jlPassword.setBounds(20, 110, 80, 40);
        frame.add(jlPassword);

        jtfUsername = new JTextField("username");
        jtfUsername.setBounds(150, 60, 300, 40);
        frame.add(jtfUsername);

        jpfPassword = new JPasswordField("password");
        jpfPassword.setBounds(150, 110, 300, 40);
        frame.add(jpfPassword);

        jbSignIn = new JButton("Sign in");
        jbSignIn.setBounds(150, 160, 150, 40);
        frame.add(jbSignIn);

        jtfUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfUsername.setText("");
            }
        });

        jpfPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jpfPassword.setText("");
            }
        });

        jbBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();

            }
        });

        jbSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jtfUsername.getText().trim();
                String pass = jpfPassword.getText().trim();
                DBConnection db = new DBConnection();
                boolean check  = db.checkRes("Select username, password from auth where username = '" + username +"' and password ='" +
                        pass +"';");
                if (check){
                    JOptionPane.showMessageDialog(frame, "Welcome");
                    frame.setVisible(false);
                    new Client(username);
                } else{
                    JOptionPane.showMessageDialog(frame, "Incorrect username or password");
                }
            }
        });

        frame.setBounds(500, 200, 500, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }


}
