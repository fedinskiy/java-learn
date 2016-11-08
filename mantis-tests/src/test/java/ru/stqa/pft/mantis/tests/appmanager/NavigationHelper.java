package ru.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by owlowl on 08.11.16.
 */
public class NavigationHelper extends BaseHelper{
	private ApplicationManager app;
	private RemoteWebDriver wd;
	
	public NavigationHelper(ApplicationManager app) {
		super(app);
		this.app = app;
		wd= app.getDriver();
	}
	
	public void login(String username, String password){
		wd.get(app.configuration().getEntryPoint());
		setFieldValue("username",username);
		setFieldValue("password",password);
		pressButtonByXPath(".//*[@id='login-form']/fieldset/span/input[@value='Login']");
	}
	public void editUser(String name){
		wd.get(app.configuration().getBase()+"manage_user_page.php");
		wd.findElement(By.xpath(".//*[@id='manage-user-div']/table/tbody//td[1]/*[contains(., '"+name+"')]")).click();
		System.out.println("!!");
	}
	
	public void resetPassword(String name){
		editUser(name);
		pressButtonByXPath(".//*[@id='manage-user-reset-form']/fieldset/span/input[@value='Reset Password']");
	}
	
	public void editPersonalInfo(String confirmationLink, String password) {
		wd.get(confirmationLink);
		setFieldValue("password",password);
		setFieldValue("password_confirm",password);
		wd.findElement(By.cssSelector("input[value='Update User']")).click();
	}
}
