
public class Driver
{
    public static void main(String[] args)
    {
        
        Connections c = new Connections();
        //Ball ball = new Ball(100, 100, 10, "GREEN");
        //a.addBall(ball);
        
        while(true)
        {
            c.arena.update();
            //System.out.println(MouseInfo.getPointerInfo().getLocation().getX() + "   " + MouseInfo.getPointerInfo().getLocation().getY());
        }
    }
}
