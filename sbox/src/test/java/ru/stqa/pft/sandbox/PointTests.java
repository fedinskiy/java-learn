package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

/**
 * Created by owlowl on 16.09.16.
 */
public class PointTests {
	
	@Test
	public void testReversal()
	{
		Point p1,p2;
		p1=new Point(0,0);
		p2=new Point(1,1);
		Assert.assertEquals(p1.distance(p2),p2.distance(p1));
		
	}
	
	@Test
	public void testDistanceGypsy()
	{
		Assert.assertEquals(testDistance(0,0,3,4),5.0);
		
	}
	
	@Test
	public void testDistancePifagorean()
	{
		Assert.assertEquals(testDistance(0,0,3,4),5.0);
		
	}
	
	@Test
	public void testDistanceNegativePifagorean()
	{
		Assert.assertEquals(testDistance(-4,-6,-1,-2),5.0);
		
	}
	
	@Test
	public void testDistanceRandom()
	{
		Assert.assertEquals(testDistance(-4,7,12,15),17.88854381999832);
		
	}
	
	@Test
	public void testDistanceZero()
	{
		Assert.assertEquals(testDistance(1,1,1,1),0.0);
		
	}
	
	public double testDistance(int x1, int y1, int x2, int y2)
	{
		Point p1,p2;
		p1=new Point(x1,y1);
		p2=new Point(x2,y2);
		return p1.distance(p2);
	}
}
