package ru.stqa.pft.sandbox;

/**
 * Created by owlowl on 14.09.16.
 */
public class Point {
	private int x;
	private int y;
	
	public Point()
	{
		x=0;
		y=0;
	}
	
	public Point(int val)
	{
		//if (val<0) throw new IllegalArgumentException();
		x=val;
		y=val;
	}
	public Point(int val1, int val2)
	{
		//if (val1<0) throw new IllegalArgumentException("Value "+String.valueOf(val1)+" is unacceptable");
		//if (val2<0) throw new IllegalArgumentException("Value "+String.valueOf(val2)+" is unacceptable");
		x=val1;
		y=val2;
	}
	public int getX()
	{return x;}
	
	public int getY()
	{return y;}
	
	public double distance(Point second)
	{
		final int squarePower=2;
		int distX, distY;
		distX=Math.abs(this.getX()-second.getX());
		distY=Math.abs(this.getY()-second.getY());
		return Math.sqrt(Math.pow(distX,squarePower)+Math.pow(distY,squarePower));
	}
}