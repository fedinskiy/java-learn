package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactDeletionTest extends AddressBookTest {
	
	@Test//(enabled = false)
	public void deleteContactFromTable() {
		ensurePreconditions();
		List<ContactData> before = app.contacts().getList();
		int deleteIndex = 0;
		
		app.contacts().delete(deleteIndex);
		
		app.moveTo().contactsPage();
		List<ContactData> after = app.contacts().getList();
		Assert.assertEquals(after.size(), before.size() - 1);
		before.remove(deleteIndex);
		after.sort(app.contacts().getComparator());
		before.sort(app.contacts().getComparator());
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
