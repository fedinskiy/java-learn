package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.annotations.Test;

/**
 * Created by owlowl on 15.09.16.
 */
public class SquareTests {
	@Test
	public void testArea(){
		Square s= new Square(10);
		
		Assert.assertEquals(s.area(),100.0);
	}
}
 