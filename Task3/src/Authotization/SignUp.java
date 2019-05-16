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

public class SignUp extends JFrame {

    String username, email, pass, passRepeat;
    JLabel jlEmail, jlUsername, jlPassword, jlPasswordRepeat;
    JTextField jtfEmail, jtfUsername;
    JPasswordField jpfPassword, jpfPasswordRepeat;
    JButton jbSignUp, jbBack;

    public SignUp() {

        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jbBack = new JButton("Back");
        jbBack.setBounds(10, 10, 80, 40);
        frame.add(jbBack);

        jlEmail = new JLabel("Email:");
        jlEmail.setBounds(20, 60, 80, 40);
        frame.add(jlEmail);

        jlUsername = new JLabel("Username:");
        jlUsername.setBounds(20, 110, 80, 40);
        frame.add(jlUsername);

        jlPassword = new JLabel("Password:");
        jlPassword.setBounds(20, 160, 80, 40);
        frame.add(jlPassword);

        jlPasswordRepeat = new JLabel("Repeat password:");
        jlPasswordRepeat.setBounds(20, 210, 120, 40);
        frame.add(jlPasswordRepeat);

        jtfEmail = new JTextField("email");
        jtfEmail.setBounds(150, 60, 300, 40);
        frame.add(jtfEmail);

        jtfUsername = new JTextField("username");
        jtfUsername.setBounds(150, 110, 300, 40);
        frame.add(jtfUsername);

        jpfPassword = new JPasswordField("password");
        jpfPassword.setBounds(150, 160, 300, 40);
        frame.add(jpfPassword);

        jpfPasswordRepeat = new JPasswordField("password");
        jpfPasswordRepeat.setBounds(150, 210, 300, 40);
        frame.add(jpfPasswordRepeat);

        jbSignUp = new JButton("Sign in");
        jbSignUp.setBounds(150, 260, 150, 40);
        frame.add(jbSignUp);

        jtfEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfEmail.setText("");
            }
        });

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

        jpfPasswordRepeat.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jpfPasswordRepeat.setText("");
            }
        });


        jbBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Login();

            }
        });

        jbSignUp.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                email = jtfEmail.getText().trim();
                username = jtfUsername.getText().trim();
                pass = jpfPassword.getText().trim();
                passRepeat = jpfPasswordRepeat.getText().trim();
                if (!pass.equals(passRepeat)) {
                    JOptionPane.showMessageDialog(frame, "Passwords don't match");
                } else if (username.length() > 50) {
                    JOptionPane.showMessageDialog(frame, "Username should me no more than 50 characters");
                } else if (pass.length() > 50) {
                    JOptionPane.showMessageDialog(frame, "Passwords should me no more than 50 characters");
                } else if (pass.length() < 8) {
                    JOptionPane.showMessageDialog(frame, "Password should be at least 8 characters ");
                } else if (email.isEmpty() || username.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Fill in all fields");
                } else {
                    DBConnection db = new DBConnection();

                    boolean checkName = db.checkRes("Select username from auth where username = '" + username + "';");
                    boolean checkEmail = db.checkRes("Select email from auth where email = '" + email + "';");
                    if (checkName || checkEmail) {
                        JOptionPane.showMessageDialog(frame, "User with this username or email already exists");;
                    } else {
                        db.exec("INSERT INTO auth(email, username, password) VALUES ('" + email + "', '" + username + "', '" + pass + "');");
                        JOptionPane.showMessageDialog(frame, "You have been succesfully signed up");
                        frame.setVisible(false);
                        new Client(username);
                    }

                }
            }
        }
        );


        frame.setBounds(500, 200, 500, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }


}
