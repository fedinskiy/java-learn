package ru.stqa.pft.sandbox;

/**
 * Created by owlowl on 15.09.16.
 */
public class Rectangle extends Square{
	private int width;
	
	public Rectangle()
	{
		super();
		width=0;
	}
	public Rectangle(int len, int wid)
	{
		super(len);
		width=wid;
	}
	public double area()
	{
		return length*width;
	}
}
