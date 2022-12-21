import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class flightSwing extends JFrame {
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel Panel3;
    private JButton buyButton;

    public flightSwing(){
        add(Panel3);
        setSize(500,500);
        setTitle("Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
