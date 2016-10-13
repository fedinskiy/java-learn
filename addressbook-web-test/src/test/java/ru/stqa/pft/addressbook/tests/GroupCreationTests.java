package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.function.ToIntFunction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends AddressBookTest {
	
	@Test
	public void testGroupCreation() {
		GroupData newGroup;
		
		app.moveTo().groupsPage();
		Groups before = app.groups().getSet();
		newGroup = new GroupData().withName("TestGroupNameSets");
		app.groups().create(newGroup);
		app.moveTo().groupsPage();
		assertThat(app.groups().getCount(), equalTo(before.size()+1));
		
		Groups after = app.groups().getSet();

		newGroup.withId(after.stream().max(app.groups().getComparator()).get().getIdNumber());
		ToIntFunction<? super GroupData> toInt = (g) -> g.getIdNumber();
		
		assertThat(after, equalTo(
				before.withAdded(newGroup.withId(after.stream().mapToInt(toInt).max().getAsInt()))));
	}
	
	@Test
	public void testGroupCreationNegative() {
		GroupData newGroup;
		
		app.moveTo().groupsPage();
		Groups before = app.groups().getSet();
		newGroup = new GroupData().withName("TestGroupNameSets'");
		app.groups().create(newGroup);
		app.moveTo().groupsPage();
		assertThat(app.groups().getCount(), equalTo(before.size()));
		
		Groups after = app.groups().getSet();
				assertThat(after, equalTo(before));
	}
}
