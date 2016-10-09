package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreationTest extends AddressBookTest {
	
	@Test//(enabled = false)
	public void createContact() {
		ContactData newContact = new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName");
		
		app.moveTo().contactsPage();
		List<ContactData> before = app.contacts().getList();
		app.contacts().create(newContact);
		app.moveTo().contactsPage();
		List<ContactData> after = app.contacts().getList();
		
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(newContact);
		
		before.sort(app.contacts().getComparator());
		after.sort(app.contacts().getComparator());
		Assert.assertEquals(after, before);
	}
	
}
