import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paymentSwing extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel Panel3;
    private JButton buyButton;
    private JLabel icon3;
    private JTextField textField4;

    public paymentSwing(){
        ImageIcon icon=new ImageIcon("image/image.jpg");
        setIconImage(icon.getImage());
        icon3.setIcon(new ImageIcon("image/blue.jpg"));
        add(Panel3);
        setSize(600,600);
        setTitle("Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

}
