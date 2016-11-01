package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

/**
 * Created by owlowl on 14.10.16.
 */
public class ContactPhoneTest extends AddressBookTest {
	@Test
	public void testPhones() {
		app.moveTo().contactsPage();
		final boolean asPlainText=true;
		ContactData fromTable = app.contacts().getSet().iterator().next();
		ContactData fromPage = app.contacts().infoFromEditForm(fromTable);
		Assert.assertEquals(fromPage.getId(),fromTable.getId());
		Assert.assertEquals(fromPage.getAllPhones(asPlainText),fromTable.getAllPhones(asPlainText));
	}
	
	
	
}
