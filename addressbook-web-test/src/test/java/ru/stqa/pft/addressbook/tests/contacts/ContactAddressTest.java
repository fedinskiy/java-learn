package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;


/**
 * Created by owlowl on 16.10.16.
 */
public class ContactAddressTest extends AddressBookTest {
	@Test
	public void testPhones() {
		app.moveTo().contactsPage();
		ContactData fromTable = app.contacts().getSet().iterator().next();
		ContactData fromPage = app.contacts().infoFromEditForm(fromTable);
		Assert.assertEquals(fromPage.getId(),fromTable.getId());
		Assert.assertEquals(fromPage.getAddress(),fromTable.getAddress());
	}
	
}
