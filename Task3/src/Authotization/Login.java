package Authotization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/** Java 3. Lesson3. I/O
 *
 * @author Maria Mikhaleva
 * @version dated Apr 23 2018
 */

public class Login extends JFrame {

    private JButton jbSignIn, jbSignUp, jbChange;

    public Login() {

        JFrame frame = new JFrame("Authorization");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jbSignIn = new JButton("Sign In");
        jbSignIn.setBounds(50, 60, 80, 40);
        frame.add(jbSignIn);

        jbSignUp = new JButton("Sign Up");
        jbSignUp.setBounds(150, 60, 80, 40);
        frame.add(jbSignUp);

        jbChange = new JButton("Change Login");
        jbChange.setBounds(250, 60, 150, 40);
        frame.add(jbChange);

        jbSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SignIn();

            }
        });

        jbSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SignUp();
            }
        });

        jbChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new UsernameChange();
            }
        });

        frame.setBounds(500, 200, 500, 200);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
