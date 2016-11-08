package ru.stqa.pft.mantis.tests.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.tests.appmanager.ApplicationManager;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * Created by owlowl on 19.09.16.
 */
public class MantisTest {
	protected static final ApplicationManager app =
			new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
	
	@BeforeSuite
	public void setUp() throws Exception {
		app.init();
		app.ftp().upload(new File("src/test/resources/config_inc.php"),"config/config_inc.php","config/config_inc.php.bak");
	}
	
	@BeforeMethod()
	public void logTestStart(Method method, Object[] p) {
	
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws IOException {
		app.ftp().restore("config/config_inc.php.bak","config/config_inc.php");
		app.stop();
	}
	
}