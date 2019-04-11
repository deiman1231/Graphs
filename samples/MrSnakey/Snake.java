public class Snake
{
    public static final double MOVEMENT_SPEED = 4.0;
    public static final int MAXIMUM_SEGMENTS = 5000;
    public static final int INITIAL_SEGMENTS = 4;


    private SnakeSegment head;
    private SnakeSegment[] body = new SnakeSegment[MAXIMUM_SEGMENTS];
    private int bodyLength;

    private double xSpeed;
    private double ySpeed;
    private boolean started;

    private GameArena arena;

    public Snake(double x, double y, GameArena a)
    {
        xSpeed = 0;
        ySpeed = 0;
        arena = a;
        started = false;
        bodyLength = INITIAL_SEGMENTS + 1;

        body[0] = new SnakeSegment(x, y, 0, 0, null, arena); 
        head = body[0];

        for (int i=1; i < bodyLength; i++)
            body[i] = new SnakeSegment(x+(i*SnakeSegment.SPAWN_DISTANCE), y, 0, 0, body[i-1], arena);

        head.sendToFront();
    }

    private boolean changeDirectionValid()
    {
        int segmentToCheck = Math.min(bodyLength, 3) - 1;

        // If the first body segment has caught up with the direciton of the head, we can change direction.
        if (head.getXSpeed() == body[segmentToCheck].getXSpeed() && head.getYSpeed() == body[segmentToCheck].getYSpeed())
            return true;

        // otherwise, we should wait.
        return false;
    }

    public void goUp()
    {
        // If we're not travelling down, then move. (the game doesn't allow you to move back on yourself){
        if (changeDirectionValid() && ySpeed == 0.0)
        {
            xSpeed = 0; ySpeed = -MOVEMENT_SPEED;
        }
    }

    public void goDown()
    {
        // If we're not travelling down, then move. (the game doesn't allow you to move back on yourself)
        if (changeDirectionValid() && ySpeed == 0.0)
        {
            xSpeed = 0; ySpeed = MOVEMENT_SPEED; 
        }
    }

    public void goLeft()
    {
        // If we're not travelling down, then move. (the game doesn't allow you to move back on yourself)
        if (changeDirectionValid() && xSpeed == 0.0)
        {
            xSpeed = -MOVEMENT_SPEED; ySpeed = 0; 
        }
    }

    public void goRight()
    {
        if (changeDirectionValid() && xSpeed == 0.0 && started)
        {
            xSpeed = MOVEMENT_SPEED; ySpeed = 0; 
        }
    }

    public boolean collides(Food f)
    {
        return head.collides(f);
    }

    public void move()
    {
        head.setSpeed(xSpeed, ySpeed);

        if (!started && (xSpeed != 0.0 || ySpeed != 0.0))
        {
            started = true;
            for (int i=1; i<bodyLength; i++)
                body[i].setSpeed(-MOVEMENT_SPEED, 0);
        }

        for (int i=0; i<bodyLength; i++)
            body[i].move();        
    }

    public void grow(int n)
    {
        for (int i=bodyLength; i < bodyLength+n; i++)
            body[i] = body[i-1].spawn();

        bodyLength += n;
    }

    public boolean hasCrashed()
    {
        if (bodyLength < 5)
            return false;

        for (int i=4; i<bodyLength; i++)
            if (head.collides(body[i]))
                return true;

        return false;
    }

    public void removeFromScreen()
    {
        for (int i=0; i<bodyLength; i++)
            body[i].removeFromScreen();        
    }

    public int getLength()
    {
        return bodyLength;
    }
}