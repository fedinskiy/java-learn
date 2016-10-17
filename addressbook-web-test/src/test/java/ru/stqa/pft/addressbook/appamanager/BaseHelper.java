package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.File;

/**
 * Created by owlowl on 23.09.16.
 */
public class BaseHelper {
	protected final RemoteWebDriver wd;
	
	public BaseHelper(RemoteWebDriver wd) {
		this.wd = wd;
	}
	
	public void pressButtonByXPath(String xpath) {
		wd.findElement(By.xpath(xpath)).click();
	}
	
	public void pressButton(String name) {
		wd.findElement(By.name(name)).click();
	}
	
	public void selectElement() {
		selectElement(0);
	}
	
	public void selectElement(int i) {
		WebElement selector = wd.findElements(By.name("selected[]")).get(i);
		if (!selector.isSelected()) {
			selector.click();
		}
	}
	
	public void acceptDialog() {
		wd.switchTo().alert().accept();
	}
	
	public String getFieldValue(String fieldName) {
		return getFieldValue(By.name(fieldName));
	}
	
	public String getFieldValue(By locator) {
		WebElement contentHolder;
		String curText;
		contentHolder = wd.findElement(locator);
		Assert.assertNotNull(contentHolder);
		if ("textarea" == contentHolder.getTagName()) {
			curText = contentHolder.getText();
		} else {
			curText = contentHolder.getAttribute("value");
		}
		return curText;
	}
	
	public void putFile(String name, File file) {
		putFile(By.name(name), file);
	}
	
	public void putFile(By locator, File filepath) {
		if (null != filepath && filepath.isFile()) {
			wd.findElement(locator).sendKeys(filepath.getAbsolutePath());
		}
	}
	
}
