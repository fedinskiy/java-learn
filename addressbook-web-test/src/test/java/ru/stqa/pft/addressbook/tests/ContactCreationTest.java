package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;


public class ContactCreationTest extends AddressBookTest {
	
	@Test
	public void createContact() {
		ContactData newContact = new ContactData("First NameForTest", "Last NameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName");
		
		app.moveTo().contactsPage();
		Set<ContactData> before = app.contacts().getSet();
		app.contacts().create(newContact);
		app.moveTo().contactsPage();
		Set<ContactData> after = app.contacts().getSet();
		
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(newContact);
		Assert.assertEquals(after, before);
	}
	
}
