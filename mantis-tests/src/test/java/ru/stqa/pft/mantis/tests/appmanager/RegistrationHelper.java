package ru.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by owlowl on 04.11.16.
 */
public class RegistrationHelper extends BaseHelper{
	private final ApplicationManager app;
	private WebDriver wd;
	
	public RegistrationHelper(ApplicationManager app) {
		super(app);
		this.app=app;
		wd=app.getDriver();
	}
	
	public void start(String user, String email) {
		wd.get(app.configuration().getSignPage());
		setFieldValue("username",user);
		setFieldValue("email",email);
		wd.findElement(By.cssSelector("input[value='Signup']")).click();
	}
	
	public void finish(String confirmationLink, String password) {
		wd.get(confirmationLink);
		setFieldValue("password",password);
		setFieldValue("password_confirm",password);
		wd.findElement(By.cssSelector("input[value='Update User']")).click();
	}
}
