public class ArrowTest
{
    public static void main(String[] args)
    {
        GameArena a = new GameArena(500,500);
        Arrow arrow = new Arrow(150,150,300,300,5,"GREEN",a);

        while(true)
            a.update();
    }
}
