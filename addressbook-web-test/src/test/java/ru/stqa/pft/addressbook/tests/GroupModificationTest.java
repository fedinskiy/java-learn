package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupModificationTest extends AddressBookTest {
	@Test
	public void testGroupModification() {
		int modifiedIndex;
		GroupData modified;
		String id;
		app.getNavigation().gotoGroupPage();
		if (!app.getGroupHelper().isGroupThere()) {
			app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		modifiedIndex=before.size()-1;
		app.getGroupHelper().selectGroup(modifiedIndex);
		app.getGroupHelper().openGroup();
		modified=new GroupData(before.get(modifiedIndex).getId(), "t1", "t2", "t3");
		app.getGroupHelper().fillGroupForm(modified);
		app.getGroupHelper().saveChanges();
		app.getGroupHelper().returnToGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
		Comparator<? super GroupData> byId= (g1, g2)-> Integer.compare(g1.getIdNumber(), g2.getIdNumber());
		before.remove(modifiedIndex);
		before.add(modified);
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(after, before);
	}
}
