package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appamanager.ApplicationManager;


/**
 * Created by owlowl on 19.09.16.
 */
public class AddressBookTest {
	
	protected final ApplicationManager app = new ApplicationManager();
	
	@BeforeMethod
	public void setUp() throws Exception {
		app.init();
	}
	
	@AfterMethod
	public void tearDown() {
		app.stop();
	}
	
}
