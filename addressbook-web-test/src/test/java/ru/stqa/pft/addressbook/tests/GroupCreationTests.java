package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends AddressBookTest {
	
	@Test
	public void testGroupCreation() {
		app.getNavigation().gotoGroupPage();
		List<GroupData> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().createGroup(new GroupData("TestGroupName", null, null));
		app.getNavigation().gotoGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		
		Assert.assertEquals(after.size(), before.size() + 1);
	}
	
}
