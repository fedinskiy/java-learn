package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactDeletionTest extends AddressBookTest{
	@Test
	public void deleteContactFromTable(){
		app.getNavigation().openContacts();
		app.getContactHelper().chooseContact();
		app.getContactHelper().pressDeleteButton();
		app.getContactHelper().acceptDialog();
	}
}
