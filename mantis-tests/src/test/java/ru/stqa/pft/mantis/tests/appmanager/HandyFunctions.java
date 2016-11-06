package ru.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

/**
 * Created by owlowl on 22.09.16.
 */
public class HandyFunctions {
	public static final String STRING_SEPARATOR="\n";
	public static void setFieldValue(String fieldName, String newValue, RemoteWebDriver wd) {
		setFieldValue(newValue, wd, By.name(fieldName));
	}
	
	public static void setFieldValue(String newValue, RemoteWebDriver wd, By locator) {
		wd.findElement(locator).click();
		if (!(null == newValue||newValue.isEmpty())) {
			String curText = wd.findElement(locator).getAttribute("value");
			if (!curText.equals(newValue)) {
				wd.findElement(locator).clear();
				wd.findElement(locator).sendKeys(newValue);
			}
		}
	}
	
	public static boolean isAlertPresent(FirefoxDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	public static void chooseInSelector(final int selectorNumber, final int optionNumber, RemoteWebDriver wd) {
		
		String expression = "//div[@id='content']/form/select[" + selectorNumber + "]//option[" + optionNumber + "]";
		if (!wd.findElement(By.xpath(expression)).isSelected()) {
			wd.findElement(By.xpath(expression)).click();
		}
	}
	
	public static void chooseInSelector(RemoteWebDriver wd, String selectorName, String value) {
		try {
			if (isElementPresent(wd, By.name(selectorName))) {
				new Select(wd.findElement(By.name(selectorName))).selectByVisibleText(value);
			}
		}catch (NoSuchElementException ex){
			System.out.println("Не удалось выбрать группу.");
		}
	}
	
	public static boolean isElementPresent(RemoteWebDriver wd, By locator) {
		try {
			wd.findElement(locator);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
	public static void click(String link, RemoteWebDriver wd) {
		wd.findElement(By.linkText(link)).click();
	}
	public static String cleanPhone(String source) {
		return source.replaceAll("[ \\-()]","" );
	}
	
	public static void ensureFileExistense(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
	}
}
