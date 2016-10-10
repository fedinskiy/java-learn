package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by owlowl on 23.09.16.
 */
public class GroupDeletionTest extends AddressBookTest  {
	
	@BeforeMethod
	public void ensurePreconditions(){
		app.moveTo().groupsPage();
		if (app.groups().getSet().size()==0) {
			app.groups().create(new GroupData().withId("TestGroupName"));
		}
	}
	
	@Test
	public void deleteGroup()
	{
		ensurePreconditions();
		Set<GroupData> before = app.groups().getSet();
		GroupData toDelete=before.iterator().next();
		
		app.groups().delete(toDelete);
		
		Set<GroupData> after = app.groups().getSet();
		Assert.assertEquals(after.size(), before.size()-1);
		before.remove(toDelete);
		
		Assert.assertEquals(before,after);
	}
	

}
