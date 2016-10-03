package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends AddressBookTest {
	
	@Test
	public void testGroupCreation() {
		GroupData newGroup;

		app.getNavigation().gotoGroupPage();
		List<GroupData> before = app.getGroupHelper().getGroupList();
		newGroup = new GroupData("TestGroupNameLambda", null, null);
		app.getGroupHelper().createGroup(newGroup);
		app.getNavigation().gotoGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		
		Assert.assertEquals(after.size(), before.size() + 1);
		
		newGroup.setId(after.stream().max((g1, g2) -> Integer.compare(g1.getIdNumber(), g2.getIdNumber())).get().getIdNumber());
		before.add(newGroup);
		Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
	}
	
}
