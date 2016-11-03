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
public class ContactGroupAddingTest  extends AddressBookTest{
	@Test
	public void testAddGroup() {
		app.moveTo().contactsPage();
		Contacts before = app.db().contacts();
		
		ContactData toModify = before.iterator().next();
		GroupData toAdd =app.db().groups().iterator().next();
		
		app.contacts().setGroup(toModify,toAdd);
		app.moveTo().contactsPage();
		ContactData after = app.db().contacts().getbyID(toModify.getId());
		Assert.assertNotNull(after);
		Assert.assertEquals(after.getGroups(),toModify.inGroup(toAdd).getGroups());
	}
}
