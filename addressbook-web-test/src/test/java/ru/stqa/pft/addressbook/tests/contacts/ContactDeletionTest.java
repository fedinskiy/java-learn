package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactDeletionTest extends AddressBookTest {
	
	@Test
	public void deleteContactFromTable() {
		Contacts before =app.db().contacts();
		ContactData toDelete = before.iterator().next();
		
		app.moveTo().contactsPage();
		app.contacts().delete(toDelete);
		app.moveTo().contactsPage(); //обновляет список
		Contacts after = app.db().contacts();
		Assert.assertEquals(after.getCount(), before.size() - 1);
	
		
		Assert.assertEquals(after, before.without(toDelete));
	}
	
}
