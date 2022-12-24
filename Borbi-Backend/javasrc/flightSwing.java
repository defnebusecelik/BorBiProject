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
    private JLabel icon1;
    private JTextField textField4;
    private JLabel label4;
    private JButton joinUsButton;

    public registerSwing(){
        ImageIcon icon=new ImageIcon("image/image.jpg");
        setIconImage(icon.getImage());
        icon1.setIcon(new ImageIcon("image/blue.jpg"));
        add(Panel1);
        setSize(600,600);
        setTitle("Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label1.setVisible(false);
        textField1.setVisible(false);
        label4.setVisible(false);
        textField4.setVisible(false);
        registerButton.setVisible(false);
        Panel1.setVisible(true);


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
        joinUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label1.setVisible(true);
                textField1.setVisible(true);
                label4.setVisible(true);
                textField4.setVisible(true);
                registerButton.setVisible(true);
            }
        });
    }

}
