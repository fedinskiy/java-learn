package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by owlowl on 22.09.16.
 */
public class  ApplicationManager {
	public FirefoxDriver wd;
	
	public static boolean isAlertPresent(FirefoxDriver wd) {
	    try {
	        wd.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}
	
	public void init() {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/index.php");
		login("admin", "secret");
	}
	
	public void login(String username, String password) {
	    setFieldValue("user",username);
	    setFieldValue("pass",password);
	    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
	}
	
	public void setFieldValue(String fieldName, String newValue) {
	    wd.findElement(By.name(fieldName)).click();
	    wd.findElement(By.name(fieldName)).clear();
	    wd.findElement(By.name(fieldName)).sendKeys(newValue);
	}
	
	public void stop() {
		wd.quit();
	}
	
	public void chooseInSelector(final int selectorNumber, final int optionNumber) {
		String expression = "//div[@id='content']/form/select[" + selectorNumber + "]//option[" + optionNumber + "]";
		if (!wd.findElement(By.xpath(expression)).isSelected()) {
	        wd.findElement(By.xpath(expression)).click();
	    }
	}
	
	public void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	public void fillGroupForm(GroupData groupData, AddressBookTest addressBookTest) {
		setFieldValue("group_name",groupData.getName());
		setFieldValue("group_header",groupData.getHeader());
		setFieldValue("group_footer",groupData.getFooter());
	}
	
	public void initGroup() {
		wd.findElement(By.name("new")).click();
	}
	
	public void gotoGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}
	
	public void returnToContacts() {
	    wd.findElement(By.linkText("home")).click();
	}
	
	public void fillContactForm(ContactData contactData, AddressBookTest addressBookTest) {
	    setFieldValue("firstname", contactData.getFirstName());
	    setFieldValue("lastname", contactData.getLastName());
	    setFieldValue("address", contactData.getAddress());
	    setFieldValue("mobile", contactData.getMobilePhone());
	    setFieldValue("email", contactData.getEmail());
	
	    
	    chooseInSelector(1, contactData.getBirth().getSelectorDay());
	    chooseInSelector(2, contactData.getBirth().getSelectorMonth());
	    setFieldValue("byear", contactData.getBirth().getYear());
	    chooseInSelector(3, contactData.getAnniversary().getSelectorDay());
	    chooseInSelector(4, contactData.getAnniversary().getSelectorMonth());
	    setFieldValue("ayear", contactData.getAnniversary().getYear());
	    
	    chooseInSelector(5, 2);
	 }
	
	public void initContact() {
	    wd.findElement(By.linkText("add new")).click();
	}
	
	public void openGroups() {
	    wd.get("http://localhost/addressbook/group.php");
	}
}
