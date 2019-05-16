
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

public class UsernameChange extends JFrame {

    JButton jbBack, jbChange;
    JLabel jlUsername, jlPassword, jlNewUN, JLNewUnRepeat;
    JTextField jtfUsername, jtfNewUN, jtfNewUnRepeat;
    JPasswordField jpfPassword;

    public UsernameChange() {
        JFrame frame = new JFrame("Username Change");
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

        jlNewUN = new JLabel("New username:");
        jlNewUN.setBounds(20, 160, 100, 40);
        frame.add(jlNewUN);

        JLNewUnRepeat = new JLabel("Repeat username:");
        JLNewUnRepeat.setBounds(20, 210, 120, 40);
        frame.add(JLNewUnRepeat);

        jtfUsername = new JTextField("username");
        jtfUsername.setBounds(150, 60, 300, 40);
        frame.add(jtfUsername);

        jpfPassword = new JPasswordField("password");
        jpfPassword.setBounds(150, 110, 300, 40);
        frame.add(jpfPassword);

        jtfNewUN = new JTextField("new username");
        jtfNewUN.setBounds(150, 160, 300, 40);
        frame.add(jtfNewUN);

        jtfNewUnRepeat = new JTextField("repeat username");
        jtfNewUnRepeat.setBounds(150, 210, 300, 40);
        frame.add(jtfNewUnRepeat);

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

        jtfNewUN.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfNewUN.setText("");
            }
        });

        jtfNewUnRepeat.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfNewUnRepeat.setText("");
            }
        });

        jbChange = new JButton("Change");
        jbChange.setBounds(150, 260, 150, 40);
        frame.add(jbChange);

        jbChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jtfUsername.getText().trim();
                String pass = jpfPassword.getText().trim();
                String newUN = jtfNewUN.getText().trim();
                String newUNR = jtfNewUnRepeat.getText().trim();

                if (!newUN.equals(newUNR)) {
                    JOptionPane.showMessageDialog(frame, "New usernames don't match");
                } else if (username.equals(newUN)) {
                    JOptionPane.showMessageDialog(frame, "Please enter a different username");
                } else {
                    DBConnection db = new DBConnection();
                    boolean check = db.checkRes("Select username, password from auth where username = '" + username + "' and password ='"
                            + pass + "';");
                    if (check) {
                        db.exec("UPDATE auth SET username = '" + newUN + "';");
                        JOptionPane.showMessageDialog(frame, "Update succesfull");
                        frame.setVisible(false);
                        new Client(newUN);

                    } else {
                        JOptionPane.showMessageDialog(frame, "Wrong username or password");
                    }
                }
            }
        });

        frame.setBounds(500, 200, 500, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
