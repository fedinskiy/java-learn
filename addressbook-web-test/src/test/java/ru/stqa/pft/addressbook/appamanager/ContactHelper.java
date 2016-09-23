package ru.stqa.pft.addressbook.appamanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

/**
 * Created by owlowl on 22.09.16.
 */
public class ContactHelper extends BaseHelper {
	
	public ContactHelper(FirefoxDriver wd) {
		super(wd);
	}
	
	public void fillContactForm(ContactData contactData) {
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
	}
	
	private void selectContactGroup(int groupNumber) {
		HandyFunctions.chooseInSelector(5, groupNumber, wd);
	}
	
	public void initContact() {
		wd.findElement(By.linkText("add new")).click();
	}
	
	public void editContact(int n) {
		this.pressButtonByXPath("//table[@id='maintable']/tbody/tr["+ String.valueOf(n)+"]/td[8]/a/img");
	}
	
	public void saveContact() {
		this.pressButton("update");
	}
	public void chooseContact() {
		selectElement();
	}public void pressDeleteButton() {
		pressButtonByXPath("//div[@id='content']/form[2]/div[2]/input");
	}
	
	
}