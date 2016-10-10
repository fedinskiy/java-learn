package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.ToIntFunction;


public class GroupCreationTests extends AddressBookTest {
	
	@Test
	public void testGroupCreation() {
		GroupData newGroup;

		app.moveTo().groupsPage();
		Set<GroupData> before = app.groups().getSet();
		newGroup = new GroupData().withName("TestGroupNameSets");
		app.groups().create(newGroup);
		app.moveTo().groupsPage();
		Set<GroupData> after = app.groups().getSet();
		
		Assert.assertEquals(after.size(), before.size() + 1);
		newGroup.withId(after.stream().max(app.groups().getComparator()).get().getIdNumber());
		ToIntFunction<? super GroupData> toInt = (g) -> g.getIdNumber();
		newGroup.withId(after.stream().mapToInt(toInt).max().getAsInt());
		before.add(newGroup);
		
		Assert.assertEquals(after,before);
	}
	
}
