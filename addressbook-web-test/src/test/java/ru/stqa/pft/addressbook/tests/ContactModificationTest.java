package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactModificationTest extends AddressBookTest{
	@Test
	public void modifyContact(){
		app.getNavigation().openContacts();
		if(!app.getContactHelper().isAnyContactsThere()){
			app.getContactHelper().createContact(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001","TestGroupName"));
			app.getNavigation().openContacts();
		}
		app.getContactHelper().editContact(1);
		app.getContactHelper().fillContactForm(new ContactData("Fname", "LName", "addr", "222", "email@test.net", "11.12.1992", "17.10.2001", null), false);
		app.getContactHelper().saveContact();
	}
}
