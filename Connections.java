import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.HashSet;

public class Connections implements ActionListener
{
    private JButton button;
    private JButton button1;
    private JTextField textfield;
    private JTextField textfield1;
    public GameArena arena;
    private int count;

    public Connections(){
        arena = new GameArena(500,500);
        JFrame window = new JFrame("Connections");
        JPanel panel = new JPanel();
        button = new JButton("Undirected Connection");
        button1 = new JButton("Direct Connection");
        textfield = new JTextField("Nodes number", 15);
        textfield1 = new JTextField("Nodes number", 15);
        FlowLayout f = new FlowLayout();
        button.addActionListener(this);
        button1.addActionListener(this);

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
    public void actionPerformed(ActionEvent e)
    {
        String linkNameStart = textfield.getText();
        String linkNameEnd = textfield1.getText();
        Ball linkStart = null;
        Ball linkEnd = null;
        for (int i = 0; i < arena.nodes.size(); i++){
            if (arena.nodes.get(i).text.getText().equals(linkNameStart))
                linkStart = arena.nodes.get(i);
            if (arena.nodes.get(i).text.getText().equals(linkNameEnd))
                linkEnd = arena.nodes.get(i);
        }
        if(edgeExists(linkStart, linkEnd) == true)
            return;
        if(e.getSource() == button){
            arena.adj.get(linkStart.index).add(linkEnd);
            arena.adj.get(linkEnd.index).add(linkStart);
            Line line = new Line(linkStart.getXPosition(), linkStart.getYPosition(), linkEnd.getXPosition(), linkEnd.getYPosition(), 5, "WHITE");
            arena.addLine(line);
        }
    }
    public boolean edgeExists(Ball start, Ball end)
    {
        
        int x = start.index;
        for(int i = 0; i < arena.nodes.size(); i++){
            Iterator node1 = arena.adj.get(i).iterator();
            while(node1.hasNext()){
                if(node1.next() == end && i == x)
                    return true;
            }
        }
        return false;
    }
}
