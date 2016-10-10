package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupModificationTest extends AddressBookTest {
	@BeforeMethod
	public void ensurePreconditions(){
		app.moveTo().groupsPage();
		if (app.groups().getSet().size()==0) {
			app.groups().create(new GroupData().withId("TestGroupName"));
		}
	}
	 
	@Test
	public void testGroupModification() {
		GroupData oldVersion;
	
		
		Set<GroupData> before = app.groups().getSet();
		oldVersion=before.iterator().next();
		GroupData newVersion=new GroupData().withName("T_1").withHeader("T_2").withFooter("T_3").withId(before.iterator().next().getId());
		
		app.groups().modify(newVersion);
		
		app.groups().returnToGroupPage();
		
		Set<GroupData> after = app.groups().getSet();
		Assert.assertEquals(after.size(), before.size());
		before.remove(oldVersion);
		before.add(newVersion);
		
		Assert.assertEquals(after, before);
	}
	

}
