package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.setFieldValue;

/**
 * Created by owlowl on 22.09.16.
 */
public class GroupHelper extends BaseHelper {
	
	public GroupHelper(RemoteWebDriver wd) {
		super(wd);
	}
	
	public void openGroup() {
		selectGroup();
		pressButton("edit");
	}
	
	public void selectGroup() {
		selectElement();
	}
	
	public void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	public void fillGroupForm(GroupData groupData) {
		setFieldValue("group_name", groupData.getName(), wd);
		setFieldValue("group_header", groupData.getHeader(), wd);
		setFieldValue("group_footer", groupData.getFooter(), wd);
	}
	
	public void initGroup() {
		wd.findElement(By.name("new")).click();
	}
	
	public void saveChanges() {
		this.pressButton("update");
	}
	
	
	public void deleteChosenGroups() {
		this.pressButton("delete");
	}
	
	public boolean isGroupThere() {
		return HandyFunctions.isElementPresent(wd,By.name("selected"));
	}
	
	public void createGroup(GroupData groupData) {
		initGroup();
		fillGroupForm(groupData);
		submitGroupCreation();
		returnToGroupPage();
	}
	
	
}
