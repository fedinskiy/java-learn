package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

/**
 * Created by owlowl on 22.09.16.
 */
public class ContactHelper extends BaseHelper {
	
	private Contacts contactsCash;
	private static final Comparator<ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
	
	public ContactHelper(RemoteWebDriver wd) {
		super(wd);
	}
	public Comparator<ContactData> getComparator() {
		return byId;
	}
	public void fillContactForm(ContactData contactData, boolean creation) {
		HandyFunctions.setFieldValue("firstname", contactData.getFirstName(), wd);
		HandyFunctions.setFieldValue("lastname", contactData.getLastName(), wd);
		HandyFunctions.setFieldValue("address", contactData.getAddress(), wd);
		HandyFunctions.setFieldValue("mobile", contactData.getMobilePhone(), wd);
		HandyFunctions.setFieldValue("email", contactData.getEmail(), wd);
		
		if (null != contactData.getBirth()) {
			HandyFunctions.chooseInSelector(1, contactData.getBirth().getSelectorDay(), wd);
			HandyFunctions.chooseInSelector(2, contactData.getBirth().getSelectorMonth(), wd);
			HandyFunctions.setFieldValue("byear", contactData.getBirth().getYear(), wd);
		}
		if (null != contactData.getAnniversary()) {
			HandyFunctions.chooseInSelector(3, contactData.getAnniversary().getSelectorDay(), wd);
			HandyFunctions.chooseInSelector(4, contactData.getAnniversary().getSelectorMonth(), wd);
			HandyFunctions.setFieldValue("ayear", contactData.getAnniversary().getYear(), wd);
		}
		//selectContactGroup(2);
		if (creation) {
			selectContactGroup(contactData.getGroup());
		}
	}
	
	private void selectContactGroup(int groupNumber) {
		HandyFunctions.chooseInSelector(5, groupNumber, wd);
	}
	
	private void selectContactGroup(String groupName) {
		HandyFunctions.chooseInSelector(wd, "new_group", groupName);
	}
	
	public void initContact() {
		wd.findElement(By.linkText("add new")).click();
	}
	
	public void editContact(int n) {
		this.pressButtonByXPath("//table[@id='maintable']/tbody/tr[" + String.valueOf(n+2) + "]/td[8]/a/img");
	}
	
	public void saveContact() {
		this.pressButton("update");
	}
	
	public void choose() {
		selectElement();
	}
	
	public void choose(int i) {
		selectElement(i);
	}
	
	public void pressDeleteButton() {
		pressButtonByXPath("//div[@id='content']/form[2]/div[2]/input");
	}
	
	
	public void create(ContactData contactData) {
		initContact();
		fillContactForm(contactData, true);
		this.contactsCash=null;
		pressButtonByXPath("//div[@id='content']/form/input[21]");
		
	}
	public Contacts getSet(){
		return getSet(false);
	}
	public Contacts getSet(Boolean regetCache){
		final int NAME_COLUMN_NUMBER=2;
		final int LAST_NAME_COLUMN_NUMBER=1;
		
		if (!(regetCache||null==contactsCash)) {
			return contactsCash;
		}
		contactsCash = new Contacts();
		List<WebElement> pageElements=	wd.findElements(By.name("entry" ));
		for(WebElement we:pageElements){
			String name=we.findElements(By.tagName("td")).get(NAME_COLUMN_NUMBER).getText();
			String lastName=we.findElements(By.tagName("td")).get(LAST_NAME_COLUMN_NUMBER).getText();
			String id =we.findElement(By.className("center")).findElement(By.name("selected[]")).getAttribute("id");
			Assert.assertNotNull(id);
			Assert.assertNotEquals(id,"","Пустое поле id");
			ContactData contact= new ContactData(name,lastName, null,null,null,null,null,null);
			contact.withId(id);
			contactsCash.add(contact);
		}
		return contactsCash;
	}
	public int getCount() {
		return wd.findElements(By.name("selected[]")).size();
	}
	public List<ContactData> getList() {
		final int NAME_COLUMN_NUMBER=2;
		final int LAST_NAME_COLUMN_NUMBER=1;
		List<ContactData> contacts=new ArrayList<ContactData>();
		List<WebElement> pageElements=	wd.findElements(By.name("entry" ));
		for(WebElement we:pageElements){
			String name=we.findElements(By.tagName("td")).get(NAME_COLUMN_NUMBER).getText();
			String lastName=we.findElements(By.tagName("td")).get(LAST_NAME_COLUMN_NUMBER).getText();
			String id =we.findElement(By.className("center")).findElement(By.name("selected[]")).getAttribute("id");
			Assert.assertNotNull(id);
			Assert.assertNotEquals(id,"","Пустое поле id");
			ContactData contact= new ContactData(name,lastName, null,null,null,null,null,null);
			contact.withId(id);
			contacts.add(contact);
		}
		return contacts;
	}
	
	public void delete(ContactData toDelete) {
		selectById(toDelete.getId());
		pressDeleteButton();
		this.contactsCash=null;
		acceptDialog();
	}
	
	private void selectById(int id) {
		wd.findElement(By.cssSelector("input[id='"+id+"']")).click();
	}
	
	public void modify(ContactData modified) {
		editContactById(modified.getId());
		fillContactForm(modified, false);
		this.contactsCash=null;
		saveContact();
	}
	
	private void editContactById(int id) {
		wd.findElement(By.xpath("//a[contains(@href,\"edit.php?id="+id+"\")]")).click();
	}
}