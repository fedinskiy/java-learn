package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by owlowl on 22.09.16.
 */
public class ContactHelper extends BaseHelper {

	
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
		
		
		HandyFunctions.chooseInSelector(1, contactData.getBirth().getSelectorDay(), wd);
		HandyFunctions.chooseInSelector(2, contactData.getBirth().getSelectorMonth(), wd);
		HandyFunctions.setFieldValue("byear", contactData.getBirth().getYear(), wd);
		HandyFunctions.chooseInSelector(3, contactData.getAnniversary().getSelectorDay(), wd);
		HandyFunctions.chooseInSelector(4, contactData.getAnniversary().getSelectorMonth(), wd);
		HandyFunctions.setFieldValue("ayear", contactData.getAnniversary().getYear(), wd);
		
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
	
	public void chooseContact() {
		selectElement();
	}
	
	public void chooseContact(int i) {
		selectElement(i);
	}
	
	public void pressDeleteButton() {
		pressButtonByXPath("//div[@id='content']/form[2]/div[2]/input");
	}
	
	
	public void createContact(ContactData contactData) {
		initContact();
		fillContactForm(contactData, true);
		pressButtonByXPath("//div[@id='content']/form/input[21]");
		
	}
	
	public boolean isAnyContactsThere() {
		String pageCounterValue = wd.findElement(By.id("search_count")).getText();
		int numberOfElements=(pageCounterValue.isEmpty())?0:Integer.parseInt(pageCounterValue);
		return numberOfElements>0;
	}
	
	public List<ContactData> getContactList() {
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
			contact.setId(id);
			contacts.add(contact);
		}
		return contacts;
	}
}