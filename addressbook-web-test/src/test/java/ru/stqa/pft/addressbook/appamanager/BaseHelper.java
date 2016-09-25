package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by owlowl on 23.09.16.
 */
public class BaseHelper {
	protected final RemoteWebDriver wd;
	
	public BaseHelper(RemoteWebDriver wd) {
		this.wd=wd;
	}
	public void pressButtonByXPath(String xpath){
		wd.findElement(By.xpath(xpath)).click();
	}
	public void pressButton(String name){
		wd.findElement(By.name(name)).click();
	}
	
	public void selectElement() {
		if (!wd.findElement(By.name("selected[]")).isSelected()) {
			wd.findElement(By.name("selected[]")).click();
		}
	}
	public void acceptDialog(){
		wd.switchTo().alert().accept();
	}
}
