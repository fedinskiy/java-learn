package ru.stqa.pft.sandbox;

/**
 * Created by owlowl on 14.09.16.
 */
public class Application {
	public static void main(String[] args)
	{
		Point first, second;
		Square sq;
		Rectangle rq;
		
		first=new Point(0);
		second=new Point(-3,4);
		System.out.println(distance(first,second));
		System.out.println(first.distance(second));
		System.out.println(second.distance(first));
		
		sq=new Square(2);
		rq=new Rectangle(3,5);
		System.out.println(sq.area());
		System.out.println(rq.area());
	}
	public static double distance(Point p1, Point p2)
	{
		final int squarePower=2;
		int distX, distY;
		distX=Math.abs(p1.getX()-p2.getX());
		distY=Math.abs(p1.getY()-p2.getY());
		return Math.sqrt(Math.pow(distX,squarePower)+Math.pow(distY,squarePower));
	}
}
