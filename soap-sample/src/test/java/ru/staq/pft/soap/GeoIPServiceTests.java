package ru.staq.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by owlowl on 09.11.16.
 */
public class GeoIPServiceTests {
	@Test
	public void testMyIP(){
		GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
		Assert.assertEquals(geoIP.getCountryCode(),"RUS");
	}
	@Test
	public void testInvalidIP(){
		GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("5.3.203.233");
		Assert.assertEquals(geoIP.getCountryCode(),"RUS");
	}
}
