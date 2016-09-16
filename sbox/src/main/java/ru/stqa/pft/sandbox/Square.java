package ru.stqa.pft.sandbox;

/**
 * Created by owlowl on 15.09.16.
 */
public class Square  {
	int length;
	public Square()
	{
		length=0;
	}
	public Square(int len)
	{
		length=len;
	}
	public double area()
	{
		return Math.pow(length,2);
	}
}
