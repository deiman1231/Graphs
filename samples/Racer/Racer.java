public class Racer
{
    public static final double PLAYER_SPEED = 5;
    public static final int ROAD_SEGMENT_WIDTH = 160;
    public static final int ROAD_SEGMENT_HEIGHT= 10;
    public static final int ROAD_CURVE_SPEED = 5;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    private GameArena arena;
    private Car player;
    private RoadSegment[] road = new RoadSegment[SCREEN_HEIGHT / ROAD_SEGMENT_HEIGHT + 1];

    private double currentRoadX = SCREEN_WIDTH/2;
    private double speed = 2.0;

    public Racer()
    {
        arena = new GameArena(SCREEN_WIDTH, SCREEN_HEIGHT);
        player = new Car(SCREEN_WIDTH/2, SCREEN_HEIGHT - 150, arena);

        // Initialise Road...
        createRoad();

        while(true)
        {
            double speed = 0;
            if (arena.leftPressed())
                speed -= PLAYER_SPEED;

            if (arena.rightPressed())
                speed += PLAYER_SPEED;

            player.setXSpeed(speed);

            player.move();
            for (int i=0; i<road.length; i++)
            {
                if (road[i] != null)
                    road[i].move();
            }

            // Recycle any segments that have crolled off screen...
            recycleRoadSegments();

            arena.pause();
        }
    }

    private RoadSegment nextRoadSegment()
    {
        currentRoadX += Math.random() * 2 * ROAD_CURVE_SPEED - ROAD_CURVE_SPEED;
        RoadSegment s = new RoadSegment(currentRoadX, -ROAD_SEGMENT_HEIGHT, ROAD_SEGMENT_WIDTH, ROAD_SEGMENT_HEIGHT, arena);
        s.setYSpeed(speed);
        return s;
    }

    private void createRoad()
    {
        for (int s = road.length-1; s >= 0 ; s--)
        {
            road[s] = nextRoadSegment(); 
            road[s].setYPosition(s*ROAD_SEGMENT_HEIGHT);
        }
    }

    private void recycleRoadSegments()
    {
        for (int i=0; i<road.length; i++)
        {
            if (road[i].getYPosition() > SCREEN_HEIGHT)
            {
                double y = road[i].getYPosition();
                road[i].remove();
                road[i] = nextRoadSegment(); 
                road[i].setYPosition(y - SCREEN_HEIGHT - ROAD_SEGMENT_HEIGHT);
            }
        }
    }

    public static void main(String[] args)
    {
        new Racer();
    }
}
