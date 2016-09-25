package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupDeletionTest extends AddressBookTest  {
	@Test
	public void deleteGroup()
	{
		app.getNavigation().gotoGroupPage();
		if(!app.getGroupHelper().isGroupThere()){
			app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		}
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().deleteChosenGroups();
		app.getGroupHelper().returnToGroupPage();;
	}
}
