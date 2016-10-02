package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

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
		selectElement(0);
	}
	public void selectElement(int i) {
		WebElement selector= wd.findElements(By.name("selected[]")).get(i);
		if (!selector.isSelected()) {
			selector.click();
		}
	}
	public void acceptDialog(){
		wd.switchTo().alert().accept();
	}
}
