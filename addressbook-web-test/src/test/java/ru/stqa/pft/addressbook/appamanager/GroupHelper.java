package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.setFieldValue;

/**
 * Created by owlowl on 22.09.16.
 */
public class GroupHelper extends BaseHelper {
	private Groups groupCache;
	private static final Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getIdNumber(), g2.getIdNumber());
	
	public Comparator<? super GroupData> getComparator() {
		return byId;
	}
	
	public GroupHelper(RemoteWebDriver wd) {
		super(wd);
	}
	
	public void openGroup() {
		pressButton("edit");
	}
	
	private void selectById(int idNumber) {
		wd.findElement(By.cssSelector("input[value='" + idNumber + "']")).click();
	}
	
	public void returnToGroupPage() {
		wd.findElement(By.linkText("group page")).click();
	}
	
	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}
	
	public void fillForm(GroupData groupData) {
		setFieldValue("group_name", groupData.getName(), wd);
		setFieldValue("group_header", groupData.getHeader(), wd);
		setFieldValue("group_footer", groupData.getFooter(), wd);
	}
	
	public void init() {
		wd.findElement(By.name("new")).click();
	}
	
	public void saveChanges() {
		this.pressButton("update");
	}
	
	
	public void deleteChosen() {
		this.pressButton("delete");
	}
	
	
	public void create(GroupData groupData) {
		init();
		fillForm(groupData);
		submitGroupCreation();
		groupCache=null;
		returnToGroupPage();
	}
	
	public Groups getSet(Boolean resetCache) {
		if (!(resetCache || null == groupCache)) {
			return new Groups(groupCache);
		}
		groupCache = new Groups();
		List<WebElement> pageElements = wd.findElements(By.cssSelector("span.group"));
		for (WebElement we : pageElements) {
			String name = we.getText();
			String id = we.findElement(By.tagName("input")).getAttribute("value");
			groupCache.add(new GroupData().withId(id).withName(name));
		}
		return new Groups(groupCache);
	}
	
	public Groups getSet() {
		return getSet(false);
	}
	
	public void modify(GroupData modified) {
		selectById(modified.getIdNumber());
		openGroup();
		fillForm(modified);
		groupCache=null;
		saveChanges();
	}
	
	public void delete(GroupData toDelete) {
		selectById(toDelete.getIdNumber());
		deleteChosen();
		groupCache=null;
		returnToGroupPage();
	}
	
	public int getCount() {
		return wd.findElements(By.name("selected[]")).size();
	}
}
