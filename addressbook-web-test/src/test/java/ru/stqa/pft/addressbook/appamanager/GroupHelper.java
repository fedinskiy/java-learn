package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.stqa.pft.addressbook.appamanager.HandyFunctions.setFieldValue;

/**
 * Created by owlowl on 22.09.16.
 */
public class GroupHelper extends BaseHelper {

	private static final Comparator<? super GroupData> byId= (g1, g2)-> Integer.compare(g1.getIdNumber(), g2.getIdNumber());
	public Comparator<? super GroupData> getComparator() {
		return byId;
	}
	
	public GroupHelper(RemoteWebDriver wd) {
		super(wd);
	}
	
	public void openGroup() {
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
		return HandyFunctions.isElementPresent(wd,By.name("selected[]"));
	}
	
	public void createGroup(GroupData groupData) {
		initGroup();
		fillGroupForm(groupData);
		submitGroupCreation();
		returnToGroupPage();
	}
	
	
	public int getGroupCount() {
		return wd.findElements(By.name("selected[]" )).size();
	}
	
	public void selectGroup(int i) {
		selectElement(i);
	}
	
	public List<GroupData> getGroupList() {
		List<GroupData> groups=new ArrayList<GroupData>();
		List<WebElement> pageElements=	wd.findElements(By.cssSelector("span.group" ));
		for(WebElement we:pageElements){
			String name=we.getText();
			String id = we.findElement(By.tagName("input")).getAttribute("value");
			GroupData group= new GroupData(id,name,null, null);
			groups.add(group);
		}
		return groups;
		
	}
}
