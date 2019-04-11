public class RoadSegment
{
    public static String ROUGH_COLOUR = "#00FF00";
    public static String KERB_COLOUR = "#808080";
    public static double KERB_WIDTH = 10;

    private Rectangle[] parts = new Rectangle[4];
    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private GameArena arena;

    private double xSpeed;
    private double ySpeed;

    public RoadSegment(double x, double y, double width, double height, GameArena a)
    {
        this.width = width;
        this.height = height;
        arena = a;

        double roughWidth1 = x - width/2;
        double roughWidth2 = arena.getArenaWidth() - (x + width/2);

        double roughX1 = -width/2 - roughWidth1/2;
        double roughX2 =  width/2 + roughWidth2/2;

        parts[0] = new Rectangle(roughX1, height/2, roughWidth1, height, ROUGH_COLOUR);
        parts[1] = new Rectangle(roughX2, height/2, roughWidth2, height, ROUGH_COLOUR);
        parts[2] = new Rectangle(-width/2-KERB_WIDTH/2, height/2, KERB_WIDTH, height, KERB_COLOUR);
        parts[3] = new Rectangle(width/2+KERB_WIDTH/2, height/2, KERB_WIDTH, height, KERB_COLOUR);

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

    public void remove()
    {
        for (int i=0; i < parts.length; i++)
        {
            if(parts[i] != null)
                arena.removeRectangle(parts[i]);

            parts[i] = null;
        }
    }
}
