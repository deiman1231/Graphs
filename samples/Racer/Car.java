public class Car
{
    public static String CAR_COLOUR = "#FF0000";
    public static String WHEEL_COLOUR = "#404040";

    private Rectangle[] parts = new Rectangle[10];
    private double xPosition;
    private double yPosition;
    private GameArena arena;

    private double xSpeed;
    private double ySpeed;

    public Car(double x, double y, GameArena a)
    {
        parts[0] = new Rectangle(10, 20, 10, 20, WHEEL_COLOUR);
        parts[1] = new Rectangle(10, 80, 10, 20, WHEEL_COLOUR);
        parts[2] = new Rectangle(50, 20, 10, 20, WHEEL_COLOUR);
        parts[3] = new Rectangle(50, 80, 10, 20, WHEEL_COLOUR);
        parts[4] = new Rectangle(30, 50, 40, 70, CAR_COLOUR);
        parts[5] = new Rectangle(15, 18, 5, 5, "WHITE");
        parts[6] = new Rectangle(45, 18, 5, 5, "WHITE");

        arena = a;
        this.setXPosition(x);
        this.setYPosition(y);

        for (int i=0; i < parts.length; i++)
        {
            if(parts[i] != null)
                arena.addRectangle(parts[i]);
        }
    }

    public void setXPosition(double x)
    {
        double dx = x - xPosition;

        for (int i=0; i < parts.length; i++)
        {
            if(parts[i] != null)
                parts[i].setXPosition(parts[i].getXPosition() + dx);
        }
        xPosition = x;
    }

    public void setYPosition(double y)
    {
        double dy = y - yPosition;
        for (int i=0; i < parts.length; i++)
        {
            if(parts[i] != null)
                parts[i].setYPosition(parts[i].getYPosition() + dy);
        }
        yPosition = y;
    }

    public double getXPosition()
    {
        return xPosition;
    }

    public double getYPosition()
    {
        return yPosition;
    }

    public void setXSpeed(double s)
    {
        xSpeed = s;
    }

    public void setYSpeed(double s)
    {
        ySpeed = s;
    }

    public void move()
    {
        this.setXPosition(xPosition + xSpeed);
        this.setYPosition(yPosition + ySpeed);
    }

}
