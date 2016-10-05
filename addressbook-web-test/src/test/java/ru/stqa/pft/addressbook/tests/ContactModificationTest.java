package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactModificationTest extends AddressBookTest{
	@Test
	public void modifyContact(){
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		app.getNavigation().openContacts();
		if(!app.getContactHelper().isAnyContactsThere()){
			app.getContactHelper().createContact(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001","TestGroupName"));
			app.getNavigation().openContacts();
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		int modifiedIndex=2;
		ContactData modified=new ContactData("Fname", "LName", "addr", "222", "email@test.net", "11.12.1992", "17.10.2001", null);
		modified.setId(before.get(modifiedIndex).getId());
		app.getContactHelper().editContact(modifiedIndex);
		app.getContactHelper().fillContactForm(modified, false);
		app.getContactHelper().saveContact();
		app.getNavigation().openContacts();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());
	
		before.remove(modifiedIndex);
		
		before.add(modified);
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(after, before);
	}
}
