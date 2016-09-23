package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by owlowl on 23.09.16.
 */
public class BaseHelper {
	protected final FirefoxDriver wd;
	
	public BaseHelper(FirefoxDriver wd) {
		this.wd=wd;
	}
	public void pressButton(String name){
		wd.findElement(By.xpath(name)).click();
	}
}
