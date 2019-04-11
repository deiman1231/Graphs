public class Food
{
    public static final int FOOD_TYPE_TOMATO = 1;
    public static final int FOOD_TYPE_GRAPES = 2;

    private Ball[] parts;
    private int points;
    private int type;
	private GameArena arena;

	public Food(GameArena a)
    {
        int x = (int) (Math.random() * a.getArenaWidth());
        int y = (int) (Math.random() * a.getArenaHeight());

        this.arena = a;
        this.type = (int) ((2.0* Math.random()) + 1);

        this.points = (int) ((Math.random() * 5.0) + 1);
        this.points = this.points * type;

        if (this.type == FOOD_TYPE_TOMATO)
        {
            parts = new Ball[4];
            parts[0] = new Ball(x, y, 20, "RED");			    // Apple
            parts[1] = new Ball(x+8, y-8, 4, "TOMATO");			// Highlight
            parts[2] = new Ball(x-4, y-18, 4, "GREEN");		    // Leaf1
            parts[3] = new Ball(x-10, y-15, 4, "GREEN");		// Leaf2
        }

        if (this.type == FOOD_TYPE_GRAPES)
        {
            parts = new Ball[8];
            parts[0] = new Ball(x, y+25, 5, "VIOLET");		// Grape
            parts[1] = new Ball(x-5, y+15, 5, "VIOLET");	// Grape
            parts[2] = new Ball(x+5, y+15, 5, "VIOLET");	// Grape
            parts[3] = new Ball(x-10, y+5, 5, "VIOLET");	// Grape
            parts[4] = new Ball(x, y+5, 5, "VIOLET");		// Grape
            parts[5] = new Ball(x+10, y+5, 5, "VIOLET");	// Grape
            parts[6] = new Ball(x-4, y-3, 4, "GREEN");		// Leaf1
            parts[7] = new Ball(x-10, y, 4, "GREEN");	    // Leaf2
        }

        addToScreen();
	}

    public int getPoints()
    {
        return points;
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
	
    public Ball[] getParts()
    {
        return parts;
    }
    
}
