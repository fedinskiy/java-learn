package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupModificationTest extends AddressBookTest {
	@Test
	public void testGroupModification() {
		app.getNavigation().gotoGroupPage();
		if (!app.getGroupHelper().isGroupThere()) {
			app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		}
		app.getGroupHelper().openGroup();
		app.getGroupHelper().fillGroupForm(new GroupData("t1", "t2", "t3"));
		app.getGroupHelper().saveChanges();
		app.getGroupHelper().returnToGroupPage();
	}
}
