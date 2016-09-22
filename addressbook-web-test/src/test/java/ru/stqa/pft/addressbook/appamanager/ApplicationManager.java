package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by owlowl on 22.09.16.
 */
public class ApplicationManager {
	private AppNavigation appNavigation;
	private ContactHelper contactHelper;
	private FirefoxDriver wd;
	private GroupHelper groupHelper;
	private Session session;
	
	public void init() {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/index.php");
		groupHelper = new GroupHelper(wd);
		contactHelper = new ContactHelper(wd);
		appNavigation = new AppNavigation(wd);
		session=new Session(wd);
		getSession().login("admin", "secret");
	}
	

	
	public void stop() {
		wd.quit();
	}
	
	public GroupHelper getGroupHelper() {
		return groupHelper;
	}
	
	public ContactHelper getContactHelper() {
		return contactHelper;
	}
	
	public AppNavigation getNavigation() {
		return appNavigation;
	}
	
	public Session getSession() {
		return session;
	}
}
