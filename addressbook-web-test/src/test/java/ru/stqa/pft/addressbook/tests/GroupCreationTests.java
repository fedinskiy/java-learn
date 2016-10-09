package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends AddressBookTest {
	
	@Test
	public void testGroupCreation() {
		GroupData newGroup;

		app.moveTo().groupsPage();
		List<GroupData> before = app.groups().getList();
		newGroup = new GroupData().withName("TestGroupNameLambda");
		app.groups().create(newGroup);
		app.moveTo().groupsPage();
		List<GroupData> after = app.groups().getList();
		
		Assert.assertEquals(after.size(), before.size() + 1);
		newGroup.withId(after.stream().max(app.groups().getComparator()).get().getIdNumber());
		before.add(newGroup);
	
		before.sort(app.groups().getComparator());
		after.sort(app.groups().getComparator());
		Assert.assertEquals(after,before);
	}
	
}
