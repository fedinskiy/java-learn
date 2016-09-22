package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.setFieldValue;

/**
 * Created by owlowl on 22.09.16.
 */
public class GroupHelper {
	
	private final FirefoxDriver wd;
	
	public GroupHelper(FirefoxDriver wd) {
		this.wd=wd;	
	}
	

	public void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	public void fillGroupForm(GroupData groupData, AddressBookTest addressBookTest) {
		setFieldValue("group_name",groupData.getName(), wd);
		setFieldValue("group_header",groupData.getHeader(),wd);
		setFieldValue("group_footer",groupData.getFooter(),wd);
	}
	
	public void initGroup() {
		wd.findElement(By.name("new")).click();
	}
}
