package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appamanager.ApplicationManager;
import ru.stqa.pft.addressbook.tests.groups.GroupCreationTests;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * Created by owlowl on 19.09.16.
 */
public class AddressBookTest {
	Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);
	protected static final ApplicationManager app =
			new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
	
	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
	}
	
	@BeforeMethod
	public void logTestStart(Method method, Object[] p) {
		logger.info("Start test " + method.getName());
		logger.debug("test parametrs are " + Arrays.asList(p));
	}
	
	@AfterMethod(alwaysRun = true)
	public void logTestFinish(Method method) {
		logger.info("Stop test " + method.getName());
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		app.stop();
	}
	
}
