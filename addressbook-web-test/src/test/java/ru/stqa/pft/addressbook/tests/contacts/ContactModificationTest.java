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
public class ContactModificationTest extends AddressBookTest {
	@Test
	public void modifyContact() {
		app.moveTo().contactsPage();
		Contacts before = app.db().contacts();
		
		ContactData toModify = before.iterator().next();
		ContactData modified = new ContactData().withFirstName("First Name").withLastName("Last Name").withAddress("address").withId(toModify.getId()).withBirth("10.03.2016").withAnniversary("27.05.1985");
		
		app.contacts().modify(modified);
		app.moveTo().contactsPage();
		
		Assert.assertEquals(app.contacts().getCount(), before.size());
		
		Contacts after = app.db().contacts();
		Assert.assertEquals(after, before.without(toModify).withAdded(modified));
	}

}
