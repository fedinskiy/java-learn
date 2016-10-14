package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.AddressBookTest;


public class ContactCreationTest extends AddressBookTest {
	
	@Test
	public void createContact() {
		ContactData newContact = new ContactData().withFirstName("FNameCrTest").withLastName("LNameCrTest").withAddress("addr")
				.withBirth("15.12.1992").withAnniversary("17.09.2001").withGroup("TestGroupName")
				.withWorkPhone("+8 (920)").withHomePhone("33-95").withMobilePhone("512 16");
		
		
		app.moveTo().contactsPage();
		Contacts before = app.contacts().getSet();
		app.contacts().create(newContact);
		app.moveTo().contactsPage();
		
		Assert.assertEquals(before.size() + 1, app.contacts().getCount());
		
		Contacts after = app.contacts().getSet();
		Assert.assertEquals(after, before.withAdded(newContact.withId(after.stream().max(app.contacts().getComparator()).get().getId())));
	}
	
}
