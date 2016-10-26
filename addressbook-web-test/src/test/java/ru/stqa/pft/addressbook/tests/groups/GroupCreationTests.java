package ru.stqa.pft.addressbook.tests.groups;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.AddressBookTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends AddressBookTest {
	

	
	@DataProvider
	public Iterator<Object[]> validGroups() {
		List<Object[]> groups = new ArrayList<Object[]>();
		Groups loaded;
		try {
			loaded = app.groups().loadFromDefaultJSON();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("Неожиданное исключение: ");
			e.printStackTrace();
			return null;
		}
		for (GroupData group : loaded) {
			groups.add(new Object[]{group});
		}
		return groups.iterator();
	}
	
	@Test(dataProvider = "validGroups")
	public void testGroupCreation(GroupData newGroup) {
		app.moveTo().groupsPage();
		Groups before = app.groups().getSet();
		
		app.groups().create(newGroup);
		app.moveTo().groupsPage();
		assertThat(app.groups().getCount(), equalTo(before.size() + 1));
		
		Groups after = app.groups().getSet();
		
		newGroup.withId(after.stream().max(app.groups().getComparator()).get().getIdNumber());
		ToIntFunction<? super GroupData> toInt = (g) -> g.getIdNumber();
		
		assertThat(after, equalTo(
				before.withAdded(newGroup.withId(after.stream().mapToInt(toInt).max().getAsInt()))));

	}
	
	@Test(enabled = false)
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
