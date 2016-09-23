package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.setFieldValue;

/**
 * Created by owlowl on 22.09.16.
 */
public class GroupHelper extends BaseHelper{
	
	public GroupHelper(FirefoxDriver wd) {
		super(wd);
	}
	
	public void openGroup() {
		if (!wd.findElement(By.name("selected[]")).isSelected()) {
			wd.findElement(By.name("selected[]")).click();
		}
		pressButton("edit");
	}

	public void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	public void fillGroupForm(GroupData groupData) {
		setFieldValue("group_name",groupData.getName(), wd);
		setFieldValue("group_header",groupData.getHeader(),wd);
		setFieldValue("group_footer",groupData.getFooter(),wd);
	}
	
	public void initGroup() {
		wd.findElement(By.name("new")).click();
	}
	
	public void saveChanges() {
		this.pressButton("update");
	}
}
