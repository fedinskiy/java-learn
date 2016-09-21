package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;


public class GroupCreationTests  extends AddressBookTest {
	private FirefoxDriver wd;
	
	@BeforeMethod
	public void setUp() throws Exception {
		wd = new FirefoxDriver();
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/index.php");
		login("admin", "secret");
	}
	

	
	@Test
	public void testGroupCreation() {
		gotoGroupPage();
		initGroup();
		fillGroupForm(new GroupData("TestGroupName", "TestGroupHeader", "TestGroupFooter"));
		submitGroupCreation();
		returnToGroupPage();
	}
	
	private void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	private void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	private void fillGroupForm(GroupData groupData) {
		setFieldValue("group_name",groupData.getName());
		setFieldValue("group_header",groupData.getHeader());
		setFieldValue("group_footer",groupData.getFooter());
	}
	
	private void initGroup() {
		wd.findElement(By.name("new")).click();
	}
	
	private void gotoGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		wd.quit();
	}
	
}
