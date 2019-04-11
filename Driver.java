import java.awt.MouseInfo;

public class Driver
{
    public static void main(String[] args)
    {
        GameArena a = new GameArena(500,500);
        //Ball ball = new Ball(100, 100, 10, "GREEN");
        //a.addBall(ball);
        
        while(true)
        {
            a.update();
            //System.out.println(MouseInfo.getPointerInfo().getLocation().getX() + "   " + MouseInfo.getPointerInfo().getLocation().getY());
        }
    }
}
