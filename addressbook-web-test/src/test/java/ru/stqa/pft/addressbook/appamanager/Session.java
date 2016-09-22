package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by owlowl on 23.09.16.
 */
public class Session {
	FirefoxDriver wd;
	
	public Session(FirefoxDriver wd) {
	this.wd=wd;
	}
	public void login(String username, String password) {
		HandyFunctions.setFieldValue("user", username, wd);
		HandyFunctions.setFieldValue("pass", password, wd);
		wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
	}
}
