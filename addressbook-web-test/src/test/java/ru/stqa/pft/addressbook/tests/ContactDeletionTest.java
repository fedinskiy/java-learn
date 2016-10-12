package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactDeletionTest extends AddressBookTest {
	
	@Test
	public void deleteContactFromTable() {
		ensurePreconditions();
		Set<ContactData> before = app.contacts().getSet();
		ContactData toDelete=before.iterator().next();
				
		app.contacts().delete(toDelete);
		
		app.moveTo().contactsPage();
		Set<ContactData> after = app.contacts().getSet();
		Assert.assertEquals(after.size(), before.size() - 1);
		before.remove(toDelete);
		Assert.assertEquals(after, before);
	}
	

	
	public void ensurePreconditions() {
		app.moveTo().contactsPage();
		if (app.contacts().getList().size()==0) {
			app.contacts().create(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName"));
			app.moveTo().contactsPage();
		}
	}
}
