package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactDeletionTest extends AddressBookTest{
	@Test
	public void deleteContactFromTable(){
		
		app.getNavigation().openContacts();
		if (!app.getContactHelper().isAnyContactsThere()) {
			app.getContactHelper().createContact(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001", "TestGroupName"));
			app.getNavigation().openContacts();
		}
		app.getContactHelper().chooseContact();
		app.getContactHelper().pressDeleteButton();
		app.getContactHelper().acceptDialog();
		app.getNavigation().openContacts();
	}
}
