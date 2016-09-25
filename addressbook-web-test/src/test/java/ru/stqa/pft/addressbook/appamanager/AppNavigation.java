package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.isElementPresent;

/**
 * Created by owlowl on 23.09.16.
 */
public class AppNavigation {
	
	
	private final RemoteWebDriver wd;
	
	public AppNavigation(RemoteWebDriver wd) {
		this.wd = wd;
	}
	
	public void gotoGroupPage() {
		if (isElementPresent(wd, By.tagName("h1"))
				&& wd.findElement(By.tagName("h1")).getText().equals("Groups")
				&& isElementPresent(wd, By.name("new"))) {
			return;
		}
		HandyFunctions.click("groups", wd);
	}
	

	
	public void openContacts() {
		if (!isElementPresent(wd, By.id("maintable"))) {
			HandyFunctions.click("home", wd);
		}
	}
}
