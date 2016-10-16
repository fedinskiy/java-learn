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
		final boolean asPlainText=true;
		ContactData fromTable = app.contacts().getSet().iterator().next();
		ContactData fromPage = app.contacts().infoFromEditForm(fromTable);
		Assert.assertEquals(fromPage.getId(),fromTable.getId());
		Assert.assertEquals(fromPage.getAddress(),fromTable.getAddress());
	}
	@BeforeMethod
	public void ensurePreconditions() {
		app.moveTo().contactsPage();
		if (app.contacts().getSet().size() == 0) {
			app.contacts().create(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName"));
			app.moveTo().contactsPage();
		}
	}
}
