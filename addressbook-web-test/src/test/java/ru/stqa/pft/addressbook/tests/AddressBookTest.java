package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appamanager.ApplicationManager;


/**
 * Created by owlowl on 19.09.16.
 */
public class AddressBookTest {
	
	protected static final ApplicationManager app =
			new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
	
	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
	}
	
	@AfterSuite
	public void tearDown() {
		app.stop();
	}
	
}
