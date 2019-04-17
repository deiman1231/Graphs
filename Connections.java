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
    private JButton button3;
    private JButton button4;
    private JTextField textfield;
    private JTextField textfield1;
    private JTextField textfield2;
    private JTextField textfield3;
    private JTextField textfield4;
    public GameArena arena;
    private int count;

    public Connections(){
        arena = new GameArena(500,500);
        JFrame window = new JFrame("Connections");
        JPanel panel = new JPanel();
        button = new JButton("Undirected Connection");
        button1 = new JButton("Direct Connection");
        button2 = new JButton("Get adjacent nodes");
        button3 = new JButton("Depth first traversal");
        button4 = new JButton("Breadth first traversal");
        textfield = new JTextField("Nodes(start) number", 15);
        textfield1 = new JTextField("Nodes(end) number", 15);
        textfield2 = new JTextField("Get adjacent Nodes(number)", 15);
        textfield3 = new JTextField("Start DFS from(number)", 15);
        textfield4 = new JTextField("Start BFS from(number)", 15);
        FlowLayout f = new FlowLayout();

        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        panel.setLayout(f);
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(textfield);
        panel.add(textfield1);
        panel.add(textfield2);
        panel.add(textfield3);
        panel.add(textfield4);
        window.setContentPane(panel);

        window.setSize(300, 400);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        String linkNameStart = textfield.getText();
        String linkNameEnd = textfield1.getText();
        String nodesNameAdj = textfield2.getText();
        String nodesIndex = textfield3.getText();
        String nodesIndex1 = textfield4.getText();
        Ball linkStart = null;
        Ball linkEnd = null;
        Ball adjNodes = null;
        if(e.getSource() == button2){
            for (int i = 0; i < arena.nodes.size(); i++){
                if(arena.nodes.get(i).text.getText().equals(nodesNameAdj))
                    adjNodes = arena.nodes.get(i);
            }
            int x = adjNodes.index;
            Iterator<Ball> node = arena.adj.get(x).iterator();
            System.out.print("Node " + x + " has adjacent nodes - [");
            while(node.hasNext()){
                System.out.print(node.next().index);
                if(node.hasNext())
                    System.out.print(", ");
            }
            System.out.print("]");
        }
        if(e.getSource() == button){
            for (int i = 0; i < arena.nodes.size(); i++){
                if (arena.nodes.get(i).text.getText().equals(linkNameStart))
                    linkStart = arena.nodes.get(i);
                if (arena.nodes.get(i).text.getText().equals(linkNameEnd))
                    linkEnd = arena.nodes.get(i);
            }
            if(edgeExists(linkStart, linkEnd) == true)
                return;
            arena.adj.get(linkStart.index).add(linkEnd);
            arena.adj.get(linkEnd.index).add(linkStart);
            Line line = new Line(linkStart.getXPosition(), linkStart.getYPosition(), linkEnd.getXPosition(), linkEnd.getYPosition(), 1, "WHITE");
            arena.addLine(line);
        }
        if(e.getSource() == button1){
            for (int i = 0; i < arena.nodes.size(); i++){
                if (arena.nodes.get(i).text.getText().equals(linkNameStart))
                    linkStart = arena.nodes.get(i);
                if (arena.nodes.get(i).text.getText().equals(linkNameEnd))
                    linkEnd = arena.nodes.get(i);
            }
            if(edgeExists(linkStart, linkEnd) == true)
                return;
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
        if(e.getSource() == button3){
            int start = 0;
            for(int i = 0; i < arena.nodes.size(); i++){
                if(arena.nodes.get(i).text.getText().equals(nodesIndex))
                    start = arena.nodes.get(i).index;
            }
            DFS(start);
        }
        if(e.getSource() == button4){
            int start1 = 0;
            for(int i = 0; i < arena.nodes.size(); i++){
                if(arena.nodes.get(i).text.getText().equals(nodesIndex1))
                    start1 = arena.nodes.get(i).index;
            } 
            BFS(start1);
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
    public void BFS(int nodeIndex)
    {
        boolean[] visited = new boolean[arena.nodes.size()]; 
        LinkedList<Integer> queue = new LinkedList<Integer>();
  
        visited[nodeIndex]=true; 
        queue.add(nodeIndex); 
  
        while (queue.size() != 0) 
        { 
            nodeIndex = queue.poll(); 
            System.out.print(nodeIndex+" "); 
  
            Iterator<Ball> q = arena.adj.get(nodeIndex).iterator(); 
            while (q.hasNext()) 
            { 
                Ball nextNode = q.next(); 
                if (!visited[nextNode.index]) 
                { 
                    visited[nextNode.index] = true; 
                    queue.add(nextNode.index); 
                } 
            } 
        }  
    }
    public void DFShelper(int nodeIndex, boolean[] visited)
    {
        visited[nodeIndex] = true; 
        System.out.print(nodeIndex + " "); 
  
        Iterator<Ball> q = arena.adj.get(nodeIndex).iterator(); 
        while (q.hasNext()) 
        { 
            Ball nextNode = q.next(); 
            if (!visited[nextNode.index]) 
                DFShelper(nextNode.index, visited); 
        } 
    }
    public void DFS(int nodeIndex)
    {
        boolean[] visited = new boolean[arena.nodes.size()];
        DFShelper(nodeIndex, visited);
    }
}
