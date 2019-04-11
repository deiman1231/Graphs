public class ResizeTest
{
	public static void main(String[] args)
	{
        double rsize = 10;
        double bsize = 1;
		GameArena a = new GameArena(300,300);
		Rectangle r = new Rectangle(150,150, rsize, rsize, "GREEN");
		Ball b = new Ball(150,150, bsize, "RED");
		double speed = 1.0;

		a.addRectangle(r);
		a.addBall(b);

		while(true)
		{
            rsize += speed;
            bsize += speed/2;

            r.setWidth(rsize);
            r.setHeight(rsize);
            b.setSize(bsize); 

			a.pause();
		}
	}
}
