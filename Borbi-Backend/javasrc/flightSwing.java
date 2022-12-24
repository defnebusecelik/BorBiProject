import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class flightSwing extends JFrame {
    private JPanel Panel2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton goToPaymentsButton;
    private JLabel icon2;

    public flightSwing(){
        ImageIcon icon=new ImageIcon("image/image.jpg");
        setIconImage(icon.getImage());
        icon2.setIcon(new ImageIcon("image/blue.jpg"));
        add(Panel2);
        setSize(600,600);
        setTitle("Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        goToPaymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

}
