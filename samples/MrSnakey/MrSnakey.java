public class MrSnakey
{
    public static int MAXIMUM_AMOUNT_OF_FOOD = 8;

    private GameArena arena = new GameArena(1000,1000);
    private Snake snake;
    private Food[] food = new Food[MAXIMUM_AMOUNT_OF_FOOD];
    private int amountOfFood = 1;
    private int score;
    public MrSnakey()
    {
        boolean playing = true;

        while(true)
        {
            snake = new Snake(250,250,arena);
           
            playing = true;
            score = 0;
            amountOfFood = 1;

            while(playing)
            {
                if (arena.upPressed())
                    snake.goUp();
                if (arena.downPressed())
                    snake.goDown();
                if (arena.leftPressed())
                    snake.goLeft();
                if (arena.rightPressed())
                    snake.goRight();

                snake.move();

                for (int i=0; i<amountOfFood; i++)
                {               
                    if (food[i] == null)
                        food[i] = new Food(arena);

                    if (snake.collides(food[i]))
                    {
                        score += food[i].getPoints();
                        snake.grow(food[i].getPoints());
                        food[i].removeFromScreen();
                        food[i] = null;
                    }
                }  

                amountOfFood = Math.min(1 + score / 25, MAXIMUM_AMOUNT_OF_FOOD);

                if (snake.hasCrashed())
                    playing = false;

                arena.update();
            }

            System.out.println("******** GAME OVER ********");
            System.out.println("-------- " + snake.getLength() + "-------- ");

            snake.removeFromScreen();
            
            for (int i=0; i<amountOfFood; i++)
            {
                if (food[i] != null)
                    food[i].removeFromScreen();

                food[i] = null;
            }
        }
    }

    public static void main(String[] args)
    {
        MrSnakey s = new MrSnakey();
    }
}