public class SnakeSegment
{
    public static double SPAWN_DISTANCE = 20.0;

    private Ball[] parts;
    
	private double xPosition = 0.0;
	private double yPosition = 0.0;
	private double xSpeed = 0.0;
	private double ySpeed = 0.0;
    private GameArena arena;
    private SnakeSegment previous;

	public SnakeSegment(double x, double y, double xs, double ys, SnakeSegment p, GameArena a)
	{
		this.xPosition = x;
        this.yPosition = y;
        this.xSpeed = xs;
        this.ySpeed = ys;
        this.arena = a;
        this.previous = p;

        if (previous == null)
        {
            // if there's no previous segment, we must be a head...
            parts = new Ball[13];
            parts[0] = new Ball(x, y, 30, "SEAGREEN");			// Head Highlight
            parts[1] = new Ball(x, y, 28, "GREEN");				// Head
            parts[2] = new Ball(x-10, y-10, 10, "WHITE");		// Left Eye
            parts[3] = new Ball(x+10, y-10, 10, "WHITE");		// Right Eye
            parts[4] = new Ball(x-10, y-10, 4, "BLACK");		// Left Pupil
            parts[5] = new Ball(x+10, y-10, 4, "BLACK");		// Right Pupil
            parts[6] = new Ball(x-15, y-30, 5, "GREEN");		// Left Antenna			
            parts[7] = new Ball(x+15, y-30, 5, "GREEN");		// Right Antenna
            parts[8] = new Ball(x-10, y+10, 2, "YELLOW");		// Mouth
            parts[9] = new Ball(x-5, y+15, 2, "YELLOW");		// Mouth
            parts[10] = new Ball(x, y+15, 2, "YELLOW");			// Mouth
            parts[11] = new Ball(x+5, y+15, 2, "YELLOW");		// Mouth
            parts[12] = new Ball(x+10, y+10, 2, "YELLOW");		// Mouth        
        }
        else
        {
            // Otherwise we must be a normal segment...
            parts = new Ball[2];            
            parts[0] = new Ball(x, y, 25, "SEAGREEN");		    // Highlight
            parts[1] = new Ball(x, y, 23, "GREEN");		        // Segment
        }
        addToScreen();
	}

    public void addToScreen()
    {
        for (int i = 0 ; i < parts.length; i++)
            arena.addBall(parts[i]);
    }

    public void removeFromScreen()
    {
        for (int i = 0 ; i < parts.length; i++)
            arena.removeBall(parts[i]);
    }

    public void sendToFront()
    {
        removeFromScreen();
        addToScreen();
    }

	public double getXPosition()
	{
		return xPosition;
	}

	public double getYPosition()
	{
		return yPosition;
    }
    
	public double getXSpeed()
	{
		return xSpeed;
	}

	public double getYSpeed()
	{
		return ySpeed;
	}

    public void setSpeed(double x, double y)
    {
        xSpeed = x;
        ySpeed = y;
    }

	private void checkWrapAround()
	{
		double dx = 0.0;
		double dy = 0.0;

		if (xPosition < 0)
			dx = arena.getArenaWidth();
		if (xPosition > arena.getArenaWidth())
			dx = -arena.getArenaWidth();

		if (yPosition < 0)
			dy = arena.getArenaHeight();
		if (yPosition > arena.getArenaHeight())
			dy = -arena.getArenaHeight();

		xPosition += dx;
		yPosition += dy;
	
		for (int i=0; i<parts.length; i++)
            parts[i].move(dx, dy);
    }

    public SnakeSegment spawn()
    {
        //	public SnakeSegment(double x, double y, double xs, double ys, SnakeSegment p, GameArena a)
        double dx = 0.0, dy = 0.0;

        if (xSpeed > 0)
            dx = -SPAWN_DISTANCE;
        if (xSpeed < 0)
            dx = SPAWN_DISTANCE;
        if (ySpeed > 0)
            dy = -SPAWN_DISTANCE;
        if (ySpeed < 0)
            dy = SPAWN_DISTANCE;

        return new SnakeSegment(xPosition+dx, yPosition+dy, xSpeed, ySpeed, this, arena);
    }

	public void move()
    {
        boolean retarget = false;

        // If we're a mormal segment, see if we need to change direction...
        if (previous != null)
        {
            if ((xSpeed < 0.0 && xPosition >= previous.xPosition && xPosition + xSpeed <= previous.xPosition) || (xSpeed > 0.0 && xPosition <= previous.xPosition && xPosition + xSpeed >= previous.xPosition))
            {
                // We're moving horizontally, and reached the point to change direction to follow the previous segment...
                // Update our X coordinate to be perfectly in line (to account for rounding errors)
                retarget = true;
                double dx = previous.xPosition - xPosition;
                xPosition = previous.xPosition;
                for (int i=0; i<parts.length; i++)
                    parts[i].move(dx, 0);
            }
    
            if ((ySpeed < 0.0 && yPosition >= previous.yPosition && yPosition + ySpeed <= previous.yPosition) || (ySpeed > 0.0 && yPosition <= previous.yPosition && yPosition + ySpeed >= previous.yPosition))
            {
                // We're moving horizontally, and reached the point to change direction to follow the previous segment...
                // Update our X coordinate to be perfectly in line (to account for rounding errors)
                retarget = true;
                double dy = previous.yPosition - yPosition;
                yPosition = previous.yPosition;
                for (int i=0; i<parts.length; i++)
                    parts[i].move(0, dy);            
            }    

            if (retarget)
            {
                xSpeed = previous.xSpeed;
                ySpeed = previous.ySpeed;    
                return;    
            }
        }

        xPosition += xSpeed;
        yPosition += ySpeed;
    
        for (int i=0; i<parts.length; i++)
            parts[i].move(xSpeed, ySpeed);

        checkWrapAround();    
    }

    boolean collides (Food f)
	{
		Ball[] foodParts = f.getParts();

		for (int i=0; i<parts.length; i++)
		{
			for (int j=0; j<foodParts.length; j++)
			{
				if (parts[i].collides(foodParts[j]))
					return true;
			}
		}

		return false;
    }
    
    boolean collides (SnakeSegment s)
	{
        for (int i=0; i<parts.length; i++)
		{
			for (int j=0; j<s.parts.length; j++)
			{
				if (parts[i].collides(s.parts[j]))
					return true;
			}
		}

		return false;
	}

}
