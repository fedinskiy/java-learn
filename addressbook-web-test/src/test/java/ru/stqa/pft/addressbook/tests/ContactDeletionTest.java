package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactDeletionTest extends AddressBookTest {
	
	@Test
	public void deleteContactFromTable() {
		ensurePreconditions();
		Contacts before = app.contacts().getSet();
		ContactData toDelete = before.iterator().next();
		
		app.contacts().delete(toDelete);
		app.moveTo().contactsPage();
		
		Assert.assertEquals(app.contacts().getCount(), before.size() - 1);
		Contacts after = app.contacts().getSet();
		
		Assert.assertEquals(after, before.without(toDelete));
	}
	
	
	public void ensurePreconditions() {
		app.moveTo().contactsPage();
		if (app.contacts().getList().size() == 0) {
			app.contacts().create(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName"));
			app.moveTo().contactsPage();
		}
	}
}
