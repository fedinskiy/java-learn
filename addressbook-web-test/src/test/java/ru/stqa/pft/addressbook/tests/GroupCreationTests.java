package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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
		newGroup.setId(after.stream().max(app.getGroupHelper().getComparator()).get().getIdNumber());
		before.add(newGroup);
	
		before.sort(app.getGroupHelper().getComparator());
		after.sort(app.getGroupHelper().getComparator());
		Assert.assertEquals(after,before);
	}
	
}
