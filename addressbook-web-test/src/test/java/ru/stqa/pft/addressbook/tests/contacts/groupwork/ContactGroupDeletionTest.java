package ru.stqa.pft.addressbook.tests.contacts.groupwork;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

/**
 * Created by owlowl on 02.11.16.
 */
public class ContactGroupDeletionTest extends AddressBookTest{
	@Test
	public void testDeleteGroup() {
		app.moveTo().contactsPage();
		
		ContactData toModify = app.db().contacts().iterator().next();
		
		GroupData toDelete =toModify.getGroups().iterator().next();
		app.contacts().deleteGroup(toModify,toDelete);
		
		app.moveTo().contactsPage();
		ContactData after = app.db().contacts().getbyID(toModify.getId());
		Assert.assertNotNull(after);
		Assert.assertEquals(after.getGroups(),toModify.withoutGroup(toDelete).getGroups());
	}
}
