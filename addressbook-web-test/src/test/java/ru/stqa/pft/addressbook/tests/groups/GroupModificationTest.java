package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupModificationTest extends AddressBookTest {
	
	@Test
	public void testGroupModification() {
		GroupData oldVersion;
		app.moveTo().groupsPage();
		Groups before = app.db().groups();
		oldVersion = before.iterator().next();
		GroupData newVersion = new GroupData().withName("T_1").withHeader("T_2").withFooter("T_3").withId(before.iterator().next().getId());
		
		app.groups().modify(newVersion);
		app.groups().returnToGroupPage();
		
		Groups after = app.db().groups();
		Assert.assertEquals(before.size(), after.getCount());
		
		assertThat(after, equalTo(before.without(oldVersion).withAdded(newVersion)));
		verifyGroupListinUI();
	}
	
}
