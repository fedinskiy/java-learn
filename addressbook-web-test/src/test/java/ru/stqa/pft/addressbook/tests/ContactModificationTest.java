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
		//app.getContactHelper().selectElement();
		app.getContactHelper().editContact(4);
		app.getContactHelper().fillContactForm(new ContactData("Fname", "LName", "addr", "222", "email@test.net", "11.12.1992", "17.10.2001", null), false);
		app.getContactHelper().saveContact();
	}
}
