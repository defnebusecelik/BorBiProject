import javax.swing.*;

public class Interface4 extends JFrame {

    private JPanel Panel4;
    private JLabel icon4;

    public Interface4() {
        ImageIcon icon = new ImageIcon("image/image.jpg");
        setIconImage(icon.getImage());
        icon4.setIcon(new ImageIcon("image/green.jpg"));
        add(Panel4);
        setSize(600, 600);
        setTitle("Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
