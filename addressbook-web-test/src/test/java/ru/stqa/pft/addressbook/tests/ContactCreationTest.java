package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends AddressBookTest {
	
	@Test
	public void createContact() {
		ContactData newContact = new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName");
		
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		app.getNavigation().openContacts();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().createContact(newContact);
		app.getNavigation().openContacts();
		List<ContactData> after = app.getContactHelper().getContactList();
		
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(newContact);
		
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(after, before);
	}
	
}
