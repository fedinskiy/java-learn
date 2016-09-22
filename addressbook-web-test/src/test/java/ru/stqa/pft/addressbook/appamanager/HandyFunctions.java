package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by owlowl on 22.09.16.
 */
public class HandyFunctions {
	public static void setFieldValue(String fieldName, String newValue, FirefoxDriver wd) {
	    wd.findElement(By.name(fieldName)).click();
	    wd.findElement(By.name(fieldName)).clear();
	    wd.findElement(By.name(fieldName)).sendKeys(newValue);
	}
	
	public static boolean isAlertPresent(FirefoxDriver wd) {
	    try {
	        wd.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}
	
	public static void chooseInSelector(final int selectorNumber, final int optionNumber, FirefoxDriver wd) {
		String expression = "//div[@id='content']/form/select[" + selectorNumber + "]//option[" + optionNumber + "]";
		if (!wd.findElement(By.xpath(expression)).isSelected()) {
	        wd.findElement(By.xpath(expression)).click();
	    }
	}
}
