package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by owlowl on 19.09.16.
 */
public class AddressBookTest {
	
	protected FirefoxDriver wd;
	
	public static boolean isAlertPresent(FirefoxDriver wd) {
	    try {
	        wd.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
	    wd = new FirefoxDriver();
	    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    wd.get("http://localhost/addressbook/index.php");
	    login("admin", "secret");
	}
	
	protected void login(String username, String password) {
	    setFieldValue("user",username);
	    setFieldValue("pass",password);
	    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
	}
	
	protected void setFieldValue(String fieldName, String newValue) {
	    wd.findElement(By.name(fieldName)).click();
	    wd.findElement(By.name(fieldName)).clear();
	    wd.findElement(By.name(fieldName)).sendKeys(newValue);
	}
	
	@AfterMethod
	public void tearDown() {
	    wd.quit();
	}
}
