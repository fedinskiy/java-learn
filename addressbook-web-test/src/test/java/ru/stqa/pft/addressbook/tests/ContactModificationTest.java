package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by owlowl on 24.09.16.
 */
public class ContactModificationTest extends AddressBookTest{
	@Test(enabled = false)
	public void modifyContact(){
		ensurePreconditions();
		List<ContactData> before = app.contacts().getList();
		int modifiedIndex=2;
		ContactData modified=new ContactData("Fname", "LName", "addr", "222", "email@test.net", "11.12.1992", "17.10.2001", null);
		
		modified.setId(before.get(modifiedIndex).getId());
		
		app.contacts().modify(modifiedIndex, modified);
		
		app.moveTo().contactsPage();
		List<ContactData> after = app.contacts().getList();
		Assert.assertEquals(after.size(), before.size());
	
		before.remove(modifiedIndex);
		
		before.add(modified);
		before.sort(app.contacts().getComparator());
		after.sort(app.contacts().getComparator());
		Assert.assertEquals(after, before);
	}
	

	
	@BeforeMethod
	public void ensurePreconditions() {
		app.moveTo().contactsPage();
		if(app.contacts().getList().size()==0){
			app.contacts().create(new ContactData("FirstNameForTest", "LastNameForTest", "addr", "mobilephone", "email", "15.12.1992", "17.09.2001","TestGroupName"));
			app.moveTo().contactsPage();
		}
	}
}
