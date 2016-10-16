package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.AddressBookTest;


/**
 * Created by owlowl on 16.10.16.
 */
public class ContacеFullInfoTest extends AddressBookTest {
	@Test
	public void checkFullInfo() {
		app.moveTo().contactsPage();
		ContactData fromTable = app.contacts().getSet().iterator().next();
		ContactData fromPage = app.contacts().infoFromEditForm(fromTable);
		ContactData fromDetails = app.contacts().infoFromDetails(fromTable);
		Assert.assertEquals(fromPage.getId(),fromDetails.getId());
		Assert.assertEquals(fromPage.getFullInfo(),fromDetails.getFullInfo());
	}
	@BeforeMethod
	public void ensurePreconditions() {
		app.moveTo().contactsPage();
		if (app.contacts().getSet().size() == 0) {
			app.contacts().create(new ContactData().withFirstName("FirstNameForTest").withLastName("LastNameForTest")
					.withAddress("addr").withMobilePhone("+7 (920)").withHomePhone("33-95").withEmail("email1@.wtf.ru").withEmail2("email2@.шо.ru"));
			app.moveTo().contactsPage();
		}
	}
}
