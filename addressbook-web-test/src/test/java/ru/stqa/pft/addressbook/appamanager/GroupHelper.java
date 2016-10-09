package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	
	public void select() {
		selectElement();
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
		returnToGroupPage();
	}
	
	
	public int getCount() {
		return wd.findElements(By.name("selected[]" )).size();
	}
	
	public void select(int i) {
		selectElement(i);
	}
	
	public List<GroupData> getList() {
		List<GroupData> groups=new ArrayList<GroupData>();
		List<WebElement> pageElements=	wd.findElements(By.cssSelector("span.group" ));
		for(WebElement we:pageElements){
			String name=we.getText();
			String id = we.findElement(By.tagName("input")).getAttribute("value");
			groups.add(new GroupData().withId(id).withName(name));
		}
		return groups;
		
	}
	public void modify(int modifiedIndex, GroupData modified) {
		select(modifiedIndex);
		openGroup();
		fillForm(modified);
		saveChanges();
	}
	public void delete(int deletedIndex) {
		select(deletedIndex);
		deleteChosen();
		returnToGroupPage();
	}
}
