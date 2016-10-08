package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupDeletionTest extends AddressBookTest  {
	@Test(enabled = false)
	public void deleteGroup()
	{
		int deletedIndex;
		app.getNavigation().gotoGroupPage();
		if(!app.getGroupHelper().isGroupThere()){
			app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		deletedIndex=before.size() - 1;
		app.getGroupHelper().selectGroup(deletedIndex);
		app.getGroupHelper().deleteChosenGroups();
		app.getGroupHelper().returnToGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size()-1);
		before.remove(deletedIndex);
		
		Assert.assertEquals(before,after);
	}
}
