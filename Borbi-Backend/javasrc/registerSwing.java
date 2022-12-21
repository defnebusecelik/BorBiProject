import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class registerSwing extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel Panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public registerSwing(){
        add(Panel1);
        setSize(500,500);
        setTitle("Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label2.setVisible(false);
        textField2.setVisible(false);
        Panel1.setVisible(true);

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        textField3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
