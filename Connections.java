import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.HashSet;

public class Connections implements ActionListener
{
    private JButton button;
    private JButton button1;
    private JButton button2;
    private JTextField textfield;
    private JTextField textfield1;
    private JTextField textfield2;
    public GameArena arena;
    private int count;

    public Connections(){
        arena = new GameArena(500,500);
        JFrame window = new JFrame("Connections");
        JPanel panel = new JPanel();
        button = new JButton("Undirected Connection");
        button1 = new JButton("Direct Connection");
        button2 = new JButton("Get adjacent nodes");
        textfield = new JTextField("Nodes(start) number", 15);
        textfield1 = new JTextField("Nodes(end) number", 15);
        textfield2 = new JTextField("Get adjacent Nodes(number)", 15);
        FlowLayout f = new FlowLayout();

        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        panel.setLayout(f);
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(textfield);
        panel.add(textfield1);
        panel.add(textfield2);
        window.setContentPane(panel);

        window.setSize(300, 250);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        String linkNameStart = textfield.getText();
        String linkNameEnd = textfield1.getText();
        String nodesNameAdj = textfield2.getText();
        Ball linkStart = null;
        Ball linkEnd = null;
        Ball adjNodes = null;
        for (int i = 0; i < arena.nodes.size(); i++){
            if (arena.nodes.get(i).text.getText().equals(linkNameStart))
                linkStart = arena.nodes.get(i);
            if (arena.nodes.get(i).text.getText().equals(linkNameEnd))
                linkEnd = arena.nodes.get(i);
            if(arena.nodes.get(i).text.getText().equals(nodesNameAdj))
                adjNodes = arena.nodes.get(i);
        }
        if(e.getSource() == button2){
            int x = adjNodes.index;
            for(int i = 0; i < arena.nodes.size(); i++){
                if(arena.adj.get(x).contains(arena.nodes.get(i)))
                    System.out.println(arena.nodes.get(i).index);
            }
        }
        if(edgeExists(linkStart, linkEnd) == true)
            return;
        if(e.getSource() == button){
            arena.adj.get(linkStart.index).add(linkEnd);
            arena.adj.get(linkEnd.index).add(linkStart);
            Line line = new Line(linkStart.getXPosition(), linkStart.getYPosition(), linkEnd.getXPosition(), linkEnd.getYPosition(), 1, "WHITE");
            arena.addLine(line);
        }
        if(e.getSource() == button1){
            if(linkStart.index != linkEnd.index){
                arena.adj.get(linkStart.index).add(linkEnd);
                Arrow arrow = new Arrow(linkStart.getXPosition(), linkStart.getYPosition(), linkEnd.getXPosition(), linkEnd.getYPosition(), 1, "WHITE", arena);
            }
            else if(linkStart.index == linkEnd.index){
                arena.adj.get(linkStart.index).add(linkEnd);
                Arrow arrow = new Arrow(linkStart.getXPosition(), linkStart.getYPosition(), linkEnd.getXPosition()+20, linkEnd.getYPosition()+20, 1, "WHITE", arena);
                Arrow arrow1 = new Arrow(linkStart.getXPosition()+20, linkStart.getYPosition()+20, linkEnd.getXPosition(), linkEnd.getYPosition(), 1, "WHITE", arena);
            }
            
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
