import javax.swing.*;
import java.awt.*;

public class Connections
{
    public Connections(){
        JFrame window = new JFrame("Connections");
        JPanel panel = new JPanel();
        JButton button = new JButton("Simple Connection");
        JButton button1 = new JButton("Direct Connection");
        JTextField textfield = new JTextField("Nodes number", 15);
        JTextField textfield1 = new JTextField("Nodes number", 15);
        FlowLayout f = new FlowLayout();

        panel.setLayout(f);
        panel.add(button);
        panel.add(button1);
        panel.add(textfield);
        panel.add(textfield1);
        window.setContentPane(panel);

        window.setSize(300, 200);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
