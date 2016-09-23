package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by owlowl on 23.09.16.
 */
public class AppNavigation {
	
	
	private final FirefoxDriver wd;
	
	public AppNavigation(FirefoxDriver wd) {
		this.wd=wd;
	}
	
	public void gotoGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}
	
	public void openContacts() {
		wd.findElement(By.linkText("home")).click();
	}
}
