package ru.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by owlowl on 04.11.16.
 */
public class RegistrationHelper {
	private final ApplicationManager app;
	private WebDriver wd;
	
	public RegistrationHelper(ApplicationManager app) {
		this.app=app;
		wd=app.getDriver();
	}
	
	public void start(String user1, String s) {
		wd.get(app.configuration().getSignPage());
	}
}
