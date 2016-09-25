package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by owlowl on 22.09.16.
 */
public class ApplicationManager {
	private AppNavigation appNavigation;
	private ContactHelper contactHelper;
	private RemoteWebDriver wd;
	private GroupHelper groupHelper;
	private Session session;
	private String browser;
	
	public ApplicationManager(String browser) {
		this.browser = browser;
	}
	
	
	public void init() {
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver();
		}else if(BrowserType.CHROME.equals(browser)) {
			wd = new ChromeDriver();
		}else if(BrowserType.IE.equals(browser)) {
			wd = new InternetExplorerDriver();
		
		}
		
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
