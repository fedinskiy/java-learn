package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends AddressBookTest {
	@DataProvider
	public Iterator<Object[]> loadContacts() {
		List<Object[]> contacts = new ArrayList<Object[]>();
		Contacts loaded;
		try {
			loaded = app.contacts().loadFromDefaultJSON();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("Неожиданное исключение: ");
			e.printStackTrace();
			return null;
		}
		for (ContactData contact : loaded) {
			contacts.add(new Object[]{contact});
		}
		return contacts.iterator();
	}
	
	@Test(dataProvider = "loadContacts")
	public void testContactCreation(ContactData newContact) {
		Groups groups = app.db().groups();
		newContact=newContact.inGroup(groups.iterator().next());
		
		app.moveTo().contactsPage();
		Contacts before = app.db().contacts();
		app.contacts().create(newContact);
		app.moveTo().contactsPage();
		Contacts after = app.db().contacts();
		Assert.assertEquals(before.size() + 1, after.getCount());
		before =	before.withAdded(newContact.withId(after.stream().max(app.contacts().getComparator()).get().getId()));
		Assert.assertEquals(after, before);
		assertThat(after,
				equalTo(before));
	}
	
	@Test(enabled = false)
	public void createContact() {
		ContactData newContact = new ContactData().withFirstName("PhotoNameCrTest").withLastName("LNameCrTest").withAddress("addr")
				.withBirth("15.12.1992").withAnniversary("17.09.2001")
				.withWorkPhone("+8 (920)").withHomePhone("33-95").withMobilePhone("512 16");
		
		File photo = new File("src/test/resources/ctulhu.jpg");
		assert photo.isFile();
		newContact.withPhoto(photo);
		testContactCreation(newContact);
	}

}
